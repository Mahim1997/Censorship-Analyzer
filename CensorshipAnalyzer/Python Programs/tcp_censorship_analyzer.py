from scapy.all import *
import socks
import socket
import time
from SocketFunctions_updated import SocketFunctions

tor_url = "https://check.torproject.org/"
tor_ip = "127.0.0.1"
tor_port_1 = 9050 
tor_port_2 = 9150
tor_timeout = 10


dnsOb = SocketFunctions()

class TCP_3_WAY_HANDSHAKE:
	Iterate = 5
	msg_to_set = ""
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
			
		
		
	def tcp_handshake_check(self,HOST,iteration,tor_ip,tor_port):
		
		'''ip = "172.217.194.99"
		statusLocal = dnsOb.socketConnectTor(ip)
		print(statusLocal)'''
	
		#---------------------------------------------------------------DNS resolution-----------------------------------------------
		print(HOST)
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
			return None
				
		ip_itr = 1		
		#print("Printing ip addresses of ipListRemote:")
		#for ip in ipListRemote:
		#	ip_itr = ip_itr + 1
		#	print(str(ip_itr) + "->" + ip)

		#---------------------------------------------------------------------------------------------------------------------------
		
		statusTor = False
		statusLocal = False
		
		#--------------------- My beginning --------------------------------------
		self.msg_to_set = ""
		for ip in ipListRemote: 	#EACH IP
			#HTTP CHECK
			port = 80
			self.msg_to_set = self.msg_to_set + "###IP:" + str(ip) + "###"
			for j in range(self.Iterate):
				# 5 times iteration
				statusTor = dnsOb.socketConnectTor(ip)
				if not statusTor:	#Do not check for local server connection
					self.msg_to_set = self.msg_to_set + str(j) + "th iteration TOR Connection Unsuccesful"
				if statusTor:	#Check for local server connection
					self.msg_to_set = self.msg_to_set + str(j) + "th iteration TOR Connection Succesful"					
					#Now 5 times local ip target
					for k in range(self.Iterate):
						statusLocal = dnsOb.socketConnectLocal(ip)
						if not statusLocal:
							self.msg_to_set = self.msg_to_set + str(k) + "th iteration Local Server Connection Unsuccesful"
							time.sleep(2)
						if statusLocal:
							self.msg_to_set = self.msg_to_set + str(k) + "th iteration Local Server Connection Succesful"
							break

					if not statusLocal:
						status,hop_count,message = dnsOb.tcp_ttl_find(ip,port,2)
						if status:
							self.msg_to_set = self.msg_to_set + "#Not TCP Censored for ip = " + ip + " , port = " + port.__str__()
							#print ("Not TCP Censored: "+ip+" port:"+port.__str__())
						else:
							#print ("TCP Censored: "+ip+" port:"+port.__str__())
							self.msg_to_set = self.msg_to_set + "#TCP Censored for ip = " + ip + " , port = " + port.__str__()
							if hop_count == None:
								#print("Unknown Hop Count"+" :-> "+message)
								self.msg_to_set = self.msg_to_set + "#Unknown hop count , msg = " + message
							else:
								#print("Hop Count: "+hop_count.__str__()+" :-> "+message)
								self.msg_to_set = self.msg_to_set + "#Hop count = " + hop_count.__str__() + " , msg = " + message
					break

			if not statusTor:	#Full connection failure via tor
				self.msg_to_set = self.msg_to_set + "#Tcp 3-way handshake failure via tor for the ip " + ip + " , port = " + port.__str__() 
								#print ("Tcp 3-way handshake failure via tor: "+ip+" port:"+port.__str__())
		#--------------------- My end --------------------------------------




is_main = True
HOST = "www.google.com"
if is_main == True:
	ob = TCP_3_WAY_HANDSHAKE()
	ob.tcp_handshake_check(HOST, 5, tor_ip, tor_port_1)
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














