import subprocess, threading
import os, time

class OS_Commands:
	def __init__(self):
		self.process = None
		self.output = []
        
        
	#provide a domain name that is uncensored to isp and a timeout value to terminate subprocess in case of error
	def get_local_dns_server_ip(self,UncensoredDomainName, timeout, server_port):
		os.popen('touch new_traffic.pcap')
		os.popen('chmod o=rw new_traffic.pcap')
		os.popen('> new_traffic.pcap')
		#self.process = subprocess.Popen(['tshark', '-w traffic_1.pcap','-a duration:10'], shell = True)
		self.process = subprocess.Popen(['tshark -w new_traffic.pcap -a duration:10'], shell = True)
		print("ol1")
		#self.output, error = self.process.communicate()
		time.sleep(4)
		os.popen("nslookup www.google.com")
		print("ol2")
		#print(self.output)
			
	def analyze_nslookup_result(self, UncensoredDomainName, timeout, server_port):
		print("ol11")
		self.get_local_dns_server_ip(UncensoredDomainName,timeout, server_port)
		print("ol12")
		
command = OS_Commands()
output = command.analyze_nslookup_result(UncensoredDomainName =  'www.google.com',timeout = 10,server_port = 53)
print(output)
time.sleep(10)

