package util.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ui.model.DNSDetails;
import ui.model.Report;
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

/*    public static List<Report> getListOfReports(int startIdx, int endIdx) {
        try {
            List<Report> list = new ArrayList<>();

            String query;//= "";

            if ((startIdx == -1) && (endIdx == -1)) {
                query = "SELECT * FROM Report, Connection where Connection.connection_id = Report.connection_id";
            } else {
                query = "SELECT * FROM Report, Connection where Connection.connection_id = Report.connection_id AND Report.report_id >= " + String.valueOf(startIdx) + " AND Report.report_id <= " + String.valueOf(endIdx);
            }
            System.out.println("===--->>> Query is <" + query + ">");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);


            while (rs.next()) {
                Report report = new Report();
                report.setReport_id(rs.getInt(1));
                report.setTime_stamp(rs.getString(3));
                report.setUrl(rs.getString(4));
                report.setIs_censored(rs.getString(5));
                report.setTest_type(rs.getString(6));
                report.setCensored_type(rs.getString(7));
                report.network.setNetwork_name(rs.getString(15));
                report.network.setNetwork_type(getNetworkType(Config.NETWORK_TYPE);
                report.setCensorship_code(rs.getInt(12));
                
                System.out.println("-->>Adding to the list the report ... printing the report");
                System.out.println(report.toString());
                
                list.add(report);   //Add to the list of reports
                //DNS Details set kora baaki aache
            }
           
            System.out.println("--->>>Outside first while loop first SQL statement, printing list of reporsts .... ");
            for(int i=0; i<list.size(); i++){
                Report get = list.get(i);
                System.out.println(get.toString());
            }

            //Now ... adding the DNS_Details for each report ... 
            String sql;
            for (int i = 0; i < list.size(); i++) {
                Report rep = list.get(i);
                System.out.println("CHECKING inside for loop i = " + i + " , report id = " + rep.getReportID());
                //For now it is skipped
//                if(rep.getCensoredType().equals("DNS") == false){
//                    continue;
//                }
                
                DNSDetails details_dns = new DNSDetails();
                details_dns.setReport(rep);

                //Local IP Addresses ... 
                sql = "SELECT * from LocalIpAddresses WHERE LocalIpAddresses.report_id = " + String.valueOf(rep.getReportID());
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                List<String> listIpLocal = new ArrayList<>();
                while (rs.next()) {
                    //Read all the rows ... 
                    listIpLocal.add(rs.getString(2));
                }

                //Now do the same with Stubby IP Addresses ... 
                sql = "SELECT * from StubbyIpAddresses WHERE StubbyIpAddresses.report_id = " + String.valueOf(rep.getReportID());
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                List<String> listIpStubby = new ArrayList<>();
                while (rs.next()) {
                    //Read all the rows ... 
                    listIpStubby.add(rs.getString(2));
                }

                //Now add in the dnsDetails ... 
                details_dns.setListIpLocalDNSServer(listIpLocal);
                details_dns.setListIpStubby(listIpStubby);

            }
            
//            System.out.println("========>>> BEFORE RETURNING ... printing list .... ");
//            list.forEach((t) -> {
//                System.out.println(t.toString());
//                System.out.println("\n\n----------------------------------------\n\n");
//            });

            return list;
        } catch (SQLException ex) {
            System.out.println("-->>EXCEPTION in ReportQueryHandler.getListOfReports() ... ");
//            ex.printStackTrace();
        }

        return null;
    }
*/
}
