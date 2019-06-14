package hall_management.db.queries;

import hall_management.db.connection.MyConnection;
import static hall_management.db.queries.Application_Queries.con;
import hall_management.ui.student.applySeat.AvailableRoomList;
import hall_management.util.Interface.Table;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Staff_Queries {

    public static final MyConnection connectionObject = new MyConnection();
    public static final Connection con = connectionObject.getConnection();

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

    public static String findFieldOf(String tableName, String staffID, String fieldToRetrieveName) throws Exception {
        String colValue = null;
        String query = null;
        if (tableName.toUpperCase().contains("STAFF")) {
            query = "SELECT " + fieldToRetrieveName + " FROM " + tableName + " WHERE STAFF_ID = " + staffID;
        } else if (tableName.toUpperCase().contains("STUDENT")) {
            query = "SELECT " + fieldToRetrieveName + " FROM " + tableName + " WHERE ID = " + staffID;
        } else if (tableName.toUpperCase().contains("GUEST")) {
            query = "SELECT " + fieldToRetrieveName + " FROM " + tableName + " WHERE NID = " + staffID;
        } else {
            query = "SELECT " + fieldToRetrieveName + " FROM " + tableName + " WHERE ID = " + staffID;
        }
        Statement st = con.createStatement();
        System.out.println("<><>Inside Staff_Queries.findFieldOf(...) => Query is  " + query);
        st.execute(query);

        ResultSet rs = st.getResultSet();

        if (rs.next()) {
            colValue = rs.getString(1);
        }

        return colValue;
    }

    public static List<String> findList(String tableName, String whatToRetrieve, String columnNameToMatch, String columnValueGivenAsInput) throws Exception {
        List<String> list = new ArrayList<>();

        String query = null;
        if (tableName.toUpperCase().contains("STUDENT")) {
            query = "SELECT " + whatToRetrieve + " FROM " + tableName + " WHERE " + columnNameToMatch + " = " + columnValueGivenAsInput
                    + " ORDER BY " + "ID" + " ASC";
        } else if (tableName.toUpperCase().contains("GUEST")) {
            query = "SELECT " + whatToRetrieve + " FROM " + tableName + " WHERE " + columnNameToMatch + " = " + columnValueGivenAsInput
                    + " ORDER BY " + "NID" + " ASC";
        }
        else {
            query = "SELECT " + whatToRetrieve + " FROM " + tableName + " WHERE " + columnNameToMatch + " = " + columnValueGivenAsInput 
                + " ORDER BY " + whatToRetrieve + " ASC";            
        }

        System.out.println("INSIDE Staff_Queries.findList(...) ... query is  " + query);
        Statement st = con.createStatement();
        st.execute(query);
        ResultSet rs = st.getResultSet();
        String s;
        while (rs.next()) {
            s = new String();
            s = rs.getString(1);
            list.add(s);
        }

        return list;
    }

    public static void addToGuestLog(String guestNID, String studentID, String visitingDate, String startTime, String endTime) {
        String startingTime = "TO_DATE('" + visitingDate + " " + startTime + "','DD/MM/YYYY HH24:MI:SS')";
        String endingTime = "TO_DATE('" + visitingDate + " " + endTime + "','DD/MM/YYYY HH24:MI:SS')";
        String query = "INSERT INTO TABLE_GUEST_LOG VALUES( "
                + guestNID + " , "
                + studentID + " , "
                + startingTime + " , "
                + endingTime
                + " )";
        System.out.println("Inside Staff_Queries.addToGuestLog.. \n Query is \n");
        System.out.println(query);
        System.out.println("<><>EXECUTING QUERY YET!");
        try {
            Statement st = con.createStatement();
            st.execute(query);
            System.out.println("<><><>QUERY IS EXECUTED!!");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /*
    public static List<String> findListUsingColumn
        (String tableName, String whatToRetrieve, String columnNameToMatch, String columnValueGivenAsInput) throws Exception{
        List<String> list = new ArrayList<>();
        
        String query = null;
        query = "SELECT " + whatToRetrieve + " FROM " + tableName + " WHERE " + columnNameToMatch + " = " + columnValueGivenAsInput ;
        System.out.println("INSIDE Staff_Queries.findList(...) ... query is  " + query);
        Statement st = con.createStatement();
        st.execute(query);
        ResultSet rs = st.getResultSet();
        String s;
        while(rs.next()){
            s = new String();
            s = rs.getString(1);
            list.add(s);
        }
        
        return list;
    }
     */
    public static String getNameOfStudentFromHall(String tableName, String receivedStudentID, String hallID) throws Exception {
        String s = null;

        String query = "SELECT FIRST_NAME || ' ' || LAST_NAME FROM " + Table.Student + " WHERE ID = " + receivedStudentID + " AND HALL_ID = " + hallID;
        Statement st = con.createStatement();
        st.execute(query);

        ResultSet rs = st.getResultSet();

        if (rs.next()) {
            s = rs.getString(1);
        }

        return s;
    }

}
