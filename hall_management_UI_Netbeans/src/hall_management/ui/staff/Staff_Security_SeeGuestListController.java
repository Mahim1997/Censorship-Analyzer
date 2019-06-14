/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.staff;

import hall_management.db.queries.Staff_Queries;
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
public class Staff_Security_SeeGuestListController implements Initializable, Controller {

    /**
     * Initializes the controller class.
     */
    public static String studentID;
    @FXML
    private TableView tableview;
    @FXML
    private TableColumn col_guestNID;
    @FXML
    private TableColumn col_guestName;
    @FXML
    private TableColumn col_buttonAddToGuestLog;
    @FXML
    private Text text_Uporer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String text = "Guest List of Student ID = " + Staff_Security_SeeGuestListController.studentID;
        text_Uporer.setText(text);
//        System.out.println("=-->> Staff_Security_SeeGuestListController.initialize().. Staff_Security_SeeGuestListController.guestNID = " + Staff_Security_SeeGuestListController.guestNID);
        
        try{
            loadAllowedGuestsOfThisStudent(Staff_Security_SeeGuestListController.studentID);
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.staff_SecurityGuard, this);
    }

    private void loadAllowedGuestsOfThisStudent(String studentID) throws Exception{
        List<String> list_guestID = Staff_Queries.findList(Table.Allowed_Guest, "NID", "STUDENT_ID", Staff_Security_SeeGuestListController.studentID);
        List<String> list_guestFullName = new ArrayList<>();
        for(int i=0; i<list_guestID.size(); i++){
            String guest_NID = list_guestID.get(i);
            String fullName = Staff_Queries.findFieldOf(Table.Guest, guest_NID, "FIRST_NAME || ' ' || LAST_NAME");
            list_guestFullName.add(fullName);
        }
        GuestWithButton gstButton ;
        List<GuestWithButton> list = new ArrayList<>();
//        System.out.println("=-->>> PRINTING BOTH LISTS SIZE =  .. ." + list_guestFullName.size());
        for(int i=0; i<list_guestID.size(); i++){
            list.add(new GuestWithButton(list_guestID.get(i), list_guestFullName.get(i), this));
//            System.out.println("NID : " + list_guestID.get(i) + " , FULL NAME: " + list_guestFullName.get(i));
        }
        
        showToScreen(list);
    }

    void runEnterToGuestLog(String NID) {
        System.out.println("=-->>> RUN TO ENTER GUEST LOG OF GUEST NID = " + NID);
        Staff_Security_SeeGuestLogController.Staff_Guest_NID = NID ;
        Staff_Security_SeeGuestLogController.Staff_StudentID = Staff_Security_SeeGuestListController.studentID;
        SceneLoader.loadScene(Scenes.staff_SecurityGuard_Guest_Log, this);
    }

    private void showToScreen(List<GuestWithButton> list) {


        ObservableList<GuestWithButton> data = FXCollections.observableArrayList(list);

        col_guestNID.setCellValueFactory(
                new PropertyValueFactory<Student_ForGuardToSee, String>("NID")
        );
        col_guestName.setCellValueFactory(
                new PropertyValueFactory<>("fullName")
        );
        col_buttonAddToGuestLog.setCellValueFactory(
                new PropertyValueFactory<>("btn")
        );

        tableview.setItems(data);
    }

}
