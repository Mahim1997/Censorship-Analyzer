/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.teacher;

import hall_management.db.queries.Functions;
import hall_management.db.queries.Query;
import hall_management.db.queries.more_queries.Event_Queries;
import hall_management.ui.startPage.Main;
import hall_management.ui.student.Student;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.Interface.Table;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author esfs
 */
public class See_students_hallController implements Initializable, Controller {

    @FXML
    private Text text_Uporer;
    @FXML
    private TableView tableview;
    @FXML
    private TableColumn col_studentID;
    @FXML
    private TableColumn col_fullName;
    @FXML
    private TableColumn col_deptID;
    @FXML
    private TableColumn col_contactNum;
    @FXML
    private TableColumn col_birthDate;
    @FXML
    private TableColumn col_fatherName;
    @FXML
    private TableColumn col_motherName;
    @FXML
    private TableColumn col_address;
    @FXML
    private TableColumn col_bloodGrp;
    @FXML
    private TableColumn col_religion;
    @FXML
    private TableColumn col_gender;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            String hallID = Functions.getHallID(Main.teacherID);
            String hallName = Event_Queries.getFieldOfEvent(Table.Hall, "HALL_NAME", "HALL_ID", hallID);
            text_Uporer.setText("Teacher ID: " + Main.teacherID + " , Hall Name: " + hallName);
            initialiseTable();
        }catch(Exception e){
            e.printStackTrace();
        }
    }    

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.teacher_ui, this);
    }

    private void initialiseTable() throws Exception{
        List<Student> list = Query.getStudentsOfThisHall();
        ObservableList<Student> data = FXCollections.observableArrayList(list);
 
        col_studentID.setCellValueFactory(new PropertyValueFactory<>("id"));

        //NAME column
 
        col_fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        //DEPT column
 
        col_deptID.setCellValueFactory(new PropertyValueFactory<>("dept_Id"));

        //Address column
 
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));

        //Blood Group Column
 
        col_bloodGrp.setCellValueFactory(new PropertyValueFactory<>("bloodGrp"));

        //Birthdate Column
  
        col_birthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        //Religion Column
 
        col_religion.setCellValueFactory(new PropertyValueFactory<>("religion"));

        //Gender Column
 
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        //Contact 
 
        col_contactNum.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));

        //Father Name
 
        col_fatherName.setCellValueFactory(new PropertyValueFactory<>("fatherName"));

 
        col_motherName.setCellValueFactory(new PropertyValueFactory<>("motherName"));
        
        tableview.setItems(data);
    }
    
}
