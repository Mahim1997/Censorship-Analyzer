package ui.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import ui.controllers.CensoredRecordController;
import ui.controllers.CensoredRecordController_Waiting;

public class Report {

    private int reportID;
    private String url;
    private String networkName;
    private String networkType;
    private String time;
    private String testType;
    private String isCensored;
    private String censoredType;
    private Button btn_details;

    private int censorship_code;

    private DNSDetails dns_details;     //For DNS Details
    private TCPDetails tcp_details;     //For TCP Details

    public TCPDetails getTcp_details() {
        return tcp_details;
    }

    public void setTcp_details(TCPDetails tcp_details) {
        this.tcp_details = tcp_details;
    }
    
    
    private String global_ip;

    public String getGlobal_ip() {
        return global_ip;
    }

    public void setGlobal_ip(String global_ip) {
        this.global_ip = global_ip;
    }

    //---------------------------- Controllers ------------------------------------
    private CensoredRecordController_Waiting controller2;
    private CensoredRecordController controller1;

    public void setController2(CensoredRecordController_Waiting controller2) {
        this.controller2 = controller2;
    }

    public void setController1(CensoredRecordController controller1) {
        this.controller1 = controller1;
    }
    //---------------------------- Controllers ------------------------------------
    
    
    public Report() {
        //If we do this now, then reportID values etc will become null
//        this.dns_details = new DNSDetails(this);
        this.btn_details = new Button("Details");
        this.btn_details.setOnAction((ActionEvent event) -> {
            if (Report.this.controller2 != null) {
                String testType_Local = Report.this.getTestType();
                System.out.println("\n================+>>>>>>>>>Inside Report ... getTestType() = " + testType_Local + "\n");
                switch (testType_Local.toUpperCase()) {
                    case "DNS":
                        //if test type is DNS
                        Report.this.controller2.goToDetailsDNSRecord(Report.this.reportID);
                        break;
                    case "TCP":
                        // if test type is TCP
                        Report.this.controller2.goToDetailsTCPRecord(Report.this.reportID);
                        break;
                    case "HTTP":
                        //TO DO
                        System.out.println("==========+>>>>>>>>> Inside Report.Report() constructor .... TO DO LINE 78");
                        break;
                    default:
                        break;
                }
            } else if (Report.this.controller1 != null) {
                //Same thing also here ...
            }
        });
    }

    public Report(int reportID, String url, String networkName, String networkType, String time, String testType, String isCensored, String censoredType) {
        this.reportID = reportID;
        this.url = url;
        this.networkName = networkName;
        this.networkType = networkType;
        this.time = time;
        this.testType = testType;
        this.isCensored = isCensored;
        this.censoredType = censoredType;
        //Probably this will work ... 
        this.dns_details = new DNSDetails(this);
    }

    public int getCensorship_code() {
        return censorship_code;
    }

    public void setCensorship_code(int censorship_code) {
        this.censorship_code = censorship_code;
    }

    public int getReportID() {
        return reportID;
    }

    @Override
    public String toString() {
        if (this.dns_details == null) {
            return "Report{" + "reportID=" + reportID + ", url=" + url + ", networkName=" + networkName + ", networkType=" + networkType + ", time=" + time + ", testType=" + testType + ", isCensored=" + isCensored + ", censoredType=" + censoredType + '}';

        } else {
            return "Report{" + "reportID=" + reportID + ", url=" + url + ", networkName=" + networkName + ", networkType=" + networkType + ", time=" + time + ", testType=" + testType + ", isCensored=" + isCensored + ", censoredType=" + censoredType + ", dns_details=" + dns_details.toString() + '}';

        }
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getIsCensored() {
        return isCensored;
    }

    public void setIsCensored(String isCensored) {
        this.isCensored = isCensored;
    }

    public String getCensoredType() {
        return censoredType;
    }

    public void setCensoredType(String censoredType) {
        this.censoredType = censoredType;
    }

    public Button getBtn_details() {
        return btn_details;
    }

    public void setBtn_details(Button btn_details) {
        this.btn_details = btn_details;
    }

    public DNSDetails getDns_details() {
        return dns_details;
    }

    public void setDns_details(DNSDetails dns_details) {
        this.dns_details = dns_details;
    }

}
