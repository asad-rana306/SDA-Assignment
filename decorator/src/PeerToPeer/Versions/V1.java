package PeerToPeer.Versions;

import java.io.*;
import java.net.*;

public class V1 {
    private static final int LISTEN_PORT = 9000;
    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(LISTEN_PORT)) {
                System.out.println("[V1] Listening on port " + LISTEN_PORT);
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                String message = in.readLine();
                System.out.println("[V1] Received: " + message);
                clientSocket.close();
                System.out.println("[V1] Server side exiting after one message.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

         try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) { }

        try (Socket socket = new Socket("localhost", LISTEN_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            String hardCoded = "Hello from V1";
            out.println(hardCoded);
            System.out.println("[V1] Sent: " + hardCoded);
        } catch (IOException e) {
            System.out.println("[V1] Failed to send: " + e.getMessage());
        }
        System.out.println("[V1] Main thread exiting.");
    }
}