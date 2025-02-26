package com.mycompany.menu;
import java.io.*;
import java.net.*;

public class ClientMenu {
    public static void main(String[] args) {
        
        try (Socket socket = new Socket("localhost", 12345);  
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            
            String a = " ";
            while(true){
                while(true){
                    a = in.readLine();
                    if("".equals(a)||"Vuelva Pronto!!!".equals(a)){break;}
                    System.out.println(a);
                }
                if("Vuelva Pronto!!!".equals(a)){break;}
                out.println(userInput.readLine());
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
