/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_package;

/**
 *
 * @author kayem
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import javafx.geometry.Pos;
import main.Config;

public class TCP_ClientSocket {

    public void sendSomething(String sentence) {
        try {
            InetAddress ip_address = InetAddress.getByAddress(Config.IP_ADDRESS.getBytes());
            try (Socket clientSocket = new Socket(ip_address, 7777)) { //"localhost"
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outToServer.println(sentence);
            }
        } catch (IOException e) {
            System.out.println("--->>>TCPClientSocket.sendSomething() PROBLEM...");
//            Notification.push("Warning", "Unable to connect to Software Server for sending data ... ", Notification.WARNING, Pos.BOTTOM_RIGHT);
        }
        
    }

}
