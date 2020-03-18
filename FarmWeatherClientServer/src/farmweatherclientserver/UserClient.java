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
        
        Socket server = new Socket("localhost",9090);
        System.out.println("connected to" + server.getInetAddress());
        try {
        TimeUnit.SECONDS.sleep(10);
        }
        catch(InterruptedException ex)
        {
            
        }
        server.close();
        System.out.println("disconnected");
    
    }
}

