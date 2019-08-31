package ui.model;

//import main.Main;
public class User {

    public static String networkName;
    public static String networkType;

    public static String userPassword = "PASSWORD";
    public static String userEmailAddress = "mahim1997mahbub@gmail.com";
    public static String modeOfAccess;

    public static String userName = "mahim";

    public static int userID = 7;   //User ID [obtained from Database]

    public static String getUserDetails_New() {
        String s = "";

//        s += ("$USER$" + String.valueOf(userID) + "$" + userName + "$" + userEmailAddress + "$" + userPassword + "$");
        s += (String.valueOf(userID) + "$" + userName + "$" + userEmailAddress + "$" + userPassword);

        return s;

    }

    public static String getUserDetails() {
        String s = "";

        s += ("$USER$" + String.valueOf(userID) + "$" + userName + "$" + userEmailAddress + "$" + userPassword + "$");

        return s;

    }
}
