from scapy.all import *
import socks
import socket
import time
from SocketFunctions import SocketFunctions

tor_url = "https://check.torproject.org/"
tor_ip = "127.0.0.1"
tor_port_1 = 9050 
tor_port_2 = 9150
tor_timeout = 10


dnsOb = SocketFunctions()

class TCP_3_WAY_HANDSHAKE:
	Iterate = 5
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
			print ("Domain doesnot exist")
			return None
						
		
		#---------------------------------------------------------------------------------------------------------------------------
		
		statusTor = False
		statusLocal = False
		for ip in ipListRemote:
			print("Checking IP:"+ip)
			port = 80
			for k in range(2):
				if k == 1:
					port = 443
				for j in range(self.Iterate):
					statusTor = dnsOb.socketConnectTor(ip,port,tor_ip,tor_port,5)		#Tcp 3-way Handshaking via tor network
					if statusTor:
						print("succeded via tor")
						for j in range(self.Iterate):
							statusLocal = dnsOb.socketConnectLocal(ip,port,2)		#Tcp 3-way Handshaking via local network
							if statusLocal:
								print ("Not TCP Censored: "+ip+" port:"+port.__str__())
								break
							else:
								time.sleep(2)
						if not statusLocal:
							status,hop_count,message = dnsOb.tcp_ttl_find(ip,port,2)
							if status:
								print ("Not TCP Censored: "+ip+" port:"+port.__str__())
							else:
								print ("TCP Censored: "+ip+" port:"+port.__str__())
								if hop_count == None:
									print("Unknown Hop Count"+" :-> "+message)
								else:
									print("Hop Count: "+hop_count.__str__()+" :-> "+message)
						break
				if not statusTor:
					print ("Tcp 3-way handshake failure via tor: "+ip+" port:"+port.__str__())



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














