/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.client;

import java.io.Serializable;
import java.util.List;
import ui.model.Network;
import ui.model.Report;
import ui.model.User;

/**
 *
 * @author mahim
 */
public class ReportDetailedThing implements Serializable {
//User Part

    public ReportDetailedThing() {
    }

    String userID;
    String userName;
    String userEmail;
    String userPassword;

//Network Part
    String networkGlobalIP;
    String networkASN;
    String networkCity;
    String networkOrganization;
    String networkCarrierName;
    String networkLatitude;
    String networkLongitude;
    String networkCountry;
    String networkRegion;
    String networkPostal;

//Report Part
    String report_reportID;
    String report_connectionID;
    String report_timestamp;
    String report_url;
    String report_testType;
    String report_isCensored;
    String report_isPeriodic;
    String report_fileNamePeriodic;
    String report_iterationNumber;
//Details Part
    //DNS Details
    String dns_ReportID;
    String dns_isTimeOut;
    String dns_isLoopBack;
    String dns_isMultiCast;
    String dns_isBroadCast;
    String dns_isPrivate;
    String dns_isBogon;
    String dns_isUnknownError;
    String dns_isNXDomain;
    String dns_isNoAnswerPacket;
    String dns_isStubbyFailed;
    String dns_isTopExistingButAuthNot;
    String dns_isTimeOutLocalServer;
    String dns_isNonOverlappingIPList;
    String dns_isFirst_8_to_24_bit_match;
    String dns_middleBoxHopCount;
    List<String> dns_ipAddressesListLocalServer;
    List<String> dns_ipAddressesListStubby;

    //TCP Descriptions
    void setReportThings(Report report) {
        this.report_connectionID = String.valueOf(report.getConnection_id());
        this.report_fileNamePeriodic = report.getFile_name_periodic();
        this.report_isCensored = report.getIs_censored();
        this.report_isPeriodic = report.getIs_periodic();
        this.report_iterationNumber = String.valueOf(report.getIteration_number());
        this.report_reportID = String.valueOf(report.getReport_id());
        this.report_testType = report.getTest_type();
        this.report_timestamp = report.getTime_stamp();
        this.report_url = report.getUrl();
    }

    void setNetworkDetails() {
        this.networkASN = Network.asn_static;
        this.networkCarrierName = Network.carrier_static;
        this.networkCity = Network.city_static;
        this.networkCountry = Network.country_static;
        this.networkGlobalIP = Network.global_ip_static;
        this.networkLatitude = Network.latitude_static;
        this.networkLongitude = Network.longitude_static;
        this.networkOrganization = Network.org_static;
        this.networkPostal = Network.postal_static;
        this.networkRegion = Network.region_static;
    }
    void setUserDetails(){
        this.userEmail = User.userEmailAddress;
        this.userName = User.userName;
        this.userID = String.valueOf(User.userID);
        this.userPassword = User.userPassword;
    }

    void setDetailsAccordingToTestType(Report report) {
        if(report.getTest_type().trim().toUpperCase().equals("DNS")){
            setDNSDetails(report);
        }else if(report.getTest_type().trim().toUpperCase().equals("TCP")){
            setTCPDetails(report);
        }else if(report.getTest_type().trim().toUpperCase().equals("HTTP")){
            setHTTPDetails(report);
        }
    }

    private void setDNSDetails(Report report) {
        this.dns_ReportID = String.valueOf(report.getReport_id());
        this.dns_ipAddressesListLocalServer = report.dns_details.getIp_address_list_local();
        this.dns_ipAddressesListStubby = report.dns_details.getIp_address_list_stubby();
        this.dns_isBogon = report.dns_details.getIs_bogon();
        this.dns_isBroadCast = report.dns_details.getIs_broadcast();
        this.dns_isFirst_8_to_24_bit_match = report.dns_details.getIs_is_first_8_to_24_bit_match();
        this.dns_isLoopBack = report.dns_details.getIs_loopback();
        this.dns_isMultiCast = report.dns_details.getIs_multicast();
        this.dns_isNXDomain = report.dns_details.getIs_nxDomain();
        this.dns_isNoAnswerPacket = report.dns_details.getIs_noAnswerPacket();
        this.dns_isNonOverlappingIPList = report.dns_details.getIs_non_overlapping_ip_list();
        this.dns_isPrivate = report.dns_details.getIs_private();
        this.dns_isStubbyFailed = report.dns_details.getIs_stubby_failed();
        this.dns_isTimeOut = report.dns_details.getIs_timeout();
        this.dns_isTimeOutLocalServer = report.dns_details.getIs_timeout_local_server();
        this.dns_isTopExistingButAuthNot = report.dns_details.getIs_topExistingButAuthNotExisting();
        this.dns_isUnknownError = report.dns_details.getIs_unknown_error();
    }

    private void setTCPDetails(Report report) {
        System.out.println("-->>TO DO TCP DETAILS");
    }

    private void setHTTPDetails(Report report) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class TCP_Details_Serializable implements Serializable {

    private String ip_address;
    private int port; //80 or 453

    private String is_tor_not_connected_str;
    private String is_timeout_str;
    private String is_fin_bit_set_str;
    private String is_rst_bit_set_str;
    private String is_tor_connect_successful_str;
    private String is_censored_TCP_str = "NO"; //default 0 [not censored]
    private String middle_box_hop_count_str;

    private String successful_iteration_local_server_str;
    private String successful_iteration_tor_str;
}
