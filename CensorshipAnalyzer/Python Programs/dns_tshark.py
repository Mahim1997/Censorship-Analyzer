import subprocess, threading
import os, time
from Local_Dns_Server_Ip import OS_Commands

class OS_Commands_tshark:
	def __init__(self):
		self.process = None
		self.output = []
        
	#provide a domain name that is uncensored to isp and a timeout value to terminate subprocess in case of error
	def get_local_dns_server_ip(self,UncensoredDomainName, timeout, server_port):
		try:
			os.popen('touch new_traffic.pcap')
			os.popen('chmod o=rw new_traffic.pcap')
			os.popen('> new_traffic.pcap')
			#self.process = subprocess.Popen(['tshark', '-w traffic_1.pcap','-a duration:10'], shell = True)
			self.process = subprocess.Popen(['tshark -w new_traffic.pcap -a duration:10'], shell = True)
			pidp =self.process.pid
			time.sleep(4)
			#os.popen("nslookup www.google.com")
			local_dns_server_ip = OS_Commands()
			local_dns_server_ip.analyze_nslookup_result(UncensoredDomainName, timeout, server_port)	
		except:
			#print("Exception in Os_Command_tshark")
			exit(1)
			
	def analyze_nslookup_result(self, UncensoredDomainName, timeout, server_port):
		self.get_local_dns_server_ip(UncensoredDomainName,timeout, server_port)
		time.sleep(10)
		
#command = OS_Commands_tshark()
#output = command.analyze_nslookup_result(UncensoredDomainName =  'www.google.com',timeout = 10,server_port = 53)
#print(output)






'''

From your output we see a "defunct", which means the process has either completed its task or has been corrupted or killed, but its child processes are still running or these parent process is monitoring its child process. To kill this kind of process, kill -9 PID doesn't work. You can try to kill them with this command but it will show this again and again.

Determine which is the parent process of this defunct process and kill it. To know this run the command:
ps -ef | grep defunct

UID          PID     PPID       C    STIME      TTY          TIME              CMD
1000       637      27872      0   Oct12      ?        00:00:04 [chrome] <defunct>
1000      1808      1777       0    Oct04     ?        00:00:00 [zeitgeist-datah] <defunct>

Then kill -9 637 27872, then verify the defunct process is gone by ps -ef | grep defunct.


'''
