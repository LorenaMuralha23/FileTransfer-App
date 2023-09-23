package Controller;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class Controller {
    int srvPort;
    InetAddress serverIP =  null;
    Socket socket;
    Scanner input = new Scanner(System.in);
    DataOutputStream out;
    public static String clientName = "Lorena";

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
            
            JSONObject yourInfo = new JSONObject();
            
            yourInfo.put("name", "Lorena");
            yourInfo.put("id", "78965");
            
            out.writeUTF(yourInfo.toString());
                
           
            
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendFile(File fileSelected){
        JSONObject jsonToSend = new JSONObject();
        
        jsonToSend.put("name", Controller.clientName);
        jsonToSend.put("command", "upload");
        
        String msgToSend = jsonToSend.toString();
        
        
        
        try {
            out.writeUTF(msgToSend);
            
            out.writeUTF(fileSelected.getName());
            out.writeLong(fileSelected.length());
            
            FileInputStream file = new FileInputStream(fileSelected);//A FileInputStream obtains input bytes from a file in a file system. 
            //What files are available depends on the host environment.
            byte[] buffer = new byte[4096];
            int bytesRead;
            
            while((bytesRead = file.read(buffer)) != -1){
                out.write(buffer, 0, bytesRead);
            }
            file.close();
            JOptionPane.showConfirmDialog(null, "File sended sucessed");
            
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
