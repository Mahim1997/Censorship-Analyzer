/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.client;

/**
 *
 * @author kayem
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import main.Config;
import ui.model.Network;
import ui.model.Report;
import ui.model.User;

public class TCPClient {

    private Socket s = null;
    private BufferedReader br = null;
    private PrintWriter pr = null;

    public void TCPClient() {

    }

    public void send(String output) {
        try {
            s = new Socket(Config.SERVER_IP_ADDRESS, 7777);

            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pr = new PrintWriter(s.getOutputStream());
            String strSend = null, strRecv = null;

            strSend = output;

            System.out.println("----------------- SENDING TO SERVER BEGIN -------------------------");

            System.out.println(strSend);
            System.out.println("----------------- SENDING TO SERVER DONE -------------------------");            
            
            pr.println(strSend);
            pr.println();
            pr.flush();

            cleanUp();

        } catch (IOException e) {
            System.out.println("Problem in connecting with the server in TCPClient.send(). Exiting main.");
//            Notification.push("Error", "Cannot connect to System Server to send data", Notification.FAILURE, Pos.CENTER);
        }

    }
    
    public int login_send_receive(String output) {
        try {
            s = new Socket(Config.SERVER_IP_ADDRESS, 7777);

            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pr = new PrintWriter(s.getOutputStream());
            String strSend = null, strRecv = null;

            strSend = output;

            System.out.println("----------------- SENDING TO SERVER BEGIN -------------------------");

            System.out.println(strSend);
            System.out.println("----------------- SENDING TO SERVER DONE -------------------------");            
            
            pr.println(strSend);
            pr.println();
            pr.flush();
            System.out.println("----------------- SENDING TO SERVER DONE -------------------------");    
            strRecv = br.readLine();
            System.out.println("Received data: "+strRecv);
            int userid = Integer.parseInt(strRecv);
            cleanUp();
            return userid;

        } catch (IOException e) {
            System.out.println("Problem in connecting with the server in TCPClient.send(). Exiting main.");
//            Notification.push("Error", "Cannot connect to System Server to send data", Notification.FAILURE, Pos.CENTER);
            return 0;
        }

    }

    private void cleanUp() {
        try {
            br.close();
            pr.close();
            s.close();
        } catch (IOException e) {

        }
    }

    public String formReportNetworkUser(Report report) {
        String str = "";

        str += User.getUserDetails();
        str += "#";
        str += Network.getNetworkDetails();
        str += "#";
        str += report.getReportString();
        return str;

    }

}
