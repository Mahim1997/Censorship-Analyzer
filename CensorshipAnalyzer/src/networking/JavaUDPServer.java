package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import javafx.application.Platform;

public class JavaUDPServer {

    private static final int BYTE_SIZE = 1024;

    protected static void runServer(DatagramSocket socket, String udp_ip_address, int udp_PORT_Server_Java) {
        byte[] receivedBytes = new byte[BYTE_SIZE];
        DatagramPacket rPacket;
        while (true) {
            rPacket = new DatagramPacket(receivedBytes, receivedBytes.length);
            try {
                socket.receive(rPacket);
            } catch (IOException ex) {
                System.out.println("<><>ERROR in UDP_Server.runServer() ... socket.receive(packet) EXCEPTION!!");
            }
            String data = new String(rPacket.getData()) ;
            System.out.println("<RECEIVED in Java> : " + data);
            
            Platform.runLater(() -> {
                
            });
            
        }
    }

}