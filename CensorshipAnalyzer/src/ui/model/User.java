 
package ui.model;

import main.Main;

 
public class User {
    public static String networkName ;
    public static String networkType;

    public static String userFirstName;
    public static String userLastName;    
    public static String userPassword = "PASSWORD";
    public static String userEmailAddress = "mahim1997mahbub@gmail.com";
    public static String modeOfAccess;
    
    public static int numberOfTests;
    public static int numberOfDifferentURLs;
    public static int numberOfCensordSites;
    public static int numberOfCensoredDNS;
    public static int numberOfCensoredTCP;
    public static int numberOfCensoredHTTP;
    public static String location;
    
    public static String userName = "Mahim_Mahbub";
    
    public static int userID = 7;   //User ID [obtained from Database]
    
    public static void initiateUser(){
        if(Main.USER_DEBUG == 1){
            userID = 1505022; //example
            networkName = "Banglalink";
            networkType = "Mobile";
            modeOfAccess = "Logged User";
            userFirstName = "Mahim";
            userLastName = "Mahbub";
            userName = userFirstName + "_" + userLastName ;
            userPassword = "PASSWORD";
            userEmailAddress = "mahim1997mahbub@gmail.com";
            location = "Dhaka, Bangladesh";
            
            numberOfTests = 1221;
            numberOfDifferentURLs = 121;
            numberOfCensordSites = 100;
            numberOfCensoredDNS = 51;
            numberOfCensoredHTTP = 30;
            numberOfCensoredTCP = 19;
        }
    }
}
