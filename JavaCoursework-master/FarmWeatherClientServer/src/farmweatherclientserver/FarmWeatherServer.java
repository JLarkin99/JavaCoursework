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
import farmweatherclientserver.ServerSocketHandler;
import farmweatherclientserver.WeatherStationClient;
import java.io.*;
import java.util.*;
import java.net.*;

public class FarmWeatherServer {

    /**
     * @param args the command line arguments
     */
    //Port for connections
    static final int CLIENTPORT = 9090;
    static int clientCounter = 0;
    //To do: implement adding weather stations to list
    public static List<WeatherStation> weatherList = new ArrayList<WeatherStation> ();
    public static int StationCount = 0;
    //list of weather stations (as objects or their sockets?)
    
    public static void main(String[] args) throws IOException {
        //Declares sockets
        ServerSocket client = null;
        ServerSocket weather = null;
        Socket clientSocket = null;
        Socket weatherSocket = null;
        
        client = new ServerSocket(CLIENTPORT);
        
        // Waits for a connection then passes the socket to the handler
        while(true){
            clientSocket = client.accept();
            // call server socket handler
                new ServerSocketHandler(clientSocket).start();
                
                // confirms connection is a new one
                clientCounter += 1;
                System.out.println("Accepted new client number " + clientCounter);

        }
            
    }
    
}
