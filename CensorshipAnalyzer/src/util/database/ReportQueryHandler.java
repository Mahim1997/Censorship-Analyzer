package util.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static util.database.DBHandler.conn;

public class ReportQueryHandler {

    public static int getCurrentLastReportID() {
        String sql = "SELECT count(*) FROM Report";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next()){
                return rs.getInt(1);    //Result Set Columns counted from index = 1
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

}
