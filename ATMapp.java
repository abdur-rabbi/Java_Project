/* Project: ATM Management System */

import java.util.*;

public class ATMapp {
    String name, accNum, password, accNumInput, passwordInput; 
    int balance = 1000, depositAmount, withdrawAmount;

    Scanner input = new Scanner(System.in);

    void input() {
        System.out.print("Enter your name: ");
        name = input.nextLine();
        System.out.print("Enter your account number: ");
        accNum = input.nextLine();
        System.out.print("Enter your password: ");
        password = input.nextLine();

        int accLen = String.valueOf(accNum).length();
        int passLen = String.valueOf(password).length();

        if ( accLen < 6 || accLen > 19 || passLen < 6 || passLen > 19) {
            throw new IllegalArgumentException(
                    "Account number and password must be positive numbers and 6 to 19 digits long.");
        }
    }

    void display() {
        System.out.println("Name\t\t\t:" + name +
                "\nAccount Number\t\t:" + accNum +
                "\nPassword\t\t:" + password);
        System.out.println("..................................................");
    }

    void login() {
        System.out.print("Enter your account number: ");
        accNumInput = input.nextLine();
        System.out.print("Enter your password: ");
        passwordInput = input.nextLine();

    }

    void ATM() {
        while (true) {
            System.out.println("..................................................");
            System.out.println("Welcome " + name + " to the ATM service!");
            System.out.println("Please select an option:\n1. Check Balance\n" +
                    "2. Deposit\n" +
                    "3. Withdraw\n" +
                    "4. Change PIN\n" +
                    "5. Exit");
            System.out.print("Please enter your choice: ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Your current balance is $: " + balance);
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    depositAmount = input.nextInt();
                    balance += depositAmount;
                    System.out.println("Deposited successfully! Your new balance is $: " + balance);
                    break;
                case 3:
                    System.out.print("Enter withdraw amount: ");
                    withdrawAmount = input.nextInt();
                    if (withdrawAmount <= balance) {
                        balance -= withdrawAmount;
                        System.out.println("Withdrawn successfully! Your new balance is $: " + balance);
                    } else {
                        System.out.println("Insufficient balance!");
                    }
                    break;
                case 4:
                    input.nextLine();
                    System.out.println("Change your PIN Enter new PIN: ");
                    String newPin = input.nextLine();
                    password = newPin;
                    System.out.println(
                            "PIN changed successfully! Your new PIN is: " + password + " Now get exit and LOGIN AGAIN");
                    break;
                case 5:
                    System.out.println("Exiting the ATM service. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ATMapp obj = new ATMapp();
        System.out.println(".........Create a new account.........");
        try {
            obj.input();
            System.out.println(".....Account created successfully!.........");
            obj.display();
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating account: " + e);
            return;
        }
        System.out.println("Now please Login to your account. You can try only three times.");
        for (int i = 0; i < 3; i++) {
            obj.login();
            if (obj.accNumInput.equals(obj.accNum) && obj.passwordInput.equals(obj.password)) {
                System.out.println(".....Login successful!......");
                obj.display();
                obj.ATM();
                break;
            } else if (!obj.accNumInput.equals(obj.accNum)) {
                System.out.println("Account number does not match! Please try again.");
            } else if (!obj.passwordInput.equals(obj.password)) {
                System.out.println("Password does not match! Please try again.");
            } else {
                System.out.println("Login failed! Please try again.");
                return;
            }
            System.out.println("Attempt " + (i + 1) + " failed. Please try again.");
        }
    }
}
