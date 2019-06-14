/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.student.addNewGuest;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import hall_management.db.queries.InsertQueries;
import hall_management.db.queries.Query;
import hall_management.ui.popup.PopUpWindow;
import hall_management.ui.pushNotification.Notification;
import hall_management.ui.startPage.Main;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.Interface.Table;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

public class NewGuestFormController implements Initializable, Controller {

    @FXML
    private JFXTextField textField_contact;
    @FXML
    private JFXTextField textField_NID;
    @FXML
    private JFXTextField textField_address;
    @FXML
    private JFXTextField textField_Rel;
    @FXML
    private JFXTextField textField_firstName;
    @FXML
    private JFXTextField textField_lastName;

    private String guestNID;
    private String guestFirstName;
    private String guestLastName;
    private String guestAddress;
    private String guestContact;
    private String guestRelation;
    @FXML
    private JFXButton button_add;
    @FXML
    private JFXButton button_submit;

    private boolean isAlreadyInGuestTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setEditable(false);
        setVisible(false);
    }

    private void submitNewGuest() throws Exception {

//        setEditable(true);
        InsertQueries.insertGuest(guestNID, guestFirstName, guestLastName, guestContact, guestAddress);
        InsertQueries.insertGuestStudentRelation(guestNID, Main.studentID, guestRelation);

        System.out.println("<><><>Inside NewGuestFormController.submitNewGuest()... ");
        System.out.println("==-->> DISPLAYING POP UP WINDOW SUCCESS\n");

//        PopUpWindow.displaySuccess("SUCCESS", "Guest Added Success!!", "");
        Notification.push("SUCCESS", guestNID + "Added Successfully\nAdd Another Guest?", Notification.SUCCESS, Pos.CENTER);
        clearScreen();
        setEditable(false);
        System.out.println("\n\n");
    }

    private void submitOnlyInAllowedGuest() throws Exception {
//        InsertQueries.insertGuest(guestNID, guestFirstName, guestLastName, guestContact, guestAddress);
        InsertQueries.insertGuestStudentRelation(guestNID, Main.studentID, guestRelation);

        System.out.println("===--->>>> Inside NewGuestFormController.submitOnlyInAllowedGuest()... ");
        System.out.println("===--->>> DISPLAYING POP UP WINDOW SUCCESS");

        PopUpWindow.displaySuccess("SUCCESS", "Guest Added Success!!", "Add Another Guest!!");
        clearScreen();
        setEditable(false);
        System.out.println("\n\n");
    }

    @FXML
    private void loadFullForm() throws Exception {
        guestNID = textField_NID.getText();

        try {
            int nid = Integer.parseInt(guestNID);
        } catch (NumberFormatException e) {
//            PopUpWindow.displayInCheck("ERROR!!", "ENTER VALID NID!!");
            Notification.push("ERROR!!", "ENTER VALID NID!!",Notification.FAILURE,Pos.CENTER);
            clearScreen();
            return;
        }
//        boolean flag = Query.isIn(Table.Allowed_Guest, guestNID);
        if (guestNID.equals("") || guestNID.equals(null)) {
//            PopUpWindow.displayInCheck("ERROR!!", "ENTER NID!!");
            Notification.push("ERROR!!", "ENTER a NID Please!!",Notification.FAILURE,Pos.CENTER);
            clearScreen();
            return;
        }
        boolean flag = Query.checkIsInUsingTwoColumns(Table.Allowed_Guest, guestNID, Main.studentID, "NID", "STUDENT_ID");
        if (flag == true) {
//            setEditable(false);
//            PopUpWindow.displayInCheck("ERROR", "THIS NID ALREADY EXISTS in your list!!");
            Notification.push("NO NEED TO INSERT!!", "THIS NID ALREADY EXISTS in your list!!",Notification.FAILURE,Pos.CENTER);
            clearScreen();
            return;
        }
        isAlreadyInGuestTable = Query.isIn(Table.Guest, guestNID);
        setVisible(true);
        if (isAlreadyInGuestTable == true) {
            ///Load 
//            String []arr = Query.getGuestInfo(guestNID);
            setEditable(false);
            guestFirstName = Query.getFieldInfoFromTable(Table.Guest, guestNID, "FIRST_NAME");
            guestLastName = Query.getFieldInfoFromTable(Table.Guest, guestNID, "LAST_NAME");
            guestAddress = Query.getFieldInfoFromTable(Table.Guest, guestNID, "ADDRESS");
            guestContact = Query.getFieldInfoFromTable(Table.Guest, guestNID, "CONTACT_NO");
            setText(guestFirstName, guestLastName, guestAddress, guestContact);
            guestRelation = textField_Rel.getText();
            textField_Rel.setEditable(true);
        } else {
            setEditable(true);
            guestFirstName = textField_firstName.getText();
            guestLastName = textField_lastName.getText();
            guestAddress = textField_address.getText();
            guestContact = textField_contact.getText();
            guestRelation = textField_Rel.getText();
        }

    }

    @FXML
    private void backToMain(ActionEvent event) {
        SceneLoader.closeScene(Scenes.student_newGuestForm);
//        Controller loadScene = SceneLoader.loadScene(Scenes.student_ui, this);
//        loadScene.
        SceneLoader.loadPreviousScene(Scenes.student_ui, this);
    }

    private void setEditable(boolean b) {

        textField_contact.setEditable(b);

//        textField_NID.setEditable(b);
//        textField_NID.setEditable(b);
        textField_address.setEditable(b);

        textField_Rel.setEditable(b);

        textField_firstName.setEditable(b);

        textField_lastName.setEditable(b);
    }

    private void setVisible(boolean b) {

        textField_contact.setVisible(b);

//        textField_NID.setEditable(!b);
//        textField_NID.setEditable(b);
        textField_address.setVisible(b);

        textField_Rel.setVisible(b);

        textField_firstName.setVisible(b);

        textField_lastName.setVisible(b);

        button_add.setVisible(b);
    }

    private boolean allFieldsComplete() {
        boolean anyEmpty = true;
        guestNID = textField_NID.getText();
        guestFirstName = textField_firstName.getText();
        guestLastName = textField_lastName.getText();
        guestAddress = textField_address.getText();
        guestContact = textField_contact.getText();
        guestRelation = textField_Rel.getText();

        anyEmpty = (guestNID.equals(null)
                || guestFirstName.equals(null)
                || guestLastName.equals(null)
                || guestContact.equals(null)
                || guestRelation.equals(null)
                || guestAddress.equals(null)
                || guestNID.equals("")
                || guestFirstName.equals("")
                || guestLastName.equals("")
                || guestContact.equals("")
                || guestRelation.equals("")
                || guestAddress.equals(""));

        return !anyEmpty;
    }

    private void clearScreen() {
        textField_NID.setText("");
        textField_firstName.setText("");
        textField_lastName.setText("");
        textField_address.setText("");
        textField_contact.setText("");

        textField_Rel.setText("");
    }

    private void printTextFieldStrings() {
        System.out.println("Printing all textFields... ");
        System.out.println(guestNID);
        System.out.println(guestFirstName);
        System.out.println(guestLastName);
        System.out.println(guestAddress);
        System.out.println(guestContact);
        System.out.println(guestRelation);
        System.out.println("PRINTING DONE!!.. ");

    }

    @FXML
    private void submitGuest() throws Exception {
        System.out.println("<><><>Inside NewGuestFormController.submitGuest() method ... ");
        printTextFieldStrings();
        if (allFieldsComplete() == false) {
            PopUpWindow.displayInCheck("ERROR", "ALL FIELDS MUST HAVE SOME VALUES!");
            return;
        }
        if (isAlreadyInGuestTable == true) {
            submitOnlyInAllowedGuest();
        } else {
            submitNewGuest();
        }
    }

    private void setText(String guestFirstName, String guestLastName, String guestAddress, String guestContact) {
        textField_lastName.setText(guestLastName);
        textField_firstName.setText(guestFirstName);
        textField_contact.setText(guestContact);
        textField_address.setText(guestAddress);
    }

}
