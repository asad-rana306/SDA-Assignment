package PeerToPeer;

import java.io.*;
import java.net.*;

public class PeerB {
    public static void main(String[] args) throws IOException {
        final int myPort = 9001;
        final String peerIP = "localhost";
        final int peerPort = 9000;
        final String myName = "PeerB";

        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(myPort)) {
                System.out.println("[" + myName + "] Listening on port " + myPort);
                while (true) {
                    Socket incoming = serverSocket.accept();
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(incoming.getInputStream()));
                    String received = in.readLine();
                    System.out.println("\n[" + myName + "] Received: " + received);
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
                System.out.print("[" + myName + "] Enter message: ");
                line = console.readLine();
                if (line == null || line.equalsIgnoreCase("exit")) {
                    System.out.println("[" + myName + "] Exiting chat.");
                    break;
                }

                try (Socket socket = new Socket(peerIP, peerPort);
                     PrintWriter out = new PrintWriter(
                             socket.getOutputStream(), true)) {
                    out.println(myName + ": " + line);
                } catch (IOException sendErr) {
                    System.out.println("[" + myName + "] Failed to send: " + sendErr.getMessage());
                }
            }
        }
    }
}
