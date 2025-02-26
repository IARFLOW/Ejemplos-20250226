package com.mycompany.servermultihilo;

import java.io.*;
import java.net.*;

public class ServerMultihilo {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);

            while (true) {  
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado desde " + clientSocket.getInetAddress());
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            out.println("Bienvenido al servidor multihilo!");
            out.println("Ingrese 'exit' para desconectarse.");

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if ("exit".equalsIgnoreCase(inputLine)) {
                    out.println("Desconectando...");
                    break;
                }
                out.println("Mensaje recibido: " + inputLine);
            }

            System.out.println("Cliente desconectado: " + clientSocket.getInetAddress());
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error con el cliente: " + e.getMessage());
        }
    }
}
