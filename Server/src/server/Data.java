/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author soab
 */
public class Data {
    
    private String nodeName;
    private String nodeIpNumber;
    private int nodePortNumber; 
    private boolean isNodeAvailable;
    private int nodeCapacityInSeconds; 
    
    public Data(String name, String ip, int port, boolean availabile, int Capacity){
        nodeName = name;
        nodeIpNumber = ip;
        nodePortNumber = port;
        isNodeAvailable = availabile;
        nodeCapacityInSeconds = Capacity;
    }
    
    public int getCapacityInSeconds(){      
        return nodeCapacityInSeconds;
    }
    
    public void setCapacityInSeconds(int nC){
       //
       if(nodeCapacityInSeconds > 0) {
           
           this.nodeCapacityInSeconds = nC;
       }else {
           nodeCapacityInSeconds = 0 ;
       }
       
    }
    
    public void increaseNodeCapacity(int addNum) {
        this.setCapacityInSeconds(this.nodeCapacityInSeconds + addNum);
    }
    
    public void decreaseNodeCapacity(int addNum) {
        
        if(this.getCapacityInSeconds() - addNum >= 0)
        {
            this.setCapacityInSeconds(this.nodeCapacityInSeconds - addNum);
        }else{
                this.setCapacityInSeconds(0);
                }
        
        
    }
    

    public String getNodeName(){
        return nodeName;
    }
    
    public String getNodeIP(){
        return nodeIpNumber;
    }
    
    public int getPort(){
        return nodePortNumber;
    }
    
    public void display(){
        System.out.println("Machine: <" + nodeName + "> <" + nodeIpNumber + "> <" + nodePortNumber + ">");
    }
    
    public boolean getavailability(){
        return isNodeAvailable;
    }
}
