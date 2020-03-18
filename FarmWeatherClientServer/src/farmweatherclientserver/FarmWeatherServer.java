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
import java.util.*;
import java.net.*;

public class FarmWeatherServer {

    /**
     * @param args the command line arguments
     */
    
    static final int CLIENTPORT = 9090;
    static final int WEATHERPORT = 9091;
    List<WeatherStationClient> weatherList = new ArrayList<WeatherStationClient> ();
    //list of weather stations (as objects or their sockets?)
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ServerSocket client = null;
        ServerSocket weather = null;
        Socket clientSocket = null;
        Socket weatherSocket = null;
        
        try {
            client = new ServerSocket(CLIENTPORT);
            weather = new ServerSocket(WEATHERPORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        
        while(true){
            if(clientSocket == null){
                clientSocket = client.accept();
                new ServerUserThread(clientSocket).run();
                System.out.println("Accepted new client");
            }
            else if (weatherSocket == null)
            {
                weatherSocket = weather.accept();
                new ServerWeatherThread(weatherSocket).run();
                System.out.println("Accepted new weather station");
                //allow users to select a station from the server list
            }
        }
            
    }
    
}
