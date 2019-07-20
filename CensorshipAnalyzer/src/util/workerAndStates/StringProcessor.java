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
//import main.Config;
import ui.model.Report;
import ui.model.TCPDetails;

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

    static List<String> getListStringDelimbyCommaSecondColumn(String s) {
        List<String> list = new ArrayList<>();

        String[] arr = s.split(":");
        if (arr.length == 1) {
            return list;
        }

        //else
        String ips = arr[1];
        String[] arr2 = ips.split(",");

        for (int i = 0; i < arr2.length; i++) {
            if (arr2[i].trim().equals("") == false) {
                list.add(arr2[i].trim());
            }
        }

        return list;
    }

    static List<Integer> getListIntegers(String s) {
        List<Integer> list = new ArrayList<>();

        String[] arr = s.split(":");
        if (arr.length == 1) {
            return list;
        }

        //else
        String ips = arr[1];
        String[] arr2 = ips.split(",");

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
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "->" + arr[i]);
        }

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
            report.dns_details.setIs_timeout(getSecondColumn_Int(arr[11]));
            report.dns_details.setIs_loopback(getSecondColumn_Int(arr[12]));
            report.dns_details.setIs_multicast(getSecondColumn_Int(arr[13]));
            report.dns_details.setIs_broadcast(getSecondColumn_Int(arr[14]));
            report.dns_details.setIs_private(getSecondColumn_Int(arr[15]));
            report.dns_details.setIs_bogon(getSecondColumn_Int(arr[16]));
            report.dns_details.setIs_unknown_error(getSecondColumn_Int(arr[17]));
            report.dns_details.setIs_reserved(getSecondColumn_Int(arr[18]));
            report.dns_details.setIs_nxDomain(getSecondColumn_Int(arr[19]));
            report.dns_details.setIs_noAnswerPacket(getSecondColumn_Int(arr[20]));
            report.dns_details.setIs_stubby_failed(getSecondColumn_Int(arr[21]));
            report.dns_details.setIs_topExistingButAuthNotExisting(getSecondColumn_Int(arr[22]));
            report.dns_details.setIs_timeout_local_server(getSecondColumn_Int(arr[23]));
            report.dns_details.setIs_is_first_8_to_24_bit_match(getSecondColumn_Int(arr[24]));

            //obtain list of ip addresses
            report.dns_details.setIp_address_list_local(getListStringDelimbyCommaSecondColumn(arr[25]));
            report.dns_details.setIp_address_list_stubby(getListStringDelimbyCommaSecondColumn(arr[26]));

            // Last two fields
            report.dns_details.setIs_non_overlapping_ip_list(getSecondColumn_Int(arr[27]));
            report.dns_details.setMiddle_box_hop_count(getSecondColumn_Int(arr[28]));

        } else if (report.getTest_type().equals("TCP")) {
            //TCP details adding ...
            report.tcp_details_arr = new ArrayList<>(); //clearing just in case
            //Each TCP Description has 11 lines ...
            int num_lines_per_tcp_description = 11;
            int how_many_desc_tcp = ((total_lines - lines_common_fields) / (num_lines_per_tcp_description)); //correct

            int num_got_so_far = 0;

            int base_iter = lines_common_fields ; //upto 10 is the common count so, lines_common_fields = 11 is base now
//            System.out.println("how_many_desc = " + how_many_desc_tcp);
            while (num_got_so_far < how_many_desc_tcp) {
                // ---------------------------- Once taken begin ----------------------------
                TCPDetails tcp = new TCPDetails();
                tcp.setIp_address(getSecondColumn_String(arr[base_iter + 0]));
                tcp.setPort(getSecondColumn_Int(arr[base_iter + 1]));
                tcp.setIs_tor_not_connected(getSecondColumn_Int(arr[base_iter + 2]));
                tcp.setIs_timeout(getSecondColumn_Int(arr[base_iter + 3]));
                tcp.setIs_fin_bit_set(getSecondColumn_Int(arr[base_iter + 4]));
                tcp.setIs_rst_bit_set(getSecondColumn_Int(arr[base_iter + 5]));
                tcp.setSuccessful_iteration_local_server(getSecondColumn_Int(arr[base_iter + 6]));
                tcp.setSuccessful_iteration_tor(getSecondColumn_Int(arr[base_iter + 7]));
                tcp.setIs_tor_connect_successful(getSecondColumn_Int(arr[base_iter + 8]));
                tcp.setMiddle_box_hop_count(getSecondColumn_Int(arr[base_iter + 9]));
                tcp.setIs_censored_TCP(getSecondColumn_Int(arr[base_iter + 10]));
                // -------------------- Once taken done ------------------------------------
                report.tcp_details_arr.add(tcp);
                
                num_got_so_far++;
            }

        }

        return report;
    }
}
