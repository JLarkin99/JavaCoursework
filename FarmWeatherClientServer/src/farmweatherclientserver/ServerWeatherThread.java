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

public class ServerWeatherThread extends Thread {
    protected Socket socket;
    WeatherStation ws;
    public ServerWeatherThread(Socket weatherSocket){
    socket = weatherSocket;
    }
    public void run() {
        InputStream inp = null;
        BufferedReader brinp = null;
        ObjectOutputStream objOut = null;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            objOut = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        
        //recieve weather station object from client
        //store object in server list
        
    }
}
