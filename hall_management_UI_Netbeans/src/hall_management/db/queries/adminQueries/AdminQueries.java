package hall_management.db.queries.adminQueries;

import hall_management.db.connection.MyConnection;
import hall_management.db.queries.Query;
import hall_management.db.queries.more_queries.Event_Queries;
import hall_management.ui.admin.Guests;
import hall_management.util.Interface.Table;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AdminQueries {

    public static final MyConnection connectionObject = new MyConnection();
    public static final Connection con = connectionObject.getConnection();
    public static ResultSet rs;
    public static Statement st;

    public static String makeDateOfBirth(String dateOfBirth_year_std, String dateOfBirth_month_std, String dateOfBirth_day_std) {
        String s = null;
        int dy, yr, mon;
        String year = dateOfBirth_year_std;
        String month = dateOfBirth_month_std;
        String day = dateOfBirth_day_std;
        try {
            yr = Integer.parseInt(dateOfBirth_year_std);
            mon = Integer.parseInt(dateOfBirth_month_std);
            dy = Integer.parseInt(dateOfBirth_day_std);
        } catch (Exception e) {
            e.printStackTrace();
            return "EXCEPTION";
        }
        if (mon < 10) {
            month = "0" + String.valueOf(mon);
        }
        if (dy < 10) {
            day = "0" + String.valueOf(dy);
        }
        String x = day + "/" + month + "/" + year;
        s = "TO_DATE( " + quote(x) + ", 'dd/mm/yyyy')";
        return s;
    }

    public static String quote(String s) {
        String[] arr = s.split("'");
        if (arr.length <= 1) {
            return " '" + s + "' ";
        } else {
            return s;
        }
    }

    public static String insertStudent(String studentID, String firstName_std,
            String lastName_std, String dept_std, String addr_std, String blood_std,
            String dateOfBirth, String type_std, String hall_name, String religion_std,
            String gender_std, String pass_std, String fatherName_std, String motherName_std, String contactNo_std) {
        if (gender_std.toUpperCase().equals("MALE")) {
            gender_std = "M";
        } else {
            gender_std = "F";
        }
        String query = null;
        try {
            String fieldInfoFromTable = Query.getFieldInfoFromTable(Table.Student, studentID, "ID");
            if (fieldInfoFromTable != null) {
                return "QUERY_FAILURE";
            }
            String hall_id = Event_Queries.getFieldOfEvent(Table.Hall, "HALL_ID", "UPPER(HALL_NAME)", quote(hall_name));
            st = con.createStatement();
            query = "INSERT INTO " + Table.Student + " VALUES ( "
                    + quote(studentID) + " , "
                    + quote(firstName_std) + " , "
                    + quote(lastName_std) + " , "
                    + quote(dept_std) + " , "
                    + quote(addr_std) + " , "
                    + quote(blood_std) + " , "
                    + dateOfBirth + " , "
                    + quote(type_std) + " , "
                    + quote(hall_id) + " , "
                    + quote(religion_std) + " , "
                    + quote(gender_std) + " , "
                    + quote(pass_std) + " , "
                    + quote(fatherName_std) + " , "
                    + quote(motherName_std) + " , "
                    + quote(contactNo_std)
                    + "  )";

            System.out.println("=-->>ADMINQUERIES.insertStudent.. query is \n" + query);
            st.execute(query);

            query = "INSERT INTO TABLE_STUDENT_HALL_HISTORY(HALL_ID, STUDENT_ID, START_DATE)\n"
                    + "VALUES( "
                    + quote(hall_id) + " , "
                    + quote(studentID) + " , "
                    + makeDateOracleForm(getCurrentDate())
                    + "  )";
            System.out.println("=->New query is .. \n" + query);
            st.execute(query);
        } catch (Exception e) {

            e.printStackTrace();
            return "QUERY_PROBLEM";
        }

        return "QUERY_SUCCESS";
    }

    public static String insertNewTeacher(String id_tcr, String fName_tcr, String lName_tcr, String dept_tcr, String desig_tcr,
            String contact_tcr, String password_tcr, String gender_tcr) throws Exception {

        String s = Query.getFieldInfoFromTable(Table.Teacher, id_tcr, "ID");
        if (s != null) {
            return "ALREADY_PRESENT";
        }

        String query = "INSERT INTO " + Table.Teacher + " VALUES ( "
                + quote(id_tcr) + " , "
                + quote(fName_tcr) + " , "
                + quote(lName_tcr) + " , "
                + quote(dept_tcr) + " , "
                + quote(desig_tcr) + " , "
                + quote(contact_tcr) + " , "
                + quote(password_tcr) + " , "
                + quote(makeGender(gender_tcr)) + " "
                + " )";

        System.out.println("=->Insert new teacher query is \n" + query);
        st = con.createStatement();
        st.execute(query);

        return "QUERY_SUCCESS";
    }

    public static String insertNewStaff(String staff_id, String fullName_staff, String jobType,
            String contact_tcr, String password_tcr, String hallNameStaff) throws Exception {

        String s = Query.getFieldInfoFromTable(Table.Staff, staff_id, "ID");
        if (s != null) {
            return "ALREADY_PRESENT";
        }
        System.out.println("=->Insert new STAFF!  \n");
        System.out.println("=-->>>hallName = " + hallNameStaff);
        String hallIDStaff = null;
        if (hallNameStaff != null) {
            hallIDStaff = Event_Queries.getFieldOfEvent(Table.Hall, "HALL_ID", "UPPER(HALL_NAME)", hallNameStaff.toUpperCase());
            System.out.println("=->>>hallIDStaff = " + hallIDStaff);
        }

        String query = "INSERT INTO " + Table.Staff + " VALUES ( "
                + quote(staff_id) + " , "
                + quote(fullName_staff) + " , "
                + quote(jobType) + " , "
                + quote(contact_tcr) + " , "
                + quote(password_tcr) + "  "
                + " )";

        System.out.println("Query is : " + query);
        st = con.createStatement();
        st.execute(query);

        if (hallIDStaff != null) {
            query = "INSERT INTO TABLE_STAFF_WORKSAT_HALL VALUES( "
                    + quote(hallIDStaff) + " , "
                    + quote(staff_id)
                    + " )";
            System.out.println("=->ANOTHER QUERY IS ..\n" + query);
            st.execute(query);
        }

        return "QUERY_SUCCESS";
    }

    public static String makeGender(String gender_std) {
        if (gender_std.toUpperCase().equals("MALE")) {
            gender_std = "M";
        } else {
            gender_std = "F";
        }
        return gender_std;
    }

    public static String insertNewEvent(String eventName, String year, String sport, String type,
            String upcoming, String hallName, String superVisorID) throws Exception {
        String event_id = Event_Queries.getNextEventID();
        //CHECKING
        eventName = Event_Queries.makeEventName(sport, year, type);
        boolean flag = Event_Queries.doesThisEventExist(eventName, year, sport);
        if (flag == true) {
            return "ALREADY_EXISTS";
        }
        String query = null;

        if (type.toUpperCase().contains("GLOBAL")) {
            query = "INSERT INTO TABLE_EVENT(EVENT_ID, EVENT_NAME, EVENT_YEAR, EVENT_SPORT, EVENT_TYPE, EVENT_STATUS,  EVENT_SUPERVISOR_ID)"
                    + " VALUES ( "
                    + quote(event_id) + " , "
                    + quote(eventName) + " , "
                    + quote(year) + " , "
                    + quote(sport.toUpperCase()) + " , "
                    + quote(type) + " , "
                    + quote(upcoming) + " , "
                    + quote(superVisorID)
                    + " )";
            System.out.println("=-->>INSIDE ADD NEW EVENT.. query is\n" + query);
            st = con.createStatement();
            st.execute(query);
        } else {
            String hallID = Event_Queries.getFieldOfEvent(Table.Hall, "HALL_ID", "UPPER(HALL_NAME)", hallName.toUpperCase());
            query = "INSERT INTO TABLE_EVENT(EVENT_ID, EVENT_NAME, EVENT_YEAR, EVENT_SPORT, EVENT_TYPE, EVENT_STATUS, HALL_ID, EVENT_SUPERVISOR_ID)"
                    + " VALUES ( "
                    + quote(event_id) + " , "
                    + quote(eventName) + " , "
                    + quote(year) + " , "
                    + quote(sport.toUpperCase()) + " , "
                    + quote(type) + " , "
                    + quote(upcoming) + " , "
                    + quote(hallID) + " , "
                    + quote(superVisorID)
                    + " )";
            System.out.println("=-->>INSIDE ADD NEW EVENT.. query is\n" + query);
            st = con.createStatement();
            st.execute(query);

        }

        return "SUCCESS";
    }

    public static String updateField(String tableName, String tobeUpdatedFieldName,
            String updatedFieldValue,
            String idOrNIDVerifier, String idOrNIDValue) throws Exception {
        //first we check
//        String s = Query.getFieldInfoFromTable(tableName, idOrNIDValue, "")
        String s = Event_Queries.getFieldOfEvent(tableName, idOrNIDVerifier, idOrNIDVerifier, idOrNIDValue);
        if (s == null) {
            return "DOESNOT";
        }
        String query = "UPDATE " + tableName
                + " SET " + tobeUpdatedFieldName + " = " + quote(updatedFieldValue)
                + " WHERE " + idOrNIDVerifier + " = " + quote(idOrNIDValue);
        ;
        System.out.println("--->> Inside AdminQueries.updateField.. query is\n" + query);
        st = con.createStatement();
        st.execute(query);

        return "SUCCESS";
    }

    /*
        public String NID;
    public String fullName;
    public String contactNumber;
    public String address;
    public Button deleteButton;
     */
    public static List<Guests> getListOfGuests() throws Exception {
        List<Guests> list = new ArrayList<>();

        String query = "select NID, FIRST_NAME || ' ' || LAST_NAME, CONTACT_NO , ADDRESS , \"Current_no_of_students\"\n"
                + "from VW_GUEST_STD_COUNT\n"
                + "ORDER BY \"Current_no_of_students\" desc , NID asc ";
        System.out.println("=->>INSIDE ADMINQUERIES.getListOfGuests().. query is \n" + query);
        st = con.createStatement();
        st.execute(query);
        rs = st.getResultSet();
        while (rs.next()) {
            list.add(new Guests(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

        }
        return list;
    }

    public static void deleteRowFromTable(String tableName, String verifierColumnName, String verifierColumnValue) throws Exception {
        String query = "DELETE FROM " + tableName + " WHERE " + verifierColumnName + " = " + quote(verifierColumnValue);
        System.out.println("=->>Inside AdminQueries.deleteRowFromTable.. query is \n" + query);
        st = con.createStatement();
        st.execute(query);
    }

    public static String makeDateOracleForm(String date) {
        String s = "TO_DATE( " + quote(date) + ", 'dd/mm/yyyy')";
        return s;
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String s = dateFormat.format(cal.getTime());
        return s;
    }
//
//    public static void insertStudentIntoHall(String studentID, String hallName) throws Exception {
//        String hallId = Event_Queries.getFieldOfEvent(Table.Hall, "HALL_ID", "UPPER(HALL_NAME)", hallName.toUpperCase());
//        String query = "INSERT INTO TABLE_STUDENT_HALL_HISTORY(HALL_ID, STUDENT_ID, START_DATE)\n"
//                + "VALUES( "
//                + quote(studentID) + " , "
//                + quote(hallId) + " , "
//                + makeDateOracleForm(getCurrentDate())
//                + "  )";
//
//        System.out.println("INSIDE AdminQueries.insertStudentIntoHall... query: \n" + query);
//        st = con.createStatement();
//        st.execute(query);
//    }

    public static void updateHallProvost(String hall_name, String provostID, String start_date) throws Exception {
        String hallID = Event_Queries.getFieldOfEvent(Table.Hall, "HALL_ID", "UPPER(HALL_NAME)", hall_name);
        String current_teacher_id = null;
        String query = "SELECT TEACHER_ID FROM TABLE_HALL_HEAD_HISTORY WHERE END_DATE IS NULL\n"
                + "AND HALL_ID = " + quote(hallID);
        System.out.println("=-->>Inside AdminQueries.updateHallProvost ... query is \n" + query);
        st = con.createStatement();
        st.execute(query);
        rs = st.getResultSet();
        if (rs.next()) {
            current_teacher_id = rs.getString(1);
        }
        String current_date = makeDateOracleForm(start_date);

        query = "UPDATE " + Table.Hall_Head_History
                + " SET END_DATE = " + current_date
                + " WHERE HALL_ID = " + quote(hallID) + "and "
                + " TEACHER_ID = " + quote(current_teacher_id) + " AND END_DATE IS NOT NULL";
        System.out.println("=-->>>NEW QUERY IS \n" + query);
        st.execute(query);
        query = "INSERT INTO TABLE_HALL_HEAD_HISTORY(TEACHER_ID, HALL_ID, START_DATE)\n"
                + "VALUES(  " + quote(provostID) + " , " + 
                quote(hallID) + " , " + 
                quote(current_date)
                + " )";
        st.execute(query);
        System.out.println("=->>ANOTHER QUERY IS\n" + query);
    }

}
