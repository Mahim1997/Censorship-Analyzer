/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.student.events;

import hall_management.db.queries.more_queries.Event_Queries;
import hall_management.ui.startPage.Main;
import hall_management.ui.teacher.events.Events;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author esfs
 */
public class ParticipatedEventsController implements Initializable, Controller {

    @FXML
    private TableView tableview;
    @FXML
    private TableColumn col_eventID;
    @FXML
    private TableColumn col_eventName;
    @FXML
    private TableColumn col_eventYear;
    @FXML
    private TableColumn col_sport;
    @FXML
    private TableColumn col_eventType;
    @FXML
    private TableColumn col_hallID;
    @FXML
    private TableColumn col_seeTeam;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            loadTableView();
        }catch(Exception e){
            e.printStackTrace();
        }
    }    

    private void loadTableView() throws Exception{
        List<Events> list = new ArrayList<>();
        list = Event_Queries.getListOfEventsParticipatedByStudent(Main.studentID);
        List<Events> list2 = new ArrayList<>();
        Events e, e2;
        for (int i = 0; i < list.size(); i++) {
            e = list.get(i);
            e2 = new Events(this);
            e2.setEvent_id(e.getEvent_id());
            e2.setEvent_name(e.getEvent_name());
            e2.setEvenr_supervisorID(e.getEvenr_supervisorID());
            e2.setEvent_sport(e.getEvent_sport());
            e2.setEvent_status(e.getEvent_status());
            e2.setEvent_type(e.getEvent_type());
            e2.setEvent_type(e.getEvent_type());
            e2.setEvent_year(e.getEvent_year());
            e2.setHall_id(e.getHall_id());
            e2.setRunnerUp_team_id(e.getRunnerUp_team_id());
            e2.setWinner_team_id(e.getWinner_team_id());
            list2.add(e2);
         
        }
        
        ObservableList<Events> data = FXCollections.observableArrayList(list2);

        
        
        col_eventID.setCellValueFactory(
                new PropertyValueFactory<>("event_id")
        );
        col_eventName.setCellValueFactory(
                new PropertyValueFactory<>("event_name")
        );
        col_eventYear.setCellValueFactory(
                new PropertyValueFactory<>("event_year")
        );
        col_eventType.setCellValueFactory(
                new PropertyValueFactory<>("event_type")
        );
        col_sport.setCellValueFactory(
                new PropertyValueFactory<>("event_sport")
        );
        col_hallID.setCellValueFactory(
                new PropertyValueFactory<>("hall_id")
        );
        col_seeTeam.setCellValueFactory(
                new PropertyValueFactory<>("btn_student_seeTeams")
        );
        tableview.setItems(data);

    }

    @FXML
    private void exitScreen( ) {
        SceneLoader.closeNewWindow(Scenes.students_events_participated);
    }

    public void runButtonClick(String event_id, String event_sport) {
        See_teamsController.eventID_participated = event_id;
        SceneLoader.closeNewWindow(Scenes.students_events_participated);
        SceneLoader.loadSceneInADifferentWindow(Scenes.student_see_teams_participated, this);
    }
    
}
