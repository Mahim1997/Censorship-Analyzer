/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.pythonCodeExecutorAndNetworkInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kayem
 */
public class PythonCodeExecutor {

    public List<String> getOutput(String command) {
        List<String> result = new ArrayList<String>();
        try {
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String ret;

            while ((ret = err.readLine()) != null) {
                System.out.println(ret);
            }
            while ((ret = in.readLine()) != null) {
                result.add(ret);
            }

        } catch (Exception e) {
            System.out.println("command error");
            e.printStackTrace();
        }
        return result;
    }

    

}