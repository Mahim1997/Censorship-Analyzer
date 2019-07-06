from scapy.all import *
import socks
import socket
import time
from SocketFunctions_updated import SocketFunctions
from TCP_Description import TCP_Description

tor_url = "https://check.torproject.org/"
tor_ip = "127.0.0.1"
tor_port_1 = 9050 
tor_port_2 = 9150
tor_timeout = 10


dnsOb = SocketFunctions()

class TCP_3_WAY_HANDSHAKE:
	Iterate = 5
	msg_to_set = ""
	tor_port = 9050

	hop_count_http = -1
	hop_count_https = -1
	tor_connection_unsuccessfull_HTTP = False
	tor_connection_unsuccessfull_HTTPS = False

	def is_tor_running(self):
#---------------------------------------------------Tor Connection Check---------------------------------------------------
		
		Tor_Status = dnsOb.CheckTorConnection(tor_url,tor_ip,tor_port_1,tor_timeout)						#checking tor connection is ok or not
		if Tor_Status:
			return tor_port_1
		else:
			Tor_Status = dnsOb.CheckTorConnection(tor_url,tor_ip,tor_port_1,tor_timeout)
			if Tor_Status:
				return tor_port_2
			else:
				print("Tor is not listening.Please open the tor network first.")
				str_msg = "[[ERROR]] TOR IS NOT LISTENING .. OPEN THE TOR NETWORK FIRST"
				return None
		#print("Tor Status: Listening")
		
#---------------------------------------------------Stubby Connection Check---------------------------------------------------
	def is_stubby_running(self):
		stubby_status = dnsOb.CheckStubbyConnection()
		return stubby_status
		'''if stubby_status:
			print("Stubby status: OK")
		else:
			print("Stubby service is not running.")'''
			
		
	
	#def tcp_handshake_check(self,HOST,iteration,tor_ip,tor_port):
	def tcp_handshake_check(self,HOST,iteration):	
		'''ip = "172.217.194.99"
		statusLocal = dnsOb.socketConnectTor(ip)
		print(statusLocal)'''

		self.tor_port = self.is_tor_running()
		self.msg_to_set = "url:" + HOST
		#---------------------------------------------------------------DNS resolution-----------------------------------------------
		# print("Checking TCP for url: " + HOST)
		
		isResolved = False
		ipListRemote = []
		for i in range(iteration):
			idRemote,messageRemote,answersFromRemote = dnsOb.QueryToRemoteDnsServer(HOST)
			if idRemote == 1:
				isResolved = True
				for answer in answersFromRemote:
					if answer.to_text() not in str(ipListRemote):
						ipListRemote.append(answer.to_text()) 
			#else:
				#print(messageRemote)
		if not isResolved:
			#print ("Domain doesnot exist")
			self.msg_to_set = "DOMAIN DOES NOT EXIST"
			return self.msg_to_set
				
		ip_itr = 1	
		## TO DO !!!
		successIter_tor_list_http = []
		successIter_ls_list_http = []  #For HTTP

		successIter_tor_list_https = []
		successIter_ls_list_https = [] #For HTTPS 

		censored_arr_http = []
		censored_arr_https = []
		# TO DO !!!

		#print("Printing ip addresses of ipListRemote:")
		#for ip in ipListRemote:
		#	ip_itr = ip_itr + 1
		#	print(str(ip_itr) + "->" + ip)

		#---------------------------------------------------------------------------------------------------------------------------
		
		statusTor = False
		statusLocal = False
		
		#--------------------- My beginning --------------------------------------
		#ipIter = 0
		for ip in ipListRemote: 	#EACH IP
			#HTTP CHECK
			#ipIter = ipIter + 1
			port = 80
			self.msg_to_set = self.msg_to_set + "\nIP:" + str(ip) + " , port = " + str(port) + "\n"
			for j in range(self.Iterate):
				# 5 times iteration
				#print(str(j+1) + "th try for TOR CHeck ... ip = " + str(ip))
				statusTor = dnsOb.socketConnectTor(ip)
				if not statusTor:	#Do not check for local server connection
					#print("NOT statusTOR (i.e. false)")	
					pass
					# self.msg_to_set = self.msg_to_set + "##" + str((j+1)) + "th iteration TOR Connection Unsuccesful"
				if statusTor:	#Check for local server connection
					self.msg_to_set = self.msg_to_set + "##" + str((j+1)) + "th iteration TOR Connection Succesful"
					#successIter_tor_list_http[ipIter] = (j + 1) 	#Put the (j + 1)th index for successIterTorList
					successIter_tor_list_http.append((j+1))
					#print("STATUS TOR IS TRUE")
					#Now 5 times local ip target
					for k in range(self.Iterate):
						statusLocal = dnsOb.socketConnectLocal(ip)
						#print(str(k+1) + "th check for Local Connection ip = " + str(ip))
						if not statusLocal:
							#print("Status LDS is false")
							# self.msg_to_set = self.msg_to_set + "#" + str((k+1)) + "th iteration Local Server Connection Unsuccesful"
							time.sleep(2)
						if statusLocal:
							# print("Status LDS is true")
							#successIter_ls_list_http[ipIter] = (k + 1)
							successIter_ls_list_http.append((k+1))
							#censored_arr_http[ipIter] = 0 	#Not censored for this Ip address
							censored_arr_http.append(0)
							self.msg_to_set = self.msg_to_set + "#" + str((k+1)) + "th iteration Local Server Connection Succesful"
							self.msg_to_set = self.msg_to_set + "#Not censored for ip = " + str(ip) + " , in port = " + str(port)
							break

					if not statusLocal:
						status,self.hop_count_http,message = dnsOb.tcp_ttl_find(ip,port,2)
						if status:
							#censored_arr_http[ipIter] = 0
							censored_arr_http.append(0)
							self.msg_to_set = self.msg_to_set + "#Not TCP Censored for ip = " + ip + " , port = " + port.__str__()
						else:
							#censored_arr_http[ipIter] = 1
							censored_arr_http.append(1)
							self.msg_to_set = self.msg_to_set + "#TCP Censored for ip = " + ip + " , port = " + port.__str__()
							if self.hop_count_http == None:
								self.hop_count_http = -1  #-1 for NONE
								#print("Unknown Hop Count"+" :-> "+message)
								self.msg_to_set = self.msg_to_set + "#Hop count = " + self.hop_count_http.__str__() + ", msg = " + message
							else:
								#print("Hop Count: "+hop_count.__str__()+" :-> "+message)
								self.msg_to_set = self.msg_to_set + "#Hop count = " + self.hop_count_http.__str__() + " , msg = " + message
					break

			if not statusTor:	#Full connection failure via tor
				self.tor_connection_unsuccessfull_HTTP = True
				self.msg_to_set = self.msg_to_set + "#Tcp 3-way handshake failure via tor for the ip " + ip + " , port = " + port.__str__() 
								#print ("Tcp 3-way handshake failure via tor: "+ip+" port:"+port.__str__())
		

		#--------------------- Now for HTTPS --------------------------------------
		#ipIter = 0
		for ip in ipListRemote: 	#EACH IP
			#HTTP CHECK
			#ipIter = ipIter + 1
			port = 443
			self.msg_to_set = self.msg_to_set + "\nIP:" + str(ip) + " , port = " + str(port) + "\n"
			for j in range(self.Iterate):
				# 5 times iteration
				#print(str(j+1) + "th try for TOR CHeck ... ip = " + str(ip))
				statusTor = dnsOb.socketConnectTor(ip)
				if not statusTor:	#Do not check for local server connection
					#print("NOT statusTOR (i.e. false)")	
					pass
					# self.msg_to_set = self.msg_to_set + "##" + str((j+1)) + "th iteration TOR Connection Unsuccesful"
				if statusTor:	#Check for local server connection
					self.msg_to_set = self.msg_to_set + "##" + str((j+1)) + "th iteration TOR Connection Succesful"
					#successIter_tor_list_https[ipIter] = (j + 1) 	#Put the (j + 1)th index for successIterTorList
					successIter_tor_list_https.append((j+1))
					#print("STATUS TOR IS TRUE")
					#Now 5 times local ip target
					for k in range(self.Iterate):
						statusLocal = dnsOb.socketConnectLocal(ip)
						#print(str(k+1) + "th check for Local Connection ip = " + str(ip))
						if not statusLocal:
							#print("Status LDS is false")
							# self.msg_to_set = self.msg_to_set + "#" + str((k+1)) + "th iteration Local Server Connection Unsuccesful"
							time.sleep(2)
						if statusLocal:
							# print("Status LDS is true")
							#successIter_ls_list_https[ipIter] = (k + 1)
							successIter_ls_list_https.append((k+1))
							#censored_arr_https[ipIter] = 0 	#Not censored for this Ip address
							censored_arr_https.append(0)
							self.msg_to_set = self.msg_to_set + "#" + str((k+1)) + "th iteration Local Server Connection Succesful"
							self.msg_to_set = self.msg_to_set + "#Not censored for ip = " + str(ip) + " , in port = " + str(port)
							break

					if not statusLocal:
						status,self.hop_count_https,message = dnsOb.tcp_ttl_find(ip,port,2)
						if status:
							#censored_arr_https[ipIter] = 0
							censored_arr_https.append(0)
							self.msg_to_set = self.msg_to_set + "#Not TCP Censored for ip = " + ip + " , port = " + port.__str__()
						else:
							#censored_arr_https[ipIter] = 1
							censored_arr_https.append(1)
							self.msg_to_set = self.msg_to_set + "#TCP Censored for ip = " + ip + " , port = " + port.__str__()
							if self.hop_count_https == None:
								self.hop_count_https = -1  #-1 for NONE
								#print("Unknown Hop Count"+" :-> "+message)
								self.msg_to_set = self.msg_to_set + "#Hop count = " + self.hop_count_https.__str__() + ", msg = " + message
							else:
								#print("Hop Count: "+hop_count.__str__()+" :-> "+message)
								self.msg_to_set = self.msg_to_set + "#Hop count = " + self.hop_count_https.__str__() + " , msg = " + message
					break

			if not statusTor:	#Full connection failure via tor
				self.tor_connection_unsuccessfull_HTTPS = True
				self.msg_to_set = self.msg_to_set + "#Tcp 3-way handshake failure via tor for the ip " + ip + " , port = " + port.__str__() 
								#print ("Tcp 3-way handshake failure via tor: "+ip+" port:"+port.__str__())
		
		description = TCP_Description()
		description.form_Description(self.msg_to_set, ipListRemote, successIter_tor_list_http, successIter_ls_list_http, 
			successIter_tor_list_https, successIter_ls_list_https, censored_arr_http, censored_arr_https,
			self.hop_count_http, self.hop_count_https, self.tor_connection_unsuccessfull_HTTP, self.tor_connection_unsuccessfull_HTTPS) 
		
		return description
		#return self.msg_to_set, ipListRemote ,successIter_tor_list_http, successIter_ls_list_http, successIter_tor_list_https, successIter_ls_list_https, censored_arr_http, censored_arr_https , self.hop_count_http, self.hop_count_https
		#--------------------- My end --------------------------------------



is_main = False
HOST = "www.xvideos.com"
if is_main == True:
	ob = TCP_3_WAY_HANDSHAKE()
	ob.tcp_handshake_check(HOST, 5)#, tor_ip, tor_port_1)
	print("NOW PRINTING THE msg_to_set .... ")
	print(ob.msg_to_set)
'''
tcp_check = TCP_3_WAY_HANDSHAKE()
f = open("domainName.txt","r")
domains = f.read().splitlines()
tor_ip = "127.0.0.1"
tor_port = 9150
print(tor_port.__str__())
if tor_port != None:
	for HOST in domains:
		tcp_check.tcp_handshake_check(HOST,5,tor_ip,tor_port)


'''














