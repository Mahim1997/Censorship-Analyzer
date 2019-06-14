 
package hall_management.ui.admin;

import hall_management.db.queries.Functions;
import hall_management.ui.settings.SettingsController;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author suban
 */
public class AdminController implements Initializable,Controller {

//    private JFXTabPane tabPane;
//    private JFXButton button_loadProfile;
//    private JFXButton button_loadHall;
//    private JFXButton button_loadGuest;
//    private JFXButton button_loadEvents;
//    private JFXButton button_loadTeams;
//    private JFXButton button_loadSettings;
//    private JFXButton button_loadStudent;
//    private JFXButton button_loadTeacher;
    
    
    String adminID;
    String password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        setSceneButtons();
//        highlightButton(TAB_INDEX_Profile);
    }    

    
//    JFXButton[] sceneButtons = null;
//
//    void setSceneButtons(){
//        sceneButtons = new JFXButton[TAB_SIZE];
//
//        sceneButtons[TAB_INDEX_Profile] = button_loadProfile;
//        sceneButtons[TAB_INDEX_Hall] = button_loadHall;
//        sceneButtons[TAB_INDEX_Student] = button_loadStudent;
//        sceneButtons[TAB_INDEX_Teacher] = button_loadTeacher;
//        sceneButtons[TAB_INDEX_Guests] = button_loadGuest;
//        sceneButtons[TAB_INDEX_Events] = button_loadEvents;
//        sceneButtons[TAB_INDEX_Teams] = button_loadTeams;
//        sceneButtons[TAB_INDEX_Settings] = button_loadSettings;
//
//    }
//    
 
//    private void setButtonPropertiesDefault() {
//        for (int i = 0; i < TAB_SIZE; i++) {
//            sceneButtons[i].getStyleClass().remove("toolbar-button-selected");
//            sceneButtons[i].getStyleClass().add("toolbar-button");
////            sceneButtons[i].getStyleClass().add("button:hover");
//        }
//    }
//
//    private void highlightButton(int buttonIndex) {
//        setButtonPropertiesDefault();
//        sceneButtons[buttonIndex].getStyleClass().remove("toolbar-button");
////        sceneButtons[buttonIndex].getStyleClass().remove("button:hover");
//        sceneButtons[buttonIndex].getStyleClass().add("toolbar-button-selected");
////        sceneButtons[buttonIndex].setStyle("-fx-background-color: "+" -fx-blue-light");
//
//    }
//    
   
    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//
//
//    private void loadHall(Event event) {
//        tabPane.getSelectionModel().select(TAB_INDEX_Hall);
//        highlightButton(TAB_INDEX_Hall);
//    }
//
//
//
//    private void loadGuest(Event event) {
//        tabPane.getSelectionModel().select(TAB_INDEX_Guests);
//        highlightButton(TAB_INDEX_Guests);
//    }
//
//
//    private void loadEvents(Event event) {
//        tabPane.getSelectionModel().select(TAB_INDEX_Events);
//        highlightButton(TAB_INDEX_Events);
//    }
//
//    private void loadTeams(Event event) {
//        tabPane.getSelectionModel().select(TAB_INDEX_Teams);
//        highlightButton(TAB_INDEX_Teams);
//    }
//
//    private void loadProfile(ActionEvent event) {
//        tabPane.getSelectionModel().select(TAB_INDEX_Profile);
//        highlightButton(TAB_INDEX_Profile);
//    }
//
//
//    private void loadSettings(ActionEvent event) {
//        int prevSelTabIndex = tabPane.getSelectionModel().getSelectedIndex();
//
//        highlightButton(TAB_INDEX_Settings);
//
//        System.out.println("Load Settings Window");
//        changePassWindow();
//        highlightButton(prevSelTabIndex);
//    }

    @FXML
    private void logOutSession(ActionEvent event) {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.startPage_ui, this);
    }
//
//    private void loadStudent(ActionEvent event) {
//        tabPane.getSelectionModel().select(TAB_INDEX_Student);
//        highlightButton(TAB_INDEX_Student);        
//    }
//
//    private void loadTeacher(ActionEvent event) {
//        tabPane.getSelectionModel().select(TAB_INDEX_Teacher);
//        highlightButton(TAB_INDEX_Teacher);        
//    }

    
    void changePassWindow() {
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);

        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        SettingsController controller = null;
        System.out.println("===>>Inside StudentController .. loadScene() Trying to Load  " + Scenes.settings_panel);
        try {
            loader.setLocation(this.getClass().getResource(Scenes.settings_panel));
            root = loader.load();
            controller = (SettingsController) loader.getController();
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }

        Scene scene = new Scene(root);

        newWindow.setScene(scene);

        controller.setOldPass(password);
        controller.setCurrentStage(newWindow);

        newWindow.showAndWait();

        if (controller.isPassUpdated()) {
            // Update COdes here
            String newPass = controller.getNewPass();
            try {
//                Update_Query.updatePassword(Table.Student, Main.studentID, newPass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

 
    
    
    
    

    
    
    
    
//    final int TAB_INDEX_Profile = 0;
//    final int TAB_INDEX_Student = 1;
//    final int TAB_INDEX_Teacher = 2;
//    final int TAB_INDEX_Hall = 3;
//    final int TAB_INDEX_Guests = 4;
//    final int TAB_INDEX_Events = 5;
//    final int TAB_INDEX_Teams = 6;
//    final int TAB_INDEX_Settings = 7;
//    final int TAB_SIZE = 8;

    @FXML
    private void deleteLogsOfRange(ActionEvent event) {
        SceneLoader.loadScene(Scenes.admin_deleteLogs, this);
    }

    @FXML
    private void addPerson(ActionEvent event) {
        SceneLoader.loadScene(Scenes.admin_addPerson, this);
    }

    @FXML
    private void addEvent(ActionEvent event) {
        SceneLoader.loadScene(Scenes.admin_addEvent, this);
    }

    @FXML
    private void updatePerson(ActionEvent event) {
        SceneLoader.loadScene(Scenes.admin_updatePerson, this);
    }

    @FXML
    private void updateProvost(ActionEvent event) {
        SceneLoader.loadScene(Scenes.admin_modifyHall, this);
    }

    @FXML
    private void deleteVoidGuests( )throws Exception{
        Functions.procedureDeleteVoidGuests();
    }

    @FXML
    private void deleteParticularGuest() throws Exception{
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadScene(Scenes.admin_delete_particular_guest, this);
        
    }
}
