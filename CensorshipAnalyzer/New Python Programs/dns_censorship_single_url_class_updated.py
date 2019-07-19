import dns
import dns.name
import dns.query
import dns.resolver
from dns.exception import DNSException
import dns.rcode
import socket
import socks
from netaddr import *
import pprint
import ipaddress
import subprocess
# import getip
import json
from json import load
import sys
import time
from SocketFunctions_updated import SocketFunctions
import bitstring, struct  # For constructing and destructing the DNS packet.

'''from DNS_TTL import DNS_Server_HopCount
from Local_Dns_Server_Ip import OS_Commands
from pcap_filter import pcap_filter'''
from Report import Report

_is_main_ = False  # To use this file as a module in somewhere else ....


class DNS_CENSORSHIP:
	TimedOut = 3
	NxDomain = 2
	NoAnswer = 4
	Iterate = 5

	ERROR_CODE = 0  # error code for DNS censorship
	IS_CENSORED = "NO"
	report = Report()

	def __init__(self):
		self.server_ttl = -1
		self.dns_server_ip = None
		self.local_ip = None
		self.dns_server_ip = None
		self.device_mac_address = None
		self.next_hop_mac_address = None
		self.output = None

	def get_server_ttl(self, domain_name, timeout, max_ttl, server_ip, server_port):
		try:
			ob = DNS_Server_HopCount()
			result = ob.find_hop_count(domain_name, timeout, max_ttl, server_ip, server_port)
			if result is None:
				return None
			return result
		except:
			# print("Exception in Dns_ttl_controle->get_server_ttl")
			return None

	def get_dns_server_ip(self, UncensoredDomainName, timeout, server_port):
		try:
			local_dns_server_ip = OS_Commands()
			self.output = local_dns_server_ip.analyze_nslookup_result(UncensoredDomainName, timeout,
																	  server_port)  # return None in case failure
			return self.output
		except:
			# print("Exception in Dns_ttl_controle->get_dns_server_ip")
			return None

	def get_dns_server_ip_tshark(self, UncensoredDomainName, timeout, server_port):
		try:
			ob = pcap_filter()
			self.status, self.local_ip, self.dns_server_ip, self.device_mac_address, self.next_hop_mac_address = ob.filtering()
			return self.dns_server_ip
		except:
			# print("Exception in Dns_ttl_controle-L->get_dns_server_ip_tshark")
			return None

	def get_host_ttl(self, domain_name, timeout, max_ttl, server_ip,
					 server_port):  # max_ttl must be greater than dns_server hop_count from get_server_ttl
		try:
			ob = DNS_Server_HopCount()
			result = ob.find_hop_count(domain_name, timeout, max_ttl, server_ip, server_port)
			return result

		except:
			# print("Exception in Dns_ttl_controle->dns_censorship_check")
			return None

	def isLocalDnsOk(self):
		"Just checking timed out is occured for excessive load or network problem"
		# make sure that HOST is not censored in this network
		dnsOb = SocketFunctions()
		HOST = "www.google.com"
		isNameResolved = False
		for i in range(self.Iterate):
			idLocal, messageLocal, answersFromLocal = dnsOb.QueryToLocalDnsServer(HOST)
			if idLocal == 1:
				isNameResolved = True
				break
		if isNameResolved:
			# self.printMessage("-->Local Dns Server Successfully Resolved "+ HOST)
			return True
		else:
			# self.printMessage("-->Local Dns Server Unable to Resolve "+ HOST)
			ERROR_CODE = 107
			return False

	def frequencyAnalysis(self, ipList, HOST):
		f = open("LocalIpList.txt", "r")
		domains = f.read().splitlines()
		for host_ip in domains:
			host = host_ip.split(':')[0]
			ip = host_ip.split(':')[1]
			for local_ip in ipList:
				if ip == local_ip and host != HOST:
					str1 = host.split('.')
					count1 = 0
					for i in str1:
						count1 = count1 + 1
					str2 = HOST.split('.')
					count2 = 0
					for i in str2:
						count2 = count2 + 1
					if str1[count1 - 1] == str2[count2 - 1] and str1[count1 - 2] == str2[count2 - 2]:
						f.close()
						return False
					else:
						message = "Censored: Local Dns Server resolved more than one  Domain name to same ip address"
						# self.writeToDetails(message,HOST)
						ERROR_CODE = 108
						f.close()
						return True

		f.close()
		return False

	def checkOverlappedIp(self, listLocal, listRemote):
		a_set = set(listLocal)
		b_set = set(listRemote)
		if len(a_set.intersection(b_set)) > 0:
			return True
		else:
			ERROR_CODE = 109
			return False

	def ipMaching(self, ipLocal, ipRemote):
		for remote_ip in ipRemote:
			ip_R = remote_ip.split('.')
			for local_ip in ipLocal:
				ip_L = local_ip.split('.')
				if ip_R[0] == ip_L[0] and ip_R[1] == ip_L[1] and ip_R[2] == ip_L[2]:
					return True
				if ip_R[0] == ip_L[0] and ip_R[1] == ip_L[1]:
					return True
				if ip_R[0] == ip_L[0]:
					return True
		return False

	def formReport(self, error_code, ip_list_local, ip_list_stubby, is_censored):
		self.report.local_ip_addresses = []
		self.report.stubby_ip_addresses = []
		self.report.stubby_ip_addresses.extend(ip_list_stubby)
		self.report.local_ip_addresses.extend(ip_list_local)
		self.report.cenosorship_code = error_code
		self.report.is_censored = is_censored
		return self.report

	def send_to_database(self, domain_name_1, server_ip, max_ttl):
		censored_server_hop_count = self.get_host_ttl(domain_name_1, 5, max_ttl, server_ip,
													  53)  # getting hop-count for a pbw
		if censored_server_hop_count is None:
			censored_server_hop_count = server_hop_count
		print("Hop Count: " + censored_server_hop_count.__str__())

	# def dns_censorship_check(self,HOST,dns_server_ip,max_ttl):
	def dns_censorship_check(self, HOST):
		self.report.type_of_testing = "DNS"
		dnsOb = SocketFunctions()
		ipListLocal = []
		ipListRemote = []
		print("\n\n---------------Dns Resolution Of " + HOST + ":-------------------\n\n")
		print("->Local Dns Server:")
		timeoutL = False
		nxdomainL = False
		servfailL = False
		noanswerL = False
		timeoutR = False
		nxdomainR = False
		servfailR = False
		noanswerR = False
		answer = False
		countL = 0
		countR = 0

		for i in range(self.Iterate):
			idLocal, messageLocal, answersFromLocal = dnsOb.QueryToLocalDnsServer(HOST)
			if idLocal is 1:
				answer = True
				for answer in answersFromLocal:
					# print("Local:"+answer.to_text())
					if answer.to_text() not in str(ipListLocal):
						ipListLocal.append(answer.to_text())
						print("Local:" + answer.to_text())
			elif idLocal is 2:
				nxdomainL = True
			elif idLocal is 3:
				countL = countL + 1
			elif idLocal is 4:
				noanswerL = True
			elif idLocal is 5:
				servfailL = True
			if idLocal != 1:
				time.sleep(1)
		if countL is self.Iterate:
			timeoutL = True
		if not answer:
			isLDSOk = self.isLocalDnsOk()
			answer = False
			for i in range(self.Iterate):
				idRemote, messageRemote, answersFromRemote = dnsOb.QueryToRemoteDnsServer(HOST)
				if idRemote is 1:
					answer = True
					for answer in answersFromRemote:
						# print("Remote:"+answer.to_text())
						if answer.to_text() not in str(ipListRemote):
							ipListRemote.append(answer.to_text())
							print("Remote:" + answer.to_text())
				elif idRemote is 2:
					nxdomainR = True
				elif idRemote is 3:
					countR = countR + 1
				elif idRemote is 4:
					noanswerR = True
				elif idRemote is 5:
					servfailR = True
				if idRemote != 1:
					time.sleep(1)
			if countR is self.Iterate:
				timeoutR = True

			if answer:
				for ip in ipListRemote:
					self.report.dns_description.ip_address_list_stubby.append(ip)
				if isLDSOk:
					print(HOST + " is censored")
					self.report.is_censored = 1
				else:
					print("Local Dns Server may be down")  ###########################
				if nxdomainL:
					self.report.dns_description.is_nxDomain = 1
				elif servfailL:
					self.report.dns_description.is_topExistingButAuthNotExisting = 1
				elif noanswerL:
					self.report.dns_description.is_noAnswerPacket = 1
				elif timeoutL:
					self.report.dns_description.is_timeout_local_server = 1
				else:
					self.report.dns_description.is_unknown_error = 1

			else:
				print("DNS Resolution Unsuccessful")
				if nxdomainR and nxdomainL:
					print(HOST + " is not censored")
					self.report.dns_description.is_nxDomain = 1
				elif servfailL and servfailR:
					print(HOST + " is not censored")
					self.report.dns_description.is_topExistingButAuthNotExisting = 1
				elif noanswerL and noanswerR:
					print(HOST + " is not censored")
					self.report.dns_description.is_noAnswerPacket = 1
				elif timeoutL and timeoutR:
					print(HOST + " is not censored")
					self.report.dns_description.is_timeout = 1
				else:
					if nxdomainR and (not nxdomainL):
						print(HOST + " is censored")
						self.report.is_censored = 1
						if nxdomainL:
							self.report.dns_description.is_nxDomain = 1
						elif servfailL:
							self.report.dns_description.is_topExistingButAuthNotExisting = 1
						elif noanswerL:
							self.report.dns_description.is_noAnswerPacket = 1
						elif timeoutL:
							self.report.dns_description.is_timeout_local_server = 1
						else:
							self.report.dns_description.is_unknown_error = 1
		else:
			contain_bogon_ip = False
			id = -1
			for ip in ipListLocal:
				self.report.dns_description.ip_address_list_local.append(ip)
			for ip in ipListLocal:
				contain_bogon_ip, id = dnsOb.is_bogon_ip(ip)
				if contain_bogon_ip:
					break
			answer = False
			for i in range(self.Iterate):
				idRemote, messageRemote, answersFromRemote = dnsOb.QueryToRemoteDnsServer(HOST)
				if idRemote is 1:
					answer = True
					for answer in answersFromRemote:
						# print("Remote1:"+answer.to_text())
						if answer.to_text() not in str(ipListRemote):
							ipListRemote.append(answer.to_text())
							print("Remote:" + answer.to_text())
				elif idRemote is 2:
					nxdomainR = True
				elif idRemote is 3:
					countR = countR + 1
				elif idRemote is 4:
					noanswerR = True
				elif idRemote is 5:
					servfailR = True
				if idRemote != 1:
					time.sleep(1)
			if countR is self.Iterate:
				timeoutR = True
			if not answer:
				print("DNS Resolution Unsuccessful")
				self.report.dns_description.is_stubby_failed = 1
				if contain_bogon_ip:
					print(HOST + " is censored")
					self.report.is_censored = 1
					if id == 1:
						print("loopback ip returned")
						self.report.dns_description.is_loopback = 1
					elif id == 2:
						print("multicast ip returned")
						self.report.dns_description.is_multicast = 1
					elif id == 3:
						print("braodcast ip returned")
						self.report.dns_description.is_broadcast = 1
					elif id == 4:
						print("private ip returned")
						self.report.dns_description.is_private = 1
					elif id == 5:
						print("bogon ip returned")
						self.report.dns_description.is_bogon = 1
				elif not timeoutR:
					print(HOST + " is censored")
					self.report.is_censored = 1
			else:
				for ip in ipListRemote:
					self.report.dns_description.ip_address_list_stubby.append(ip)
				isOverlapped = self.checkOverlappedIp(ipListLocal, ipListRemote)
				if not isOverlapped:
					if contain_bogon_ip:
						print(HOST + " is censored")
						self.report.is_censored = 1
						if id == 1:
							print("loopback ip returned")
							self.report.dns_description.is_loopback = 1
						elif id == 2:
							print("multicast ip returned")
							self.report.dns_description.is_multicast = 1
						elif id == 3:
							print("braodcast ip returned")
							self.report.dns_description.is_broadcast = 1
						elif id == 4:
							print("private ip returned")
							self.report.dns_description.is_private = 1
						elif id == 5:
							print("bogon ip returned")
							self.report.dns_description.is_bogon = 1
					else:
						isOverlapped = self.ipMaching(ipListLocal, ipListRemote)
						if isOverlapped:
							print(HOST + " is not censored(first 8-24 bit matched)")
							self.report.dns_description.is_first_8_to_24_bit_match = 1
						else:
							print("Non-overlapped ip set")
							self.report.dns_description.is_non_overlapping_ip_list = 1

				else:
					print(HOST + " is not censored")
					print("Local Dns Server and Stubby Return Overlapped Ip Address")

	# return self.formReport(self.ERROR_CODE, ipListLocal, ipListRemote, self.IS_CENSORED)


is_Main = True
if is_Main:  # run as main
	dns_check = DNS_CENSORSHIP()
	dns_check.dns_censorship_check("www.redtube.com")
	str_report = dns_check.report.getReportString()
	print("Returned Report:")
	print(str_report)

'''
dns_check = DNS_CENSORSHIP()
f = open("fileURLS.txt","r")
domains = f.read().splitlines()
for HOST in domains:
	dns_check.dns_censorship_check(HOST)'''
'''
#print("Start")
timeout = 3
server_port = 53
domain_name = "www.google.com"
domain_name_1 = "bdmirror24.net"
max_ttl_server = 15
ob = DNS_CENSORSHIP()
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
max_ttl = max_ttl+2'''

'''

censored_server_hop_count = ob.dns_censorship_check(domain_name_1,timeout,max_ttl,server_ip,server_port)	#getting hop-count for a pbw
if censored_server_hop_count is None:
	censored_server_hop_count = server_hop_count
	print("server is not responsed")
	exit(1)
print(censored_server_hop_count)
#if output is none then dns resolution is unsuccessfull
'''
'''
dns_check = DNS_CENSORSHIP()
f = open("domainName.txt","r")
domains = f.read().splitlines()
for HOST in domains:
	dns_check.dns_censorship_check(HOST,server_ip,max_ttl)'''
