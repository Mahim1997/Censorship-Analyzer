/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.student;

import hall_management.db.queries.InsertQueries;
import hall_management.util.Controller;
import hall_management.ui.popup.PopUpWindow;
import hall_management.ui.startPage.Main;
import hall_management.ui.startPage.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import hall_management.db.queries.Query;
import hall_management.util.SceneLoader;

/**
 * FXML Controller class
 *
 * @author esfs
 */
public class Student_addGuestFormController implements Initializable, Controller {

    private String studentID;
    @FXML
    private TextField textField_NID;
    @FXML
    private TextField textField_name;
    @FXML
    private TextField textField_contact;
    @FXML
    private TextField textField__addr;
    @FXML
    private TextField textField_Relation;
    @FXML
    private Button button_Submit;
    @FXML

    private Button button_Back;
    private String guestNID;
    private String guestName;
    private String guestContact;
    private String guestRelation;
    private String guestAddress;

    @FXML
    public void goBack() {
        SceneLoader.loadScene(SceneLoader.student_ui, this);
    }

    public void setStudentScene(Scene studentScene) {
        this.studentScene = studentScene;
    }

    Main mainClass;
    Scene studentScene;

    public void setMainClass(Main mainClass) {
        this.mainClass = mainClass;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void setEditableAll(boolean flag) {
        //        studentID_field.setText(Main.studentID);

        textField_NID.setEditable(flag);

        textField_name.setEditable(flag);

        textField_contact.setEditable(flag);

        textField__addr.setEditable(flag);

        textField_Relation.setEditable(flag);

        button_Submit.setOnAction((event) -> {
            System.out.println("Consume Event.. ");
            event.consume();
        });

        button_Back.setOnAction((event) -> {
            System.out.println("Consume Event... ");
            event.consume();

        });

//        studentID_field.setEditable(flag);
    }

    @FXML
    public void submitGuest() throws Exception {
        System.out.println("<><>Inside Student_addGuestFormController.submitGuest() .... ");

        guestNID = textField_NID.getText();
        guestName = textField_name.getText();
        guestAddress = textField__addr.getText();
        guestContact = textField_contact.getText();
        guestRelation = textField_Relation.getText();
        
        
        printTextFieldStrings();

        if (allFieldsComplete() == false) {
            PopUpWindow.displayInCheck("Error", "Required filled can't be null");
            return;
        }

        boolean flag = InsertQueries.isIn("Guest", guestNID);

        if (flag == true) {
            PopUpWindow.displayInCheck("ERROR!", "Guest With NID already Present!");
            clearScreen();
            return;
        }

        InsertQueries.insertGuest("GUEST", guestNID, guestName, guestContact, guestAddress);
        InsertQueries.insertGuestStudentRelation("ALLOWED_GUEST", guestNID, Main.studentID, guestRelation);

//        PopUpWindow.displ
        PopUpWindow.displaySuccess("GUEST ADDED !!", "CLICK TO RETURN", "GO BACK");
        clearScreen();
        System.out.println("<><><><>DONE!!\n\n");
//        goBack();
//        setEditableAll(false);
    }

    private boolean allFieldsComplete() {
        boolean anyEmpty = true;
        guestNID = textField_NID.getText();
        guestName = textField_name.getText();
        guestAddress = textField__addr.getText();
        guestContact = textField_contact.getText();
        guestRelation = textField_Relation.getText();

        anyEmpty = (guestNID.equals(null)
                || guestName.equals(null)
                || guestContact.equals(null)
                || guestRelation.equals(null)
                || guestAddress.equals(null)
                || guestNID.equals("")
                || guestName.equals("")
                || guestContact.equals("")
                || guestRelation.equals("")
                || guestAddress.equals(""));

        return !anyEmpty;
    }

    private void clearScreen() {
        textField_NID.setText("");
        textField_name.setText("");
        textField__addr.setText("");
        textField_contact.setText("");

        textField_Relation.setText("");
    }

    private void printTextFieldStrings() {
        System.out.println("Printing all textFields... ");
        System.out.println(guestNID);
        System.out.println(guestName);
        System.out.println(guestAddress);
        System.out.println(guestContact);
        System.out.println(guestRelation);
        System.out.println("PRINTING DONE!!.. ");

    }

}
