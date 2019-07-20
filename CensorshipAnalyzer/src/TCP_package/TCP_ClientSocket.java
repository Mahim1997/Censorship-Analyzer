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
import java.net.Socket;
import javafx.geometry.Pos;
import ui.sounds.Notification;

public class TCP_ClientSocket {

    public void sendSomething(String sentence) {
        try {
            Socket clientSocket = new Socket("localhost", 5000);
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.println(sentence);
            clientSocket.close();
        } catch (IOException e) {
            Notification.push("Warning", "Unable to connect to Software Server", Notification.WARNING, Pos.BOTTOM_RIGHT);
        }
        
    }

}
