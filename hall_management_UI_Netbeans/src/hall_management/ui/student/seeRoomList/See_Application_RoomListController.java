/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.student.seeRoomList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import hall_management.db.queries.Application_Queries;
import hall_management.ui.startPage.Main;
import hall_management.ui.student.seeApplicationLog.Application_With_Button;
import static hall_management.ui.student.seeApplicationLog.Application_With_Button.seeRoomList_ApplicationID;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author esfs
 */
public class See_Application_RoomListController implements Initializable, Controller {
//    public String applicationID;

    @FXML
    private Text text_Uporer;
//    private TableView tableview;
    @FXML
    private JFXTextField textField_room1;
    @FXML
    private JFXTextField textField_room2;
    @FXML
    private JFXTextField textField_room3;
    @FXML
    private JFXTextField textField_room4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setAllTextFieldsEditable(false);
        text_Uporer.setText("Application ID : " + Application_With_Button.seeRoomList_ApplicationID + " , Student ID: " + Main.studentID);
        System.out.println("<><><>INITIALISIGN!!! ApplicationID : " + Application_With_Button.seeRoomList_ApplicationID);
        try {
            initialiseColumns();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    private void goBack() {
//        SceneLoader.loadScene(Scenes.student_see_application_ui, this);
//    }

    private void initialiseColumns() throws Exception {
        TableColumn roomCol = new TableColumn("Room Number");

        List<Application_Rooms> list = new ArrayList<>();
        list = Application_Queries.getApplicationRoomNumber(Application_With_Button.seeRoomList_ApplicationID);

        if (list.size() >= 1) {
            textField_room1.setText(list.get(0).getRoom_no());
        }
        if (list.size() >= 2) {
            textField_room2.setText(list.get(1).getRoom_no());
        }
        if (list.size() >= 3) {
            textField_room1.setText(list.get(2).getRoom_no());
        }
        if (list.size() >= 4) {
            textField_room4.setText(list.get(3).getRoom_no());
        }
//        setAllTextFieldsEditable(false);

    }


    private void setAllTextFieldsEditable(boolean flag) {
        textField_room1.setEditable(flag);
        textField_room2.setEditable(flag);
        textField_room3.setEditable(flag);
        textField_room4.setEditable(flag);
    }

}
