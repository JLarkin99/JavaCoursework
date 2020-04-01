/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmweatherclientserver;

import java.net.*;
import java.io.*;
/**
 *
 * @author N0812181
 */
public class ServerSocketHandler extends Thread {
    Socket socket;
    // server type = 0 for user; type = 1 for weather station;
    
    int serverType;
    public ServerSocketHandler(Socket newSocket){
    socket = newSocket;
    
    }
    
    public void run() {
        // decide if socket is user or weather station
        
        // Reads an integer from the client and then runs the appropriate thread
        
            try{
                System.out.println("handler has been ran");
                DataInputStream inFromClient = new DataInputStream(socket.getInputStream());
                serverType = inFromClient.readInt();
            }
            catch(IOException e){
                System.out.println("exception occured");
            }
            //if user client run user thread
            if(serverType == 0){
                System.out.println("choice 0 has been made");
                new ServerUserThread(socket).run();
            }
            //if weather client run weather thread
            else if (serverType == 1){
                System.out.println("choice 1");
                new ServerWeatherThread(socket).run();
            }
            else{};
    }
}
