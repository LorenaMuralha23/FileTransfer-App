package Controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    int srvPort;
    InetAddress serverIP =  null;
    Socket socket;
    Scanner input = new Scanner(System.in);
    DataOutputStream out;
    
    public Controller(int srvPort, InetAddress srvIP) {
     
        this.srvPort = srvPort;
        this.serverIP = srvIP;
        if((this.srvPort != 0) && (this.serverIP != null)){
            startConnection();
        }else{
            System.out.println("Error: Null arguments!");
        }
    }
    
    public void startConnection(){
        System.out.println("Connecting with server at port <" + srvPort + "> and IP <" + serverIP.toString() + ">");
        try {
            socket = new Socket(serverIP, srvPort);
            out = new DataOutputStream(socket.getOutputStream());
            
            System.out.println("Connected!!");
            
            while(true){
                System.out.print("Message: ");
                String msg = input.nextLine();
                byte[] bytes = msg.getBytes();
                
                
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
