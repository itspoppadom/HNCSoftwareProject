import java.util.*;
import java.io.*;
public class IMSMain {

    public static void adSet() {
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

            } catch (IOException e) {
                System.out.printf("Error occurred writing to file: %s%n", e.toString());
            }
        }
    }
    public static void KeyCheck(ArrayList<Items> thisClass, boolean pCheck){
        // This function runs to see whether a password has been created.
        File file = new File("ssk.txt");

        if (file.length() == 0) {
            System.out.println("You haven't set a secret passphrase yet!");
            adSet();
        } else {
            introMsg(thisClass, pCheck);
        }
    }
    public static void guest(ArrayList thisClass, boolean pCheck) {
        Scanner gInput = new Scanner(System.in);

        System.out.println("Welcome to the VStoq Inventory Management System, or IMS for short.\n You can use this program for browsing products, prices, amount in stock, and saving products to basket. \n Please enter: I - to access all inventory, C - to display the categories of items, B - Browsers basket");

        boolean gCheck = false;
        while (!gCheck){
            String guestIn1 = gInput.next();
            try {
                if (guestIn1.equalsIgnoreCase("i")) {
                    displayAllitemsG(thisClass, pCheck);
                    gCheck = true ;

                } else if (guestIn1.equalsIgnoreCase("c")){
                    stockCat(thisClass,pCheck);
                    gCheck = true ;
                    userReturnPrompt(thisClass, pCheck);
                } else if (guestIn1.equalsIgnoreCase("b")){
                    gBasket(thisClass, pCheck);
                    gCheck = true ;
                    userReturnPrompt(thisClass, pCheck);
                } else {
                    gCheck = false;
                }
            } catch (Exception e) {
                System.out.println("I'm sorry but your input is invalid" + e);
                guest(thisClass,pCheck);
            } }
    }
    public static void displayAllitemsG(ArrayList<Items> thisClass, boolean pCheck){
        // note the slight change to this procedure
        for (int i = 0; i < thisClass.size();i++) {
            Items thisItems = (Items)thisClass.get(i);
            System.out.format("(%d) ,%s , %s, %s, %d, %.2f \n",i+1, thisItems.itemName, thisItems.itemCat, thisItems.itemColour, thisItems.itemQty, thisItems.itemPrice);
        }
        Scanner GuestPrompt = new Scanner(System.in);
        System.out.println("You can either add the items to a basket, or filter them down by Category.\n  To access basket feature's please enter (B). \nTo filter by category please enter (C)");
        String GuestPromptResponse = GuestPrompt.next();
        try {
            if (GuestPromptResponse.equalsIgnoreCase("b")){
                gBasket(thisClass, pCheck);
            } else if (GuestPromptResponse.equalsIgnoreCase("c")){
                stockCat(thisClass, pCheck);

            } else {
                System.out.println("The system will now shutdown...");
                System.exit(1);
            }

        } catch (Exception e) {
            System.out.println("I'm sorry but something happened while opening the file: " + e.toString());
        }
    }

    public static void stockCat(ArrayList<Items> thisClass, boolean pCheck){
        Scanner catSelect = new Scanner(System.in);
        System.out.println("In order to search by category please enter which category you'd like to view. (1) Computers (2) Laptops (3) Monitors (4) Accessories (5)Peripherals  ");
        int userSelection = catSelect.nextInt();
        if (userSelection == 1){
            for (int i = 0; i < thisClass.size();i++) {
                Items thisItems = (Items)thisClass.get(i);
                if (thisItems.itemCat.equalsIgnoreCase("Computers")) {
                    System.out.format("(%d) ,%s , %s, %s, %d, %.2f \n", i + 1, thisItems.itemName, thisItems.itemCat, thisItems.itemColour, thisItems.itemQty, thisItems.itemPrice);

                }
                }
        } else if (userSelection ==2) {
            for (int i = 0; i < thisClass.size();i++) {
                Items thisItems = (Items)thisClass.get(i);
                if (thisItems.itemCat.equalsIgnoreCase("Laptops")) {
                    System.out.format("(%d) ,%s , %s, %s, %d, %.2f \n", i + 1, thisItems.itemName, thisItems.itemCat, thisItems.itemColour, thisItems.itemQty, thisItems.itemPrice);
                }
            }
        } else if (userSelection ==3 ) {
            for (int i = 0; i < thisClass.size();i++) {
                Items thisItems = (Items)thisClass.get(i);
                if (thisItems.itemCat.equalsIgnoreCase("Monitors")) {
                    System.out.format("(%d) ,%s , %s, %s, %d, %.2f \n", i + 1, thisItems.itemName, thisItems.itemCat, thisItems.itemColour, thisItems.itemQty, thisItems.itemPrice);
                }
            }
        } else if (userSelection == 4 ) {
            for (int i = 0; i < thisClass.size();i++) {
                Items thisItems = (Items)thisClass.get(i);
                if (thisItems.itemCat.equalsIgnoreCase("Accessories")) {
                    System.out.format("(%d) ,%s , %s, %s, %d, %.2f \n", i + 1, thisItems.itemName, thisItems.itemCat, thisItems.itemColour, thisItems.itemQty, thisItems.itemPrice);
                }
            }
        } else if (userSelection ==5 ) {
            for (int i = 0; i < thisClass.size();i++) {
                Items thisItems = (Items)thisClass.get(i);
                if (thisItems.itemCat.equalsIgnoreCase("Peripherals")) {
                    System.out.format("(%d) ,%s , %s, %s, %d, %.2f \n", i + 1, thisItems.itemName, thisItems.itemCat, thisItems.itemColour, thisItems.itemQty, thisItems.itemPrice);
                }
            }
        }else {
            System.out.println("Input not in the scope.");
            System.exit(1);
        }
    }
    public static void gBasket(ArrayList<Items> thisClass, boolean pCheck){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the index of the item you would like to add to basket. ( 1 -30 )");
        int basketSelect = input.nextInt();

// A loop to make sure that
        if (basketSelect >= 1 && basketSelect <= thisClass.size()) {
            try (FileWriter out = new FileWriter("Basket.txt")) {
                for (int i = 0; i < thisClass.size(); i++) {
                    Items thisItems = thisClass.get(i);
                    if (basketSelect == i + 1) {
                        String formattedString = String.format("|Item: %s |Category: %s |Colour %s |Quantity %s| Price %s ",
                                thisItems.itemName, thisItems.itemCat, thisItems.itemColour, thisItems.itemQty, thisItems.itemPrice);
                        out.write(Integer.toString(i) + formattedString);
                        System.out.println("Item added to the basket successfully!.");
                        //Item quantity to go down after items have been added to the basket.
                        thisItems.itemQty -= 1;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error occurred writing to file: " + e.toString());
            }
        } else {
            System.out.println("Invalid index entered. Please enter an index between 1 and " + thisClass.size());
        }

    }
    public static boolean adminPcheck(ArrayList<Items> thisClass, boolean pCheck) {
        System.out.println("In order to proceed please enter the secret passphrase. ");
        Scanner adInput = new Scanner(System.in);
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
        if (!pCheck) {
            admin(thisClass, pCheck);}


        return pCheck;
    }


    private static ArrayList<Items> loadFile(String filename) {
        //Setup an Array to hold the Inventory data from the Items.txt file
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
                newitems.itemPrice = Double.parseDouble(allItems.get(4));
                thisClass.add(newitems);
                line = in.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error occurred reading file: " + e.toString());
        }
        return thisClass;
    }


    public static ArrayList<Items> admin(ArrayList<Items> thisClass, boolean pCheck){
        Scanner adInput = new Scanner(System.in);
        System.out.println("Welcome back Operator. You have successfully entered the privileged settings. ");
        boolean adSelect = false;
        while (!adSelect){

            System.out.println("Would you like to see the stock levels? \n Press Y to continue, or N to return to menu");
            String adChoice = adInput.next();
            try {
                if(adChoice.equalsIgnoreCase("y")){
                    displayAllitems(thisClass, pCheck);
                    adSelect = true;
                }
                else if (adChoice.equalsIgnoreCase("n")) {
                    userReturnPrompt(thisClass,pCheck);
                }

            } catch (Exception e) {
                System.out.println("Invalid input" + e.toString());
                System.exit(1);
            }
        }
        return thisClass;
    }
    public static void displayAllitems(ArrayList<Items> thisClass, boolean pCheck){
        // note the slight change to this procedure
        Scanner adInput =new Scanner(System.in);
        for (int i = 0; i < thisClass.size();i++) {
            Items thisItems = (Items)thisClass.get(i);
            System.out.format("(%d) ,%s , %s, %s, %d, %.2f \n",i, thisItems.itemName, thisItems.itemCat, thisItems.itemColour, thisItems.itemQty, thisItems.itemPrice);
        }
        double TotalValue = 0.0;
        for (Items price : thisClass) {
            double itemTotal = price.itemQty * price.itemPrice;
            TotalValue += itemTotal;
            System.out.format("Item: %s, | Quantity: %d | Price: %.2f | Item total: %.2f | Running Total: %.2f \n",price.itemName , price.itemQty , price.itemPrice , itemTotal , TotalValue);

        }
            System.out.format("The total value of the stock is: £%.2f \n", TotalValue );
            System.out.println("Would you like to update any prices? (Y) Yes (N) No");
            String modifyPrices = adInput.next();
            if (modifyPrices.equalsIgnoreCase("y")){
                priceModificationPanel(thisClass, pCheck);
            } else if (modifyPrices.equalsIgnoreCase("n")) {
                userReturnPrompt(thisClass,pCheck);
            }
    }

    public static void priceModificationPanel(ArrayList<Items> thisClass,boolean pCheck) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the index of the item you would like to edit the price of. ( 1 -30 )");
        int modPriceIndex = input.nextInt();

// A loop to make sure that

        if (modPriceIndex >= 1 && modPriceIndex <= thisClass.size()) {

                for (int i = 0; i < thisClass.size(); i++) {
                    Items thisItems = thisClass.get(i);
                    if (modPriceIndex == i + 1) {
                        System.out.format("(%d)|Current item: %s Current price: %.2f\n", i,thisItems.itemName,thisItems.itemPrice );
                        System.out.println("Please enter the new product price. (£0.00");
                        double newPrice = input.nextDouble();
                        thisItems.itemPrice = newPrice;
                        System.out.println("Price changed successfully\n");
                        System.out.format("(%d)|Current item: %s Current price: %.2f\n", i,thisItems.itemName,thisItems.itemPrice );
                    }
                }

        } else {
            System.out.println("Invalid index entered. Please enter an index between 1 and " + thisClass.size());
        }
    }
    public static void introMsg(ArrayList<Items> thisClass, boolean pCheck){
        System.out.println("Welcome to the VStoq Inventory Management System, or IMS for short.\n Press G to enter the Guest mode, or A to enter the Administrator mode.");
        Scanner homeInput = new Scanner(System.in);
        boolean OnCheck = false ;
        while (!OnCheck) {


            String userChoice01 = homeInput.next();
            try {
                if (userChoice01.equalsIgnoreCase("g")) {
                    guest(thisClass, pCheck);
                    OnCheck = true ;
                } else if (userChoice01.equalsIgnoreCase("a")){
                    adminPcheck(thisClass, pCheck);
                    OnCheck = true ;
                } else {
                    introMsg(thisClass, pCheck);
                }
            } catch (Exception e) {
                System.out.println("I'm sorry but your input is invalid" + e.toString());
                introMsg(thisClass, pCheck);
            }


        }

    }
    public static void userReturnPrompt(ArrayList<Items> thisClass, boolean pCheck){
        Scanner promptReturn = new Scanner(System.in);
        System.out.println("Would you like to return to the main menu? Please enter Y(YES) or N(NO) ");
        String promptRUser = promptReturn.next();
        try {
            if(promptRUser.equalsIgnoreCase("y")){
                introMsg(thisClass,pCheck);
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
        Scanner adInput = new Scanner(System.in);
        ArrayList<Items> thisClass = new ArrayList<Items>();
        boolean pCheck = false;
        boolean stockCheck = false;
        while (!stockCheck){

            System.out.println("The system currently has no data in side of it. Please make sure the file items.txt is accessible \n Press Y to continue, or N to return to menu");
            String adChoice = adInput.next();
            try {
                if(adChoice.equalsIgnoreCase("y")){
                    thisClass = loadFile("Items.txt");
                    // Loop for all objects in text file

                    stockCheck = true;
                    userReturnPrompt(thisClass,pCheck);

                }
                else if (adChoice.equalsIgnoreCase("n")) {
                    stockCheck =true;
                    userReturnPrompt(thisClass, pCheck);

                }

            } catch (Exception e) {
                System.out.println("Invalid input" + e.toString());
                System.exit(1);
            }
        }


        KeyCheck(thisClass,pCheck);
        userReturnPrompt(thisClass, pCheck);
    }
}
