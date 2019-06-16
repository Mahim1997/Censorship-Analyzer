from dns_censorship_single_url_class import DNS_CENSORSHIP
import socket
import string

def isSourceJava(msg):	#Simple function to check if contains source:java as the first line
	x = msg.split(':')
	if x[0]=='source':
		if x[1] == 'java':
			return True
	return False

def getSecondColumn(msg):	#To get the second column after : thing
	x = msg.split(':')
	return x[1]

# ---------------- Command Process Function -------------------
def processMessage(msg):
	#print("Inside message -->" + msg)
	splitMsg = msg.split('$')
	if isSourceJava(splitMsg[0])==False:
		return
	userID = getSecondColumn(splitMsg[1])
	connectionID = getSecondColumn(splitMsg[2])
	typeOfTesting = getSecondColumn(splitMsg[3])
	timestamp = getSecondColumn(splitMsg[4])
	url = getSecondColumn(splitMsg[5])
	periodicity = getSecondColumn(splitMsg[6])
	isPeriodic = getSecondColumn(splitMsg[7])
	fileNamePeriodic = "NULL"
	iterationNumber = -1	 #Initialise to -1
	if periodicity == 'forced':
		iterationNumber = 0	#Initial check is 0th
	if isPeriodic == 1:
		fileNamePeriodic = getSecondColumn(splitMsg[8])

	if typeOfTesting == 'dns':
		print('Run DNS .... ')
		dns_check = DNS_CENSORSHIP()
		dns_check.dns_censorship_check(url)
	elif typeOfTesting == 'tcp':
		print('Run TCP .....')
	elif typeOfTesting == 'http':
		print('Run HTTP .... ')
	elif typeOfTesting == 'all':
		print('Run All ..... ')
	else :
		return

#------------------------ Python Server Function -------------------------
def runServer():	
	localIP     = "127.0.0.1"	#loop-back IP
	localPort   = 7722		#Port Python = 7722
	bufferSize  = 1024		#Accept buffer size of 1024 bytes

	UDPServerSocket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)		# Create a datagram socket

	UDPServerSocket.bind((localIP, localPort))		# Bind to address and ip

	print('UDP server up and listening [Python] \n')

	# Listen for incoming datagrams

	while(True):

	    bytesAddressPair = UDPServerSocket.recvfrom(bufferSize)

	    message = bytesAddressPair[0]
	    #clientMsg = "{}".format(message)
	    clientMsg = str(message)

	    processMessage(clientMsg)

	    print(clientMsg)	#Printing message from Client [Java Client]



#--------------------------------------------------Main Program---------------------------------------------------------------

#runServer()
str = "source:java$userID:7$connectionID:4$typeOfTesting:dns$timestamp:17 June 2019, 4 45 am$url:www.google.com$periodicity:forced$isPeriodic:yes$fileNamePeriodic:1505022.txt$iterationNumber:4"
processMessage(str)