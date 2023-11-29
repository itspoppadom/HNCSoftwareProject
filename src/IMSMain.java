import java.util.*;
import java.io.*;

public class IMSMain {

    public static boolean adSet() {
        Scanner input = new Scanner(System.in);
        //This function is created just to set an admin password.
        boolean adPassSet = false ;
        while (!adPassSet) {
            try {
                FileWriter out = new FileWriter("ssk.txt");
                System.out.println("Your program is currently unsecure! Meaning it is susceptible to a cyber attack!");
                System.out.println("To protect your IMS from unwanted changes you need to set a password now. ");
                String adPriv = input.next();
                out.write(adPriv);
                out.close();
                adPassSet = true ;
                ;
            } catch (IOException e) {
            System.out.println("Error occured writing to file: " + e.toString());
            }
        }


        return adPassSet;
    }
    public static void KeyCheck(){
        // This function runs to see whether a password has been created.
        File file = new File("ssk.txt");

        if (file.length() == 0) {
            System.out.println("You haven't set a secret passphrase yet!");
            adSet();
        } else {
            introMsg();
        }
    }
    public static void guest() {
        System.out.println("Welcome to the VStoq Inventory Management System, or IMS for short.\n You can use this program for browsing products, prices, amount in stock, and saving products to basket. \n    ");

    }
    public static boolean adminPcheck() {
        Scanner adInput = new Scanner(System.in);
        boolean pCheck = false ;
        while (!pCheck) {
            String adP = adInput.next();
            try {
                BufferedReader in = new BufferedReader(new FileReader("ssk.txt"));
                String line = in.readLine();
                if (adP.equals(line)){
                    pCheck = true ;
                } else {
                    System.out.println("I'm sorry but you cannot access the admin settings. ");
                }

            } catch (IOException e) {
                System.out.println("I'm sorry but something happened while opening the file: " + e.toString());
            }
        }
        if (pCheck){
            System.out.println("Welcome back Operator. You have successfully entered the privileged settings. ");
        }
        return pCheck;
    }
    public static void admin(){
    System.out.println("In order to proceed please enter the secret passphrase. ");
    adminPcheck();

    }
    public static void introMsg(){
        System.out.println("Welcome to the VStoq Inventory Management System, or IMS for short.\n Press G to enter the Guest mode, or A to enter the Administrator mode.");
        Scanner homeInput = new Scanner(System.in);
        boolean OnCheck = false ;
        while (OnCheck != true) {


            String userChoice01 = homeInput.next();
            try {
                if (userChoice01.equalsIgnoreCase("g")) {
                    guest();
                    OnCheck = true ;
                } else if (userChoice01.equalsIgnoreCase("a")){
                    admin();
                    OnCheck = true ;
                } else {
                    OnCheck = false;
                }
            } catch (Exception e) {
                System.out.println("I'm sorry but your input is invalid" + e.toString());
                introMsg();
            }


        }

    }
    public static void main(String[] args) {
        KeyCheck();

    }
}
