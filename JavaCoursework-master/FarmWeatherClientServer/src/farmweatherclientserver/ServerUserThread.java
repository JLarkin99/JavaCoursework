/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmweatherclientserver;

/**
 *
 * @author N0812181
 */
import java.io.*;
import java.net.*;
import java.util.*;
import farmweatherclientserver.FarmWeatherServer;
import farmweatherclientserver.WeatherStation;

public class ServerUserThread extends Thread {
    protected Socket socket;
    
    DataOutputStream out = null;
    DataInputStream in = null;
    ObjectOutputStream objectOut = null; 
    
    public ServerUserThread(Socket clientSocket){
    socket = clientSocket;
    try{
    in = new DataInputStream(socket.getInputStream());
    out = new DataOutputStream(socket.getOutputStream());
    objectOut = new ObjectOutputStream(socket.getOutputStream());}
    catch(IOException e){}
    }
    public void run() {
        //connect to the client and say hello
        try{
        String name = in.readUTF();
        FarmWeatherServer.nameList.add(name);
        }
        catch(IOException e){}
        
        while(true){
                waitForData();
            }
//        try {
//            //handshake procedure START
//            
//            //out.writeUTF("Server says hello,User!");
//            //System.out.println("about to send utf");
//            //out.writeUTF(" there are " + FarmWeatherServer.weatherList.size() + " connected stations, please select the number to download" );
//            //handshake procedure END
//            
//            while(true){
//                waitForData();
//            }
//            
////            in = new DataInputStream(socket.getInputStream());
////            String opt = in.readUTF();
////            int option = Integer.parseInt(opt);
////            System.out.println(option);
////            objectOut.writeObject(FarmWeatherServer.weatherList.get(option-48).getStatistics());
//            
//        } catch () {
//            return;
//        }
    }
    
    void waitForData() {
        
        try{
        System.out.println("waiting for request"); 
        //to send connected stations
        boolean choice = in.readBoolean();
        if(choice){
            List<Integer> stations = new ArrayList<Integer>();
            //send stations
            
            for(WeatherStation ws: FarmWeatherServer.weatherList ){
                stations.add(ws.getID());
            }
            
            objectOut.writeObject(stations);
        }
        
        else{
        // to send stats
        String opt = in.readUTF();
        int option = Integer.parseInt(opt);
        System.out.println(option);
        objectOut.writeObject(FarmWeatherServer.weatherList.get(option).getStatistics());
        }
        } catch(IOException e){}
        
    }
    
}
