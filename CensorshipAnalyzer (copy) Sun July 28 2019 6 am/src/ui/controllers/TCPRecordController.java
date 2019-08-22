package ui.controllers;

import com.jfoenix.controls.JFXTabPane;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import main.Config;
import ui.model.Report;
import ui.model.TCPDetails;
import util.commands.Util;

public class TCPRecordController {

    private Report report;

    // -------------------------- Above network things -----------------------------
    @FXML
    private Text text_url;
    @FXML
    private Text text_NetworkName;
    @FXML
    private Text text_testType;
    @FXML
    private Text text_networkType;
    @FXML
    private Text text_time;
    private Text text_censoredOrNot;

    // ----------------------- TAB PANE THINGS ----------------------------------
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Tab tab_http;
    @FXML
    private Tab tab_https;

    // ---------------------- HTTP Table View ----------------------------------
    @FXML
    private TableView tableView_HTTP;
    @FXML
    private TableColumn col_ipAddress_HTTP;
    @FXML
    private TableColumn col_timeout_HTTP;
    @FXML
    private TableColumn col_finBitSet_HTTP;
    @FXML
    private TableColumn col_rstBitSet_HTTP;
    @FXML
    private TableColumn col_localServerIterationSuccess_HTTP;
    @FXML
    private TableColumn col_torBrowserIterationSuccess_HTTP;
    @FXML
    private TableColumn col_middleBoxHopCount_HTTP;
    @FXML
    private TableColumn col_isCensoredTCP_HTTP;
    @FXML
    private Text text_overAllHTTPCensored;
    @FXML
    private Text text_overAllHTTPSCensored;
    @FXML
    private TableView tableView_HTTPS;
    @FXML
    private TableColumn col_ipAddress_HTTPS;
    @FXML
    private TableColumn col_timeout_HTTPS;
    @FXML
    private TableColumn col_finBitSet_HTTPS;
    @FXML
    private TableColumn col_rstBitSet_HTTPS;
    @FXML
    private TableColumn col_localServerIterationSuccess_HTTPS;
    @FXML
    private TableColumn col_torBrowserIterationSuccess_HTTPS;
    @FXML
    private TableColumn col_middleBoxHopCount_HTTPS;
    @FXML
    private TableColumn col_isCensoredTCP_HTTPS;

    // ---------------------- HTTPS Table View -----------------------------------
    public void setUpThings(Report report) {
        this.report = report;

//        System.out.println("---------------IN setUpThings() ... report is -------------------");
//        this.report.printReport();
//        System.out.println("----------------------------------------------------------");
        int is_cens = 0;
        for (int i = 0; i < this.report.tcp_details_list.size(); i++) {
            TCPDetails tcpDesc = this.report.tcp_details_list.get(i);
            if (tcpDesc.getIs_censored_TCP_str().contains("1")) {
                //any one is censored .. so, overall is censored
                is_cens = 1;
                break;
            }
        }
        if (is_cens == 0) {
            //not censored
            this.text_overAllHTTPCensored.setText("Is Censored for HTTP ? NO");
            this.text_overAllHTTPSCensored.setText("Is Censored for HTTPS ? NO");

        } else {
            //censored
            this.text_overAllHTTPCensored.setText("Is Censored for HTTP ? YES");
            this.text_overAllHTTPSCensored.setText("Is Censored for HTTPS ? YES");
        }
    }

    public void showThings() {
        this.text_NetworkName.setText(this.report.getNetwork_name());
        this.text_networkType.setText(this.report.getNetwork_type());
//        this.text_censoredOrNot.setText("IS CENSORED? " + this.report.getIs_censored());
        this.text_time.setText(this.report.getTime_stamp());
        this.text_testType.setText(this.report.getTest_type());
        this.text_url.setText(this.report.getUrl());

        loadTableViewHTTP();
        loadTableViewHTTPS();
    }

    @FXML
    private void selectHTTP_Tab(Event event) {
        System.out.println("-->> To load Table View HTTP");
//        loadTableViewHTTP();
    }

    @FXML
    private void selectHTTPS_Tab(Event event) {
        System.out.println("-->> To load Table View HTTPS");
//        loadTableViewHTTPS();
    }

    private void loadTableViewHTTP() {

        List<TCPDetails> list_tcp_desc = new ArrayList<>();

//        System.out.println("------------ Inside loadTableViewHTTP() --------------- Printing report ... " + this.report.toString());
        //-------- Add only the HTTP records to list_tcp_desc -------------
        for (int i = 0; i < this.report.tcp_details_list.size(); i++) {
            TCPDetails tcpDet = this.report.tcp_details_list.get(i);
            if (tcpDet.getPort() == Config.PORT_HTTP) {
                list_tcp_desc.add(tcpDet);
            }
        }

        list_tcp_desc.forEach(Util::makeTCPDetailStrings); //make Yes/No things
//        list_tcp_desc.forEach(TCPDetails::makeImageView); //make image view [STILL ERRORS]

        //Now table view things
        ObservableList<TCPDetails> data = FXCollections.observableArrayList(list_tcp_desc);

        col_ipAddress_HTTP.setCellValueFactory(
                new PropertyValueFactory<>("ip_address")
        );
        col_timeout_HTTP.setCellValueFactory(
                new PropertyValueFactory<>("is_timeout_str")
        );
        col_finBitSet_HTTP.setCellValueFactory(
                new PropertyValueFactory<>("is_fin_bit_set_str")
        );
        col_rstBitSet_HTTP.setCellValueFactory(
                new PropertyValueFactory<>("is_rst_bit_set_str")
        );
        col_localServerIterationSuccess_HTTP.setCellValueFactory(
                new PropertyValueFactory<>("successful_iteration_local_server_str")
        );
        col_torBrowserIterationSuccess_HTTP.setCellValueFactory(
                new PropertyValueFactory<>("successful_iteration_tor_str")
        );
        col_middleBoxHopCount_HTTP.setCellValueFactory(
                new PropertyValueFactory<>("middle_box_hop_count_str")
        );
        col_isCensoredTCP_HTTP.setCellValueFactory(
                new PropertyValueFactory<>("is_censored_TCP_str")
        );
        this.tableView_HTTP.setItems(data);
    }

    private void loadTableViewHTTPS() {
        List<TCPDetails> list_tcp_desc = new ArrayList<>();

//        System.out.println("------------ Inside loadTableViewHTTPS() --------------- Printing report ... " + this.report.toString());
        //-------- Add only the HTTP records to list_tcp_desc -------------
        for (int i = 0; i < this.report.tcp_details_list.size(); i++) {
            TCPDetails tcpDet = this.report.tcp_details_list.get(i);
            if (tcpDet.getPort() == Config.PORT_HTTPS) {
                list_tcp_desc.add(tcpDet);
            }
        }
        
        
        System.out.println("====+++----->> loadTableViewHTTPS() ... list_tcp_desc.size() = " + list_tcp_desc.size() + " ... printing the list");
        
        
        list_tcp_desc.forEach(Util::makeTCPDetailStrings); //make Yes/No things

        ObservableList<TCPDetails> data = FXCollections.observableArrayList(list_tcp_desc);

        col_ipAddress_HTTPS.setCellValueFactory(
                new PropertyValueFactory<>("ip_address")
        );
        col_timeout_HTTPS.setCellValueFactory(
                new PropertyValueFactory<>("is_timeout_str")
        );
        col_finBitSet_HTTPS.setCellValueFactory(
                new PropertyValueFactory<>("is_fin_bit_set_str")
        );
        col_rstBitSet_HTTPS.setCellValueFactory(
                new PropertyValueFactory<>("is_rst_bit_set_str")
        );
        col_localServerIterationSuccess_HTTPS.setCellValueFactory(
                new PropertyValueFactory<>("successful_iteration_local_server_str")
        );
        col_torBrowserIterationSuccess_HTTPS.setCellValueFactory(
                new PropertyValueFactory<>("successful_iteration_tor_str")
        );
        col_middleBoxHopCount_HTTPS.setCellValueFactory(
                new PropertyValueFactory<>("middle_box_hop_count_str")
        );
        col_isCensoredTCP_HTTPS.setCellValueFactory(
                new PropertyValueFactory<>("is_censored_TCP_str")
        );
        this.tableView_HTTPS.setItems(data);
    }

}

/*
        List<JFXTextField> textFieldList = new ArrayList<>();

        for (String iteration_success : this.report.getTcp_details().getIteration_success_local_http()) {
            //each ip is to be loaded in a text field
            JFXTextField textField = new JFXTextField();
            if (isWithin(iteration_success)) {
                textField.setText("Attempt " + iteration_success + " Successful");
            }
            textField.setEditable(false);
            textField.setStyle("-fx-text-fill: #000000; ");//-fx-font-size: 16px;");
            textFieldList.add(textField);
        }

        //Finally add to the vbox
        this.vbox_HTTPSuccessAttemptLocalServer.getChildren().addAll(textFieldList);

 */
