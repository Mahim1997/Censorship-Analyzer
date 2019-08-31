package ui.model;

import java.util.ArrayList;
import java.util.List;
import util.commands.Util;

public class DNSDetails {

    private String is_timeout;
    private String is_loopback;
    private String is_multicast;
    private String is_broadcast;
    private String is_private;
    private String is_bogon;
    private String is_unknown_error;
    private String is_reserved;
    private String is_nxDomain;
    private String is_noAnswerPacket;
    private String is_stubby_failed;
    private String is_topExistingButAuthNotExisting;
    private String is_timeout_local_server;
    private String is_non_overlapping_ip_list;
    private String is_is_first_8_to_24_bit_match;
    private String middle_box_hop_count;

    private List<String> ip_address_list_local = new ArrayList<>();
    private List<String> ip_address_list_stubby = new ArrayList<>();

    public String getIs_timeout() {
        return Util.getYesOrNo(is_timeout);
    }

    public void setIs_timeout(String is_timeout) {
        this.is_timeout = is_timeout;
    }

    public String getIs_loopback() {
        return Util.getYesOrNo(is_loopback);
    }

    public void setIs_loopback(String is_loopback) {
        this.is_loopback = is_loopback;
    }

    public String getIs_multicast() {
        return Util.getYesOrNo(is_multicast);
    }

    public void setIs_multicast(String is_multicast) {
        this.is_multicast = is_multicast;
    }

    public String getIs_broadcast() {
        return Util.getYesOrNo(is_broadcast);
    }

    public void setIs_broadcast(String is_broadcast) {
        this.is_broadcast = is_broadcast;
    }

    public String getIs_private() {
        return Util.getYesOrNo(is_private);
    }

    public void setIs_private(String is_private) {
        this.is_private = is_private;
    }

    public String getIs_bogon() {
        return Util.getYesOrNo(is_bogon);
    }

    public void setIs_bogon(String is_bogon) {
        this.is_bogon = is_bogon;
    }

    public String getIs_unknown_error() {
        return Util.getYesOrNo(is_unknown_error);
    }

    public void setIs_unknown_error(String is_unknown_error) {
        this.is_unknown_error = is_unknown_error;
    }

    public String getIs_reserved() {
        return Util.getYesOrNo(is_reserved);
    }

    public void setIs_reserved(String is_reserved) {
        this.is_reserved = is_reserved;
    }

    public String getIs_nxDomain() {
        return Util.getYesOrNo(is_nxDomain);
    }

    public void setIs_nxDomain(String is_nxDomain) {
        this.is_nxDomain = is_nxDomain;
    }

    public String getIs_noAnswerPacket() {
        return Util.getYesOrNo(is_noAnswerPacket);
    }

    public void setIs_noAnswerPacket(String is_noAnswerPacket) {
        this.is_noAnswerPacket = is_noAnswerPacket;
    }

    public String getIs_stubby_failed() {
        return Util.getYesOrNo(is_stubby_failed);
    }

    public void setIs_stubby_failed(String is_stubby_failed) {
        this.is_stubby_failed = is_stubby_failed;
    }

    public String getIs_topExistingButAuthNotExisting() {
        return Util.getYesOrNo(is_topExistingButAuthNotExisting);
    }

    public void setIs_topExistingButAuthNotExisting(String is_topExistingButAuthNotExisting) {
        this.is_topExistingButAuthNotExisting = is_topExistingButAuthNotExisting;
    }

    public String getIs_timeout_local_server() {
        return Util.getYesOrNo(is_timeout_local_server);
    }

    public void setIs_timeout_local_server(String is_timeout_local_server) {
        this.is_timeout_local_server = is_timeout_local_server;
    }

    public String getIs_non_overlapping_ip_list() {
        return Util.getYesOrNo(is_non_overlapping_ip_list);
    }

    public void setIs_non_overlapping_ip_list(String is_non_overlapping_ip_list) {
        this.is_non_overlapping_ip_list = is_non_overlapping_ip_list;
    }

    public String getIs_is_first_8_to_24_bit_match() {
        return Util.getYesOrNo(is_is_first_8_to_24_bit_match);
    }

    public void setIs_is_first_8_to_24_bit_match(String is_is_first_8_to_24_bit_match) {
        this.is_is_first_8_to_24_bit_match = is_is_first_8_to_24_bit_match;
    }

    public String getMiddle_box_hop_count() {
        return middle_box_hop_count;
    }

    public void setMiddle_box_hop_count(String middle_box_hop_count) {
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
    
    public List<String> getMatchedIPs() {
        List<String> ip_matched_list = new ArrayList<>();

        for(int i=0; i<this.ip_address_list_local.size(); i++){
            for(int j=0; j<this.ip_address_list_stubby.size(); j++){
                String ip1 = this.ip_address_list_local.get(i);
                String ip2 = this.ip_address_list_stubby.get(j);
                
                if(ip1.equals(ip2)){
                    if(ip_matched_list.contains(ip1) == false){
                        ip_matched_list.add(ip1);
                    }
                }
                
            }
        }
//        Set<String> ip_matched_set = new HashSet<>();
//
//        ip_address_list_local.forEach(ip_matched_set::add);
//        ip_address_list_stubby.forEach(ip_matched_set::add);
//
//        //convert back to list
//        String[] arr = new String[ip_matched_set.size()];
//        arr = ip_matched_set.toArray(arr);
//        ip_matched_list = Arrays.asList(arr);

        return ip_matched_list;
    }

    
    public DNSDetails() {
    }


    //IP address lists



    @Override
    public String toString() {
        return "DNSDetails{" + "is_timeout=" + is_timeout + ", is_loopback=" + is_loopback + ", is_multicast=" + is_multicast + ", is_broadcast=" + is_broadcast + ", is_private=" + is_private + ", is_bogon=" + is_bogon + ", is_unknown_error=" + is_unknown_error + ", is_reserved=" + is_reserved + ", is_nxDomain=" + is_nxDomain + ", is_topExistingButAuthNotExisting=" + is_topExistingButAuthNotExisting + ", is_timeout_local_server=" + is_timeout_local_server + ", is_non_overlapping_ip_list=" + is_non_overlapping_ip_list + ", is_is_first_8_to_24_bit_match=" + is_is_first_8_to_24_bit_match + ", middle_box_hop_count=" + middle_box_hop_count + ", ip_address_list_local=" + ip_address_list_local + ", ip_address_list_stubby=" + ip_address_list_stubby + '}';
    }

    public String getDNSDetails() {
        String s = "";

        s += (this.is_timeout + "$" + this.is_loopback + "$" + this.is_multicast 
                + "$" + this.is_broadcast + "$" + this.is_private
                + "$" + this.is_bogon + "$" + this.is_unknown_error 
                + "$" + this.is_nxDomain + "$" + this.is_noAnswerPacket
                + "$" + this.is_stubby_failed + "$" + this.is_topExistingButAuthNotExisting
                + "$" + this.is_timeout_local_server + "$" + this.is_non_overlapping_ip_list
                + "$" + this.is_is_first_8_to_24_bit_match
                + "$" + this.middle_box_hop_count
                + "$" + this.getListOfIP_Local()
                + "$" + this.getListOfIP_Stubby());

        return s;
    }

    private String getListOfIP_Local() {
        String s = "";
        for (String ip : this.ip_address_list_local) {
            s += (ip + ",");
        }
        return s;
    }

    private String getListOfIP_Stubby() {
        String s = "";
        for (String ip : this.ip_address_list_stubby) {
            s += (ip + ",");
        }
        return s;
    }

}


/*
package ui.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
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
    private int is_topExistingButAuthNotExisting;
    private int is_timeout_local_server;
    private int is_non_overlapping_ip_list;
    private int is_is_first_8_to_24_bit_match;
    private int middle_box_hop_count;

    private List<String> ip_address_list_local = new ArrayList<>();
    private List<String> ip_address_list_stubby = new ArrayList<>();
    
    public List<String> getMatchedIPs() {
        List<String> ip_matched_list = new ArrayList<>();

        Set<String> ip_matched_set = new HashSet<>();

        ip_address_list_local.forEach(ip_matched_set::add);
        ip_address_list_stubby.forEach(ip_matched_set::add);

        //convert back to list
        String[] arr = new String[ip_matched_set.size()];
        arr = ip_matched_set.toArray(arr);
        ip_matched_list = Arrays.asList(arr);

        return ip_matched_list;
    }

    
    public DNSDetails() {
    }


    //IP address lists



    @Override
    public String toString() {
        return "DNSDetails{" + "is_timeout=" + is_timeout + ", is_loopback=" + is_loopback + ", is_multicast=" + is_multicast + ", is_broadcast=" + is_broadcast + ", is_private=" + is_private + ", is_bogon=" + is_bogon + ", is_unknown_error=" + is_unknown_error + ", is_reserved=" + is_reserved + ", is_nxDomain=" + is_nxDomain + ", is_topExistingButAuthNotExisting=" + is_topExistingButAuthNotExisting + ", is_timeout_local_server=" + is_timeout_local_server + ", is_non_overlapping_ip_list=" + is_non_overlapping_ip_list + ", is_is_first_8_to_24_bit_match=" + is_is_first_8_to_24_bit_match + ", middle_box_hop_count=" + middle_box_hop_count + ", ip_address_list_local=" + ip_address_list_local + ", ip_address_list_stubby=" + ip_address_list_stubby + '}';
    }

    public String getDNSDetails() {
        String s = "";

        s += (this.is_timeout + "$" + this.is_loopback + "$" + this.is_multicast + "$" + this.is_broadcast + "$" + this.is_private
                + "$" + this.is_bogon + "$" + this.is_unknown_error + "$" + this.is_nxDomain + "$" + this.is_noAnswerPacket
                + "$" + this.is_stubby_failed + "$" + this.is_topExistingButAuthNotExisting
                + "$" + this.is_timeout_local_server + "$" + this.is_non_overlapping_ip_list
                + "$" + this.is_is_first_8_to_24_bit_match
                + "$" + this.middle_box_hop_count
                + "$" + this.getListOfIP_Local()
                + "$" + this.getListOfIP_Stubby());

        return s;
    }

    private String getListOfIP_Local() {
        String s = "";
        for (String ip : this.ip_address_list_local) {
            s += (ip + ",");
        }
        return s;
    }

    private String getListOfIP_Stubby() {
        String s = "";
        for (String ip : this.ip_address_list_stubby) {
            s += (ip + ",");
        }
        return s;
    }

}

*/

/*
package ui.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import util.commands.Util;

public class DNSDetails {

    private String is_timeout;
    private String is_loopback;
    private String is_multicast;
    private String is_broadcast;
    private String is_private;
    private String is_bogon;
    private String is_unknown_error;
    private String is_reserved;
    private String is_nxDomain;
    private String is_noAnswerPacket;
    private String is_stubby_failed;
    private String is_topExistingButAuthNotExisting;
    private String is_timeout_local_server;
    private String is_non_overlapping_ip_list;
    private String is_is_first_8_to_24_bit_match;
    private String middle_box_hop_count;

    private List<String> ip_address_list_local = new ArrayList<>();
    private List<String> ip_address_list_stubby = new ArrayList<>();

    public String getIs_timeout() {
        return Util.getYesOrNo(is_timeout);
    }

    public void setIs_timeout(String is_timeout) {
        this.is_timeout = is_timeout;
    }

    public String getIs_loopback() {
        return is_loopback;
    }

    public void setIs_loopback(String is_loopback) {
        this.is_loopback = is_loopback;
    }

    public String getIs_multicast() {
        return is_multicast;
    }

    public void setIs_multicast(String is_multicast) {
        this.is_multicast = is_multicast;
    }

    public String getIs_broadcast() {
        return is_broadcast;
    }

    public void setIs_broadcast(String is_broadcast) {
        this.is_broadcast = is_broadcast;
    }

    public String getIs_private() {
        return is_private;
    }

    public void setIs_private(String is_private) {
        this.is_private = is_private;
    }

    public String getIs_bogon() {
        return is_bogon;
    }

    public void setIs_bogon(String is_bogon) {
        this.is_bogon = is_bogon;
    }

    public String getIs_unknown_error() {
        return is_unknown_error;
    }

    public void setIs_unknown_error(String is_unknown_error) {
        this.is_unknown_error = is_unknown_error;
    }

    public String getIs_reserved() {
        return is_reserved;
    }

    public void setIs_reserved(String is_reserved) {
        this.is_reserved = is_reserved;
    }

    public String getIs_nxDomain() {
        return is_nxDomain;
    }

    public void setIs_nxDomain(String is_nxDomain) {
        this.is_nxDomain = is_nxDomain;
    }

    public String getIs_noAnswerPacket() {
        return is_noAnswerPacket;
    }

    public void setIs_noAnswerPacket(String is_noAnswerPacket) {
        this.is_noAnswerPacket = is_noAnswerPacket;
    }

    public String getIs_stubby_failed() {
        return is_stubby_failed;
    }

    public void setIs_stubby_failed(String is_stubby_failed) {
        this.is_stubby_failed = is_stubby_failed;
    }

    public String getIs_topExistingButAuthNotExisting() {
        return is_topExistingButAuthNotExisting;
    }

    public void setIs_topExistingButAuthNotExisting(String is_topExistingButAuthNotExisting) {
        this.is_topExistingButAuthNotExisting = is_topExistingButAuthNotExisting;
    }

    public String getIs_timeout_local_server() {
        return is_timeout_local_server;
    }

    public void setIs_timeout_local_server(String is_timeout_local_server) {
        this.is_timeout_local_server = is_timeout_local_server;
    }

    public String getIs_non_overlapping_ip_list() {
        return is_non_overlapping_ip_list;
    }

    public void setIs_non_overlapping_ip_list(String is_non_overlapping_ip_list) {
        this.is_non_overlapping_ip_list = is_non_overlapping_ip_list;
    }

    public String getIs_is_first_8_to_24_bit_match() {
        return is_is_first_8_to_24_bit_match;
    }

    public void setIs_is_first_8_to_24_bit_match(String is_is_first_8_to_24_bit_match) {
        this.is_is_first_8_to_24_bit_match = is_is_first_8_to_24_bit_match;
    }

    public String getMiddle_box_hop_count() {
        return middle_box_hop_count;
    }

    public void setMiddle_box_hop_count(String middle_box_hop_count) {
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
    
    public List<String> getMatchedIPs() {
        List<String> ip_matched_list = new ArrayList<>();

        for(int i=0; i<this.ip_address_list_local.size(); i++){
            for(int j=0; j<this.ip_address_list_stubby.size(); j++){
                String ip1 = this.ip_address_list_local.get(i);
                String ip2 = this.ip_address_list_stubby.get(j);
                
                if(ip1.equals(ip2)){
                    if(ip_matched_list.contains(ip1) == false){
                        ip_matched_list.add(ip1);
                    }
                }
                
            }
        }
//        Set<String> ip_matched_set = new HashSet<>();
//
//        ip_address_list_local.forEach(ip_matched_set::add);
//        ip_address_list_stubby.forEach(ip_matched_set::add);
//
//        //convert back to list
//        String[] arr = new String[ip_matched_set.size()];
//        arr = ip_matched_set.toArray(arr);
//        ip_matched_list = Arrays.asList(arr);

        return ip_matched_list;
    }

    
    public DNSDetails() {
    }


    //IP address lists



    @Override
    public String toString() {
        return "DNSDetails{" + "is_timeout=" + is_timeout + ", is_loopback=" + is_loopback + ", is_multicast=" + is_multicast + ", is_broadcast=" + is_broadcast + ", is_private=" + is_private + ", is_bogon=" + is_bogon + ", is_unknown_error=" + is_unknown_error + ", is_reserved=" + is_reserved + ", is_nxDomain=" + is_nxDomain + ", is_topExistingButAuthNotExisting=" + is_topExistingButAuthNotExisting + ", is_timeout_local_server=" + is_timeout_local_server + ", is_non_overlapping_ip_list=" + is_non_overlapping_ip_list + ", is_is_first_8_to_24_bit_match=" + is_is_first_8_to_24_bit_match + ", middle_box_hop_count=" + middle_box_hop_count + ", ip_address_list_local=" + ip_address_list_local + ", ip_address_list_stubby=" + ip_address_list_stubby + '}';
    }

    public String getDNSDetails() {
        String s = "";

        s += (this.is_timeout + "$" + this.is_loopback + "$" + this.is_multicast + "$" + this.is_broadcast + "$" + this.is_private
                + "$" + this.is_bogon + "$" + this.is_unknown_error + "$" + this.is_nxDomain + "$" + this.is_noAnswerPacket
                + "$" + this.is_stubby_failed + "$" + this.is_topExistingButAuthNotExisting
                + "$" + this.is_timeout_local_server + "$" + this.is_non_overlapping_ip_list
                + "$" + this.is_is_first_8_to_24_bit_match
                + "$" + this.middle_box_hop_count
                + "$" + this.getListOfIP_Local()
                + "$" + this.getListOfIP_Stubby());

        return s;
    }

    private String getListOfIP_Local() {
        String s = "";
        for (String ip : this.ip_address_list_local) {
            s += (ip + ",");
        }
        return s;
    }

    private String getListOfIP_Stubby() {
        String s = "";
        for (String ip : this.ip_address_list_stubby) {
            s += (ip + ",");
        }
        return s;
    }

}


/*
package ui.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
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
    private int is_topExistingButAuthNotExisting;
    private int is_timeout_local_server;
    private int is_non_overlapping_ip_list;
    private int is_is_first_8_to_24_bit_match;
    private int middle_box_hop_count;

    private List<String> ip_address_list_local = new ArrayList<>();
    private List<String> ip_address_list_stubby = new ArrayList<>();
    
    public List<String> getMatchedIPs() {
        List<String> ip_matched_list = new ArrayList<>();

        Set<String> ip_matched_set = new HashSet<>();

        ip_address_list_local.forEach(ip_matched_set::add);
        ip_address_list_stubby.forEach(ip_matched_set::add);

        //convert back to list
        String[] arr = new String[ip_matched_set.size()];
        arr = ip_matched_set.toArray(arr);
        ip_matched_list = Arrays.asList(arr);

        return ip_matched_list;
    }

    
    public DNSDetails() {
    }


    //IP address lists



    @Override
    public String toString() {
        return "DNSDetails{" + "is_timeout=" + is_timeout + ", is_loopback=" + is_loopback + ", is_multicast=" + is_multicast + ", is_broadcast=" + is_broadcast + ", is_private=" + is_private + ", is_bogon=" + is_bogon + ", is_unknown_error=" + is_unknown_error + ", is_reserved=" + is_reserved + ", is_nxDomain=" + is_nxDomain + ", is_topExistingButAuthNotExisting=" + is_topExistingButAuthNotExisting + ", is_timeout_local_server=" + is_timeout_local_server + ", is_non_overlapping_ip_list=" + is_non_overlapping_ip_list + ", is_is_first_8_to_24_bit_match=" + is_is_first_8_to_24_bit_match + ", middle_box_hop_count=" + middle_box_hop_count + ", ip_address_list_local=" + ip_address_list_local + ", ip_address_list_stubby=" + ip_address_list_stubby + '}';
    }

    public String getDNSDetails() {
        String s = "";

        s += (this.is_timeout + "$" + this.is_loopback + "$" + this.is_multicast + "$" + this.is_broadcast + "$" + this.is_private
                + "$" + this.is_bogon + "$" + this.is_unknown_error + "$" + this.is_nxDomain + "$" + this.is_noAnswerPacket
                + "$" + this.is_stubby_failed + "$" + this.is_topExistingButAuthNotExisting
                + "$" + this.is_timeout_local_server + "$" + this.is_non_overlapping_ip_list
                + "$" + this.is_is_first_8_to_24_bit_match
                + "$" + this.middle_box_hop_count
                + "$" + this.getListOfIP_Local()
                + "$" + this.getListOfIP_Stubby());

        return s;
    }

    private String getListOfIP_Local() {
        String s = "";
        for (String ip : this.ip_address_list_local) {
            s += (ip + ",");
        }
        return s;
    }

    private String getListOfIP_Stubby() {
        String s = "";
        for (String ip : this.ip_address_list_stubby) {
            s += (ip + ",");
        }
        return s;
    }

}

*/