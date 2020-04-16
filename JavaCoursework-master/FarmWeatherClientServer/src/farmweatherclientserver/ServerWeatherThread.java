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
        
        DataOutputStream out = null;
        try {
            //connect to the client and say hello
            out = new DataOutputStream(socket.getOutputStream());
            
            out.writeUTF("Server says hello, Weather Station!");
        } catch (IOException e) {
            return;
        }
        
    }
}
