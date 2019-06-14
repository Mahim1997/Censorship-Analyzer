package hall_management.db.queries.more_queries;

import hall_management.db.connection.MyConnection;
import hall_management.ui.teacher.events.Events;
import hall_management.ui.teacher.upcomingEvents.Teams;
import hall_management.util.Interface.Table;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Event_Queries {

    public static final MyConnection connectionObject = new MyConnection();
    public static final Connection con = connectionObject.getConnection();

    public static String quote(String s) {
        String[] arr = s.split("'");
        if (arr.length <= 1) {
            return " '" + s + "' ";
        } else {
            return s;
        }
    }

    public static String getNextEventID() throws Exception {
    
//        String query = "SELECT MAX(TEAM_ID) + 1 FROM " + Table.Team;
        String query = "SELECT EVENT_ID FROM " + Table.Event + " " ;
        Statement st = con.createStatement();
        st.execute(query);
        ResultSet rs = st.getResultSet();
//        if(rs.next() == false){
//            return "1";
//        }
        List<Integer> list = new ArrayList<>();
        while(rs.next()){
//            System.out.println("rs.getString(1) = " + rs.getString(1));
            list.add(Integer.parseInt(rs.getString(1)));
        }
        if(list.isEmpty())
            return "1";
        int maxID = -1;
        for(int i=0; i<list.size(); i++){
            if(maxID <= list.get(i))
                maxID = list.get(i);
        }
        maxID++ ;
        return String.valueOf(maxID);
    
    }

    public static List<Events> getListOfEventsSupervisedBy(String teacherID, String status) throws SQLException {
        List<Events> list = new ArrayList<>();
        String query = "SELECT * FROM " + Table.Event + " WHERE UPPER(EVENT_STATUS) = " + quote(status) + " AND EVENT_SUPERVISOR_ID = " + teacherID
                + "   ORDER BY EVENT_YEAR ASC ";
        Statement st = con.createStatement();
        System.out.println("=->> Inside Event_Queries.getListOfEventsSupervisedBy.. query is \n" + query);
        st.execute(query);
        ResultSet rs = st.getResultSet();
        Events event = new Events();
        while (rs.next()) {
            list.add(new Events(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                    rs.getString(9), rs.getString(10)));
        }

        return list;
    }

    public static String getFieldOfEvent(String tableName, String fieldName, String eventOrTeamIDQualifier, String idValue) throws Exception {
        String query = "SELECT " + fieldName + " FROM " + tableName + " WHERE "
                + eventOrTeamIDQualifier + " = " + quote(idValue);
        Statement st = con.createStatement();
        System.out.println("=->> Inside Event_Queries.getFieldOfEvent.. query is \n" + query);
        st.execute(query);
        ResultSet rs = st.getResultSet();
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    /*
    String team_id;
    String team_name;
    String team_year;
    String team_sport;
    String team_type;
    String hallID;
    String hallName;
    String captainStudentID;
     */
    public static List<Teams> getListOfTeamsForEventID(String event_id, String status) throws Exception {
        List<Teams> list = new ArrayList<>();
//        String query = "SELECT * FROM " + Table.Team + " WHERE UPPER(EVENT_TYPE) = " + quote(status) + " AND EVENT_SUPERVISOR_ID = " + teacherID;

        String query = "SELECT t.TEAM_ID , t.TEAM_NAME, t.TEAM_YEAR, t.TEAM_SPORT, t.TEAM_TYPE, t.HALL_ID, \n"
                + "(SELECT TABLE_HALL.HALL_NAME FROM TABLE_HALL WHERE TABLE_HALL.HALL_ID = t.HALL_ID), CAPTAIN_STUDENT_ID\n"
                + "FROM TABLE_TEAM t ,TABLE_EVENT e\n"
                + "WHERE (UPPER(t.TEAM_SPORT) = UPPER(e.EVENT_SPORT))"
                + " AND (UPPER(t.TEAM_TYPE) = UPPER(e.EVENT_TYPE)) "
                + " AND (t.TEAM_YEAR = e.EVENT_YEAR)\n"
                + " AND e.EVENT_ID = " + event_id
                + " AND e.EVENT_STATUS = " + quote(status);
        Statement st = con.createStatement();
        System.out.println("=->> Inside Event_Queries.getListOfTeamsForEventID.. query is \n" + query);
        st.execute(query);
        ResultSet rs = st.getResultSet();
        Teams team = new Teams();

        while (rs.next()) {
            team = new Teams(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            list.add(team);
        }

        return list;
    }

    public static boolean doesThisEventExist(String eventName, String eventYear, String eventSport) throws Exception {
        String query = "SELECT * FROM " + Table.Event + " WHERE "
                + " UPPER(EVENT_NAME) = " + quote(eventName.toUpperCase())
                + " AND UPPER(EVENT_YEAR) = " + quote(eventYear.toUpperCase())
                + " AND UPPER(EVENT_SPORT) = " + quote(eventSport.toUpperCase());
        System.out.println("=-->>>INSIDE EVENT_QUERIES.doesThisEventExist... query is\n" + query);
        Statement st = con.createStatement();
//        System.out.println("=->> Inside Event_Queries.doesThisEventExist.. query is \n" + query);
        st.execute(query);
        ResultSet rs = st.getResultSet();
        return rs.next();
    }

    public static String makeEventName(String eventSport, String eventYear, String eventType) {
        String eventName = null;
        if (eventType.toUpperCase().contains("LOCAL")) {
            eventName = eventSport + "_" + eventYear + "_" + "INTERHALL";
        } else {
            eventName = "GLOBAL_" + eventSport + "_" + eventYear;
        }
        System.out.println("=-->>EVENT NAME IS " + eventName + " , RETURNING IT!");

        return eventName;
    }

    public static List<Events> getListOfEventsParticipatedByStudent(String studentID) throws Exception {
//        String query = "SELECT STD.ID, STD.FIRST_NAME || ' ' || STD.LAST_NAME AS \"FULL NAME\", STDTM.TEAM_ID , \n"
//                + "TM.TEAM_YEAR, TM.TEAM_TYPE, TM.TEAM_SPORT, TM.TEAM_NAME ,\n"
//                + "TEV.EVENT_ID\n"
//                + "\n"
//                + "FROM TABLE_STUDENT STD, TABLE_STUDENT_FORMS_TEAM STDTM , TABLE_TEAM TM , TABLE_TEAM_EVENT_HISTORY TEV , TABLE_EVENT EV\n"
//                + "WHERE STD.ID = STDTM.STUDENT_ID AND STDTM.TEAM_ID = TM.TEAM_ID AND TEV.TEAM_ID = TM.TEAM_ID AND TEV.EVENT_ID = EV.EVENT_ID\n"
//                + "AND UPPER(EV.EVENT_STATUS) = 'OVER'\n"
//                + "AND STD.ID = " + studentID;
        String query = "SELECT DISTINCT(TEV.EVENT_ID), EV.EVENT_NAME, EV.EVENT_YEAR, EV.EVENT_SPORT, EV.EVENT_TYPE, EV.EVENT_STATUS, EV.HALL_ID, \n"
                + "EV.EVENT_SUPERVISOR_ID, EV.WINNER_TEAM_ID, EV.RUNNERUP_TEAM_ID\n"
                + "\n"
                + "FROM TABLE_STUDENT STD, TABLE_STUDENT_FORMS_TEAM STDTM , TABLE_TEAM TM , TABLE_TEAM_EVENT_HISTORY TEV , TABLE_EVENT EV\n"
                + "WHERE STD.ID = STDTM.STUDENT_ID AND STDTM.TEAM_ID = TM.TEAM_ID AND TEV.TEAM_ID = TM.TEAM_ID AND TEV.EVENT_ID = EV.EVENT_ID\n"
                + "AND UPPER(EV.EVENT_STATUS) = 'OVER'\n"
                + "AND STD.ID = " + studentID;
//        String query = "";
        Statement st = con.createStatement();
        System.out.println("Inside Event_Queries.getListOfEventsParticipatedByStudent.. query is \n" + query);
        st.execute(query);
        ResultSet rs = st.getResultSet();
        Events event = new Events();
        List<Events> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Events(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                    rs.getString(9), rs.getString(10)));
        }

        return list;

    }

    public static List<Events> getListOfUpcomingEvents() throws Exception {
        String query = "SELECT * FROM " + Table.Event + " WHERE UPPER(EVENT_STATUS) = " + quote("UPCOMING")
                + "   ORDER BY EVENT_YEAR ASC ";

        Statement st = con.createStatement();
        System.out.println("Inside Event_Queries.getListOfEventsParticipatedByStudent.. query is \n" + query);
        st.execute(query);
        ResultSet rs = st.getResultSet();
        Events event = new Events();
        List<Events> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Events(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                    rs.getString(9), rs.getString(10)));
        }

        return list;

    }

    public static void updateFieldOfTable(String tableName, String toBeUpdatedFieldName, String toBeUpdatedFieldValue,
            String verifierName, String verifierValue) throws Exception {
        String query = "UPDATE " + tableName + " SET " + toBeUpdatedFieldName + " = " + quote(toBeUpdatedFieldValue)
                + "  WHERE " + verifierName + " = " + quote(verifierValue);
        Statement st = con.createStatement();
        System.out.println("Inside Event_Queries.updateFieldOfTable.. query is \n" + query);
        st.execute(query);

    }

}
