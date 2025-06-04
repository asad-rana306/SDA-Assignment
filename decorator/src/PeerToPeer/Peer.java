package PeerToPeer;

import java.io.*;
import java.net.*;

public class Peer {
    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            System.out.println("Usage: java Peer <myPort> <peerIP> <peerPort> <myName>");
            return;
        }

        int myPort = Integer.parseInt(args[0]);
        String peerIP = args[1];
        int peerPort = Integer.parseInt(args[2]);
        String myName = args[3];

        // 1) Start server thread
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

        // 2) Main thread: loop reading from console & send to peerIP:peerPort
        try (BufferedReader console = new BufferedReader(
                new InputStreamReader(System.in))) {
            String line;
            while (true) {
                System.out.print("[" + myName + "] Enter message: ");
                line = console.readLine();
                if (line == null || line.equalsIgnoreCase("exit")) {
                    System.out.println("[" + myName + "] Exiting...");
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
