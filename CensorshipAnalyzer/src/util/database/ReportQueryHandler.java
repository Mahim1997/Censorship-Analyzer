package util.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ui.model.Network;
import ui.model.Report;
import ui.model.Report_Network_User_Join;
import ui.model.TCPDetails;
import ui.model.User;
import static util.database.DBHandler.conn;

public class ReportQueryHandler {

    static Statement stmt;
    static ResultSet rs;

    public static int getCurrentLastReportID() {
        String sql = "SELECT count(*) FROM Report";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt(1);    //Result Set Columns counted from index = 1
            }

        } catch (SQLException e) {
            System.out.println("EXCEPTION in getCurrentLastReportID() from ReportQueryHandler ");
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public static String getReportAndAll(User user, Network net, Report rep) {

        String str = "";

        return str;
    }

    private static String getNetworkType(String networkName) {
        String type = "ISP";

        if (networkName.toUpperCase().contains("BANGLALNIK")) {
            type = "MOBILE";
        } else if (networkName.toUpperCase().contains("GRAMEEN")) {
            type = "MOBILE";
        } else if (networkName.toUpperCase().contains("AIRTEL")) {
            type = "MOBILE";
        } else if (networkName.toUpperCase().contains("ROBI")) {
            type = "MOBILE";
        }

        return type;
    }

    public static List<String> getNetworkNames() {
        DBHandler.openConnection();
        List<String> list = new ArrayList<>();
        String query = "SELECT distinct carrier_name from NETWORK;";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            int count = 0;
            while (rs.next()) {
                count++;
                String net = rs.getString(1);
                list.add(net);
            }
            System.out.println("Total query entries : " + count);
        } catch (SQLException ex) {
            System.out.println("________SQL_EXCEPTION_____________________");
            ex.printStackTrace();
        }
        DBHandler.closeConnection();

        return list;
    }

    public static List<Report> getAllReports() {
        //DBHandler.closeConnection();
        DBHandler.openConnection();
        List<Report> list = new ArrayList<>();
        String query = "SELECT REPORT_ID,REPORT.CONNECTION_ID,timestamp,url,type_of_testing,is_censored, CONNECTION.global_ip,asn,city,organization,carrier_name,latitude,longitude,country_name,region,postal\n"
                + "FROM REPORT INNER JOIN CONNECTION ON REPORT.CONNECTION_ID = CONNECTION.CONNECTION_ID INNER JOIN NETWORK ON CONNECTION.global_ip=NETWORK.global_ip;";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            int count = 0;
            while (rs.next()) {
                count++;
                Report report = new Report();
                report.setReport_id(rs.getInt(1));
                report.setConnection_id(rs.getInt(2));
                report.setTime_stamp(rs.getString("timestamp"));
                report.setUrl(rs.getString("url"));
                report.setTest_type(rs.getString("type_of_testing"));
                report.setIs_censored(rs.getInt("is_censored") == 1 ? "YES" : "NO");
                report.setNetwork_type(rs.getString("carrier_name").compareToIgnoreCase("NA") == 0 ? "WIFI" : "ISP");
                if (report.getNetwork_type().equals("WIFI")) {
                    report.setNetwork_name(rs.getString("organization"));
                } else {
                    report.setNetwork_name(rs.getString("carrier_name"));
                }
                list.add(report);
                ReportQueryHandler.printReport(report);
            }
            System.out.println("Total query entries : " + count);
        } catch (SQLException ex) {
            System.out.println("________SQL_EXCEPTION Line 113 ReportQueryHandler.getAllReports()_____________________");
//            ex.printStackTrace();
        }
        DBHandler.closeConnection();

        return list;
    }

    public static void printReport(Report report) {
        System.out.println(report.getReport_id() + " : " + report.getConnection_id() + " : " + report.getTime_stamp() + " : " + report.getUrl() + " : " + report.getTest_type() + " : " + report.getIs_censored() + " : " + report.getNetwork_name() + " : " + report.getNetwork_type());
    }

    public Report_Network_User_Join getFullReport(int report_id) {
        DBHandler.openConnection();

        String query = "SELECT REPORT_ID,REPORT.CONNECTION_ID,timestamp,url,type_of_testing,is_censored, CONNECTION.global_ip,asn,city,organization,carrier_name,latitude,longitude,country_name,region,postal\n"
                + "FROM REPORT INNER JOIN CONNECTION ON REPORT.CONNECTION_ID = CONNECTION.CONNECTION_ID INNER JOIN NETWORK ON CONNECTION.global_ip=NETWORK.global_ip WHERE REPORT.REPORT_ID = " + report_id + ";";

        query = "SELECT REPORT_ID, REPORT.CONNECTION_ID, timestamp, url, type_of_testing, is_censored"
                + ", CONNECTION.global_ip, carrier_name, asn, city, organization, carrier_name, latitude"
                + ", longitude, country_name, continent_name, city, region FROM REPORT, CONNECTION, NETWORK "
                + " WHERE REPORT.CONNECTION_ID = CONNECTION.CONNECTION_ID AND CONNECTION.global_ip = NETWORK.global_ip "
                + " AND REPORT.report_id = " + report_id;

        System.out.println("QUERY is:\n" + query);

        String test_type = "NULL";

        Report_Network_User_Join reportJoiner = new Report_Network_User_Join();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                reportJoiner.report.setReport_id(rs.getInt(1));
                reportJoiner.report.setConnection_id(rs.getInt(2));
                reportJoiner.report.setTime_stamp(rs.getString("timestamp"));
                reportJoiner.report.setUrl(rs.getString("url"));
                reportJoiner.report.setTest_type(rs.getString("type_of_testing"));
                reportJoiner.report.setIs_censored(rs.getInt("is_censored") == 1 ? "YES" : "NO");
                reportJoiner.report.setNetwork_type(rs.getString("carrier_name").compareToIgnoreCase("NA") == 0 ? "WIFI" : "ISP");

                if (reportJoiner.report.getNetwork_type().equals("WIFI")) {
                    reportJoiner.report.setNetwork_name(rs.getString("organization"));
                } else {
                    reportJoiner.report.setNetwork_name(rs.getString("carrier_name"));
                }

                reportJoiner.network.setAsn(rs.getString("asn"));
                reportJoiner.network.setCity(rs.getString("city"));
//                reportJoiner.network.setContinent(rs.getString("continent"));
                reportJoiner.network.setCountry(rs.getString("country_name"));
                reportJoiner.network.setGlobal_ip(rs.getString("global_ip"));
                reportJoiner.network.setRegion(rs.getString("region"));
                reportJoiner.network.setPostal(rs.getString("postal"));
                reportJoiner.network.setOrg(rs.getString("org"));
//                reportJoiner.network.setStatus(rs.getString("status"));

            }

            if (test_type.equals("TCP")) {
                query = "SELECT report_id, ip_address, port, tor_not_connected, is_time_out, is_fin_bit_set, is_rst_bit_set, successful_iteration_number_local_server, "
                        + "successful_iteration_number_tor, is_tor_connect_successful, middle_box_hop_count, is_censored_TCP WHERE report_id = " + report_id;
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                
                while(rs.next()){
                    TCPDetails tcp_detail = new TCPDetails();
                    tcp_detail.setIp_address(rs.getString("ip_address"));
                    tcp_detail.setIs_censored_TCP_str(rs.getString("is_censored_TCP"));
                    tcp_detail.setIs_tor_not_connected_str(rs.getString("tor_not_connected"));
                    tcp_detail.setIs_timeout_str(rs.getString("is_time_out"));
                    tcp_detail.setIs_fin_bit_set_str(rs.getString("is_fin_bit_set"));
                    tcp_detail.setIs_rst_bit_set_str(rs.getString("is_rst_bit_set"));
                    tcp_detail.setSuccessful_iteration_local_server_str(rs.getString("successful_iteration_number_local_server"));
                    tcp_detail.setSuccessful_iteration_tor_str(rs.getString("successful_iteration_number_tor"));
                    tcp_detail.setIs_tor_connect_successful_str(rs.getString("is_tor_connect_successful"));
//                    reportJoiner.report.tcp_details_list.add(tcp_detail);
                }

            }

        } catch (SQLException ex) {
            System.out.println("________SQL_EXCEPTION Line 113 ReportQueryHandler.getAllReports()_____________________");
//            ex.printStackTrace();
        }

        DBHandler.closeConnection();

        return null;

    }

}
