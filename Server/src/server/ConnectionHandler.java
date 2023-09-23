package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

    public ConnectionHandler(ServerSocket srvrSocket, Socket client) throws IOException {
        this.srvrSocket = srvrSocket;
        this.client = client;
        in = new DataInputStream(client.getInputStream());
    }

    @Override
    public void run() {
        //wating for messages
        while (true) {
            byte[] msgByte = null;

            System.out.println("<server> Waiting messages... <server>");

            try {

                String msgReceived = in.readUTF();
                JSONObject commandJson = new JSONObject(msgReceived);

                if (commandJson.get("Command").equals("upload")) {
                    System.out.println("<server> He wants to upload a file! <server>");
                } else {
                    System.out.println("<server> He wants to download a file! <server>");
                }

            } catch (IOException ex) {
                Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
