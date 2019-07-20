package ui.model;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import ui.controllers.CensoredRecordController;
import ui.controllers.CensoredRecordController_Waiting;

public class Report {

    
    
    //Button
    private final Button btn_details;

    //---------------------------- Controllers ------------------------------------
    private CensoredRecordController_Waiting controller_waiting;
    private CensoredRecordController controller_for_db;

    public void setController2(CensoredRecordController_Waiting controller_waiting) {
        this.controller_waiting = controller_waiting;
    }

    public void setController1(CensoredRecordController controller_db) {
        this.controller_for_db = controller_db;
    }
    //---------------------------- Controllers ------------------------------------
    int reportID = -1; //to change
    
    public Report() {
        this.btn_details = new Button("Details");
        this.btn_details.setOnAction((ActionEvent event) -> {
            if (Report.this.controller_waiting != null) {
                String testType_Local = "DNS"; //TO CHANGE
                System.out.println("\n================+>>>>>>>>>Inside Report ... getTestType() = " + testType_Local + "\n");
                switch (testType_Local.toUpperCase()) {
                    case "DNS":
                        //if test type is DNS
                        Report.this.controller_waiting.goToDetailsDNSRecord(Report.this.reportID);
                        break;
                    case "TCP":
                        // if test type is TCP
                        Report.this.controller_waiting.goToDetailsTCPRecord(Report.this.reportID);
                        break;
                    case "HTTP":
                        //TO DO
                        System.out.println("==========+>>>>>>>>> Inside Report.Report() constructor .... TO DO LINE 78");
                        break;
                    default:
                        break;
                }
            } else if (Report.this.controller_for_db != null) {
                //Same thing also here ...
            }
        });
    }


 


}
