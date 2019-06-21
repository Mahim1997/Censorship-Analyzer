import sqlite3

conn = sqlite3.connect("Client Data.db")
c = conn.cursor()
class Database:
	def createTable(self):
		#Network PK(network_name, global_IP), network_type
		c.execute("CREATE TABLE IF NOT EXISTS 	Network(network_name TEXT,global_ip TEXT, network_type TEXT, PRIMARY KEY (network_name,global_ip))")

		#User (user_id(PK), email_address, password, user_name)
		c.execute("CREATE TABLE IF NOT EXISTS 	USER(user_id INTEGER PRIMARY KEY,email_address TEXT UNIQUE,password TEXT, user_name TEXT)")

		#Connection(connectionID(PK), network_name, global_IP, userID all FK's, location)
		c.execute("CREATE TABLE IF NOT EXISTS 	Connection(connection_id INTEGER PRIMARY KEY,network_name TEXT, global_ip TEXT, user_id INTEGER, location TEXT,FOREIGN KEY (network_name,global_ip) REFERENCES MobileNetworks(network_name,global_ip),FOREIGN KEY (user_id) REFERENCES USER(user_id))")
	
		#Report (reportID(PK), connectionID(FK), timestamp, url, isCensored, type_of_testing, method_of_censorship, isFileCheck, isPeriodic, fileName_Periodic, iterationNumber)
		c.execute("CREATE TABLE IF NOT EXISTS 	Report(report_id INTEGER PRIMARY KEY,connection_id INTEGER, time_stamp REAL UNIQUE, url TEXT, is_censored TEXT ,type_of_testing TEXT, method_of_censorship TEXT, isFileCheck TEXT, isPeriodic TEXT, fileNamePeriodic TEXT, iterationNumber INTEGER, FOREIGN KEY(connection_id) REFERENCES Connection(connection_id))")
	

		#DNS_Description (reportID(FK, PK), censorship_code, middle_box_hop_count, censorship_details)
		c.execute("CREATE TABLE IF NOT EXISTS 	DnsDescription(report_id INTEGER PRIMARY KEY,censorship_code INTEGER, middle_box_hop_count INTEGER, censorship_details TEXT,FOREIGN KEY(report_id) REFERENCES Report(report_id))")
	
		#LocalIPAddresses (reportID(FK, PK), local_IP(PK))	
		c.execute("CREATE TABLE IF NOT EXISTS 	LocalIpAddresses(report_id INTEGER,local_ip TEXT, PRIMARY KEY(report_id,local_ip),FOREIGN KEY(report_id) REFERENCES DnsDescription(report_id))")
		
		#StubbyIP (reportID(FK, PK), stubby_IP(PK))
		c.execute("CREATE TABLE IF NOT EXISTS 	StubbyIpAddresses(report_id INTEGER,stubby_ip TEXT, PRIMARY KEY(report_id,stubby_ip),FOREIGN KEY(report_id) REFERENCES DnsDescription(report_id))")
	
		#HTTPDescrption(reportID(FK, PK), content_UsingStubby, content_UsingTOR, threshold_percent_comparision, final_verdict, middle_box_hop)
		c.execute("CREATE TABLE IF NOT EXISTS 	HTTPDescription(report_id INTEGER PRIMARY KEY,content_tor TEXT, content_local TEXT, comparison_percentage REAL, final_verdict TEXT, middle_box_hop_count INTEGER,FOREIGN KEY(report_id) REFERENCES Report(report_id))")
	
		#TCPDescrption(reportID(FK, PK),tor_3_way_handshake_status, local_3_way_handshake_status, error_message, middle_box_hop)
		c.execute("CREATE TABLE IF NOT EXISTS 	TCPDescription(report_id INTEGER PRIMARY KEY, tor_3_way_handshake_status TEXT, local_3_way_handshake_status TEXT, error_message TEXT, middle_box_hop_count INTEGER,FOREIGN KEY(report_id) REFERENCES Report(report_id))")
	
		conn.commit(); #Very Very important			
		

#----------------------------------------------------------------------------------------------#
create_database = Database()
create_database.createTable()
c.close()
conn.close()
