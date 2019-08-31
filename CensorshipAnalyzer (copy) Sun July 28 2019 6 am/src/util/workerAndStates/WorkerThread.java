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
import util.client.TCPClient;
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
        System.out.println("-->>> WokerThread constructor ... ");
    }

    public void setFxmlToRun(String fxmlToRun) {
        this.fxmlToRun = fxmlToRun;
    }

    public synchronized void setWillRun(boolean flag) {
        System.out.println("--++-->> Worker.setWillRun(" + flag + ") is called");
        this.willRun = flag;
    }

    @Override
    public void run() {
        //if conditions ... 
        System.out.println("+++++>>>>>>>> WorkerThread.run() method initialising .... ");
        if (this.fxmlToRun.equals(Scenes.censoredRecordsWaitingFXML)) {
            runForCensoredRecordController_Waiting();
        }
        System.out.println("+++>>> WorkerThread.run() ends .... ");
    }

    private void runForCensoredRecordController_Waiting() {

        try {
            System.out.println("<><><><><><><><><> Initiating server .... inside WorkerThread.runForCensoredRecordController_Waiting() ...... <><><><><> ");
            try (DatagramSocket serverSocket = new DatagramSocket(Config.PORT_JAVA)) {  //try-with-resources ... auto-close socket
                byte[] receiveData = new byte[Config.RECEIVE_BYTES];
                System.out.println("Inside WorkerThread ... listReports.size = " + this.controller_censored_waiting.reportsListToBeRefreshed.size() + " , "
                        + "this.controller.numReports = " + this.controller_censored_waiting.numberOfReportsNeeded);
//                while (this.controller_censored_waiting.reportsListToBeRefreshed.size() < this.controller_censored_waiting.numberOfReportsNeeded) {  //should be while ???
                while (this.willRun == true) {
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);

                    String receivedString = new String(receivePacket.getData());
                    System.out.println("RECEIVED: " + receivedString);

                    //EXIT CHECKING BEGIN
                    if (receivedString.contains(Config.END_MESSAGE)) {
                        this.willRun = false;
                        break;
                    }
                    //EXIT CHECKING DONE

                    Report report = StringProcessor.processStringAndFormReport(receivedString);
                    TCPClient client = new TCPClient();
//                    String s = client.formReportNetworkUser(report);

                    if ((Config.SEND_TO_SERVER == true) && (report.getUrl() != null || (report.getUrl().trim().equals("") == false))) {
                        System.out.println("------>>>Inside WorkerThread.java ... client.send(s) is DONE");
//                        client.send(report);
                        String s = client.formReportNetworkUser(report).replace("\0", "").trim();
                        client.send(s);
                        System.out.println("----------------->>> SENT TO SERVER .... DONE !!");
                    }

//                    client.send(ReportQueryHandler.getReportAndAll(report.getReport_id(),report));
                    Platform.runLater(() -> {
                        System.out.println("+++>>> Platform.runLater() ... refreshInfo(report) ... ");
                        this.controller_censored_waiting.refreshInfo(report);

                        if (this.controller_censored_waiting.is_single_url_mode == false) {
                            String textToSet = String.valueOf(this.controller_censored_waiting.reportsListToBeRefreshed.size()) + " urls completed out of "
                                    + String.valueOf(this.controller_censored_waiting.total_num_reports + " urls");

                            this.controller_censored_waiting.text_how_many_lines_completed.setText(textToSet);

                        } else {
                            this.controller_censored_waiting.text_how_many_lines_completed.setText("1 out of 1 done");
                        }

                    });
//                    while(this.controller_censored_waiting.is_paused == true){
//                        //keep looping ... until you get false
//                    }
                    this.controller_censored_waiting.sendNextURL(); //send next URL

                    System.out.println("++>>> After platform.runLater() list.size = " + this.controller_censored_waiting.reportsListToBeRefreshed.size());

                }
                //Server ends here .... try-with-resources ... auto-close here ...
                System.out.println("\n\n+++++++>>>> )))) >>>> WorkerThread ...  Ends HERE !!!");
            }
        } catch (SocketException ex) {
            System.out.println("+++-------+++++++++++>>>>>>>>>>>>>> Socket Exception in runForCensoredController_Waiting in WorkerThread.java");
//            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("++++--------+++++++++ >>>> I/O Exception in runForCensoredController_Waiting in WorkerThread.java");
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
