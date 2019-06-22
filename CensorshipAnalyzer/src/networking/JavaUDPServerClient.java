package networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import main.Config;

public class JavaUDPServerClient {

    private static String udp_ip_address;
    private static int udp_PORT_Server_Java;
    private static int udp_PORT_Server_Python;
    private static DatagramSocket udpSocket;

    private static void initialise() {
        //Should read from a protected file [Config. file]
        JavaUDPServerClient.udp_PORT_Server_Java = Config.PORT_JAVA;
        JavaUDPServerClient.udp_PORT_Server_Python = Config.PORT_PYTHON;
        JavaUDPServerClient.udp_ip_address = InetAddress.getLoopbackAddress().getHostAddress();
    }

    protected static int UDP_Java_Port_Server() {
        return udp_PORT_Server_Java;
    }

    protected static int UDP_Python_Port_Server() {
        return udp_PORT_Server_Python;
    }

    protected static String getIPAddressUDP() {
        return udp_ip_address;
    }

    public static void runProgram() {
        initialise();
        setUpServer();
        runServer();
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
        JavaUDPServer.runServer(udpSocket, udp_ip_address, udp_PORT_Server_Java);
    }


    
    public static void sendCommandToPython(String msg)
    {
        System.out.println("Sending to python : ");
        System.out.println(msg);
        try {
            DatagramSocket datagramSock = new DatagramSocket();
            byte[] buff ;
            buff = msg.getBytes();
            InetAddress addr = InetAddress.getByName("127.0.0.1");  //Loop-back IP
            DatagramPacket datagramPack = new DatagramPacket(buff, buff.length, addr , Config.PORT_PYTHON);
            System.out.println("-->Sending msg <" + msg + ">");
            datagramSock.send(datagramPack);
        } catch (Exception ex) {
            System.out.println("<><><><><><>   EXCEPTION IN JavaUDPServerClient.sendCommandToPython ... \n\n");
            System.exit(-1);
        } 
    }

}