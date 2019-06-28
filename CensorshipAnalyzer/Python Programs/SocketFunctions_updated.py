import dns
import os
import dns.name
import dns.query
import dns.resolver
from dns.exception import DNSException
import dns.rcode
import socket
#from socket import timeout
import socks
#import httplib
from netaddr import *
import pprint
import ipaddress
import subprocess
#import getip
import json
from json import load
#from urllib2 import urlopen
import urllib.request
import sys
import requests
import time
import errno

class SocketFunctions:
	def is_bogon_ip(self,ip):
		networks = [
			"0.0.0.0/8",
			"10.0.0.0/8",
			"100.64.0.0/10",
			"127.0.0.0/8",
			"169.254.0.0/16",
			"172.16.0.0/12",
			"192.0.0.0/24",
			"192.0.2.0/24",
			"192.88.99.0/24",
			"192.168.0.0/16",
			"198.18.0.0/15",
			"198.51.100.0/24",
			"203.0.113.0/24",
			"240.0.0.0/4",
			"255.255.255.255/32",
			"224.0.0.0/4",
		]

		for network in networks:
			try:
				if IPAddress(ip) in IPNetwork(network):
					return True
			except:
				continue

		return False


	def socketConnectLocal(self,ip):
		s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
		s.settimeout(5)
		print(ip)
		port = 80
		status = -1
		try:
			s.connect((ip, port))         # "random" IP address and port
			print("Successfully Connected")
			return True
		except Exception as e:
			print("Caught exception socket.error : %s" % e)
			return False

	def socketConnectTor(self,ip):
		socks.setdefaultproxy(socks.PROXY_TYPE_SOCKS5, "127.0.0.1", 9150, True)
		s = socks.socksocket()
		s.settimeout(10)
		print(ip)
		port = 80
		status = -1
		try:
			s.connect((ip, port))         # "random" IP address and port
			print("Successfully Connected")
			return True
		except Exception as e:
			print("Caught exception socket.error : %s" % e)
			return False

	
	def CheckTorConnection(self,url,ip,port,timeout_1):
		try:
			socks.setdefaultproxy(socks.PROXY_TYPE_SOCKS5, ip, port,True)
			socket.socket = socks.socksocket
			response = requests.get(url,timeout = timeout_1)
			if response.status_code is 200:
				return True
			else:
				return False
		except:
			return False
					

			
		
	def CheckStubbyConnection(self):
		try:
			x = subprocess.check_output(["sudo netstat -lnptu | grep stubby | wc -l"],shell=True)
			count = int(x.decode('utf-8').strip())
			if count is 0:
				return False
			else:
				return True
		except:
			return False
		
	
	def getLocalIp(self):
		s = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
		try:
			s.connect(("10.255.255.255",1))
			local_ip = s.getsockname()[0]
			return True,local_ip
		except Exception as e:
			return False,local_ip

	'''def getIP_1(self):
		ip=""
		try:
			ip = getip.get()
			return True,ip
		except Exception as e:
			return False,ip
	def getIP_2(self):
		ip=""
		try:
			ip = urlopen('http://ip.42.pl/raw').read()
			return True,ip
		except Exception as e:
			return False,ip

	def getIP_3(self):
		ip=""
		try:
			ip = load(urlopen('http://jsonip.com'))['ip']
			return True,ip
		except Exception as e:
			return False,ip


	def getIP_4(self):
		ip=""
		try:
			ip = load(urlopen('https://api.ipify.org/?format=json'))['ip']
			return True,ip
		except Exception as e:
			return False,ip
	'''
	#def getFromLocalDns(self,host):
	def QueryToLocalDnsServer(self,host):
		my_resolver = dns.resolver.Resolver()
		my_resolver.timeout = 5
		my_resolver.lifetime = 5
		answers = 0
		try:
			answers = my_resolver.query(host,'A')
			'''rcode = answers.rcode()
			if rcode != dns.rcode.SERVFAIL:
				print("servfail")
				if rcode == dns.rcode.NXDOMAIN:
					raise Exception('%s does not exist.' % sub)
				else:
					raise Exception('Error %s' % dns.rcode.to_text(rcode))'''
			str = "Successfully resolved"
			return 1, str, answers;
		except dns.resolver.NXDOMAIN:
			str = "No such domain"
			return 2, str, answers;
		except dns.resolver.Timeout:
			str = "Timed out"
			return 3, str, answers;
		except dns.resolver.NoAnswer:
			str = "No answer"
			return 4, str, answers;
		except dns.resolver.NoNameservers:
			str = "No name servers"
			return 5, str, answers;
		except dns.exception.DNSException:
			str = "Unhandled exception"
			return 6, str, answers;
		except:
			return None,None,None;


	#def getFromRemoteDns(self,host):
	def QueryToRemoteDnsServer(self,host):
		my_resolver = dns.resolver.Resolver(configure=False)
		my_resolver.nameservers = ['127.0.0.1']
		my_resolver.timeout = 5
		my_resolver.lifetime = 5
		answers = 0
		try:
			answers = my_resolver.query(host)
			str = "Successfully resolved"
			return 1, str, answers;
		except dns.resolver.NXDOMAIN:
			str = "No such domain"
			return 2, str, answers;
		except dns.resolver.Timeout:
			str = "Timed out"
			return 3, str, answers;
		except dns.resolver.NoAnswer:
			str = "No answer"
			return 4, str, answers;
		except dns.resolver.NoNameservers:
			str = "No name servers"
			return 5, str, answers;
		except dns.exception.DNSException:
			str = "Unhandled exception"
			return 6, str, answers;
		except:
			return None,None,None;
			
			
			
	def tcp_ttl_find(self,host_ip,port,timeout):
		isTimeout = False
		timeout_iterate = 0
		isOther_Error = False
		for i in range(1,64,1):
			try:	
				s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
				s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
				s.settimeout(timeout)
				s.setsockopt(socket.IPPROTO_IP, socket.IP_TTL, i)
				s.connect((host_ip, port))
				return True,i,"3-way handshake successful"
			except socket.timeout:
				isOther_Error = False
				if isTimeout:
					timeout_iterate += 1
				else:
					isTimeout = True
					timeout_iterate = 0
				if timeout_iterate == 10:
					return False,i-9,"timed out"
				#print("timed out")
			except socket.error as err:
				isTimeout = False
				if err.errno != errno.EHOSTUNREACH:
					if isOther_Error:
						return False,i-1,err.__str__()
					else:
						isOther_Error = True
			except:
				return False,None,"unknown error"

