import socket

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

	    print(clientMsg)	#Printing message from Client [Java Client]



#--------------------------------------------------Main Program---------------------------------------------------------------

runServer()