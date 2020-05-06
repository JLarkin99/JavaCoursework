/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmweatherclientserver;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author N0812181
 */
public class WeatherStationClient {
    
    //GPS LOCATION, IMPLEMENT LATER
    public static void main(String[] args) throws IOException {
       boolean waiting = true;
       Socket server = null;
       //if server isn't open, wait for server to start
       while(waiting){
           try{
               server = new Socket("localhost",9090);
               waiting = false;
           }
           catch(ConnectException e){
               
           }
           
           
       } 
       
       
        
        System.out.println("connected to" + server.getInetAddress());
        DataInputStream inFromServer = new DataInputStream(server.getInputStream());
        
        DataOutputStream outToServer = new DataOutputStream(server.getOutputStream());
        
        
        System.out.println("sending data type");
        //Tell server handler that this is a weather client
        outToServer.writeInt(1);
        System.out.println("sent data type");
        String text = inFromServer.readUTF();
        System.out.println(text);
        ObjectOutputStream objectOut = new ObjectOutputStream(server.getOutputStream());
        ObjectInputStream objectIn = new ObjectInputStream(server.getInputStream());
        WeatherStation ws;
        ws = new WeatherStation();
        System.out.println(ws.getID());
        objectOut.writeObject(ws);
        while(true){
        try {
            
        TimeUnit.SECONDS.sleep(9);
        ws.generateNewData();
        objectOut.writeObject(ws);
        
        }
        catch(InterruptedException ex)
        {
            break;
        }
        }
        server.close();
        System.out.println("disconnected");
    
    }
}

class WeatherStation implements Serializable{
    double temperature;
    int humidity;
    int windforce;
    int pressure;
    Random rand;
    int ID;
    static int tracker = 0;
    
    public int getID(){
        return ID;
    }
    
    public WeatherStation()
            {
                rand = new Random();
                ID = rand.nextInt(1000);
                tracker += 1;
                //ID = tracker;
                
                generateNewData();
            }
    public String getStatistics()
    {
        return ("temperature is " + temperature +
                "\n humidity is " + humidity +
                "\n windforce is " + windforce +
                "\n pressure is " + pressure);
    }
    
    public void generateNewData(){
        temperature = rand.nextInt(50) - 20;
                humidity = rand.nextInt(50);
                windforce = rand.nextInt(50) - 20;
                pressure = rand.nextInt(50) - 20;
    }
}
