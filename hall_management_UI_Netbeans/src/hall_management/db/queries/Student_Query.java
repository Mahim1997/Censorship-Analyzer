/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.db.queries;

import hall_management.db.connection.MyConnection;
import hall_management.ui.popup.PopUpWindow;
import hall_management.ui.startPage.Main;
import hall_management.ui.student.seeGuestList.Guest_Log;
import hall_management.util.Interface.Table;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Student_Query {

    private static final MyConnection connectionObject = new MyConnection();
    private static final Connection con = connectionObject.getConnection();

    public static boolean updateInfo(String ID, String fieldName, String newEntry) {
        try {
            System.out.println("INSIDE Student_Query.updateInfo first Function <WITH EXECUTION> ... ");
            String query = "UPDATE TABLE_STUDENT SET " + fieldName + " = '" + newEntry + "' WHERE ID = " + ID;
            System.out.println("QUERY IS .. " + query);

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            System.out.println("<><><><> RETURNING TRUE !! \n");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkFormat(String newBirth_date) {
        return isFormatCorrect(newBirth_date);
    }

    private static boolean isFormatCorrect(String date) {
        String[] split = date.split("/");
        if (split.length <= 1 || split.length >= 4) {
            return false;
        }
        int day, month, yr;
        try {
            day = Integer.parseInt(split[0]);
            month = Integer.parseInt(split[1]);
            yr = Integer.parseInt(split[2]);
        } catch (Exception e) {
            return false;
        }
        if (day < 1 || day >= 32) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }

        return true;
    }

    public static String makeDate(String newBirthDAY, String newBirthMON, String newBirthYEAR) {
        String date = null;
        if (Integer.parseInt(newBirthDAY) < 10) {
            newBirthDAY = "0" + newBirthDAY;
        }
        if (Integer.parseInt(newBirthMON) < 10) {
            newBirthMON = "0" + newBirthMON;
        }
        date = newBirthDAY + "/" + newBirthMON + "/" + newBirthYEAR;
        return date;
    }

    public static void updateInfo(String firstName, String lastName, String newBirthDAY, String newBirthMON, String newBirthYEAR, String newReligion, String newFatherName, String newMotherName, String newGender, String newBloodGrp, String newContactNum, String newAddress) throws Exception {
        String birth_date = makeDate(newBirthDAY, newBirthMON, newBirthYEAR);
        String new_gender = null;
        if (newGender.contains("Male") || newGender.contains("MALE") || newGender.toLowerCase().contains("male")) {
            new_gender = "M";
        } else {
            new_gender = "F";
        }
        updateInfo(Main.studentID, "FIRST_NAME", firstName);
        updateInfo(Main.studentID, "LAST_NAME", lastName);
        updateDate(Main.studentID, birth_date);
        updateInfo(Main.studentID, "RELIGION", newReligion);
        updateInfo(Main.studentID, "FATHER_NAME", newFatherName);
        updateInfo(Main.studentID, "MOTHER_NAME", newMotherName);
        updateInfo(Main.studentID, "GENDER", new_gender);
        updateInfo(Main.studentID, "BLOOD_GROUP", newBloodGrp);
        updateInfo(Main.studentID, "CONTACT_NO", newContactNum);
        updateInfo(Main.studentID, "ADDRESS", newAddress);
    }

    public static boolean updateDate(String ID, String birth_date) throws Exception {
        if (isFormatCorrect(birth_date) == false) {
            PopUpWindow.displayInCheck("ERROR", "PROBLEM WITH DATE!! TRY AGAIN!!");
            return false;
        }
        birth_date = "TO_DATE('" + birth_date + "', 'DD/MM/YYYY')";
        try {
            System.out.println("INSIDE Student_Query.updateDate first Function <WITH EXECUTION> ... ");
            String query = "UPDATE TABLE_STUDENT SET BIRTH_DATE = " + birth_date + " WHERE ID = " + ID;
            System.out.println("QUERY IS .. " + query);

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            System.out.println("<><><><> RETURNING TRUE !! \n");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<Guest_Log> getGuestLogOf(String student_id, String guest_nid) throws SQLException {
        String query = "select TO_CHAR(START_TIME, 'dd/mm/yyyy') as \"visiting date\",TO_CHAR(START_TIME, 'hh:mm:ss') as \"start time\", TO_CHAR(END_TIME,'hh:mm:ss') as \"end time\"\n"
                + "from TABLE_GUEST_LOG\n"
                + "where NID =  " + guest_nid
                + " and STUDENT_ID = " + student_id;

        List<Guest_Log> list = new ArrayList<>();

        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        Guest_Log guestLog = new Guest_Log();
        while (rs.next()) {
            guestLog.setVisitingDate(rs.getString(1));
            guestLog.setStartTime(rs.getString(2));
            guestLog.setEndTime(rs.getString(3));
            list.add(guestLog);
        }
        System.out.println("Inside Student_Query.fingGuestLog... Query is ");
        System.out.println(query);
        return list;
    }

}
