package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.model.Report;

public class TCPRecordController {

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
    @FXML
    private Text text_censoredOrNot;

    private Report report;

    private Stage stage;
    @FXML
    private Text text_details;
    @FXML
    private VBox vbox_resolvedIPs;
    @FXML
    private VBox vbox_HTTPSuccessAttemptTOR;
    @FXML
    private VBox vbox_HTTPSuccessAttemptLocalServer;
    @FXML
    private VBox vbox_HTTPSSuccessAttemptTOR;
    @FXML
    private VBox vbox_HTTPSSuccessAttemptLocalServer;

    public void setUpThings(Report report, Stage stage) {
        this.report = report;
        this.stage = stage;
    }

    public void showThings() {
        this.text_NetworkName.setText("Network Name: " + this.report.getNetworkName());
        this.text_networkType.setText("Network Type:" + this.report.getNetworkType());

        this.text_censoredOrNot.setText("IS CENSORED? " + this.report.getIs_censored());
        this.text_time.setText("Time: " + this.report.getTime_stamp());
        this.text_testType.setText("Test Type: " + this.report.getTest_type());
        this.text_url.setText("URL:" + this.report.getUrl());

        loadResolvedIPList();
        loadHTTPSuccessIteration_TOR();
        loadHTTPSuccessIteration_LocalServer();
        loadHTTPSSuccessIteration_TOR();
        loadHTTPSSuccessIteration_LocalServer();
        loadCensoredOrNot();
        
        //For now DO NOTHING in showDetails
        this.text_details.setText("");
    }

    private void loadCensoredOrNot() {
        boolean isCensoredHTTP = false;
        boolean isCensoredHTTPS = false;

    }

    private void setUpText(String text) {
        this.text_censoredOrNot.setText("Is Censored?" + "\n" + text);
    }

    private void loadResolvedIPList() {

    }

    private void loadHTTPSuccessIteration_TOR() {
    }

    private void loadHTTPSuccessIteration_LocalServer() {

    }

    private boolean isWithin(String iteration_success) {
        String s = iteration_success.trim();
        try {
            int a = Integer.parseInt(s);
            if (a >= 1 && a <= 5) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return false;
    }

    private void loadHTTPSSuccessIteration_TOR() {
    }

    private void loadHTTPSSuccessIteration_LocalServer() {
    }

    private boolean is_censored_http(String iteration_success) {
        return false;
    }

    private boolean is_censored_https(String iteration_success) {
        return false;
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