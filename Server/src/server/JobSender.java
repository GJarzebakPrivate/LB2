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
public class JobSender implements Runnable {
    
    private LinkedList<Data> connectedData;
    
    private String messageBeingSent;
    private int messageCapacity = 0;
    private int port = 0;
    
    
    public JobSender(LinkedList<Data> listOfNodes, int RandomNum, String sendermessage) {
        connectedData = listOfNodes;
        messageCapacity = RandomNum;
        messageBeingSent = sendermessage;
     
    }
    
    

    
    public void sendMessage() {

        String host = "localhost";
        String message = messageBeingSent;

        System.out.println("THE MESSAGE CAPACITY WAS " + this.messageCapacity);
        try {

            DatagramSocket client = new DatagramSocket();
            InetAddress addr = InetAddress.getByName(host);

            int port = 0;
            int highestCapacityNodeCapacity = 0;
            int highestCapacityNodeIndex = 0;

            for (Data connectedDataNode : connectedData) {

                if (connectedDataNode.getCapacityInSeconds() > highestCapacityNodeCapacity) {
                    highestCapacityNodeCapacity = connectedDataNode.getCapacityInSeconds();
                    highestCapacityNodeIndex = connectedData.indexOf(connectedDataNode);
                    port = connectedDataNode.getPort();
                }

            }
            
            
              if (this.messageCapacity <= highestCapacityNodeCapacity) {
            connectedData.get(highestCapacityNodeIndex).decreaseNodeCapacity(this.messageCapacity);}
            else{
                System.out.println("Currently no server has the capacity to process that message");
            }
                       //System.out.println("Current capacity of the node is  : " + connectedData.get(highestCapacityIndex).getCapacityInSeconds());
            System.out.println("Current capacity of the node is  : " + connectedData.get(highestCapacityNodeIndex).getCapacityInSeconds());
            //String s = "Current capacity of the node is  : " + String.valueOf(connectedData.get(highestCapacityIndex).getCapacityInSeconds());
            String s = "Current capacity of the node is  : ";
                    

            DatagramPacket packet11 = new DatagramPacket(message.getBytes(), message.getBytes().length, addr, port);
            DatagramPacket packet22 = new DatagramPacket(s.getBytes(), s.getBytes().length, addr, port);
            client.send(packet11);
            client.send(packet22);
            client.close();
              
              
              /*
            DatagramPacket packet11 = new DatagramPacket(message.getBytes(), message.getBytes().length, addr, getHighestCapacityFromNodes());

            client.send(packet11);


            //System.out.print("Message send to node: " + CapacitySorter());
            client.close();*/

        } catch (Exception error) {
            System.out.print("error");
        }

    }
    
        @Override
    public void run() {
        sendMessage();

    }
    
    
    
}
