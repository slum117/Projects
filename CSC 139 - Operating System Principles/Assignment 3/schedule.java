/*
CSC139 
Spring 2021
Assignment 3
Lum,Steven
Section 4
*/

import java.util.*;
import java.io.*;
public class schedule {
   
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("input.txt"));
      PrintStream output = new PrintStream(new File("output.txt"));
      int timeQuantum, numberOfProcesses;
      String type = input.next();
      if(type.equals("RR"))
         timeQuantum = input.nextInt();
      else
         timeQuantum = 0;
      input.nextLine();
      numberOfProcesses = input.nextInt();
      input.nextLine();
      
      // load process number, arrival time, cpu burst, priority
      int[][] processInfo = new int[numberOfProcesses][4];
      for(int i = 0; i < numberOfProcesses; i++) {
         processInfo[i][0] = input.nextInt();
         processInfo[i][1] = input.nextInt(); 
         processInfo[i][2] = input.nextInt(); 
         processInfo[i][3] = input.nextInt(); 
         if(i != numberOfProcesses-1)
            input.nextLine();
      } 
      
      if(type.equals("RR"))
         roundRobin(output, processInfo, timeQuantum);
      else if (type.equals("SJF"))
         shortestJobFirst(output, processInfo);
      else if (type.equals("PR_noPREMP")) 
         priorityNoPreempt(output, processInfo);
      else if (type.equals("PR_withPREMP"))
         priorityWithPreempt(output, processInfo);
   }
   public static void roundRobin(PrintStream output, int[][] processInfo, int timeQuantum) {
      output.println("RR " + timeQuantum);
      int[][] readyQueue = new int[processInfo.length][4];
      int completedProcesses = 0, currentProccess = 0, nextArrival = 0, time = 0, timeIncrement = 0, totalWait = 0, readyQueuePointer = 0;
      sort(processInfo,1);
      while (processInfo[0][1]!=time) 
         time++;
      for(int i = nextArrival; i < processInfo.length && processInfo[i][1] <= time; i++) {
         readyQueue[readyQueuePointer++] = processInfo[i];
         nextArrival++;
      }
      while(completedProcesses != processInfo.length) {
         output.printf("%d %d\n", time, readyQueue[0][0]);
         if(readyQueue[0][2] <= timeQuantum)
            timeIncrement = readyQueue[0][2];
         else
            timeIncrement = timeQuantum;
         time+= timeIncrement;
         readyQueue[0][2] -= timeIncrement;
         totalWait += timeIncrement * (readyQueuePointer-1);
         int[] temp = readyQueue[0];
         readyQueuePointer--;
         for(int i = 0; i < readyQueuePointer; i++)
            readyQueue[i] = readyQueue[i+1];
         for(int i = nextArrival; i < processInfo.length && processInfo[i][1] <= time; i++) {
            readyQueue[readyQueuePointer++] = processInfo[i];
            nextArrival++;
            totalWait += time - processInfo[i][1];
         }
         if(temp[2] == 0)
            completedProcesses++;
         else
            readyQueue[readyQueuePointer++] = temp;
      }
      output.printf("Average Wait: %.2f\n", (double)totalWait/(processInfo.length));
   }
   
   public static void shortestJobFirst(PrintStream output, int[][] processInfo) {
      output.println("SJF");
      int[][] readyQueue = new int[processInfo.length][4];
      int completedProcesses = 0, currentProccess = 0, nextArrival = 0, time = 0, timeIncrement = 0, totalWait = 0, readyQueuePointer = 0;
      sort(processInfo,1);
      while (processInfo[0][1] != time) 
         time++;
      for(int i = nextArrival; i < processInfo.length && processInfo[i][1] <= time; i++) {
         readyQueue[readyQueuePointer++] = processInfo[i];
         nextArrival++;
      }
      while (completedProcesses != processInfo.length) {
         timeIncrement = 0;
         currentProccess = 0;
         for(int i = 1; i < readyQueuePointer; i++) {
            if((readyQueue[currentProccess][2] == readyQueue[i][2] && readyQueue[currentProccess][1] == readyQueue[i][1] && readyQueue[i][0] < readyQueue[currentProccess][0])
            || readyQueue[currentProccess][2] > readyQueue[i][2] || (readyQueue[currentProccess][2] == readyQueue[i][2] && readyQueue[currentProccess][1] > readyQueue[i][1]))
               currentProccess = i;
         }
         
         output.printf("%d %d\n", time, readyQueue[currentProccess][0]);
         timeIncrement = readyQueue[currentProccess][2];
         readyQueue[currentProccess][2] = 0;
         completedProcesses++;
         time += timeIncrement;
         readyQueue[currentProccess] = readyQueue[readyQueuePointer-1];
         readyQueuePointer--;
         totalWait += timeIncrement*readyQueuePointer;
         for (int i = nextArrival; i < processInfo.length && processInfo[i][1] <= time; i++) {
            readyQueue[readyQueuePointer++] = processInfo[i];
            nextArrival++;
            totalWait+= time - processInfo[i][1];
         }
      }
   
      output.printf("Average Wait: %.2f\n", (double)totalWait/(processInfo.length));
   }
   
   public static void priorityNoPreempt(PrintStream output, int[][] processInfo) {
      output.println("PR_noPREMP");
      int[][] readyQueue = new int[processInfo.length][4];
      int completedProcesses = 0, time = 0, currentProccess = 0, nextArrival = 0, timeIncrement = 0, totalWait = 0, readyQueuePointer = 0;
      sort(processInfo, 1);
      while (processInfo[0][1]!=time) 
         time++;
      for (int i = nextArrival; i < processInfo.length && processInfo[i][1]<=time; i++) {
         readyQueue[readyQueuePointer++] = processInfo[i];
         nextArrival++;
      }
      while (completedProcesses != processInfo.length) {
         timeIncrement = 0;
         currentProccess = 0;
         for (int i = 1; i < readyQueuePointer; i++) {
            if ((readyQueue[currentProccess][3] == readyQueue[i][3] && readyQueue[currentProccess][0] > readyQueue[i][0]) 
            || readyQueue[currentProccess][3] > readyQueue[i][3])
               currentProccess = i;
         }		
         output.printf("%d %d\n", time, readyQueue[currentProccess][0]);
         timeIncrement = readyQueue[currentProccess][2];
         time += timeIncrement;
         totalWait += (readyQueuePointer-1)*timeIncrement;
         readyQueue[currentProccess][2] = 0;
         readyQueue[currentProccess] = readyQueue[readyQueuePointer-1];
         readyQueuePointer--;
         completedProcesses++;
         for (int i = nextArrival; i < processInfo.length && processInfo[i][1] <= time; i++) {
            readyQueue[readyQueuePointer++] = processInfo[i];
            nextArrival++;
            totalWait+= time - processInfo[i][1];
         }
      }
      output.printf("Average Wait: %.2f\n", (double)totalWait/(processInfo.length));
      
   }
   
   public static void priorityWithPreempt(PrintStream output, int[][] processInfo) {
      output.println("PR_withPREMP");
      int[][] readyQueue = new int[processInfo.length][4];
      int completedProcesses = 0, time = 0, currentProccess = 0, nextArrival = 0, timeIncrement = 0, totalWait = 0, readyQueuePointer = 0;
      sort(processInfo, 1);
      boolean decision = true;
      while(processInfo[0][1] != time)
         time++;
      while(completedProcesses != processInfo.length) {
         timeIncrement = 0;
         for (int i = nextArrival; i < processInfo.length && processInfo[i][1]<=time; i++) {
            readyQueue[readyQueuePointer++] = processInfo[i];
            if (currentProccess == -1 || processInfo[i][3] < readyQueue[currentProccess][3]) 
               decision = true;
            nextArrival++;
         }
         // If a scheduling decision is needed
         if (decision) {
            currentProccess = 0;
            for (int i = 1; i < readyQueuePointer; i++) {
               if ((readyQueue[currentProccess][3] == readyQueue[i][3] && readyQueue[currentProccess][0] > readyQueue[i][0])
               || readyQueue[currentProccess][3] > readyQueue[i][3] || (readyQueue[currentProccess][2] == readyQueue[i][2] && readyQueue[currentProccess][1] > readyQueue[i][1]))
                  currentProccess = i;
            }		
            decision = false;
            output.printf("%d %d\n", time, readyQueue[currentProccess][0]);
         }
         if (nextArrival == processInfo.length || (readyQueue[currentProccess][2]+time) <= processInfo[nextArrival][1]) {
            timeIncrement = readyQueue[currentProccess][2];
            readyQueue[currentProccess][2] = 0;
            completedProcesses++;
            readyQueue[currentProccess] = readyQueue[readyQueuePointer - 1];
            currentProccess = -1;
            readyQueuePointer--;
            decision = true;
         } else {
            timeIncrement = processInfo[nextArrival][1] - time;
            if (readyQueuePointer!=0)
               readyQueue[currentProccess][2] -= timeIncrement;
         }
         time += timeIncrement;
         for (int i = 0; i<readyQueuePointer; i++) {
            if (i!=currentProccess)
               totalWait += timeIncrement;
         }
      } 
      output.printf("Average Wait: %.2f\n", (double)totalWait/(processInfo.length));
   }
   
   public static void sort(int[][] processInfo, int column) {
      for (int i = 1; i < processInfo.length; i++) {
         int[] temp = processInfo[i];
         int j = i;
         while(j>0 && processInfo[j][column] < processInfo[j-1][column]) {
            processInfo[j] = processInfo[j-1];
            j--;
            processInfo[j] = temp;
         }	
      }
   }
}