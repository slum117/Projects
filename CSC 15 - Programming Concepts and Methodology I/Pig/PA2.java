import java.util.*;
public class PA2 {

   public static final int POINTS = 30;
   public static final int FORFEIT_POINTS = 20;
   
   public static void main(String[] args) {
   
      Scanner console = new Scanner(System.in);
      play(console);
   
   }
   // main method to call all other methods to play the game
   public static void play(Scanner console) {
      Random rand = new Random();
      boolean offOn = true;
      
      while (offOn) {
         boolean cpuStartFirst = false;
         int coinFlip = rand.nextInt(2);
         int myTotalPoints = 0;
         int cpuTotalPoints = 0;
         description();
         String cpuName = genRanName(rand.nextInt(10));
         String myName = introduction(console,cpuName);
         
         // determines who starts the game.
         if(coinFlip == 0) {
            System.out.printf("%s is going to start the game%n%n",myName);
         } else if (coinFlip == 1) {
            System.out.printf("%s is going to start the game%n%n",cpuName);
            cpuStartFirst = true;
         }
         
         // CPU starts the game
         if(coinFlip == 1) {
            while (myTotalPoints < POINTS || cpuTotalPoints < POINTS) {
               cpuTotalPoints += currentTurn(cpuName,myName,true, console, cpuTotalPoints);
               System.out.println(cpuName + " has a total of " + cpuTotalPoints + " points");
               if(cpuTotalPoints >= POINTS) {
                  System.out.printf("%s has won the game%n%n",cpuName);
                  break;
               }
               myTotalPoints += currentTurn(cpuName,myName,false, console, myTotalPoints);
               System.out.println(myName + " has a total of " + myTotalPoints + " points");
               if(myTotalPoints >= POINTS) {
                  System.out.printf("%s has won the game%n%n",myName);
                  break;
               }
            }
         // I start the game
         } else {
            while (myTotalPoints < POINTS || cpuTotalPoints < POINTS) {
               myTotalPoints += currentTurn(cpuName,myName,false, console, myTotalPoints);
               System.out.println(myName + " has a total of " + myTotalPoints + " points\n");
               if(myTotalPoints >= POINTS) {
                  System.out.printf("%s has won the game%n%n",myName);
                  break;
               }
               cpuTotalPoints += currentTurn(cpuName,myName,true, console, cpuTotalPoints);
               System.out.println(cpuName + " has a total of " + cpuTotalPoints + " points\n");
               if(cpuTotalPoints >= POINTS) {
                  System.out.printf("%s has won the game%n%n",cpuName);
                  break;
               }
            }
         }
         System.out.println("Play again?");
         String playAgain = console.next();
         if(playAgain.equalsIgnoreCase("no"))
            offOn = false;
      }

   }
   // produces the description players will see
   public static void description() {
      System.out.println("________________________________________________________________");
      System.out.println("|                                                               |");
      System.out.println("|   You are about to play the pig game against a computer.      |");
      System.out.println("|   Each turn, the current player will roll a pair of dice      |");
      System.out.println("|   and accumulate points. The goal is to reach 30 points       |");
      System.out.println("|   before your opponent. If a roll contains a 1, all points    |");
      System.out.println("|   for the current round will be lost. If the roll contains    |");
      System.out.println("|   2 1's, then all the points the player has earned will be    |");
      System.out.println("|   lost and control will be given to the other player. The     |");
      System.out.println("|   player may voluntarily turn over the dice after each roll.  |");
      System.out.println("|   Each turn, the current player will roll a pair of dice.     |");
      System.out.println("|   Therefore, the player must decide to roll again and risk    |");
      System.out.println("|   it all or give the computer a chance to win by giving it    |");
      System.out.println("|   control. Player one will be chosen at random.               |");
      System.out.println("|_______________________________________________________________|");   
   }
   // returns a random name for the CPU
   public static String genRanName(int ofTenNames) {
      
      
      if (ofTenNames == 1) {
         return "Gandalf";
      } else if (ofTenNames == 2) {
         return "Frodo";
      } else if (ofTenNames == 3) {
         return "Sauron";
      } else if (ofTenNames == 4) {
         return "Aragorn";
      } else if (ofTenNames == 5) {
         return "Legolas";
      } else if (ofTenNames == 6) {
         return "Gollum";
      } else if (ofTenNames == 7) {
         return "Gimli";
      } else if (ofTenNames == 8) {
         return "Sam";
      } else if (ofTenNames == 9) {
         return "Bilbo";
      } else {
         return "Harry Potter"; // returns harry if ofTenNames == 0;
      }
   }
   
   // method to introduce CPU's name to player and have them set their name then return to play
   public static String introduction(Scanner console, String oppName) {
      String myName = "";
      System.out.printf("Hi my name is %s%n", oppName);
      System.out.print("What is your name? ");
      myName = console.next();
      System.out.printf("Hi %s, I am flipping the coin to determine who goes first%n", myName);
      System.out.println("Press any key to start the game\n");

      return myName;
   }
   
   // rolls the dice for an integer between 1 and 6
   public static int rollDice() {
      Random rand = new Random();
      return rand.nextInt(6)+1;
   }
   
   public static int currentTurn(String cpuName, String myName, boolean cpuTurn, Scanner console, int totalPoints) {
      int accumulatedPoints = 0;
      int firstRoll, secondRoll;
      String endTurn = "";
      while (accumulatedPoints+totalPoints < POINTS && cpuTurn) {
         firstRoll = rollDice();
         secondRoll = rollDice();
         System.out.printf("%s's total points: %d%n", cpuName, totalPoints);
         System.out.printf("%s's accumulated points: %d%n", cpuName, accumulatedPoints);
         System.out.printf("%s has rolled a %d and a %d %n%n", cpuName, firstRoll, secondRoll);
         
         if(firstRoll != 1 && secondRoll != 1)
            accumulatedPoints+= firstRoll + secondRoll;
         else if(firstRoll == 1 || secondRoll == 1) {
            return 0;
         } else if(firstRoll == 1 && secondRoll == 1) {
            return -totalPoints;
         }
      
         if(accumulatedPoints > FORFEIT_POINTS)
            return accumulatedPoints;
      }
      
      while (accumulatedPoints+totalPoints < POINTS && !cpuTurn) {
         firstRoll = rollDice();
         secondRoll = rollDice();
         System.out.printf("%s's total points: %d%n", myName, totalPoints);
         System.out.printf("%s's accumulated points: %d%n", myName, accumulatedPoints);
         System.out.printf("%s has rolled a %d and a %d %n%n", myName, firstRoll, secondRoll);
         console.next();
         if(firstRoll != 1 && secondRoll != 1)
            accumulatedPoints+= firstRoll + secondRoll;
         else if(firstRoll == 1 || secondRoll == 1) {
            return 0;
         } else if(firstRoll == 1 && secondRoll == 1) {
            return -totalPoints;
         }
         System.out.println("End your turn? [yes/no]");
         endTurn = console.next();
         if(endTurn.equalsIgnoreCase("yes"))
            return accumulatedPoints;
      }
      
      return accumulatedPoints;
   }
}