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
import ui.model.TCPDetails;
import ui.model.Report;
import ui.model.User;

/**
 *
 * @author mahim
 */
public class StringProcessor {

    static String getTimeStampSecondCol(String s) {
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

    static String getSecondColumn(String s) {
        if ((s == null) || (s.trim().equals(""))) {
            return s;
        }

        String[] arr = s.split(":");
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
//        String str = "ReportID:25$ConnectionID:0$TimeStamp:30-06-2019 03:49:42$URL:www.facebook.com$Type_of_Test:NONE$is_censored:NO$method_of_censorship:NONE$is_file_check:1$is_periodic:no$file_name_periodic:NULL$iteration_number:0$censorship_code:0$censorshipDetails:NONE$middle_box_hop_count:0$local_ip_addresses:$stubby_ip_addresses:$END$                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ";

//        String str = "ReportID:25$ConnectionID:0$TimeStamp:30-06-2019 03:49:42$URL:www.facebook.com$Type_of_Test:NONE$is_censored:NO$method_of_censorship:NONE$is_file_check:1$is_periodic:no$file_name_periodic:NULL$iteration_number:0$censorship_code:0$censorshipDetails:NONE$middle_box_hop_count:0$local_ip_addresses:128.0.1.0,122.0.0.1,$stubby_ip_addresses:$END$                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ";
//        System.out.println(str);
//        System.out.println("==========================================MSG RECEIVED IS===================================================");
//        System.out.println(str);
        Report report = new Report();

        String[] arr = str.split("\\$");

//        for(int i=0; i<arr.length; i++){
//            System.out.println(i + "->" + arr[i]);
//        }
//        System.out.println("---------------------------------------------------------------------------------------------");
        //For now setting to banglalink
        report.setNetworkName(User.networkName);
        report.setNetworkType(User.networkType);

        //Local IP addresses may give errors ... 
        report.setReportID(Integer.parseInt(getSecondColumn(arr[0])));
        report.setTime(getTimeStampSecondCol(arr[2]));
        report.setUrl(getSecondColumn(arr[3]));
        report.setTestType(getSecondColumn(arr[4]));
        report.setIsCensored(getSecondColumn(arr[5]));
        report.setCensoredType(getSecondColumn(arr[6]));

        if (report.getTestType().toUpperCase().equals("DNS")) { //This only exists for DNS
            try {
                report.setCensorship_code(Integer.parseInt(getSecondColumn(arr[11])));
            } catch (NumberFormatException e) {
                System.out.println("---++---->>> Exception  e in StringProcessor.formReport() for setCensorship_Code");
            }
        }

//        report.setDns_details();
        if (report.getTestType().toUpperCase().equals("DNS")) {
            DNSDetails dns = new DNSDetails();

            //List of IP Addresses are obtained here
            dns.setListIpLocalDNSServer(getListStringDelimbyCommaSecondColumn(arr[14]));
            dns.setListIpStubby(getListStringDelimbyCommaSecondColumn(arr[15]));
            dns.formMatchedIPList();

            dns.setErrorCode(Integer.parseInt(getSecondColumn(arr[11].trim())));

            //Cross-connection initializing
            report.setDns_details(dns);
            dns.setReport(report);
            //Cross-connection done
        } else if (report.getTestType().toUpperCase().equals("TCP")) {
            TCPDetails tcp = new TCPDetails();
            tcp.setHop_count_http(Integer.valueOf(getSecondColumn(arr[14]).trim()));
            tcp.setHop_count_https(Integer.valueOf(getSecondColumn(arr[15]).trim()));
            tcp.setTorConnectionUnsuccessfullHTTP(arr[16]);
            tcp.setTorConnectionUnsuccessfullHTTPS(arr[17]);
            tcp.setIp_addresses_resolved(getListStringDelimbyCommaSecondColumn(arr[18]));

            tcp.setIteration_success_tor_http(getListStringDelimbyCommaSecondColumn(arr[19]));
            tcp.setIteration_success_local_http(getListStringDelimbyCommaSecondColumn(arr[20]));

            tcp.setIteration_success_tor_https(getListStringDelimbyCommaSecondColumn(arr[21]));
            tcp.setIteration_success_local_https(getListStringDelimbyCommaSecondColumn(arr[22]));

            tcp.setIs_censored_http_thisIP(getListStringDelimbyCommaSecondColumn(arr[23]));
            tcp.setIs_censored_https_thisIP(getListStringDelimbyCommaSecondColumn(arr[24]));

            //Cross-connection initializing
            report.setTcp_details(tcp);
            tcp.setReport(report);
            //Cross-connection done
        }
        System.out.println("--------------------------------============= ****** ================--------------------------------------");
        System.out.println("FINALLY in StringProcessor.processString() -->> printing report ... ");
        System.out.println(report.toString());
        return report;

    }
}
