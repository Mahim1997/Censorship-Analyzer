#!/usr/bin/env python

"""
Python DNS Client
(C) 2014 David Lettier
lettier.com
A simple DNS client similar to `nslookup` or `host`.
Does not use any DNS libraries.
Handles only A type records.
"""

import codecs
import sys
import socket
import bitstring, struct # For constructing and destructing the DNS packet.
import time

class DNS_Server_HopCount:
	def to_hex_string(self,x):
		"""
		Encodes either a positive integer or string to its hexadecimal representation.
		"""

		result = "0"
		if x.__class__.__name__ == "int" and x >= 0:
			result = hex(x)
			if x < 16:
				result = "0" + result[2:]
		elif x.__class__.__name__ == "str":
			result = "".join([hex(ord(y))[2:] for y in x])
		return "0x" + result


	def resolve_host_name(self,host_name_to,ttl,timeoutValue,dns_server_ip,server_port):
		"""
		Queries the DNS A record for the given host name and returns the result.
		"""
		result = {'host_name': None, 'ip_address': None}
		host_name_to = host_name_to.split(".")

		# Construct the DNS packet consisting of header + QNAME + QTYPE + QCLASS.

		DNS_QUERY_FORMAT = [
		"hex=id"
		, "bin=flags"
		, "uintbe:16=qdcount"
		, "uintbe:16=ancount"
		, "uintbe:16=nscount"
		, "uintbe:16=arcount"
		]

		DNS_QUERY = {
		"id": "0x1a2b"
		, "flags": "0b0000000100000000" # Standard query. Ask for recursion.
		, "qdcount": 1 # One question.
		, "ancount": 0
		, "nscount": 0
		, "arcount": 0
		}

		# Construct the QNAME:
		# size|label|size|label|size|...|label|0x00

		j = 0
		for i, _ in enumerate(host_name_to):
			host_name_to[i] = host_name_to[i].strip()
			DNS_QUERY_FORMAT.append("hex=" + "qname" + str(j))
			DNS_QUERY["qname" + str(j)] = self.to_hex_string(len(host_name_to[i]))
			j += 1
			DNS_QUERY_FORMAT.append("hex=" + "qname" + str(j))
			DNS_QUERY["qname" + str(j)] = self.to_hex_string(host_name_to[i])
			j += 1

		# Add a terminating byte.

		DNS_QUERY_FORMAT.append("hex=qname" + str(j))

		DNS_QUERY["qname" + str(j)] = self.to_hex_string(0)

		# End QNAME.

		# Set the type and class now.

		DNS_QUERY_FORMAT.append("uintbe:16=qtype")

		DNS_QUERY["qtype"] = 1 # For the A record.

		DNS_QUERY_FORMAT.append("hex=qclass")

		DNS_QUERY["qclass"] = "0x0001" # For IN or Internet.

		# Convert the struct to a bit string.

		data = bitstring.pack(",".join(DNS_QUERY_FORMAT), **DNS_QUERY)

		# Send the packet off to the server.

		#DNS_IP = "8.8.8.8" # Google public DNS server IP.
		#DNS_IP = "9.9.9.9"
		#DNS_IP = "208.67.222.222"
		#DNS_IP = "1.1.1.1"
		#DNS_IP = "64.6.64.6"
		DNS_IP = dns_server_ip
		
		DNS_PORT = server_port # DNS server port for queries.

		READ_BUFFER = 1024 # The size of the buffer to read in the received UDP packet.

		address = (DNS_IP, DNS_PORT) # Tuple needed by sendto.


		try:
			client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM) # Internet, UDP.

			client.settimeout(timeoutValue)
			#ttl = 10											#setting ttl value
			client.setsockopt(socket.IPPROTO_IP, socket.IP_TTL, ttl)					#modify socket ttl

			client.sendto(data.tobytes(), address) # Send the DNS packet to the server using the port.

			# Get the response DNS packet back, decode, and print out the IP.

			# Get the response and put it in data. Get the responding server address and put it in address.
			data, address = client.recvfrom(READ_BUFFER)

		except socket.error as e:
			return False,-1,e,result

		# Convert data to bit string.

		data = bitstring.BitArray(bytes=data)

		# Unpack the receive DNS packet and extract the IP the host name resolved to.

		# Get the host name from the QNAME located just past the received header.

		host_name_from = []

		# First size of the QNAME labels starts at bit 96 and goes up to bit 104.
		# size|label|size|label|size|...|label|0x00

		x = 96
		y = x + 8

		for i, _ in enumerate(host_name_to):

			# Based on the size of the very next label indicated by
			# the 1 octet/byte before the label, read in that many
			# bits past the octet/byte indicating the very next
			# label size.

			# Get the label size in hex. Convert to an integer and times it
			# by 8 to get the number of bits.

			increment = (int(str(data[x:y].hex), 16) * 8)

			x = y
			y = x + increment

			# Read in the label, converting to ASCII.

			host_name_from.append(codecs.decode(data[x:y].hex, "hex_codec").decode())

			# Set up the next iteration to get the next label size.
			# Assuming here that any label size is no bigger than
			# one byte.

			x = y
			y = x + 8 # Eight bits to a byte.

	  # Get the response code.
	  # This is located in the received DNS packet header at
	  # bit 28 ending at bit 32.

		response_code = str(data[28:32].hex)


		# Check for errors.

		if (response_code == "0"):

			result['host_name'] = ".".join(host_name_from)

			# Assemble the IP address the host name resolved to.
			# It is usually the last four octets of the DNS
			# packet--at least for A records.

			result['ip_address'] = ".".join([
			str(data[-32:-24].uintbe)
			, str(data[-24:-16].uintbe)
			, str(data[-16:-8].uintbe)
			, str(data[-8:].uintbe)
			])

		elif (response_code == "1"):

			#print("\nFormat error. Unable to interpret query.\n")
			return True,1,"Format error. Unable to interpret query.",result
		elif (response_code == "2"):

			#print("\nServer failure. Unable to process query.\n")
			return True,2,"Server failure. Unable to process query.",result
		elif (response_code == "3"):

			#print("\nName error. Domain name does not exist.\n")
			return True,3,"Name error. Domain name does not exist.",result
		elif (response_code == "4"):

			#print("\nQuery request type not supported.\n")
			return True,4,"Query request type not supported.",result
		elif (response_code == "5"):
			#print("\nServer refused query.\n")
			return True,5,"Server refused query.",result
		return True,response_code,"Successfully Resolved.",result
		
	def find_hop_count(self,domain_name,timeout,max_hopCount,server_ip,server_port):
		for i in range(1,max_hopCount):
			print(i)
			status,error_no,message,result = self.resolve_host_name(domain_name,i,timeout,server_ip,server_port)
			if status:
				#print(result['ip_address'])
				return i
					
		return None
	'''
ob = DNS_Server_HopCount()
result = ob.find_hop_count("www.xnxx.com",4,32,'172.16.220.10',53)
#status,error_no,message,result = ob.resolve_host_name("www.google.com",4,5,'103.231.163.1',53)
print(result)'''
	
