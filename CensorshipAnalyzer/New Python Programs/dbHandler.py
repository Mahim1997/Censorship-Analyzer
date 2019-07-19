import sqlite3
import socket

class DBHandler:

	def openConnection(self):
		conn = sqlite3.connect("Client Data.db")
		c = conn.cursor()

	def closeConnection(self):
		c.close()
		conn.close()

	# User (user_id(PK), email_address, password, user_name)
	def addToUser(self, uId, eM, pas, uN):
		self.openConnection()
		c.execute("INSERT INTO User(user_id, email_address, password, user_name) VALUES (?, ?, ?, ?)", 
		(uId, eM, pas, uN))
		conn.commit()
		self.closeConnection()

	# addToUser(3, 'vipergon22e@gmail.com', '1234', 'mahim') 	#Kam hoise
	def getCurrentConnection(self):
		pass


	def checkIfConnectionExists(self, uIDGiven, networkName, location): 	#NONE TYPE ERROR ASHE !!!
		self.openConnection()
		c.execute("SELECT * FROM Connection WHERE Connection.user_id = ? AND Connection.network_name = ? AND Connection.location = ?", (uIDGiven, networkName, location))
		conn.commit()
		# TO DO !!!!!!
		self.closeConnection()


	def handleReport_TCP(self, report):

		conn = sqlite3.connect("Client Data.db")
		c = conn.cursor()

		

		c.execute("SELECT COUNT(*) FROM Report")  #Get the count from Report
		countGot = c.fetchall()
		num = -1 
		for row in countGot:
			# print("--->>>>>>> In handleReport () ... Row[0] : " + str(row[0]))
			num = row[0] 	#Don't know exactly why like this !!

		report.reportID = num + 1

		print("Priinting report inside handleReport_TCP() ... ")
		
		strReport = report.getReportString()
		print(strReport)

		c.execute("INSERT INTO Report VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
			( report.reportID, report.connectionID, report.timestamp, report.url, report.is_censored, report.type_of_testing,
				report.method_of_censorship, report.is_file_check, report.is_periodic,
				report.file_name_periodic, report.iteration_number, report.censorship_code, report.censorship_details))

		localIP = [] 	#Ip addresses resolved for this report ....

		for ipAdd in report.ip_addresses_resolved:
			if ipAdd not in localIP:
				localIP.append(ipAdd)
		
#-------------------------------------------------------- TO DO -----------------------------------------------
		#1) add for HTTP
		iterator = 0
		for iterator in range(len(localIP)):
			c.execute("INSERT INTO IP_HTTP_FOR_TCP VALUES(?, ?, ?, ?, ?)",
				(
				report.reportID, localIP[iterator], report.successIter_tor_list_http[iterator], 
				report.successIter_ls_list_http[iterator], report.censored_arr_http[iterator])
				)

		#2) add for HTTPS
		iterator = 0
		for iterator in range(len(localIP)):
			c.execute("INSERT INTO IP_HTTPS_FOR_TCP VALUES(?, ?, ?, ?, ?)",
				(
				report.reportID, localIP[iterator], report.successIter_tor_list_https[iterator], 
				report.successIter_ls_list_https[iterator], report.censored_arr_http[iterator])
				)

		print("Adding to database is done for TCP record ... ")


		#Send to java over UDP
		MESSAGE = report.getReportString()
		self.sendToJava(MESSAGE)


		#Apply commit
		conn.commit()



		c.close()
		conn.close()


	def handleReport(self, report):
		# Open file to write for JAVA


		report.printReport()
		conn = sqlite3.connect("Client Data.db")
		c = conn.cursor()
		#c.execute("INSERT INTO Network VALUES(?, ?, ?)", ("Banglalink", "109.09.012", "Mobile Network") )
		
		#Table ... Report (report_id(PK), connection_id(FK), time_stamp, url, 
			#is_censored, type_of_testing, method_of_censorship, isFileCheck, isPeriodic, fileNamePeriodic, iterationNumber)
		print("Inserting into Report Table .... ")

		c.execute("SELECT COUNT(*) FROM Report")
		#conn.commit()
		countGot = c.fetchall()
		num = -1 
		for row in countGot:
			print("--->>>>>>> In handleReport () ... Row[0] : " + str(row[0]))
			num = row[0] 	#Don't know exactly why like this !!

		report.reportID = num + 1

		print("===>>>>>>>>>>> In dbHandler.handleReport() ... before inserting into REPORT ... printing it ")
		report.printReport()
		
		c.execute("INSERT INTO Report VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
			( report.reportID, report.connectionID, report.timestamp, report.url, report.is_censored, report.type_of_testing,
				report.method_of_censorship, report.is_file_check, report.is_periodic,
				report.file_name_periodic, report.iteration_number, report.censorship_code, report.censorship_details))
		
		localIP = []
		remoteIP = []

		for ipAdd in report.local_ip_addresses:
			if ipAdd not in localIP:
				localIP.append(ipAdd)
		
		for ipAdd in report.stubby_ip_addresses:
			if ipAdd not in remoteIP:
				remoteIP.append(ipAdd)


		print("Inside dbHandler ... printing local ip addresses : ")

		for ip in localIP:
			print(ip + ", ")

		print("Inside dbHandler ... prinitng repmote ip addresses : ")

		for ip in remoteIP:
			print(ip + ", ")


		for ip in localIP:
			c.execute("INSERT INTO LocalIpAddresses VALUES (?, ?)", 
				(report.reportID, ip))
		
		for ip in remoteIP:
			c.execute("INSERT INTO StubbyIpAddresses VALUES (?, ?)", 
				(report.reportID, ip))
			
		# send to java
		MESSAGE = report.getReportString()
		self.sendToJava(MESSAGE)

		conn.commit()

		c.close()
		conn.close()
 


	def sendToJava(self, MESSAGE):
		# ---------- Transfer over socket to JAVA [JAVA Listening PORT = 7731] --------------
		UDP_IP = '127.0.0.1' 	# loop back ip
		UDP_PORT = 7731 		# Java listening port

		# MESSAGE = report.getReportString()

		print("----------------------------------- SENDING TO JAVA UDP ------------------------------------------")
		# print("UDP target IP:", UDP_IP)
		# print("UDP target port:", UDP_PORT)
		print("message:", MESSAGE)
		print("----------------------------------------------------------------------------------------------------")
		# print("Sending to Java UDP ... [7731] In dbHandler.py ")

		sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM) # UDP
		sock.sendto(bytes(MESSAGE, "utf-8"), (UDP_IP, UDP_PORT))
		# -------------------------- Done sending to JAVA ------------------------------------------