package hall_management.db.queries;

import hall_management.db.connection.MyConnection;
import hall_management.ui.guest.Student_OfThisGuest;
import hall_management.ui.student.seeGuestList.AllowedGuest;
import hall_management.ui.startPage.Main;
import hall_management.ui.student.Student;
import hall_management.util.Interface.Table;
import hall_management.util.Interface.View;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Query {

    public static final MyConnection connectionObject = new MyConnection();
    public static final Connection con = connectionObject.getConnection();

    public static boolean isIn(String table, String id) throws SQLException {
        System.out.println("<><><><>INSIDE Query.isIn Function ... ");
        String tableName = table;
        String query;
        if (tableName.toUpperCase().contains("GUEST") == true) {
            query = "SELECT NID FROM " + tableName + " WHERE NID = " + id;
        } else if (tableName.toUpperCase().contains("ROOM") == true) {
            query = "SELECT ROOM_NO FROM " + tableName + " WHERE ROOM_NO = " + id;
        } else {
            query = "SELECT ID FROM " + tableName + " WHERE ID = " + id;
        }

        System.out.println("Query is " + query);
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        boolean flag = rs.next();
        System.out.println("==>>RETURNING " + flag + "\n------*****-------\n");
        return flag;
    }

    public static boolean doesPasswordMatchForId(String table, String id, String password) throws SQLException {
        System.out.println("===>>>> INSIDE Query(CLASS) function doesPasswordMatchForId... ");

        String tableName = table;
        String pass = "PASSWORD";
        String query = null;
        if (table.toUpperCase().contains("STAFF")) {
            pass = "STAFF_PASSWORD";
            query = "SELECT " + pass + " FROM " + tableName + " WHERE ID = " + id;
        } else if (tableName.toUpperCase().contains("GUEST") == true) {
            query = "SELECT " + "\"password\"" + " FROM " + tableName + " WHERE NID = " + id;
        } else {
            query = "SELECT " + pass + " FROM " + tableName + " WHERE ID = " + id;
        }
        Statement statement = con.createStatement();

        System.out.println("Query is .. \n" + query);

        ResultSet rs = statement.executeQuery(query);

        String receivedPass = null;
        if (rs.next()) {
            receivedPass = rs.getString(1);
        }
        System.out.println("Received pass is " + receivedPass + " and given pass is " + password);
        System.out.println("<><><>>\n\n");
        return (receivedPass.equals(password));
    }

    public static String findPasswordForThisID(String table, String id) throws SQLException {
        String tableName = table;
        String pass = "PASSWORD";
        if (table.equals(Table.Staff)) {
            pass = "STAFF_PASSWORD";
        }
        String query = "SELECT " + pass + " FROM " + tableName + " WHERE ID = " + id;
        Statement statement = con.createStatement();

        System.out.println("Query is .. \n" + query);

        ResultSet rs = statement.executeQuery(query);
        return rs.getString(1);
    }

    public static String[] getStudentInfo(String id) throws SQLException {

        String query = "SELECT ID ,NAME ,DEPARTMENT_ID ,ADDRESS ,TO_CHAR(BIRTH_DATE,'dd-mm-yyyy') ,RELIGION ,FATHER_NAME "
                + ",MOTHER_NAME ,GENDER ,BLOOD_GROUP ,CONTACT_NO FROM " + View.Student + " WHERE ID=" + id;
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        String[] info = new String[columnsNumber];
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {

                info[i - 1] = rs.getString(i);
//                System.out.print(rs.getString(i) + " <> "); //Print one element of a row

            }
        }
        return info;
    }

    public static void insertGuest(String guest, String guestNID, String guestName, String guestContact, String guestAddress) throws SQLException {
        String[] fullName = guestName.split(" ");
        String first_name = fullName[0];
        String last_name = fullName[1];
        String query = "INSERT INTO " + Table.Guest + " VALUES('" + guestNID + "' , '" + first_name + "' , '" + last_name
                + "' , '" + guestContact + "', '" + guestAddress + "')";
        Statement statement = con.createStatement();
        System.out.println("Inside Query.insertGuest{}.. Query is .. ");
        System.out.println(query);
        ResultSet rs = statement.executeQuery(query);
        System.out.println("Inside Query.insertGuest{}.. Query is .. ");
        System.out.println(query);

    }

    public static void insertGuestStudentRelation(String allowed_guest, String guestNID, String studentID, String guestRelation) throws Exception {
        String query = "INSERT INTO " + Table.Allowed_Guest + " VALUES('" + guestNID + "' , '" + studentID + "' , '" + guestRelation + "')";
        Statement statement = con.createStatement();
        System.out.println("Inside Query.insertGuestStudentRelation... , Query is .. ");
        System.out.println(query);
        ResultSet rs = statement.executeQuery(query);
//        System.out.println("Inside Query.insertGuestStudentRelation... , Query is .. ");
//        System.out.println(query);
    }

    public static List<Student> getStudentsOfThisHall() throws Exception {
        List<Student> list = new ArrayList<>();

//        MyConnection myCon = new MyConnection();
//        Connection con = myCon.getConnection();
        String query = "SELECT ID , FIRST_NAME || ' ' || LAST_NAME, DEPARTMENT_ID , ADDRESS"
                + ", TO_CHAR(BIRTH_DATE, 'dd/mm/yyyy'), RELIGION, FATHER_NAME, MOTHER_NAME , GENDER, BLOOD_GROUP, CONTACT_NO\n"
                + "from TABLE_STUDENT \n"
                + "where HALL_ID IN (\n"
                + "	select hall_id from TABLE_TEACHER join TABLE_HALL_HEAD_HISTORY on (TABLE_TEACHER.ID = TABLE_HALL_HEAD_HISTORY.TEACHER_ID)\n"
                + "	where TABLE_HALL_HEAD_HISTORY.END_DATE is null\n"
                + "	AND TABLE_HALL_HEAD_HISTORY.TEACHER_ID = "
                + String.valueOf(Main.teacherID)
                + ")";
        System.out.println(query);
        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
//        String []info = new String[columnsNumber];
        String[] str = new String[columnsNumber];
        Student student = new Student();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {

//                info[i - 1] = rs.getString(i);
//                System.out.print(rs.getString(i) + " <> "); //Print one element of a row
                str[i - 1] = rs.getString(i);

            }
//            System.out.println("\n");
            student = new Student();
            student.setId(str[0]);
            student.setFullName(str[1]);
            student.setDept_Id(str[2]);
            student.setAddress(str[3]);
            student.setBirthDate(str[4]);
            student.setReligion(str[5]);
            student.setFatherName(str[6]);
            student.setMotherName(str[7]);
            student.setGender(str[8]);
            student.setBloodGrp(str[9]);
            student.setContactNumber(str[10]);
            list.add(student);
        }
//        System.out.println("===>>> PRINTING LIST... Size = " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        return list;
    }

    public static String[] findHallIDAndNameOfStudent(String studentId) throws Exception {

        String query;
//        query = "SELECT HALL_ID FROM TABLE_HALL_HEAD_HISTORY WHERE TEACHER_ID = " + Main.teacherID + " AND END_DATE IS NULL\n";
        query = "SELECT HALL_ID, HALL_NAME FROM " + View.Student + " WHERE ID = " + studentId;
        System.out.println("Query of findHallIdAndNameOfStudent ... ");
        System.out.println(query);
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        String[] arr = new String[columnsNumber];
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                arr[i - 1] = rs.getString(i);
            }
        }
        return arr;
    }

    public static String[] findHallIDAndName(String teacherID) throws Exception {

        String query;
//        query = "SELECT HALL_ID FROM TABLE_HALL_HEAD_HISTORY WHERE TEACHER_ID = " + Main.teacherID + " AND END_DATE IS NULL\n";
        query = "SELECT HALL_ID, (select hall_name from TABLE_HALL where TABLE_HALL.HALL_ID = h.HALL_ID)\n"
                + "from TABLE_HALL_HEAD_HISTORY h\n"
                + "where h.END_DATE is null AND h.teacher_id = " + teacherID; //SUBQUERY
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        String[] arr = new String[columnsNumber];
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                arr[i - 1] = rs.getString(i);
            }
        }
        return arr;
    }

    public static boolean isThisTeacherHallHead(String userID) throws Exception {

        String query;
        query = "SELECT HALL_ID FROM TABLE_HALL_HEAD_HISTORY WHERE TEACHER_ID = " + Main.teacherID + " AND END_DATE IS NULL\n";

        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);

        return rs.next();
    }

    public static List<AllowedGuest> getAllowedGuests(String id) throws Exception {
//        System.out.println("\n==>>>Inside Query.getAllowedGuests(id).... ");
        List<AllowedGuest> list = new ArrayList<>();

//        MyConnection myCon = new MyConnection();
//        Connection con = myCon.getConnection();
        String query = "SELECT TABLE_GUEST.NID, FIRST_NAME||' ' || LAST_NAME, TABLE_ALLOWED_GUEST.RELATION"
                + ", TABLE_ALLOWED_GUEST.STUDENT_ID, TABLE_GUEST.CONTACT_NO, TABLE_GUEST.ADDRESS\n"
                + "FROM TABLE_GUEST JOIN TABLE_ALLOWED_GUEST ON (TABLE_GUEST.NID = TABLE_ALLOWED_GUEST.NID)\n"
                + "WHERE TABLE_ALLOWED_GUEST.STUDENT_ID = '" + id + "'";
//        System.out.println("Query is : \n" + query);
        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
//        String []info = new String[columnsNumber];
        String[] str = new String[columnsNumber];
//        System.out.println("COL NUM = " + columnsNumber);
        AllowedGuest allowedGuest = new AllowedGuest();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {

//                info[i - 1] = rs.getString(i);
//                System.out.print(rs.getString(i) + " <> "); //Print one element of a row
                str[i - 1] = rs.getString(i);
//                System.out.println("str[i-1] = " + str[i-1]);
            }
            allowedGuest = new AllowedGuest();
            allowedGuest.setNID(str[0]);
            allowedGuest.setGuestFullName(str[1]);
            allowedGuest.setRelationWithStudent(str[2]);
            if (str[3].equals(Main.studentID) == false) {
                allowedGuest.setStudentId(Main.studentID);
            } else {
                allowedGuest.setStudentId(str[3]);
            }
            allowedGuest.setGuestContactNumber(str[4]);
            allowedGuest.setGuestAddress(str[5]);
            list.add(allowedGuest);
//            System.out.println(allowedGuest);
        }
//        System.out.println("===>>> PRINTING LIST AllowedGuest ... Size = " + list.size());
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//        System.out.println("<><><><>RETURNING LIST... \n");
        return list;

    }

    public static Student getStudentHallRelatedInfo(String id) throws Exception {
        Student student = new Student();
        System.out.println("<><><> Inside Query.getStudent()... ");
        String query = "SELECT ID ,HALL_NAME ,TYPE ,\n"
                + "(SELECT r.ROOM_NO FROM TABLE_ROOM_HISTORY r where s.HALL_ID=r.HALL_ID and s.ID = r.STUDENT_ID and r.END_DATE is null) \"CUR_ROOM\" ,\n"
                + "(SELECT TO_CHAR(r.START_DATE,'DD/MM/YYYY') FROM TABLE_ROOM_HISTORY r where s.HALL_ID=r.HALL_ID and s.ID = r.STUDENT_ID and r.END_DATE is null)\"START_DATE\" ,\n"
                + "HALL_ID \n"
                + "from VW_STUDENT s\n"
                + "where s.ID = " + id;
        System.out.println("QUERY IS " + query);

        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        if (rs.next()) {
            student.setId(rs.getString(1));
            student.setHallName(rs.getString(2));
            student.setType(rs.getString(3).toUpperCase());
            student.setCurrentRoomNumber(rs.getString(4));
            student.setAllocationDate(rs.getString(5));
            student.setHallID(rs.getString(6));
        }

        System.out.println("<><><>Returning Student... " + student);
        return student;
    }

    public static String getFieldInfoFromTable(String TableName, String idOrNid, String columnName) throws Exception {
        String s = null;
        String verifierIdentity = verify_ID_or_NID_or_StaffID_or_HallID(TableName);
        String query = "SELECT " + columnName + " FROM " + TableName + " WHERE " + verifierIdentity + " = " + idOrNid;

        System.out.println("<><><>INSIDE Query.getFieldInfoFromTable(...) .. query is " + query);

        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (rs.next()) {
            if (columnsNumber >= 1) {
                s = rs.getString(1);
            }
        }
        System.out.println("<><>EXECUTING query.. returning s = " + s + "\n\n");
        return s;
    }

    public static String verify_ID_or_NID_or_StaffID_or_HallID(String TableName) {
        String verifierIdentity = "ID";
        if (TableName.toUpperCase().contains("GUEST")) {
            verifierIdentity = "NID";
        } else if (TableName.toUpperCase().contains("STAFF")) {
            verifierIdentity = "ID";
        } else if (TableName.toUpperCase().contains("HALL")) {
            verifierIdentity = "HALL_ID";
        } else {
            verifierIdentity = "ID";
        }
        return verifierIdentity;
    }

    public static boolean checkIsInUsingTwoColumns(String TableName, String col1, String col2, String col1AttributeName, String col2AttributeName) throws Exception {
        boolean flag = false;
        System.out.println("<><><>INside Query.checkIsInUsingTwoColumns... ");

        String query = "SELECT " + col1AttributeName + " , " + col2AttributeName + " FROM " + TableName + " WHERE " + col1AttributeName + " = " + col1 + " AND " + col2AttributeName + " = " + col2;

        System.out.println("Printing query " + query);
        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        flag = rs.next();
        System.out.println("Executing Query .. returning " + flag);
        return flag;
    }

    /*
        this.studentID = studentID;
        this.studentFullName = studentFullName;
        this.guestNID = guestNID;
        this.relationWithGuest = relationWithGuest;
     */
    public static List<Student_OfThisGuest> getStudentsOfThisGuest(String guestID) throws Exception {
        List<Student_OfThisGuest> list = new ArrayList<>();
        String query = "SELECT TABLE_STUDENT.ID , TABLE_STUDENT.FIRST_NAME || ' ' || TABLE_STUDENT.LAST_NAME , \n"
                + "(SELECT TABLE_HALL.HALL_NAME FROM TABLE_HALL WHERE TABLE_STUDENT.HALL_ID = TABLE_HALL.HALL_ID ) , UPPER(TABLE_ALLOWED_GUEST.RELATION)\n"
                + "FROM TABLE_STUDENT , TABLE_ALLOWED_GUEST \n"
                + "WHERE TABLE_ALLOWED_GUEST.STUDENT_ID = TABLE_STUDENT.ID \n"
                + "AND TABLE_ALLOWED_GUEST.NID = " + quote(Main.guestID);
        Statement st = con.createStatement();
        System.out.println("Inside Query.getStudentsOfThisGuest(guestID)... Query is \n");
        System.out.println(query);
        st.execute(query);
        ResultSet rs = st.getResultSet();
        while (rs.next()) {
            Student_OfThisGuest stdnt = new Student_OfThisGuest(rs.getString(1),
                     rs.getString(2),
                     rs.getString(3),
                     rs.getString(4));
            list.add(stdnt);
        }
        return list;
    }

    private static String quote(String s) {
        return " '" + s + "' ";
    }

    public static void removeGuest(String NID, String studentID) throws Exception{
         String query = "DELETE FROM " + Table.Allowed_Guest + " WHERE NID = " + NID + " AND STUDENT_ID = " + studentID;
         System.out.println("=-->Inside removeGuest..query is\n" + query);
         Statement st = con.createStatement();
         st.execute(query);
         
    }

}
