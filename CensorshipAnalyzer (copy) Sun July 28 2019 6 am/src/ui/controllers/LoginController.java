/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import util.loader.SceneLoader;
import util.loader.Scenes;
import util.client.TCPClient;
import main.Main;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoginController {

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField password;

    @FXML
    private JFXButton login;
    
    @FXML
    private Hyperlink skip;

    @FXML
    void setlogin(ActionEvent event) {
        String email_ = email.getText().trim();
        String password_ = password.getText().trim();
        String send_message = "login#"+email_+"$"+password_;
        TCPClient client = new TCPClient();
        String str = client.login_send_receive(send_message);
        String []str_id_name = str.split("#");
        int userid = Integer.parseInt(str_id_name[0]);
        Main.USER_ID = userid;
        if(Main.USER_ID!=0){
            Main.USER_NAME = str_id_name[1];
            System.out.println("user name: "+Main.USER_NAME);
            SceneLoader.loadSceneInSameStage(Scenes.homeScreenFXML);
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Invalid email or password", "Dialog",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @FXML
    void skip_login(ActionEvent event) {
        
        SceneLoader.loadSceneInSameStage(Scenes.homeScreenFXML);
        
    }

}
