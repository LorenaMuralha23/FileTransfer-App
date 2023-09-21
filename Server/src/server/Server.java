package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) {

        int serverPort = 0;
        Socket client;

        if (args.length == 1) {

            try {
                serverPort = Integer.parseInt(args[0]);
                ServerSocket server = new ServerSocket(serverPort);

                //Waiting for connections
                while (true) {
                    System.out.println("Waiting for connection...");
                    client = server.accept();

                    //A client have connected
                    System.out.println("<SERVER> NEW CONNECTION DONE SUCCESFULLY! <SERVER>");
                    System.out.println("<SERVER> Connecting with client at port <" + client.getPort() + "> and IP <" + client.getInetAddress() + "> <SERVER>");

                    ConnectionHandler handler = new ConnectionHandler(server, client);
                    Thread connectionThread = new Thread(handler);
                    connectionThread.start();

                }

            } catch (NumberFormatException e) {
                System.out.println("<ERROR> Invalid argument for server port! <ERROR>");
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("Missing arguments!");
        }

    }

}
