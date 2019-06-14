/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.teacher.events;

import hall_management.db.queries.more_queries.Event_Queries;
import hall_management.db.queries.more_queries.Team_Queries;
import hall_management.ui.teacher.upcomingEvents.Teams;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.Interface.Table;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SeeTeamsController implements Initializable, Controller {

    /**
     * Initializes the controller class.
     */
    String eventId ;
    @FXML
    private TableView tableview;
    @FXML
    private TableColumn col_teamName;
    @FXML
    private TableColumn col_sport;
    @FXML
    private TableColumn col_hallName;
    @FXML
    private TableColumn col_teamType;
    @FXML
    private TableColumn col_eventYear;
    @FXML
    private TableColumn btn_col;
    String eventStatus;
    @FXML
    private TableColumn  col_pos;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        eventId = SeeEventsSuperviedController.eventIDToSee;
        try{
            eventStatus = Event_Queries.getFieldOfEvent(Table.Event, "EVENT_STATUS", "EVENT_ID", eventId);
        }catch(Exception e){
            e.printStackTrace();
        }
        try {
            loadTableView();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    

    private void loadTableView() throws Exception{
        List<Teams> list = new ArrayList<>();
        list = Event_Queries.getListOfTeamsForEventID(eventId, eventStatus);
        List<Teams> list2 = new ArrayList<>();
        Teams t, t2;
        for(int i=0; i<list.size(); i++){
            t = list.get(i);
            t2 = new Teams(this);
            t2.setCaptainStudentID(t.getCaptainStudentID());
            t2.setHallID(t.getHallID());
            t2.setHallName(t.getHallName());
            t2.setTeam_id(t.getTeam_id());
            t2.setTeam_name(t.getTeam_name());
            t2.setTeam_sport(t.getTeam_sport());
            t2.setTeam_type(t.getTeam_type());
            t2.setTeam_year(t.getTeam_year());
            t2.setPosition(Team_Queries.getPositionOfTeam(t2.getTeam_id(), eventId));
            list2.add(t2);
        }
        ObservableList<Teams> data = FXCollections.observableArrayList(list2);
        col_eventYear.setCellValueFactory(
                new PropertyValueFactory<>("team_year")
        );
        col_hallName.setCellValueFactory(
                new PropertyValueFactory<>("hallName")
        );
        col_sport.setCellValueFactory(
                new PropertyValueFactory<>("team_sport")
        );
        col_teamName.setCellValueFactory(
                new PropertyValueFactory<>("team_name")
        );
        col_teamType.setCellValueFactory(
                new PropertyValueFactory<>("team_type")
        );
        col_pos.setCellValueFactory(
                new PropertyValueFactory<>("position")
        );  
        btn_col.setCellValueFactory(
                new PropertyValueFactory<>("btn_click_See_Students")
        );
        tableview.setItems(data);
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.closeNewWindow(Scenes.teacher_seeEvents_teams);
        SceneLoader.loadSceneInADifferentWindow(Scenes.teacher_seeEvents, this);
    }

    public void runOnButtonClick(String team_id, String team_name) {
        System.out.println("=->Load Students of team_id = " + team_id + " , team_name = " + team_name);
//        SceneLoader.closeNewWindow(Scenes.teacher_seeEvents_teams);
        See_teams_studentsController.team_id_to_see = team_id;
        SceneLoader.loadSceneInADifferentWindow(Scenes.teacher_seeEvents_teams_students, this);
    }
    
}
