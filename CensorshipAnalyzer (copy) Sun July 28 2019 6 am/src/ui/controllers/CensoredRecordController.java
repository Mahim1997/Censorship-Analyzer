package ui.controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import ui.model.Report;
import util.database.ReportQueryHandler;
import util.loader.SceneLoader;
import util.loader.Scenes;

public class CensoredRecordController implements Initializable {

    @FXML
    private TableView<Report> tableView;

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
    private TableColumn column_numberAttempts;

    @FXML
    private JFXTextField textField_url;
    @FXML
    private DatePicker startTime;
    @FXML
    private DatePicker endTime;
    @FXML
    private JFXToggleButton toggleButton_NetworkType;
    @FXML
    private JFXToggleButton toggleButtonCensored;
    @FXML
    private ChoiceBox<String> choiceBox_NetworkName;
    @FXML
    private ChoiceBox<String> choiceBox_TestType;
    @FXML
    private ChoiceBox<String> choiceBox_censoredType;
    @FXML
    private Text text_whichPage;

    private boolean isFileChecking;
    private String fileName_absPath;

    private int totalPage;
    private int currentPage;

    List<Report> reports, reportsListToBeRefreshed, filteredList;

    private void setFileName(String f) {
        this.fileName_absPath = f;
    }
    private boolean isForcedCheck;

    private boolean networkType;
    private boolean iscensored;

    private boolean isFilter;

    @FXML
    private void toggleNetworkType(ActionEvent event) {
        networkType = !networkType;
        this.isFilter = true;
    }

    @FXML
    private void toggleCensored(ActionEvent event) {
        iscensored = !iscensored;
        this.isFilter = true;
    }

    @FXML
    private void refreshInfo(ActionEvent event) {
        System.out.println("Refreshing Info ... ");
        if (!this.isFilter && this.choiceBox_NetworkName.getValue() != null || this.choiceBox_TestType.getValue() != null || this.choiceBox_censoredType.getValue() != null || (this.textField_url.getText() != null && !this.textField_url.getText().equalsIgnoreCase("")) || this.startTime.getValue() != null || this.endTime.getValue() != null) {
            this.isFilter = true;
            System.out.println(this.isFilter + "-----------FILTER-------------");
        }
        if (this.isFilter == false) {
            if (reports.size() > 0) {
                reportsListToBeRefreshed = reports.subList(0, this.reports.size() < 20 ? this.reports.size() : 20);
            }
            this.totalPage = (int) Math.ceil(this.reports.size() / 20.0);
            this.currentPage = 1;
            this.choiceBox_NetworkName.setValue(null);
            this.choiceBox_TestType.setValue(null);
            this.choiceBox_censoredType.setValue(null);
            this.textField_url.setText(null);
            this.toggleButtonCensored.setText(null);
            this.toggleButton_NetworkType.setText(null);
            this.startTime.setPromptText("Start");
            this.endTime.setPromptText("End");
            this.loadTableView();
        } else {

            System.out.println("-------------FILTER INFO-----------");
            System.out.println("NetworkName : " + this.choiceBox_NetworkName.getValue());
            System.out.println("Test Type: " + this.choiceBox_TestType.getValue());
            System.out.println("Censored type : " + this.choiceBox_censoredType.getValue());
            System.out.println("URL: " + this.textField_url.getText());
            System.out.println("Start Time: " + this.startTime.getValue());
            System.out.println("End Time : " + this.endTime.getValue());
            System.out.println("-------------FILTER INFO END-------");

            filteredList = new ArrayList<>();
//            System.out.println("-----------------FILTEREDLIST---------------------------");
            for (int i = 0; i < reports.size(); i++) {
                Report report = reports.get(i);
                if (this.choiceBox_NetworkName.getValue() != null) {
                    if (!report.getNetwork_name().equalsIgnoreCase(this.choiceBox_NetworkName.getValue())) {
                        continue;
                    }
                }
//                System.out.println("---------------NETWORK NAME FILTER PASSED : " + i);
                if (this.choiceBox_TestType.getValue() != null) {
                    if (!report.getTest_type().equalsIgnoreCase(this.choiceBox_TestType.getValue()) && !this.choiceBox_TestType.getValue().equalsIgnoreCase("ALL")) {
                        continue;
                    }
                }
//                System.out.println("---------------TEST TYPE FILTER PASSED : " + i);
                if (this.choiceBox_censoredType.getValue() != null) {
                    if (!report.getCensored_type().equalsIgnoreCase(this.choiceBox_censoredType.getValue()) && !this.choiceBox_censoredType.getValue().equalsIgnoreCase("ALL")) {
                        continue;
                    }
                }
//                System.out.println("---------------CENSORED TYPE FILTER PASSED : " + i);
                if (this.textField_url.getText() != null && !this.textField_url.getText().equalsIgnoreCase("")) {
                    if (!report.getUrl().equalsIgnoreCase(this.textField_url.getText()) && !report.getUrl().substring(4, report.getUrl().length()).equalsIgnoreCase(this.textField_url.getText())) {
                        continue;
                    }
                }
//                System.out.println("---------------URL FILTER PASSED : " + i);
                LocalDate ldstart = this.startTime.getValue();
                LocalDate ldend = this.endTime.getValue();

                String[] firstSplit = report.getTime_stamp().split(" ");
                String[] secondSplit = firstSplit[0].split("-");
                System.out.println("Time : " + firstSplit[0]);
                for (String secondSplit1 : secondSplit) {
                    System.out.print(secondSplit1 + " ");
                }
                System.out.println();

                if (!report.getTime_stamp().equalsIgnoreCase("NULL")) {
                    LocalDate rdate = LocalDate.of(Integer.parseInt(secondSplit[2]), Integer.parseInt(secondSplit[1]), Integer.parseInt(secondSplit[0]));
                    if (ldstart != null) {
                        if (ldstart.compareTo(rdate) < 0) {
                            continue;
                        }
                    }
//                    System.out.println("--------------START TIME FILTER PASSED : " + i);
                    if (ldend != null) {
                        if (ldend.compareTo(rdate) > 0) {
                            continue;
                        }
                    }

//                    System.out.println("---------------END TIME FILTER PASSED : " + i);
                }

                if (this.iscensored) {
                    if (report.getIs_censored().equalsIgnoreCase("NO")) {
                        continue;
                    }
                }

                if (!this.iscensored) {
                    if (report.getIs_censored().equalsIgnoreCase("YES")) {
                        continue;
                    }
                }

//                System.out.println("--------------IS CENSORED FILTER PASSED : " + i);
                if (this.networkType) {
                    if (!report.getNetwork_type().equalsIgnoreCase("ISP")) {
                        continue;
                    }
                }

                if (!this.networkType) {
                    if (report.getNetwork_type().equalsIgnoreCase("ISP")) {
                        continue;
                    }
                }

//                System.out.println("--------------NETWORK TYPE FILTER PASSED : " + i);
//                ReportQueryHandler.printReport(report);
                this.filteredList.add(report);
            }

            if (filteredList.size() > 0) {
                reportsListToBeRefreshed = filteredList.subList(0, this.filteredList.size() < 20 ? this.filteredList.size() : 20);
            }
            this.totalPage = (int) Math.ceil(this.filteredList.size() / 20.0);
            this.currentPage = 1;
            this.loadTableView();

        }

    }

    @FXML
    private void nextPage(ActionEvent event) {
        if (this.currentPage < this.totalPage) {
            if (!this.isFilter) {
                this.totalPage = (int) Math.ceil(this.reports.size() / 20.0);
                this.currentPage++;
                this.reportsListToBeRefreshed = this.reports.subList((this.currentPage - 1) * 20, ((this.currentPage - 1) * 20 + 20) < this.reports.size() ? ((this.currentPage - 1) * 20 + 20) : this.reports.size());
                this.loadTableView();
            } else {
                this.totalPage = (int) Math.ceil(this.filteredList.size() / 20.0);
                this.currentPage++;
                this.reportsListToBeRefreshed = this.filteredList.subList((this.currentPage - 1) * 20, ((this.currentPage - 1) * 20 + 20) < this.filteredList.size() ? ((this.currentPage - 1) * 20 + 20) : this.filteredList.size());
                this.loadTableView();

            }

        }
    }

    @FXML
    private void previousPage(ActionEvent event) {
        if (this.currentPage > 1) {
            if (!this.isFilter) {
                this.totalPage = (int) Math.ceil(this.reports.size() / 20.0);
                this.currentPage--;
                this.reportsListToBeRefreshed = this.reports.subList((this.currentPage - 1) * 20, ((this.currentPage - 1) * 20 + 20) < this.reports.size() ? ((this.currentPage - 1) * 20 + 20) : this.reports.size());
                this.loadTableView();
            } else {
                this.totalPage = (int) Math.ceil(this.filteredList.size() / 20.0);
                this.currentPage--;
                this.reportsListToBeRefreshed = this.filteredList.subList((this.currentPage - 1) * 20, ((this.currentPage - 1) * 20 + 20) < this.filteredList.size() ? ((this.currentPage - 1) * 20 + 20) : this.filteredList.size());
                this.loadTableView();
            }

        }

    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.loadSceneInSameStage(Scenes.homeScreenFXML);
    }

    private void loadTableView() {

        //Load the table view here ...
        ObservableList<Report> data = FXCollections.observableArrayList(this.reportsListToBeRefreshed);

        System.out.println("========>>> INSIDE LoadTableView ... this.reportsListToBeRefreshed.size = " + this.reportsListToBeRefreshed.size() + " .. printing ");
        System.out.println("--->>> HERE, number_of_attempts = " + this.reports.get(0).number_of_attempts);
        
        
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
        column_censoredType.setCellValueFactory(
                new PropertyValueFactory<>("censored_type")
        );
        column_censored.setCellValueFactory(
                new PropertyValueFactory<>("is_censored")
        );
        column_details.setCellValueFactory( //Button for details
                new PropertyValueFactory<>("btn_details_censored")
        );
        column_numberAttempts.setCellValueFactory( //Button for details
                new PropertyValueFactory<>("number_of_attempts")
        );


        this.text_whichPage.setText("Page : " + this.currentPage + "/" + this.totalPage);

        this.tableView.setItems(data);
    }

    void initializeComboBox() {
        ObservableList<String> typebox = FXCollections.observableArrayList();
        typebox.add("ALL");
        typebox.add("DNS");
        typebox.add("HTTP");
        typebox.add("TCP");

        ObservableList<String> censebox = FXCollections.observableArrayList();
        censebox.add("ALL");
        censebox.add("DNS");
        censebox.add("HTTP");
        censebox.add("TCP");

        ObservableList<String> networkNames = FXCollections.observableArrayList(ReportQueryHandler.getNetworkNames());

        choiceBox_TestType.setItems(typebox);
        choiceBox_censoredType.setItems(censebox);
        choiceBox_NetworkName.setItems(networkNames);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("--->>> Initializing CensoredRecordController.java ....");
        iscensored = true;
        networkType = false;

        reports = ReportQueryHandler.getAllReports();
        totalPage = (int) Math.ceil(reports.size() / 20.0);
        System.out.println("Report Size: " + reports.size());
        System.out.println("Total Page: " + totalPage);
        if (reports.size() > 0) {
            reportsListToBeRefreshed = reports.subList(0, this.reports.size() < 20 ? this.reports.size() : 20);
        }
        this.currentPage = 1;
        this.loadTableView();
        this.text_whichPage.setText("Page : " + this.currentPage + "/" + this.totalPage);
        this.initializeComboBox();
        isFilter = false;

        System.out.println("-------------INITIAL INFO-----------");
        System.out.println("NetworkName : " + this.choiceBox_NetworkName.getValue());
        System.out.println("Test Type: " + this.choiceBox_TestType.getValue());
        System.out.println("Censored type : " + this.choiceBox_censoredType.getValue());
        System.out.println("URL: " + this.textField_url.getText());
        System.out.println("Start Time: " + this.startTime.getValue());
        System.out.println("End Time : " + this.endTime.getValue());
        System.out.println("-------------INITIAL INFO END-------");
    }

}
