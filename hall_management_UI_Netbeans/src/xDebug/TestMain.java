package xDebug;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestMain {

    public static void main(String[] args) {
//        System.out.println(Checker.doesThisIDExist(type_Student, "7000"));
//        try{
//            System.out.println(Query.doesPasswordMatchForId("Student", "7000", "1430"));
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        MyConnection con = new MyConnection();
//        try{
//            Connection connnection = con.getConnection();
//        }catch(Exception e){
//            System.out.println("CANNOT CONNECT!!");
//        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        formatter.setLenient(false);
        String dateInString = "06-01-2013";

        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException ex) {
//            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(date);
        System.out.println(formatter.format(date));

//        System.out.println(LocalDate.parse(date));//

    }

    public static void runTest() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hall_management", "hall_management");
        String query = "SELECT * FROM TABLE_STUDENT";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        // Iterate through the data in the result set and display it. 
        while (rs.next()) {
            //Print one row          
            for (int i = 1; i <= columnsNumber; i++) {

                System.out.print(rs.getString(i) + " <> "); //Print one element of a row

            }
            System.out.println();//Move to the next line to print the next row.           
        }
        con.close();
    }
}
