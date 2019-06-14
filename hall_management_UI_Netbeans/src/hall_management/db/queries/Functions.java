package hall_management.db.queries;

import hall_management.db.connection.MyConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class Functions {

    public static final MyConnection connectionObject = new MyConnection();
    public static final Connection con = connectionObject.getConnection();
    public static CallableStatement callableStatement;

    public static String getHallID(String teacherID) {
        String hall_id = null;
        try {
            String query = "{? = call FIND_HALL_ID(?)}";
            callableStatement = con.prepareCall(query);
            callableStatement.setString(2, teacherID);
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.execute();
            return callableStatement.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hall_id;
    }

    public static String getHallNAME(String teacherID) {
        String hall_id = null;
        try {
            String query = "{? = call FIND_MANAGED_HALL_NAME(?)}";
            callableStatement = con.prepareCall(query);
            callableStatement.setString(2, teacherID);
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.execute();
            return callableStatement.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hall_id;
    }

    public static String getHallHeadTeacherID(String hallID) {
        try {
            String query = "{? = call FIND_HALL_HEAD(?)}";
            callableStatement = con.prepareCall(query);
            callableStatement.setString(2, hallID);
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.execute();
            return callableStatement.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void procedureDeleteVoidGuests() throws Exception {
        try {
            String query = "{call proc_remove_unlistedGuest()}";
            callableStatement = con.prepareCall(query);
            System.out.println("=-->>INSIDE Functions.procedureDeleteVoidGuests .. executing query = " + query);
            callableStatement.execute();
//            return callableStatement.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

//        return null;
    }
}
