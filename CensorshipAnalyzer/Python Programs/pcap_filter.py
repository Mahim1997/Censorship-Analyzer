#https://stackoverflow.com/questions/39331137/python-library-to-parse-dns-from-wireshark-capture-file-pcap
#https://stackoverflow.com/questions/24792462/python-scapy-dns-sniffer-and-parser/24792711#24792711
'''
touch traffic.pcap
chmod o=rw traffic.pcap
sudo tshark -w traffic.pcap
'''
from scapy.all import *
from scapy.layers.dns import DNS, DNSQR
from dns_tshark import OS_Commands_tshark


class pcap_filter:
	def filtering(self):
		try:
			dns_server_ip = None
			local_ip = None
			dns_server_ip = None
			device_mac_address = None
			next_hop_mac_address = None

			command = OS_Commands_tshark()
			command.analyze_nslookup_result(UncensoredDomainName =  'www.google.com',timeout = 10,server_port = 53)
			types = {0: 'ANY', 255: 'ALL',1: 'A', 2: 'NS', 3: 'MD', 4: 'MD', 5: 'CNAME',
				 6: 'SOA', 7:  'MB',8: 'MG',9: 'MR',10: 'NULL',11: 'WKS',12: 'PTR',
				 13: 'HINFO',14: 'MINFO',15: 'MX',16: 'TXT',17: 'RP',18: 'AFSDB',
				 28: 'AAAA', 33: 'SRV',38: 'A6',39: 'DNAME'}

			dns_packets = rdpcap('new_traffic.pcap')
			for packet in dns_packets:
				if packet.haslayer(DNS):
					#print("---------------------------------------------------------------------")
					isQR = packet[DNS].qr
					if isQR is 0:
						dns_server_ip = packet[IP].dst
						local_ip = packet[IP].src
						
						device_mac_address = packet[Ether].src
						next_hop_mac_address = packet[Ether].dst
					else:
						dns_server_ip = packet[IP].src
						local_ip = packet[IP].dst
						
						device_mac_address = packet[Ether].dst
						next_hop_mac_address = packet[Ether].src
					
					'''print("My IP:"+local_ip)
					print("Dns Server IP:"+dns_server_ip)
					print("Device MAC Address:"+device_mac_address)
					print("Next Hop Mac Address:"+next_hop_mac_address)'''
					packet_count = True
					#print("---------------------------------------------------------------------")
					return True,local_ip,dns_server_ip,device_mac_address,next_hop_mac_address
					
			#print("No packet")
			return False,local_ip,dns_server_ip,device_mac_address,next_hop_mac_address
		except:
			#print("Exception in pacp_filtering")
			return False,local_ip,dns_server_ip,device_mac_address,next_hop_mac_address
			
	'''		
ob = pcap_filter()
status,li,dnsip,mac,mach = ob.filtering()
if not status:
	print("Check your Internet Connection")'''

