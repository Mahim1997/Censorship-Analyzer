/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.model;

import java.util.List;

/**
 *
 * @author mahim
 */
public class TCPDetails {

    private Report report; //Parent Report

    private List<String> ip_addresses_resolved;
    private List<String> iteration_success_tor_http;
    private List<String> iteration_success_local_http;

    private List<String> iteration_success_tor_https;
    private List<String> iteration_success_local_https;

    private List<String> is_censored_http_thisIP;
    private List<String> is_censored_https_thisIP;

    private int hop_count_http;
    private int hop_count_https;

    private String torConnectionUnsuccessfullHTTP;
    private String torConnectionUnsuccessfullHTTPS;

    public String getTorConnectionUnsuccessfullHTTP() {
        return torConnectionUnsuccessfullHTTP;
    }

    public void setTorConnectionUnsuccessfullHTTP(String torConnectionUnsuccessfullHTTP) {
        this.torConnectionUnsuccessfullHTTP = torConnectionUnsuccessfullHTTP;
    }

    public String getTorConnectionUnsuccessfullHTTPS() {
        return torConnectionUnsuccessfullHTTPS;
    }

    public void setTorConnectionUnsuccessfullHTTPS(String torConnectionUnsuccessfullHTTPS) {
        this.torConnectionUnsuccessfullHTTPS = torConnectionUnsuccessfullHTTPS;
    }
    
    public int getHop_count_http() {
        return hop_count_http;
    }

    public void setHop_count_http(int hop_count_http) {
        this.hop_count_http = hop_count_http;
    }

    public int getHop_count_https() {
        return hop_count_https;
    }

    public void setHop_count_https(int hop_count_https) {
        this.hop_count_https = hop_count_https;
    }

    public TCPDetails(Report r) {
        this.report = r;
    }

    public TCPDetails() {
    }

    public Report getReport() {
        return report;
    }

    public List<String> getIteration_success_tor_http() {
        return iteration_success_tor_http;
    }

    public void setIteration_success_tor_http(List<String> iteration_success_tor_http) {
        this.iteration_success_tor_http = iteration_success_tor_http;
    }

    public List<String> getIteration_success_local_http() {
        return iteration_success_local_http;
    }

    public void setIteration_success_local_http(List<String> iteration_success_local_http) {
        this.iteration_success_local_http = iteration_success_local_http;
    }

    public List<String> getIteration_success_tor_https() {
        return iteration_success_tor_https;
    }

    public void setIteration_success_tor_https(List<String> iteration_success_tor_https) {
        this.iteration_success_tor_https = iteration_success_tor_https;
    }

    public List<String> getIteration_success_local_https() {
        return iteration_success_local_https;
    }

    public void setIteration_success_local_https(List<String> iteration_success_local_https) {
        this.iteration_success_local_https = iteration_success_local_https;
    }

    public List<String> getIs_censored_http_thisIP() {
        return is_censored_http_thisIP;
    }

    public void setIs_censored_http_thisIP(List<String> is_censored_http_thisIP) {
        this.is_censored_http_thisIP = is_censored_http_thisIP;
    }

    public List<String> getIs_censored_https_thisIP() {
        return is_censored_https_thisIP;
    }

    public void setIs_censored_https_thisIP(List<String> is_censored_https_thisIP) {
        this.is_censored_https_thisIP = is_censored_https_thisIP;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public List<String> getIp_addresses_resolved() {
        return ip_addresses_resolved;
    }

    public void setIp_addresses_resolved(List<String> ip_addresses_resolved) {
        this.ip_addresses_resolved = ip_addresses_resolved;
    }

    public String getFromList_String(List<String> list) {
        String s = "";
        s += "<";
        for (String str : list) {
            s += (String.valueOf(str) + ",");
        }
        s += ">";
        return s;
    }

    public String getFromList(List<String> list) {
        String s = "";
        s += "<";
        for (String str : list) {
            s += (str + ",");
        }
        s += ">";
        return s;
    }

    @Override
    public String toString() {
        if (this.report != null) {
            return "TCPDetails{" + "report_id=" + report.getReportID() + ", ip_addresses_resolved=" + ip_addresses_resolved + ", iteration_success_tor_http=" + iteration_success_tor_http + ", iteration_success_local_http=" + iteration_success_local_http + ", iteration_success_tor_https=" + iteration_success_tor_https + ", iteration_success_local_https=" + iteration_success_local_https + ", is_censored_http_thisIP=" + is_censored_http_thisIP + ", is_censored_https_thisIP=" + is_censored_https_thisIP + ", hop_count_http=" + hop_count_http + ", hop_count_https=" + hop_count_https + '}';
        } else {
            return "TCPDetails{" + "report_id=null, " + ", ip_addresses_resolved=" + ip_addresses_resolved + ", iteration_success_tor_http=" + iteration_success_tor_http + ", iteration_success_local_http=" + iteration_success_local_http + ", iteration_success_tor_https=" + iteration_success_tor_https + ", iteration_success_local_https=" + iteration_success_local_https + ", is_censored_http_thisIP=" + is_censored_http_thisIP + ", is_censored_https_thisIP=" + is_censored_https_thisIP + ", hop_count_http=" + hop_count_http + ", hop_count_https=" + hop_count_https + '}';            
        }

    }

}
