/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.admin;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;

/**
 *
 * @author esfs
 */
public class Guests {

    public String NID;
    public String fullName;
    public String contactNumber;
    public String address;
    public Button deleteButton;
    public String noOfStudents;

    public Guests(String NID, String fullName, String contactNumber, String address, String status) {
        this.NID = NID;
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.noOfStudents = status;
    }
    public Guests() {
    }

    public String getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(String noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public Guests(String NID, String fullName, String contactNumber, String address, String status, 
            Delete_particular_guestController controller, String buttonText) {
        this.NID = NID;
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.noOfStudents = status;
        deleteButton = new Button(buttonText);
        deleteButton.setOnAction((event) -> {
            try {
                controller.runDeleteGuest(NID);
            } catch (Exception ex) {
                Logger.getLogger(Guests.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

//    public Guests(String NID, String fullName, String contactNumber, String address) {
//        this.NID = NID;
//        this.fullName = fullName;
//        this.contactNumber = contactNumber;
//        this.address = address;
//    }

    @Override
    public String toString() {
        return "Guests{" + "NID=" + NID + ", fullName=" + fullName + ", contactNumber=" + contactNumber + ", address=" + address + ", deleteButton=" + deleteButton + ", noOfStudents=" + noOfStudents + '}';
    }

 

 

    public Guests(Delete_particular_guestController controller, String buttonText) {
        deleteButton = new Button(buttonText);
        deleteButton.setOnAction((event) -> {
            try {
                controller.runDeleteGuest(NID);
            } catch (Exception ex) {
                Logger.getLogger(Guests.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

}
