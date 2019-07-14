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
from Report import Report


_is_main_ = False	#To use this file as a module in somewhere else .... 

class DNS_CENSORSHIP:
	TimedOut = 3
	NxDomain = 2
	NoAnswer = 4
	Iterate = 5
    
	ERROR_CODE = 0 #error code for DNS censorship
	IS_CENSORED = "YES"
	report = Report()

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
			ERROR_CODE = 100			
			return False, str;
		if IPAddress(ip).is_private():
			str = "private ip"
			ERROR_CODE = 101
			return False, str;
		if IPAddress(ip).is_reserved():
			str = "reserved ip"
			ERROR_CODE = 102
			return False, str;
		if IPAddress(ip).is_multicast():
			str = "multicast ip"
			ERROR_CODE = 103
			return False, str;
		if IPAddress(ip).is_unicast() and not IPAddress(ip).is_private():
			str = "public ip"
			ERROR_CODE = 104
			return True, str;
		str = "undefined"
		ERROR_CODE = 105
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
					#report.stubby_ip_addresses.append(ip.to_text()) 	#Place in Report
				output.close()
				break
		if isNameResolved:
			return True
		else:
			ERROR_CODE = 106
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
			ERROR_CODE = 107
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
						message = "Censored: Local Dns Server resolved more than one domain name to same ip address"
						ERROR_CODE = 108
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
			self.IS_CENSORED = "NO"
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
			ERROR_CODE = 109
			return False


	def formReport(self, error_code, ip_list_local, ip_list_stubby, is_censored):
		self.report.stubby_ip_addresses.extend(ip_list_stubby)
		self.report.local_ip_addresses.extend(ip_list_local)
		self.report.cenosorship_code = error_code
		self.report.is_censored = is_censored
		return self.report 
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
					ERROR_CODE = 110
					message = "Censored: No Response From Local Dns Server"
					self.writeToDetails(message,HOST)
				else:
					self.printMessage("-->Timed Out Occurred Due to Network Problem or Excessive Load")
					ERROR_CODE = 111
					message = "check network connectivity"
					self.writeToDetails(message,HOST)
					
					
			if not isTimedOut:
				isDomainExist = self.checkDomainExistence(HOST)
				if isDomainExist:
					self.printMessage("-->Remote Dns Server Successfully Resolved "+HOST)
					self.printMessage("-->"+HOST+"s is censored")
					message = "Local Dns Server Returning rrset with rcode set"
					ERROR_CODE = 112
					self.writeToDetails(message,HOST)
				else:
					try:
						self.printMessage("-->Remote Dns Server Unable to Resolve "+HOST)
						self.printMessage("-->Domain Name:"+HOST+" Does not Exist or Unknown Reason")
						message = "Invalid Domain Name or Unknown Error Occured"
						ERROR_CODE = 113
						self.writeToDetails(message,HOST)
					except:
						print("===========================>>> error1")
			#	def formReport(self, error_code, ip_list_local, ip_list_stubby, is_censored):
			return self.formReport(ERROR_CODE, ipListLocal, ipListRemote, self.IS_CENSORED) 
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
						ERROR_CODE = 114
				else:
					self.printMessage("Remote Dns Server Unable to Resolve "+HOST)
					ERROR_CODE = 115
					self.checkIpLocalDns(ipListLocal,HOST)
			else:
				isOverlapped = self.checkOverlappedIp(ipListLocal,ipListRemote)
				if isOverlapped:
					self.printMessage("LSD and RDS Returned Overlapped Ip Address Set")
					self.printMessage("Domain Name is not censored")
					ERROR_CODE = 116
					self.IS_CENSORED = "NO" 	#NOT CENSORED
					message = "Domain Name is not censored"
					self.writeToDetails(message,HOST)
					self.writeToLocalIPList(ipListLocal,HOST)
				else:
					self.printMessage("LSD and RDS Returned Non-Overlapped Ip Address Set")
					self.checkIpLocalDns(ipListLocal,HOST)
					ERROR_CODE = 117
				
				output = open("RemoteIpList.txt", "a")
				for ip in ipListRemote:
					output.write(HOST + ":"+ip+'\n')
				output.close()


			print('\nFinally .... ERROR_CODE = ' + str(self.ERROR_CODE))
			#Finally returning report [TO DO few things more such as isFileCheck etc ]
			return self.formReport(ERROR_CODE, ipListLocal, ipListRemote, self.IS_CENSORED)  

# ------------------------------- TEST MAIN PROGRAM -------------------------------
_is_main_ = False #Make it false for usin it to run by other python programs
if _is_main_ == True :
	dns_check = DNS_CENSORSHIP()
	dns_check.dns_censorship_check("www.ranker.com")



