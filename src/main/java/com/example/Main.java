package com.example;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Client partito");
        Socket s = new Socket("localhost", 3000);
        System.out.println("Il client si è collegato");

        Scanner scanner = new Scanner(System.in);
        Scanner input = new Scanner(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        boolean fine = false;
        
        menu();
        do {
            System.out.println("\nInserisci un numero");
            String numero = scanner.nextLine();
            out.writeBytes(numero + "\n");
            String risposta = input.nextLine();
            switch (risposta) {
                case "<":
                    System.out.println("Numero troppo piccolo");
                    break;
                case ">":
                    System.out.println("Numero troppo grande");
                    break;
                case "=":
                    System.out.println("Numero indovinato");
                    fine = true;
                    break;
                default:
                    System.out.println("!!!Il numero da te inserito, risulta erroneo!!!");
                    break;
            }
        } while (!fine);
        String turni = input.nextLine();
        System.out.println(turni);
        
        out.close();
        input.close();
        scanner.close();
        s.close();
    }

    public static void menu(){
        System.out.println("\n- - - BENVENUTO - - -");
        System.out.println("Nel server è stato generato un numero, tovcca a te scoprirlo.");
        System.out.println("!!!Ricorda, il numero deve essere minore di 100!!!");
    }
}