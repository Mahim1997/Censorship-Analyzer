import sqlite3
from isp_info import Ip_info, Get_Ip_Info
class DBHandler:
	ob = Get_Ip_Info()
	global_ip = ob.get_global_ip()

	def openConnection(self):
		conn = sqlite3.connect("Client Data.db")
		c = conn.cursor()

	def closeConnection(self):
		c.close()
		conn.close()

	#User (user_id(PK), email_address, password, user_name)
	def addToUser(self, uId, eM, pas, uN):
		self.openConnection()
		c.execute("INSERT INTO User(user_id, email_address, password, user_name) VALUES (?, ?, ?, ?)", 
		(uId, eM, pas, uN))
		conn.commit()
		self.closeConnection()

	#addToUser(3, 'vipergon22e@gmail.com', '1234', 'mahim') 	#Kam hoise
	def getCurrentConnection(self):
		pass


	def checkIfConnectionExists(self, uIDGiven, networkName, location): 	#NONE TYPE ERROR ASHE !!!
		self.openConnection()
		c.execute("SELECT * FROM Connection WHERE Connection.user_id = ? AND Connection.network_name = ? AND Connection.location = ?", (uIDGiven, networkName, location))
		conn.commit()
		#TO DO !!!!!!
		self.closeConnection()

	def checkAndMakeConnection(self, uIDGiven):
		print('Inside checkAndMakeConnection (uIDGiven = )' + uIDGiven)
		self.ob = Get_Ip_Info()
		self.global_ip = self.ob.get_global_ip()
		
		if self.global_ip is not None:
			ip_details = self.ob.get_ip_info(self.global_ip)
			#print('Network Name:' + ip_details.organisation)
			flag = checkIfConnectionExists(self, uIDGiven, ip_details.organisation, ip_details.location, ip_details.global_ip_network)
	 

	def handleReport(self, report):
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
			#print("Row[0] : " + str(row[0]))
			num = row[0] 	#Don't know exactly why like this !!

		report.reportID = num + 1
		
		c.execute("INSERT INTO Report VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
			( report.reportID, report.connectionID, report.timestamp, report.url, report.is_censored, report.type_of_testing,
				report.method_of_censorship, report.is_file_check, report.is_periodic,
				report.file_name_periodic, report.iteration_number))
		
		for ip in report.local_ip_addresses:
			c.execute("INSERT INTO LocalIpAddresses VALUES (?, ?)", 
				(report.reportID, ip))
		
		for ip in report.stubby_ip_addresses:
			c.execute("INSERT INTO StubbyIpAddresses VALUES (?, ?)", 
				(report.reportID, ip))
			
		conn.commit()

		c.close()
		conn.close()
 