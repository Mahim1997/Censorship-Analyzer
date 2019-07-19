# --------------------------------------------------Main Program---------------------------------------------------------------
import sys


def processMessage(str):
	print("Inside testPythonProg ... argv len = " + str(len(sys.argv)))
	print("SECOND ARG IS --> " + str(sys.argv[1]))


# str = "source:java$userID:2$connectionID:4$typeOfTesting:TCP$timestamp:17 June 2019, 4 45 am$url:www.xvideos.com$periodicity:forced$isPeriodic:yes$fileNamePeriodic:1505022.txt$iterationNumber:4"


processMessage(str)
# runServer()


# str ='source:java$userID:7$connectionID:7$typeOfTesting:DNS$timestamp:23/06/2019 04:06:22$url:www.youtube.com$isFile:0$periodicity:forced$isPeriodic:no$fileNamePeriodic:NULL$iterationNumber:0$periodInHours:0$'
# processMessage(str)
