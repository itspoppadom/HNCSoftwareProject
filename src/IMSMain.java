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
    public static void KeyCheck(ArrayList<Items> thisClass){
        // This function runs to see whether a password has been created.
        File file = new File("ssk.txt");

        if (file.length() == 0) {
            System.out.println("You haven't set a secret passphrase yet!");
            adSet();
        } else {
            introMsg(thisClass);
        }
    }
    public static void guest(ArrayList thisClass) {
        Scanner gInput = new Scanner(System.in);
        System.out.println("Welcome to the VStoq Inventory Management System, or IMS for short.\n You can use this program for browsing products, prices, amount in stock, and saving products to basket. \n Please enter: I - to access all inventory, C - to display the categories of items, B - Browsers basket");
        boolean gCheck = false;
        while (!gCheck){
            String guestIn1 = gInput.next();
            try {
                if (guestIn1.equalsIgnoreCase("i")) {
                    displayAllitems(thisClass);
                    gCheck = true ;

                } else if (guestIn1.equalsIgnoreCase("c")){
                    stockCat();
                    gCheck = true ;
                } else if (guestIn1.equalsIgnoreCase("b")){
                    gBasket();
                    gCheck = true ;
                } else {
                    gCheck = false;
                }
            } catch (Exception e) {
                System.out.println("I'm sorry but your input is invalid" + e.toString());
                guest(thisClass);
            } }
    }


    public static void stockCat(){
        System.out.println("Test Category");
    }
    public static void gBasket(){
        System.out.println("Test Basket");
    }
    public static boolean adminPcheck(ArrayList<Items> thisClass) {
        System.out.println("In order to proceed please enter the secret passphrase. ");
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
                    System.exit(1);
                }

            } catch (IOException e) {
                System.out.println("I'm sorry but something happened while opening the file: " + e.toString());
            }
        }
        if (pCheck){
            admin(thisClass);
        }

        return pCheck;
    }


    private static ArrayList<Items> loadFile(String filename) {
        List<String> allItems;
        ArrayList<Items> thisClass = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String line = in.readLine();
            while (line != null) {
                allItems = Arrays.asList(line.split(","));
                Items newitems = new Items();
                newitems.itemName = allItems.get(0);
                newitems.itemCat = allItems.get(1);
                newitems.itemColour = allItems.get(2);
                newitems.itemQty = Integer.parseInt(allItems.get(3));
                newitems.itemPrice = Float.parseFloat(allItems.get(4));
                thisClass.add(newitems);
                line = in.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error occurred reading file: " + e.toString());
        }
        return thisClass;
    }


    public static ArrayList<Items> admin(ArrayList<Items> thisClass){
        Scanner adInput = new Scanner(System.in);
        System.out.println("Welcome back Operator. You have successfully entered the privileged settings. ");
        boolean stockCheck = false;
        while (!stockCheck){

            System.out.println("Your program currently has no data in side of it. Please make sure the file items.txt is accessible \n Press Y to continue, or N to return to menu");
            String adChoice = adInput.next();
            try {
                if(adChoice.equalsIgnoreCase("y")){
                    thisClass = loadFile("Items.txt");
                    // Loop for all objects in text file
                    displayAllitems(thisClass);
                    stockCheck = true;
                    userReturnPrompt(thisClass);

                }
                else if (adChoice.equalsIgnoreCase("n")) {
                    stockCheck =true;
                    userReturnPrompt(thisClass);

                }

            } catch (Exception e) {
                System.out.println("Invalid input" + e.toString());
                System.exit(1);
            }
        }
        return thisClass;
    }
    public static void displayAllitems(ArrayList<Items> thisClass){
        // note the slight change to this procedure
        Items thisItems = (Items)thisClass.get(0);
        System.out.format("%s , %s, %s, %d, %2f",thisItems.itemName,thisItems.itemCat, thisItems.itemColour, thisItems.itemQty, thisItems.itemPrice);
    }
    public static void introMsg(ArrayList<Items> thisClass){
        System.out.println("Welcome to the VStoq Inventory Management System, or IMS for short.\n Press G to enter the Guest mode, or A to enter the Administrator mode.");
        Scanner homeInput = new Scanner(System.in);
        boolean OnCheck = false ;
        while (!OnCheck) {


            String userChoice01 = homeInput.next();
            try {
                if (userChoice01.equalsIgnoreCase("g")) {
                    guest(thisClass);
                    OnCheck = true ;
                } else if (userChoice01.equalsIgnoreCase("a")){
                    adminPcheck(thisClass);
                    OnCheck = true ;
                } else {
                    OnCheck = false;
                }
            } catch (Exception e) {
                System.out.println("I'm sorry but your input is invalid" + e.toString());
                introMsg(thisClass);
            }


        }

    }
    public static void userReturnPrompt(ArrayList<Items> thisClass){
        Scanner promptReturn = new Scanner(System.in);
        System.out.println("Would you like to return to the main menu? Please enter Y(YES) or N(NO) ");
        String promptRUser = promptReturn.next();
        try {
            if(promptRUser.equalsIgnoreCase("y")){
                introMsg(thisClass);
            }
            else if (promptRUser.equalsIgnoreCase("n")) {
                System.exit(1);
            }

        } catch (Exception e) {
            System.out.println("Invalid input" + e.toString());
            System.exit(1);
        }
    }
    public static void main(String[] args) {
        ArrayList<Items> thisClass = new ArrayList<Items>();
        KeyCheck(thisClass);
        userReturnPrompt(thisClass);
    }
}
