/********************
 *Steven Lum
 *12/7/2017
 *Project #4
 *******************/
 
import java.util.*;

public class GroceryDriver {
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      boolean cus;
      do {
         start(console);
         System.out.print("Is there another customer? ");
         cus = vYorN(console,"yes","no");
         System.out.println();
         if(cus)
            console.nextLine();
      } while (cus);
      
      
   }
   
   public static void shoppingTime(groceryList theList, Scanner console, item[] items) {
      boolean itemsToBuy = true;
      // assumes the limit is 50 items
      //item[] items = new item[50];
      int count = 0;
      while(itemsToBuy) {
         
         String itemName = "";
         int itemQty = 0;
         double itemPrice = 0;
         
         System.out.print("Enter the name of the item: ");
         itemName = console.nextLine();
         System.out.print("Enter the quantity of the item: ");
         while(!console.hasNextInt()) {
            System.out.print("INVALID AMOUNT");
            console.next();
         }
         itemQty = console.nextInt();
         
         System.out.print("Enter the price of the item: ");
         
         itemPrice = console.nextDouble();
         console.nextLine();
         items[count] = new item(itemName,itemQty,itemPrice);
         theList.add(items[count]);
         
         System.out.print("Are there more items to buy? ");
         itemsToBuy = vYorN(console,"yes","no");
         console.nextLine();
         count++;
         System.out.println();
      }
      
   }
   
   // returns true or false
   public static boolean vYorN(Scanner console,String equal1, String equal2) {
      boolean validAnswer = false;
      boolean retVal = true;
      while(!validAnswer) {
         String YorNIn = console.next();
         if (YorNIn.equalsIgnoreCase(equal1)) {
            retVal = true;
            validAnswer = true;
         } else if (YorNIn.equalsIgnoreCase(equal2)) {
            retVal = false;
            validAnswer = true;
         } else
            System.out.print("Please enter " + equal1 + " or " + equal2 + " ");
      }
      return retVal;
   }
   
   public static String getList(groceryList theList) {
      return theList.toString();
   }
   
   // modifies the grocery list
   public static void modMyList(groceryList theList, Scanner console, boolean mod, item[] items) {
      
      while(mod) {
         // item to modify
         console.nextLine();
         System.out.print("What is the name of the item you want to modify?: ");
         String modName = console.nextLine();
         System.out.print("Enter the new price of the item: ");
         while(!console.hasNextDouble()) {
            System.out.print("INVALID AMOUNT ");
            console.next();
         }
         double modPrice = console.nextDouble();
         System.out.print("Enter the new quantity of the item: ");
         while(!console.hasNextInt()) {
            System.out.print("INVALID AMOUNT ");
            console.next();
         }
         int modQuantity = console.nextInt();
         
         
         // removal or modification
         System.out.println("Type \"rem\" to remove this item");
         System.out.println("Type \"mod\" to modify this item");
         boolean remMod = vYorN(console,"rem","mod");
         
         int indexOfItem = theList.find(modName);
         
         if(indexOfItem == -1) 
            System.out.println("The item is not on the list");
            // if they want to modify
         else if (!remMod) {
            items[indexOfItem].setQuantity(modQuantity);
            items[indexOfItem].setPricePerUnit(modPrice);
            // if they want to remove
         } else if (remMod) {
            theList.remove(items[indexOfItem],indexOfItem);
         }
         
         
         System.out.print("Do you want to keep modifying items on this list? ");
         mod = vYorN(console,"yes","no");
         System.out.println();
         
      }
   }
   
   public static void getFinalCost(groceryList theList) {
      System.out.printf("The total cost of the items is: %.2f%n", theList.getTotalCost());
   }
   
   public static void start(Scanner console) {
      item[] items = new item[50];
      groceryList myList = new groceryList();
      shoppingTime(myList,console,items);
      System.out.println(myList.getList());
      System.out.print("Do you want to mod the list of items? ");
      boolean mod = vYorN(console,"yes","no");
      modMyList(myList,console,mod,items);
      if(mod)
         System.out.println(myList.getList());
      getFinalCost(myList);
   }
}