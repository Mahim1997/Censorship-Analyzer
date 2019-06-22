from copy import deepcopy
import string

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

	#DNS PART
	censorship_code=0
	middle_box_hop_count=0
	censorship_details="NONE"

	local_ip_addresses=[]
	stubby_ip_addresses=[]





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