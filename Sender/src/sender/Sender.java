/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author soab
 */
public class Sender {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String host = "localhost" ;
        try{
            InetAddress addr = InetAddress.getByName(host);
            //String message = "REGISTER,node2,102.772.1.20,8000";
                        
            System.out.println("WELCOME: \n" + "SEND A MESSAGE: ");
            
            BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
            String Inputmessage = reader.readLine();

            // Printing the read line
            
            DatagramPacket packet = new DatagramPacket(Inputmessage.getBytes(), Inputmessage.getBytes().length, addr, 5000);
            //DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, addr, 5000);
            DatagramSocket socket = new DatagramSocket(3000);
            socket.send(packet);
            socket.close();
        } catch (Exception error){
            
        }
        
        
    }
    
}
