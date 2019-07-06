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

    static List<String> getListIPs(String s) {
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
        report.setCensorship_code(Integer.parseInt(getSecondColumn(arr[11])));
//        report.setDns_details();

        DNSDetails dns = new DNSDetails();

        //List of IP Addresses are obtained here
        dns.setListIpLocalDNSServer(getListIPs(arr[14]));
        dns.setListIpStubby(getListIPs(arr[15]));
        dns.formMatchedIPList();

        dns.setErrorCode(Integer.parseInt(getSecondColumn(arr[11].trim())));

        report.setDns_details(dns);
        dns.setReport(report);

//        System.out.println("FINALLY ... printing report ... ");
//        System.out.println(report.toString());
        return report;
        
    }
}
