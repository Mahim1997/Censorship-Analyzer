/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.student.events;

import hall_management.db.queries.more_queries.Event_Queries;
import hall_management.db.queries.more_queries.Team_Queries;
import hall_management.ui.startPage.Main;
import static hall_management.ui.teacher.events.See_teams_studentsController.team_id_to_see;
import hall_management.ui.teacher.upcomingEvents.Student_Games;
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
public class See_teamsController implements Initializable, Controller {

    /**
     * Initializes the controller class.
     */
    public static String eventID_participated;
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
    @FXML
    private Text text_uporerPart;
    @FXML
    private Text text_nicherPart;
    private String teamID;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // TODO
            loadTableView();
            String pos = null;
            String getThing = Event_Queries.getFieldOfEvent(Table.Event, "WINNER_TEAM_ID", "EVENT_ID", eventID_participated);
            if (getThing != null) {
                pos = "WINNER ";
            }
            else{
                getThing = Event_Queries.getFieldOfEvent(Table.Event, "RUNNERUP_TEAM_ID", "EVENT_ID", eventID_participated);
                if(getThing != null){
                    pos = "RUNNERUP ";
                }
                else{
                    pos = "PARTICIPATED";
                }
            }
            text_nicherPart.setText("POSITION : " + pos);

            String teamName = null;
            teamName = Team_Queries.getTeamNameOfStudentFromEvent(Main.studentID, eventID_participated);
            text_uporerPart.setText("TEAM NAME :" + teamName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.closeNewWindow(Scenes.student_see_teams_participated);
        SceneLoader.loadSceneInADifferentWindow(Scenes.students_events_participated, this);
    }

    private void loadTableView() throws Exception {
        teamID = Team_Queries.findTeamID(eventID_participated, Main.studentID);
        List<Student_Games> list = Team_Queries.getListOfStudentsFromTeamID(teamID);
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
