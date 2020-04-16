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

public class ServerUserThread extends Thread {
    protected Socket socket;
    
    public ServerUserThread(Socket clientSocket){
    socket = clientSocket;
    }
    public void run() {
        //connect to the client and say hello
        DataOutputStream out = null;
        try {
            
            out = new DataOutputStream(socket.getOutputStream());
            
            out.writeUTF("Server says hello,User!");
        } catch (IOException e) {
            return;
        }
    }
    
}
