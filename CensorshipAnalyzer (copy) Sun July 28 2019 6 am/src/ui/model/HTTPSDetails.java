/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.model;

import helper.HelperFunctions;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mahim
 */
public class HTTPSDetails {

    public int reportIDDebug;

    public int getReportIDDebug() {
        return reportIDDebug;
    }

    public void setReportIDDebug(int reportIDDebug) {
        this.reportIDDebug = reportIDDebug;
    }

    public HTTPSDetails() {
        System.out.println("-->>>CLEARING HTTPS Details ... ");
        this.ip_address.clear();
        this.https_response_code_local.clear();
        this.https_response_code_tor.clear();
        this.redirection_history_local.clear();
        this.redirection_history_tor.clear();
    }

    private List<String> ip_address = new ArrayList<>();
    private List<String> https_response_code_local = new ArrayList<>();
    private List<String> https_response_code_tor = new ArrayList<>();
    private String is_other_error;
    private String message_HTTPS;
    private String is_fin_bit_set;
    private String is_rst_bit_set;
    private List<String> redirection_history_local = new ArrayList<>();
    private List<String> redirection_history_tor = new ArrayList<>();
    private String threshold_comparison;
    private String is_different_wrt_threshold;
    private String content_difference; //in percentage
    private String is_max_redirection_reached;
    private String max_redirection_count;
    private String middle_box_hop_count;
    private String is_tls_handshake_failed;

    public List<String> getIp_address() {
        return ip_address;
    }

    public void setIp_address(List<String> ip_address) {
        this.ip_address = ip_address;
    }

    public List<String> getHttps_response_code_local() {
        return https_response_code_local;
    }

    public void setHttps_response_code_local(List<String> https_response_code_local) {
        this.https_response_code_local = https_response_code_local;
    }

    public List<String> getHttps_response_code_tor() {
        return https_response_code_tor;
    }

    public void setHttps_response_code_tor(List<String> https_response_code_tor) {
        this.https_response_code_tor = https_response_code_tor;
    }

    public String getIs_other_error() {
        return is_other_error;
    }

    public void setIs_other_error(String is_other_error) {
        this.is_other_error = is_other_error;
    }

    public String getMessage_HTTPS() {
        return message_HTTPS.toUpperCase();
    }

    public void setMessage_HTTPS(String message_HTTPS) {
        this.message_HTTPS = message_HTTPS;
    }

    public String getIs_fin_bit_set() {
        return is_fin_bit_set;
    }

    public void setIs_fin_bit_set(String is_fin_bit_set) {
        this.is_fin_bit_set = is_fin_bit_set;
    }

    public String getIs_rst_bit_set() {
        return is_rst_bit_set;
    }

    public void setIs_rst_bit_set(String is_rst_bit_set) {
        this.is_rst_bit_set = is_rst_bit_set;
    }

    public List<String> getRedirection_history_local() {
        return redirection_history_local;
    }

    public void setRedirection_history_local(List<String> redirection_history_local) {
        this.redirection_history_local = redirection_history_local;
    }

    public List<String> getRedirection_history_tor() {
        return redirection_history_tor;
    }

    public void setRedirection_history_tor(List<String> redirection_history_tor) {
        this.redirection_history_tor = redirection_history_tor;
    }

    public String getThreshold_comparison() {
        return threshold_comparison;
    }

    public void setThreshold_comparison(String threshold_comparison) {
        this.threshold_comparison = threshold_comparison;
    }

    public String getIs_different_wrt_threshold() {
        return is_different_wrt_threshold;
    }

    public void setIs_different_wrt_threshold(String is_different_wrt_threshold) {
        this.is_different_wrt_threshold = is_different_wrt_threshold;
    }

    public String getContent_difference() {
        return content_difference;
    }

    public void setContent_difference(String content_difference) {
        this.content_difference = content_difference;
    }

    public String getIs_max_redirection_reached() {
        return is_max_redirection_reached;
    }

    public void setIs_max_redirection_reached(String is_max_redirection_reached) {
        this.is_max_redirection_reached = is_max_redirection_reached;
    }

    public String getMax_redirection_count() {
        return max_redirection_count;
    }

    public void setMax_redirection_count(String max_redirection_count) {
        this.max_redirection_count = max_redirection_count;
    }

    public String getMiddle_box_hop_count() {
        return middle_box_hop_count;
    }

    public void setMiddle_box_hop_count(String middle_box_hop_count) {
        this.middle_box_hop_count = middle_box_hop_count;
    }

    public String getIs_tls_handshake_failed() {
        return is_tls_handshake_failed;
    }

    public void setIs_tls_handshake_failed(String is_tls_handshake_failed) {
        this.is_tls_handshake_failed = is_tls_handshake_failed;
    }

    @Override
    public String toString() {
        return "HTTPSDetails{" + "reportIDDebug=" + reportIDDebug + ", ip_address=" + ip_address + ", https_response_code_local=" + https_response_code_local + ", https_response_code_tor=" + https_response_code_tor + ", is_other_error=" + is_other_error + ", message_HTTPS=" + message_HTTPS + ", is_fin_bit_set=" + is_fin_bit_set + ", is_rst_bit_set=" + is_rst_bit_set + ", redirection_history_local=" + redirection_history_local + ", redirection_history_tor=" + redirection_history_tor + ", threshold_comparison=" + threshold_comparison + ", is_different_wrt_threshold=" + is_different_wrt_threshold + ", content_difference=" + content_difference + ", is_max_redirection_reached=" + is_max_redirection_reached + ", max_redirection_count=" + max_redirection_count + ", middle_box_hop_count=" + middle_box_hop_count + ", is_tls_handshake_failed=" + is_tls_handshake_failed + '}';
    }

    public void pad_fields_for_https(int MAX_REDIRECT) {

        //pad the array
        String padder = "";
        this.ip_address = HelperFunctions.padListWith(padder, MAX_REDIRECT, ip_address);
        this.https_response_code_local = HelperFunctions.padListWith(padder, MAX_REDIRECT, https_response_code_local);
        this.https_response_code_tor = HelperFunctions.padListWith(padder, MAX_REDIRECT, https_response_code_tor);
        this.redirection_history_local = HelperFunctions.padListWith(padder, MAX_REDIRECT, redirection_history_local);
        this.redirection_history_tor = HelperFunctions.padListWith(padder, MAX_REDIRECT, redirection_history_tor);

    }

    private String getHTTPSResponseCodeLocal() {
        String s = "";
        for (String ip : this.https_response_code_local) {
            s += (ip + ",");
        }
        return s;
    }

    private String getHTTPSResponseCodeTOR() {
        String s = "";
        for (String ip : this.https_response_code_tor) {
            s += (ip + ",");
        }
        return s;
    }

    private String getRedirectionHistoryLocal() {
        String s = "";
        for (String ip : this.redirection_history_local) {
            s += (ip + ",");
        }
        return s;
    }

    private String getRedirectionHistoryTOR() {
        String s = "";
        for (String ip : this.redirection_history_tor) {
            s += (ip + ",");
        }
        return s;
    }

    private String getIPAddresses() {
        String s = "";
        for (String ip : this.ip_address) {
            s += (ip + ",");
        }
        return s;
    }

    public String getDescription() {
        String s = "";
        s += (getIPAddresses() + "$");
        s += (getHTTPSResponseCodeLocal() + "$");
        s += (getHTTPSResponseCodeTOR() + "$");
        s += (this.is_other_error + "$");
        s += (this.message_HTTPS + "$");
        s += (this.is_fin_bit_set + "$");
        s += (this.is_rst_bit_set + "$");
        s += (getRedirectionHistoryLocal() + "$");
        s += (getRedirectionHistoryTOR() + "$");
        s += (this.threshold_comparison + "$");
        s += (this.is_different_wrt_threshold + "$");
        s += (this.content_difference + "$");
        s += (this.is_max_redirection_reached + "$");
        s += (this.max_redirection_count + "$");
        s += (this.middle_box_hop_count + "$");
        s += (this.middle_box_hop_count + "$");
        s += (this.is_tls_handshake_failed + "$");

        return s;
    }

}
