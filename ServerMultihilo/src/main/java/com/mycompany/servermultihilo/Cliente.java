
package com.mycompany.servermultihilo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor en " + SERVER_ADDRESS + ":" + PORT);

            String serverResponse;
            while ((serverResponse = in.readLine()) != null) {
                System.out.println("Servidor: " + serverResponse);
                if (serverResponse.contains("Ingrese 'exit'")) break;
            }

            String userMessage;
            while (true) {
                System.out.print("Ingrese mensaje: ");
                userMessage = userInput.readLine();
                out.println(userMessage);

                if ("exit".equalsIgnoreCase(userMessage)) {
                    System.out.println("Desconectado del servidor.");
                    break;
                }

                serverResponse = in.readLine();
                System.out.println("Servidor: " + serverResponse);
            }

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
