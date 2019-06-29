/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.workerAndStates;

import javafx.application.Platform;
import ui.controllers.CensoredRecordController;
import ui.controllers.CensoredRecordController_Waiting;
import util.loader.Scenes;

/**
 *
 * @author viper
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
        while (willRun) {
            long timeNow = System.currentTimeMillis();
            if ((timeNow - startTime) == delayTime) {
                Platform.runLater(() -> {
                    controller_censored_waiting.refreshInfo();
                });

                startTime = timeNow;    //Update the startTime ... 
            }
        }
    }

}
