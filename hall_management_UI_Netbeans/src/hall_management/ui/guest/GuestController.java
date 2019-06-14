/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.guest;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import hall_management.db.queries.Query;
import hall_management.db.queries.Update_Query;
import hall_management.ui.popup.PopUpWindow;
import hall_management.ui.settings.SettingsController;
import hall_management.ui.staff.Student_ForGuardToSee;
import hall_management.ui.startPage.Main;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author suban
 */
public class GuestController implements Initializable,Controller {

    @FXML
    private JFXTabPane tabPane;
    @FXML
    private JFXTextField textLabel_NID;
    @FXML
    private JFXTextField textLabel_contactNo;
    @FXML
    private JFXTextField textLabel_address;
    @FXML
    private JFXTextField textLabel_FullName;
    @FXML
    private JFXButton button_refresh;
    @FXML
    private JFXButton button_SaveChanges;
    @FXML
    private JFXToggleButton toggleButton_edit;
    @FXML
    private JFXButton button_loadProfile;
//    private JFXButton button_GuestLog;
    @FXML
    private JFXButton button_loadSettings;
    @FXML
    private JFXButton button_signOut;

    String guestID;
    String password;
    @FXML
    private TableView tableview;
    @FXML
    private TableColumn col_student_ID;
    @FXML
    private TableColumn col_studentFullName;
    @FXML
    private TableColumn col_hallName;
    @FXML
    private TableColumn col_relation;
    @FXML
    private JFXButton button_students;
    
        
    public String getGuestID() {
        return guestID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
        try {
            refreshInfo();
        } catch (Exception ex) {
            Logger.getLogger(GuestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    

    private String fullName;
    private String address;
    private String contactNumber;
    JFXButton[] sceneButtons = null;

    String staffId;
    String currentPass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sceneButtons = new JFXButton[TAB_SIZE];

        sceneButtons[TAB_INDEX_Profile] = button_loadProfile;
        sceneButtons[TAB_INDEX_CheckGuest] = button_students;
        sceneButtons[TAB_INDEX_Settings] = button_loadSettings;

        makeInitial();

        try {
            refreshInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        highlightButton(TAB_INDEX_Profile);
    }

    private void makeInitial() {
        setAllEditable(false);
        try {
            // TODO
            refreshInfo();
            toggleButton_edit.setSelected(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        load_enter_into_studentList();
//        loadProfile();
    }

    private void setTextFeildsToStrings() {
        textLabel_NID.setText(guestID);
        textLabel_contactNo.setText(contactNumber);
        textLabel_address.setText(address);
        textLabel_FullName.setText(fullName);
    }

    public void setCurrentPass(String currentPass) {
        this.currentPass = currentPass;
    }

    private void getTextsFromTextFields() {
        contactNumber = textLabel_contactNo.getText();
        fullName = textLabel_FullName.getText();
        address = textLabel_address.getText();
    }

    private void setAllEditable(boolean flag) {
        textLabel_NID.setEditable(false);
        textLabel_contactNo.setEditable(flag);
        textLabel_address.setEditable(flag);
        textLabel_FullName.setEditable(flag);
    }

    @FXML
    private void refreshInfo() throws Exception {
        ///LOADS INFO
//        fullName = Query.getFieldInfoFromTable(Table.Staff, Main.staffID, "STAFF_NAME");
//        address = Query.getFieldInfoFromTable(Table.Staff, Main.staffID, "JOB_TYPE");
//        StaffController.Staff_jobType = address;
//        contactNumber = Query.getFieldInfoFromTable(Table.Staff, Main.staffID, "CONTACT_NO");

        fullName = Query.getFieldInfoFromTable(Table.Guest, guestID, "FIRST_NAME || ' '  || LAST_NAME");
        address = Query.getFieldInfoFromTable(Table.Guest, guestID, "ADDRESS");
        contactNumber = Query.getFieldInfoFromTable(Table.Guest, guestID, "CONTACT_NO");
//        toggleButton_edit.setDisableVisualFocus(false);
        toggleButton_edit.setSelected(false);
        setTextFeildsToStrings();
        

    }

    @FXML
    private void setCanEdit_toTrue() {
        if (toggleButton_edit.isSelected() == true) {
//            System.out.println("<><><>TOGGLE SELECTED!! ");
            setAllEditable(true);

        } else {
//            System.out.println("<><><>< NOT SELECTED <ELSE> !");
            setAllEditable(false);
        }
    }

    @FXML
    private void loadProfile() throws Exception {
        refreshInfo();
        tabPane.getSelectionModel().select(TAB_INDEX_Profile);
        highlightButton(TAB_INDEX_Profile);
    }

    @FXML
    private void loadSettings() {
        int prevSelTabIndex = tabPane.getSelectionModel().getSelectedIndex();

        highlightButton(TAB_INDEX_Settings);

        System.out.println("Load Settings Window");
        changePassWindow();
        highlightButton(prevSelTabIndex);
    }

    @FXML
    private void signOut() {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.startPage_ui, this);
    }
    private String quote(String s){
        String x = " '" + s + "' ";
        return x;
    }
    @FXML
    private void saveChanges_toEditInfo() throws Exception {
        //On clicking Save Changes Button
        getTextsFromTextFields();
        if (areAllFieldsComplete() == false) {
            PopUpWindow.displayInCheck("ERROR!", "CAN'T BE EMPTY!");
            makeInitial();
            return;
        }
//        Update_Query.updateInfo(Table.Guest, "STAFF_NAME", fullName, Main.staffID);
        Update_Query.updateInfo(Table.Guest, "CONTACT_NO", contactNumber, Main.guestID);
        String []names = fullName.split(" ");
        String first_name = (" ");
        String last_name = (" ");
        if(names.length < 2)
        {
            first_name = names[0];
            last_name = (" ");
        }else{
            first_name = names[0];
            last_name = names[1];
        }
//        else{
//            first_name = (names[0]);
//            for(int i=1; i<names.length; i++){
//                last_name = last_name + " " + names[i];
//            }
//            last_name = (last_name);
//        }
        Update_Query.updateInfo(Table.Guest, "FIRST_NAME", first_name, Main.guestID);
        Update_Query.updateInfo(Table.Guest, "LAST_NAME", last_name, Main.guestID);
        Update_Query.updateInfo(Table.Guest, "CONTACT_NO", contactNumber, Main.guestID);
        makeInitial();
    }

    private boolean areAllFieldsComplete() {
        boolean flag = false;
        flag = fullName.equals("") || fullName == null || contactNumber.equals("") || contactNumber == null;
        return !flag;
    }

    void changePassWindow() {
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);

        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        SettingsController controller = null;
        System.out.println("===>>Inside StaffController .. loadScene() Trying to Load  " + Scenes.settings_panel);
        try {
            loader.setLocation(this.getClass().getResource(Scenes.settings_panel));
            root = loader.load();
            controller = (SettingsController) loader.getController();
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }

        Scene scene = new Scene(root);

        newWindow.setScene(scene);

        controller.setOldPass(currentPass);
        controller.setCurrentStage(newWindow);

        newWindow.showAndWait();

        if (controller.isPassUpdated()) {
            // Update COdes here
            String newPass = controller.getNewPass();
            try {
                Update_Query.updatePassword(Table.Guest, Main.guestID, newPass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void setButtonPropertiesDefault() {
        for (int i = 0; i < TAB_SIZE; i++) {
            sceneButtons[i].getStyleClass().remove("toolbar-button-selected");
            sceneButtons[i].getStyleClass().add("toolbar-button");
//            sceneButtons[i].getStyleClass().add("button:hover");
        }
    }

    private void highlightButton(int buttonIndex) {
        setButtonPropertiesDefault();
        sceneButtons[buttonIndex].getStyleClass().remove("toolbar-button");
//        sceneButtons[buttonIndex].getStyleClass().remove("button:hover");
        sceneButtons[buttonIndex].getStyleClass().add("toolbar-button-selected");
//        sceneButtons[buttonIndex].setStyle("-fx-background-color: "+" -fx-blue-light");

    }

    final int TAB_INDEX_Profile = 0;
    final int TAB_INDEX_CheckGuest = 1;
    final int TAB_INDEX_Settings = 2;
    final int TAB_SIZE = 3;

    @FXML
    private void load_enter_into_studentList( ) {
        
        tabPane.getSelectionModel().select(TAB_INDEX_CheckGuest);
        highlightButton(TAB_INDEX_CheckGuest);
        
        List<Student_OfThisGuest> list = new ArrayList<>();
        System.out.println("-->>>ENTEER INTO StudentList");
        try{
             list = Query.getStudentsOfThisGuest(Main.guestID);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("==>>> LOADING STUDENTS OF THIS GUEST... PRINTING LIST .. .");
        
        for(Student_OfThisGuest s: list){
            System.out.println(s.toString());
        }
        
        showThisListToWindow(list);
    }
    /*
    private String studentID;
    private String studentFullName;
    private String hallName;
    private String relationWithGuest;

    */
    private void showThisListToWindow(List<Student_OfThisGuest> list) {
        
        ObservableList<Student_OfThisGuest> data = FXCollections.observableArrayList(list);

        col_student_ID.setCellValueFactory(
                new PropertyValueFactory<Student_ForGuardToSee, String>("studentID")
        );
        col_studentFullName.setCellValueFactory(
                new PropertyValueFactory<>("studentFullName")
        );
        col_relation.setCellValueFactory(
                new PropertyValueFactory<>("relationWithGuest")
        );
        col_hallName.setCellValueFactory(
                new PropertyValueFactory<>("hallName")
        );

        tableview.setItems(data);
    }
    
   
}
