/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.staff;

import com.jfoenix.controls.JFXTextField;
import hall_management.db.queries.Application_Queries;
import hall_management.db.queries.Query;
import hall_management.db.queries.Staff_Queries;
import hall_management.ui.popup.PopUpWindow;
import hall_management.ui.startPage.Main;
import hall_management.ui.student.seeGuestList.AllowedGuest_WithButton;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.Interface.Table;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.util.ArrayList;
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
public class Staff_Security_GuardController implements Initializable, Controller {

    public static String studentIDforGuardToUse;
    @FXML
    private Text text_UporerPart;
    @FXML
    private TableView tableview;
    @FXML
    private TableColumn col_studentID;
    @FXML
    private TableColumn col_studentName;
    @FXML
    private TableColumn col_button_seeGuestList;

    private String hallID;
    private String hallName;
    @FXML
    private JFXTextField textField_StudentID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            initialiseInfoOfHallIdAndName();
            String text = "STAFF ID : " + Main.staffID + " , Hall ID : " + hallID + " , Hall Name : " + hallName;
            text_UporerPart.setText(text);
            loadStudentIDAndNamesOfThisHall(hallID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadScene(Scenes.staff_ui, this);
    }

    private void initialiseInfoOfHallIdAndName() throws Exception {
        hallID = Staff_Queries.findFieldOf(Table.Staff_WorksAtHall, Main.staffID, "HALL_ID");
        hallName = Query.getFieldInfoFromTable(Table.Hall, hallID, "HALL_NAME");
    }

    private void loadStudentIDAndNamesOfThisHall(String hallID) throws Exception {
        List<String> idList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        idList = Staff_Queries.findList(Table.Student, "ID", "HALL_ID", hallID);
        nameList = Staff_Queries.findList(Table.Student, "FIRST_NAME || ' ' || LAST_NAME", "HALL_ID", hallID);
        Student_ForGuardToSee stdnt;
        List<Student_ForGuardToSee> list = new ArrayList<>();
//        System.out.println("PRINTING COMBINED LIST in Controller. .. ");
        for (int i = 0; i < idList.size(); i++) {
            stdnt = new Student_ForGuardToSee(idList.get(i), nameList.get(i), this);
            list.add(stdnt);
//            System.out.println("ID : " + idList.get(i) + " , FULLNAME : " + nameList.get(i));
        }
        loadToWindow(list);
    }

    public void runSeeGuestList(String studentID) {
        System.out.println("=-->>>TO RUN SEE GUEST LIST FOR STUDENT ID : " + studentID);
//        Staff_Security_SeeGuestLogController.Staff_StudentID = studentID;
//        Staff_Security_SeeGuestLogController.Staff_GuestID = null;
        Staff_Security_SeeGuestListController.studentID = studentID;
        SceneLoader.loadScene(Scenes.staff_SecurityGuard_Guest_List, this);
    }

    private void loadToWindow(List<Student_ForGuardToSee> list) {
//        col_studentID = new TableColumn("Student ID");
//        col_studentName = new TableColumn("Full Name");
//        col_button_seeGuestList = new TableColumn("Button");

//        tableview.getColumns().addAll(col_studentID, col_studentName, col_button_seeGuestList);
        ObservableList<Student_ForGuardToSee> data = FXCollections.observableArrayList(list);

        col_studentID.setCellValueFactory(
                new PropertyValueFactory<Student_ForGuardToSee, String>("id")
        );
        col_studentName.setCellValueFactory(
                new PropertyValueFactory<>("fullName")
        );
        col_button_seeGuestList.setCellValueFactory(
                new PropertyValueFactory<>("button_seeGuestList")
        );

        tableview.setItems(data);
    }

    @FXML
    private void searchThisStudentID( ) throws Exception {
        String receivedStudentID = textField_StudentID.getText();
        List<Student_ForGuardToSee> list = new ArrayList<>();
        if (receivedStudentID == null || receivedStudentID.equals("")) {
            System.out.println("<><>STUDENT ID IS NULL!! so return ;");
            loadStudentIDAndNamesOfThisHall(hallID);
            return;
        }
        try {
            int x = Integer.parseInt(receivedStudentID);
        } catch (Exception e) {
            PopUpWindow.displayInCheck("ERROR!", "STUDENT ID SHOULD BE NUMBER!");
            textField_StudentID.clear();
            return;
//            e.printStackTrace();
        }

//        String name = Query.getFieldInfoFromTable(Table.Student, receivedStudentID, "FIRST_NAME || ' ' || LAST_NAME");
        
        String name = Staff_Queries.getNameOfStudentFromHall(Table.Student, receivedStudentID, hallID);
        
        if (name != null) {
            list.add(new Student_ForGuardToSee(receivedStudentID, name, this));
            loadToWindow(list);
        }
//        else{
//            loadToWindow(null);
//        }
    }

}
