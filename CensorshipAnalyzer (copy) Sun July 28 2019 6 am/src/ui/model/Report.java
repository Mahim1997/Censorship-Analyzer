package ui.model;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import ui.controllers.CensoredRecordController;
import ui.controllers.CensoredRecordController_Waiting;

public class Report {

    public String number_of_attempts = "2";

    public String getNumber_of_attempts() {
        return number_of_attempts;
    }

    public void setNumber_of_attempts(String number_of_attempts) {
        this.number_of_attempts = number_of_attempts;
    }

    //------------ Normal Things ---------------
    private int report_id;
    private int connection_id;
    private String time_stamp;
    private String url;
    private String test_type;
    private String is_censored; //0 or 1 -> No or Yes
    private String censored_type;

    //-----------File Things and DETAILS --------------------
    private String is_file_check;
    private String is_periodic;
    private String file_name_periodic;
    private String censorship_details = "NULL";
    private int iteration_number;
    public DNSDetails dns_details;
    public List<TCPDetails> tcp_details_list = new ArrayList<>();
    public HTTPDetails httpDetails;
    public HTTPSDetails httpsDetails;

    // ------------ Network Things ----------------
    public Network network = new Network();

    private String network_name;
    private String network_type;

    //Button
    private Button btn_details;

    //---------------------------- Controllers ------------------------------------
    private CensoredRecordController_Waiting controller_waiting;
    private CensoredRecordController controller_for_db;

    public void setController2(CensoredRecordController_Waiting controller_waiting) {
        this.controller_waiting = controller_waiting;
    }

    public void setController1(CensoredRecordController controller_db) {
        this.controller_for_db = controller_db;
    }
    //---------------------------- Controllers ------------------------------------

    public Report() {
        this.test_type = "";
        this.censored_type = "NONE";

        this.btn_details = new Button("Details");
        this.btn_details.setOnAction((ActionEvent event) -> {
            if (Report.this.controller_waiting != null) {
                String testType_Local = Report.this.getTest_type();
                System.out.println("\n================+>>>>>>>>>Inside Report ... btn_details.setOnAction() --> getTestType() = " + testType_Local + "\n");
                switch (testType_Local.toUpperCase()) {
                    case "DNS":
                        //if test type is DNS
                        Report.this.controller_waiting.goToDetailsDNSRecord(Report.this.report_id);
                        break;
                    case "TCP":
                        // if test type is TCP
                        Report.this.controller_waiting.goToDetailsTCPRecord(Report.this.report_id);
                        break;
                    case "HTTP":
                        //TO DO
                        Report.this.controller_waiting.goToDetailsHTTP_HTTPSRecord(Report.this.report_id);
                        break;
                    case "HTTPS":
                        Report.this.controller_waiting.goToDetailsHTTP_HTTPSRecord(Report.this.report_id);
                        break;
                    default:
                        break;
                }
            } else if (Report.this.controller_for_db != null) {
                //Same thing also here ...
            }
        });

    }

    public String getNetwork_name() {
        return network_name;
    }

    public void setNetwork_name(String network_name) {
        this.network_name = network_name;
    }

    public String getNetwork_type() {
        return network_type;
    }

    public void setNetwork_type(String network_type) {
        this.network_type = network_type;
    }

    public int getIteration_number() {
        return iteration_number;
    }

    public void setIteration_number(int iteration_number) {
        this.iteration_number = iteration_number;
    }

    public String getCensored_type() {
        return censored_type;
    }

    public void setCensored_type(String censored_type) {
        this.censored_type = censored_type;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getConnection_id() {
        return connection_id;
    }

    public void setConnection_id(int connection_id) {
        this.connection_id = connection_id;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getUrl() {
        return url;
    }

    public Button getBtn_details() {
        return btn_details;
    }

    public void setBtn_details(Button btn_details) {
        this.btn_details = btn_details;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTest_type() {
        return test_type;
    }

    public void setTest_type(String test_type) {
        this.test_type = test_type;
    }

    public String getYesOrNo(String s) {
        if (s != null) {
//            System.out.println("YES/NO check --> s = <" + s + ">" );
            if (s.trim().equals("0") || s.trim().equals("NO")) {
                s = "NO";
            } else {
                s = "YES";
            }
        }
        return s;
    }

    public String getIs_censored() {
        is_censored = getYesOrNo(is_censored);
        return is_censored;
    }

    public void setIs_censored(String is_censored) {
        this.is_censored = is_censored;
    }

    public String getIs_file_check() {
        return is_file_check;
    }

    public void setIs_file_check(String is_file_check) {
        this.is_file_check = is_file_check;
    }

    public String getIs_periodic() {
        return is_periodic;
    }

    public void setIs_periodic(String is_periodic) {
        this.is_periodic = is_periodic;
    }

    public String getFile_name_periodic() {
        return file_name_periodic;
    }

    public void setFile_name_periodic(String file_name_periodic) {
        this.file_name_periodic = file_name_periodic;
    }

    public String getCensorship_details() {
        return censorship_details;
    }

    public void setCensorship_details(String censorship_details) {
        this.censorship_details = censorship_details;
    }

    public DNSDetails getDns_details() {
        return dns_details;
    }

    public void setDns_details(DNSDetails dns_details) {
        this.dns_details = dns_details;
    }

    public List<TCPDetails> getTcp_details_arr() {
        return tcp_details_list;
    }

    public void setTcp_details_arr(List<TCPDetails> tcp_details_arr) {
        this.tcp_details_list = tcp_details_arr;
    }
//
//    public CensoredRecordController_Waiting getController_waiting() {
//        return controller_waiting;
//    }
//
//    public void setController_waiting(CensoredRecordController_Waiting controller_waiting) {
//        this.controller_waiting = controller_waiting;
//    }
//
//    public CensoredRecordController getController_for_db() {
//        return controller_for_db;
//    }
//
//    public void setController_for_db(CensoredRecordController controller_for_db) {
//        this.controller_for_db = controller_for_db;
//    }

    public String getOnlyReportString() {
        return "Report{" + "report_id=" + report_id + ", time_stamp=" + time_stamp + ", url=" + url + ", test_type=" + test_type + ", is_censored=" + is_censored + ", censored_type=" + censored_type + ", censorship_details=" + censorship_details + ", networkName=" + network_name + ", networkType=" + network_type + '}';
    }

    @Override
    public String toString() {
        String str = this.getOnlyReportString();

        if (this.test_type.equals("DNS")) {
            str += this.dns_details.toString();
        } else if (this.test_type.equals("TCP")) {
            str += this.tcp_details_list.stream().map((tcpDet) -> tcpDet.toString()).reduce(str, String::concat); //for each tcp_details ... 
        }
        return str;
    }

    public void printReport() {
        System.out.println("------------------------ PRINTING REPORT BEGIN ---------------------------------");
        System.out.println(this.toString());
        System.out.println("-------------------- *** Printing report end *** ----------------------");
    }

    public String getReportString() {
        String s = "";

        s += ("$REPORT$" + String.valueOf(report_id) + "$" + String.valueOf(connection_id) + "$" + time_stamp + "$"
                + url + "$" + test_type + "$" + String.valueOf(is_censored) + "$" + String.valueOf(is_periodic)
                + "$" + String.valueOf(file_name_periodic) + "$" + String.valueOf(iteration_number));
        s += "$#";

        if (this.getTest_type().equals("DNS")) {
            s += ("$DNS_DESCRIPTION$" + this.report_id + "$");
            s += this.dns_details.getDNSDetails();
            s += "$#";
        } else if (this.getTest_type().equals("TCP")) {
            s += ("$TCP_DESCRIPTION$" + this.report_id + "$");
            for (int i = 0; i < this.tcp_details_list.size(); i++) {
                s += ("$");
                TCPDetails det = this.tcp_details_list.get(i);
                s += det.getTCPDetails();
            }

            s += "$#";
        } else if (this.getTest_type().equals("HTTP") || (this.getTest_type().equals("HTTPS"))) {
            s += ("$HTTP_DESCRIPTION$" + String.valueOf(this.report_id) + "$");
            s += (this.httpDetails.getDescription());
            s += ("$HTTPS_DESCRIPTION$" + String.valueOf(this.report_id) + "$");
            s += (this.httpsDetails.getDescription());
            s += "$#";
        }

        return s;
    }

    public String getReportString_New() {
        String s = "";

        s += "</>$"; //for Report
        s += (String.valueOf(report_id) + "$" + String.valueOf(connection_id) + "$" + time_stamp + "$" + url + "$" + test_type + "$"
                + String.valueOf(is_censored) + "$" + String.valueOf(is_periodic) + "$"
                + String.valueOf(file_name_periodic) + "$" + String.valueOf(iteration_number));

        s += "$</>$"; // for Details

        if (this.getTest_type().equals("DNS")) {
            s += (String.valueOf(this.report_id) + "$");
            s += this.dns_details.getDNSDetails();
            s += "$<END>";
        } else if (this.getTest_type().equals("TCP")) {
//            s += ("$TCP_DESCRIPTION$" + this.report_id + "$");
            s += (String.valueOf(this.report_id));
            String numberOfTCPRecords = String.valueOf(this.tcp_details_list.size());
            for (int i = 0; i < this.tcp_details_list.size(); i++) {
                s += ("$<#" + numberOfTCPRecords + ">$");
                TCPDetails det = this.tcp_details_list.get(i);
                s += det.getTCPDetails();
            }
            s += "$<END>$";
        }

        return s;
    }

}
