package util.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

    public static List<Report> getListOfReports(int startIdx, int endIdx) {
        List<Report> list = new ArrayList<>();

        String query = "SELECT count(*) FROM Report";
        
        return list;
    }

}
