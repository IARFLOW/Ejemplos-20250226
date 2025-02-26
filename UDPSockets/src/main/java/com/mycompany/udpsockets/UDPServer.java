package com.mycompany.udpsockets;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer { // Aunque le voy a llamar UDPServer, los sockets son iguales en el server que el Client.
    public static void main(String[] args) throws SocketException {
        try{
            int port = 12345;
            DatagramSocket socket = new DatagramSocket(port);

            System.out.println("Servidor UDP escuchando en el puerto " + port);
            
            byte[] buffer = new byte[1024];
            
            while(true) {
                
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                String message = new String(request.getData(), 0, request.getLength());
                System.out.println("Mensaje recibido del cliente: " + message);
                
                String response = "Mensaje recibido: " + message;
                byte[] responseData = response.getBytes();
                
                DatagramPacket responsePacket = new DatagramPacket(
                        responseData,
                        responseData.length,
                        request.getAddress(), // con esto averiguo la IP del cliente.
                        request.getPort()     // y el puerto.
                );
                
                socket.send(responsePacket); // envío a través del socket
                
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
