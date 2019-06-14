/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.teacher.upcomingEvents;

import com.jfoenix.controls.JFXTextField;
import hall_management.db.queries.Query;
import hall_management.db.queries.more_queries.Event_Queries;
import hall_management.ui.pushNotification.Notification;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author esfs
 */
public class WinnerAndRunnerUpSelectroController implements Initializable, Controller {

    @FXML
    private TableView tableview;
    @FXML
    private TableColumn col_teamName;
    @FXML
    private TableColumn btn_col_winner;
    @FXML
    private TableColumn btn_col_runnerUp;
    @FXML
    private Text text_winnerTeamName;
    @FXML
    private Text text_runnerUpTeamName;
    @FXML
    private JFXTextField textField_WinnerTeamName;
    @FXML
    private JFXTextField textField_runnerUpTeamName;

    public static String eventIDToFinish;
    public static String eventSportToFinish;

    String team_id_winner;
    String team_id_runnerUp;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            WinnerAndRunnerUpSelectroController.eventSportToFinish = Event_Queries.getFieldOfEvent(Table.Event, "EVENT_SPORT", "EVENT_ID",
                    eventIDToFinish);
            initialiseTableView();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO
    }

    private void initialiseTableView() throws Exception {
        List<Teams> loadingList = new ArrayList<>();
        loadingList = Event_Queries.getListOfTeamsForEventID(eventIDToFinish, "UPCOMING");
        List<Teams> list = new ArrayList<>();
        Teams e;
        Teams e2;
        for (int i = 0; i < loadingList.size(); i++) {
            e = loadingList.get(i);
            System.out.println("e = " + e.toString());
            e2 = new Teams(this);
//            e2 = new Teams(this, "VERDICT");
            e2.setCaptainStudentID(e2.getCaptainStudentID());
            e2.setHallID(e.getHallID());
            e2.setHallName(e.getHallName());
            e2.setTeam_id(e.getTeam_id());
            e2.setTeam_name(e.getTeam_name());
            e2.setTeam_sport(e.getTeam_sport());
            e2.setTeam_type(e.getTeam_type());
            e2.setTeam_year(e.getTeam_year());

            e2.setHallName(Query.getFieldInfoFromTable(Table.Hall, e2.getHallID(), "HALL_NAME"));
            list.add(e2);
        }
        System.out.println("INISIDE CONTROLLER> printing list => size = " + list.size());
        for(Teams t: list){
            System.out.println(t.toString());
        }
        
        ObservableList<Teams> data = FXCollections.observableArrayList(list);

        col_teamName.setCellValueFactory(
                new PropertyValueFactory<>("team_name")
        );
        btn_col_winner.setCellValueFactory(
                new PropertyValueFactory<>("btn_makeWinner")
        );
        btn_col_runnerUp.setCellValueFactory(
                new PropertyValueFactory<>("btn_makeRunnerUp")
        );

//        col_eventSport.setCellValueFactory(
//                new PropertyValueFactory<>("event_sport")
//        );
        tableview.setItems(data);
    }

    @FXML
    private void goBack() {
        SceneLoader.closeNewWindow(Scenes.teacher_event_winnerSelector);
        SceneLoader.loadSceneInADifferentWindow(Scenes.teacher_manage_upcoming_events, this);

    }

    @FXML
    private void submitWinners() {
        try {
            Event_Queries.updateFieldOfTable(Table.Event, "WINNER_TEAM_ID", team_id_winner, "EVENT_ID", eventIDToFinish);
            Event_Queries.updateFieldOfTable(Table.Event, "RUNNERUP_TEAM_ID", team_id_runnerUp, "EVENT_ID", eventIDToFinish);
            Event_Queries.updateFieldOfTable(Table.Event, "EVENT_STATUS", "OVER", "EVENT_ID", eventIDToFinish);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Notification.push("SUCCESS", "EVENT IS FINISHED", Notification.SUCCESS, Pos.CENTER);
        goBack();
    }

    void runMakeWinner(String team_id, String team_name, String team_sport, String captainID) {
        team_id_winner = team_id;
        if (team_sport.toUpperCase().contains("CHESS")) {
            text_winnerTeamName.setText("Winner Std.ID: ");
            textField_WinnerTeamName.setText(captainID);
        } else {
            text_winnerTeamName.setText("Winner Team Name:");
            textField_WinnerTeamName.setText(team_name);
        }
    }

    void makeRunnerUp(String team_id, String team_name, String team_sport, String captainID) {
        team_id_runnerUp = team_id;
        if (team_sport.toUpperCase().contains("CHESS")) {
            text_runnerUpTeamName.setText("RunnerUp Std.ID: ");
            text_runnerUpTeamName.setText(captainID);
        } else {
            text_runnerUpTeamName.setText("RunnerUp Team Name:");
            textField_runnerUpTeamName.setText(team_name);
        }
    }

}
