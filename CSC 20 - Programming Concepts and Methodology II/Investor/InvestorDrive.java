import java.util.*;

public class InvestorDrive {
   public static void main(String[] args) 
      throws NullPointerException {
      Scanner console = new Scanner(System.in);
      start(console);
   
   }
   public static void start(Scanner console) {
      System.out.println("Investor_Drive");
      boolean exit = false;
      System.out.print("What is your name? ");
      String userName = console.next();
      
      System.out.println("Welcome, " + userName);
      Investor user = new Investor(userName);
      
      // options for the user to choose from
      console.nextLine();
      while(!exit) {
      menu();
      String userChoice = console.nextLine();
      
      
      
      if(userChoice.equalsIgnoreCase("exit"))
         exit = true;
      else if(userChoice.equalsIgnoreCase("buy")) {
         buyStock(user,console);
         console.nextLine();
      } else if(userChoice.equalsIgnoreCase("display")) 
         display(user); 
      else if(userChoice.equalsIgnoreCase("gain or loss")) {
         gainOrLoss(user,console);
         console.nextLine();
      } else if(userChoice.equalsIgnoreCase("Sell All")) {
         sellAllStocks(user,console);
         console.nextLine();
      } else if (userChoice.equalsIgnoreCase("Sell Partial")) {
         sellPartial(user,console);
         console.nextLine();
      } else
         System.out.println(userChoice + " is not available");
      }
      
      
   }
   public static void menu() {
      System.out.println("___________________________");
      System.out.println();
      System.out.println("Buy");
      System.out.println("Sell All");
      System.out.println("Sell Partial");
      System.out.println("Display");
      System.out.println("Gain or Loss");
      System.out.println("Exit");
      System.out.println("___________________________");
      System.out.println();

   }
   
   public static void buyStock(Investor me, Scanner console) {
      System.out.print("Enter the stocks name: ");
      String name = console.nextLine();
      
      System.out.print("Enter the amount being bought: ");
      while(!console.hasNextInt()) {
         System.out.println("Please enter an amount");
         console.next();
         }
      int amount = console.nextInt();
      System.out.print("Enter the price of the stock: ");
      while(!console.hasNextDouble()) {
         System.out.println("Please enter an amount");
         console.next();
         }
      double price = console.nextDouble();
      me.buy(name,amount,price);

   }
   
   public static void display(Investor me) {
      System.out.println(me.toString());
   }
   
   public static void gainOrLoss(Investor me, Scanner console) {
      System.out.println("Select the stock you want to find out the gain or loss: ");
      stock[] newStocks = me.getStock();
      for(int i = 0; i <= me.getCount(); i++) {
         if(newStocks[i] != null) {
            System.out.println("Name: " + newStocks[i].getName());
            System.out.println("Price: " + newStocks[i].getPrice());
            System.out.println("Amount: " + newStocks[i].getAmount());
            System.out.println("Date Purchased: " + newStocks[i].getDatePurchased());
            }
         System.out.println();
      }
      System.out.print("Enter your choice: ");
      String name = console.nextLine();
      System.out.print("Enter the current price for this stock: ");
      while(!console.hasNextDouble()) {
         System.out.println("Please enter a price");
         console.next();
         }
      double price = console.nextDouble();
      System.out.printf("Gain: %.2f %n",newStocks[me.find(name)].getProfit(price));
      
   }
   
   public static void sellAllStocks(Investor me, Scanner console) {
      System.out.println("Select the stock you want to sell: ");
      stock[] newStocks = me.getStock();
      for(int i = 0; i <= me.getCount(); i++) {
         if(newStocks[i] != null) {
            System.out.println("Name: " + newStocks[i].getName());
            System.out.println("Price: " + newStocks[i].getPrice());
            System.out.println("Amount: " + newStocks[i].getAmount());
            System.out.println("Date Purchased: " + newStocks[i].getDatePurchased());
            }
         System.out.println();
      }
      System.out.print("Enter your choice: ");
      String name = console.nextLine();
      System.out.print("Enter the current price for this stock: ");
      while(!console.hasNextDouble()) {
         System.out.println("Please enter a price");
         console.next();
         }
      double price = console.nextDouble();
      System.out.printf("The amount deposited to your accoint is: %.2f %n",newStocks[me.find(name)].getProfit(price));
      me.sellAll(name,price);
   }
   
   public static void sellPartial(Investor me, Scanner console) {
      System.out.println("Select the stock you want to sell: ");
      stock[] newStocks = me.getStock();
      for(int i = 0; i <= me.getCount(); i++) {
         if(newStocks[i] != null) {
            System.out.println("Name: " + newStocks[i].getName());
            System.out.println("Price: " + newStocks[i].getPrice());
            System.out.println("Amount: " + newStocks[i].getAmount());
            System.out.println("Date Purchased: " + newStocks[i].getDatePurchased());
            }
         System.out.println();
      }
      System.out.print("Enter your choice: ");
      String name = console.nextLine();
      System.out.print("Enter the current price for this stock: ");
      while(!console.hasNextDouble()) {
         System.out.println("Please enter a price");
         console.next();
         }
      double price = console.nextDouble();
      System.out.print("Enter the amount of stocks you would like to sell");
      while(!console.hasNextInt()) {
         System.out.println("Please enter a price");
         console.next();
         }
      int quantity = console.nextInt();
      System.out.printf("The amount deposited to your account is: %.2f %n",newStocks[me.find(name)].getProfit(price));
      me.sellPartial(name,price,quantity);
   }
}