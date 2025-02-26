package com.mycompany.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMenu {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor iniciado en el puerto 12345...");
            
            Socket clientSocket = serverSocket.accept();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            while(true){
                out.println("Bienvenido! Seleccione un servicio:\n1. Calculadora\n2. Contador de letras\n3. Mostrar mi IP\n4. Salir del Servidor\nIngrese el número de la opción\n");
                int option = Integer.parseInt(in.readLine());
                System.out.println("Opción: " + option);

                switch (option) {
                    case 1:
                        out.println("Ingrese dos números y la operación (+, -, *, /), separados por espacios:\n");
                        String[] input = in.readLine().split(" ");
                        double num1 = Double.parseDouble(input[0]);
                        double num2 = Double.parseDouble(input[1]);
                        char operation = input[2].charAt(0);
                        double result = 0;
                        switch (operation) {
                            case '+': result = num1 + num2; break;
                            case '-': result = num1 - num2; break;
                            case '*': result = num1 * num2; break;
                            case '/': result = num2 != 0 ? num1 / num2 : Double.NaN; break;
                            default: out.println("Operación no válida"); return;
                        }
                        out.println("Resultado: " + result);
                        break;
                    case 2:
                        out.println("Ingrese un texto para contar las letras: \n");
                        String text = in.readLine();
                        out.println("El texto tiene " + text.length() + " caracteres.");
                        break;
                    case 3:
                        out.println("Su IP es: " + clientSocket.getInetAddress().getHostAddress() + "");
                        break;
                    case 4:
                        out.println("Vuelva Pronto!!!");
                        break;
                    default:
                        out.println("Opción no válida");
                }
                if(option == 4){break;}
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
