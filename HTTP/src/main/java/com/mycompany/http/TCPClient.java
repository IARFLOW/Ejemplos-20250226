package com.mycompany.http;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        String serverIP = "10.42.69.100"; // Cambia por la IP del servidor
        int serverPort = 8080; // Puerto donde escucha el servidor

        try (Socket socket = new Socket(serverIP, serverPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println("GET / HTTP/1.1");
            out.println("Host: " + serverIP);
            out.println("Connection: close");
            out.println(); // Línea vacía indica el final del encabezado HTTP
            out.flush();

            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
            }

        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }
}
