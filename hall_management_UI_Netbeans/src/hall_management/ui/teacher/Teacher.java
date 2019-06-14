
package hall_management.ui.teacher;

import hall_management.db.queries.Query;
import hall_management.ui.startPage.Main;
import java.sql.*;
import static hall_management.db.queries.Query.con;

class Teacher {
    public static String teacherID ;
    
    public static int index_ID = 0;
    public static int index_FullName = 1;
    public static int index_departmentID = 2;
    public static int index_designation = 3;
    public static int index_contactNum = 4;
    public static int index_Gender = 5;
    
    public static void setInitialScene()
    {
        Teacher.teacherID = Main.teacherID;
    }
    public static String[] getTeacherInfo(String id) throws SQLException {
        String query = "SELECT ID , NAME, DEPARTMENT_ID , DESIGNATION, CONTACT_NO,GENDER \n"
                + "FROM VW_TEACHER \n"
                + "WHERE ID = " + id;
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        
        String []info = new String[columnsNumber];
        
        while(rs.next()){
            for (int i = 1; i <= columnsNumber; i++) {
                info[i - 1] = rs.getString(i);
            }
        }
        return info;
    }

    public static void updatePhoneNumber(String newContactNumber) throws SQLException {
        Connection con = Query.con;
        String query;
        query = "UPDATE TABLE_TEACHER \nSET CONTACT_NO = '" + newContactNumber + "' \nWHERE ID = " + Main.teacherID;
        System.out.println("QUERY IS .. " + query);
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);        
    }
}
