package socketTestingPY;

import java.util.ArrayList;
import java.util.List;

public class DNSDetails {

    private Report report;

    private List<String> listIpLocalDNSServer;
    private List<String> listIpStubby;
    private List<String> matchedIPs;
    
    private int errorCode = 0;
    
    private boolean isBogonIP = false;
    private boolean isTimeout = false;
    private boolean isPrivateIP = false;
    private boolean isInvalidDomain = false;
    private boolean isNoNameServers = false;
    private boolean localServerRRCodeSet = false;
    private boolean unknownError = false;
    
    
    private String errorMsgCensorship;
    
    public DNSDetails(Report r) {
        this.report = r;
    }

    public DNSDetails() {
        System.out.println("Initializing DNSDetails ... ");
    }
    
    public void setFlags(){
        //TO DO
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public List<String> getListIpLocalDNSServer() {
        return listIpLocalDNSServer;
    }

    public void setListIpLocalDNSServer(List<String> listIpLocalDNSServer) {
        this.listIpLocalDNSServer = listIpLocalDNSServer;
    }

    public List<String> getListIpStubby() {
        return listIpStubby;
    }

    public void setListIpStubby(List<String> listIpStubby) {
        this.listIpStubby = listIpStubby;
    }

    public List<String> getMatchedIPs() {
        return matchedIPs;
    }

    public void setMatchedIPs(List<String> matchedIPs) {
        this.matchedIPs = matchedIPs;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isIsBogonIP() {
        return isBogonIP;
    }

    public void setIsBogonIP(boolean isBogonIP) {
        this.isBogonIP = isBogonIP;
    }

    public boolean isIsTimeout() {
        return isTimeout;
    }

    public void setIsTimeout(boolean isTimeout) {
        this.isTimeout = isTimeout;
    }


    public String getFromList(List<String> list){
        String s = "";
        s += "<";
        for(String str: list){
            s += (str + ",");
        }
        s += ">";
        return s;
    }
    
    public void formMatchedIPList(){
        //Can it be optimized using normal set functions ??
        List<String> listMatchedIPs = new ArrayList<>();
        for(int i=0; i<this.listIpLocalDNSServer.size(); i++){
            String ipLDS = this.listIpLocalDNSServer.get(i);
            if(this.listIpStubby.contains(ipLDS) == true){
                listMatchedIPs.add(ipLDS);  //Add if there is a match
            }
        }
        this.matchedIPs = listMatchedIPs;
    }
    
    @Override
    public String toString() {
        return "DNSDetails{reportID" + report.getReportID() + ", listIpLocalDNSServer=" + getFromList(listIpLocalDNSServer) + ", listIpStubby=" + getFromList(listIpStubby) + ", matchedIPs=" + getFromList(matchedIPs) + ", errorCode=" + errorCode + ", isBogonIP=" + isBogonIP + ", isTimeout=" + isTimeout + ", isPrivateIP=" + isPrivateIP + ", isInvalidDomain=" + isInvalidDomain + ", isNoNameServers=" + isNoNameServers + ", localServerRRCodeSet=" + localServerRRCodeSet + ", unknownError=" + unknownError + ", errorMsgCensorship=" + errorMsgCensorship + '}';
    }

    public boolean isIsPrivateIP() {
        return isPrivateIP;
    }

    public void setIsPrivateIP(boolean isPrivateIP) {
        this.isPrivateIP = isPrivateIP;
    }

    public boolean isIsInvalidDomain() {
        return isInvalidDomain;
    }

    public void setIsInvalidDomain(boolean isInvalidDomain) {
        this.isInvalidDomain = isInvalidDomain;
    }

    public boolean isIsNoNameServers() {
        return isNoNameServers;
    }

    public void setIsNoNameServers(boolean isNoNameServers) {
        this.isNoNameServers = isNoNameServers;
    }

    public boolean isLocalServerRRCodeSet() {
        return localServerRRCodeSet;
    }

    public void setLocalServerRRCodeSet(boolean localServerRRCodeSet) {
        this.localServerRRCodeSet = localServerRRCodeSet;
    }

    public boolean isUnknownError() {
        return unknownError;
    }

    public void setUnknownError(boolean unknownError) {
        this.unknownError = unknownError;
    }

    public String getErrorMsgCensorship() {
        return errorMsgCensorship;
    }

    public void setErrorMsgCensorship(String errorMsgCensorship) {
        this.errorMsgCensorship = errorMsgCensorship;
    }
    
    
}
