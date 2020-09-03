# SoftwareProject [Censorship Analyzer]
This project was done during the Level 3 Term 2(ISD) and the Level 4 Term 1 (Software development) courses.

## Short description
The aim of this project is to determine different methods of censorship applied in the ISPs.
There are basically 3 types where censorship is applied: DNS, TCP, HTTP
Our first step is to autonomously detect, given a url, whether it is censored and if so, then which censorship method is applied on it.
We also plan to produce an interactive user-interface as well i.e. a desktop application for easier analysis.

- The **Censorship Detection** part is coded in **Python**

- Other **networking modules** are also implemented in **Python** using various packages like **Scapy**, **TShark**, etc.

- The **Desktop GUI** part is coded in **Java FX**  

- **SQLite** used for desktop **Database**

## Topics Covered in this project

### Python Codes
<!-- UL -->
We have used various python packages (eg. socket, scapy) and external programs (eg. stubby)
* DNS Censorship detection
* TCP Censorship detection
* HTTP Censorship detection
* Middlebox detection
* Network-related parameters identification
* Miscellaneous tasks (eg. ping, tracerout, nslookup)

### Java Codes
<!-- UL -->
* DNS Censorship detection module integration with desktop and database 
* TCP Censorship detection module integration with desktop and database
* HTTP Censorship detection module integration with desktop and database
* Middlebox detection module integration with desktop and database
* Network-related parameters identification and integration with desktop application
* Miscellaneous tasks (eg. ping, tracerout, nslookup) integration with desktop application
* UDP Sockets(for Loopback IP) are used to transfer information from Java desktop program to python modules and vice-vers
* SQLite used to maintain the records obtained by the python modules

<!--### Server Side
While desktop application may be used for per-user basis, after obtaining a record and entering in **local database**, we also
make sure the record goes to a remote server so that a full high-level analysis can be done.
This Server Side is in -->



## How do the different Censorship Detection Methods work?

### DNS Censorship Detection Method
First the program will ask for the Local DNS Server to resolve the ip address mapping, given a url as input.
Many cases may arise and these cases are handled. Some examples are shown below:
<!-- UL -->
* If Local Server does not return any IP address. In this case, timeout occurs. We check for a few more times if timeout occurs. Then we try to resolve for a well known website (eg. www.google.com) and if timeout occurs as well, then we conclude that there is too much load on the network (maybe due to congestion)
* If Local Server returns a set of IP addresses / one IP address
  If ip(s) are/is in 
  * Private ip range then censored
  * Loopback ip range then censored
  * Multicast ip range then censored
  * Bogon ip range then censored
* If no such domain exists then censored
* Stubby is used to retrieve another set of ip addresses against this url. If there exists an overlap between local server ip addresses and stubby ip addresses, then **not censored**
* If public ip is returned, frequency analysis is done to make sure whether advertising is done i.e. redirection hence concluded to be censored
* Local Server ip(s) and stubby ip(s) first 8-24 characters are compared for a match. If exists, then **not censored**

### TCP Censorship Detection Method
First the program will obtain *true* ip(s) against this url using stubby/external proxy server. Since independent checking of DNS, TCP, HTTP is done, we cannot use local dns server to obtain this mapping. After getting ip(s), we use Local DNS Server to **connect** to the ip(s).
<!-- UL -->
* If we succeed, then **not censored**
* Else, we try again this process for 4 times. If we obtain failure all the time, then we **still do not** conclude the url to be tcp-censored. Then we do the following:
  * We try to **connect** to a well-known website (eg. www.google.com) to check whether there is a load on the network or not.
  * We use **TOR** to connect to the url (i.e. the ip) to make sure whether the website was **not moved/taken down**.

### HTTP Censorship Detection Method
We use Local Server to obtain the http content and use TOR to do the same.
Applying a comparison between them within a certain threshold, we conclude whether HTTP related censorship is done or not.
HTTPS related censorship detection methods is also checked in this case.


## To run
<!-- UL -->
* First run the *pythonServer.py* program in terminal
```bash
  pushd "CensorshipAnalyzer/Python Programs/"
  python3 pythonServer.py                       # This python program acts like a server to accept instructions from Java Program
  popd
```
* Then run the Java Project
```bash
  pushd "CensorshipAnalyzer/src/"
  rm -f *.class                     # Remove the class files from before
  javac *.java                      # Compile all the java files
  java Main                         # Run the Main java file
  popd
```
**OR Simply Open Netbeans and run the CensorshipAnalyzer project*
