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
            boolean controllo = false;
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
                    String turni = input.nextLine();
                    System.out.println("Tentativi: " + turni);
                    fine = true;
                    do{
                        controllo = false;
                        System.out.println("Vuoi conitnuare?");
                        System.out.println("1 = sì - 0 = no");
                        String scelta = scanner.nextLine();
                        out.writeBytes(scelta + "\n");
                        String continua = input.nextLine();
                        switch (continua) {
                            case "1":
                                fine = false;
                                System.out.println("Si ricomincia!!!");
                                break;
                            case "0":
                                System.out.println("Partita conclusa");
                                break;
                            default:
                                System.out.println("!!!Scelta errata!!!");
                                controllo = true;
                                break;
                        }
                    }while(controllo);
                    break;
                default:
                    System.out.println("!!!Il numero da te inserito, risulta erroneo!!!");
                    break;
            }
        } while (!fine);
        
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