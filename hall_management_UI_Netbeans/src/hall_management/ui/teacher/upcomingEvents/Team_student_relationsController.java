/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.teacher.upcomingEvents;

 
import hall_management.db.queries.more_queries.Team_Queries;
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
public class Team_student_relationsController implements Initializable, Controller {
    String eventID;
    String teamID ;
    String sport;
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
    private Text text_Uporer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.teamID = Manage_teamsController.team_id_to_use;
        this.sport = Manage_teamsController.sport_to_use;
        this.eventID = ManageUpcomingEventsController.event_id_toManage;
        System.out.println("=->Initialise... Team_student_relationsController... teamId = " + this.teamID+ ", Sport = " + this.sport + 
                " , EVENT ID = " + this.eventID);
        
        try{
            if(this.sport.toUpperCase().contains("CHESS")){
                loadChessWindow(teamID);
            }
            else{
                loadTableView(teamID);
            }
            String teamName = Team_Queries.getFieldOfTeam(Table.Team, "TEAM_NAME", "TEAM_ID", teamID);
            text_Uporer.setText("TEAM NAME: " + teamName);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }    

    private void loadTableView(String teamID) throws Exception{
        List<Student_Games> list = new ArrayList<>();
        list = Team_Queries.getListOfStudentsFromTeamID(teamID);
        showThisListToWindow(list);
    }

    private void loadChessWindow(String teamID) throws Exception{
        Student_Games student = new Student_Games();
        student = Team_Queries.getStudentOfChessFromTeamID(teamID);
        List<Student_Games> list = new ArrayList<>();
        list.add(student);
        showThisListToWindow(list);
    }

    @FXML
    private void goBack( ) {
        SceneLoader.closeNewWindow(Scenes.teacher_acceptOrReject_Teams);
        SceneLoader.loadSceneInADifferentWindow(Scenes.teacher_load_teams_for_events, this);
    }

    @FXML
    private void rejectThisTeam( ) throws Exception{
        System.out.println("=->>REJECT FOR TEAM ID = " + teamID + " , EVENT ID " + ManageUpcomingEventsController.event_id_toManage);
        Team_Queries.deleteTeam(teamID);
        Notification.push("Deleted Team!", "Delete Another Team Maybe??", Notification.SUCCESS);
        goBack();
    }

    @FXML
    private void acceptThisTeam( )throws Exception{
        System.out.println("=->ACCEPT FOR TEAM ID = " + teamID + " , EVENT ID = " + ManageUpcomingEventsController.event_id_toManage);
        System.out.println("OK1");
        String s = Team_Queries.addTeamToEvent(teamID, eventID, "UPCOMING");
        System.out.println("OK2");
        if(s.equals(Team_Queries.QUERY_SUCCESS)){
        System.out.println("OK2");
            Notification.push("SUCCESS", "ADD ANOTHER TEAM MAYBE?", Notification.SUCCESS);
//            SceneLoader.closeNewWindow(Scenes.teacher_acceptOrReject_Teams);
            goBack();
        }
        else{
            Notification.push("FAILURE", "OOPS! Probably Already Exists!!", Notification.FAILURE);
        }
    }

    private void showThisListToWindow(List<Student_Games> list) {
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
