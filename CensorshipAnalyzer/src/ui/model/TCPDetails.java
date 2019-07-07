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
    private List<Integer> iteration_success_tor;
    private List<Integer> iteration_success_local;
    private List<String> is_censored;

    public TCPDetails(Report r) {
        this.report = r;
    }

    public TCPDetails() {
    }

    public Report getReport() {
        return report;
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

    public List<Integer> getIteration_success_tor() {
        return iteration_success_tor;
    }

    public void setIteration_success_tor(List<Integer> iteration_success_tor) {
        this.iteration_success_tor = iteration_success_tor;
    }

    public List<Integer> getIteration_success_local() {
        return iteration_success_local;
    }

    public void setIteration_success_local(List<Integer> iteration_success_local) {
        this.iteration_success_local = iteration_success_local;
    }

    public List<String> getIs_censored() {
        return is_censored;
    }

    public void setIs_censored(List<String> is_censored) {
        this.is_censored = is_censored;
    }
    public String getFromList_Integer(List<Integer> list) {
        String s = "";
        s += "<";
        for (int str : list) {
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
            return "TCPDetails{" + "report_id=" + String.valueOf(report.getReportID()) + ", ip_addresses_resolved=" + getFromList(ip_addresses_resolved) + ", iteration_success_tor=" + getFromList_Integer(iteration_success_tor) + ", iteration_success_local=" + getFromList_Integer(iteration_success_local) + ", is_censored=" + getFromList(is_censored) + '}';
        } else {
            return "TCPDetails{" + "report=null, " + ", ip_addresses_resolved=" + getFromList(ip_addresses_resolved) + ", iteration_success_tor=" + getFromList_Integer(iteration_success_tor) + ", iteration_success_local=" + getFromList_Integer(iteration_success_local) + ", is_censored=" + getFromList(is_censored) + '}';
        }
    }

}
