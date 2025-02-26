
package com.mycompany.udpsockets;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
    public static void main(String[] args) throws SocketException {
        try{
            
            String serverIP = "10.42.69.100"; // Dirección IP del servidor
            int serverPort = 12345;       // Puerto del servidor
            
            DatagramSocket socket = new DatagramSocket();

            String message = "Hola desde el cliente!";
            byte[] buffer = message.getBytes();

            InetAddress serverAddress = InetAddress.getByName(serverIP); // secrea un objeto tipo Conexión IP (representa una dirección IP en objeto java)
            
            DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
            socket.send(requestPacket);
            
            //preparamos el Datagram para la respuesta:
            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            
            socket.receive(responsePacket);
            
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Respuesta del servidor: " + response);

            socket.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
