import sys
import socket
import bitstring, struct # For constructing and destructing the DNS packet.
from DNS_TTL import DNS_Server_HopCount
from Local_Dns_Server_Ip import OS_Commands
from pcap_filter import pcap_filter




class Dns_ttl_controle:
	def __init__(self):
		self.server_ttl = -1
		self.dns_server_ip = None
		self.local_ip = None
		self.dns_server_ip = None
		self.device_mac_address = None
		self.next_hop_mac_address = None
		self.output = None
		
	def get_server_ttl(self,domain_name,timeout,max_ttl,server_ip,server_port):
		try:
			ob = DNS_Server_HopCount()
			result = ob.find_hop_count(domain_name,timeout,max_ttl,server_ip,server_port)
			if result is None:
				return None
			return result
		except:
			#print("Exception in Dns_ttl_controle->get_server_ttl")
			return None
			
	
	def get_dns_server_ip(self,UncensoredDomainName,timeout, server_port):
		try:
			local_dns_server_ip = OS_Commands()
			self.output = local_dns_server_ip.analyze_nslookup_result(UncensoredDomainName, timeout, server_port)		#return None in case failure
			return self.output
		except:
			#print("Exception in Dns_ttl_controle->get_dns_server_ip")
			return None
			
		
	def get_dns_server_ip_tshark(self,UncensoredDomainName,timeout, server_port):
		try:
			ob = pcap_filter()
			self.status,self.local_ip,self.dns_server_ip,self.device_mac_address,self.next_hop_mac_address = ob.filtering()
			return self.dns_server_ip
		except:
			#print("Exception in Dns_ttl_controle-L->get_dns_server_ip_tshark")
			return None
		
	def dns_censorship_check(self,domain_name,timeout,max_ttl,server_ip,server_port):			#max_ttl must be greater than dns_server hop_count from get_server_ttl
		try:
			ob = DNS_Server_HopCount()
			result = ob.find_hop_count(domain_name,timeout,max_ttl,server_ip,server_port)
			return result
				
		except:
			#print("Exception in Dns_ttl_controle->dns_censorship_check")
			return None
			
			#return None

#print("Start")
timeout = 5
server_port = 53
domain_name = "www.google.com"
domain_name_1 = "bdmirror24.net"
max_ttl_server = 20
ob = Dns_ttl_controle()
server_ip = ob.get_dns_server_ip_tshark(domain_name,timeout+1,server_port)			#getting dns server ip address
if server_ip is None:
	print("server ip is not resolved")
	exit(1)
print(server_ip)
server_hop_count = ob.get_server_ttl(domain_name,timeout,max_ttl_server,server_ip,server_port)	#getting dns server hop-distance
if server_hop_count is None:
	print("server hop count is not found")
	exit(1)
print(server_hop_count)
max_ttl = int(server_hop_count)
max_ttl = max_ttl+2



censored_server_hop_count = ob.dns_censorship_check(domain_name_1,timeout,max_ttl,server_ip,server_port)	#getting hop-count for a pbw
if censored_server_hop_count is None:
	censored_server_hop_count = server_hop_count
	print("server is not responsed")
	exit(1)
print(censored_server_hop_count)
#if output is none then dns resolution is unsuccessfull
