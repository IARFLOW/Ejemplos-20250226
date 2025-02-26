package com.mycompany.sockets;

import java.io.*;
import java.net.*;

public class TCPServer { //Servidor tipo TCP
    public static void main(String[] args) {
        
        int port = 12345;
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Sevidor en espera en puerto: " + port);
            
            Socket clientSocket = serverSocket.accept();  //empieza a aceptar clientes con accept() // esta sentencia para el main hasta que se conecta un cliente.
            System.out.println("Cliente conectado desde " + clientSocket.getInetAddress().getHostAddress()); //esta es la ip del cliente que se acaba de conectar.
            
            // hay que crear los buffers de comunicación::
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            // esto conecta los buffers de entrada y salida cliente-servidor. Para que se puedan comunicar.
            
            String message = in.readLine();
            System.out.println("Mensaje recibido del cliente: " + message);
            //imprimimos por pantalla lo que nos manda el cliente
            
            //le respondemos al cliente:
            
            out.println("Mensaje recibido: " + message);
            
            in.close();
            out.close();
            clientSocket.close();
            // se cierran las conexiones.
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }
}
