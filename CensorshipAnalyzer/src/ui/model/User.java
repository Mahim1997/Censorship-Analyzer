 
package ui.model;

import main.Main;

 
public class User {
    public static String networkName;
    public static String networkType;

    public static String userFirstName;
    public static String userLastName;    
    public static String userPassword;
    

    public static int numberOfTests;
    public static int numberOfDifferentURLs;
    public static int numberOfCensordSites;
    public static int numberOfCensoredDNS;
    public static int numberOfCensoredTCP;
    public static int numberOfCensoredHTTP;
    public static String location;
    
    public static void initiateUser(){
        if(Main.USER_DEBUG == 1){
            networkName = "Banglalink";
            networkType = "Mobile";
            
            userFirstName = "Mahim";
            userLastName = "Mahbub";
            userPassword = "1505022";
            
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
