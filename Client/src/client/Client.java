package client;

import Controller.Controller;
import View.CentralView;
import View.MainFrame;
import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws UnknownHostException {
        startWindow();
        
        InetAddress srvAddress = null;
        int srvPort = 0;
      
        if(args.length == 2){
            
            try {
                
                srvAddress = InetAddress.getByName(args[0]);
                
                srvPort = Integer.parseInt(args[1]);
                
                if((srvPort < 1) || (srvPort > 65535)){
                    System.out.println("Error: Port range (1 - 65535)");
                    System.exit(1);
                }
                
            } catch (NumberFormatException e) {
                
                System.out.println("Error: Invalid argument!");
                System.exit(1);
                
            } catch (UnknownHostException e){
                
                System.out.println("Error: Invalid IP!");
                System.exit(1);
                
            }
            
            Controller controller = new Controller(srvPort, srvAddress);
            
        }else{
            System.out.println("Error: No arguments!");
        }
        
        
        
        
        
    }
    
   public static void startWindow(){
       MainFrame window = new MainFrame();

        CentralView centralPanel = new CentralView();
        window.getContentPane().removeAll();
        window.add(centralPanel, BorderLayout.CENTER);
        window.setVisible(true);
        window.pack();
   }
    
}
