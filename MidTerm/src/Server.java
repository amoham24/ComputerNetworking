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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {

        //port number, ServerSocket, Socket, and Scanner are declared and instanciated
        //sets up its server on the following port
        int PORT = 1234;
        ServerSocket servSocket = new ServerSocket(PORT);
        Socket socket = servSocket.accept();
        Scanner input = new Scanner(socket.getInputStream());

        //initaial bank account ammount
        double money = 0;

        //loop for the coninuous menu
        loop:
        while (true) {
            //recieves the Client's choice in the menu
            int choice = input.nextInt();
            PrintStream p = new PrintStream(socket.getOutputStream());
            switch (choice) {
                case 1:
                    //simply displays balance
                    p.println("Your balance is $" + money + ".");
                    break;
                case 2:
                    //adds the amount sent in by the Client to the total bank account
                    double inputMoney = input.nextDouble();
                    money += inputMoney;
                    //sends back the following message
                    p.println("You've successfully deposited: $" + inputMoney);
                    break;
                case 3:
                    //same concept as deposit choice but has a check for when the user is withdrawing more than possible
                    double outMoney = input.nextDouble();
                    //checks if ammount sent in to withdraw is more than total back account
                    if (outMoney > money) {
                        p.println("Sorry! You dont have that much.");
                        break;
                    } else {
                        //withdraws money from account and prints message
                        money -= outMoney;
                        p.println("You've successfully withdrew: $" + outMoney);
                        break;
                    }
                case 4:
                    //ends the loop and Server when this choice is recieved
                    break loop;

            }
        }

    }
}
