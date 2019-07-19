/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.model;
import java.net. *;
import java.io. *;
import javafx.geometry.Pos;
import ui.sounds.Notification;
/**
 *
 * @author mahim
 */
public class Network {

    public static String status;
    public static String asn;
    public static String city;
    public static String continent;
    public static String country;
    public static String latitude;
    public static String longitude;
    public static String org;
    public static String postal;
    public static String region;
    public static String carrier;
    public static String global_ip;

    
    public static int connection_id = 1; //For debug [set default to 1]
    
    public static String getGlobalIP() {
        try {
            URL url_whatismyip = new URL("http://checkip.amazonaws.com"); //from geeksforgeeks.com
            BufferedReader in = new BufferedReader(new InputStreamReader(url_whatismyip.openStream()));
            
            String ip = in.readLine(); //you get the IP as a String
            System.out.println("In Network.getGlobalIP() .. global ip is: " + ip);
            return ip;
        } catch (IOException ex) {
            System.out.println("-->>>Exception in obtaining global ip ... ");
            Notification.push("Error", "Unable to obtain global IP of network ... please check internet connection", Notification.FAILURE, Pos.BOTTOM_RIGHT);
        }
        return "NA";
    }
}
