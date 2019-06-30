from dns_censorship_single_url_class_updated import DNS_CENSORSHIP
import socket
import string
from datetime import datetime
from dbHandler import DBHandler

def isSourceJava(msg):	#Simple function to check if contains source:java as the first line
	x = msg.split(':')
	
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

	if url == 'none':
		print("url is none so return")
		return 

	isFile = getSecondColumn(splitMsg[6])	#isFile -> 0 then only url check ... so DO NOT add to database
	periodicity = getSecondColumn(splitMsg[7])
	isPeriodic = getSecondColumn(splitMsg[8])
	

	fileNamePeriodic = "NULL"
	iterationNumber = -1	 #Initialise to -1
	if periodicity == 'forced':
		iterationNumber = 0	#Initial check is 0th
	if isPeriodic == 1:
		fileNamePeriodic = getSecondColumn(splitMsg[9])
		iterationNumber = getSecondColumn(splitMsg[10])
	
	if typeOfTesting == 'DNS':
		print('Run DNS .... ')
		dns_check = DNS_CENSORSHIP()
		if isFile == 0:
			dns_check.ADD_TO_DATABASE = 0

		db = DBHandler()
		#db.checkAndMakeConnection(userID) 	#TO DO  [Problem [NoneType etc]]
		
		report = dns_check.dns_censorship_check(url)
		#Further modifications .... 
		report.url = url
		report.timestamp = datetime.now().strftime('%d-%m-%Y %H:%M:%S') 	#In this format
		report.is_file_check = isFile
		report.is_periodic = isPeriodic
		report.file_name_periodic = fileNamePeriodic
		report.iteration_number = iterationNumber
		report.type_of_testing = "DNS"
		#print("Inside pythonServer.py ... typeOfTesting('dns') <PRINTING REPORT> url = " + url)
		#report.printReport()

		db.handleReport(report)

	elif typeOfTesting == 'TCP':
		print('Run TCP .....')
	elif typeOfTesting == 'HTTP':
		print('Run HTTP .... ')
	elif typeOfTesting == 'ALL':
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

runServer()
#str = "source:java$userID:2$connectionID:4$typeOfTesting:DNS$timestamp:17 June 2019, 4 45 am$url:www.xvideos.com$periodicity:forced$isPeriodic:yes$fileNamePeriodic:1505022.txt$iterationNumber:4"
#processMessage(str)

#str ='source:java$userID:7$connectionID:7$typeOfTesting:DNS$timestamp:23/06/2019 04:06:22$url:www.youtube.com$isFile:0$periodicity:forced$isPeriodic:no$fileNamePeriodic:NULL$iterationNumber:0$periodInHours:0$'
#processMessage(str)
