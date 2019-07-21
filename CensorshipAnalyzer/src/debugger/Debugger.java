package debugger;

import java.util.List;
import ui.model.Network;
import ui.model.Report;
import util.database.DBHandler;
import util.pythonCodeExecutorAndNetworkInfo.NetworkInfoObtainer;
import util.pythonCodeExecutorAndNetworkInfo.PythonCodeExecutor;
import util.workerAndStates.StringProcessor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mahim
 */
public class Debugger {

    public static void debugProgram() {
//        testPythonProgram();
//        formConnectionDebug();
        debugStringProcessor();
    }


    private static void formConnectionDebug() {
        System.out.println("-------- obtaining network info ------------- ");
        NetworkInfoObtainer.extractNetworkInfo();
        System.out.println("----------- network info obtained -------------");
        DBHandler.openConnection();
        DBHandler.formConnection_ID();
        DBHandler.closeConnection();
        System.out.println("---------->> AFTERWARDS ... Network.conID = " + Network.connection_id_static);
        
        
    }



    public static void testPythonProgram() {
        System.out.println("Inside Debugger.testPythonProgram ... ");
        PythonCodeExecutor pce = new PythonCodeExecutor();
        List<String> result = pce.getOutput("python3 New_Python_Programs/testPythonProgram.py Mahim#Mahbub#019256059878#Helalala#url:www.youtube.com");
//        String command = "source:java#userID:2#connectionID:4#typeOfTesting:TCP#timestamp:NULL#url:www.xvideos.com#periodicity:forced#isPeriodic:yes#fileNamePeriodic:1505022.txt#iterationNumber:4";
//        List<String> result = pce.getOutput("python3 New_Python_Programs/pythonServer.py " + command);

        System.out.println(result);

    }

    private static void debugStringProcessor() {
        String str = "ReportID:129$ConnectionID:0$TimeStamp:21-07-2019 07:38:55$URL:bdsaradin.com$Type_of_Test:TCP$is_censored:0$is_periodic:no$file_name_periodic:NULL$iteration_number:0$censorshipDetails:$middle_box_hop_count:-1$ip_address:103.224.182.245$port_number:80$is_tor_not_connected:0$is_time_out:0$is_fin_bit_set:0$is_rst_bit_set:0$successful_iteration_local_server:1$successful_iteration_tor:1$is_tor_connect_successful:1$middle_box_hop_count:-1$is_censored_TCP:0$ip_address:103.224.182.245$port_number:443$is_tor_not_connected:0$is_time_out:0$is_fin_bit_set:0$is_rst_bit_set:0$successful_iteration_local_server:1$successful_iteration_tor:1$is_tor_connect_successful:1$middle_box_hop_count:-1$is_censored_TCP:0";
        Report report = StringProcessor.processStringAndFormReport(str);
        
        report.printReport();
    }

}
