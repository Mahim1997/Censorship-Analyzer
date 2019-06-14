package hall_management.ui.staff;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import hall_management.db.queries.Query;
import hall_management.db.queries.Update_Query;
import hall_management.ui.popup.PopUpWindow;
import hall_management.ui.settings.SettingsController;
import hall_management.ui.startPage.Main;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.Interface.Table;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StaffController implements Initializable, Controller {

    public static String Staff_jobType;
    @FXML
    private JFXTextField textLabel_staffID;
    @FXML
    private JFXTextField textLabel_contactNo;
    @FXML
    private JFXTextField textLabel_jobType;
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
    @FXML
    private JFXButton button_loadSettings;
    @FXML
    private JFXButton button_GuestLog;
    @FXML
    private JFXButton button_signOut;

    private String fullName;
    private String jobType;
    private String contactNumber;
    JFXButton[] sceneButtons = null;

    String staffId;
    String currentPass;
    @FXML
    private JFXTabPane tabPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeInitial();

        sceneButtons = new JFXButton[TAB_SIZE];

        sceneButtons[TAB_INDEX_Profile] = button_loadProfile;
        sceneButtons[TAB_INDEX_CheckGuest] = button_GuestLog;
        sceneButtons[TAB_INDEX_Settings] = button_loadSettings;

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
    }

    private void setTextFeildsToStrings() {
        textLabel_staffID.setText(Main.staffID);
        textLabel_contactNo.setText(contactNumber);
        textLabel_jobType.setText(jobType);
        textLabel_FullName.setText(fullName);
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public void setCurrentPass(String currentPass) {
        this.currentPass = currentPass;
    }

    private void getTextsFromTextFields() {
        contactNumber = textLabel_contactNo.getText();
        fullName = textLabel_FullName.getText();
        jobType = textLabel_jobType.getText();
    }

    private void setAllEditable(boolean flag) {
        textLabel_staffID.setEditable(false);
        textLabel_contactNo.setEditable(flag);
        textLabel_jobType.setEditable(false);
        textLabel_FullName.setEditable(flag);
    }

    @FXML
    private void refreshInfo() throws Exception {
        ///LOADS INFO
        fullName = Query.getFieldInfoFromTable(Table.Staff, Main.staffID, "STAFF_NAME");
        jobType = Query.getFieldInfoFromTable(Table.Staff, Main.staffID, "JOB_TYPE");
        StaffController.Staff_jobType = jobType;
        contactNumber = Query.getFieldInfoFromTable(Table.Staff, Main.staffID, "CONTACT_NO");

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
    private void load_enter_into_guestLog() {
//        if(StaffController.Staff_jobType.toUpperCase().equals("SECURITY GUARD") == false){
//            PopUpWindow.displayInCheck("NOT APPLICABLE", "ONLY APPLICABLE FOR GUARD");
//            return ;
//        }
        SceneLoader.loadScene(Scenes.staff_SecurityGuard, this);
        tabPane.getSelectionModel().select(TAB_INDEX_CheckGuest);
        highlightButton(TAB_INDEX_CheckGuest);
    }

    @FXML
    private void signOut() {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.startPage_ui, this);
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
        Update_Query.updateInfo(Table.Staff, "STAFF_NAME", fullName, Main.staffID);
        Update_Query.updateInfo(Table.Staff, "CONTACT_NO", contactNumber, Main.staffID);
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
                Update_Query.updatePassword(Table.Staff, Main.staffID, newPass);
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

}
