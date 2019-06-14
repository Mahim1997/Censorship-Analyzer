/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.student.formingTeamApply;

import hall_management.db.queries.Query;
import hall_management.db.queries.more_queries.Team_Queries;
import hall_management.ui.staff.GuestWithButton;
import hall_management.ui.staff.Student_ForGuardToSee;
import hall_management.ui.startPage.Main;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Table;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class LoadStudentsOfHallController implements Initializable, Controller {

    @FXML
    private TableView  tableview;
    @FXML
    private TableColumn col_studentID;
    @FXML
    private TableColumn col_fullName;
    @FXML
    private TableColumn col_studentType;
    @FXML
    private Text text_UporerPart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String text = null;
        String hallID = null;
        String hallName = null;
        try{
            hallID = Query.getFieldInfoFromTable(Table.Student, Main.studentID, "HALL_ID");
            hallName = Query.getFieldInfoFromTable(Table.Hall, hallID, "HALL_NAME");
        }catch(Exception e){
            e.printStackTrace();
        }
        text = "Loading Students of HallID : " + hallID + " , Hall Name : " + hallName ;
        text_UporerPart.setText(text);
        try {
            loadTableView();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
    private void loadTableView() throws Exception{
        List<Students_OfThisHall> list = new ArrayList<>();
        list = Team_Queries.getStudentsOfHallAsStudent(Main.studentID);
        ObservableList<Students_OfThisHall> data = FXCollections.observableArrayList(list);

        col_studentID.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        col_fullName.setCellValueFactory(
                new PropertyValueFactory<>("fullName")
        );
        col_studentType.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );

        tableview.setItems(data);
    }
    
    
}
