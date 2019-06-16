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
from SocketFunctions import SocketFunctions


_is_main_ = False	#To use this file as a module in somewhere else .... 

class DNS_CENSORSHIP:
	TimedOut = 3
	NxDomain = 2
	NoAnswer = 4
	Iterate = 5
    

	def printMessage(self,message):
		print(message) 


	def getMyGlobalIp(self):
		dnsOb = SocketFunctions()
		ip=""
		isIp,ip = dnsOb.getIP_1()
		if isIp:
			return True,ip
		isIp,ip = dnsOb.getIP_2()
		if isIp:
			return True,ip
		isIp,ip = dnsOb.getIP_3()
		if isIp:
			return True,ip
		isIp,ip = dnsOb.getIP_4()
		if isIp:
			return True,ip
		return False,ip



	def __checkIpValid(self,ip):
		"This checks if this ip resides in private ip range,loopback ip range"
		if IPAddress(ip).is_loopback():
			str = "loopback ip"
			return False, str;
		if IPAddress(ip).is_private():
			str = "private ip"
			return False, str;
		if IPAddress(ip).is_reserved():
			str = "reserved ip"
			return False, str;
		if IPAddress(ip).is_multicast():
			str = "multicast ip"
			return False, str;
		if IPAddress(ip).is_unicast() and not IPAddress(ip).is_private():
			str = "public ip"
			return True, str;
		str = "undefined"
		return True, str;

    


	def writeToLocalIPList(self,ipList,HOST):
		output = open("LocalIpList.txt", "a")
		for answer in ipList:
			output.write(HOST + ":"+answer+'\n')
		output.close()

	def writeToDetails(self,message,HOST):
		output = open("Details.txt", "a")
		output.write(HOST + ": ")
		output.write('\t' + message +'\n\n')
		output.close()

	def checkDomainExistence(self,HOST):
		dnsOb = SocketFunctions()
		isNameResolved = False
		for i in range(self.Iterate):
			idRemote,messageRemote,answersFromRemote = dnsOb.QueryToRemoteDnsServer(HOST)
			if idRemote == 1:
				isNameResolved = True
				output = open("RemoteIpList.txt", "a")
				for ip in answersFromRemote:
					output.write(HOST + ":"+ip.to_text()+'\n')
				output.close()
				break
		if isNameResolved:
			return True
		else:
			return False

	def isLocalDnsOk(self):
		"Just checking timed out is occured for excessive load or network problem"
		#make sure that HOST is not censored in this network
		dnsOb = SocketFunctions()
		HOST = "www.google.com"
		isNameResolved = False
		for i in range(self.Iterate):
			idLocal,messageLocal,answersFromLocal = dnsOb.QueryToLocalDnsServer(HOST)
			if idLocal == 1:
				isNameResolved = True
				break
		if isNameResolved:
			self.printMessage("-->Local Dns Server Successfully Resolved "+ HOST)
			return True
		else:
			self.printMessage("-->Local Dns Server Unable to Resolve "+ HOST)
			return False

	def frequencyAnalysis(self,ipList,HOST):
		f = open("LocalIpList.txt","r")
		domains = f.read().splitlines()
		for host_ip in domains:
			host = host_ip.split(':')[0]
			ip = host_ip.split(':')[1]
			for local_ip in ipList:
				if ip == local_ip and host != HOST:
					str1 = host.split('.')
					count1 = 0
					for i in str1:
						count1 = count1+1
					str2 = HOST.split('.')
					count2 = 0
					for i in str2:
						count2 = count2+1
					if str1[count1-1] == str2[count2-1] and str1[count1-2] == str2[count2-2]:
						f.close()
						return False
					else:
						message = "Censored: Local Dns Server resolved more than one  Domain name to same ip address"
						self.writeToDetails(message,HOST)
						f.close()
						return True
                
                
		f.close()
		return False


	def checkIpLocalDns(self,ipList,HOST):
		isMultipleIp = self.frequencyAnalysis(ipList,HOST)
		if isMultipleIp:
			self.printMessage("Local Dns Server Resolved Multiple Domain Name to Same Ip Address")
			self.writeToLocalIPList(ipList,HOST)
			return
		ispublic = True
		for answer in ipList:
			ispublic,str = self.__checkIpValid(answer)
			if ispublic == False:
				self.printMessage("Local Dns Server Returned Non-Public Ip Address: "+str)
				message = "Local Dns Server Returned Non-Public Ip Address"
				self.writeToDetails(message,HOST)
				break
		if ispublic:
			self.writeToLocalIPList(ipList,HOST)
			self.printMessage("Domain Name is not censored")
			message = "Domain Name is not censored"
			self.writeToDetails(message,HOST)


	def _checkIpLocalDns(self,ipList,HOST):
		isMultipleIp = self.frequencyAnalysis(ipList,HOST)
		if isMultipleIp:
			self.printMessage("Local Dns Server Resolved Multiple Domain Name to Same Ip Address")
			self.writeToLocalIPList(ipList,HOST)
			return False
		ispublic = True
		for answer in ipList:
			ispublic,str = self.__checkIpValid(answer)
			if ispublic == False:
				self.printMessage( "Local Dns Server Returned Non-Public Ip Address: "+str)
				message = "Local Dns Server Returned Non-Public Ip Address"
				self.writeToDetails(message,HOST)
				return False
		if ispublic:
			return True

	def checkOverlappedIp(self,listLocal,listRemote):
		a_set = set(listLocal) 
		b_set = set(listRemote) 
		if len(a_set.intersection(b_set)) > 0: 
			return True  
		else: 
			return False


    #success,publicip = getMyGlobalIp()
    #if success:
    #   print("Public Ip:%s"%(publicip))
	def dns_censorship_check(self,HOST):
		dnsOb = SocketFunctions()
		f1 = open("Details.txt","w")
		f1.close()
		f1 = open("LocalIpList.txt","w")
		f1.close()
		f1 = open("RemoteIpList.txt","w")
		f1.close()
		#f = open("domainName.txt","r")
		isNameResolved=False
		errorCodeLocal = []
		errorCodeRemote = []
		ipListLocal = [] 
		ipListRemote = []
		#domains = f.read().splitlines()
		#for HOST in domains:
		del errorCodeLocal [:]
		del errorCodeRemote [:]
		del ipListLocal [:]
		del ipListRemote [:]
		self.printMessage("\n\n---------------Dns Resolution Of "+HOST+":-------------------\n\n")
		self.printMessage("->Local Dns Server:")
		isNameResolved = False
		attempt_count=1
		for i in range(self.Iterate):
			idLocal,messageLocal,answersFromLocal = dnsOb.QueryToLocalDnsServer(HOST)
			errorCodeLocal.append(idLocal)
			self.printMessage("-->Attempt "+attempt_count.__str__()+":"+messageLocal)
			if idLocal == 1:
				isNameResolved = True
				for answer in answersFromLocal:
					ipListLocal.append(answer.to_text())
				break
			attempt_count = attempt_count+1
		if not isNameResolved:
			isNxDomain = False
			isTimedOut = True
			for errorcode in errorCodeLocal:
				if errorcode != self.TimedOut:
					isTimedOut = False
					break
			if isTimedOut:
				isOk = self.isLocalDnsOk()
				if isOk:
					self.printMessage("-->No Network Problem or Excessive Load")
					self.printMessage("Censored: No Response From Local Dns Server")
					message = "Censored: No Response From Local Dns Server"
					self.writeToDetails(message,HOST)
				else:
					self.printMessage("-->Timed Out Occurred Due to Network Problem or Excessive Load")
					message = "check network connectivity"
					self.writeToDetails(message,HOST)
					
					
			if not isTimedOut:
				isDomainExist = self.checkDomainExistence(HOST)
				if isDomainExist:
					self.printMessage("-->Remote Dns Server Successfully Resolved "+HOST)
					self.printMessage("-->"+HOST+"s is censored")
					message = "Local Dns Server Returning rrset with rcode set"
					self.writeToDetails(message,HOST)
				else:
					self.printMessage("-->Remote Dns Server Unable to Resolve "+HOST)
					self.printMessage("-->Domain Name:"+HOST+" Does not Exist or Unknown Reason")
					message = "Invalid Domain Name or Unknown Error Occured"
					self.writeToDetails(message,HOST)
		else:
			isNameResolved = False
			self.printMessage("->Remote Dns Server:")
			attempt_count = 1
			isNxdomain = True
			for i in range(self.Iterate):
				idRemote,messageRemote,answersFromRemote = dnsOb.QueryToRemoteDnsServer(HOST)
				errorCodeRemote.append(idRemote)
				self.printMessage("-->Attempt "+attempt_count.__str__()+":"+messageRemote)
				if idRemote != 2:
					isNxdomain = False
				if idRemote == 1:
					isNameResolved = True
					for answer in answersFromRemote:
						ipListRemote.append(answer.to_text())
					break
				attempt_count = attempt_count+1
			if not isNameResolved:
				if isNxdomain:
					istrue = self._checkIpLocalDns(ipListLocal,HOST)
					if istrue:
						self.printMessage("Domain Does not Exist or Unknown Error")
				else:
					self.printMessage("Remote Dns Server Unable to Resolve "+HOST)
					self.checkIpLocalDns(ipListLocal,HOST)
			else:
				isOverlapped = self.checkOverlappedIp(ipListLocal,ipListRemote)
				if isOverlapped:
					self.printMessage("LSD and RDS Returned Overlapped Ip Address Set")
					self.printMessage("Domain Name is not censored")
					message = "Domain Name is not censored"
					self.writeToDetails(message,HOST)
					self.writeToLocalIPList(ipListLocal,HOST)
				else:
					self.printMessage("LSD and RDS Returned Non-Overlapped Ip Address Set")
					self.checkIpLocalDns(ipListLocal,HOST)
				
				output = open("RemoteIpList.txt", "a")
				for ip in ipListRemote:
					output.write(HOST + ":"+ip+'\n')
				output.close()


# ------------------------------- TEST MAIN PROGRAM -------------------------------

if _is_main_ == True :
	dns_check = DNS_CENSORSHIP()
	dns_check.dns_censorship_check("www.google.com")



