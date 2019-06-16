import dns
import dns.name
import dns.query
import dns.resolver
from dns.exception import DNSException
import dns.rcode
import socket
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
import sys

class SocketFunctions:
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
		my_resolver.timeout = 10
		my_resolver.lifetime = 10
		answers = 0
		try:
			answers = my_resolver.query(host,'A')
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

