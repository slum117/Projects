/*
CSC139 
Spring 2021
Assignment 4
Lum,Steven
Section 4
*/

import java.util.*;
import java.io.*;
public class MemoryManagement {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("input.txt"));
      PrintStream output = new PrintStream(new File("output.txt"));
      int pages, frameAmount, pageAccessRequestsAmount;
      pages = input.nextInt();
      frameAmount = input.nextInt();
      pageAccessRequestsAmount = input.nextInt();
      int pageAccessRequest[] = new int[pageAccessRequestsAmount];
      int frame[] = new int[frameAmount];
      input.nextLine();
      for(int i = 0; i < pageAccessRequestsAmount; i++) {
         pageAccessRequest[i] = input.nextInt();
         input.nextLine();
      }
      
      for(int i = 0; i < frame.length; i++)
         frame[i] = -1;
         
      firstInFirstOut(output, pageAccessRequest, frame);
      optimal(output, pageAccessRequest, frame);
      leastRecentlyUsed(output, pageAccessRequest, frame);
      System.out.printf("%d %d %d%n",pages, frameAmount, pageAccessRequestsAmount);
      for(int i = 0; i < pageAccessRequestsAmount; i++) {
         System.out.println(pageAccessRequest[i]);
      }
   }
   
   public static void firstInFirstOut(PrintStream output, int[] pageAccessRequest, int[] frame) {
      int unloadFrame = 0;
      int pageFault = 0;
      boolean requireUnload;
      output.println("FIFO");
      for(int i = 0; i < pageAccessRequest.length; i++) {
         requireUnload = true;
         for(int j = 0; j < frame.length; j++) {
            if(pageAccessRequest[i] == frame[j]) { // check if page already exists
               output.println("Page " + pageAccessRequest[i] + " already in Frame " + j);
               requireUnload = false;
               break;
            } else if (frame[j] == -1) { // current frame is empty
               output.println("Page " + pageAccessRequest[i] + " loaded into Frame " + j);
               frame[j] = pageAccessRequest[i];
               pageFault++;
               requireUnload = false;
               break;
            }
         }
         if(requireUnload) {
            output.println("Page " + frame[unloadFrame] + " unloaded from Frame " + unloadFrame + ", Page " + pageAccessRequest[i] + " loaded into Frame " + unloadFrame);
            frame[unloadFrame] = pageAccessRequest[i];
            unloadFrame = (unloadFrame + 1) % frame.length;
            pageFault++;
         }
      }
      output.println("Pagefaults: " + pageFault + "\n");
      emptyFrames(frame);
   }
   
   public static void optimal(PrintStream output, int[] pageAccessRequest, int[] frame) {
      int unloadFrame = 0;
      int pageFault = 0;
      int distanceValue = 0;
      int highestValue;
      boolean requireUnload, ValueFound = false;
      output.println("Optimal");
      for(int i = 0; i < pageAccessRequest.length; i ++) {
         requireUnload = true;
         highestValue = 0;
         for(int j = 0; j < frame.length; j++) {
            if(pageAccessRequest[i] == frame[j]) { // check if page already exists
               output.println("Page " + pageAccessRequest[i] + " already in Frame " + j);
               requireUnload = false;
               break;
            } else if (frame[j] == -1) { // current frame is empty
               output.println("Page " + pageAccessRequest[i] + " loaded into Frame " + j);
               frame[j] = pageAccessRequest[i];
               pageFault++;
               requireUnload = false;
               break;
            }         
         }
         
         if(requireUnload) {
            for(int j = 0; j < frame.length; j++) {
               ValueFound = false;
               for(int k = i+1; k < pageAccessRequest.length; k++) {
                  if(frame[j] == pageAccessRequest[k]) {
                     distanceValue = k-i;
                     ValueFound = true;
                     //break;
                  }
               }
               if(!ValueFound) {
                  unloadFrame = j;
                  break;
               } else
                  if(distanceValue > highestValue)
                     unloadFrame = j;
            }
            output.println("Page " + frame[unloadFrame] + " unloaded from Frame " + unloadFrame + ", Page " + pageAccessRequest[i] + " loaded into Frame " + unloadFrame);
            frame[unloadFrame] = pageAccessRequest[i];
            pageFault++;
         }
      }
      output.println("Pagefaults: " + pageFault + "\n");
      emptyFrames(frame);
   }
   
   public static void leastRecentlyUsed(PrintStream output, int[] pageAccessRequest, int[] frames) {
      int unloadFrame = 0;
      int pageFault = 0;
      boolean requireUnload;
      int highestValue = 0;
      int[][] frame = new int[frames.length][2];
      output.println("Least Recently Used");
      for(int i = 0; i < frames.length; i++) {
         frame[i][0] = frames[i];
         frame[i][1] = 0;
      }
      for(int i = 0; i < pageAccessRequest.length; i ++) {
         highestValue = 0;
         requireUnload = true;
         for(int j = 0; j < frame.length; j++) {
            if(pageAccessRequest[i] == frame[j][0]) { // check if page already exists
               output.println("Page " + pageAccessRequest[i] + " already in Frame " + j);
               requireUnload = false;
               incTime(frame, j);
               break;
            } else if (frame[j][0] == -1) { // current frame is empty
               output.println("Page " + pageAccessRequest[i] + " loaded into Frame " + j);
               frame[j][0] = pageAccessRequest[i];
               incTime(frame, j);
               pageFault++;
               requireUnload = false;
               break;
            }    
         }
         if(requireUnload) {
            for(int j = 0; j < frame.length; j++) {
               if (frame[j][1] > highestValue) {
                  highestValue = frame[j][1];
                  unloadFrame = j;
               }
            }
            output.println("Page " + frame[unloadFrame][0] + " unloaded from Frame " + unloadFrame + ", Page " + pageAccessRequest[i] + " loaded into Frame " + unloadFrame);
            frame[unloadFrame][0] = pageAccessRequest[i];
            pageFault++;
            incTime(frame, unloadFrame);
         }
      }
      output.println("Pagefaults: " + pageFault + "\n");
      emptyFrames(frames);
   }
   
   public static void emptyFrames(int[] frame) {
      for(int i = 0; i < frame.length; i++)
         frame[i] = -1;
   }
   
   
   public static void incTime(int[][] frame, int curFrame)
   {
      for(int i = 0; i < frame.length; i++) {
         frame[i][1] = frame[i][1] + 1;
      }
      frame[curFrame][1] = 0;
   }
}