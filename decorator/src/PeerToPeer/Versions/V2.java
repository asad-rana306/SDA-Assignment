package PeerToPeer.Versions;

import java.io.*;
import java.net.*;

public class V2 {
    private static final int LISTEN_PORT = 9000;
    private static final String TARGET_HOST = "localhost";
    private static final int TARGET_PORT = 9000;

    public static void main(String[] args) throws IOException {
        // 1) Start server thread on port 9000
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(LISTEN_PORT)) {
                System.out.println("[V2] Listening on port " + LISTEN_PORT);
                while (true) {
                    Socket incoming = serverSocket.accept();
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(incoming.getInputStream()));
                    String received = in.readLine();
                    System.out.println("\n[V2] Received: " + received);
                    incoming.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        try (BufferedReader console = new BufferedReader(
                new InputStreamReader(System.in))) {
            String line;
            while (true) {
                System.out.print("[V2] Enter message (or 'exit'): ");
                line = console.readLine();
                if (line == null || line.equalsIgnoreCase("exit")) {
                    System.out.println("[V2] Exiting send loop.");
                    break;
                }

                try (Socket socket = new Socket(TARGET_HOST, TARGET_PORT);
                     PrintWriter out = new PrintWriter(
                             socket.getOutputStream(), true)) {
                    out.println("User: " + line);
                } catch (IOException sendErr) {
                    System.out.println("[V2] Failed to send: " + sendErr.getMessage());
                }
            }
        }
    }
}