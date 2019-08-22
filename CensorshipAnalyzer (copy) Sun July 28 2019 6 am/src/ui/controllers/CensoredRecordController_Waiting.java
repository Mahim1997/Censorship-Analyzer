package ui.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import networking.JavaUDPServerClient;
import ui.model.Report;
import ui.sounds.Notification;
import util.workerAndStates.WorkerThread;
import util.commands.CommandGenerator;
import util.loader.SceneLoader;
import util.loader.Scenes;

public class CensoredRecordController_Waiting {

    @FXML
    private TableView<Report> tableView;
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

    public List<Report> reportsListToBeRefreshed;//= new ArrayList<>();
    @FXML
    private Text text_waiting;

    public int numberOfReportsNeeded;
    private String testingMode; //To send to Python Server
    @FXML
    private JFXButton btn_home;
    @FXML
    private JFXButton btn_pause;
    @FXML
    private ImageView imageView_pauseBtn;
    @FXML
    public Text text_how_many_lines_completed;
    @FXML
    private JFXButton btn_terminate;

    //flags
    public boolean is_paused = false;
    public boolean is_closed = false;

    public boolean is_single_url_mode;

    public int total_num_reports;

    Parent root_for_details; //for details controller
    @FXML
    private JFXButton btn_viewFile;

    public void setUpInitial(boolean isFile, String name, String absolutPathOrCommandForURL, int start, int end, String testingMode) {
//        String fileNameFXMLToLoad = Scenes.realTimeDetails;
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(this.getClass().getResource(fileNameFXMLToLoad));
//        try {
//            this.root_for_details = loader.load();
//        } catch (IOException ex) {
//            System.out.println("-->EXCEPTION in CensoredRecordController_Waiting.setUpInitial() ... loader.load()");
//        }
//        RealTimeDetailsController controller = (RealTimeDetailsController) loader.getController();

        String textToSet = String.valueOf("0 urls completed");
        this.text_how_many_lines_completed.setText(textToSet);

        this.reportsListToBeRefreshed = new ArrayList<>();

        System.out.println("\n========>>Inside CensoredRecordController_Waiting.setUpInitial() .... ");
        this.isFile = isFile;
        this.absFileName = absolutPathOrCommandForURL;
        if (this.isFile == true) {
            this.fileName = name;
            this.is_single_url_mode = false;
        } else { //url
            this.is_single_url_mode = true;
            this.urlName = name;
        }
        this.reportIndex_Start = start;
        this.reportIndex_End = end;
        this.testingMode = testingMode;

        if (this.isFile == true) {
            fillTextsWithColor("FILE: ", this.fileName, Color.WHITE);
            runForFile();   //Also updates the numberOfReports needed 
            System.out.println("RUnning for file ... this.absFile = " + this.absFileName + " , this.file = " + this.fileName + " this.numReports = " + this.numberOfReportsNeeded);

        } else {
            System.out.println("Running for URL .... this.url = " + this.urlName + " , ... RUNNING !!");
            this.numberOfReportsNeeded = 1; //ONLY one for URL
            fillTextsWithColor("URL: ", this.urlName, Color.WHITE);
            runForURL(absolutPathOrCommandForURL);
        }

        runWorkerThread();

        //Make home button invisible
        this.btn_home.setOpacity(0);
    }

    private void fillTextsWithColor(String textForLeft, String textForRight, Color color) {
        this.text_URL.setText(textForLeft);
        this.text_url_actual.setText(textForRight);
        this.text_URL.setFill(color);
        this.text_url_actual.setFill(color);
    }

    //Run the worker thread
    Thread workerThread_thread;

    private void runWorkerThread() {
        this.worker = new WorkerThread(this);
        worker.setFxmlToRun(Scenes.censoredRecordsWaitingFXML);

        workerThread_thread = new Thread(worker);  //Create thread object 
        workerThread_thread.start();  //Start the thread    

    }

    @FXML
    private void goBack(ActionEvent event) {
        //Actually goHome 
        if (this.worker != null) {
            //make the boolean flag = false for the thread ... 
            this.worker.setWillRun(false);
//            this.workerThread_thread.stop();
        }

        SceneLoader.loadSceneInSameStage(Scenes.homeScreenFXML);
    }

    //------------------ FOR FILE --------------------------
    public List<String> file_urls_list = new ArrayList<>();
    public boolean has_gotten_return_msg = true;
    public int index_of_url_in_file_list = 0;

    private void getFileURLSList() {
        this.file_urls_list = readFile();
        this.total_num_reports = this.file_urls_list.size();
    }

    private void runForFile() {
        getFileURLSList(); //read from file
        if (this.file_urls_list != null) {
            //send first url
            String url = this.file_urls_list.get(index_of_url_in_file_list);
            this.index_of_url_in_file_list++;

            while (url == null) {
                //Keep iterating
                if (index_of_url_in_file_list == this.file_urls_list.size()) {
                    //exit
                    stopWorkerThreadFromRunning();
                }
                index_of_url_in_file_list++;
                url = this.file_urls_list.get(index_of_url_in_file_list);

            }

            //afterwards
            if (index_of_url_in_file_list >= this.file_urls_list.size()) {
                return;
            }

            url = this.file_urls_list.get(index_of_url_in_file_list);
            String command = CommandGenerator.generateCommandFileNonPeriodicForcedCheck(url, CensoredRecordController_Waiting.this.absFileName, this.testingMode);
            //Send to UDP
            JavaUDPServerClient.sendCommandToPython(command);

        } else {
            stopWorkerThreadFromRunning();
        }

    }

    private void runForURL(String commandProvided) {  //TO DO
        System.out.println("Inside CensoredRecordController_Waiting.java .... runForURL() ... TODO!! ");
        JavaUDPServerClient.sendCommandToPython(commandProvided);
        this.btn_viewFile.setOpacity(0);
        this.text_how_many_lines_completed.setText("Waiting...");
    }

    private Report getReport(int reportID) {
        System.out.println("------>>CensoredRecordController_Waiting.getReport() called with reportID = " + reportID);
        for (Report report : this.reportsListToBeRefreshed) {
            if (report.getReport_id() == reportID) {
                System.out.println("------------------- In Report getReport() of CensoredRecordController_Waiting.java ..... --------------- returning report " + report.toString());
                return report;
            }
        }
        System.out.println("------------------- In Report getReport() of CensoredRecordController_Waiting.java ..... --------------- returning NULL report ");
        return null;
    }

    public void goToDetailsDNSRecord(int reportID) {
        //Called when button is clicked ... 
        System.out.println("------->>> Method goToDetailsDNSRecord() is called reportID = " + reportID);

        try {
            String fileNameFXMLToLoad = Scenes.dnsRecords;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource(fileNameFXMLToLoad));

            Parent root = loader.load();
            DNSRecordController controller = (DNSRecordController) loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("DNS Details");
            stage.setScene(scene);

            controller.setUpThings(getReport(reportID), stage);    //Set up which report to show ... 
            controller.showThings();

            stage.show();

        } catch (IOException ex) {
            System.out.println("\n--->>> EXCEPTION IN TestSitesController.submitFile function() .... ");
        }

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
            System.out.println("EXCEPTION 1 in reading file .... ");
            return null;
        } catch (IOException ex) {
            System.out.println("Exception 2 in reading file .... ");
            return null;
        }
        this.reportIndex_End = list.size() + this.reportIndex_Start;
        this.numberOfReportsNeeded = Math.abs(this.reportIndex_End - this.reportIndex_Start);
//        System.out.println("============>>> HERE [Line 218] , Report start idx = " + this.reportIndex_Start + " , report end idx = " + this.reportIndex_End);
        return list;
    }

    @FXML
    private void clickForDetails(ActionEvent event) {   //TODO //Python -> File -> Java [Visualize what is going on]
        //TODO
        System.out.println("Inside click for details ... load from a file ... python writes to that file .... ");
        Stage stage = new Stage();
        //Load from fxml [TO Do] In a new Stage
        String fileNameFXMLToLoad = Scenes.realTimeDetails;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource(fileNameFXMLToLoad));
        try {
            this.root_for_details = loader.load();
        } catch (IOException ex) {
            System.out.println("-->EXCEPTION in CensoredRecordController_Waiting.setUpInitial() ... loader.load()");
        }
        RealTimeDetailsController controller = (RealTimeDetailsController) loader.getController();
        
        Scene scene = new Scene(this.root_for_details);//, Main.STAGE_WIDTH, Main.STAGE_HEIGHT);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();

        stage.setOnCloseRequest((WindowEvent e) -> {
            JavaUDPServerClient.sendExitToSelfCommand();
        });
    }

    private void loadTableView() {

        //Load the table view here ...
        ObservableList<Report> data = FXCollections.observableArrayList(this.reportsListToBeRefreshed);

        System.out.println("========>>> INSIDE LoadTableView ... this.reportsListToBeRefreshed.size = " + this.reportsListToBeRefreshed.size() + " .. printing ");
        this.reportsListToBeRefreshed.forEach((r) -> {
            System.out.println(r.toString());
        });

        column_reportID.setCellValueFactory(
                new PropertyValueFactory<>("report_id")
        );
        column_url.setCellValueFactory(
                new PropertyValueFactory<>("url")
        );
        column_time.setCellValueFactory(
                new PropertyValueFactory<>("time_stamp")
        );
        column_testType.setCellValueFactory(
                new PropertyValueFactory<>("test_type")
        );
        column_networkType.setCellValueFactory(
                new PropertyValueFactory<>("network_type")
        );
        column_networkName.setCellValueFactory(
                new PropertyValueFactory<>("network_name")
        );
//        column_censoredType.setCellValueFactory(
//                new PropertyValueFactory<>("censored_type")
//        );
        column_censored.setCellValueFactory(
                new PropertyValueFactory<>("is_censored")
        );
        column_details.setCellValueFactory( //Button for details
                new PropertyValueFactory<>("btn_details")
        );

        this.tableView.setItems(data);
    }

    private int refreshCounter = 0; //Test purposes

    public void refreshInfo(Report report) {
        System.out.println("-------------------------------------- Inside refreshInfo(loadTableView()) refreshCnt = " + refreshCounter + "--------------------------------");
        System.out.println("-->>Inside refreshInfo() ... refreshCnt = " + refreshCounter);
        refreshCounter++;

        //Before adding report ... to initialize the button make sure 'this' class is instantiated
        report.setController2(this);

        this.reportsListToBeRefreshed.add(report);  //Report is added

        if (this.isFile == true) {  //File
            loadTableView();
            has_gotten_return_msg = true;

//            int numRecordsNeeded = this.reportIndex_End - this.reportIndex_Start;
            if (this.reportsListToBeRefreshed.size() == this.numberOfReportsNeeded) {
                //Stop worker thread from running
                stopWorkerThreadFromRunning();
                //set the text to DONE as well ...
                text_waiting.setStyle("-fx-text-inner-color: green;");
                this.text_waiting.setText("COMPLETED !!");

            }
            if (this.is_closed == true) {
                text_waiting.setStyle("-fx-text-inner-color: red;");
                this.text_waiting.setText("TERMINATED BY USER !");
            }

        } else {   //URL
            System.out.println("--------------------- URL : this.listReports.size = " + this.reportsListToBeRefreshed.size() + "  ---------------------------- ");
            loadTableView();
            text_waiting.setStyle("-fx-text-inner-color: green;");
            this.text_waiting.setText("COMPLETED !!! ");
            //Stop worker Thread from running
            stopWorkerThreadFromRunning();
            System.out.println("============================ ****URL Running REFRESH COMPLETED*** ===================================");
        }
    }

    private void stopWorkerThreadFromRunning() {
        this.btn_home.setOpacity(1); //make home button visible now ...
        this.worker.setWillRun(false);
        JavaUDPServerClient.sendThreadExitCommand();    //Send a loop-back command to JAVA PORT
    }

    public void goToDetailsTCPRecord(int reportID) {
        //Called when button is clicked ... 
        System.out.println("------->>> Method goToDetailsTCPRecord() is called reportID = " + reportID);

        try {
            String fileNameFXMLToLoad = Scenes.tcpRecords;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource(fileNameFXMLToLoad));

            Parent root = loader.load();
            TCPRecordController controller = (TCPRecordController) loader.getController();

            controller.setUpThings(getReport(reportID));    //Set up which report to show ... 

            controller.showThings();

            SceneLoader.loadSceneInNewStage(root, "TCP DETAILS");

        } catch (IOException ex) {
            System.out.println("\n--->>> EXCEPTION IN TestSitesController.submitFile function() .... ");
        }
    }

    @FXML
    private void pauseOrPlay(ActionEvent event) {
        if (is_paused == true) { //already paused, change to play
            //play

            this.is_paused = false; //NOW IS NOT PAUSED

            //change btn to pause
            this.btn_pause.setText("Pause");
            this.imageView_pauseBtn.setImage(new Image("pauseIcon.jpeg"));

            sendNextURL();

        } else if (is_paused == false) {
            //pause
            this.is_paused = true;
            //change btn to play
            this.btn_pause.setText("Play");
            //change button image??
            this.imageView_pauseBtn.setImage(new Image("resumeIcon.jpeg"));
        }

    }

    @FXML
    private void terminate(ActionEvent event) {
        System.out.println("-->>INSIDE terminate ... ");
        stopWorkerThreadFromRunning();
        this.text_waiting.setText("USER TERMINATED EXECUTION !");
    }

    public void sendNextURL() {
        if (this.is_single_url_mode == false) { //only for file
            if (is_paused == true) { //already paused, change to play
                // do nothing

            } else if (is_paused == false) {
                actuallySendNextURL();
            }
        }

    }

    private void actuallySendNextURL() {
        System.out.println("-->>Inside actuallySendNextURL() ... ");
        if (this.file_urls_list != null) {
            //send first url
            String url = this.file_urls_list.get(index_of_url_in_file_list);
            this.index_of_url_in_file_list++;

            while (url == null) {
                //Keep iterating
                if (index_of_url_in_file_list == this.file_urls_list.size()) {
                    //exit
                    stopWorkerThreadFromRunning();
                }
                index_of_url_in_file_list++;
                url = this.file_urls_list.get(index_of_url_in_file_list);

            }

            //afterwards
            if (index_of_url_in_file_list >= this.file_urls_list.size()) {
                return;
            }

            url = this.file_urls_list.get(index_of_url_in_file_list);
            String command = CommandGenerator.generateCommandFileNonPeriodicForcedCheck(url, CensoredRecordController_Waiting.this.absFileName, this.testingMode);
            //Send to UDP
            JavaUDPServerClient.sendCommandToPython(command);
        }
    }

    @FXML
    private void viewFile(ActionEvent event) {
        try {
            Stage stage = new Stage();
            //Load from fxml [TO Do] In a new Stage
            String fileNameFXMLToLoad = Scenes.fileViewFXML;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource(fileNameFXMLToLoad));
            Parent root = loader.load();

            FileViewerController controller = (FileViewerController) loader.getController();
            System.out.println("--->>>In viewFile from CensoredRecordController_Waiting.java ... this.fileName = " + this.fileName + " , this.absFileName = " + this.absFileName);

            controller.setFileName(this.fileName, this.absFileName);
            controller.showData();

            Scene scene = new Scene(root);//, Main.STAGE_WIDTH, Main.STAGE_HEIGHT);
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.show();
        } catch (IOException ex) {
            Notification.push("Error", "Error in loading file", Notification.FAILURE, Pos.BOTTOM_RIGHT);
        }

    }

}

/*
try {
            String fileNameFXMLToLoad = Scenes.tcpRecords;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource(fileNameFXMLToLoad));

            Parent root = loader.load();
            TCPRecordController controller = (TCPRecordController) loader.getController();
//            controller.setUpThings(getReport(reportID));    //Set up which report to show ... 

            
//            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("TCP Details");
            stage.setScene(scene);

            controller.setUpThings(getReport(reportID));    //Set up which report to show ... 

            controller.showThings();

            stage.show();

        } catch (IOException ex) {
            System.out.println("\n--->>> EXCEPTION IN TestSitesController.submitFile function() .... ");
        }
 */
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

 /*
for (int i = 0; i < this.file_urls_list.size(); i++) {
            String url = this.file_urls_list.get(i);

            while(has_gotten_return_msg == false){
                //keep looping .... until becomes true
            }
            has_gotten_return_msg = false;
            
            if (is_paused) {
                return;
            }
            if (is_closed) {
                this.stopWorkerThreadFromRunning(); /// ????
                return;
            }
            System.out.println("URL Sending to python is <" + url + ">");
            if (url == null) {
                System.out.println("URL IS NULL HERE ... return;");
                return;
            }
            System.out.println("--->> FORMING command for url = " + url);
            //Make command ...
            String command = CommandGenerator.generateCommandFileNonPeriodicForcedCheck(url, CensoredRecordController_Waiting.this.absFileName, this.testingMode);
            //Send to UDP
            JavaUDPServerClient.sendCommandToPython(command);
        }

 */
