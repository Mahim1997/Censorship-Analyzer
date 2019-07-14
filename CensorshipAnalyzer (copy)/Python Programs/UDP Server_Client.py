import socket

msgFromClient = "Hello UDP Server"

bytesToSend = str.encode(msgFromClient)

serverAddressPort = ("127.0.0.1", 7722)	#Loop Back			# 7722 is python port number, 7731 is java port number

bufferSize = 1024

# Create a UDP socket at client side

UDPClientSocket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)

# Send to server using created UDP socket



#n = 10
#while n > 0:
 #   g = input('Enter msg to send to Java Server: ')	#Take input
  #  bytesToSend = str.encode(g)
   # # UDPClientSocket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)
    #UDPClientSocket.sendto(bytesToSend, serverAddressPort)
    #print('Message sent to server: ' + g)
    #n = n - 1
