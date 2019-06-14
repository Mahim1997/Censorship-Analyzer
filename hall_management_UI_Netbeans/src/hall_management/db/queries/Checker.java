 
package hall_management.db.queries;
import hall_management.util.Interface.Table;
import static hall_management.util.Interface.Type.*;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.stage.Stage;

public class Checker extends Application{

    int type=-1; 
    String id;
    String password;

    public static void main(String [] args) throws Exception {
        String[] arr = Query.getStudentInfo("7000");
        for(String s: arr){
            System.out.println(s);
        }
    }
    
    public Checker(int type, String id, String password) {
        this.type = type;
        this.id = id;
        this.password = password;
    }
    
    public static boolean doesThisIDExist(int type, String id) throws SQLException
    {
        boolean flag = false ;
        switch (type) {
            case type_Student:
                flag = Query.isIn(Table.Student, id);
                break;
            case type_Teacher:
                flag = Query.isIn(Table.Teacher, id);
                break;
            case type_Staff:
                flag = Query.isIn(Table.Staff, id);
                break;
            case type_Guest:
                flag = Query.isIn(Table.Guest, id);
                break;
            default:
                break;
        }
        return flag ;
    }
    
    public static boolean doesPasswordMatch(int type, String id, String password) throws SQLException
    {
        boolean flag = false ;
        switch (type) {
            case type_Student:
                flag = Query.doesPasswordMatchForId(Table.Student, id, password);
                break;
            case type_Teacher:
                flag = Query.doesPasswordMatchForId(Table.Teacher, id, password);
                break;
            case type_Staff:
                flag = Query.doesPasswordMatchForId(Table.Staff, id, password);
                break;
            case type_Guest:
//                flag = true;
                flag = Query.doesPasswordMatchForId(Table.Guest_Pass, id, password);                
                break;
            default:
                break;
        }
        return flag ; 
    }
    public static String getType(int type)
    {
        switch (type) {
            case type_Staff:
                return "Staff";
            case type_Student:
                return "Student";
            case type_Teacher:
                return "Teacher";
            case type_Guest:
                return "Guest";
            default:
                break;
        }
        return null ;
    }
    public static String findPasswordForThisID(int type, String id) throws SQLException
    {
        return Query.findPasswordForThisID(getType(type), id);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.show();
    }
    
}
