package ui.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import networking.JavaUDPServerClient;
import ui.model.ModelURL;
import ui.model.Report;
import util.workerAndStates.WorkerThread;
import util.commands.CommandGenerator;
import util.database.DBHandler;
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
    @FXML
    private TableColumn column_reportID;

    private boolean isFile;
    private String fileName;
    private String urlName;
    private String absFileName;

    private int reportIndex_Start;
    private int reportIndex_End;

    WorkerThread worker;

    public List<Report> reportsListToBeRefreshed = new ArrayList<>();
    @FXML
    private Text text_waiting;

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
        this.worker = new WorkerThread(this);
        worker.setFxmlToRun(Scenes.censoredRecordsWaitingFXML);

        Thread t = new Thread(worker);  //Create thread object 
        t.start();  //Start the thread

    }

    @FXML
    private void refreshInfo(ActionEvent event) {
        // Not needed 
    }

    @FXML
    private void goBack(ActionEvent event) {
        //Actually goHome 
        if (this.worker != null) {
            //make the boolean flag = false for the thread ... 
            this.worker.setWillRun(false);
        }

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
            System.out.println("URL Sending to python is <" + url + ">");
            if (url == null) {
                System.out.println("URL IS NULL HERE ... return;");
                return;
            }
            //Make command ...
            String command = CommandGenerator.generateCommandFileNonPeriodicForcedCheck(url, CensoredRecordController_Waiting.this.absFileName, "DNS");
            //Send to UDP
            JavaUDPServerClient.sendCommandToPython(command);
        }

    }

    private void runForURL() {  //TO DO
        System.out.println("Inside CensoredRecordController_Waiting.java .... runForURL() ... TODO!! ");

    }

    public void goToDetailsDNSRecord(int reportID){
        //Called when button is clicked ... 
        System.out.println("------->>> Method goToDetailsDNSRecord() is called reportID = " + reportID);
    }
    
    
    @FXML
    private void clickForDetails(ActionEvent event) {   //TODO //Python -> File -> Java [Visualize what is going on]
        //TODO
        System.out.println("Inside click for details ... ");
        
    }


    /* Report:
    private int reportID;
    private String url;
    private String networkName;
    private String networkType;
    private String time;
    private String testType;
    private String isCensored;
    private String censoredType;
    private Button btn_details;

    private int censorship_code;
     */
    private void loadTableView() {
        //Load the table view here ...
        ObservableList<Report> data = FXCollections.observableArrayList(this.reportsListToBeRefreshed);

        column_reportID.setCellValueFactory(
                new PropertyValueFactory<>("reportID")
        );
        column_url.setCellValueFactory(
                new PropertyValueFactory<>("url")
        );
        column_time.setCellValueFactory(
                new PropertyValueFactory<>("time")
        );
        column_testType.setCellValueFactory(
                new PropertyValueFactory<>("testType")
        );
        column_networkType.setCellValueFactory(
                new PropertyValueFactory<>("networkType")
        );
        column_censoredType.setCellValueFactory(
                new PropertyValueFactory<>("censoredType")
        );
        column_censored.setCellValueFactory(
                new PropertyValueFactory<>("isCensored")
        );
        column_details.setCellValueFactory( //Button for details
                new PropertyValueFactory<>("btn_details")
        );

        this.tableView.setItems(data);
    }

    private int refreshCounter = 0; //Test purposes

    public void refreshInfo(Report report) {
        System.out.println("-------------------------------------- Inside refreshInfo() refreshCnt = " + refreshCounter + "--------------------------------");
        System.out.println("-->>Inside refreshInfo() ... refreshCnt = " + refreshCounter);
        refreshCounter++;

        //Before adding report ... to initialize the button make sure 'this' class is instantiated
        report.setController2(this);
        
        this.reportsListToBeRefreshed.add(report);

        System.out.println("=++++===----+++--->>> PRINTING List of reports .... ");
        int si = 0;
        for(int i=0; i<this.reportsListToBeRefreshed.size(); i++){
            System.out.println(i  + "-->" + this.reportsListToBeRefreshed.get(i).toString());
        }
        loadTableView();

        int numRecordsNeeded = this.reportIndex_End - this.reportIndex_Start;
        if (this.reportsListToBeRefreshed.size() == numRecordsNeeded) {
            this.worker.setWillRun(false);  //i.e. stop the thread from listening ... 
            //set the text to DONE as well ...
            this.text_waiting.setText("COMPLETED !!");

        }

        System.out.println("-------------------------================== ***** ================-----------------------------------");
    }
}

/*
class UDPServer
{
   public static void main(String args[]) throws Exception
      {
         DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String( receivePacket.getData());
                  System.out.println("RECEIVED: " + sentence);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  String capitalizedSentence = sentence.toUpperCase();
                  sendData = capitalizedSentence.getBytes();
                  DatagramPacket sendPacket =
                  new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
               }
      }
}
 */
