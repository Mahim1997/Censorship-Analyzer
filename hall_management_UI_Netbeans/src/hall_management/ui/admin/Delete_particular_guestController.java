/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.admin;

import hall_management.db.queries.adminQueries.AdminQueries;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author esfs
 */
public class Delete_particular_guestController implements Initializable, Controller {

    @FXML
    private Text text_UporerPart;
    @FXML
    private TableView tableview;
    @FXML
    private TableColumn col_NID;
    @FXML
    private TableColumn col_fullName;
    @FXML
    private TableColumn col_contactNo;
    @FXML
    private TableColumn col_Address;
    @FXML
    private TableColumn col_btn_delete;
    @FXML
    private TableColumn  col_no_of_students;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("=->>Inside initialize().. \n");
        try {
            // TODO

            loadTableView();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadTableView() throws Exception {
        List<Guests> list = AdminQueries.getListOfGuests();
        System.out.println("=-->>PRINTING LIST .. ");
        list.forEach((t) -> {
            System.out.println(t.toString());
        });
        loadToWindow(list);
    }

    /*
        public String NID;
    public String fullName;
    public String contactNumber;
    public String address;
    public Button deleteButton;
     */
    private void loadToWindow(List<Guests> list) {
        List<Guests> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Guests g = list.get(i);
            list2.add(new Guests(g.getNID(), g.getFullName(), g.getContactNumber(), g.getAddress(), g.getNoOfStudents(), this, "DELETE GUEST"));
        }
        ObservableList<Guests> data = FXCollections.observableArrayList(list2);
        col_NID.setCellValueFactory(
                new PropertyValueFactory<>("NID")
        );
        col_fullName.setCellValueFactory(
                new PropertyValueFactory<>("fullName")
        );
        col_Address.setCellValueFactory(
                new PropertyValueFactory<>("address")
        );
        col_contactNo.setCellValueFactory(
                new PropertyValueFactory<>("contactNumber")
        );
        col_btn_delete.setCellValueFactory(
                new PropertyValueFactory<>("deleteButton")
        );
        col_no_of_students.setCellValueFactory(
                new PropertyValueFactory<>("noOfStudents")
        );
        tableview.setItems(data);
    }

    void runDeleteGuest(String NID) throws Exception {
        System.out.println("=->>RUN delete guest NID = " + NID);
//        runAreYouSureWindow();
        AdminQueries.deleteRowFromTable(Table.Guest, "NID", NID);
        loadTableView();
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.admin_ui, this);
    }

    private void runAreYouSureWindow() {
//        Stage stage = new Stage();
        
    }

}
