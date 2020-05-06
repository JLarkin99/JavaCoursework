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
import farmweatherclientserver.FarmWeatherServer;

public class ServerUserThread extends Thread {
    protected Socket socket;
    
    public ServerUserThread(Socket clientSocket){
    socket = clientSocket;
    }
    public void run() {
        //connect to the client and say hello
        DataOutputStream out = null;
        DataInputStream in = null;
        try {
            
            out = new DataOutputStream(socket.getOutputStream());
            
            //out.writeUTF("Server says hello,User!");
            out.writeUTF(" there are " + FarmWeatherServer.weatherList.size() + " connected stations, please select the number to download" );
            
            in = new DataInputStream(socket.getInputStream());
            String opt = in.readUTF();
            int option = Integer.parseInt(opt);
            System.out.println(option);
            out.writeUTF(FarmWeatherServer.weatherList.get(option-48).getStatistics());
            
        } catch (IOException e) {
            return;
        }
    }
    
}
