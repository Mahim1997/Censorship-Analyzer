/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.student.events;

import hall_management.db.queries.more_queries.Team_Queries;
import hall_management.ui.startPage.Main;
import static hall_management.ui.student.events.See_teamsController.eventID_participated;
import hall_management.ui.teacher.upcomingEvents.Student_Games;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
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

/**
 * FXML Controller class
 *
 * @author esfs
 */
public class SeeTeamsOfUpcomingEventsController implements Initializable, Controller {

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
        // TODO
    }    

    @FXML
    private void closeWindow(ActionEvent event) {
        SceneLoader.closeNewWindow(Scenes.student_see_teams_upcoming_events);
    }

    @FXML
    private void loadCricket(ActionEvent event)throws Exception {
        loadTableView("CRICKET", "LOCAL");
    }

    @FXML
    private void loadFootBall(ActionEvent event) throws Exception{
        loadTableView("FOOTBALL", "LOCAL");
    }

    @FXML
    private void loadChess(ActionEvent event) throws Exception{
        loadTableView("CHESS", "LOCAL");
    }

    private void loadTableView(String sport, String teamType) throws Exception{
//        teamID = Team_Queries.findTeamID(eventID_participated, Main.studentID);
        List<Student_Games> list = Team_Queries.getListOfStudentsUpcomingEvent(sport, teamType);
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

    @FXML
    private void loadGlobalCricket(ActionEvent event) throws Exception{
        loadTableView("CRICKET", "GLOBAL");
    }

    @FXML
    private void loadGlobalFootball(ActionEvent event) throws Exception{
        loadTableView("FOOTBALL", "GLOBAL");
    }

    @FXML
    private void loadGlobalChess(ActionEvent event) throws Exception{
        loadTableView("CHESS", "GLOBAL");
    }
    
}
