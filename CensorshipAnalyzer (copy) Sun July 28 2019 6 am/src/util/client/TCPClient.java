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
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            s = new Socket(Config.SERVER_IP_ADDRESS, Config.SERVER_PORT);

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

    private void cleanUp() {
        try {
            br.close();
            pr.close();
            s.close();
        } catch (IOException e) {

        }
    }

    // ---------------- NOT USED ---------------
    public String formReportNetworkUser(Report report) {
        String str = "";

        str += User.getUserDetails();
        str += "#";
        str += Network.getNetworkDetails();
        str += "#";
        str += report.getReportString();
        return str;

    }

    // ---- USED ------_
    public String formReportNetworkUser_New(Report report) {
        String str = "";

        str += User.getUserDetails_New();
        str += "$";
        str += Network.getNetworkDetails_New();
        str += "$";
        str += report.getReportString_New();
        return str;

    }

    public void send(Report report) {
        try {
            ReportDetailedThing reportDetailedThing = new ReportDetailedThing();
            reportDetailedThing.setReportThings(report);
            reportDetailedThing.setNetworkDetails();
            reportDetailedThing.setDetailsAccordingToTestType(report);
            s = new Socket(Config.SERVER_IP_ADDRESS, Config.SERVER_PORT);
            
            try {
                ObjectOutputStream oot = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));
                oot.writeObject(reportDetailedThing);
                oot.flush();
                oot.close();

            } catch (IOException e) {
                System.out.println("-->>OBJECT OUTPUT STREAM WRITING ERROR !!!");
                e.printStackTrace(System.err);
            }
            
        } catch (IOException ex) {
            System.out.println("-->>[SOCKET OPENING ERROR]EXCEPTION IN SENDING MESSAGE TO TCP_SERVER");
        }

    }

}
