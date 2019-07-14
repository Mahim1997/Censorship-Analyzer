import socket # for socket 
import sys  
  
try: 
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
    print "Socket successfully created"
except socket.error as err: 
    print "socket creation failed with error %s" %(err) 
  
# default port for socket 
s.settimeout(5)
port = 80
  
try: 
    host_ip = socket.gethostbyname('www.google.com') 
except socket.gaierror: 
  
    # this means could not resolve the host 
    print "there was an error resolving the host"
    sys.exit() 
print (host_ip)
# connecting to the server 
try:
	status = s.connect((host_ip, port)) 
	print (status)
except socket.error, exc:
	print "Caught exception socket.error : %s" % exc
print "the socket has successfully connected to google"
