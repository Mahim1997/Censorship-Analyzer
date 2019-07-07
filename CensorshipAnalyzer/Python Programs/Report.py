from copy import deepcopy
import string
from datetime import datetime

from isp_info import Ip_info
from isp_info import Get_Ip_Info

class Report:
	reportID=0
	connectionID=0
	timestamp="0"
	url="NONE"
	type_of_testing="NONE"
	method_of_censorship="NONE"
	is_file_check="NONE"
	is_periodic="NONE"
	file_name_periodic="NONE"
	iteration_number="NONE"


	is_censored = "NONE"
	censorship_code=0
	middle_box_hop_count=0
	censorship_details="NONE"

	#DNS PART
	local_ip_addresses=[]
	stubby_ip_addresses=[]


	#TCP PART
	msg_to_set = "NONE"
	ip_addresses_resolved = []

	successIter_tor_list_http = []
	successIter_ls_list_http = [] 
	successIter_tor_list_https = []
	successIter_ls_list_https = []

	censored_arr_http = []
	censored_arr_https = []

	hop_count_http = -1 	#TO ADD IN REPORT IN DATABASE
	hop_count_https = -1	#TO ADD IN REPORT IN DATABASE
	tor_connection_unsuccessfull_HTTP = False 	#TO ADD IN REPORT IN DATABASE
	tor_connection_unsuccessfull_HTTPS = False 	#TO ADD IN REPORT IN DATABASE
 	#HTTP Part

	def printReport(self):
		print("---------------------------- PRINTING REPORT BEGIN--------------------------")
		print("Report ID: " + str(self.reportID))
		print("Connection ID: " + str(self.connectionID))
		print("TimeStamp: " + str(self.timestamp))
		print("URL: " + self.url)
		print("Type of Test: " + self.type_of_testing)
		print("Is Censored? " + self.is_censored)
		
		print("Method Censorship: " + self.method_of_censorship)
		print("Is File Check : " + str(self.is_file_check))
		print("Is periodic Check: " + str(self.is_periodic))
		print("File Name Periodic: " + self.file_name_periodic)
		print("Iteration Number: " + str(self.iteration_number))


		print("Censorship Code: " + str(self.censorship_code))
		print("Censorship Details: " + self.censorship_details)
		print("Mid box hop count: " + str(self.middle_box_hop_count))
		
		if self.type_of_testing == "DNS":
			print("Printing LocalIpAddresses: ")
			print(*self.local_ip_addresses, sep = " , ")
			print("Printing StubbyIpAddresses: ")
			print(*self.stubby_ip_addresses, sep = " , ")

		elif self.type_of_testing == "TCP":
			print("Printing resolved ips:")
			print(*self.ip_addresses_resolved, sep = " , ")
			print("Printing successIter_tor_list_http:")
			print(*self.successIter_tor_list_http, sep = " , ")
			print("Printing successIter_ls_list_http:")
			print(*self.successIter_ls_list_http, sep = " , ")

			print("Printing successIter_tor_list_https:")
			print(*self.successIter_tor_list_https, sep = " , ")
			print("Printing successIter_ls_list_https:")
			print(*self.successIter_ls_list_https, sep = " , ")

			print("Printing censored_arr_http:")
			print(*self.censored_arr_http, sep = " , ")
			print("Printing censored_arr_https:")
			print(*self.censored_arr_https, sep = " , ")

			print("Printing hop_count_http = " + str(self.hop_count_http) + ",hop_count_https = " + str(self.hop_count_https) + " , tor_connection_unsuccessfull = " + str(self.tor_connection_unsuccessfull))

		print("---------------------------- PRINTING REPORT DONE --------------------------")




	def getReportString(self):
		strReport = "ReportID:" + str(self.reportID)	#0
		strReport = strReport + "$ConnectionID:" + str(self.connectionID)	#1
		strReport = strReport + "$TimeStamp:" + str(self.timestamp)	#2
		strReport = strReport + "$URL:" + self.url 					#3
		strReport = strReport + "$Type_of_Test:" + self.type_of_testing	#4
		strReport = strReport + "$is_censored:" + self.is_censored	#5
		strReport = strReport + "$method_of_censorship:" + self.method_of_censorship #6
		strReport = strReport + "$is_file_check:" + str(self.is_file_check) #7
		strReport = strReport + "$is_periodic:" + str(self.is_periodic) #8
		strReport = strReport + "$file_name_periodic:" + self.file_name_periodic #9
		strReport = strReport + "$iteration_number:" + str(self.iteration_number) #10
		strReport = strReport + "$censorship_code:" + str(self.censorship_code) #11
		strReport = strReport + "$censorshipDetails:" + self.censorship_details #12
		strReport = strReport + "$middle_box_hop_count:" + str(self.middle_box_hop_count) #13

		# Latest added
		# strReport = strReport + "$global_ip: -"
		ob = Get_Ip_Info()
		global_ip = ob.get_global_ip()
		if global_ip is not None:
			ip_details = ob.get_ip_info(global_ip)
			if ip_details is not None:
				pass
				# strReport = strReport + "$global_ip:" + ip_details.global_ip_network
		# Latest added [NOT YET WORKING]

		 

		if self.type_of_testing == "DNS": 			
			strReport = strReport + "$local_ip_addresses:"
			for ip in self.local_ip_addresses: 				#14
				strReport = strReport + str(ip) + ","

			strReport = strReport + "$stubby_ip_addresses:"
			for ip in self.stubby_ip_addresses: 			#15
				strReport = strReport + str(ip) + ","
			strReport = strReport + "$END$"

		elif self.type_of_testing == "TCP":
			strReport = strReport + "$hop_count_http:" + str(self.hop_count_http) #14
			strReport = strReport + "$hop_count_https:" + str(self.hop_count_https) #15

			strReport = strReport + "$tor_connection_unsuccessfull_HTTP:" + str(self.tor_connection_unsuccessfull_HTTP) #16
			strReport = strReport + "$tor_connection_unsuccessfull_HTTPS:" + str(self.tor_connection_unsuccessfull_HTTPS) #17

			strReport = strReport + "$ipListRemote:"
			for ip in self.ip_addresses_resolved:  	#18
				strReport = strReport + str(ip) + ","

			strReport = strReport + "$successIter_tor_list_http:" 	#19
 			for ip in self.successIter_tor_list_http:
				strReport = strReport + str(ip) + ","

			strReport = strReport + "$successIter_ls_list_http:" 	#20
			for ip in self.successIter_ls_list_http:
				strReport = strReport + str(ip) + ","

			strReport = strReport + "$successIter_tor_list_https:" 	#21
			for ip in self.successIter_tor_list_https:
				strReport = strReport + str(ip) + ","

			strReport = strReport + "$successIter_ls_list_https:" 	#22
			for ip in self.successIter_ls_list_https:
				strReport = strReport + str(ip) + ","

			strReport = strReport + "$censored_arr_http:" 	#23
			for ip in self.censored_arr_http:
				strReport = strReport + str(ip) + ","

			strReport = strReport + "$censored_arr_https:"	#24
			for ip in self.censored_arr_https:
				strReport = strReport + str(ip) + ","

		return strReport
# ---------------------------------------------------------------------------------------------------------------------------------------------------------

def formTestReport(repID, conID, time, url, typeTesting, methodCensorship, isFileCheck, isPeriodic, fileNamePeriodic, iterationNumber, censorshipCode, 
	midboxHopCount, censorshipDetails, ip_local, ip_stubby):
	print("Inside function ... forming Test Report ... ")
	rep = Report()
	rep.reportID = repID
	rep.connectionID = conID
	rep.timestamp = time
	rep.url = url
	rep.type_of_testing = typeTesting
	rep.method_of_censorship = methodCensorship
	rep.is_file_check = isFileCheck
	rep.is_periodic = isPeriodic 
	rep.file_name_periodic = fileNamePeriodic 
	rep.iteration_number = iterationNumber 
	rep.censorship_code = censorshipCode 
	rep.middle_box_hop_count = midboxHopCount 
	rep.censorship_details = censorshipDetails 
	rep.local_ip_addresses = deepcopy(ip_local)
	rep.stubby_ip_addresses = deepcopy(ip_stubby)
	return rep 

#-------------------------------------------------------------
isMain=False 	#Keep false to be called by outside file
if isMain == True:
	rep = Report()
	ip_local = ['197.00.01.01' '100.00.11.2']
	ip_stubby = ['209.11.49.69' '400.10.51.3']
	rep = formTestReport(7, 4, "1 15 am June 23", "www.google.com", "DNS", "DNS", "NO", "NO", "domain.txt", 6, 107, 0, "Details TO DO", ip_local, ip_stubby)
	ts = datetime.now().strftime('%d-%m-%Y %H:%M:%S')
	rep.timestamp = ts 
	rep.printReport()