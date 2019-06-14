package hall_management.db.queries;

import hall_management.db.connection.MyConnection;
import java.sql.Connection;
import hall_management.db.connection.MyConnection;
import hall_management.ui.popup.PopUpWindow;
import hall_management.ui.startPage.Main;
import hall_management.util.Interface.Table;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Update_Query {

    private static final MyConnection connectionObject = new MyConnection();
    private static final Connection con = connectionObject.getConnection();

    public static void updateInfo(String tableName, String columnName, String newColumnValue, String idOrNIDorTeacherIDVALUE) throws Exception {
        System.out.println("<><><<>>>Inside UpdateQuery.updateInfo( X, X, X) ... ");
        String verifier = Query.verify_ID_or_NID_or_StaffID_or_HallID(tableName);
        String query = "UPDATE " + tableName + " SET " + columnName + " = '" + newColumnValue + "' WHERE " + verifier + " = " + idOrNIDorTeacherIDVALUE;
        System.out.println("Query is " + query);
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        
        System.out.println("<><><><>EXECUTING query..\n\n");
    }

    public static void updatePassword(String tableName, String id_OrNID_OrStaffID, String newPass) throws Exception{
        String thistableName = tableName;
        String whatToCallWithID = "ID";
        String whatToCallPassword = "PASSWORD";
        if(tableName.toUpperCase().contains("STAFF")){
            whatToCallWithID = "ID";
            whatToCallPassword = "STAFF_PASSWORD";
        }
        else if(tableName.toUpperCase().contains("STUDENT")){
            whatToCallWithID = "ID";
            whatToCallPassword = "PASSWORD";
        }
        else if(tableName.toUpperCase().contains("TEACHER")){
            whatToCallWithID = "ID";
            whatToCallPassword = "PASSWORD";
        }
        else if(tableName.toUpperCase().contains("GUEST")){
            whatToCallWithID = "NID";
            whatToCallPassword = "PASSWORD";
            tableName = "TABLE_GUEST_PASS";
        }
        String query = null;
        query = "UPDATE " + tableName + " SET " + whatToCallPassword + " = " + newPass + " WHERE " + whatToCallWithID + " = " +id_OrNID_OrStaffID ;
        System.out.println("Inside Uppdate_Query.updatePassword() .. QUERY IS ==>> \n" + query);
        Statement st = con.createStatement();
        st.execute(query);
    }
}
