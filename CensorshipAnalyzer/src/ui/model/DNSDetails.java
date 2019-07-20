package ui.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DNSDetails {

    private int is_timeout;
    private int is_loopback;
    private int is_multicast;
    private int is_broadcast;
    private int is_private;
    private int is_bogon;
    private int is_unknown_error;
    private int is_reserved;
    private int is_nxDomain;
    private int is_noAnswerPacket;
    private int is_stubby_failed;

    public DNSDetails() {
    }

    public int getIs_noAnswerPacket() {
        return is_noAnswerPacket;
    }

    public void setIs_noAnswerPacket(int is_noAnswerPacket) {
        this.is_noAnswerPacket = is_noAnswerPacket;
    }

    public int getIs_stubby_failed() {
        return is_stubby_failed;
    }

    public void setIs_stubby_failed(int is_stubby_failed) {
        this.is_stubby_failed = is_stubby_failed;
    }
    private int is_topExistingButAuthNotExisting;
    private int is_timeout_local_server;
    private int is_non_overlapping_ip_list;
    private int is_is_first_8_to_24_bit_match;
    private int middle_box_hop_count;

    //IP address lists
    private List<String> ip_address_list_local = new ArrayList<>();
    private List<String> ip_address_list_stubby = new ArrayList<>();

    public List<String> getMatchedIPs(){
        List<String> ip_matched_list = new ArrayList<>();
        
        Set<String> ip_matched_set = new HashSet<>();
        
        ip_address_list_local.forEach(ip_matched_set::add);
        ip_address_list_stubby.forEach(ip_matched_set::add);
        
        //convert back to list
        String[]arr = new String[ip_matched_set.size()];
        arr = ip_matched_set.toArray(arr);
        ip_matched_list = Arrays.asList(arr);
        
        return ip_matched_list;
    }
    
    public int getIs_timeout() {
        return is_timeout;
    }

    public void setIs_timeout(int is_timeout) {
        this.is_timeout = is_timeout;
    }

    public int getIs_loopback() {
        return is_loopback;
    }

    public void setIs_loopback(int is_loopback) {
        this.is_loopback = is_loopback;
    }

    public int getIs_multicast() {
        return is_multicast;
    }

    public void setIs_multicast(int is_multicast) {
        this.is_multicast = is_multicast;
    }

    public int getIs_broadcast() {
        return is_broadcast;
    }

    public void setIs_broadcast(int is_broadcast) {
        this.is_broadcast = is_broadcast;
    }

    public int getIs_private() {
        return is_private;
    }

    public void setIs_private(int is_private) {
        this.is_private = is_private;
    }

    public int getIs_bogon() {
        return is_bogon;
    }

    public void setIs_bogon(int is_bogon) {
        this.is_bogon = is_bogon;
    }

    public int getIs_unknown_error() {
        return is_unknown_error;
    }

    public void setIs_unknown_error(int is_unknown_error) {
        this.is_unknown_error = is_unknown_error;
    }

    public int getIs_reserved() {
        return is_reserved;
    }

    public void setIs_reserved(int is_reserved) {
        this.is_reserved = is_reserved;
    }

    public int getIs_nxDomain() {
        return is_nxDomain;
    }

    public void setIs_nxDomain(int is_nxDomain) {
        this.is_nxDomain = is_nxDomain;
    }

    public int getIs_topExistingButAuthNotExisting() {
        return is_topExistingButAuthNotExisting;
    }

    public void setIs_topExistingButAuthNotExisting(int is_topExistingButAuthNotExisting) {
        this.is_topExistingButAuthNotExisting = is_topExistingButAuthNotExisting;
    }

    public int getIs_timeout_local_server() {
        return is_timeout_local_server;
    }

    public void setIs_timeout_local_server(int is_timeout_local_server) {
        this.is_timeout_local_server = is_timeout_local_server;
    }

    public int getIs_non_overlapping_ip_list() {
        return is_non_overlapping_ip_list;
    }

    public void setIs_non_overlapping_ip_list(int is_non_overlapping_ip_list) {
        this.is_non_overlapping_ip_list = is_non_overlapping_ip_list;
    }

    public int getIs_is_first_8_to_24_bit_match() {
        return is_is_first_8_to_24_bit_match;
    }

    public void setIs_is_first_8_to_24_bit_match(int is_is_first_8_to_24_bit_match) {
        this.is_is_first_8_to_24_bit_match = is_is_first_8_to_24_bit_match;
    }

    public int getMiddle_box_hop_count() {
        return middle_box_hop_count;
    }

    public void setMiddle_box_hop_count(int middle_box_hop_count) {
        this.middle_box_hop_count = middle_box_hop_count;
    }

    public List<String> getIp_address_list_local() {
        return ip_address_list_local;
    }

    public void setIp_address_list_local(List<String> ip_address_list_local) {
        this.ip_address_list_local = ip_address_list_local;
    }

    public List<String> getIp_address_list_stubby() {
        return ip_address_list_stubby;
    }

    public void setIp_address_list_stubby(List<String> ip_address_list_stubby) {
        this.ip_address_list_stubby = ip_address_list_stubby;
    }

    @Override
    public String toString() {
        return "DNSDetails{" + "is_timeout=" + is_timeout + ", is_loopback=" + is_loopback + ", is_multicast=" + is_multicast + ", is_broadcast=" + is_broadcast + ", is_private=" + is_private + ", is_bogon=" + is_bogon + ", is_unknown_error=" + is_unknown_error + ", is_reserved=" + is_reserved + ", is_nxDomain=" + is_nxDomain + ", is_topExistingButAuthNotExisting=" + is_topExistingButAuthNotExisting + ", is_timeout_local_server=" + is_timeout_local_server + ", is_non_overlapping_ip_list=" + is_non_overlapping_ip_list + ", is_is_first_8_to_24_bit_match=" + is_is_first_8_to_24_bit_match + ", middle_box_hop_count=" + middle_box_hop_count + ", ip_address_list_local=" + ip_address_list_local + ", ip_address_list_stubby=" + ip_address_list_stubby + '}';
    }

}
