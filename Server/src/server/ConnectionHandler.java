package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionHandler implements Runnable {

    ServerSocket srvrSocket = null;
    Socket client;
    DataInputStream in;

    public ConnectionHandler(ServerSocket srvrSocket, Socket client) throws IOException {
        this.srvrSocket = srvrSocket;
        this.client = client;
        in = new DataInputStream(client.getInputStream());
    }

    @Override
    public void run() {
        //wating for connections
        while (true) {

            System.out.println("<server> Waiting messages... <server>");

            

        }
    }

}
