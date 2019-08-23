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
public class HTTPDetails {

    
    
    public int reportIDDebug;

    public int getReportIDDebug() {
        return reportIDDebug;
    }

    public void setReportIDDebug(int reportIDDebug) {
        this.reportIDDebug = reportIDDebug;
    }
    
    
    public HTTPDetails() {
        System.out.println("-->>>CLEARING HTTP Details ... ");
        this.ip_address.clear();
        this.http_response_code_local.clear();
        this.http_response_code_tor.clear();
        this.redirection_history_local.clear();
        this.redirection_history_tor.clear();
    }

    private List<String> ip_address = new ArrayList<>();
    private List<String> http_response_code_local = new ArrayList<>();
    private List<String> http_response_code_tor = new ArrayList<>();
    private String is_other_error;
    private String message_HTTP;
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

    public List<String> getIp_address() {
        return ip_address;
    }

    public void setIp_address(List<String> ip_address) {
        System.out.println("-->>>INSIDE HTTPDetails.setIP_address() ... setting ");
        HelperFunctions.printListNoLine(ip_address);
        this.ip_address = ip_address;
    }

    public List<String> getHttp_response_code_local() {
        return http_response_code_local;
    }

    public void setHttp_response_code_local(List<String> http_response_code_local) {
        this.http_response_code_local = http_response_code_local;
    }

    public List<String> getHttp_response_code_tor() {
        return http_response_code_tor;
    }

    public void setHttp_response_code_tor(List<String> http_response_code_tor) {
        this.http_response_code_tor = http_response_code_tor;
    }

    public String getIs_other_error() {
        return is_other_error;
    }

    public void setIs_other_error(String is_other_error) {
        this.is_other_error = is_other_error;
    }

    public String getMessage_HTTP() {
        return message_HTTP.toUpperCase();
    }

    public void setMessage_HTTP(String message_HTTP) {
        this.message_HTTP = message_HTTP;
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

    @Override
    public String toString() {
        return "HTTPDetails{" + "reportIDDebug=" + reportIDDebug + ", ip_address=" + ip_address + ", http_response_code_local=" + http_response_code_local + ", http_response_code_tor=" + http_response_code_tor + ", is_other_error=" + is_other_error + ", message_HTTP=" + message_HTTP + ", is_fin_bit_set=" + is_fin_bit_set + ", is_rst_bit_set=" + is_rst_bit_set + ", redirection_history_local=" + redirection_history_local + ", redirection_history_tor=" + redirection_history_tor + ", threshold_comparison=" + threshold_comparison + ", is_different_wrt_threshold=" + is_different_wrt_threshold + ", content_difference=" + content_difference + ", is_max_redirection_reached=" + is_max_redirection_reached + ", max_redirection_count=" + max_redirection_count + ", middle_box_hop_count=" + middle_box_hop_count + '}';
    }



    public void pad_fields_for_http(int MAX_REDIRECT) {
        //pad the array
        String padder = "";
        this.ip_address = HelperFunctions.padListWith(padder, MAX_REDIRECT, ip_address);
        this.http_response_code_local = HelperFunctions.padListWith(padder, MAX_REDIRECT, http_response_code_local);
        this.http_response_code_tor = HelperFunctions.padListWith(padder, MAX_REDIRECT, http_response_code_tor);
        this.redirection_history_local = HelperFunctions.padListWith(padder, MAX_REDIRECT, redirection_history_local);
        this.redirection_history_tor = HelperFunctions.padListWith(padder, MAX_REDIRECT, redirection_history_tor);
    }

}
