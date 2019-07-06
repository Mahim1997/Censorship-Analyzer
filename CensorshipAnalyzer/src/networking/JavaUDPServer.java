package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import main.Config;

public class JavaUDPServer {

    private static final int BYTE_SIZE = 4096;

//    static DatagramSocket serverJava;
//    
//    public static void initialiseServerJava(){
//        try {
//            serverJava = new DatagramSocket(Config.PORT_JAVA);
//        } catch (SocketException ex) {
//            ex.printStackTrace();
//        }
//        
//    }
//    
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
            
            
            
        }
    }

}