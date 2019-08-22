package ui.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import ui.model.ModelURL;
import ui.sounds.Notification;

public class FileViewerController implements Initializable {

    private String fileName;
    private String absFileName;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Text text_fileName;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn column_index;
    @FXML
    private TableColumn column_url;

    public void setFileName(String name, String absPath) {
        this.text_fileName.setText(name);
        this.fileName = name;
        this.absFileName = absPath;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Setting up Table for File name: " + this.fileName);

    }

    private void showDataToScreen(List<ModelURL> list) {
        System.out.println("PRINTING List ");
        list.forEach((t) -> {
            t.printModel();
        });

        ObservableList<ModelURL> data = FXCollections.observableArrayList(list);

        column_index.setCellValueFactory(
                new PropertyValueFactory<>("index")
        );
        column_url.setCellValueFactory(
                new PropertyValueFactory<>("url")
        );

        this.tableView.setItems(data);
    }

    private List<ModelURL> loadFromFile() throws FileNotFoundException, IOException {

        List<ModelURL> list = new ArrayList<>();
        System.out.println("-->Absolute File name is : " + this.absFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(this.absFileName))) {
            String line = reader.readLine();
            int index = 1;
            while (line != null) {
//                System.out.println(line);
                // read next line
                line = reader.readLine();

                if (line != null) {
                    list.add(new ModelURL(index, line));
                    index++;
                }

            }
        }

        return list;
    }

    public void showData() {
        List<ModelURL> list = new ArrayList<>();
        try {
            list = loadFromFile();
        } catch (FileNotFoundException ex) {
            Notification.push("Error", "Can't load data from file for unknown reasons 1.", Notification.FAILURE, Pos.BOTTOM_RIGHT);
        } catch (IOException ex) {
            Notification.push("Error", "Can't load data from file for unknown reasons 2.", Notification.FAILURE, Pos.BOTTOM_RIGHT);
        }
        showDataToScreen(list); //Show this data to screen in the table-view

    }

}

/*
ObservableList<Student_OfThisGuest> data = FXCollections.observableArrayList(list);

        col_student_ID.setCellValueFactory(
                new PropertyValueFactory<>("studentID")
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
 */
