package ui.controllers;

import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.model.Report;
import ui.model.TCPDetails;

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

        this.text_censoredOrNot.setText("IS CENSORED? " + this.report.getIsCensored());
        this.text_time.setText("Time: " + this.report.getTime());
        this.text_testType.setText("Test Type: " + this.report.getTestType());
        this.text_url.setText("URL:" + this.report.getUrl());

        loadResolvedIPList();
        loadHTTPSuccessIteration_TOR();
        loadHTTPSuccessIteration_LocalServer();
        loadHTTPSSuccessIteration_TOR();
        loadHTTPSSuccessIteration_LocalServer();
        loadCensoredOrNot();
    }

    private void loadCensoredOrNot() {
        boolean isCensoredHTTP = false;
        boolean isCensoredHTTPS = false;

//        System.out.println("---------------------------Inside loadCensoredOrNot()---------------------------");
//        System.out.println("report.getTcp_details().getIs_censored_http_thisIP()");
        for (String is_censored_http_thisIP : report.getTcp_details().getIs_censored_http_thisIP()) {
            if (is_censored_http_thisIP.equals("1") || is_censored_http_thisIP.toUpperCase().equals("FALSE") || is_censored_http_thisIP.toUpperCase().equals("NO")) {
                isCensoredHTTP = true;
                break;
            }
        }

        for (String is_censoredHTTPS_This_IP : report.getTcp_details().getIs_censored_https_thisIP()) {
            if (is_censoredHTTPS_This_IP.equals("1") || is_censoredHTTPS_This_IP.toUpperCase().equals("FALSE") || is_censoredHTTPS_This_IP.toUpperCase().equals("NO")) {
                isCensoredHTTPS = true;
                break;
            }
        }

        //If both are false then NOT Censored
        if ((isCensoredHTTP == false) && (isCensoredHTTPS == false)) {
            setUpText("NOT CENSORED");
        } else {
            setUpText("CENSORED");
        }
    }

    private void setUpText(String text) {
        this.text_censoredOrNot.setText("Is Censored?" + "\n" + text);
    }

    private void loadResolvedIPList() {
        //Form the matched set
        TCPDetails details_tcp = this.report.getTcp_details();
        List<JFXTextField> textFieldList = new ArrayList<>();

        List<String> is_censored_http_arr  = this.report.getTcp_details().getIs_censored_http_thisIP();
        List<String> is_censored_https_arr = this.report.getTcp_details().getIs_censored_https_thisIP();
        int index = 0;
        for (String ip : details_tcp.getIp_addresses_resolved()) {
            //each ip is to be loaded in a text field
            JFXTextField textField = new JFXTextField();

//            textField.setText(ip);

            if(is_censored_http_arr.get(index).equals("1") || is_censored_https_arr.get(index).equals("1")){
                textField.setText(ip + " (CENSORED)");
            }else{
                textField.setText(ip + " (NOT CENSORED) ") ;
            }

            index++;

            textField.setEditable(false);
            textField.setStyle("-fx-text-fill: #000000; ");//-fx-font-size: 16px;");
            textFieldList.add(textField);
        }

        //Finally add to the vbox
        this.vbox_resolvedIPs.getChildren().addAll(textFieldList);

    }

    private void loadHTTPSuccessIteration_TOR() {
        List<JFXTextField> textFieldList = new ArrayList<>();

        for (String iteration_success : this.report.getTcp_details().getIteration_success_tor_http()) {
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
        this.vbox_HTTPSuccessAttemptTOR.getChildren().addAll(textFieldList);
    }

    private void loadHTTPSuccessIteration_LocalServer() {
        List<JFXTextField> textFieldList = new ArrayList<>();

        for (String iteration_success : this.report.getTcp_details().getIteration_success_local_http()) {
            //each ip is to be loaded in a text field
            JFXTextField textField = new JFXTextField();
            if (isWithin(iteration_success)) {
                textField.setText("Attempt " + iteration_success + " Successful)");
            }
            textField.setEditable(false);
            textField.setStyle("-fx-text-fill: #000000; ");//-fx-font-size: 16px;");
            textFieldList.add(textField);
        }

        //Finally add to the vbox
        this.vbox_HTTPSuccessAttemptLocalServer.getChildren().addAll(textFieldList);
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
        List<JFXTextField> textFieldList = new ArrayList<>();

        for (String iteration_success : this.report.getTcp_details().getIteration_success_tor_https()) {
            //each ip is to be loaded in a text field
            JFXTextField textField = new JFXTextField();
//            textField.setText(iteration_success);
            if (isWithin(iteration_success)) {
                textField.setText("Attempt " + iteration_success + " Successful");
            }
            textField.setEditable(false);
            textField.setStyle("-fx-text-fill: #000000; ");//-fx-font-size: 16px;");
            textFieldList.add(textField);
        }

        //Finally add to the vbox
        this.vbox_HTTPSSuccessAttemptTOR.getChildren().addAll(textFieldList);
    }

    private void loadHTTPSSuccessIteration_LocalServer() {
        List<JFXTextField> textFieldList = new ArrayList<>();

        for (String iteration_success : this.report.getTcp_details().getIteration_success_local_https()) {
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
        this.vbox_HTTPSSuccessAttemptLocalServer.getChildren().addAll(textFieldList);
    }

    private boolean is_censored_http(String iteration_success) {
        try {
            int it = Integer.parseInt(iteration_success);
            //get the .isCensored array [it]
            List<String> list = this.report.getTcp_details().getIs_censored_http_thisIP();
            if (list.get(it).equals("0")) {
                //is_censored = false
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private boolean is_censored_https(String iteration_success) {
        int it = Integer.parseInt(iteration_success);
        //get the .isCensored array [it]
        List<String> list = this.report.getTcp_details().getIs_censored_https_thisIP();
        if (list.get(it).equals("0")) {
            //is_censored = false
            return false;
        } else {
            return true;
        }
    }
}
