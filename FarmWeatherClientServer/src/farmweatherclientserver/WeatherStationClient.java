/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmweatherclientserver;

import java.io.*;
import java.net.*;
import java.util.*;


/**
 *
 * @author N0812181
 */
public class WeatherStationClient {
    
    //GPS LOCATION, IMPLEMENT LATER
    public static void main(String[] args) throws IOException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Socket server = new Socket("localhost",9091);
        System.out.println("connected to" + server.getInetAddress());
        
        //send weatherstation object to thread
        //oos.writeObject(new WeatherStation());
        
        server.close();
        System.out.println("disconnected");
    
    }
}

class WeatherStation {
    double temperature;
    int humidity;
    int windforce;
    int pressure;
    
    public WeatherStation()
            {
                Random rand = new Random();
                temperature = rand.nextInt(50) - 20;
                humidity = rand.nextInt(50);
                windforce = rand.nextInt(50) - 20;
                pressure = rand.nextInt(50) - 20;
            }
    public String getStatistics()
    {
        return ("temperature is " + temperature +
                "\n humidity is " + humidity +
                "\n windforce is " + windforce +
                "\n pressure is " + pressure);
    }
}
