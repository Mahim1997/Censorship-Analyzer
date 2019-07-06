import subprocess
from ipdata import ipdata
from requests import get

class Ip_info:
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
		try:
			process = subprocess.Popen(["curl","-m 5000",url], stdout=subprocess.PIPE, stderr=subprocess.PIPE, universal_newlines=True)
			output, error = process.communicate(timeout=5)
			return output
		except:
			process.kill()
			return ""

	'''def isp_details(self):
		
		url = "https://ipapi.co/"+ip+"/json/"
		response = self.get_global_ip(url)
		if response == "":
			return None
		return response'''

	def get_ip_info(self):
		try:
			url = "https://ipapi.co/ip/"
			ip = self.get_global_ip(url)
			if ip == "":
				return None
			ipdata1 = ipdata.IPData('test')
			response = ipdata1.lookup(ip,fields=['asn','city','continent_name','country_name','latitude','longitude','organisation','postal','region','carrier'])
			if response['status'] is 200:
				ip_details = Ip_info()
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
				return ip_details
			return None
		except:
			return None


ob = Get_Ip_Info()
ip_details = ob.get_ip_info()
if ip_details != None:
	print("Autonomous System Number: "+ip_details.asn)
	print("City: ",ip_details.city)
	print("Continent Name: ",ip_details.continent_name)
	print("Country Name: ",ip_details.country_name)
	print("Region: ",ip_details.region)
	print("Postal: ",ip_details.postal)
	print("Carrier name: ",ip_details.carrier_name)
	print("Organisation: ",ip_details.organisation)
	print("Latitude: ",ip_details.latitude)
	print("Longitude: ",ip_details.longitude)

#https://api.ipdata.co/70.70.70.70
