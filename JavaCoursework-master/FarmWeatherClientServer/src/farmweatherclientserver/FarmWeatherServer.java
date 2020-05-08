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

public class FarmWeatherServer extends Thread {

    /**
     * @param args the command line arguments
     */
    //Port for connections
    static final int CLIENTPORT = 9090;
    static int clientCounter = 0;
    //To do: implement adding weather stations to list
    public static List<WeatherStation> weatherList = new ArrayList<WeatherStation> ();
    public static List<String> nameList = new ArrayList<String>();
    public static int StationCount = 0;
    //list of weather stations (as objects or their sockets?)
    
    ServerSocket client = null;
        ServerSocket weather = null;
        Socket clientSocket = null;
        Socket weatherSocket = null;
    
    public void run() {
        //Declares sockets
        try{
        mainFunction();
        }
        catch(IOException e){}
        
       
            
    }
    
    //return string array of connected stations by ID
    public String[] getConnectedStations(){
        List<String> stations = new ArrayList<String>();
        for(WeatherStation ws : weatherList){
            stations.add("station " + ws.getID());
        }
        
        return stations.toArray(new String[stations.size()]);
    }
    
    public String[] getUsers(){
        List<String> names = new ArrayList<String>();
        names = nameList;
        
        return names.toArray(new String [names.size()]);
    }
    
    void mainFunction() throws IOException{
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
