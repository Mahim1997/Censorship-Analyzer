import subprocess, threading

class OS_Commands:
	def __init__(self):
		self.process = None
		self.output = []
        
        
	#provide a domain name that is uncensored to isp and a timeout value to terminate subprocess in case of error
	def get_local_dns_server_ip(self,UncensoredDomainName, timeout, server_port):
		def target():
			self.process = subprocess.Popen(['nslookup','-port=%s'% server_port,UncensoredDomainName], stdout=subprocess.PIPE, stderr=subprocess.PIPE, universal_newlines=True)
			self.output, error = self.process.communicate()
		
		thread = threading.Thread(target=target)
		thread.start()

		thread.join(timeout)
		if thread.is_alive():
			#Terminating process
			self.process.terminate()
			thread.join()
		if self.process.returncode is not 0:
			return False, self.output
		else:
			return True, self.output
	
	def analyze_nslookup_result(self, UncensoredDomainName, timeout, server_port):
		status,output = self.get_local_dns_server_ip(UncensoredDomainName,timeout, server_port)
		if status:
			dns_server = output.splitlines()[0]
			dns_server_ip = dns_server.split(':')[1].strip()
			return dns_server_ip
		else:
			return None
'''
command = OS_Commands()
output = command.analyze_nslookup_result(UncensoredDomainName =  'www.google.com',timeout = 3,server_port = 53)
print(output)
'''
