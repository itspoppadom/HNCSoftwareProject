import java.util.*;
import java.io.*;
public class IMSMain {

    public static boolean adSet() {
        //This function is created just to check if admin password has been set
        boolean adPassSet = false ;
        while (adPassSet = false) {
            System.out.println("Your program is currently unsecure! Meaning it is susceptible to a cyber attack!");

        }
        return adPassSet;

    }

    public static void guest() {
        System.out.println("Welcome to the VStoq Inventory Management System, or IMS for short.\n You can use this program for browsing products, prices, amount in stock, and saving products to basket. \n    ");

    }
    public static void admin(){
    System.out.println("Test test Admin test");
    }
    public static void introMsg(){
        System.out.println("Welcome to the VStoq Inventory Management System, or IMS for short.\n Press G to enter the Guest mode, or A to enter the Administrator mode.");


    }
    public static void main(String[] args) {
        Scanner homeInput = new Scanner(System.in);
       boolean OnCheck = false ;
       while (OnCheck != true) {
           introMsg();
           String userChoice01 = homeInput.next();
           try {
               if (userChoice01.toLowerCase().equals("g")) {
                   guest();
                   OnCheck = true ;
               } else if (userChoice01.toLowerCase().equals("a")){
                   admin();
                   OnCheck = true ;
               } else {
                   OnCheck = false;
               }
           } catch (Exception e) {
               System.out.println("I'm sorry but your input is invalid" + e.toString());
               introMsg();
           } finally {
               if (!userChoice01.toLowerCase().equals("G") && !userChoice01.toLowerCase().equals("A")){
                   System.out.println("I'm sorry but your input is invalid, ");
               }
           }

        }
    }
}
