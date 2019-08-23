/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.workerAndStates;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import ui.model.DNSDetails;
import ui.model.HTTPDetails;
import ui.model.HTTPSDetails;
//import main.Config;
import ui.model.Report;
import ui.model.TCPDetails;
import ui.model.User;

/**
 *
 * @author mahim
 */
public class StringProcessor {

    static String getSecondColumn_TimeStamp(String s) {
        StringTokenizer st = new StringTokenizer(s, ":");
        if (st.hasMoreElements()) {
            st.nextToken();
        }
        String str = "";
        while (st.hasMoreElements()) {
            str += ":" + st.nextToken();
        }
        String str2 = "";
        if (str.charAt(0) == ':') {
            int idx = 1;
            while (idx < str.length()) {
                str2 += str.charAt(idx);
                idx++;
            }
        }
        return str2;
    }

    static int getSecondColumn_Int(String s) {
        if ((s == null) || (s.trim().equals(""))) {
            return Integer.parseInt(s);
        }
        s = s.trim();

        String[] arr = s.split(":");
        return Integer.parseInt(arr[1]);
    }

    static String getSecondColumn_String(String s) {
//        System.out.println("-------->>>THIS CONDITION !! s = <" + s + ">");
        if ((s == null) || (s.trim().equals(""))) {
            return s;
        }

        String[] arr = s.split(":");
        if (arr.length == 1) {
            return ""; //nothing ...
        }
        return arr[1];
    }

    static List<String> getListStringDelimmedSecondColumn(String s, String delim) {
        List<String> list = new ArrayList<>();

        String[] arr = s.split(":");
        if (arr.length == 1) {
            return list;
        }

        //else
        String ips = arr[1];
//        String[] arr2 = ips.split(",");
        String[] arr2 = ips.split(delim);

        for (int i = 0; i < arr2.length; i++) {
            if (arr2[i].trim().equals("") == false) {
                list.add(arr2[i].trim());
            }
        }

        return list;
    }

    static List<Integer> getListIntegers(String s, String delim) {
        List<Integer> list = new ArrayList<>();

        String[] arr = s.split(":");
        if (arr.length == 1) {
            return list;
        }

        //else
        String ips = arr[1];
//        String[] arr2 = ips.split(",");
        String[] arr2 = ips.split(delim);

        for (int i = 0; i < arr2.length; i++) {
            if (arr2[i].trim().equals("") == false) {
                list.add(Integer.valueOf(arr2[i].trim()));
            }
        }

        return list;
    }

    public static Report processStringAndFormReport(String str) {
        Report report = new Report();

        String[] arr = str.split("\\$");

        int total_lines = arr.length;
        //Printing each line for debugging
        System.out.println("===================== Inside STRING PROCESSOR ... printing arr after split ... ==============================");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "->" + arr[i]);
        }
        System.out.println("===================== ****** ==============================");
        // ------------------------------ COMMON THINGS ARE ADDED ---------------------------
        int BASE = 0; //Everything indices will be BASE + 0 ... BASE + 1 ... etc

        report.setReport_id(getSecondColumn_Int(arr[0]));
        report.setConnection_id(getSecondColumn_Int(arr[1]));
        report.setTime_stamp(getSecondColumn_TimeStamp(arr[2]));
        report.setUrl(getSecondColumn_String(arr[3]));
        report.setTest_type(getSecondColumn_String(arr[4]));
        report.setIs_censored(getSecondColumn_String(arr[5]));
        report.setIs_periodic(getSecondColumn_String(arr[6]));
        report.setFile_name_periodic(getSecondColumn_String(arr[7]));
        report.setIteration_number(getSecondColumn_Int(arr[8]));
        report.setCensorship_details(getSecondColumn_String(arr[9]));
        //10th field is mid_box_hop_count --> is not taken
        int lines_common_fields = 11; // 0 to 10 are common so, 11 lines

        // ---------------------------------- TEST TYPE SPECIFIC ---------------------------------
        if (report.getTest_type().equals("DNS")) {
            //First fields of DNS details
            report.dns_details = new DNSDetails();
            report.dns_details.setIs_timeout(getSecondColumn_String(arr[11]));
            report.dns_details.setIs_loopback(getSecondColumn_String(arr[12]));
            report.dns_details.setIs_multicast(getSecondColumn_String(arr[13]));
            report.dns_details.setIs_broadcast(getSecondColumn_String(arr[14]));
            report.dns_details.setIs_private(getSecondColumn_String(arr[15]));
            report.dns_details.setIs_bogon(getSecondColumn_String(arr[16]));
            report.dns_details.setIs_unknown_error(getSecondColumn_String(arr[17]));
            report.dns_details.setIs_reserved(getSecondColumn_String(arr[18]));
            report.dns_details.setIs_nxDomain(getSecondColumn_String(arr[19]));
            report.dns_details.setIs_noAnswerPacket(getSecondColumn_String(arr[20]));
            report.dns_details.setIs_stubby_failed(getSecondColumn_String(arr[21]));
            report.dns_details.setIs_topExistingButAuthNotExisting(getSecondColumn_String(arr[22]));
            report.dns_details.setIs_timeout_local_server(getSecondColumn_String(arr[23]));
            report.dns_details.setIs_is_first_8_to_24_bit_match(getSecondColumn_String(arr[24]));

            //obtain list of ip addresses
            report.dns_details.setIp_address_list_local(getListStringDelimmedSecondColumn(arr[25], ","));
            report.dns_details.setIp_address_list_stubby(getListStringDelimmedSecondColumn(arr[26], ","));

            // Last two fields
            report.dns_details.setIs_non_overlapping_ip_list(getSecondColumn_String(arr[27]));
            report.dns_details.setMiddle_box_hop_count(getSecondColumn_String(arr[28]));

        } else if (report.getTest_type().equals("TCP")) {
            //TCP details adding ...
            report.tcp_details_list = new ArrayList<>(); //clearing just in case
            //Each TCP Description has 11 lines ...
            int num_lines_per_tcp_description = 11;
            int how_many_desc_tcp = ((total_lines - lines_common_fields) / (num_lines_per_tcp_description)); //correct

            int num_got_so_far = 0;

            int base_iter = lines_common_fields; //upto 10 is the common count so, lines_common_fields = 11 is base now
//            System.out.println("how_many_desc = " + how_many_desc_tcp);
            while (num_got_so_far < how_many_desc_tcp) {
                // ---------------------------- Once taken begin ----------------------------
                TCPDetails tcp = new TCPDetails();
                tcp.setIp_address(getSecondColumn_String(arr[base_iter + 0]));
                tcp.setPort(getSecondColumn_Int(arr[base_iter + 1]));
                tcp.setIs_tor_not_connected_str(getSecondColumn_String(arr[base_iter + 2]));
                tcp.setIs_timeout_str(getSecondColumn_String(arr[base_iter + 3]));
                tcp.setIs_fin_bit_set_str(getSecondColumn_String(arr[base_iter + 4]));
                tcp.setIs_rst_bit_set_str(getSecondColumn_String(arr[base_iter + 5]));
                tcp.setSuccessful_iteration_local_server_str(getSecondColumn_String(arr[base_iter + 6]));
                tcp.setSuccessful_iteration_tor_str(getSecondColumn_String(arr[base_iter + 7]));
                tcp.setIs_tor_connect_successful_str(getSecondColumn_String(arr[base_iter + 8]));
                tcp.setMiddle_box_hop_count_str(getSecondColumn_String(arr[base_iter + 9]));
//                System.out.println("--.>>> TCP middle box hop count set = " + arr[base_iter + 9] + " , get hop cnt st = " + tcp.getMiddle_box_hop_count_str());
                tcp.setIs_censored_TCP_str(getSecondColumn_String(arr[base_iter + 10]));
                // -------------------- Once taken done ------------------------------------
                report.tcp_details_list.add(tcp);

                num_got_so_far++;
                base_iter += (num_lines_per_tcp_description); //Advance the pointer
            }

        } else if (report.getTest_type().equals("HTTP") || report.getTest_type().equals("HTTPS")) {
            System.out.println("------->>>HTTP/HTTPS TEST TYPE .... TO DO!! ");

            //Add HTTP
            report.httpDetails = new HTTPDetails();
            int base_itr_to_be_added = 10;
            report.httpDetails.setIp_address(getListStringDelimmedSecondColumn(arr[base_itr_to_be_added + 0], "#"));
            report.httpDetails.setHttp_response_code_local(getListStringDelimmedSecondColumn(arr[base_itr_to_be_added + 1], "#"));
            report.httpDetails.setHttp_response_code_tor(getListStringDelimmedSecondColumn(arr[base_itr_to_be_added + 2], "#"));
            report.httpDetails.setIs_other_error(getSecondColumn_String(arr[base_itr_to_be_added + 3]));
            report.httpDetails.setMessage_HTTP(getSecondColumn_String(arr[base_itr_to_be_added + 4]));
            report.httpDetails.setIs_fin_bit_set(getSecondColumn_String(arr[base_itr_to_be_added + 5]));
            report.httpDetails.setIs_rst_bit_set(getSecondColumn_String(arr[base_itr_to_be_added + 6]));

            report.httpDetails.setRedirection_history_local(getListStringDelimmedSecondColumn_URL(arr[base_itr_to_be_added + 7], "#"));
            report.httpDetails.setRedirection_history_tor(getListStringDelimmedSecondColumn_URL(arr[base_itr_to_be_added + 8], "#"));

            report.httpDetails.setThreshold_comparison(getSecondColumn_String(arr[base_itr_to_be_added + 9]));
            report.httpDetails.setIs_different_wrt_threshold(getSecondColumn_String(arr[base_itr_to_be_added + 10]));
            report.httpDetails.setContent_difference(getSecondColumn_String(arr[base_itr_to_be_added + 11]));
            report.httpDetails.setIs_max_redirection_reached(getSecondColumn_String(arr[base_itr_to_be_added + 12]));
            report.httpDetails.setMax_redirection_count(getSecondColumn_String(arr[base_itr_to_be_added + 13]));
            report.httpDetails.setMiddle_box_hop_count(getSecondColumn_String(arr[base_itr_to_be_added + 14]));

            //Add HTTPS
            report.httpsDetails = new HTTPSDetails();
            base_itr_to_be_added = 25;
            report.httpsDetails.setIp_address(getListStringDelimmedSecondColumn(arr[base_itr_to_be_added + 0], "#"));
            report.httpsDetails.setHttps_response_code_local(getListStringDelimmedSecondColumn(arr[base_itr_to_be_added + 1], "#"));
            report.httpsDetails.setHttps_response_code_tor(getListStringDelimmedSecondColumn(arr[base_itr_to_be_added + 2], "#"));
            report.httpsDetails.setIs_other_error(getSecondColumn_String(arr[base_itr_to_be_added + 3]));
            report.httpsDetails.setMessage_HTTPS(getSecondColumn_String(arr[base_itr_to_be_added + 4]));
            report.httpsDetails.setIs_fin_bit_set(getSecondColumn_String(arr[base_itr_to_be_added + 5]));
            report.httpsDetails.setIs_rst_bit_set(getSecondColumn_String(arr[base_itr_to_be_added + 6]));

            report.httpsDetails.setRedirection_history_local(getListStringDelimmedSecondColumn_URL(arr[base_itr_to_be_added + 7], "#"));
            report.httpsDetails.setRedirection_history_tor(getListStringDelimmedSecondColumn_URL(arr[base_itr_to_be_added + 8], "#"));

            report.httpsDetails.setThreshold_comparison(getSecondColumn_String(arr[base_itr_to_be_added + 9]));
            report.httpsDetails.setIs_different_wrt_threshold(getSecondColumn_String(arr[base_itr_to_be_added + 10]));
            report.httpsDetails.setContent_difference(getSecondColumn_String(arr[base_itr_to_be_added + 11]));
            report.httpsDetails.setIs_max_redirection_reached(getSecondColumn_String(arr[base_itr_to_be_added + 12]));
            report.httpsDetails.setMax_redirection_count(getSecondColumn_String(arr[base_itr_to_be_added + 13]));
            report.httpsDetails.setMiddle_box_hop_count(getSecondColumn_String(arr[base_itr_to_be_added + 14]));

            System.out.println("-------->>> RECEIVED printing http and https details");
            System.out.println(report.httpDetails.toString());
            System.out.println("-----------");
            System.out.println(report.httpsDetails.toString());
        }
        //FOR NOW
        report.setNetwork_name(User.networkName);
        report.setNetwork_type(User.networkType);
        return report;
    }

    private static List<String> getListStringDelimmedSecondColumn_URL(String s, String delim) {
        List<String> list = new ArrayList<>();

        String[] arr = s.split("#");  //initial split too
        if (arr.length == 1) {
            return list;
        }

        //else
        String ips = arr[1];
//        String[] arr2 = ips.split(",");
        String[] arr2 = ips.split(delim);

        for (int i = 0; i < arr2.length; i++) {
            if (arr2[i].trim().equals("") == false) {
                list.add(arr2[i].trim());
            }
        }

        return list;

    }
}
