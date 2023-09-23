package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class ConnectionHandler implements Runnable {

    ServerSocket srvrSocket = null;
    Socket client;
    DataInputStream in;
    DataOutputStream out;
    //this is the path that every file sended by a client will be saved
    String repositoryPath;
    String clientName;

    public ConnectionHandler(ServerSocket srvrSocket, Socket client) throws IOException {
        this.srvrSocket = srvrSocket;
        this.client = client;
        in = new DataInputStream(client.getInputStream());
        out = new DataOutputStream(client.getOutputStream());
        
        String clienConnectedInfo = in.readUTF();
        
        JSONObject clientInfoJSON = new JSONObject(clienConnectedInfo);
        this.clientName = clientInfoJSON.getString("name").toUpperCase();
        
        }

    @Override
    public void run() {
        //wating for messages
        while (true) {
            System.out.println("<server> Waiting messages... <server>");

            try {

                String clientRequest = in.readUTF();
                
                System.out.println("<server> Message received from " + client.getInetAddress() + " <server>");
                                
                JSONObject request_json = new JSONObject(clientRequest);
                
                if(request_json.get("command").equals("upload")){
                    uploadFile();
                }else{
                    downloadFile();
                }
                

            } catch (IOException ex) {
                Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void uploadFile(){
        try {
            String fileName = in.readUTF();
            long fileLenght = in.readLong();
            
            //A file output stream is an output stream for writing data to a File or to a FileDescriptor
            FileOutputStream fileToUpload = new FileOutputStream(this.repositoryPath + fileName); //-> FileOutputStream(String name) = Creates a file output stream to write to 
            //the file with the specified name.
            
            byte[] buffer = new byte[4096];
            int bytesRead;
            
            while((bytesRead = in.read(buffer)) != -1){
                fileToUpload.write(buffer, 0, bytesRead);//writing the data of the file sended into a file in the repository
            }
            
            fileToUpload.close();
            
            System.out.println("<server> A new file had been uploaded in the system <server>");
            System.out.println("===File Details===");
            System.out.println("Client: " + clientName);
            System.out.println("File: " + fileName);
            System.out.println("Path: " + repositoryPath + fileName);
            
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void downloadFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void createServerRepository(){
        String userDirectory = System.getProperty("user.home");
        this.repositoryPath = userDirectory + "\\Server_Repository\\Client_Files\\" + this.clientName;
        File f = new File(this.repositoryPath);
        System.out.println("Repository: " + this.repositoryPath);
        f.mkdir();
    }
    
}
