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
            Network.status_static = result.get(0);
            Network.asn_static = result.get(1);
            Network.city_static = result.get(2);
            Network.continent_static = result.get(3);
            Network.country_static = result.get(4);
            Network.latitude_static = result.get(5);
            Network.longitude_static = result.get(6);
            Network.org_static = result.get(7);
            Network.postal_static = result.get(8);
            Network.region_static = result.get(9);
            Network.carrier_static = result.get(10);
            Network.global_ip_static = Network.getGlobalIP();
        } else {
            Network.status_static = "Offline"; //.get(0);
            Network.global_ip_static = "NA";
            Network.asn_static = "NA"; //.get(1);
            Network.city_static = "NA"; //.get(2);
            Network.continent_static = "NA"; //.get(3);
            Network.country_static = "NA"; //.get(4);
            Network.latitude_static = "NA"; //.get(5);
            Network.longitude_static = "NA"; //.get(6);
            Network.org_static = "NA"; //.get(7);
            Network.postal_static = "NA"; //.get(8);
            Network.region_static = "NA"; //.get(9);
            Network.carrier_static = "NA"; //.get(10);
        }

        return result;
    }
}
