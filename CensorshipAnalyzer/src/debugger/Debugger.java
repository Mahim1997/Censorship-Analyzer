package debugger;

import java.util.List;
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
    public static void testPythonProgram(){
        System.out.println("Inside Debugger.testPythonProgram ... ");
        PythonCodeExecutor pce = new PythonCodeExecutor();
        List<String> result = pce.getOutput("python3 New_Python_Programs/testPythonProgram.py Mahim#Mahbub#019256059878#Helalala");
        System.out.println(result);
        
    }
}
