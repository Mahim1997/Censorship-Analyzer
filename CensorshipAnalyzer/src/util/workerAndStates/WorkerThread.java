/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.workerAndStates;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import javafx.application.Platform;
import main.Config;
import ui.controllers.CensoredRecordController_Waiting;
import ui.model.Report;
import util.loader.Scenes;

/**
 *
 * @author mahim
 */
//Use this in the initializable method ... 
//new Thread(new Worker(this)).start();
public class WorkerThread implements Runnable {

    public String fxmlToRun;

    CensoredRecordController_Waiting controller_censored_waiting;

    long startTime;

    int delayHowMuch = 5;   //How many seconds  [eg. 5]

    long delayTime = delayHowMuch * 1000; // * 1000 to get the ms

    public boolean willRun = true;

    public WorkerThread(CensoredRecordController_Waiting con) {
        this.startTime = System.currentTimeMillis();
        this.controller_censored_waiting = con;
    }

    public void setFxmlToRun(String fxmlToRun) {
        this.fxmlToRun = fxmlToRun;
    }

    public void setWillRun(boolean flag) {
        this.willRun = flag;
    }

    @Override
    public void run() {
        //if conditions ... 
        if (this.fxmlToRun.equals(Scenes.censoredRecordsWaitingFXML)) {
            runForCensoredRecordController_Waiting();
        }
    }

   

    private void runForCensoredRecordController_Waiting() {

        try {
            System.out.println("Initiating server .... ");
            DatagramSocket serverSocket = new DatagramSocket(Config.PORT_JAVA);

            byte[] receiveData = new byte[Config.RECEIVE_BYTES];

            while (willRun) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String receivedString = new String(receivePacket.getData());
                System.out.println("RECEIVED: " + receivedString);

                Report report = StringProcessor.processStringAndFormReport(receivedString);
                Platform.runLater(() -> {
                    this.controller_censored_waiting.refreshInfo(report);
                });

            }
        } catch (SocketException ex) {
            System.out.println("Socket Exception in runForCensoredController_Waiting in WorkerThread.java");
//            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("I/O Exception in runForCensoredController_Waiting in WorkerThread.java");
        }
    }

}

/*
    private void runForCensoredRecordController_Waiting() {
        while (willRun) {
            long timeNow = System.currentTimeMillis();
            if ((timeNow - startTime) == delayTime) {
//                Platform.runLater(() -> {
//                    controller_censored_waiting.refreshInfo();
//                });
                controller_censored_waiting.refreshInfo();
                startTime = timeNow;    //Update the startTime ... 
            }
        }
    }
 */
