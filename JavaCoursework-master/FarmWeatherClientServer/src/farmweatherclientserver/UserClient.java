/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmweatherclientserver;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author N0812181
 */
public class UserClient {
    public static void main(String[] args) throws IOException {
        
        //socket that the client connects to
        Socket server = new Socket("localhost",9090);
        System.out.println("connected to" + server.getInetAddress());
        //define input and output streams for communicating with the server
        DataInputStream inFromServer = new DataInputStream(server.getInputStream());
        
        DataOutputStream outToServer = new DataOutputStream(server.getOutputStream());
        // sends why type of client this is to the server handler
        
        System.out.println("sending data type");
        outToServer.writeInt(0);
        System.out.println("sent data type");
        //read and print text from server
        String text = inFromServer.readUTF();
        try {
            
            System.out.println(text);
            //stop process for 10 seconds
        TimeUnit.SECONDS.sleep(10);
        }
        catch(InterruptedException ex)
        {
            
        }
        server.close();
        System.out.println("disconnected");
    
    }
}

