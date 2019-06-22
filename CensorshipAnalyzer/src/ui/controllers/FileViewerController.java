 
package ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

 
public class FileViewerController implements Initializable{
    private String fileName;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Text text_fileName;
    @FXML
    private TableView<String> tableView;
    
    public void setFileName(String name){
        this.text_fileName.setText(name);
        this.fileName = name;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Setting up Table for File name: " + this.fileName);
        
        //Read each line
        //Place in the table view
         
    }

  
    
    
}
