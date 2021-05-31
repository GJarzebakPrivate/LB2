/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.LinkedList;


/**
 *
 * @author soab
 */
public class Serversender implements Runnable {
    
    private LinkedList<Data> connectedData;
    
    private int MessageCapactiy; 
    private String message2send;
    private int max = 0;
    private int port = 0;
    
    
    public Serversender(LinkedList<Data> Tabledata, int RandomNum, String sendermessage) {
        connectedData = Tabledata;
        MessageCapactiy = RandomNum;
        message2send = sendermessage;
     
    }
    
    @Override
    public void run() {
        sendMessage();

    }
    
    public int CapacitySorter() {
        
        int port = 0;
        for (Data M : connectedData) {

            if (M.getCapactiy() > max) {
                this.max = M.getCapactiy();
                this.port = M.getPort();
               // connectedData.get(max).SetCapacity(50);
            }

        }
        return port;

    }
    
    public void sendMessage() {

        String host = "localhost";
        String message = message2send;
        
         System.out.println("THE MESSAGE CAPACITY WAS " + this.max );
            try {
                if (CapacitySorter() >= MessageCapactiy) {
                DatagramSocket client = new DatagramSocket();
                InetAddress addr = InetAddress.getByName(host);
                DatagramPacket packet11 = new DatagramPacket(message.getBytes(), message.getBytes().length, addr, CapacitySorter());

                client.send(packet11);
                
                for (Data M : connectedData) {

                        if (M.getPort() == CapacitySorter()) {
                            System.out.println("before: " + M.getCapactiy() );
                            M.ChangeCapacity(+MessageCapactiy);
                            System.out.println("AFTER: " + M.getCapactiy() );
                            //connectedData.get(M).ChangeCapacity(+MessageCapactiy);
                            
                        }

                    }
               
                
                
                
                //System.out.print("Message send to node: " + CapacitySorter());

                client.close();
                }else {
            System.out.println("ALL NODES ARE BUSY AND DONT HAVE CAPACITY FOR THIS MESSAGE");
        }
            } catch (Exception error) {
                System.out.print("error");
            }
            
        
        
        

    }
    
    
    
}
