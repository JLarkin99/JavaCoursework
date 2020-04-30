/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmweatherclientserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author Fernando
 */
public class LoginCheck {

    private static Scanner newUser;
    
    //Made redundant by login gui
    Boolean userLogin() {
    File filePath = new File("Users.txt");
    
    Scanner userDetails = new Scanner(System.in);
    
    System.out.println("Enter your Usernamer:");
    String username = userDetails.nextLine();
    
    System.out.println("Enter your Password:");
    String password = userDetails.nextLine();
    
    return userVerify(username, password, filePath);
    }
    
    public Boolean userVerify(String username, String password, File filePath){
        String checkUsername;
        String checkPassword;
        Boolean isCorrect = false;
        
        try{
        
            newUser = new Scanner(filePath);
            newUser.useDelimiter(",|\\n");
            
            while(!isCorrect && newUser.hasNext())
            {
                checkUsername = newUser.next();
                checkPassword = newUser.next();
                
                if(checkUsername.equals(username) && checkPassword.equals(password))
                {
                    isCorrect = true;               
                }

            }
            newUser.close();

        }
        catch(FileNotFoundException e)
        {
            System.out.println("Something went wrong :/");
        }
        return isCorrect;
    }
}
