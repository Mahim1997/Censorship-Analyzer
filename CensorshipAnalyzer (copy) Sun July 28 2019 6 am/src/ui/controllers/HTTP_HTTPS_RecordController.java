package ui.controllers;

import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import helper.HelperFunctions;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.text.Text;
import ui.model.Report;
import ui.model.TCPDetails;

public class HTTP_HTTPS_RecordController {

    private Report report;

    @FXML
    private Text text_url;
    @FXML
    private Text text_NetworkName;
    @FXML
    private Text text_time;
    @FXML
    private Text text_testType;
    @FXML
    private Text text_networkType;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Tab tab_http;
    @FXML
    private Text text_overAllHTTPCensored;
    @FXML
    private JFXTextField text_ip_1;
    @FXML
    private JFXTextField text_ip_2;
    @FXML
    private JFXTextField text_ip_3;
    @FXML
    private JFXTextField text_ip_4;
    @FXML
    private JFXTextField text_localServer_responseCode_1;
    @FXML
    private JFXTextField text_localServer_responseCode_2;
    @FXML
    private JFXTextField text_localServer_responseCode_3;
    @FXML
    private JFXTextField text_localServer_responseCode_4;
    @FXML
    private JFXTextField text_TOR_responseCode_1;
    @FXML
    private JFXTextField text_TOR_responseCode_2;
    @FXML
    private JFXTextField text_TOR_responseCode_3;
    @FXML
    private JFXTextField text_TOR_responseCode_4;
    @FXML
    private JFXTextField text_localServerRedirection_1;
    @FXML
    private JFXTextField text_localServerRedirection_2;
    @FXML
    private JFXTextField text_localServerRedirection_3;
    @FXML
    private JFXTextField text_localServerRedirection_4;
    @FXML
    private JFXTextField text_TOR_Redirection_1;
    @FXML
    private JFXTextField text_TOR_Redirection_2;
    @FXML
    private JFXTextField text_TOR_Redirection_3;
    @FXML
    private JFXTextField text_TOR_Redirection_4;
    @FXML
    private Text text_overAllHTTPCensored111;
    @FXML
    private Text text_HTTPMessage;
    @FXML
    private Text text_overAllHTTPCensored1111;
    @FXML
    private Text text_otherErrorPresent;
    @FXML
    private Text text_overAllHTTPCensored11111;
    @FXML
    private Text text_max_redirection_cnt;
    @FXML
    private Text text_overAllHTTPCensored111111;
    @FXML
    private Text text_is_fin_bit_set;
    @FXML
    private Text text_overAllHTTPCensored1111111;
    @FXML
    private Text text_is_rst_bit_set;
    @FXML
    private Text text_overAllHTTPCensored11111111;
    @FXML
    private Text text_thresholdComparison;
    @FXML
    private Text text_overAllHTTPCensored111111111;
    @FXML
    private Text text_contentMismatch;
    @FXML
    private Text text_overAllHTTPCensored11112;
    @FXML
    private Text text_middleBoxHopCount;
    @FXML
    private Text text_overAllHTTPCensored111111112;
    @FXML
    private Text text_isCensoredWRTThresholdContentMismatch;
    @FXML
    private Tab tab_https;
    @FXML
    private Text text_overAllHTTPSCensored;
    @FXML
    private JFXTextField text_ip_11;
    @FXML
    private JFXTextField text_ip_21;
    @FXML
    private JFXTextField text_ip_31;
    @FXML
    private JFXTextField text_ip_41;
    @FXML
    private JFXTextField text_localServer_responseCode_11;
    @FXML
    private JFXTextField text_localServer_responseCode_21;
    @FXML
    private JFXTextField text_localServer_responseCode_31;
    @FXML
    private JFXTextField text_localServer_responseCode_41;
    @FXML
    private JFXTextField text_TOR_responseCode_11;
    @FXML
    private JFXTextField text_TOR_responseCode_21;
    @FXML
    private JFXTextField text_TOR_responseCode_31;
    @FXML
    private JFXTextField text_TOR_responseCode_41;
    @FXML
    private JFXTextField text_localServerRedirection_11;
    @FXML
    private JFXTextField text_localServerRedirection_21;
    @FXML
    private JFXTextField text_localServerRedirection_31;
    @FXML
    private JFXTextField text_localServerRedirection_41;
    @FXML
    private JFXTextField text_TOR_Redirection_11;
    @FXML
    private JFXTextField text_TOR_Redirection_21;
    @FXML
    private JFXTextField text_TOR_Redirection_31;
    @FXML
    private JFXTextField text_TOR_Redirection_41;
    @FXML
    private Text text_HTTPMessage1;
    @FXML
    private Text text_otherErrorPresent1;
    @FXML
    private Text text_max_redirection_cnt1;
    @FXML
    private Text text_is_fin_bit_set1;
    @FXML
    private Text text_is_rst_bit_set1;
    @FXML
    private Text text_thresholdComparison1;
    @FXML
    private Text text_overAllHTTPCensored1111111111;
    @FXML
    private Text text_contentMismatch1;
    @FXML
    private Text text_overAllHTTPCensored111121;
    @FXML
    private Text text_middleBoxHopCount1;
    @FXML
    private Text text_overAllHTTPCensored1111111121;
    @FXML
    private Text text_isCensoredWRTThresholdContentMismatch1;
    @FXML
    private Text text_overAllHTTPCensored11111111211;
    @FXML
    private Text text_tlsHandshakeFailed;

    @FXML
    private void selectHTTP_Tab(Event event) {
    }

    @FXML
    private void selectHTTPS_Tab(Event event) {
    }

    public void setUpThings(Report report) {
        this.report = report;
        int is_cens = 0;
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

    private void loadNetworkThings() {
        this.text_NetworkName.setText(this.report.getNetwork_name());
        this.text_networkType.setText(this.report.getNetwork_type());
//        this.text_censoredOrNot.setText("IS CENSORED? " + this.report.getIs_censored());
        this.text_time.setText(this.report.getTime_stamp());
        this.text_testType.setText(this.report.getTest_type());
        this.text_url.setText(this.report.getUrl());

    }

    public void showThings() {
        loadNetworkThings();

        //Pad the list
        int MAX_REDIRECT = 4;
        this.report.httpDetails.pad_fields_for_http(MAX_REDIRECT);
        this.report.httpsDetails.pad_fields_for_https(MAX_REDIRECT);

        loadForHTTP();
        loadForHTTPS();
    }

    private void loadForHTTP() {        //Load the things for HTTP
        //1, Load the IP address
        this.text_ip_1.setText(this.report.httpDetails.getIp_address().get(0));
        this.text_ip_2.setText(this.report.httpDetails.getIp_address().get(1));
        this.text_ip_3.setText(this.report.httpDetails.getIp_address().get(2));
        this.text_ip_4.setText(this.report.httpDetails.getIp_address().get(3));

        //2, load response codes
        //2.1 Local response codes
        this.text_localServer_responseCode_1.setText(this.report.httpDetails.getHttp_response_code_local().get(0));
        this.text_localServer_responseCode_2.setText(this.report.httpDetails.getHttp_response_code_local().get(1));
        this.text_localServer_responseCode_3.setText(this.report.httpDetails.getHttp_response_code_local().get(2));
        this.text_localServer_responseCode_4.setText(this.report.httpDetails.getHttp_response_code_local().get(3));
        //2.2 TOR response codes
        this.text_TOR_responseCode_1.setText(this.report.httpDetails.getHttp_response_code_tor().get(0));
        this.text_TOR_responseCode_2.setText(this.report.httpDetails.getHttp_response_code_tor().get(1));
        this.text_TOR_responseCode_3.setText(this.report.httpDetails.getHttp_response_code_tor().get(2));
        this.text_TOR_responseCode_4.setText(this.report.httpDetails.getHttp_response_code_tor().get(3));

        //3, Load redirection histories
        //3.1 Local Server redirection urls
        this.text_localServerRedirection_1.setText(this.report.httpDetails.getRedirection_history_local().get(0));
        this.text_localServerRedirection_2.setText(this.report.httpDetails.getRedirection_history_local().get(1));
        this.text_localServerRedirection_3.setText(this.report.httpDetails.getRedirection_history_local().get(2));
        this.text_localServerRedirection_4.setText(this.report.httpDetails.getRedirection_history_local().get(3));
        //3.2 TOR redirection urls
        this.text_TOR_Redirection_1.setText(this.report.httpDetails.getRedirection_history_tor().get(0));
        this.text_TOR_Redirection_2.setText(this.report.httpDetails.getRedirection_history_tor().get(1));
        this.text_TOR_Redirection_3.setText(this.report.httpDetails.getRedirection_history_tor().get(2));
        this.text_TOR_Redirection_4.setText(this.report.httpDetails.getRedirection_history_tor().get(3));

        //4, Messages
        this.text_HTTPMessage.setText(this.report.httpDetails.getMessage_HTTP());
        this.text_otherErrorPresent.setText(HelperFunctions.getYesOrNo(this.report.httpDetails.getIs_other_error()));
        this.text_middleBoxHopCount.setText(this.report.httpDetails.getMiddle_box_hop_count());
        this.text_is_fin_bit_set.setText(HelperFunctions.getYesOrNo(this.report.httpDetails.getIs_fin_bit_set()));
        this.text_is_rst_bit_set.setText(HelperFunctions.getYesOrNo(this.report.httpDetails.getIs_rst_bit_set()));
        this.text_max_redirection_cnt.setText(this.report.httpDetails.getMax_redirection_count());
        this.text_thresholdComparison.setText(HelperFunctions.getPercentage(this.report.httpDetails.getThreshold_comparison()));
        this.text_contentMismatch.setText(this.report.httpDetails.getContent_difference());
        this.text_isCensoredWRTThresholdContentMismatch.setText(HelperFunctions.getYesOrNo(this.report.httpDetails.getIs_different_wrt_threshold()));
        
    }

    private void loadForHTTPS() {

    }
}
