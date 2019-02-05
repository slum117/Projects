/********************
 * Steven Lum
 * 10/12/2017
 ******************/

import java.util.Scanner;

public class Admit {

   public static void main(String[] args) {
      Scanner kb = new Scanner(System.in);
      start(kb);
   }
   
   public static void start(Scanner console) {
      Description();
      System.out.print("How many times do you want to use the software: ");
      
      int amount = console.nextInt();
      double theScore[] = {0,1};
      // Runs for amount of times user desires
      // nested loop for inputing data for different aplicants
      for(int i = 1; i <= amount; i++) {
         for(int j = 1; j <= 2; j++) {
            theScore[j-1] = receiveData(j, console);
         }
      // Declare scores
         System.out.printf("First applicant overall score = %.1f %n" , theScore[0]);
         System.out.printf("Second applicant overall score = %.1f %n" , theScore[1]);
      
      // Declare who has better scores
         if (theScore[0] > theScore[1]) {
            System.out.println("The first applicant seems to be better \n");
         } else if (theScore[0] < theScore[1]) {
            System.out.println("The second applicant seems to be better \n");
         } else {
            System.out.println("The two applicants seem to be equal \n");
         }
      }
   }
   
   
   public static double receiveData(int applicantNum, Scanner console) {
      System.out.println("Information for applicant #" + applicantNum);
      
      double SatOrActScore = 1;
      for (int i = 1; i <= 1; i++) {
         SatOrActScore = actOrSat(console);
         if(SatOrActScore == 0) {
            i = 1;
         }
      }
      
      // this area asks for GPA input
      double maxGPA, overAllGPA, transMulti,GPA_Score;
      System.out.print("\toverall GPA? ");
      overAllGPA = console.nextDouble();
      System.out.print("\tmax GPA? ");
      maxGPA = console.nextDouble();
      System.out.print("\tTranscript Multiplier? ");
      transMulti = console.nextDouble();
      GPA_Score = overAllGPA/maxGPA*100*transMulti;
      GPA_Score = (double)Math.round(GPA_Score*10)/10;
      System.out.println("\tGPA score = " + GPA_Score + "\n");
      double score = SatOrActScore + GPA_Score;
      return score;
   }
   public static double actOrSat(Scanner console) {
      // for loop to make sure that a valid input is accepted
      for(int i = 1; i == 1; i++) {
         System.out.print("\tDo you have 1) SAT scores or 2) ACT scores? ");
         int ActOrSat = console.nextInt();
         double examScore, math, english, reading, science, writing;
           // SAT
         if(ActOrSat == 1) {
            System.out.print("\tSAT math? ");
            math = console.nextInt();
            System.out.print("\tSAT critical reading? ");
            reading = console.nextInt();
            System.out.print("\tSAT writing? ");
            writing = console.nextInt();
            examScore = (2*math+reading+writing)/32;
            System.out.printf("\texam score = %.1f %n", examScore);
            return examScore;
            
           //ACT 
         } else if (ActOrSat == 2) {
            System.out.print("\tACT English? ");
            english = console.nextInt();
            System.out.print("\tACT math? ");
            math = console.nextInt();
            System.out.print("\tACT reading? ");
            reading = console.nextInt();
            System.out.print("\tACT science? ");
            science = console.nextInt();
            examScore = (english+(2*math)+reading+science)/1.8;
            System.out.printf("\texam score = %.1f %n", examScore);
            return examScore;
            
           // ask for input and set i to 0; 
         } else {
            System.out.println("Please input 1 or 2");
            i = 0;
         }
      }
      return 231;
   }
   
   public static void Description() {
      System.out.println("This program compares two applicants to");
      System.out.println("determine which one seems like the stronger");
      System.out.println("applicant. For each candidate I will need");
      System.out.println("either SAT or ACT scores plus a weighted GPA");
   }
}