package hall_management.db.queries.more_queries;

import hall_management.db.connection.MyConnection;
import hall_management.db.queries.Query;
import hall_management.ui.startPage.Main;
import hall_management.ui.student.formingTeamApply.Students_OfThisHall;
import hall_management.ui.teacher.upcomingEvents.Student_Games;
import hall_management.util.Interface.Table;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Team_Queries {

    public static String QUERY_SUCCESS = "QUERY_SUCCESS";
    public static String QUERY_FAILURE = "QUERY_FAILURE";
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

    static String makeCurrentDate() {
        String s = getCurrentDate();
        s = "TO_DATE('" + s + "', 'dd/mm/yyyy')";
        return s;
    }

    static String makeDate(String date) {
        String s = date;
        s = "TO_DATE('" + s + "', 'dd/mm/yyyy')";
        return s;
    }

    static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String s = dateFormat.format(cal.getTime());
        return s;
    }

    static String getCurrentYear() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Calendar cal = Calendar.getInstance();
        String s = dateFormat.format(cal.getTime());
        return s;
    }

    public static boolean checkIfTeamExists_interHall(String teamName, String teamYear, String teamSport, String hallID) throws Exception {
        if (teamName == null) {
            return false;
        }
        String query = "SELECT * FROM " + Table.Team + " WHERE TEAM_NAME = "
                + quote(teamName)
                + " AND TEAM_YEAR = "
                + quote(teamYear)
                + " AND UPPER(TEAM_SPORT) = "
                + quote(teamSport)
                + " AND TEAM_TYPE = "
                + quote("LOCAL")
                + " AND HALL_ID = "
                + quote(hallID);
        System.out.println("=->Inside Team_Queries.checkIfTeamExsits_interHall... query is \n" + query);
        Statement st = con.createStatement();
        st.execute(query);
        ResultSet rs = st.getResultSet();

        return rs.next();
    }

    public static void insertTeam_interHall(String team_name, String team_yr, String sportType, String hallID, String captainStudentID) throws Exception {
        String query = null;
        query = "INSERT INTO " + Table.Team + " VALUES ( "
                + quote(getNextTeamID()) + " , "
                + quote(team_name) + " , "
                + quote(team_yr) + " , "
                + quote(sportType) + " , "
                + quote("LOCAL") + " , "
                + quote(hallID) + " , "
                + quote(captainStudentID)
                + " )";

        System.out.println("==-->>INSIDE Team_Queries.insertTeam.. Query is \n" + query);
        Statement st = con.createStatement();
        st.execute(query);
    }

    public static void insertStudentFormsTeam(String team_name, String team_yr, String[] player_ids, String[] player_positions,
            String hallID, String sportType, String teamType)
            throws Exception {

//        System.out.println("=>Inside Team_Queries.insertStudentFormsTeam.. ");
        for (int i = 1; i < player_ids.length; i++) {
            insertStudentFormsTeam(team_name, team_yr, player_ids[i], player_positions[i], hallID, sportType, teamType);
        }

    }

    private static String findTeamID(String teamName, String teamYear, String teamSport, String hallID) throws Exception {
        String query = "SELECT TEAM_ID FROM " + Table.Team + " WHERE TEAM_NAME = "
                + quote(teamName)
                + " AND TEAM_YEAR = "
                + quote(teamYear)
                + " AND UPPER(TEAM_SPORT) = "
                + quote(teamSport)
                + " AND TEAM_TYPE = "
                + quote("LOCAL")
                + " AND HALL_ID = "
                + quote(hallID);
        System.out.println("=-->>FindTeamID.. query is \n" + query);
        Statement st = con.createStatement();
        st.execute(query);
        ResultSet rs = st.getResultSet();
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static void insertStudentFormsTeam(String team_name, String team_yr, String player_id,
            String player_position, String hallID, String sportType, String teamType)
            throws Exception {
        String team_id = "NULL";
        if (teamType.equals("LOCAL")) {
            team_id = findTeamID(team_name, team_yr, sportType, hallID);
        } else {
            team_id = findTeamID(team_name, team_yr, sportType);
        }
        if (team_id.equals("NULL")) {
            return;
        }
        String query = null;
        Statement st = con.createStatement();
        query = "INSERT INTO " + Table.Student_Forms_Team + " VALUES ( "
                + quote(player_id) + " , "
                + quote(team_id) + " , "
                + quote(player_position)
                + " )";
        System.out.println("=-->>insertStudentFormsTeam.. EXECUTING QUERY .. \n" + query);
        st.execute(query);
    }

    public static String getNextTeamID() throws Exception {
//        String query = "SELECT MAX(TEAM_ID) + 1 FROM " + Table.Team;
        String query = "SELECT TEAM_ID FROM " + Table.Team + " ";
        Statement st = con.createStatement();
        st.execute(query);
        ResultSet rs = st.getResultSet();
//        if(rs.next() == false){
//            return "1";
//        }
        List<Integer> list = new ArrayList<>();
        while (rs.next()) {
//            System.out.println("rs.getString(1) = " + rs.getString(1));
            list.add(Integer.parseInt(rs.getString(1)));
        }
        if (list.isEmpty()) {
            return "1";
        }
        int maxID = -1;
        for (int i = 0; i < list.size(); i++) {
            if (maxID <= list.get(i)) {
                maxID = list.get(i);
            }
        }
        maxID++;
        return String.valueOf(maxID);
    }

    public static boolean didThisPersonRegisterForChess(String studentID, String hallID, String currentYear, String chess) throws Exception {
        return Team_Queries.checkIfTeamExists_interHall(makeSingleTeamNameForChess(studentID), currentYear, ("CHESS"), hallID);
    }

    public static String makeSingleTeamNameForChess(String studentID) {
        String teamName = "SINGLE_CHESS_" + studentID;
        return teamName;
    }

    public static List<Students_OfThisHall> getStudentsOfHallAsStudent(String studentID) throws Exception {
        List<Students_OfThisHall> list = new ArrayList<>();

        Students_OfThisHall student = new Students_OfThisHall();

        String query = "SELECT ID, FIRST_NAME || ' ' || LAST_NAME, TYPE FROM TABLE_STUDENT NATURAL JOIN TABLE_HALL WHERE HALL_ID = "
                + " ( SELECT HALL_ID FROM TABLE_STUDENT WHERE ID = " + studentID + " ) "
                + " AND ID <> " + quote(studentID)
                + " ORDER BY TYPE DESC , ID ASC";
        Statement st = con.createStatement();
        st.execute(query);

        ResultSet rs = st.getResultSet();

        while (rs.next()) {
            list.add(new Students_OfThisHall(rs.getString(1), rs.getString(2), rs.getString(3)));
        }

        return list;
    }

    public static Student_Games getStudentOfChessFromTeamID(String teamID) throws Exception {
        Student_Games student = null;

        String query = "SELECT STUDENT_ID, FIRST_NAME||' ' ||LAST_NAME AS \"FULL NAME\", TYPE, DESIGNATION,\n"
                + "(SELECT TABLE_HALL.HALL_NAME FROM TABLE_HALL WHERE TABLE_HALL.HALL_ID = TABLE_STUDENT.HALL_ID)\n"
                + ",CONTACT_NO, TABLE_TEAM.TEAM_YEAR\n"
                + "FROM TABLE_STUDENT, TABLE_STUDENT_FORMS_TEAM, TABLE_TEAM\n"
                + "WHERE TABLE_STUDENT.ID = TABLE_STUDENT_FORMS_TEAM.STUDENT_ID\n"
                + "AND TABLE_STUDENT_FORMS_TEAM.TEAM_ID = TABLE_TEAM.TEAM_ID\n"
                + "AND TABLE_STUDENT_FORMS_TEAM.TEAM_ID =  " + teamID;
        Statement st = con.createStatement();
        st.execute(query);
        System.out.println("==>>>Team_Queries.getStudentOfChesFromTeamID... query is \n" + query);
        ResultSet rs = st.getResultSet();

        if (rs.next()) {
            student = new Student_Games(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
        }
        System.out.println("=->>RETURNING STUDENT (FOR CHESS)\n" + student.toString());
        System.out.println("=-----------------------------***********------------------------=");
        return student;
    }

    public static List<Student_Games> getListOfStudentsFromTeamID(String teamID) throws Exception {
        List<Student_Games> list = new ArrayList<>();
        Student_Games student = null;

        String query = "SELECT STUDENT_ID, FIRST_NAME||' ' ||LAST_NAME AS \"FULL NAME\", TYPE, DESIGNATION,\n"
                + "(SELECT TABLE_HALL.HALL_NAME FROM TABLE_HALL WHERE TABLE_HALL.HALL_ID = TABLE_STUDENT.HALL_ID)\n"
                + ",CONTACT_NO, TABLE_TEAM.TEAM_YEAR\n"
                + "FROM TABLE_STUDENT, TABLE_STUDENT_FORMS_TEAM, TABLE_TEAM\n"
                + "WHERE TABLE_STUDENT.ID = TABLE_STUDENT_FORMS_TEAM.STUDENT_ID\n"
                + "AND TABLE_STUDENT_FORMS_TEAM.TEAM_ID = TABLE_TEAM.TEAM_ID\n"
                + "AND TABLE_STUDENT_FORMS_TEAM.TEAM_ID =  " + teamID + ""
                + "  ORDER BY TABLE_STUDENT.TYPE ASC";

        Statement st = con.createStatement();
        st.execute(query);
        System.out.println("==>>>Team_Queries.getStudentOfChesFromTeamID... query is \n" + query);
        ResultSet rs = st.getResultSet();

        while (rs.next()) {
            list.add(new Student_Games(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
        }
        System.out.println("=->>RETURNING LIST (FOR TEAM ID : " + teamID + ") .. printing list size = " + list.size());
        list.forEach((std) -> {
            System.out.println(std.toString());
        });
        System.out.println("=-----------------------------***********------------------------=");

        return list;
    }

    public static String getFieldOfTeam(String tableName, String fieldName, String eventOrTeamIDQualifier, String idValue) throws Exception {
        String query = "SELECT " + fieldName + " FROM " + tableName + " WHERE "
                + eventOrTeamIDQualifier + " = " + idValue;
        Statement st = con.createStatement();
        System.out.println("=->> Inside Event_Queries.getFieldOfEvent.. query is \n" + query);
        st.execute(query);
        ResultSet rs = st.getResultSet();
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static String addTeamToEvent(String teamID, String eventID, String status) throws Exception {
        String query = "SELECT * FROM " + Table.Team_Event_History + " WHERE "
                + "TEAM_ID = " + teamID
                + " AND EVENT_ID = " + eventID;
        System.out.println("=->>INSIDE TEAM_QUERIES.addTeamToEvent.. query is \n" + query);
        Statement st = con.createStatement();
        st.execute(query);
        ResultSet rs = st.getResultSet();
        if (rs.next()) {
            System.out.println("=->ALREADY EXISTS SO DONT ADD NO MORE..");
            return QUERY_FAILURE;
        }
        System.out.println("=->THIS IS UNIQUE SO ADDING...");
        query = "INSERT INTO TABLE_TEAM_EVENT_HISTORY(TEAM_ID,EVENT_ID,STATUS)  \n"
                + "SELECT  " + quote(teamID)
                + "  ,  "
                + "   " + quote(eventID)
                + " ,  "
                + "   " + quote(status)
                + "  from dual"; //semicolon ok run de?
//        query = "INSERT INTO " + Table.Team_Event_History
//                + "  VALUES( "
//                + quote(teamID) + " , "
//                + quote(eventID) + " , "
//                + quote(status) + " "
//                + " )";

        System.out.println("=->QUERY IS \n" + query);
        st.execute(query);
        System.out.println("=->RETURINIG QUERY_SUCCESS");
        System.out.println("----------**********---------------");
        return QUERY_SUCCESS;
    }

    public static String deleteTeam(String teamID) throws Exception {
        String query = "DELETE FROM " + Table.Team + " WHERE TEAM_ID = " + teamID;

        Statement st = con.createStatement();
        System.out.println("=->EXECUTING QUERY.. \n" + query);
        st.execute(query);
        System.out.println("=->RETURINIG QUERY_SUCCESS");
        System.out.println("----------**********---------------");
        return QUERY_SUCCESS;
    }

    public static boolean checkIfTeamExists_Global(String teamName, String teamYear, String teamSport) throws Exception {
        if (teamName == null) {
            return false;
        }
        String query = "SELECT * FROM " + Table.Team + " WHERE TEAM_NAME = "
                + quote(teamName)
                + " AND TEAM_YEAR = "
                + quote(teamYear)
                + " AND UPPER(TEAM_SPORT) = "
                + quote(teamSport)
                + " AND TEAM_TYPE = "
                + quote("GLOBAL");

        System.out.println("=->Inside Team_Queries.checkIfTeamExists_Global... query is \n" + query);
        Statement st = con.createStatement();
        st.execute(query);
        ResultSet rs = st.getResultSet();

        return rs.next();
    }

    public static void insertTeam_Global(String team_name, String team_yr, String sportType, String hallID, String captainID) throws Exception {
        String query = null;
        query = "INSERT INTO " + Table.Team + " VALUES ( "
                + quote(getNextTeamID()) + " , "
                + quote(team_name) + " , "
                + quote(team_yr) + " , "
                + quote(sportType) + " , "
                + quote("GLOBAL") + " , "
                + quote(hallID) + " , "
                + quote(captainID)
                + " )";

        System.out.println("==-->>INSIDE Team_Queries.insertTeam_Global.. Query is \n" + query);
        Statement st = con.createStatement();
        st.execute(query);
    }

    private static String findTeamID(String team_name, String team_yr, String sportType) throws Exception {
        String query = "SELECT TEAM_ID FROM " + Table.Team + " WHERE TEAM_NAME = "
                + quote(team_name)
                + " AND TEAM_YEAR = "
                + quote(team_yr)
                + " AND UPPER(TEAM_SPORT) = "
                + quote(sportType)
                + " AND TEAM_TYPE = "
                + quote("GLOBAL");
        System.out.println("=-->>FindTeamID.. query is \n" + query);
        Statement st = con.createStatement();
        st.execute(query);
        ResultSet rs = st.getResultSet();
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static boolean didThisPersonRegisterForChess(String studentID, String hallID, String currentYear, String chess, String global) throws Exception {
        return Team_Queries.checkIfTeamExists_Global(makeSingleTeamNameForChess(studentID), currentYear, ("CHESS"));
    }

    public static String getPositionOfTeam(String team_id, String event_id) throws Exception {
        String query = "SELECT * FROM TABLE_EVENT WHERE WINNER_TEAM_ID = " + team_id + " AND EVENT_ID =  " + event_id;
        Statement st = con.createStatement();
        System.out.println("==-->>INSIDE Team_Queries.getPositionOfTeam .. query is\n" + query);
        st.execute(query);
        ResultSet rs = st.getResultSet();
        String s = "N.A.";
        if (rs.next() == true) {
            s = "WINNER";
        }
        query = "SELECT * FROM TABLE_EVENT WHERE RUNNERUP_TEAM_ID = " + team_id + " AND EVENT_ID =  " + event_id;
        st.execute(query);
        rs = st.getResultSet();
        if (rs.next() == true) {
            s = "RUNNER UP";
        }
        return s;
    }

    public static String findTeamID(String eventID, String studentID) throws Exception {
        String query = "SELECT TABLE_TEAM.TEAM_ID\n"
                + "FROM TABLE_EVENT , TABLE_TEAM , TABLE_STUDENT_FORMS_TEAM\n"
                + "WHERE TABLE_EVENT.EVENT_YEAR = TABLE_TEAM.TEAM_YEAR AND TABLE_EVENT.EVENT_SPORT = TABLE_TEAM.TEAM_SPORT\n"
                + "AND TABLE_STUDENT_FORMS_TEAM.TEAM_ID = TABLE_TEAM.TEAM_ID\n"
                + "AND TABLE_EVENT.EVENT_STATUS = 'OVER'\n"
                + "AND TABLE_STUDENT_FORMS_TEAM.STUDENT_ID = " + studentID;
        System.out.println("=-->>TEAM_QUERIES.FindTeamID.. query is \n" + query);
        Statement st = con.createStatement();
        st.execute(query);
        ResultSet rs = st.getResultSet();
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static String getTeamNameOfStudentFromEvent(String studentID, String eventID_participated) throws Exception {
        String query = "SELECT TABLE_TEAM.TEAM_NAME\n"
                + "FROM TABLE_EVENT , TABLE_TEAM , TABLE_STUDENT_FORMS_TEAM\n"
                + "WHERE TABLE_EVENT.EVENT_YEAR = TABLE_TEAM.TEAM_YEAR AND TABLE_EVENT.EVENT_SPORT = TABLE_TEAM.TEAM_SPORT\n"
                + "AND TABLE_STUDENT_FORMS_TEAM.TEAM_ID = TABLE_TEAM.TEAM_ID\n"
                + "AND TABLE_EVENT.EVENT_STATUS = 'OVER'\n"
                + "AND TABLE_STUDENT_FORMS_TEAM.STUDENT_ID = " + studentID;
        System.out.println("=-->>TEAM_QUERIES.FindTeamID.. query is \n" + query);
        Statement st = con.createStatement();
        st.execute(query);
        ResultSet rs = st.getResultSet();
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static List<Student_Games> getListOfStudentsUpcomingEvent(String sportType, String teamType) throws Exception {
        List<Student_Games> list = new ArrayList<>();
        Student_Games student = null;

        String query = "SELECT ST.ID , ST.FIRST_NAME || ' ' || ST.LAST_NAME , ST.TYPE, STT.DESIGNATION,\n"
                + "(SELECT HALL_NAME FROM TABLE_HALL WHERE TABLE_HALL.HALL_ID = ST.HALL_ID),\n"
                + "ST.CONTACT_NO, T.TEAM_YEAR, T.TEAM_ID\n"
                + "FROM TABLE_TEAM T, TABLE_STUDENT_FORMS_TEAM STT , TABLE_STUDENT ST \n"
                + "WHERE T.TEAM_ID = STT.TEAM_ID AND ST.ID = STT.STUDENT_ID \n"
                + "AND T.TEAM_YEAR = " + quote(getCurrentYear()) + " \n"
                + "AND T.TEAM_SPORT = " + quote(sportType) + " \n"
                + "AND T.TEAM_TYPE = " + quote(teamType) + "\n"
                + "AND " + Main.studentID + " IN (\n"
                + "	SELECT T1.STUDENT_ID \n"
                + "	FROM TABLE_STUDENT_FORMS_TEAM T1\n"
                + "	WHERE STT.TEAM_ID = T1.TEAM_ID \n"
                + ")";

        Statement st = con.createStatement();
        st.execute(query);
        System.out.println("==>>>Team_Queries.getListOfStudentsUpcomingEvent... query is \n" + query);
        ResultSet rs = st.getResultSet();
        String team_id_first = null;
//        if(rs.next()){
//            team_id_first = rs.getString(rs.getString(8));
//            list.add(new Student_Games(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));            
//        }
        while (rs.next()) {
//            if(team_id_first.equals(rs.getString(rs.getString(8))) == false)
//                break;
            list.add(new Student_Games(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            
        }
//        System.out.println("=->>RETURNING LIST (FOR TEAM ID : " + teamID + ") .. printing list size = " + list.size());
        list.forEach((std) -> {
            System.out.println(std.toString());
        });
        System.out.println("=-----------------------------***********------------------------=");

        return list;
    }
}
