/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.commands;

import ui.model.TCPDetails;

/**
 *
 * @author mahim
 */
public class Util {

    public static String getYesOrNo(String flag) {
        if (flag.contains("1")) {
            return "YES";
        } else {
            return "NO";
        }
    }

    public static String getYesOrNo(int flag) {
        if (flag == 0) {
            return "NO";
        } else {
            return "YES";
        }
    }

    /*
    public static void makeTCPDetailStrings(TCPDetails tcp) {
        tcp.setIs_censored_TCP_str(Util.getYesOrNo(tcp.getIs_censored_TCP()));
        tcp.setIs_fin_bit_set_str(Util.getYesOrNo(tcp.getIs_fin_bit_set()));
        tcp.setIs_rst_bit_set_str(Util.getYesOrNo(tcp.getIs_rst_bit_set()));
        tcp.setIs_timeout_str(Util.getYesOrNo(tcp.getIs_timeout()));
        tcp.setIs_tor_connect_successful_str(Util.getYesOrNo(tcp.getIs_tor_connect_successful()));
        tcp.setIs_tor_not_connected_str(Util.getYesOrNo(tcp.getIs_tor_not_connected()));
        if (tcp.getMiddle_box_hop_count() == -1) {
            tcp.setMiddle_box_hop_count_str("-");
        } else {
            tcp.setMiddle_box_hop_count_str(String.valueOf(tcp.getMiddle_box_hop_count()));
        }
        String str_loc = "Success at Attempt " + String.valueOf(tcp.getSuccessful_iteration_local_server());
        tcp.setSuccessful_iteration_local_server_str(str_loc);

        String str_tor = "Success at Attempt " + String.valueOf(tcp.getSuccessful_iteration_tor());
        tcp.setSuccessful_iteration_tor_str(str_tor);
    }
     */
    public static void makeTCPDetailStrings(TCPDetails tcp) {
        tcp.setIs_censored_TCP_str(Util.getYesOrNo(tcp.getIs_censored_TCP_str()));
        tcp.setIs_fin_bit_set_str(Util.getYesOrNo(tcp.getIs_fin_bit_set_str()));
        tcp.setIs_rst_bit_set_str(Util.getYesOrNo(tcp.getIs_rst_bit_set_str()));
        tcp.setIs_timeout_str(Util.getYesOrNo(tcp.getIs_timeout_str()));
        tcp.setIs_tor_connect_successful_str(Util.getYesOrNo(tcp.getIs_tor_connect_successful_str()));
        tcp.setIs_tor_not_connected_str(Util.getYesOrNo(tcp.getIs_tor_not_connected_str()));
        
        if (tcp.getMiddle_box_hop_count_str().contains("-1")) {
            tcp.setMiddle_box_hop_count_str("-");
        }
        
        String str_loc = "Success at Attempt " + tcp.getSuccessful_iteration_local_server_str();
        tcp.setSuccessful_iteration_local_server_str(str_loc);

        String str_tor = "Success at Attempt " + tcp.getSuccessful_iteration_tor_str();
        tcp.setSuccessful_iteration_tor_str(str_tor);
    }
}
