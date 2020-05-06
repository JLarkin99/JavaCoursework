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

public class ServerWeatherThread extends Thread {
    protected Socket socket;
    WeatherStation ws;
    public ServerWeatherThread(Socket weatherSocket){
    socket = weatherSocket;
    }
    public void run() {
        
        DataOutputStream out = null;
        ObjectOutputStream objectOut = null;
        ObjectInputStream objectIn = null;
        try {
            //connect to the client and say hello
            out = new DataOutputStream(socket.getOutputStream());
            
            //FarmWeatherServer.StationCount += 1;
            out.writeUTF("Server says hello, Weather Station! Test is " + FarmWeatherServer.StationCount);
            
            objectOut = new ObjectOutputStream(socket.getOutputStream());
            objectIn = new ObjectInputStream(socket.getInputStream());
            boolean reading = true;
            
            //////
            while(reading){
            try{
            ws = (WeatherStation) objectIn.readObject();
            }
            catch(ClassNotFoundException e){
                
            }
            //If Ids match, replace weather station rather than append
            boolean replaceFlag = false;
            int replaceIndex = 0;
            if(!FarmWeatherServer.weatherList.isEmpty()){
                for(WeatherStation w: FarmWeatherServer.weatherList){
                    if(w.getID() == ws.getID()){
                        //FarmWeatherServer.weatherList.set(FarmWeatherServer.weatherList.indexOf(w),ws);
                        replaceFlag = true;
                        replaceIndex = FarmWeatherServer.weatherList.indexOf(w);
                        System.out.println("replace index is" + FarmWeatherServer.weatherList.indexOf(w));
                        break;
                    }
                    
                }
            } 
            //if empty, append
            if(replaceFlag){
                //System.out.println("replace index is" + replaceIndex);
                //FarmWeatherServer.weatherList.set(FarmWeatherServer.weatherList.indexOf(replaceIndex),ws);
                FarmWeatherServer.weatherList.set(replaceIndex,ws);
            }
            else{
                FarmWeatherServer.weatherList.add(ws);
            }
            System.out.println(ws.getStatistics());
            FarmWeatherServer.StationCount += 1;
            
            }    
        } catch (IOException e) {
            return;
        }
        
        
    }
}
