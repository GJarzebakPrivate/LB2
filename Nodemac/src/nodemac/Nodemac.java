/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodemac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author soab
 */
public class Nodemac {
    
    private String host;

      
    public void Connect2Server(String message, String host, DatagramSocket socket) {
        try {
            
            InetAddress addr = InetAddress.getByName(host);
            //String message = "REGISTER,node2,102.772.1.20,8000";       
            DatagramPacket packet1 = new DatagramPacket(message.getBytes(), message.getBytes().length, addr, 5000);
            //DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, addr, 5000);
            //DatagramSocket socket = new DatagramSocket(4000);
            socket.send(packet1);
           listening(socket);
           socket.close();
        } catch (Exception error) {

        }
    }
    
    public void listening(DatagramSocket socket) {

        //DatagramSocket server = new DatagramSocket(4160);
        boolean x = true;
        while (x = true){
            byte[] buffer = new byte[1024];
                DatagramPacket p = new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(p);
        } catch (IOException ex) {
            Logger.getLogger(Nodemac.class.getName()).log(Level.SEVERE, null, ex);
        }
                String response = new String(p.getData());
                System.out.println("Got message: " + response);
        }
        

    }
    
      
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        String message = args[0];
        String[] argElements = args[0].trim().split(",");

        int nodeportArg = Integer.parseInt(argElements[3]);

        try {
            DatagramSocket socket = new DatagramSocket(nodeportArg);
            Nodemac sys = new Nodemac();
            sys.Connect2Server(message, host, socket);
                    

        } catch (SocketException ex) {
           // Logger.getLogger(Nodemac.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Port number: " + argElements[3] + " is in use");
        }
        

    }

}
