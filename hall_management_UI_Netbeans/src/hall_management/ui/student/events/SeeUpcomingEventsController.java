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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author esfs
 */
public class SeeUpcomingEventsController implements Initializable, Controller {

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
        list = Event_Queries.getListOfUpcomingEvents( );
        ObservableList<Events> data = FXCollections.observableArrayList(list);

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
//        col_button_seeteamsParticipated.setCellValueFactory(
//                new PropertyValueFactory<>("button")
//        );
        tableview.setItems(data);
    }

    @FXML
    private void exitScreen(ActionEvent event) {
        SceneLoader.closeNewWindow(Scenes.student_see_upcoming_events);
    }
    
}
