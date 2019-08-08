package VerySimpleChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleChatServer {
    public static void main(String[] args) {
        SimpleChatServer server = new SimpleChatServer();
        server.goServ();
    }

    public void goServ(){
        try {
            ServerSocket serverSocket = new ServerSocket(4242);

            while(true){
                Socket sock = serverSocket.accept();
                InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                System.out.println(reader.readLine());

            }
        } catch (IOException eo){
            eo.printStackTrace();
        }

    }

}
