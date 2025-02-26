
package com.mycompany.sockets;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        
        String serverAddress = "10.42.69.100"; //esta es la ip del servidor.
        int port = 12345; //El puerto que ha dejado abierto el servidor.
        
        try (Socket socket = new Socket(serverAddress, port)) { //aquí ya se conecta al servidor.
            System.out.println("Conectado al servidor en " + serverAddress + ":" + port);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //los buffers ifgual que el servidor.
            
            String message = "Hola, servidor!";
            out.println(message);
            
            System.out.println("Mensaje enviado al servidor: " + message);
            
            String response = in.readLine();
            System.out.println("Respuesta del servidor: " + response);
            
            // el orden y la cantidad de mensajes tiene que ser coherente con la parte del servidor.
        
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
