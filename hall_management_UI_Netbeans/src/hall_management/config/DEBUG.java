package hall_management.config;

import hall_management.ui.startPage.Main;
import hall_management.util.Interface.Type;

public interface DEBUG {

    boolean isDEBUG_ON = false;

    int DEBUG_MODE_STUDENT = 0;
    int DEBUG_MODE_TEACHER = 1;
    int DEBUG_MODE_STAFF = 2;
    int DEBUG_MODE_ADMIN = 3;
    int DEBUG_MODE_GUEST = 4;
    
    int DEBUG_MODE = DEBUG_MODE_ADMIN;

    String DEBUG_STUDENT_ID = "7000";
    String DEBUG_STUDENT_PASS = "1432";

    String DEBUG_TEACHER_ID = "204";//"100";
    String DEBUG_TEACHER_PASS = "2847";//"4971";

    String DEBUG_STAFF_ID = "114";
    String DEBUG_STAFF_PASS = "1111";

    String DEBUG_GUEST_ID = "5363";
    String DEBUG_GUEST_PASS = "1111";
    
    String DEBUG_ADMIN_ID = "admin";
    String DEBUG_ADMIN_PASS = "admin";

    static String DEBUG_LOGIN_ID() {
        if (DEBUG_MODE == DEBUG_MODE_STUDENT) {
            Main.studentID = DEBUG_STUDENT_ID;
            return DEBUG_STUDENT_ID;
        }
        if (DEBUG_MODE == DEBUG_MODE_TEACHER) {
            Main.teacherID = DEBUG_TEACHER_ID;
            return DEBUG_TEACHER_ID;
        }
        if (DEBUG_MODE == DEBUG_MODE_STAFF) {
            Main.staffID = DEBUG_STAFF_ID;
            return DEBUG_STAFF_ID;
        }
        if(DEBUG_MODE == DEBUG_MODE_GUEST){
            Main.guestID = DEBUG_GUEST_ID;
            return DEBUG_GUEST_ID;
        }
        Main.adminID = DEBUG_ADMIN_ID;
        return DEBUG_ADMIN_ID;
    }

    static String DEBUG_LOGIN_PASS() {
        if (DEBUG_MODE == DEBUG_MODE_STUDENT) {
            return DEBUG_STUDENT_PASS;
        }
        if (DEBUG_MODE == DEBUG_MODE_TEACHER) {
            return DEBUG_TEACHER_PASS;
        }
        if (DEBUG_MODE == DEBUG_MODE_STAFF) {
            return DEBUG_STAFF_PASS;
        }
        if(DEBUG_MODE == DEBUG_MODE_GUEST){
            return DEBUG_GUEST_PASS;
        }
        return DEBUG_ADMIN_PASS;
    }

    static int DEBUG_LOGIN_TYPE() {
        if (DEBUG_MODE == DEBUG_MODE_STUDENT) {
            return Type.type_Student;
        }
        if (DEBUG_MODE == DEBUG_MODE_TEACHER) {
            return Type.type_Teacher;
        }
        if (DEBUG_MODE == DEBUG_MODE_STAFF) {
            return Type.type_Staff;
        }
        if(DEBUG_MODE == DEBUG_MODE_GUEST){
            return Type.type_Guest;
        }
        return Type.type_Admin;
    }
}
