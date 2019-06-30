/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author mahim
 */
public class TestController implements Initializable{

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Text text_fileName;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> column_index;
    @FXML
    private TableColumn<?, ?> column_url;
    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("---->>> Inside initialize() .... for TestController.java");
        loadImage();
    }

    private void loadImage() {
        this.imageView = new ImageView("resources/images_testSites/X_transparent.png");
//            this.imageView.setImage(new Image(new FileInputStream("X_transparent.png")));
    }
    
}
