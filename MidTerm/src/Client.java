/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ahmed Mohamed
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        //local IP and port number; can be changed if needed to
        String localIP = "127.0.0.1";
        int PORT = 1234;

        Scanner input = new Scanner(System.in);
        System.out.println("Connecting to Server...");

        //declaring the socket/scanner/printstream here so that it can be used outside the try catch block
        Socket socket;
        Scanner input2;
        PrintStream p;
        
        //attemps to connect to server
        try {
            socket = new Socket(localIP, PORT);
            input2 = new Scanner(socket.getInputStream());
            p = new PrintStream(socket.getOutputStream());
        } catch (Exception e) {
            //prints the error  when failed
            System.out.println("An error has occured connecting to server: (" + e + ")");
            return;
        }
        System.out.println("Connection Successful!");

        //loop for the menu
        loop:
        while (true) {
            //menu display
            System.out.println("Please enter the number of what you'd like to do: ");

            System.out.println("1 - Display Account Funds");
            System.out.println("2 - Deposit Funds");
            System.out.println("3 - Withdraw Funds");
            System.out.println("4 - Exit and Logoff");

            int choice;
            //continuesly tries to get the user to put a propper input
            while (true) {
                try {
                    input = new Scanner(System.in);
                    choice = input.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Error " + e + " : Please put in a number from 1 - 4");
                }
            }
            //swtich statment of all possible menu choices
            switch (choice) {
                case 1: {
                    //sends into the server their choice and receives option 1 output
                    p.println(choice);
                    String temp = input2.nextLine();
                    System.out.println(temp);
                    break;
                }

                case 2: {
                    //sends into the server their choice and receives option 2 output
                    p.println(choice);
                    System.out.println("How much money would you like to deposit? ");
                    double inputMoney;

                    //continuesly tries to get the user to put a proper deposit ammount
                    while (true) {
                        try {
                            //Scanner is reset at times to stop malfunctions
                            input = new Scanner(System.in);
                            //takes in the user deposit ammount
                            inputMoney = input.nextDouble();

                            //makes sure the number isn't negative
                            if (inputMoney < 0) {
                                System.out.println("Sorry, no negative numbers! Please enter how much money you would like to deposit: ");
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Error " + e + " : Please put in the ammount of money you would like to deposit: ");
                        }
                    }
                    //sends to server the deposit ammont
                    p.println(inputMoney);
                    String temp = input2.nextLine();
                    //receives the server's responce
                    System.out.println(temp);
                    break;
                }

                case 3: {
                    //sends into the server their choice and receives option 3 output
                    p.println(choice);
                    System.out.println("How much money would you like to withdraw?");
                    //same concept as deposit choice, essentially
                    double outMoney;
                    while (true) {
                        try {
                            input = new Scanner(System.in);
                            outMoney = input.nextDouble();
                            if (outMoney < 0) {
                                System.out.println("Sorry, no negative numbers! Please enter how much money you would like to withdraw: ");
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Error " + e + " : Please put in the ammount of money you would like to withdraw: ");
                        }
                    }
                    p.println(outMoney);
                    String temp = input2.nextLine();
                    System.out.println(temp);
                    break;
                }

                case 4:
                    //sends into the server their choice and receives option 4 output, which simply ends the server
                    p.println(choice);
                    System.out.println("Thank you! Goodbye!");
                    //stops menu loop and ends Client
                    break loop;

                //respsonce for when the choice number isnt an option given
                default:
                    System.out.println("Please enter a number from 1 - 4");
                    break;
            }
        }
    }
}
