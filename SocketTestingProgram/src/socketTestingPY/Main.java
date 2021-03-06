package socketTestingPY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] arsgs) {
//        UDP_Server_Client.sendMessage("Hello python");
//        setUpReport();
    String str = "url:www.youtube.com###IP:185.88.181.7#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.5#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.3#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.8#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.11#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.2#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.9#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.6#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.4#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.10#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.7#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.5#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.3#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.8#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.11#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.2#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.9#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.6#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.4#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful###IP:185.88.181.10#####1th iteration TOR Connection Succesful#1th iteration Local Server Connection Succesful";
    processTCP(str);
//        processString();
        
        
//        System.out.println(getSecondColumn("TimeStamp:30-06-2019 03:49:42"));
    }
    static void processTCP(String str){
        //System.out.println("String is " + str);
    
        
    }

    /*
    Report:
    
    private int reportID;
    private String url;
    private String networkName;
    private String networkType;
    private String time;
    private String testType;
    private String isCensored;
    private String censoredType;
    private int censorship_code;

    private Button btn_details;
     */
    
    static String getTimeStampSecondCol(String s){
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
        if((s == null) || (s.trim().equals(""))){
            return s;
        }
        
        String[] arr = s.split(":");
        return arr[1];
    }
    
    static List<String> getListIPs(String s){
        List<String> list = new ArrayList<>();
        
        String[] arr = s.split(":");
        if(arr.length == 1){
            return list;
        }
        
        //else
        String ips = arr[1];
        String[]arr2 = ips.split(",");

        for(int i=0; i<arr2.length; i++){
            if(arr2[i].trim().equals("") == false){
                list.add(arr2[i].trim());
            }
        }
        
        return list;
    }

    static void processString() {
//        String str = "ReportID:25$ConnectionID:0$TimeStamp:30-06-2019 03:49:42$URL:www.facebook.com$Type_of_Test:NONE$is_censored:NO$method_of_censorship:NONE$is_file_check:1$is_periodic:no$file_name_periodic:NULL$iteration_number:0$censorship_code:0$censorshipDetails:NONE$middle_box_hop_count:0$local_ip_addresses:$stubby_ip_addresses:$END$                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ";

        String str = "ReportID:25$ConnectionID:0$TimeStamp:30-06-2019 03:49:42$URL:www.facebook.com$Type_of_Test:NONE$is_censored:NO$method_of_censorship:NONE$is_file_check:1$is_periodic:no$file_name_periodic:NULL$iteration_number:0$censorship_code:0$censorshipDetails:NONE$middle_box_hop_count:0$local_ip_addresses:128.0.1.0,122.0.0.1,$stubby_ip_addresses:$END$                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ";
        
        System.out.println(str);

        Report report = new Report();

        String[] arr = str.split("\\$");
        for (int i = 0; i < arr.length; i++) {
//            System.out.println(i + "->" + getSecondColumn(arr[i]) );//getSecondColumn(arr[i]));

            System.out.println(i + "->" + arr[i] );//getSecondColumn(arr[i]));
        }

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
        
        dns.setListIpLocalDNSServer(getListIPs(arr[14]));
        dns.setListIpStubby(getListIPs(arr[15]));
        dns.formMatchedIPList();
        
        dns.setErrorCode(Integer.parseInt(getSecondColumn(arr[11].trim())));
        
        
        report.setDns_details(dns);
        dns.setReport(report);
        
        System.out.println("FINALLY ... printing report ... ");
        
        System.out.println(report.toString());

    }

    private static void setUpReport() {
        Report report = new Report();
        report.setReportID(1);
        report.setUrl("www.pornhub.com");
        report.setTime("1:21 am Sunday");
        report.setTestType("DNS");
        report.setNetworkType("ISP");
        report.setNetworkName("XPLoreNet BD");
        report.setIsCensored("Yes");
        report.setCensoredType("DNS");

        DNSDetails dns = new DNSDetails();
        dns.setErrorCode(101);
        dns.setErrorMsgCensorship("Nx Domain ");
        dns.setIsBogonIP(false);
        dns.setIsInvalidDomain(false);
        dns.setIsNoNameServers(false);
        dns.setIsPrivateIP(false);
        dns.setIsTimeout(false);

        report.setDns_details(dns);
        dns.setReport(report);

        //new ArrayList<>("192.88.0.0", "192.88.0.1", "192.88.0.2")
        List<String> list = new ArrayList<>();
        list.add("192.88.0.1");
        list.add("192.88.0.2");
        list.add("192.88.0.3");

        dns.setListIpLocalDNSServer(new ArrayList<>(list));

        list.remove("192.88.0.1");
        list.add("192.88.0.5");
        list.add("192.88.0.7");

        dns.setListIpStubby(new ArrayList<>(list));

        dns.formMatchedIPList();

        System.out.println("Printing report ... ");
        System.out.println(report.toString());
    }
}
