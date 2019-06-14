package hall_management.db.queries;

import hall_management.db.connection.MyConnection;
import hall_management.ui.student.applySeat.AvailableRoomList;
import hall_management.ui.student.seeApplicationLog.Application_With_Button;
import hall_management.ui.student.seeApplicationLog.Application_Entity;
import hall_management.ui.student.seeRoomList.Application_Rooms;
import hall_management.ui.teacher.applications.Application_For_Teacher;
import hall_management.util.Interface.Table;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Application_Queries {

    public static final MyConnection connectionObject = new MyConnection();
    public static final Connection con = connectionObject.getConnection();

    public static String getNextApplicationID() throws Exception {
        System.out.println("<><><><>INSIDE Application_Queries.getNextApplicationID() Function ... ");

        String query = "SELECT COUNT(APPLICATION_ID) + 1"
                + " FROM " + Table.Application;
        System.out.println("Query is " + query);
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        String app_id = null;
        if (rs.next()) {
            app_id = rs.getString(1);
        }
        System.out.println("==>>RETURNING " + app_id + "\n------*****-------\n");
        return app_id;
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String s = dateFormat.format(cal.getTime());
        return s;
    }

    public static List<AvailableRoomList> getListOfAvailableRooms(String id) throws Exception {
        System.out.println("\n==>>>Inside Application_Queries.getListOfAvailableRooms(id).... ");
        List<AvailableRoomList> list = new ArrayList<>();

//        MyConnection myCon = new MyConnection();
//        Connection con = myCon.getConnection();
        String query = "SELECT HALL_ID, ROOM_NO, ROOM_CAPACITY, \"current no of residents\" FROM TABLE_ROOM\n"
                + "WHERE \"current no of residents\" < ROOM_CAPACITY\n"
                + "AND HALL_ID = (\n"
                + "	SELECT TABLE_STUDENT.HALL_ID\n"
                + "	FROM TABLE_STUDENT\n"
                + "	WHERE TABLE_STUDENT.ID = " + id
                + "\n )";
        System.out.println("Query is : \n" + query);
        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

//        String []info = new String[columnsNumber];
        String[] str = new String[columnsNumber];
//        System.out.println("COL NUM = " + columnsNumber);
        AvailableRoomList availableRoom = new AvailableRoomList();
        int indx = 0;
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {

//                info[i - 1] = rs.getString(i);
//                System.out.print(rs.getString(i) + " <> "); //Print one element of a row
                str[i - 1] = rs.getString(i);
//                System.out.println("str[i-1] = " + str[i-1]);
            }
            availableRoom = new AvailableRoomList();
            availableRoom.setHall_id(str[0]);
            availableRoom.setRoom_no(str[1]);
            availableRoom.setRoom_capacity(str[2]);
            availableRoom.setCurrent_no_ppl(str[3]);

//            System.out.println("****----****");
//            System.out.println(availableRoom);
//            System.out.println("****----****");
            list.add(availableRoom);
//            System.out.println(allowedGuest);
        }

//        System.out.println("===>>> LIST ... Size = " + list.size());
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//        System.out.println("<><><><>RETURNING LIST... \n");
        return list;

    }

    public static void insert_into_Room_List(String applicationID, String hall_id, String room_no) throws Exception {
        String query = "INSERT INTO TABLE_APPLICATION_ROOM_LIST "
                + "VALUES ( "
                + applicationID + " , "
                + hall_id + " , "
                + room_no
                + ")";
        System.out.println("==-->> Inside Application_Queries.insert_into_Room_List(....) .. Query is .. \n" + query);
        Statement statement = con.createStatement();
        boolean execute = statement.execute(query);
        System.out.println("<><><>RETURNING ... \n\n");
    }

    public static void insert_Into_Application(String applicationID, String studentID, String hall_id, String application_date, char has_ROOM, String current_room_no)
            throws Exception {
        String query = null;
        if (current_room_no == null || current_room_no.equals("")) {
            query = "INSERT INTO TABLE_APPLICATION(APPLICATION_ID, STUDENT_ID, HALL_ID, APPLICATION_DATE, HAS_ROOM) \n"
                    + "VALUES ( "
                    + applicationID + " , "
                    + studentID + " , "
                    + hall_id + " , "
                    + "TO_DATE( '" + application_date + "' , 'dd/MM/yyyy'" + ") , "
                    + "'" + has_ROOM + "'"
                    + " )";
        } else {
            query = "INSERT INTO TABLE_APPLICATION(APPLICATION_ID, STUDENT_ID, HALL_ID, APPLICATION_DATE, HAS_ROOM, CURRENT_ROOMID) \n"
                    + "VALUES ( "
                    + applicationID + " , "
                    + studentID + " , "
                    + hall_id + " , "
                    + "TO_DATE( '" + application_date + "' , 'dd/MM/yyyy'" + ") , "
                    + "'" + has_ROOM + "'" + " , "
                    + current_room_no
                    + " )";
        }
        System.out.println("==-->> Inside Application_Queries.insert_Into_Application(....) .. Query is .. \n" + query);
        Statement statement = con.createStatement();
        boolean execute = statement.execute(query);
        System.out.println("<><><>RETURNING ... \n\n");
//        System.out.println("");
    }

    public static List<Application_Entity> loadApplicationsOfThisStudent(String studentID) throws Exception {
        List<Application_Entity> list = new ArrayList<>();
        String query = null;

        query = "select APPLICATION_ID, STUDENT_ID, TO_CHAR(APPLICATION_DATE, 'DD/MM/YYYY'), TO_CHAR(VERDICT_DATE, 'DD/MM/YYYY'),\n"
                + "CURRENT_ROOMID, HAS_ROOM, STATUS, ALLOTTED_ROOM_NO\n"
                + "from TABLE_APPLICATION where STUDENT_ID = " + studentID + " order by APPLICATION_ID";

        System.out.println("<><><>Inside Application_Queries.loadApplicationsOfThisStudent(id)... Query is");
        System.out.println(query);

        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        Application_Entity app = new Application_Entity();

        String[] arr = new String[columnsNumber];
//        Printer.printLine();
        while (rs.next()) {

            for (int i = 1; i <= columnsNumber; i++) {
                arr[i - 1] = rs.getString(i);
//                System.out.print(rs.getString(i) + " <> ");
            }

            app = new Application_Entity();
            app.setApplicationID(arr[0]);
            app.setStudentId(arr[1]);
            app.setApplicationDate(arr[2]);
            app.setVerdictDate(arr[3]);
            app.setCurrentRoomID(arr[4]);
            app.setHasRoom(arr[5]);
            app.setApplicationStatus(arr[6]);
            app.setAllotted_room_number(arr[7]);

//            app.button_see_Application_Room_List.setText("See App. Room(s)");
//            app.button_see_Application_Room_List = new Button("See App. Room(s)");
//            app.setButton_see_Application_Room_List(new Button("See App. Room(s)"));
            list.add(app);
//            Printer.printLine();
        }

//        Printer.printList(list);
//        List<Application> list2 = new ArrayList<>();
        return list;
    }

    public static List<Application_Rooms> getApplicationRoomNumber(String applicationID) throws Exception {
        List<Application_Rooms> list = new ArrayList<>();
        String query = "select ROOM_NO from TABLE_APPLICATION_ROOM_LIST"
                + " where APPLICATION_ID = " + applicationID
                + "  order by ROOM_NO";
        System.out.println("<><><>Inside Application_Queries.getApplicationRoomNumber(app_id)... Query is");
        System.out.println(query);

        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        Application_Rooms app_Room = new Application_Rooms();

        while (rs.next()) {
            app_Room = new Application_Rooms();
            app_Room.setRoom_no(rs.getString(1));
            list.add(app_Room);
        }

        return list;
    }

    public static List<Application_Entity> getApplicationsUnderTeacher(String teacherID) throws Exception {
        List<Application_Entity> list = new ArrayList<>();
        String query = "select APPLICATION_ID, STUDENT_ID, HALL_ID , TO_CHAR(APPLICATION_DATE, 'dd/mm/yyyy'), TO_CHAR(VERDICT_DATE, 'dd/mm/yyyy'),\n"
                + "CURRENT_ROOMID, HAS_ROOM, STATUS, ALLOTTED_ROOM_NO\n"
                + "from TABLE_APPLICATION\n"
                + "where UPPER(STATUS) = 'PENDING' "
//                + " AND TABLE_APPLICATION.HALL_ID = find_hall_id (" + teacherID + ")";
                + " AND TABLE_APPLICATION.HALL_ID = '" + Functions.getHallID(teacherID) + "'";
        System.out.println("=-->>> Inside Application_Queries.getApplicationsUnderTeacher(" + teacherID +").. printing query. ");
        System.out.println(query + "\n----------------***********-----------------");
        Statement st = con.createStatement();
        st.executeQuery(query);
        ResultSet rs = st.getResultSet();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsCount = rsmd.getColumnCount();
        Application_Entity app = new Application_Entity();
        while (rs.next()) {
            app = new Application_Entity();
            app.setApplicationID(rs.getString(1));
            app.setStudentId(rs.getString(2));
            app.setHallID(rs.getString(3));
            app.setApplicationDate(rs.getString(4));
            app.setVerdictDate(rs.getString(5));
            app.setCurrentRoomID(rs.getString(6));
            app.setHasRoom(rs.getString(7));
            app.setApplicationStatus(rs.getString(8));
            app.setAllotted_room_number(rs.getString(9));
            list.add(app);
//                System.out.print(rs.getString(i) + "\t");
//            System.out.println("\n<><><>");
        }

        return list;
    }

    /*public static void updateApplicationStatusToAccepted(String applicationID) throws Exception {
        String query = null;
        query = "UPDATE " + Table.Application + " SET STATUS = 'ACCEPTED' WHERE APPLICATION_ID = " + applicationID;
        Statement st = con.createStatement();
        System.out.println("QUERY IS " + query);
        System.out.println("<><><>BEFORE EXECUTING(Query).. ");
//        st.execute(query);
    }*/
    public static String getApplicationStatus(String applicationID) throws Exception {
        String query = "SELECT STATUS FROM " + Table.Application + " WHERE APPLICATION_ID = " + applicationID;
        Statement st = con.createStatement();
        st.execute(query);
        ResultSet rs = st.getResultSet();
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;

    }

    public static void updateApplicationField(String applicationID, String fieldName, String fieldValue) throws Exception {
        String query = null;
//        query = "UPDATE " + Table.Application + " SET STATUS = 'ACCEPTED' WHERE APPLICATION_ID = " + applicationID;
        query = "UPDATE " + Table.Application + " SET " + fieldName + " = " + " '" + fieldValue + "' WHERE APPLICATION_ID = " + applicationID;
        Statement st = con.createStatement();
        System.out.println("QUERY IS " + query);
        System.out.println("<><><>BEFORE EXECUTING(Query).. ");
        st.execute(query);
    }

    public static List<AvailableRoomList> getListOfRoomsForThisApplicationID(String applicationID) throws Exception {
        System.out.println("\n==>>>Inside Application_Queries.getListOfRoomsForThisApplicationID(applicationID).... ");
        List<AvailableRoomList> list = new ArrayList<>();

//        MyConnection myCon = new MyConnection();
//        Connection con = myCon.getConnection();
        String query = "SELECT L.ROOM_NO, ROOM_CAPACITY, \"current no of residents\"\n"
                + "FROM TABLE_APPLICATION_ROOM_LIST L, TABLE_ROOM R\n"
                + "WHERE ( L.HALL_ID = R.HALL_ID ) AND  (L.ROOM_NO = R.ROOM_NO )\n"
                + "AND L.APPLICATION_ID = " + applicationID;
        System.out.println("Query is : \n" + query);
        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery(query);

        AvailableRoomList room;
        while (rs.next()) {
            room = new AvailableRoomList();
            room.setRoom_no(rs.getString(1));
            room.setRoom_capacity(rs.getString(2));
            room.setCurrent_no_ppl(rs.getString(3));
            list.add(room);
        }
        return list;

    }

    public static List<AvailableRoomList> getListOfRoomsForThisHallID(String hallID) throws Exception {
        System.out.println("\n==>>>Inside Application_Queries.getListOfRoomsForThisApplicationID(applicationID).... ");
        List<AvailableRoomList> list = new ArrayList<>();

        String query = "select ROOM_NO, ROOM_CAPACITY, \"current no of residents\" from TABLE_ROOM\n"
                + "where HALL_ID = " + hallID
                + "  and \"current no of residents\" < ROOM_CAPACITY";

        System.out.println("Query is : \n" + query);
        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery(query);

        AvailableRoomList room;
        while (rs.next()) {
            //Total 3 columns
            room = new AvailableRoomList();
            room.setRoom_no(rs.getString(1));
            room.setRoom_capacity(rs.getString(2));
            room.setCurrent_no_ppl(rs.getString(3));
            list.add(room);
        }

        return list;
    }
//    private static void insertIntoRoomHistory(String studentID, String startDate, String endDate){
//        
//    }
    public static void updateApplicationFieldForDate(String applicationID, String fieldName, String fieldValue) throws Exception {
        String query = null;
//        query = "UPDATE " + Table.Application + " SET STATUS = 'ACCEPTED' WHERE APPLICATION_ID = " + applicationID;
        query = "UPDATE " + Table.Application + " SET " + fieldName + " = " + fieldValue + " WHERE APPLICATION_ID = " + applicationID;
        Statement st = con.createStatement();
        System.out.println("QUERY IS " + query);
        System.out.println("<><><>BEFORE EXECUTING(Query).. ");
        st.execute(query);
    }
    public static String makeCurrentDate() {
        String s = Application_Queries.getCurrentDate();
        s = "TO_DATE('" + s + "', 'dd/mm/yyyy')";
        return s;
    }

    public static boolean checkWhetherStudentHasPendingApplications(String studentID) throws Exception{
        String query = null;
//        query = "UPDATE " + Table.Application + " SET STATUS = 'ACCEPTED' WHERE APPLICATION_ID = " + applicationID;
        query = "SELECT * FROM " + Table.Application + " WHERE STUDENT_ID = " + (studentID) + " and UPPER(STATUS) = 'PENDING'" ;
        Statement st = con.createStatement();        
        st.execute(query);
        ResultSet rs = st.getResultSet();
        
        return rs.next();
    }
        
    

}
