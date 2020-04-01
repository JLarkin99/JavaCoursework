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
        Socket server = new Socket("localhost",9090);
        System.out.println("connected to" + server.getInetAddress());
        DataInputStream inFromServer = new DataInputStream(server.getInputStream());
        
        DataOutputStream outToServer = new DataOutputStream(server.getOutputStream());
        System.out.println("sending data type");
        //Tell server handler that this is a weather client
        outToServer.writeInt(1);
        System.out.println("sent data type");
        String text = inFromServer.readUTF();
        try {
            System.out.println(text);
        TimeUnit.SECONDS.sleep(10);
        }
        catch(InterruptedException ex)
        {
            
        }
        server.close();
        System.out.println("disconnected");
    
    }
}
// Currently unimplemented class to do with Weather station data
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
