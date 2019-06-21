package util.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import ui.model.User;

public class CommandGenerator {

    private static String getConnectionID_UsingPython() {
        int DEBUG_CONNECTION_ID = 1;

        if (DEBUG_CONNECTION_ID == 1) {
            return "7";
        }
        
        //Use Python Module ... 
        
        return null;
    }

    private static String getGeneralCommands() {
        String s = "";
        s += "source:java\n";
        s += ("connectionID:" + getConnectionID_UsingPython() + "\n");

        return null;
    }

    private static String getLine(String first, String last) {
        return (first + ":" + last + "\n");
    }

    private static String getLine(String first, int last) {
        return (first + ":" + String.valueOf(last) + "\n");
    }

    private static String getLine(String first, double last) {
        return (first + ":" + String.valueOf(last) + "\n");
    }

    private static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public static String getCommand(String url, String testTypeName, boolean isFileChecking, String fileNameFullePath,
            boolean isForcedChecking, boolean isPeriodicCheck, double periodInHours) {   //Factory Method

        if (isFileChecking == false) //Only URL Checking
        {
            return generateCommandURL(url, testTypeName);
        }

        //File Checking ... 
        if (isForcedChecking == true) //File Checking, Forced Checking
        {
            if (isPeriodicCheck == true) {  //Forced, Periodic
                return generateCommandFilePeriodicForcedCheck(url, fileNameFullePath, testTypeName, periodInHours);
            } else {    //Forced, Not Periodic
                return generateCommandFileNonPeriodicForcedCheck(url, fileNameFullePath, testTypeName);
            }

        } else //Periodic Checking
        {
            return generateCommandPeriodicCheck(url, fileNameFullePath, testTypeName, periodInHours);
        }

    }

    public static String generateCommandURL(String url, String testTypeName) {
        String s = "";
        s += getLine("source", "java");
        s += getLine("userID", User.userID);
        s += getLine("connectionID", getConnectionID_UsingPython());
        s += getLine("typeOfTesting", testTypeName);
        s += getLine("timestamp", getCurrentTime());
        s += getLine("url", url);
        s += getLine("isFile", 0);
        s += getLine("periodicity", "forced");
        s += getLine("isPeriodic", "no");
        s += getLine("fileNamePeriodic", "NULL");
        s += getLine("iterationNumber", 0);
        s += getLine("periodInHours", 0);
        return s;
    }

    public static String generateCommandFileNonPeriodicForcedCheck(String url, String fileFullPathName, String testTypeName) {
        String s = "";

        s += getLine("source", "java");
        s += getLine("userID", User.userID);
        s += getLine("connectionID", getConnectionID_UsingPython());
        s += getLine("typeOfTesting", testTypeName);
        s += getLine("timestamp", getCurrentTime());
        s += getLine("url", url);
        s += getLine("isFile", 0);
        s += getLine("periodicity", "forced");
        s += getLine("isPeriodic", "no");
        s += getLine("fileNamePeriodic", fileFullPathName);
        s += getLine("iterationNumber", 0);
        s += getLine("periodInHours", 0);
        return s;
    }

    public static String generateCommandFilePeriodicForcedCheck(String url, String fileFullPathName, String testTypeName, double periodInHours) {
        String s = "";

        s += getLine("source", "java");
        s += getLine("userID", User.userID);
        s += getLine("connectionID", getConnectionID_UsingPython());
        s += getLine("typeOfTesting", testTypeName);
        s += getLine("timestamp", getCurrentTime());
        s += getLine("url", url);
        s += getLine("isFile", 0);
        s += getLine("periodicity", "forced");
        s += getLine("isPeriodic", "yes");
        s += getLine("fileNamePeriodic", fileFullPathName);
        s += getLine("iterationNumber", 0);
        s += getLine("periodInHours", periodInHours);

        return s;
    }

    public static String generateCommandPeriodicCheck(String url, String fileFullPathName, String testTypeName, double periodInHours) {
        String s = "";

        s += getLine("source", "java");
        s += getLine("userID", User.userID);
        s += getLine("connectionID", getConnectionID_UsingPython());
        s += getLine("typeOfTesting", testTypeName);
        s += getLine("timestamp", getCurrentTime());
        s += getLine("url", url);
        s += getLine("isFile", 0);
        s += getLine("periodicity", "periodic");
        s += getLine("isPeriodic", "yes");
        s += getLine("fileNamePeriodic", fileFullPathName);
        s += getLine("iterationNumber", 0);
        s += getLine("periodInHours", periodInHours);

        return s;
    }

}
