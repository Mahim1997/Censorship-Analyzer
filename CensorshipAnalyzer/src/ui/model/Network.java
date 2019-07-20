/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.model;

import java.net.*;
import java.io.*;
import javafx.geometry.Pos;
import ui.sounds.Notification;

/**
 *
 * @author mahim
 */
public class Network {

    public static String status_static;
    public static String asn_static;
    public static String city_static;
    public static String continent_static;
    public static String country_static;
    public static String latitude_static;
    public static String longitude_static;
    public static String org_static;
    public static String postal_static;
    public static String region_static;
    public static String carrier_static;
    public static String global_ip_static;
    public static int connection_id_static = 1; ///DEBUG [default: 1]

    public static String getGlobalIP() {
        try {
            URL url_whatismyip = new URL("http://checkip.amazonaws.com"); //from geeksforgeeks.com
            BufferedReader in = new BufferedReader(new InputStreamReader(url_whatismyip.openStream()));

            String ip = in.readLine(); //you get the IP as a String
            System.out.println("----------->>> In Network.getGlobalIP() .. global ip is: " + ip);
            return ip;
        } catch (IOException ex) {
            System.out.println("-->>>Exception in obtaining global ip ... ");
            Notification.push("Error", "Unable to obtain global IP of network ... please check internet connection", Notification.FAILURE, Pos.BOTTOM_RIGHT);
        }
        return "NA";
    }

    //---------------------------------------------- OBJECT -----------------------------------------------------------
    private String status;
    private String asn;
    private String city;
    private String continent;
    private String country;
    private String latitude;
    private String longitude;
    private String org;
    private String postal;
    private String region;
    private String carrier;
    private String global_ip;
    private int connection_id;//= 1; //For debug [set default to 1]

    // ----------- Extra ----------------
    private String network_type;
    private String network_name;

    public String getNetwork_type() {
        return network_type;
    }

    public void setNetwork_type(String network_type) {
        this.network_type = network_type;
    }

    public String getNetwork_name() {
        return network_name;
    }

    public void setNetwork_name(String network_name) {
        this.network_name = network_name;
    }
    
    Network() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAsn() {
        return asn;
    }

    public void setAsn(String asn) {
        this.asn = asn;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getGlobal_ip() {
        return global_ip;
    }

    public void setGlobal_ip(String global_ip) {
        this.global_ip = global_ip;
    }

    public int getConnection_id() {
        return connection_id;
    }

    public void setConnection_id(int connection_id) {
        this.connection_id = connection_id;
    }

    public Network(String status, String asn, String city, String continent, String country, String latitude, String longitude, String org, String postal, String region, String carrier, String global_ip, int connection_id) {
        this.status = status;
        this.asn = asn;
        this.city = city;
        this.continent = continent;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.org = org;
        this.postal = postal;
        this.region = region;
        this.carrier = carrier;
        this.global_ip = global_ip;
        this.connection_id = connection_id;
    }

}
