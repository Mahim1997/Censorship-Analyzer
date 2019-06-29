package ui.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import networking.JavaUDPServerClient;
import util.workerAndStates.WorkerThread;
import util.commands.CommandGenerator;
import util.loader.SceneLoader;
import util.loader.Scenes;

public class CensoredRecordController_Waiting {

    @FXML
    private TableView tableView;
    @FXML
    private Text text_URL;
    @FXML
    private Text text_url_actual;
    @FXML
    private TableColumn column_url;
    @FXML
    private TableColumn column_networkName;
    @FXML
    private TableColumn column_networkType;
    @FXML
    private TableColumn column_time;
    @FXML
    private TableColumn column_testType;
    @FXML
    private TableColumn column_censored;
    @FXML
    private TableColumn column_censoredType;
    @FXML
    private TableColumn column_details;

    private boolean isFile;
    private String fileName;
    private String urlName;
    private String absFileName;

    private int reportIndex_Start;
    private int reportIndex_End;
 

    public void setUpInitial(boolean isFile, String name, String absPath, int start, int end) {
        System.out.println("\n========>>Inside SetupInitial .... ");
//        this.text_url_actual.setText("FLALALADMALMDLASMDLASMD");
        this.isFile = isFile;
        this.absFileName = absPath;
        if (this.isFile == true) {
            this.fileName = name;
        } else {
            this.urlName = name;
        }
        this.reportIndex_Start = start;
        this.reportIndex_End = end;
        

        if (this.isFile == true) {
            System.out.println("RUnning for file ... this.absFile = " + this.absFileName + " , this.file = " + this.fileName);
            this.text_URL.setText("FILE: ");
            this.text_URL.setFill(Color.WHITE);
            this.text_url_actual.setText(this.fileName);
            this.text_url_actual.setFill(Color.WHITE);
            runForFile();
        } else {
            this.text_URL.setText("URL: ");
            this.text_url_actual.setText(this.urlName);
            runForURL();
        }

        runWorkerThread();
    }

    //Run the worker thread
    private void runWorkerThread() {
        WorkerThread worker = new WorkerThread(this);
        worker.setFxmlToRun(Scenes.censoredRecordsWaitingFXML);
        
        Thread t = new Thread(worker);  //Create thread object 
        t.start();  //Start the thread
        
    }

    @FXML
    private void refreshInfo(ActionEvent event) {

    }

    public void refreshInfo() {
        System.out.println("Refreshing info ... ");
    }

    @FXML
    private void goBack(ActionEvent event) {
        //Actually goHome 
        SceneLoader.loadSceneInSameStage(Scenes.homeScreenFXML);
    }

    private List<String> readFile() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.absFileName))) {
            String line = reader.readLine();
            int index = 1;
            while (line != null) {
//                System.out.println(line);
                // read next line
                line = reader.readLine();
                if (line != null) {
                    list.add(line);
                }

            }
        } catch (FileNotFoundException ex) {
            System.out.println("EXCEPTION in reading file .... ");
            return null;
        } catch (IOException ex) {
            System.out.println("Exception in reading file 2 ... ");
            return null;
        }
        this.reportIndex_End = list.size() + this.reportIndex_Start;
        System.out.println("============>>> HERE [Line 136] , Report start idx = " + this.reportIndex_Start + " , report end idx = " + this.reportIndex_End);
        return list;
    }

    private void runForFile() {
        List<String> list = readFile();
        if (list == null) {
            return;
        }
        list.add(urlName);
        for (String url : list) {
            //Make command ...
            String command = CommandGenerator.generateCommandFileNonPeriodicForcedCheck(url, CensoredRecordController_Waiting.this.absFileName, "DNS");
            //Send to UDP
            JavaUDPServerClient.sendCommandToPython(command);
        }

    }

    private void runForURL() {
        System.out.println("Inside CensoredRecordController_Waiting.java .... runForURL() ... ");

    }

    @FXML
    private void clickForDetails(ActionEvent event) {

    }

}
