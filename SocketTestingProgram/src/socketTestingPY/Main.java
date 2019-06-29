package socketTestingPY;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] arsgs) {
//        UDP_Server_Client.sendMessage("Hello python");
        setUpReport();
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
