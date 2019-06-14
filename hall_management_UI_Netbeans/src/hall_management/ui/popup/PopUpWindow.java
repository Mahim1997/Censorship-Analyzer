/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.popup;

import hall_management.ui.pushNotification.Notification;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import static network.PopUpWindow.btn;

/**
 *
 * @author esfs
 */
public class PopUpWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static void displayInCheck(String title, String messageToDisplay) {
        
        Notification.push(title, messageToDisplay, Notification.WARNING, Pos.CENTER);
        
        return;
//        Stage stage = new Stage();
//
//        stage.initModality(Modality.APPLICATION_MODAL); // Block Input events with other windows until this closes
//        stage.setTitle(title);
//
//        stage.setMinWidth(500);
//        stage.setMinHeight(300);
//        Label label = new Label();
//        label.setText(messageToDisplay);
//        Button btn = new Button();
//        btn = new Button("Close");
//        btn.setOnAction((event) -> {
//            stage.close();
//        });
//
//        VBox layout = new VBox();
//        layout.getChildren().addAll(label, btn);
//        layout.setAlignment(Pos.CENTER);
//
//        Scene scene = new Scene(layout);
//        stage.setScene(scene);
//        stage.showAndWait();    //DISPLAY ALERT BOX and before returning it should be cloes
    }

    public static void displaySuccess(String title, String messageToDisplay, String btnMessage) {
        
        Notification.push(title, messageToDisplay+"\n"+btnMessage, Notification.SUCCESS, Pos.CENTER);

       
//        Stage stage = new Stage();
//
//        stage.initModality(Modality.APPLICATION_MODAL); // Block Input events with other windows until this closes
//        stage.setTitle(title);
//
//        stage.setMinWidth(750);
//        stage.setMinHeight(500);
//        
//        Label label = new Label();
//        label.setText(messageToDisplay);
//        
//        Button btn = new Button();
//        
//        btn = new Button(btnMessage);
//        btn.setOnAction((event) -> {
//            stage.close();
//        });
//
//        VBox layout = new VBox();
//        layout.getChildren().addAll(label, btn);
//        layout.setAlignment(Pos.CENTER);
//
//        Scene scene = new Scene(layout);
//        stage.setScene(scene);
//        stage.showAndWait();    //DISPLAY ALERT BOX and before returning it should be cloes
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        displayInCheck("TITLE","msg");
        displaySuccess("ERROR", "FUCK OFF", "DONT CLOSE MF");
    }
}

//public class PopUpWindow extends Application {
//    public static void main(String[] args) {
//        launch(args);
//    }
//    static Button btn;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
////        Test.displayInCheck("TITLE", "HELLO");
//    }
//
//}
