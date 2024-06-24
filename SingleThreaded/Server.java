
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    public void run() throws IOException, UnknownHostException {
        int port = 8000;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);

        while (true) {
            System.out.println("Server is listneing on port : " + port);
            Socket acceptedConnection = socket.accept();
            System.out.println("Connection accepted from client "+ acceptedConnection.getRemoteSocketAddress()); // getting client address
            PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
            toClient.println("Hello from server");

            toClient.close();
            acceptedConnection.close();
            fromClient.close();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();

        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}