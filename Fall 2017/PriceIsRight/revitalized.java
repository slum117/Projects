/*************************
 * Steven Lum
 * Project 3
 ************************/
 
import java.io.*;
import java.util.*;

public class revitalized {
   
   public static void main(String[] args) 
         throws FileNotFoundException {
         
      Scanner console = new Scanner(System.in);
      play(console);
   
      
   }
   
   
   //
   public static void play(Scanner console) 
         throws FileNotFoundException {
   // to see if the items/name has been used
      boolean desireToPlay = true;
      
      while(desireToPlay) {
         int[] usedNames = new int[20];
         int[] usedItems = new int[20];
      
         // store names into an array
         String[] cntNames = new String[20];
         fillNames(cntNames, "names.txt");
         //System.out.println(Arrays.toString(cntNames));
         
         String[] crntPlyrs = new String[4];
         
         // store item names and price into array
         int[] price = new int[20];
         String[] item = new String[20];
         int[] bid = new int[4];
         fillNames(item,price,"Items");
         //System.out.println(Arrays.toString(price));
         //System.out.println(Arrays.toString(item));
         
         description();
         System.out.println("These four players will be chosen to play the game: ");
         
         // select the first four players
         slctPlyrs(usedNames,crntPlyrs, cntNames);
         /*for(int i = 0; i < 4; i++) {
            int selectDigit = genNum();
            while(usedNames[selectDigit] != 0) {
               selectDigit = genNum();
            }
            usedNames[selectDigit]++;
            crntPlyrs[i] = cntNames[selectDigit];
            //System.out.println(selectDigit); 
            //System.out.print(crntPlyrs[i] + " ");
         }*/
         System.out.println();
         
         // selects the items
         displayRandItem(item, usedItems, bid, price, crntPlyrs, console, usedNames, cntNames);
         
         /*int selectDigit = genNum();
         while(usedItems[selectDigit] != 0)
            selectDigit = genNum();
         System.out.printf("");*/
         
         
         //while there are players/items to be
         
         
         // asks the user if s/he wants to keep on playing
         System.out.println("\nDo you want to start a new game?");
         String keepPlaying = console.next();
         desireToPlay = continuePlaying(desireToPlay, keepPlaying, console);
         /*while(invalidInput) {
            if(keepPlaying.equalsIgnoreCase("no")) {
               desireToPlay = false;
               invalidInput = false;
            } else if (keepPlaying.equalsIgnoreCase("yes")) {
               desireToPlay = true;
               invalidInput = false;
            } else {
               invalidInput = true;
               System.out.printf("Invalid input: %s%n", keepPlaying);
               System.out.println("Please enter [yes/no]");
               keepPlaying = console.next();
            }
         }*/
      } 
   } // end play
   
   
   // selects and displays a random item and then has players put there inputs in it
   // removes the random item from the list and finds the bid that is closest to the actual item
   // and returns the index of that bid
   public static void displayRandItem(String[] item, int[] usedItems, int[] bid, int[] price, String[] crntPlyrs, Scanner console, int[] usedNames, String[] cntNames) {
      boolean replacePlayer = false;
      boolean desireToPlay = true;
      boolean playersLeft = playersLeft(usedNames);
      int[] replaceUsers = new int[4];
      
      while(desireToPlay) {
      
         // accessible after the 1st playthrough
         if(playersLeft) {
            if(replacePlayer) {
               System.out.println("Replacing Overbidders");
               for(int i = 0; i < 4; i++) {
                  if(replaceUsers[i] != 0) {
                     crntPlyrs[i] = cntNames[newPlyrs(usedNames)];
                  }
               }
               Arrays.fill(replaceUsers,0);
            }
         } else
            System.out.println("There are no players left to be replaced");
            
            
         int selectDigit = genNum();
         while(usedItems[selectDigit] != 0)
            selectDigit = genNum();
         usedItems[selectDigit]++;
         
         System.out.printf("Item:%3s %n", item[selectDigit]);
      
         for(int i = 0; i < 4; i++) {
         
            System.out.printf("%s, please enter your bid: ", crntPlyrs[i]);
            while(!console.hasNextInt()) {
               System.out.print("Please enter a integer: ");
               console.next();
            }
            bid[i] = console.nextInt();
         
         }
      
         int highestBid = 0;
         int winner = -1;
         for(int i = 0; i < 4; i++) {
            if(bid[i] <= price[selectDigit] && bid[i] > highestBid) {
               highestBid = bid[i];
               winner = i;
            }
         }
      
         System.out.printf("%n%10s %5s%n","Name","Price");
         System.out.printf("-----------------------%n");
         for(int i = 0; i < 4; i++)
            System.out.printf("%10s %5d %n",crntPlyrs[i],bid[i]);
         System.out.printf("-----------------------%n");
         if(winner >= 0 && winner <= 3)
            System.out.printf("The winner is %s, with a bid of %d %n",crntPlyrs[winner],highestBid);
         else
            System.out.println("no won one");
         System.out.printf("%s is listed as $%d%n",item[selectDigit],price[selectDigit]);
      //return bid;
         System.out.println("\nDo you want to keep playing?");
         String keepPlaying = console.next();
         desireToPlay = continuePlaying(desireToPlay, keepPlaying, console);
         for(int i = 0; i < 4; i++) {
            if(bid[i] > price[selectDigit])  {
               replacePlayer = true;
               replaceUsers[i]++;
            }
         }
         
      }
   }
   
   
   // checks if there are players left in the list, returns false if there are no players
   public static boolean playersLeft(int[] usedNames) {
      boolean thereArePlayers = false;
      for(int i = 0; i < usedNames.length; i++) {
         if(usedNames[i] == 0) {
            thereArePlayers = true;
         }
      }
      return thereArePlayers;
   }
   
   
   // selects four random players to play the game
   public static void slctPlyrs(int[] usedNames, String[] crntPlyrs, String[] cntNames) {
      for(int i = 0; i < 4; i++) {
         int selectDigit = genNum();
         while(usedNames[selectDigit] != 0) {
            selectDigit = genNum();
         }
         usedNames[selectDigit]++;
         crntPlyrs[i] = cntNames[selectDigit];
            //System.out.println(selectDigit); 
         System.out.print(crntPlyrs[i] + " ");
      }
   }
   
   
   public static int newPlyrs(int[] usedNames) {
      int selectDigit = genNum();
      while(usedNames[selectDigit] != 0) {
         selectDigit = genNum();
      }
      usedNames[selectDigit]++;
      return selectDigit;
   }
   
   
   // asks if the player wants to keep playing the game
   public static boolean continuePlaying(boolean invalidInput, String keepPlaying, Scanner console) {
      boolean desireToPlay = false;
      
      while(invalidInput) {
         if(keepPlaying.equalsIgnoreCase("no")) {
            desireToPlay = false;
            invalidInput = false;
         } else if (keepPlaying.equalsIgnoreCase("yes")) {
            desireToPlay = true;
            invalidInput = false;
         } else {
            invalidInput = true;
            System.out.printf("Invalid input: %s%n", keepPlaying);
            System.out.println("Please enter [yes/no]");
            keepPlaying = console.next();
         }
      }
      return desireToPlay;
   } // end continuePlaying
   
   
   // fills the array in the parameter with names
   public static void fillNames(String[] StrArr1, String fileName) 
         throws FileNotFoundException {
      File fNames = new File(fileName);
         
      if(fNames.exists()) {
         Scanner getNames = new Scanner(fNames);
         int i = 0;
         while(getNames.hasNext()) {
            StrArr1[i] = getNames.next();
            i++;
         }
      }
      else
         System.out.printf("%s not found%n", fileName);
   } // end fillNames
   
   
   // it's okay to overload right?
   // fills the item and price list
   public static void fillNames(String[] StrArr1, int[] intArr1, String fileName) 
         throws FileNotFoundException {
      File fNames = new File(fileName);
         
      if(fNames.exists()) {
         Scanner getNames = new Scanner(fNames);
         int i = 0;
         while(getNames.hasNext()) {
            while(getNames.hasNextInt()) {
               intArr1[i] = getNames.nextInt();
            }
            StrArr1[i] = getNames.nextLine();
            i++;
         }
      }
      else
         System.out.printf("%s not found%n", fileName);
   } // end fillNames

   
   // displays discription for people to read
   public static void description() {
      System.out.println(" ____________________________________________________");
      System.out.println("|                                                    |");
      System.out.println("|Bid on the Price of the Item without going overboard|");
      System.out.println("|The player closest to the actual price will win     |");
      System.out.println("|____________________________________________________|");
   }
   
   
   // generates a number between 1 and 20
   public static int genNum() {
      Random rand = new Random();
      return rand.nextInt(20);
   }

}