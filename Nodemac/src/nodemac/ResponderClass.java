/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodemac;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gerard
 */
public class ResponderClass implements Runnable {

    int messageCapacity;
    String messageContent;
    String host;
    DatagramSocket socket;

    public ResponderClass(String stringFromPacket, String host, int nodeportArg) {
        String[] messageParts = stringFromPacket.split(",");
        this.messageContent = messageParts[0];
        this.messageCapacity = Integer.parseInt(messageParts[1]);
        this.host = host;
        try {
            this.socket = new DatagramSocket(nodeportArg);
        } catch (SocketException ex) {
            Logger.getLogger(ResponderClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        ;
    }

    @Override
    public void run() {

        try {

            for (int i = 0; i < this.messageCapacity; i++) {
                System.out.println("Currently working on the message : " + this.messageContent);
                //TimeUnit.SECONDS.sleep(1);
                Thread.sleep(1000);
            }

            InetAddress addr = InetAddress.getByName(this.host);
            //String message = "REGISTER,node2,102.772.1.20,8000";       
            DatagramPacket packet1 = new DatagramPacket(this.messageContent.getBytes(), this.messageContent.getBytes().length, addr, 5000);
            //DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, addr, 5000);
            //DatagramSocket socket = new DatagramSocket(4000);
            this.socket.send(packet1);
            socket.close();
        } catch (Exception error) {
            System.out.print("error");
        }

    }

}
