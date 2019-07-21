from scapy.all import *
import socks
import socket
from TCPDescription import TCPDescription
import time
from SocketFunctions_updated import SocketFunctions
from Report import Report

tor_url = "https://check.torproject.org/"
tor_ip = "127.0.0.1"
tor_port_1 = 9050
tor_port_2 = 9150
tor_timeout = 10

dnsOb = SocketFunctions()


class TCP_3_WAY_HANDSHAKE:
	Iterate = 2
	msg_to_set = ""
	tor_port = 9050

	hop_count_http = -1
	hop_count_https = -1
	tor_connection_unsuccessfull_HTTP = False
	tor_connection_unsuccessfull_HTTPS = False

	def is_tor_running(self):
		# ---------------------------------------------------Tor Connection Check---------------------------------------------------

		Tor_Status = dnsOb.CheckTorConnection(tor_url, tor_ip, tor_port_1,
											  tor_timeout)  # checking tor connection is ok or not
		if Tor_Status:
			return tor_port_1
		else:
			Tor_Status = dnsOb.CheckTorConnection(tor_url, tor_ip, tor_port_1, tor_timeout)
			if Tor_Status:
				return tor_port_2
			else:
				print("Tor is not listening.Please open the tor network first.")
				str_msg = "[[ERROR]] TOR IS NOT LISTENING .. OPEN THE TOR NETWORK FIRST"
				return None

	# print("Tor Status: Listening")

	# ---------------------------------------------------Stubby Connection Check---------------------------------------------------
	def is_stubby_running(self):
		stubby_status = dnsOb.CheckStubbyConnection()
		return stubby_status
		'''if stubby_status:
			print("Stubby status: OK")
		else:
			print("Stubby service is not running.")'''

	# def tcp_handshake_check(self,HOST,iteration,tor_ip,tor_port):
	def tcp_handshake_check(self, HOST, iteration):

		self.tor_port = self.is_tor_running()
		self.msg_to_set = "url:" + HOST
		# ---------------------------------------------------------------DNS resolution-----------------------------------------------
		print("Checking TCP for url: " + HOST)
		report_object = []
		isResolved = False
		ipListRemote = []
		for i in range(iteration):
			idRemote, messageRemote, answersFromRemote = dnsOb.QueryToRemoteDnsServer(HOST)
			if idRemote == 1:
				isResolved = True
				for answer in answersFromRemote:
					if answer.to_text() not in str(ipListRemote):
						ipListRemote.append(answer.to_text())
		if not isResolved:
			print("Domain doesnot exist")
			return report_object

		ip_itr = 1
		## TO DO !!!

		statusTor = False
		statusLocal = False

		# --------------------- My beginning --------------------------------------
		# For HTTP
		for ip in ipListRemote:  # EACH IP
			port = 80
			report = Report()
			report.tcp_description = TCPDescription()
			report.type_of_testing = "TCP"
			report.tcp_description.ip_address = ip
			report.tcp_description.port_number = 80
			print("Checking(" + ip + ", " + port.__str__() + ")")
			statusTor = False
			for j in range(self.Iterate):

				statusTor = dnsOb.socketConnectTor(ip, port)
				if not statusTor:  # Do not check for local server connection
					print("tor attempt " + j.__str__() + " unsuccessfull")
					pass
				if statusTor:
					print("tor attempt " + j.__str__() + " successfull")
					report.tcp_description.successful_iteration_number_tor = (j + 1)
					for k in range(self.Iterate):
						statusLocal, socket_state = dnsOb.socketConnectLocal(ip, port)
						if not statusLocal:
							print("local attempt " + k.__str__() + " unsuccessfull")
							time.sleep(2)
						if statusLocal:
							print("local attempt " + k.__str__() + " successfull")
							report.tcp_description.successful_iteration_number_local_server = (k + 1)
							break

					if not statusLocal:
						print("------------->>>Inside if not statusLocal: condition")
						status, self.hop_count_http, message = dnsOb.tcp_ttl_find(ip, port, 2)
						print("===>>>Obtained status = " + str(status) + ", hop_cnt_http = " + str(self.hop_count_http))
						if status:
							report.tcp_description.successful_iteration_number_local_server = self.Iterate
						else:
							report.is_censored = 1
							if socket_state == 2:
								report.tcp_description.is_time_out = 1
							elif socket_state == 7:
								report.tcp_description.is_rst_bit_set = 1
							else:
								report.tcp_description.is_fin_bit_set = 1
							if self.hop_count_http == -1:
								print("middlebox hop count : Not found")
								report.tcp_description.middle_box_hop_count = -1
							else:
								print("middlebox hop count : " + self.hop_count_http.__str__())
								report.tcp_description.middle_box_hop_count = self.hop_count_http
					break

			if not statusTor:  # Full connection failure via tor
				report.tcp_description.is_tor_connect_successful = 0
			print("--->>APPENDING TO REPORT ARRAY ... printing report")
			report.printReport()
			report_object.append(report)

		# --------------------- Now for HTTPS --------------------------------------
		# ipIter = 0
		for ip in ipListRemote:  # EACH IP
			port = 443
			report = Report()
			report.tcp_description = TCPDescription()
			report.type_of_testing = "TCP"
			report.tcp_description.ip_address = ip
			report.tcp_description.port_number = 443
			print("checking(" + ip + ", " + port.__str__() + ")")
			statusTor = False
			for j in range(self.Iterate):

				statusTor = dnsOb.socketConnectTor(ip, port)
				if not statusTor:  # Do not check for local server connection
					print("tor attempt " + j.__str__() + " unsuccessfull")
					pass
				if statusTor:
					print("tor attempt " + j.__str__() + " successfull")
					report.tcp_description.successful_iteration_number_tor = (j + 1)
					for k in range(self.Iterate):
						statusLocal, socket_state = dnsOb.socketConnectLocal(ip, port)
						if not statusLocal:
							print("local attempt " + k.__str__() + " unsuccessfull")
							time.sleep(2)
						if statusLocal:
							print("local attempt " + k.__str__() + " successfull")
							report.tcp_description.successful_iteration_number_local_server = (k + 1)
							break

					if not statusLocal:
						status, self.hop_count_http, message = dnsOb.tcp_ttl_find(ip, port, 2)
						if status:
							report.tcp_description.successful_iteration_number_local_server = self.Iterate
						else:
							report.is_censored = 1
							if socket_state == 2:
								report.tcp_description.is_time_out = 1
							elif socket_state == 7:
								report.tcp_description.is_rst_bit_set = 1
							else:
								report.tcp_description.is_fin_bit_set = 1
							if self.hop_count_http == -1:
								print("middlebox hop count : Not found")
								report.tcp_description.middle_box_hop_count = -1
							else:
								print("middlebox hop count : " + self.hop_count_http.__str__())
								report.tcp_description.middle_box_hop_count = self.hop_count_http
					break

			if not statusTor:  # Full connection failure via tor
				report.tcp_description.is_tor_connect_successful = 0
			report_object.append(report)

		return report_object
# --------------------- My end --------------------------------------


is_main = False
if is_main:
	print("Running tcp checker ")
	ob = TCP_3_WAY_HANDSHAKE()
	HOST = "www.xvideos.com"
	report_ob = ob.tcp_handshake_check(HOST, 5)
	print("report_ob.len = " + str(report_ob.__len__()))

	num = 1
	for rep in report_ob:
		print("------------------ Printing rep num = " + str(num) + " -------------")
		num = num + 1
		rep.printReport()
		print("======================================================================")

'''
ob = TCP_3_WAY_HANDSHAKE()
tor_port = ob.is_tor_running()
print(ob.__str__())'''
'''
is_main = True
HOST = "www.xvideos.com"
if is_main == True:
	ob = TCP_3_WAY_HANDSHAKE()
	ob.tcp_handshake_check(HOST, 5)#, tor_ip, tor_port_1)
	print("NOW PRINTING THE msg_to_set .... ")
	print(ob.msg_to_set)'''
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
