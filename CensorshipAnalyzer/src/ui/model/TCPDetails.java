/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.model;

/**
 *
 * @author mahim
 */
public class TCPDetails {   //This is per IP

    private String ip_address;
    private int port; //80 or 453
    private int is_tor_not_connected;
    private int is_timeout;
    private int is_fin_bit_set;
    private int is_rst_bit_set;

    private int successful_iteration_local_server;
    private int successful_iteration_tor;

    private int is_tor_connect_successful;

    private int middle_box_hop_count;
    private int is_censored_TCP;

    public TCPDetails() {
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getIs_tor_not_connected() {
        return is_tor_not_connected;
    }

    public void setIs_tor_not_connected(int is_tor_not_connected) {
        this.is_tor_not_connected = is_tor_not_connected;
    }

    public int getIs_timeout() {
        return is_timeout;
    }

    public void setIs_timeout(int is_timeout) {
        this.is_timeout = is_timeout;
    }

    public int getIs_fin_bit_set() {
        return is_fin_bit_set;
    }

    public void setIs_fin_bit_set(int is_fin_bit_set) {
        this.is_fin_bit_set = is_fin_bit_set;
    }

    public int getIs_rst_bit_set() {
        return is_rst_bit_set;
    }

    public void setIs_rst_bit_set(int is_rst_bit_set) {
        this.is_rst_bit_set = is_rst_bit_set;
    }

    public int getSuccessful_iteration_local_server() {
        return successful_iteration_local_server;
    }

    public void setSuccessful_iteration_local_server(int successful_iteration_local_server) {
        this.successful_iteration_local_server = successful_iteration_local_server;
    }

    public int getSuccessful_iteration_tor() {
        return successful_iteration_tor;
    }

    public void setSuccessful_iteration_tor(int successful_iteration_tor) {
        this.successful_iteration_tor = successful_iteration_tor;
    }

    public int getIs_tor_connect_successful() {
        return is_tor_connect_successful;
    }

    public void setIs_tor_connect_successful(int is_tor_connect_successful) {
        this.is_tor_connect_successful = is_tor_connect_successful;
    }

    public int getMiddle_box_hop_count() {
        return middle_box_hop_count;
    }

    public void setMiddle_box_hop_count(int middle_box_hop_count) {
        this.middle_box_hop_count = middle_box_hop_count;
    }

    public int getIs_censored_TCP() {
        return is_censored_TCP;
    }

    public void setIs_censored_TCP(int is_censored_TCP) {
        this.is_censored_TCP = is_censored_TCP;
    }

    @Override
    public String toString() {
        return "TCPDetails{" + "ip_address=" + ip_address + ", port=" + port + ", is_tor_not_connected=" + is_tor_not_connected + ", is_timeout=" + is_timeout + ", is_fin_bit_set=" + is_fin_bit_set + ", is_rst_bit_set=" + is_rst_bit_set + ", successful_iteration_local_server=" + successful_iteration_local_server + ", successful_iteration_tor=" + successful_iteration_tor + ", is_tor_connect_successful=" + is_tor_connect_successful + ", middle_box_hop_count=" + middle_box_hop_count + ", is_censored_TCP=" + is_censored_TCP + '}';
    }

}
