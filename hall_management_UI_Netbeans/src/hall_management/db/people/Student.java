 
package hall_management.db.people;
 
import hall_management.db.queries.Checker;
import static hall_management.util.Interface.Type.*;
import java.sql.SQLException;

public class Student {
    public int type ;
    public String id;
    public String password;

    public Student(String id, String password) {
        this.type = type_Student;
        this.id = id;
        this.password = password;
    }

    public Student(String studentId) throws SQLException {
        this.type = type_Student;
        this.id = studentId;
        this.password = Checker.findPasswordForThisID(type_Student, id);
    }
    
    
    
}
