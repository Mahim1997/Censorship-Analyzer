package debugger;

import java.util.List;
import ui.model.Network;
import util.database.DBHandler;
import util.pythonCodeExecute.NetworkInfoObtainer;
import util.pythonCodeExecute.PythonCodeExecutor;

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
        formConnectionDebug();
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

}
