/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.pythonCodeExecute;

import java.util.List;
import ui.model.Network;

/**
 *
 * @author mahim
 */
public class NetworkInfoObtainer {

    public static List<String> extractNetworkInfo() {
        PythonCodeExecutor pce = new PythonCodeExecutor();
        List<String> result = pce.getOutput("python3.6 New_Python_Programs/isp_info_final.py");

        if (result.size() == 11) {
            Network.status = result.get(0);
            Network.asn = result.get(1);
            Network.city = result.get(2);
            Network.continent = result.get(3);
            Network.country = result.get(4);
            Network.latitude = result.get(5);
            Network.longitude = result.get(6);
            Network.org = result.get(7);
            Network.postal = result.get(8);
            Network.region = result.get(9);
            Network.carrier = result.get(10);
            Network.global_ip = Network.getGlobalIP();
        } else {
            Network.status = "Offline"; //.get(0);
            Network.global_ip = "NA";
            Network.asn = "NA"; //.get(1);
            Network.city = "NA"; //.get(2);
            Network.continent = "NA"; //.get(3);
            Network.country = "NA"; //.get(4);
            Network.latitude = "NA"; //.get(5);
            Network.longitude = "NA"; //.get(6);
            Network.org = "NA"; //.get(7);
            Network.postal = "NA"; //.get(8);
            Network.region = "NA"; //.get(9);
            Network.carrier = "NA"; //.get(10);
        }

        return result;
    }
}
