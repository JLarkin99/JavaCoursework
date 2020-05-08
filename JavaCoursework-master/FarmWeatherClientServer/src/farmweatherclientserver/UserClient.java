/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmweatherclientserver;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

import java.util.*;

/**
 *
 * @author N0812181
 */
public class UserClient {
    Socket server = null;
    ObjectInputStream objectIn = null;
    int choice = 0;
    String Username;
    //public int currentStationIndex = 0;
    List<Integer> currentStats = new ArrayList<Integer>();
    public String[] connectedStations = new String[] {"A","B","C"};
    
    
    public UserClient(String name) throws IOException {
        
        boolean waiting = true;
        Username = name;
        while(waiting){
        //socket that the client connects to
        try{
        server = new Socket("localhost",9090);
        System.out.println("connected to" + server.getInetAddress());
        waiting = false;
        }
        catch(ConnectException e){
            
        }
        }
        //define input and output streams for communicating with the server
        DataInputStream inFromServer = new DataInputStream(server.getInputStream());
        
        DataOutputStream outToServer = new DataOutputStream(server.getOutputStream());
        // sends what type of client this is to the server handler
        
        System.out.println("sending data type");
        outToServer.writeInt(0);
        System.out.println("sent data type");
        
        outToServer.writeUTF(Username);
        
        //read and print text from server
//        String text = inFromServer.readUTF();
//        System.out.println(text);
        try {
            objectIn = new ObjectInputStream(server.getInputStream());
        }
        catch(IOException e){}

        currentStats = getDataFromServer();
        System.out.println(currentStats);
//        while(true){
//        
////        int option = System.in.read();
////        
////        outToServer.writeUTF(String.valueOf(option));
////        String stats = inFromServer.readUTF();
////        System.out.print(stats);
//        
//        
//        try {
//            
//            
//            //stop process for 10 seconds
//        
//        List<Integer> currentStats = getDataFromServer();
//        System.out.println(currentStats);
//        TimeUnit.SECONDS.sleep(10);
//        }
//        catch(InterruptedException ex)
//        {
//            break;
//        }
//        }
        
        
        
//        server.close();
//        System.out.println("disconnected");
    
    }
    
    
    
    public void setChoice(int newChoice){
        choice = newChoice;
    }
    
    public List<Integer> getStats(){
        return currentStats;
    }
    
    public void getConnectedStations(){
        List<String> stations = new ArrayList<String>();
        List<Integer> IDs = new ArrayList<Integer>();
        try{
        System.out.println("resetting output stream");
            DataOutputStream outToServer = new DataOutputStream(server.getOutputStream());
           
            
            System.out.println("asking for data");
            outToServer.writeBoolean(true);
            try {
                //System.out.println("resetting input stream");
                //ObjectInputStream objectIn = new ObjectInputStream(server.getInputStream());
                IDs = (List<Integer>) objectIn.readObject();
                //System.out.print(stats);
            } catch (ClassNotFoundException e) {
             System.out.println("class not found");
            }
            
            for(Integer i : IDs){
                stations.add("Station " + i);
            }
            
            
        }
        catch(IOException e){}
        
        
        connectedStations =  stations.toArray(new String[stations.size()]);
        
    }
    
    public void doSomething(){
        System.out.println("test");
    }
    
    public List<Integer> getDataFromServer() {
        List<Integer> stats = new ArrayList<Integer>();

        try {
            //int option = System.in.read();
            //DataInputStream inFromServer = new DataInputStream(server.getInputStream());
            System.out.println("resetting output stream");
            DataOutputStream outToServer = new DataOutputStream(server.getOutputStream());
            
            
            System.out.println("asking for data");
            outToServer.writeBoolean(false);
            
            
            outToServer.writeUTF(String.valueOf(choice));

            try {
                System.out.println("resetting input stream");
                //ObjectInputStream objectIn = new ObjectInputStream(server.getInputStream());
                stats = (List<Integer>) objectIn.readObject();
                //System.out.print(stats);
            } catch (ClassNotFoundException e) {
              System.out.println("class not found");
            }
        } catch (IOException e) {
            System.out.println("io exception");
        }
        return stats;
    }
}

