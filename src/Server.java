import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by Arvid Bodin och Mattias Grehnik on 2015-12-06.
 *
 */
public class Server {

    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private ServerSocket serverSocket;

    private  static final int port = 8008;

    public Server() {
        while (true) {
            try {
                serverSocket = new ServerSocket(port);

                socket = serverSocket.accept();

                System.out.println("Accepted" + serverSocket.getInetAddress());

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    socket.setSoTimeout(10000);
                    String data = in.readLine();


                    String[] split = data.split(";");

                    for (int i = 0; i < split.length; i++) {
                        System.out.println(split[i]);
                    }

                }
            } catch (SocketTimeoutException t) {
                System.out.println("Client timeout");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
