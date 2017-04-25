package server.helpers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static server.helpers.Logging.log;


/**
 * The syntax for initial HUNT handshake is as follows (clientSentence):
 * IP::USERNAME::OS::COUNTRY::
 */
public class Listener extends Thread{



    int port;
    String clientSentence;

    public Listener(int port){
        this.port = port;
    }

    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            log("Started listening daemon on port " + port, Logging.LogType.SUCCESS);

            while(true) {
                Socket socket = serverSocket.accept();

                BufferedReader inFromClient =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));
                clientSentence = inFromClient.readLine();
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                System.out.println(clientSentence);

            }
        } catch (IOException ioe){
            try {
                log("Error binding: " + server.helpers.Misc.staticStackTraceToString(ioe), Logging.LogType.ERROR);
                this.interrupt();  // Kill the thread
            } catch (IOException e){
                ioe.printStackTrace();
                System.out.println("\n\n");
                e.printStackTrace();
            }
        }
    }

}
