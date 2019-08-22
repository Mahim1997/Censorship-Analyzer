/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controllers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import main.Config;

/**
 *
 * @author mahim
 */
public class RealTimeDetailsController implements Initializable {

    String stringStored = "";
    @FXML
    private TextArea textArea;
//    NetworkReader netreader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("-->>Inside RealTimeDetailsController.java ... ");
        this.textArea.setText("");
        new Thread(new NetworkReader(this)).start();
//        new Thread(new WorkerThreadForDetails(this)).start();
    }

    public void appendText(String s) {
        System.out.println("-->>In appendText text = <" + s + ">");

        this.stringStored += s;

        this.textArea.setStyle("-fx-background-color:#38ee00;");
//        this.textArea.setText(this.stringStored);
        if (s != null) {
            this.textArea.appendText(s);
//            this.textArea.appendText("\n");
        }

    }

}

class NetworkReader implements Runnable {

    private final RealTimeDetailsController controller;

    public NetworkReader(RealTimeDetailsController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        try (DatagramSocket serverSocket = new DatagramSocket(Config.PORT_JAVA_SECONDARY)) {  //try-with-resources ... auto-close socket
            byte[] receiveData = new byte[Config.RECEIVE_BYTES];
//                while (this.controller_censored_waiting.reportsListToBeRefreshed.size() < this.controller_censored_waiting.numberOfReportsNeeded) {  //should be while ???
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String receivedString = new String(receivePacket.getData());

                System.out.println("-->>In run() of RealTimeDetailsController.java ... msg is <" + receivedString + ">");

                this.controller.appendText(receivedString);

                if (receivedString.contains("END_THING")) { //end
                    break;
                }

            }
        } catch (IOException ex) {
            System.out.println("-->>Error in run() of NetworkReader class");
        }
    }

}
