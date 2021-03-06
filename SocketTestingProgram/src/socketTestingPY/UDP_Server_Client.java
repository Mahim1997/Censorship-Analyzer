package socketTestingPY;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDP_Server_Client {

    private static String udp_ip_address;
    private static int udp_PORT_Server_Java;
    private static int udp_PORT_Server_Python;
    private static DatagramSocket udpSocket;

    private static void initialise() {
        //Should read from a protected file [Config. file]
        UDP_Server_Client.udp_PORT_Server_Java = 7722;
        UDP_Server_Client.udp_PORT_Server_Python = 7731;
        UDP_Server_Client.udp_ip_address = InetAddress.getLoopbackAddress().getHostAddress();
    }

    protected static int UDP_Java_Port_Server() {
        return udp_PORT_Server_Java;
    }

    protected static int UDP_Python_Port_Server() {
        return udp_PORT_Server_Python;
    }

    protected static String getIPAddressUDP() {
        return UDP_Server_Client.udp_ip_address;
    }

    public static void runProgram() {
        UDP_Server_Client.initialise();
        UDP_Server_Client.setUpServer();
        UDP_Server_Client.runServer();
//        UDP_Server_Client.setUpClient();
        UDP_Server_Client.runClient();
    }

    private static void setUpServer() {
        System.out.println("Setting up Java Server...");
        try {
            udpSocket = new DatagramSocket(udp_PORT_Server_Java);
        } catch (SocketException ex) {
            System.out.println("-->>>EXCEPTION in UDP Server Setup setUpServer() .... ");
        }
    }

    private static void runServer() {
        System.out.println("Running Java Server ....");
        UDP_Server.runServer(UDP_Server_Client.udpSocket, UDP_Server_Client.udp_ip_address, UDP_Server_Client.udp_PORT_Server_Java);
    }

    private static void runClient() {
        System.out.println("Running Java Client .... ");
        UDP_Client.runClient(udp_ip_address, udp_PORT_Server_Python);
    }

    
    public static void sendMessage(String msg)
    {
        try {
            int portPython = 7722;
            DatagramSocket ds = new DatagramSocket();
            byte[] buff = msg.getBytes();
            DatagramPacket dp = new DatagramPacket(buff, buff.length, InetAddress.getByName("127.0.0.1"), portPython);
            ds.send(dp);
            System.out.println("-->Successfully sent msg <NEWLINE> \n--------------------------------------------------\n" 
                    + msg + "\n--------------------------------------------------");
        } catch (SocketException | UnknownHostException ex) {
            Logger.getLogger(UDP_Server_Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDP_Server_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
