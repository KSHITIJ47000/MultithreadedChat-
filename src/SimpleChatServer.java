import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleChatServer {
    private static Set<PrintWriter> clientWriters = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server started on port 1234...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket.getInetAddress());

                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                clientWriters.add(writer);

                new Thread(() -> handleClient(socket, writer)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket, PrintWriter writer) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received: " + message);
                broadcast(message);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected: " + socket.getInetAddress());
        } finally {
            clientWriters.remove(writer);
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }

    private static void broadcast(String message) {
        synchronized (clientWriters) {
            for (PrintWriter w : clientWriters) {
                w.println(message);
            }
        }
    }
}
