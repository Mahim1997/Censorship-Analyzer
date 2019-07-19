import subprocess
import socket
from ipdata import ipdata
from requests import get


def internet(host="8.8.8.8", port=53, timeout=3):
   try:
     socket.setdefaulttimeout(timeout)
     socket.socket(socket.AF_INET, socket.SOCK_STREAM).connect((host, port))
     return 'Connected'
   except socket.error as ex:
     return 'Not Connected'


class Ip_info:
	status = None
	asn = None
	city = None
	continent_name = None
	country_name = None
	latitude = None
	longitude = None
	organisation = None
	postal = None
	region = None
	carrier_name = None
	
	


# Create an instance of an ipdata object. Replace `test` with your API Key
class Get_Ip_Info:

	def get_global_ip(self,url):
		#try:
		process = subprocess.Popen(["curl","-m 5000",url], stdout=subprocess.PIPE, stderr=subprocess.PIPE, universal_newlines=True)
		output, error = process.communicate(timeout=5)
		return output


	def get_ip_info(self):
		try:
			url = "https://ipapi.co/ip/"
			ip = self.get_global_ip(url)
			#print(ip)
			if ip == "":
				return None
			ipdata1 = ipdata.IPData('test')
			response = ipdata1.lookup(ip,fields=['asn','city','continent_name','country_name','latitude','longitude','organisation','postal','region','carrier'])
			#print(response)
			if response['status'] is 200:
				ip_details = Ip_info()
				ip_details.status = internet()
				ip_details.asn = response['asn']
				ip_details.city = response['city']
				ip_details.continent_name = response['continent_name']
				ip_details.country_name = response['country_name']
				ip_details.latitude = response['latitude']
				ip_details.longitude = response['longitude']
				ip_details.organisation = response['organisation']
				ip_details.postal = response['postal']
				ip_details.region = response['region']
				if response['carrier'] is not None:
					ip_details.carrier_name = response['carrier']['name']
				else:
					ip_details.carrier_name = 'NA'
				return ip_details
			
			return None
		except:
			return None

	
# dig TXT +short o-o.myaddr.l.google.com @ns1.google.com [To see global IP address]
ob = Get_Ip_Info()
ip_details = ob.get_ip_info()
if ip_details != None:
	print(ip_details.status)
	print(ip_details.asn)
	print(ip_details.city)
	print(ip_details.continent_name)
	print(ip_details.country_name)
	print(ip_details.latitude)
	print(ip_details.longitude)
	print(ip_details.organisation)
	print(ip_details.postal)
	print(ip_details.region)
	print(ip_details.carrier_name)
	
	
	

#https://api.ipdata.co/70.70.70.70
