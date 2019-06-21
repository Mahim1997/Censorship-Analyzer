from ipdata import ipdata
from requests import get

from requests import get

#ip = get('https://api.ipify.org').text
#print ('My public IP address is:', ip)


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
	global_ip_network = None	


# Create an instance of an ipdata object. Replace `test` with your API Key
class Get_Ip_Info:

	def get_global_ip(self):
		try:
			return get('https://ipapi.co/ip/',timeout=3).text
		except:
			return None


	def get_ip_info(self,ip):
		try:
			ipdata1 = ipdata.IPData('test')
			response = ipdata1.lookup(ip,fields=['asn','city','continent_name','country_name','latitude','longitude','organisation','postal','region','carrier'])
			if response['status'] is 200:
				ip_details = Ip_info()
				ip_details.global_ip_network = get('https://api.ipify.org').text 	#Get the global IP address of this network
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


_is_main = False
if _is_main == True:
	ob = Get_Ip_Info()
	global_ip = ob.get_global_ip()
	if global_ip is not None:
		ip_details = ob.get_ip_info(global_ip)
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
		print("Global IP: ", ip_details.global_ip_network)


#https://api.ipdata.co/70.70.70.70
