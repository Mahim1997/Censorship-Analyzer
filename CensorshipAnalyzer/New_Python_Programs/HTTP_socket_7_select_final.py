import sys
import struct
from requests_toolbelt.adapters import host_header_ssl
from urllib.parse import urlparse
from urllib.parse import urljoin
from pprint import pprint as pp
from SocketFunctions import SocketFunctions
import requests
from requests.exceptions import Timeout
import dns.resolver # NOTE: dnspython package
import tldextract
import socket
import urllib3

import select
from bs4 import BeautifulSoup
from difflib import SequenceMatcher
urllib3.disable_warnings()

#---------------------------------------https---------------------------------------
from requests.adapters import HTTPAdapter
import socket
import ssl,os

class HostHeaderSSLAdapter(HTTPAdapter):
	def send(self, request, **kwargs):
		# HTTP headers are case-insensitive (RFC 7230)
		host_header = None
		for header in request.headers:
			if header.lower() == "host":
				host_header = request.headers[header]
				break

		connection_pool_kwargs = self.poolmanager.connection_pool_kw

		if host_header:
			connection_pool_kwargs["assert_hostname"] = host_header
		elif "assert_hostname" in connection_pool_kwargs:
			# an assert_hostname from a previous request may have been left
			connection_pool_kwargs.pop("assert_hostname", None)

		return super(HostHeaderSSLAdapter, self).send(request, **kwargs)

#------------------------------------------------------------------------------------

'''
orig_stdout = sys.stdout
f = open('http.txt', 'a')
sys.stdout = f'''
#f=open("http.txt","w")


'''
		ErrorCodes
invalid url                  1000
connectTimeout               1001
readTimeout                  1002
connectionError              1003
otherErrors                  1004

capcha                       403
http_ok                      200
moved                        301,302,307,308
'''
'''
enum {
    TCP_ESTABLISHED = 1,
    TCP_SYN_SENT = 2,	#no response(tcp censored)
    TCP_SYN_RECV = 3,	
    TCP_FIN_WAIT1 = 4,	#fin received
    TCP_FIN_WAIT2 = 5,	#fin received
    TCP_TIME_WAIT = 6,	#fin received
    TCP_CLOSE = 7,	#rst received
    TCP_CLOSE_WAIT = 8,	#fin received
    TCP_LAST_ACK = 9,	#fin received
    TCP_LISTEN = 10,
    TCP_CLOSING = 11,    /* Now a valid state */#fin received
    TCP_MAX_STATES = 12  /* Leave at the end! */
};


The receiver of a RST first validates it, then changes state.  If the
  receiver was in the LISTEN state, it ignores it.  If the receiver was
  in SYN-RECEIVED state and had previously been in the LISTEN state,
  then the receiver returns to the LISTEN state, otherwise the receiver
  aborts the connection and goes to the CLOSED state.  If the receiver
  was in any other state, it aborts the connection and advises the user
  and goes to the CLOSED state.
  
  
  
					redirects

There are 3 types of redirections

    HTTP - as information in response headers (with code 301, 302, 3xx)
    HTML - as tag <meta> in HTML (wikipedia: Meta refresh)
    JavaScript - as code like window.location = new_url

requests execute HTTP redirections and keep all urls in r.history

import requests

r = requests.get('http://' + 'bit.ly/english-4-it')

print(r.history)
print(r.url)

result:

[<Response [301]>, <Response [301]>]
http://helion.pl/ksiazki/english-4-it-praktyczny-kurs-jezyka-angielskiego-dla-specjalistow-it-i-nie-tylko-beata-blaszczyk,anginf.htm

---------------------start-----------------------

_________http://banglamail71.info/archives/1948___________
ip: 37.48.65.148
Dns done through Tor
{'cache-control': 'max-age=0, private, must-revalidate', 'connection': 'close', 'content-length': '11', 'date': 'Sat, 20 Jul 2019 19:31:58 GMT', 'location': 'http://837753490.cs-utilities.com/archives/1948?sid=0abb9462-ab25-11e9-b16b-b49ccac110f4&token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJKb2tlbiIsImRvbWFpbiI6ImJhbmdsYW1haWw3MS5pbmZvIiwiZXhwIjoxNTYzNjU4MzE5LCJpYXQiOjE1NjM2NTExMTksImlzcyI6Ikpva2VuIiwianRpIjoiMm1wajhxNGppM2xodWFiMnRnMGltaWliIiwibmJmIjoxNTYzNjUxMTE5LCJ0cyI6MTU2MzY1MTExOTM4NDU0NX0.IukIKmQHJolnF--YLRBc-ct_s_WouG4k9pboRQ-yGOI', 'server': 'nginx', 'set-cookie': 'sid=0abb9462-ab25-11e9-b16b-b49ccac110f4; path=/; domain=.banglamail71.info; expires=Thu, 07 Aug 2087 22:46:06 GMT; max-age=2147483647; HttpOnly'}
b'Redirecting'
{'cache-control': 'max-age=0, private, must-revalidate', 'connection': 'close', 'content-length': '486', 'content-type': 'text/html; charset=utf-8', 'date': 'Sat, 20 Jul 2019 19:31:59 GMT', 'server': 'nginx', 'set-cookie': 'sid=0b18dfd2-ab25-11e9-a656-b49c4424890e; path=/; domain=.banglamail71.info; expires=Thu, 07 Aug 2087 22:46:06 GMT; max-age=2147483647; HttpOnly'}
b"<html><head><title>Loading...</title></head><body><script type='text/javascript'>window.location.replace('http://banglamail71.info/archives/1948?js=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJKb2tlbiIsImV4cCI6MTU2MzY1ODMxOSwiaWF0IjoxNTYzNjUxMTE5LCJpc3MiOiJKb2tlbiIsImpzIjoxLCJqdGkiOiIybXBqOHE1bnVhazRtYXE4aDQwaW5iNDQiLCJuYmYiOjE1NjM2NTExMTksInRzIjoxNTYzNjUxMTE5OTk1MDA2fQ.hG6y9bxJId6tuqG6Djc61k11cpVdbYgLGd09NyVYlX8&sid=0b18dfd2-ab25-11e9-a656-b49c4424890e');</script></body></html>"
Tor response status code: 302
Local response status code: 200
Http censorship applied: Http response status code mismatch

---------------------end-----------------------


'''

class http_https_data:
	
	proxies = {
	    'http': 'socks5://localhost:9150',
	    'https': 'socks5://localhost:9150',
	    'http': 'socks5://localhost:9050',
	    'https': 'socks5://localhost:9050'
	}
	
	file_counter = 0
	socket_status = 1
	connect_hop_count = -1
	http_hop_count = -1
	is_http_response_rec = False
	is_tcp_censored = False
		
	
	#--------------------------------https-------------------------------------------------------------
	def https_through_tor(self,url,ip,isTor):
		try:
			isCaptcha = False
			status_code = 1000
			parsed = urlparse(url)
			response = None
			if parsed.scheme == "https":
				hostname = parsed.netloc
				parsed = parsed._replace(netloc=ip)
				ip_url = parsed.geturl()
				s = requests.Session()
				s.mount('https://', HostHeaderSSLAdapter())
				#print(ip_url)
				#print(hostname)
				if isTor:
					s.proxies = self.proxies
				for i in range(5):
					try:
						headers = {
							'Host': hostname,
							'Accept-Language': 'en-US,en;q=0.8',
							'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36',
							'Connection': 'keep-alive',
						}
						response = s.get(ip_url ,headers=headers,allow_redirects = False,verify = False, timeout=5)
						#print(response.headers)
						#print(response.content)
						if response.status_code >= 400 and response.status_code < 600:
							soup = BeautifulSoup(response.content, features="html.parser")
							token = soup.find(class_ ='g-recaptcha')
							if token != None:
								isCaptcha = True
							elif 'captcha' in response.text:
								isCaptcha = True
								
						'''if response.status_code == 200:
							regex= '/window\.location\.replace\s*=\s*\"([^"]+)\"/'
							occurance = regex.exec(responce.text)
							if (occurance[1]): 
								print(occurance[1]) '''
							
							
						return True,response.status_code,response.content,response.headers,isCaptcha,response.text
					except requests.exceptions.ConnectTimeout as e:
						status_code = 1001
					except requests.exceptions.ReadTimeout:
						status_code = 1002
					except requests.exceptions.ConnectionError as e:
						status_code = 1003
					except:
						status_code = 1004
					
				return False,status_code,None,None,None,None
			
		except:
			return False,status_code,None,None,None,None
			
	
	def getTCPInfo(self,s):
		fmt = "B"*7+"I"*21
		x = struct.unpack(fmt, s.getsockopt(socket.IPPROTO_TCP, socket.TCP_INFO, 92))
		return x[0]
	
	#'socket create error' = 1
	#'fin bit received' = 2
	#'rst bit received' = 3
	#'tcp 3-way not established' = 4
	#'tls handshake failed' = 5
	def checking_no_response_https(self,ip,host_name,path,port):
		if path == "":
			path = '/'
		
		request_header ='GET '+path+' HTTP/1.1\r\n'+'Host: '+host_name+'\r\nConnection: keep-alive'+'\r\n\r\n'
		try:
			context = ssl.create_default_context()
			context = ssl.SSLContext(ssl.PROTOCOL_TLS)
			context.verify_mode = ssl.CERT_NONE
			context.check_hostname = False
			host = host_name
			conn = context.wrap_socket(socket.socket(socket.AF_INET),do_handshake_on_connect=False, server_hostname=host, suppress_ragged_eofs=False)
		except socket.error as err:
			return False, 1
		conn.settimeout(5)
		for i in range(3):
			try:
				conn.connect((ip, port))
				a = self.getTCPInfo(conn)
				if a == 4 or a == 5 or a == 6 or a == 8 or a == 9 or a == 11:
					return False, 2
				elif a == 7:
					return False, 3
				break
			except socket.error as err:
				#a = self.getTCPInfo(conn)
				#print("error: "+a.__str__())
				return False, 4
		
		a = self.getTCPInfo(conn)
		if a == 4 or a == 5 or a == 6 or a == 8 or a == 9 or a == 11:
			return False, 2
		elif a == 7:
			return False, 3
		try:
			conn.do_handshake()
		except:
			return False,5   #tls handshake exception
		#s.send(request_header.encode('utf-8'))
		a = self.getTCPInfo(conn)
		if a == 4 or a == 5 or a == 6 or a == 8 or a == 9 or a == 11:
			return False, 2
		elif a == 7:
			return False, 3
		
		conn.sendall(request_header.encode('utf-8'))
		
		a = self.getTCPInfo(conn)
		if a == 4 or a == 5 or a == 6 or a == 8 or a == 9 or a == 11:
			return False, 2
		elif a == 7:
			return False, 3
		
		
		rl,sl,el = select.select([conn],[],[],2)
		if not rl:
			a = self.getTCPInfo(conn)
			if a == 4 or a == 5 or a == 6 or a == 8 or a == 9 or a == 11:
				return False, 2
			elif a == 7:
				return False, 3
		try:
			s.close()
		except:
			pass
		return True,0
	
	#--------------------------------------------------------------------------------------------------
			
	def get_ip(self,hostname):
		isResolved = False
		ipListRemote = []
		dnsOb = SocketFunctions()
		for i in range(5):
			idRemote,messageRemote,answersFromRemote = dnsOb.QueryToRemoteDnsServer(hostname)
			if idRemote == 1:
				isResolved = True
				for answer in answersFromRemote:
					if answer.to_text() not in str(ipListRemote):
						ipListRemote.append(answer.to_text())
				break
		if not isResolved:
			return None
		return ipListRemote[0]
		
	def tcp_connectivity_check(self,ip,port):
		try:
			s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		except socket.error as err:
			return False
		s.settimeout(5)
		self.is_tcp_censored = False
		for i in range(3):
			try:
				#s.setsockopt(socket.IPPROTO_IP, socket.IP_TTL, 24)
				s.connect((ip, port))
				return True
			except socket.error as err:
				continue
		self.is_tcp_censored = True
		return False
		
	
	'''
	enum {
	    TCP_ESTABLISHED = 1,
	    TCP_SYN_SENT = 2,	#no response(tcp censored)
	    TCP_SYN_RECV = 3,	
	    TCP_FIN_WAIT1 = 4,	#fin received
	    TCP_FIN_WAIT2 = 5,	#fin received
	    TCP_TIME_WAIT = 6,	#fin received
	    TCP_CLOSE = 7,	#rst received
	    TCP_CLOSE_WAIT = 8,	#fin received
	    TCP_LAST_ACK = 9,	#fin received
	    TCP_LISTEN = 10,
	    TCP_CLOSING = 11,    /* Now a valid state */#fin received
	    TCP_MAX_STATES = 12  /* Leave at the end! */
	};

	'''
	#'socket create error' = 1
	#'fin bit received' = 2
	#'rst bit received' = 3
	#'tcp 3-way not established' = 4

	def checking_no_response(self,ip,host_name,path,port):
		if path == "":
			path = '/'
		
		request_header ='GET '+path+' HTTP/1.1\r\n'+'Host: '+host_name+'\r\nConnection: keep-alive'+'\r\n\r\n'
		try:
			s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		except socket.error as err:
			return False, 1
		s.settimeout(5)
		for i in range(3):
			try:
				s.connect((ip, port))
				a = self.getTCPInfo(s)
				if a == 4 or a == 5 or a == 6 or a == 8 or a == 9 or a == 11:
					return False, 2
				elif a == 7:
					return False, 3
				break
			except socket.error as err:
				#a = self.getTCPInfo(s)
				#print("error: "+a.__str__())
				return False, 4
		
		a = self.getTCPInfo(s)
		if a == 4 or a == 5 or a == 6 or a == 8 or a == 9 or a == 11:
			return False, 2
		elif a == 7:
			return False, 3
		s.send(request_header.encode('utf-8'))
		a = self.getTCPInfo(s)
		if a == 4 or a == 5 or a == 6 or a == 8 or a == 9 or a == 11:
			return False, 2
		elif a == 7:
			return False, 3
			
		rl,sl,el = select.select([s],[],[],2)
		if not rl:
			a = self.getTCPInfo(s)
			if a == 4 or a == 5 or a == 6 or a == 8 or a == 9 or a == 11:
				return False, 2
			elif a == 7:
				return False, 3
		try:
			s.close()
		except:
			pass
		return True,0
		
		
	def http_through_tor(self,url,ip,isTor):
		try:
			isCaptcha = False
			status_code = 1000
			parsed = urlparse(url)
			response = None
			if parsed.scheme == "http":
				hostname = parsed.netloc
				parsed = parsed._replace(netloc=ip)
				ip_url = parsed.geturl()
				s = requests.Session()
				if isTor:
					s.proxies = self.proxies
				for i in range(5):
					try:
						headers = {
							'Host': hostname,
							'Accept-Language': 'en-US,en;q=0.8',
							'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36',
							'Connection': 'keep-alive',
						}
						response = s.get(ip_url ,headers=headers,allow_redirects = False,timeout=5,verify=False)
						#print(response.headers)
						#print(response.content)
						if response.status_code >= 400 and response.status_code < 600:
							soup = BeautifulSoup(response.content, features="html.parser")
							token = soup.find(class_ ='g-recaptcha')
							if token != None:
								isCaptcha = True
							elif 'captcha' in response.text:
								isCaptcha = True
								
						'''if response.status_code == 200:
							regex= '/window\.location\.replace\s*=\s*\"([^"]+)\"/'
							occurance = regex.exec(responce.text)
							if (occurance[1]): 
								print(occurance[1]) '''
							
							
						return True,response.status_code,response.content,response.headers,isCaptcha,response.text
					except requests.exceptions.ConnectTimeout as e:
						status_code = 1001
					except requests.exceptions.ReadTimeout:
						status_code = 1002
					except requests.exceptions.ConnectionError as e:
						status_code = 1003
					except:
						status_code = 1004
					
				return False,status_code,None,None,None,None
			
		except:
			return False,status_code,None,None
			
	def http_censorship_check(self,url):
		if 'https://' in url:
			url = url.replace("https://","http://")
		elif 'http://' not in url:					#final url starts with http://
			url = '%s%s' % ('http://', url)
		try:
			parsed = urlparse(url)
			hostname = parsed.netloc
		except:
			print("Invalid Url")
			return None
		print("_________"+url+"___________")
		ip = self.get_ip(hostname)
		if ip == None:
			print ("Domain doesnot exist")
			return None
		print("ip: "+ip.__str__())
		print("Dns done through Tor")
		
		
		#checking no response-----------------------------------------------------------------------------------------------------
		
		status,error_code = self.checking_no_response(ip,hostname,parsed.path,80)
		if not status:
			if error_code == 2:
				print("fin bit received after connect")
			elif error_code == 3:
				print("rst bit received after connect")
			elif error_code == 4:
				print("tcp 3-way not established")
			return None
		#print('--------------------------------------------\n')
		
		
		
		
		
		
		statusT,scT,cT,hT,isCaptchaT,tT = self.http_through_tor(url,ip,True)
		if scT == 1003:
			print("connection error:please check your internet connection")
			return None
		if not statusT:
			print("Tor: No HTTP response received")
			return None
		statusL,scL,cL,hL,isCaptchaL,tL = self.http_through_tor(url,ip,False)
		if (not statusL) and scL == 1001:
			print("connect timeout")
			return None
		elif (not statusL) and scL == 1002:
			print("read timeout")
			print("http error")
			return None
		elif not statusL:
			print("no response")
			return None
		
		print("Tor response status code: "+scT.__str__())
		print("Local response status code: "+scL.__str__())



		if ((scL >= 300 and scL < 400) and (scT == 200)):
			print("Http censorship applied: Http response status code mismatch")
			return None
		if ((scT >= 300 and scT < 400) and (scL == 200)):
			#if 'http-equiv="Refresh' in cL:
				#return hT['Location']
			#if 'window.location.replace' in cL or 'window.location' in cL:
				#return  hT['Location']
				
			#else:
			print("Http censorship applied: Http response status code mismatch")
			return None
			
			
		if scT == 200 and isCaptchaL:
			print("Http: Not censored(local received captcha)")
			return None
		
		if isCaptchaL:
			print("Http: Not censored(local_1 captcha received)")
			return None
		if isCaptchaT:
			'''if scL >=300 and scL < 400:
				print("Http censorship applied: Http response status code mismatch(tor received captcha)")
				return None'''
			if scL == 200:
				if len(cL) < 1024:
					if 'block' in tL or 'blocked' in tL: 
						print("Http censorship applied:(response content length < 1024 and (block,blocked) keyword found)")
						return None
				print("Http: Not censored(tor received captcha)")
				return None
			else:
				print("Http: Not censored(tor captcha received)")
				return None
				
		if scT != scL:
			print("Http response status code mismatch")
			return None
		elif (scT == 200) and (scL == 200):
			if len(cL) < 1024:
				if 'block' in tL or 'blocked' in tL: 
					print("Http censorship applied")
			print("start html ")
			m = SequenceMatcher(None, cT, cL)
			#percentage = 1 - m.ratio()
			percentage = 1 - m.quick_ratio()
			print("difference: "+percentage.__str__())
			if percentage > 0.3000:
				print("Http censorship applied: Http content difference "+(percentage*100).__str__()+"%")
				return None
			print("Http: Not censored")
			return None
		elif scL >= 400:
			print("Http (server side or client side) error received")
			return None
		elif scL >= 300 and scL < 400:
			new_url_tor = hT['Location']
			new_url_local = hL['Location']
			if 'https://' in new_url_tor and 'https://' not in new_url_tor:
				print("Http censorship applied: new url mismatch")
				return None
			if 'http://' in new_url_tor and 'http://' not in new_url_tor:
				print("Http censorship applied: new url mismatch")
				return None
			if "http://" in new_url_tor or "https://" in new_url_tor:
				print("new url tor: "+new_url_tor)
				print("new url local: "+new_url_local)
				return new_url_tor
			new_url = urljoin(url,new_url_local)
			new_url_ =  urljoin(url,new_url_tor)
			print("new url: "+new_url_local)
			print("new url tor: "+new_url_tor)
			return new_url
			
			
	#----------------------------------------https----------------------------------------------------------
	def https_censorship_check(self,url):
		if 'http://' in url:
			url = url.replace("http://","https://")
		elif 'https://' not in url:					#final url starts with http://
			url = '%s%s' % ('https://', url)
		try:
			parsed = urlparse(url)
			hostname = parsed.netloc
		except:
			print("Invalid Url")
			return None
		print("_________"+url+"___________")
		ip = self.get_ip(hostname)
		if ip == None:
			print ("Domain doesnot exist")
			return None
		print("ip: "+ip.__str__())
		print("Dns done through Tor")
		
		
		#checking no response-----------------------------------------------------------------------------------------------------
		
		status,error_code = self.checking_no_response_https(ip,hostname,parsed.path,443)############
		if not status:
			if error_code == 2:
				print("fin bit received after connect")
			elif error_code == 3:
				print("rst bit received after connect")
			elif error_code == 4:
				print("tcp 3-way not established")
			elif error_code == 5:
				print("tls handshake failed")
			return None
		#print('--------------------------------------------\n')
		
		print("no response check done")
		
		
		
		
		statusT,scT,cT,hT,isCaptchaT,tT = self.https_through_tor(url,ip,True)
		if scT == 1003:
			print("connection error:please check your internet connection")
			return None
		if not statusT:
			print("Tor: No HTTP response received")
			return None
		statusL,scL,cL,hL,isCaptchaL,tL = self.https_through_tor(url,ip,False)
		if (not statusL) and scL == 1001:
			print("connect timeout")
			return None
		elif (not statusL) and scL == 1002:
			print("read timeout")
			print("http error")
			return None
		elif not statusL:
			print("no response")
			return None
		
		print("Tor response status code: "+scT.__str__())
		print("Local response status code: "+scL.__str__())



		if ((scL >= 300 and scL < 400) and (scT == 200)):
			print("Https censorship applied: Https response status code mismatch")
			return None
		if ((scT >= 300 and scT < 400) and (scL == 200)):
			#if 'http-equiv="Refresh' in cL:
				#return hT['Location']
			#if 'window.location.replace' in cL or 'window.location' in cL:
				#return  hT['Location']
				
			#else:
			print("Https censorship applied: Https response status code mismatch")
			return None
			
			
		if scT == 200 and isCaptchaL:
			print("Https: Not censored(local received captcha)")
			return None
		
		if isCaptchaL:
			print("Https: Not censored(local_1 captcha received)")
			return None
		if isCaptchaT:
			'''if scL >=300 and scL < 400:
				print("Https censorship applied: Https response status code mismatch(tor received captcha)")
				return None'''
			if scL == 200:
				if len(cL) < 1024:
					if 'block' in tL or 'blocked' in tL: 
						print("Https censorship applied:(response content length < 1024 and (block,blocked) keyword found)")
						return None
				print("Https: Not censored(tor received captcha)")
				return None
			else:
				print("Https: Not censored(tor captcha received)")
				return None
				
		if scT != scL:
			print("Https response status code mismatch")
			return None
		elif (scT == 200) and (scL == 200):
			if len(cL) < 1024:
				if 'block' in tL or 'blocked' in tL: 
					print("Https censorship applied")
			print("start html ")
			m = SequenceMatcher(None, cT, cL)
			#percentage = 1 - m.ratio()
			percentage = 1 - m.quick_ratio()
			print("difference: "+percentage.__str__())
			if percentage > 0.3000:
				print("Https censorship applied: Https content difference "+(percentage*100).__str__()+"%")
				return None
			print("Https: Not censored")
			return None
		elif scL >= 400:
			print("Https (server side or client side) error received")
			return None
		elif scL >= 300 and scL < 400:
			new_url_tor = hT['Location']
			new_url_local = hL['Location']
			if 'https://' in new_url_tor and 'https://' not in new_url_tor:
				print("Http censorship applied: new url mismatch")
				return None
			if 'http://' in new_url_tor and 'http://' not in new_url_tor:
				print("Http censorship applied: new url mismatch")
				return None
			if "http://" in new_url_tor or "https://" in new_url_tor:
				print("new url tor: "+new_url_tor)
				print("new url local: "+new_url_local)
				return new_url_tor
			new_url = urljoin(url,new_url_local)
			new_url_ =  urljoin(url,new_url_tor)
			print("new url: "+new_url_local)
			print("new url tor: "+new_url_tor)
			return new_url
	
	#-------------------------------------------------------------------------------------------------------

ob = http_https_data()
#ob.http("www.pornhub.com/","66.254.114.41")
#ob.http("www.youtube.com","74.125.68.93")
#ob.http("www.ranker.com","103.243.13.160")
#sc,c,h = ob.http_through_tor("bdpolitico.com","104.28.1.128")
url_list = open('domain_name.txt','r')
urls = url_list.read().splitlines()
new_url = None
for url in urls:
	print("\n---------------------start-----------------------\n")
	for i in range(3):
	
		if 'http://' in url:
			new_url = ob.http_censorship_check(url)
		elif 'https://' in url:
			new_url = ob.https_censorship_check(url)
		else:
			new_url = ob.http_censorship_check(url)
		#print("new url__: "+new_url)
		if new_url is None:
			break
		else:
			url = new_url
	if new_url != None:
		print("more than 3 redirection")
	print("\n---------------------end-----------------------\n")
'''
sys.stdout = orig_stdout
f.close()'''
#st = ob.tcp_connectivity_check("172.217.168.196",80)

#print(sc)

#"GET / HTTP/1.1\r\nHost: www.google.com\r\n\r\n".encode('utf-8')
#request_header ='GET '+'/'+' HTTP/1.0\r\n'+'Host: www.google.com'+'\r\n\r\n'


