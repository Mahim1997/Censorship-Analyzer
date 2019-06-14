/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.teacher.events;

import hall_management.db.queries.more_queries.Team_Queries;
import hall_management.ui.teacher.upcomingEvents.Student_Games;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author esfs
 */
public class See_teams_studentsController implements Initializable, Controller {
    public static String team_id_to_see;
    @FXML
    private TableView tableview;
    @FXML
    private TableColumn col_studentID;
    @FXML
    private TableColumn col_fullName;
    @FXML
    private TableColumn col_studentType;
    @FXML
    private TableColumn col_playerPosition;
    @FXML
    private TableColumn col_contactNumber;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            
            loadTableView();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
    @FXML
    private void exitScreen(){
        SceneLoader.closeNewWindow(Scenes.teacher_seeEvents_teams_students);
    }

    private void loadTableView() throws Exception {
        List<Student_Games>  list = Team_Queries.getListOfStudentsFromTeamID(team_id_to_see);
        ObservableList<Student_Games> data = FXCollections.observableArrayList(list);
        /*
    String studentID;
    String fullName;
    String student_type;
    String position;
    String hallName;
    String contactNo;
    String team_year;
         */
        col_contactNumber.setCellValueFactory(
                new PropertyValueFactory<>("contactNo")
        );
        col_fullName.setCellValueFactory(
                new PropertyValueFactory<>("fullName")
        );
        col_playerPosition.setCellValueFactory(
                new PropertyValueFactory<>("position")
        );
        col_studentID.setCellValueFactory(
                new PropertyValueFactory<>("studentID")
        );
        col_studentType.setCellValueFactory(
                new PropertyValueFactory<>("student_type")
        );
 
//        col_eventSport.setCellValueFactory(
//                new PropertyValueFactory<>("event_sport")
//        );
        tableview.setItems(data);
    }
}
