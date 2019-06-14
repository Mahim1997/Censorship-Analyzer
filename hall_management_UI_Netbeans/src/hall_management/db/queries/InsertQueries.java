package hall_management.db.queries;

import hall_management.db.connection.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import static queries.Query.con;

public class InsertQueries {

    public static final MyConnection connectionObject = new MyConnection();
    public static final Connection connection = connectionObject.getConnection();

    public static boolean isIn(String table, String id) throws SQLException {
        System.out.println("==>>> INSIDE InsertQueries.isIn Function ... ");
        String tableName = "TABLE_" + table.toUpperCase();
        String query = "SELECT NID FROM " + tableName + " WHERE NID = " + id;
        System.out.println("Query is \n" + query);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        boolean flag = rs.next();
        System.out.println("NID = " + id + " , returining " + flag);
        System.out.println("<><><><>...<><><><>");
        return flag;
    }

    public static void insertGuest(String guestNID, String first_name, String last_name, String guestContact, String guestAddress) throws SQLException {
//        if (InsertQueries.isIn("Guest", guestNID) == true) {
//            PopUpWindow.displayInCheck("Error", "Guest Already Present");
//            return;
//        }
        String tableName = "TABLE_GUEST";
//        String query = "INSERT INTO TABLE_GUEST VALUES('" + guestNID + " , '" + guestName + "' , '" + guestContact + "', '" + guestAddress + "')"; 

        String query = "INSERT INTO TABLE_GUEST VALUES('" + guestNID + "' , '" + first_name + "' , '" + last_name + "' , '" + guestContact + "', '" + guestAddress + "')";
        Statement statement = connection.createStatement();
        System.out.println("Inside InsertQueries.insertGuest{}.. Query is .. ");
        System.out.println(query);
        ResultSet rs = statement.executeQuery(query);
        System.out.println("<><>Query Executed.. \n\n");
//        System.out.println("Inside Query.insertGuest{}.. Query is .. ");
//        System.out.println(query);

    }

    public static void insertGuestStudentRelation(String guestNID, String studentID, String guestRelation) throws Exception {
//        if (InsertQueries.isIn("Guest", guestNID) == false) {
//            System.out.println("<><>Inside InsertQueries.insertGuestStudentRelation ... guestNID not present in Guest Table...return ");
//            return;
//        }
        String query = "INSERT INTO TABLE_ALLOWED_GUEST VALUES('" + guestNID + "' , '" + studentID + "' , '" + guestRelation + "')";
        Statement statement = connection.createStatement();
        System.out.println("Inside InsertQueries.insertGuestStudentRelation... , Query is .. ");
        System.out.println(query);
        ResultSet rs = statement.executeQuery(query);
        System.out.println("<><><><><><> QUERY IS EXECUTED!!\n\n");
//        System.out.println("Inside Query.insertGuestStudentRelation... , Query is .. ");
//        System.out.println(query);
    }
}
