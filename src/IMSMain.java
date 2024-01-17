import java.util.*;
import java.io.*;
import java.util.logging.*;
public class IMSMain {
    private static final Logger logger = Logger.getLogger(IMSMain.class.getName());
    // set up a protected logger that cannot be hijacked and is protected across the lifetime
    private static void setupLogging() { //A function to set up logging in the program.
        try {
            FileHandler fileHandler = new FileHandler("application.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            // Set the logging level (FINEST, FINER, FINE, CONFIG, INFO, WARNING, SEVERE)
            logger.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void adSet() {
        Scanner input = new Scanner(System.in);
        logger.log(Level.WARNING, "Admin password is being configured. ");
        //This function is created in order to set an admin password, and store it in a file called ssk.txt.
        boolean adPassSet = false ;
        while (!adPassSet) {
            try {
                FileWriter out = new FileWriter("ssk.txt");
                System.out.println("Your program is currently unsecure! Meaning it is susceptible to being hacked!");
                System.out.println("To protect your IMS from unwanted changes you need to set a password now. ");
                // Ask user for an input of a password they would like to create.
                String adPriv = input.next();
                out.write(adPriv);
                out.close();
                logger.log(Level.WARNING, "Password has been created created successuflly. ");
                adPassSet = true ;

            } catch (IOException e) {
                //Error handling with logging for easier troubleshooting in the future
                System.out.printf("Error occurred writing to file: %s%n", e.toString());
                logger.log(Level.SEVERE, "Application encountered an error wrting to file: %s%n"+ e.getMessage()+e);
            }
        }
    }
    public static void KeyCheck(ArrayList<Items> thisClass, boolean pCheck){
        // This function runs to see whether a password has been created.
        File file = new File("ssk.txt");
        //A function to find out whether or not there is context within a file created purely to store the password.
        if (file.length() == 0) {
            System.out.println("System hasn't detected a password file within the source.");
            logger.log(Level.SEVERE, "*SOFTWARE UNSECURUE* *WARNING*  *PLEASE MAKE SURE YOU CREATE A MEMORABLE PASSWORD*");
            adSet();

        } else {
            introMsg(thisClass, pCheck);
            logger.log(Level.INFO, "*RUNNING CONFIG DETECTED, PROCEEDING TO VSTOQ IMS");
        }
    }
    public static void guest(ArrayList thisClass, boolean pCheck) {
        logger.log(Level.INFO, "Guest panel accessed successfully ");
        Scanner gInput = new Scanner(System.in);
        //A Simple menu for the guest user to navigate through their available features.
        System.out.println("Welcome to the VStoq Inventory Management System, or IMS for short.\n You can use this program for browsing products, prices, amount in stock, and saving products to basket. \n Please enter: I - to access all inventory, C - to display the categories of items, B - Browsers basket");

        boolean gCheck = false;
        while (!gCheck){
            String guestIn1 = gInput.next();

            try {
                if (guestIn1.equalsIgnoreCase("i")) {
                    logger.log(Level.INFO, "Guest Mode: Inventory accessed.");
                    displayAllitemsG(thisClass, pCheck);
                    gCheck = true ;

                } else if (guestIn1.equalsIgnoreCase("c")){
                    logger.log(Level.INFO, "Guest Mode: Search by Category view accessed .");
                    stockCat(thisClass,pCheck);
                    gCheck = true ;
                    userReturnPrompt(thisClass, pCheck);
                } else if (guestIn1.equalsIgnoreCase("b")){
                    logger.log(Level.INFO, "Guest Mode: User Basket accessed.");
                    gBasket(thisClass, pCheck);
                    gCheck = true ;
                    userReturnPrompt(thisClass, pCheck);
                } else {
                    gCheck = false;
                }
            } catch (Exception e) {
                System.out.println("I'm sorry but your input is invalid" + e);
                // Reload the function if there has been an error during input
                guest(thisClass,pCheck);
            } }
    }
    public static void displayAllitemsG(ArrayList<Items> thisClass, boolean pCheck){
        // A for loop to display all items within the Array
        for (int i = 0; i < thisClass.size();i++) {
            Items thisItems = (Items)thisClass.get(i);
            System.out.format("(%d) ,%s , %s, %s, %d, %.2f \n",i+1, thisItems.itemName, thisItems.itemCat, thisItems.itemColour, thisItems.itemQty, thisItems.itemPrice);
        }
        // Logging messages to keep track of the in's and out's of the program
        logger.log(Level.INFO, "Guest Mode: Inventory displayed.");
        Scanner GuestPrompt = new Scanner(System.in);
        //Menu to display the options available to the Guest users of the application while browsing the All items view.
        System.out.println("You can either add the items to a basket, or filter them down by Category.\n  To access basket feature's please enter (B). \nTo filter by category please enter (C)");
        String GuestPromptResponse = GuestPrompt.next();
        try {
            if (GuestPromptResponse.equalsIgnoreCase("b")){
                logger.log(Level.INFO, "Guest Mode: Basket accessed.");
                gBasket(thisClass, pCheck);
            } else if (GuestPromptResponse.equalsIgnoreCase("c")){
                logger.log(Level.INFO, "Guest Mode: Search by Category view accessed.");
                stockCat(thisClass, pCheck);

            } else {
                System.out.println("Invalid input. Please try again. ");
                logger.log(Level.INFO, "Guest Mode: User choice undefined retrying.");
                displayAllitemsG(thisClass, pCheck);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while loading the inventory." + e.getMessage());
            System.out.println("I'm sorry but the program encountered an error: " + e.toString());
        }
    }

    public static void stockCat(ArrayList<Items> thisClass, boolean pCheck){
        //A function which allows the users to filter all the items by the category of the item.
        logger.log(Level.INFO, "Guest Mode: Search by category view has now been accessed.");
        Scanner catSelect = new Scanner(System.in);
        System.out.println("In order to search by category please enter which category you'd like to view. (1) Computers (2) Laptops (3) Monitors (4) Accessories (5)Peripherals  ");
        int userSelection = catSelect.nextInt();
        //A simple menu to allow users to select one of the 5 categories available
        try {
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
            logger.log(Level.INFO, "Guest Mode: Input outside of scope");
            stockCat(thisClass, pCheck); }
        } catch (ArithmeticException e) {
            logger.log(Level.INFO, "Stock Category, Invalid input has been detected. " + e.getMessage());
    }
        //Calling the UserReturnPrompt function in order to take the user back out of the Category view
        userReturnPrompt(thisClass, pCheck);
    }
    public static void gBasket(ArrayList<Items> thisClass, boolean pCheck){
        //A function to allow users to add individual items to the basket, and update the quantity of the items within the array.
        logger.log(Level.INFO, "Guest Mode: Guest Basket accessed.");
        File Basket = new File("Basket.txt");
        if (Basket.length() != 0) {
            try {
                BufferedReader in = new BufferedReader( new FileReader(Basket));
                String line;
                System.out.println("Item's in the basket are: ");
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }

            } catch (IOException e ) {
                System.out.println("An Error occured reading from file : " + e.getMessage());
                logger.log(Level.SEVERE, "Guest Mode: Error occured reading from file: ");
            }

        } else {
            System.out.println("The basket is empty");
        }


        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the index of the item you would like to add to basket. ( 1 -30 )");
        int basketSelect = input.nextInt();

// A loop to make sure that the input is within the range of scope.
        if (basketSelect >= 1 && basketSelect <= thisClass.size()) {
            try (FileWriter out = new FileWriter(Basket)) {
                for (int i = 0; i < thisClass.size(); i++) {
                    Items thisItems = thisClass.get(i);
                    if (basketSelect == i + 1) {
                        // If loop to find the position of the selected item, and then display the corresponding information
                        String formattedString = String.format("|Item: %s |Category: %s |Colour %s |Quantity %s| Price %s \n",
                                thisItems.itemName, thisItems.itemCat, thisItems.itemColour, thisItems.itemQty, thisItems.itemPrice);
                        out.write(Integer.toString(i) + formattedString);
                        out.close("Basket.txt);
                        System.out.println("Item added to the basket successfully!.");
                        System.out.format("|Item: %s |Category: %s |Colour %s | Price %s \n", thisItems.itemName, thisItems.itemCat,thisItems.itemColour, thisItems.itemPrice);
                        //Item quantity to go down after items have been added to the basket.
                        logger.log(Level.WARNING, "%s , Item has been added to the basket, lowering the Quantity in the array." + thisItems.itemName);
                        thisItems.itemQty -= 1;
                        userReturnPrompt(thisClass,pCheck);

                    }
                }
            } catch (IOException e) {
                System.out.println("Error occurred writing to file: " + e.getMessage());
                logger.log(Level.SEVERE, "Guest Basket: Error occurred writing to file: ." + e.getMessage());
            }
        } else {
            System.out.println("Invalid index entered. Please enter an index between 1 and " + thisClass.size());
            logger.log(Level.INFO, "Guest Basket: Invalid index entered. Reloading Basket. ");
            //Reload the function incase of an invalid input.
            gBasket(thisClass, pCheck);
        }

    }
    public static boolean adminPcheck(ArrayList<Items> thisClass, boolean pCheck) {
        //Function to authorize access to the Admin settings
        logger.log(Level.SEVERE, "Admin Mode: Admin authentication in progress.");
        System.out.println("In order to proceed please enter the secret passphrase. ");
        Scanner adInput = new Scanner(System.in);
        //Program asks the user for input and then compares the values to the password stored in the text file ssk.txt
        while (!pCheck) {
            String adP = adInput.next();
            try {
                BufferedReader in = new BufferedReader(new FileReader("ssk.txt"));
                String line = in.readLine();
                if (adP.equals(line)){
                    pCheck = true ;
                    logger.log(Level.INFO, "Admin Mode: Authentication successful.");
                    admin(thisClass, pCheck);
                } else {
                    System.out.println("I'm sorry but you cannot access the admin settings. ");
                    logger.log(Level.SEVERE, "Admin Mode: Invalid logon detected! System will shutdown immediately.");
                    System.exit(1);
                }

            } catch (IOException e) {
                System.out.println("I'm sorry but something happened while opening the file: " + e.toString());
                logger.log(Level.SEVERE, "Admin Mode: The program encountered an issue while opening the file: %s." + e.getMessage());
            }
        }



        return pCheck;
    }


    private static ArrayList<Items> loadFile(String filename) {
        //Setup an Array to hold the Inventory data from the Items.txt file
        logger.log(Level.CONFIG, "Beginning to load inventory file.");
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
                logger.log(Level.CONFIG, "Data Array has been generated successfully.");
            }
        } catch (IOException e) {
            System.out.println("Error occurred reading file: " + e.toString());
            logger.log(Level.SEVERE, "Error occurred reading file: %s%n " + e.getMessage());
        }
        return thisClass;
    }


    public static ArrayList<Items> admin(ArrayList<Items> thisClass, boolean pCheck){
        //Function that presents a menu to the privileged user
        logger.log(Level.INFO, "Admin Mode: Access Granted.");
        Scanner adInput = new Scanner(System.in);
        System.out.println("Welcome back Operator. You have successfully entered the privileged settings. ");
        boolean adSelect = false;
        //A loop that will run as long as the conditions inside are not satisfied.
        while (!adSelect){

            System.out.println("Would you like to see the stock levels? \n Press Y to continue, or N to return to menu");
            String adChoice = adInput.next();
            try {
                if(adChoice.equalsIgnoreCase("y")){
                    logger.log(Level.INFO, "Admin Mode: Inventory accessed.");
                    displayAllitems(thisClass, pCheck);
                    adSelect = true;
                }
                else if (adChoice.equalsIgnoreCase("n")) {
                    logger.log(Level.INFO, "Admin Mode: Return to Main menu initiated .");
                    userReturnPrompt(thisClass,pCheck);
                }

            } catch (Exception e) {
                logger.log(Level.SEVERE, "Admin Mode: Invalid input detected " + e.getMessage());
                System.out.println("Invalid input" + e.toString());
                System.exit(1);
            }
        }
        return thisClass;
    }
    public static void displayAllitems(ArrayList<Items> thisClass, boolean pCheck){
        // A function that will allow the Admin to display all items held by the program
        Scanner adInput =new Scanner(System.in);
        for (int i = 0; i < thisClass.size();i++) {
            Items thisItems = (Items)thisClass.get(i);
            System.out.format("(%d) ,%s , %s, %s, %d, %.2f \n",i+1, thisItems.itemName, thisItems.itemCat, thisItems.itemColour, thisItems.itemQty, thisItems.itemPrice);
        }
        double TotalValue = 0.0;
        // this loop will tally up all the values inside of the array and produce a total value of all items in stock.
        for (Items price : thisClass) {
            double itemTotal = price.itemQty * price.itemPrice;
            TotalValue += itemTotal;
            System.out.format("Item: %s, | Quantity: %d | Price: %.2f | Item total: %.2f | Running Total: %.2f \n",price.itemName , price.itemQty , price.itemPrice , itemTotal , TotalValue);

        }
        //The program will then ask users to see whether or not they would like to alter the current prices held within the array
            System.out.format("The total value of the stock is: £%.2f \n", TotalValue );
            System.out.println("Would you like to update any prices? (Y) Yes (N) No");
            String modifyPrices = adInput.next();
            if (modifyPrices.equalsIgnoreCase("y")){
                logger.log(Level.WARNING, "Admin Mode: Price modification panel has been accessed.");
                priceModificationPanel(thisClass, pCheck);
            } else if (modifyPrices.equalsIgnoreCase("n")) {
                logger.log(Level.INFO, "Admin Mode: Return to main menu prompted.");
                userReturnPrompt(thisClass,pCheck);
            }
    }

    public static void priceModificationPanel(ArrayList<Items> thisClass, boolean pCheck) {
        //Function to modify prices at the corresponding index.
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the index of the item you would like to edit the price of. ( 1 -30 )");
        int modPriceIndex = input.nextInt();

// A loop to make sure that only values within the scope can be used

        if (modPriceIndex >= 1 && modPriceIndex <= thisClass.size()) {

                for (int i = 0; i < thisClass.size(); i++) {
                    Items thisItems = thisClass.get(i);
                    if (modPriceIndex == i + 1) {
                        System.out.format("(%d)|Current item: %s Current price: %.2f\n", i+1,thisItems.itemName,thisItems.itemPrice );
                        System.out.println("Please enter the new product price. (£0.00)");
                        double newPrice = input.nextDouble();
                        thisItems.itemPrice = newPrice;
                        System.out.println("Price changed successfully\n");
                        logger.log(Level.WARNING, "Admin Mode: Price modification of the following item complete: " + thisItems.itemName);
                        System.out.format("(%d)|Current item: %s Current price: %.2f\n", i,thisItems.itemName,thisItems.itemPrice );
                        AdminPMPReturnPrompt(thisClass, pCheck);
                    }

                }

        } else {
            logger.log(Level.WARNING, "Admin Mode: Invalid index has been entered, instruction not resolved.");
            System.out.println("Invalid index entered. Please enter an index between 1 and %d)" + thisClass.size());
            priceModificationPanel(thisClass, pCheck);
        }
    }
    public static void introMsg(ArrayList<Items> thisClass, boolean pCheck){
        //Main menu interface allowing users to choose between the Customer and the Moderator options (Guest and Admin)
        logger.log(Level.INFO, "Main menu loading");
        System.out.println("Welcome to the VStoq Inventory Management System, or IMS for short.\n Press G to enter the Guest mode, or A to enter the Administrator mode.");
        Scanner homeInput = new Scanner(System.in);
        boolean OnCheck = false ;
        while (!OnCheck) {


            String userChoice01 = homeInput.next();
            try {
                if (userChoice01.equalsIgnoreCase("g")) {
                    logger.log(Level.INFO, "GUEST MODE ACTIVATED");
                    guest(thisClass, pCheck);
                    OnCheck = true ;
                } else if (userChoice01.equalsIgnoreCase("a")){
                    logger.log(Level.WARNING, "RESTRICTED ACCESS, PRIVILEGED PERSONNEL ONLY");
                    adminPcheck(thisClass, pCheck);
                    OnCheck = true ;
                } else {
                    logger.log(Level.INFO, "UNDEFINED ANSWER PROVIDED, RELOAD INITIATED");
                    introMsg(thisClass, pCheck);
                }
            } catch (Exception e) {
                System.out.println("I'm sorry but your input is invalid" + e.toString());
                logger.log(Level.SEVERE, "Invalid input provided " + e.getMessage());
                introMsg(thisClass, pCheck);
            }


        }

    }
    public static void AdminPMPReturnPrompt(ArrayList<Items> thisClass, boolean pCheck){
        //This function is used to allow users find a point of return to the main menu.
        Scanner promptReturn = new Scanner(System.in);
        logger.log(Level.WARNING, "Admin mode: Continue changes or return to main menu ");
        System.out.println("Would you like to continue updating prices? Please enter Y(YES) or N(NO) to return to main menu ");
        String promptRUser = promptReturn.next();
        try {
            if(promptRUser.equalsIgnoreCase("y")){
                logger.log(Level.INFO, "Admin choice: Continue price changes");
                priceModificationPanel(thisClass, pCheck);

            }
            else if (promptRUser.equalsIgnoreCase("n")) {
                logger.log(Level.INFO, "Admin choice: Return to main menu");
                introMsg(thisClass, pCheck);
            }

        } catch (Exception e) {
            System.out.println("Invalid input" + e.getMessage());
            logger.log(Level.SEVERE, "Error detected due to invalid input" + e.getMessage() + e );
            System.exit(1);
        }
    }
    public static void userReturnPrompt(ArrayList<Items> thisClass, boolean pCheck){
        //This function is used to allow users find a point of return to the main menu.
        Scanner promptReturn = new Scanner(System.in);
        logger.log(Level.INFO, "System waiting for user choice(Return to Main Menu).");
        System.out.println("Would you like to return to the main menu? Please enter Y(YES) or N(NO) ");
        String promptRUser = promptReturn.next();
        try {
            if(promptRUser.equalsIgnoreCase("y")){
                logger.log(Level.INFO, "User choice: Main Menu");
                introMsg(thisClass,pCheck);

            }
            else if (promptRUser.equalsIgnoreCase("n")) {
                logger.log(Level.INFO, "User choice: Exit ");
                System.exit(1);
            }

        } catch (Exception e) {
            System.out.println("Invalid input" + e.toString());
            logger.log(Level.SEVERE, "Error detected due to invalid input" + e.getMessage() + e );
            System.exit(1);
        }
    }
    public static void main(String[] args) {
        //Initialise Logging on startup of the program
        setupLogging();
        // Basic log to indicate a Starting point for the app.
        logger.log(Level.INFO, "Application started.");
        ArrayList<Items> thisClass = new ArrayList<Items>();
        thisClass = initiateLoadingFiles(thisClass);
        boolean pCheck = false;
        // Initialise variables and create a scanner




        //Run a function which will check for presence of a file which stores a password
        KeyCheck(thisClass,pCheck);
        userReturnPrompt(thisClass, pCheck);
    }
    //A function which will proceed ask the user whether the correct base file has been added and if they would like to continue.
        public static ArrayList<Items> initiateLoadingFiles(ArrayList<Items> thisClass) {
            boolean stockCheck = false;
        while (!stockCheck) {

                Scanner adInput = new Scanner(System.in);
                logger.log(Level.CONFIG, "Loading files initiated.");
                System.out.println("The system currently has no data in side of it. Please make sure the file items.txt is accessible \n Press Y to continue, or N to return to exit");
                String adChoice = adInput.next();
                try {
                    if (adChoice.equalsIgnoreCase("y")) {
                        logger.log(Level.CONFIG, "Files loaded successfully.");
                        thisClass = loadFile("Items.txt");
                        // Loop for all objects in text file

                        stockCheck = true;


                    } else if (adChoice.equalsIgnoreCase("n")) {
                        logger.log(Level.INFO, "User chose not to load files. System will now shutdown.");
                        System.exit(1);

                    }

                } catch (Exception e) {
                    System.out.println("Invalid input" + e.getMessage());
                    logger.log(Level.SEVERE, "INVALID INPUT, SYSTEM WILL NOW SHUTDOWN." + e.getMessage() + e);
                    System.exit(1);
                }
            }
        return thisClass;
        }



}
