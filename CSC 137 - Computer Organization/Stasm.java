import java.util.*;
import java.io.*;

public class Stasm
{
   public static void main(String[] args) throws FileNotFoundException
   {
      HashMap instructionMnemonic = new HashMap(59); // contains keys:values to translate assembly to hex
      HashMap labelLocations = new HashMap(256); // stores the address of labels
      HashMap labelValues = new HashMap(256);
      instructionMnemonic.put("PUSHI", 1000);
      instructionMnemonic.put("ADD","F000");
      instructionMnemonic.put("NOP","0000");
      instructionMnemonic.put("HALT","0F00");
      instructionMnemonic.put("PUSHPC","0100");
      instructionMnemonic.put("POPPC","0200");
      instructionMnemonic.put("LD","0300");
      instructionMnemonic.put("ST","0400");
      instructionMnemonic.put("DUP","0500");
      instructionMnemonic.put("DROP","0600");
      instructionMnemonic.put("OVER","0700");
      instructionMnemonic.put("DNEXT","0800");
      instructionMnemonic.put("SWAP","0900");
      instructionMnemonic.put("PUSH","2000");
      instructionMnemonic.put("POP","3000");
      instructionMnemonic.put("JMP","4000");
      instructionMnemonic.put("JZ","5000");
      instructionMnemonic.put("JNZ","6000");
      instructionMnemonic.put("IN","D000");
      instructionMnemonic.put("OUT","E000");
      instructionMnemonic.put("SUB","F001");
      instructionMnemonic.put("MUL","F002");
      instructionMnemonic.put("DIV","F003");
      instructionMnemonic.put("MOD","F004");
      instructionMnemonic.put("SHL","F005");
      instructionMnemonic.put("SHR","F006");
      instructionMnemonic.put("BAND","F007");
      instructionMnemonic.put("BOR","F008");
      instructionMnemonic.put("BXOR","F009");
      instructionMnemonic.put("AND","F00A");
      instructionMnemonic.put("OR","F00B");
      instructionMnemonic.put("EQ","F00C");
      instructionMnemonic.put("NE","F00D");
      instructionMnemonic.put("GE","F00E");
      instructionMnemonic.put("LE","F00F");
      instructionMnemonic.put("GT","F010");
      instructionMnemonic.put("LT","F011");
      instructionMnemonic.put("NEG","F012");
      instructionMnemonic.put("BNOT","F013");
      instructionMnemonic.put("NOT","F014");
      ArrayList<String> lines = new ArrayList<String>(); // holds assembly code
      ArrayList<String> directives = new ArrayList<String>(); // holds assembly-to-hex code
      ArrayList<String> labels = new ArrayList<String>(); // holds label strings for processing
      boolean listFlag = false;
      File sourceFile = new File("");
      Scanner fileReader = null;
      String outputFile;
      int address = 0;
      
      // get the file/arguments
      if (args.length >= 2) {
         sourceFile = new File(args[0]);
         if(sourceFile.exists()) 
            fileReader = new Scanner(sourceFile);
         outputFile = args[1];
         if(args.length > 2)
            if(args[2].equals("-l")) 
               listFlag = true;
      } else {
         System.out.println("USAGE: java Stasm.java <source file> <object file> [-l]");
         System.out.println("             -l : print listing to standard error");
      }
      
      // load mnemonics into arraylist
      while (sourceFile.exists() && fileReader.hasNext())
      {
         lines.add(fileReader.nextLine());
      }
      
      //find and store labels
      for(int i = 0; i < lines.size(); i++) {
         if(lines.get(i).contains(":")) {
            String[] strg = lines.get(i).split("\\W+");
            labels.add(strg[0]);
            labelLocations.put(strg[0],i);
            labelValues.put(i,null); 
            if(lines.get(i).contains("DW")) {
               address++;
               for(int j = 0; j < strg.length; j++)
                  if(strg[j].equals("DW")) {
                     labelValues.put(i,strg[j + 1]);
                     break;
                  }
            }     
         }
      }
      for(int i = 0; i < lines.size(); i++) {
         boolean operandFlag = false;
         //System.out.println(lines.get(i));
         if(!lines.get(i).contains(":")) {
            String[] splitLine = lines.get(i).trim().split("\\W");
            //System.out.println(Arrays.toString(splitLine));
            if(instructionMnemonic.containsKey(splitLine[0])) {
               int operatorDecVal = Integer.parseInt(instructionMnemonic.get(splitLine[0]).toString(),16);
               int operandDecVal = 0;
               if(splitLine.length >= 2) {
                  if(!splitLine[1].startsWith(";") && !splitLine[1].equals("")) {
                     for(int j = 0; j < labels.size(); j++)
                        if(labels.get(j).equals(splitLine[1])) {
                           if(labelValues.get(labelLocations.get(labels.get(j))) != null || true) {
                              operandDecVal = Integer.parseInt(labelLocations.get(labels.get(j)).toString());
                              operandFlag = true;
                              break;
                           } 
                           //System.out.println("Label: " + labels.get(j) + " Location: " + labelLocations.get(labels.get(j)) + " Value: " + labelValues.get(labelLocations.get(labels.get(j))));
                        }
                     if(!operandFlag)
                        operandDecVal = Integer.parseInt(splitLine[1]);
                     //System.out.println(splitLine[1]);
                  }
               }
               if(operatorDecVal+operandDecVal == 3840)
                  directives.add("0F00");
               else if(operatorDecVal+operandDecVal == 0)
                  directives.add("0000");
               else if(operatorDecVal+operandDecVal == 100)
                  directives.add("0100"); 
               else if(operatorDecVal+operandDecVal == 200)
                  directives.add("0200");
               else if(operatorDecVal+operandDecVal == 300)
                  directives.add("0300");
               else if(operatorDecVal+operandDecVal == 400)
                  directives.add("0400");
               else if(operatorDecVal+operandDecVal == 500)
                  directives.add("0500");
               else if(operatorDecVal+operandDecVal == 600)
                  directives.add("0600");
               else if(operatorDecVal+operandDecVal == 700)
                  directives.add("0700");
               else if(operatorDecVal+operandDecVal == 800)
                  directives.add("0800");
               else if(operatorDecVal+operandDecVal == 900)
                  directives.add("0900");
               
               else   
                  directives.add(Integer.toHexString(operatorDecVal+operandDecVal).toUpperCase());
               address++;
            }
         }
      }
      if(listFlag) {
         System.out.println("*** LABEL LIST ***");
         for(int i = 0; i < labels.size(); i++) {
            String[] x = labels.get(i).split("\\W+");
            System.out.printf("%-7s        %3d%n", x[0], labelLocations.get(x[0]));
         //System.out.println(labelValues.get(labelLocations.get(x[0])));
         }
         System.out.println("\n*** MACHINE PROGRAM ***");
         int count = 0;
         int count2 = 0;
         /*
         for(int i = 0; i < address;i++) {
            for(int j = 0; j < labels.size(); j++) {
               if((int)labelLocations.get(labels.get(j)) == i) {
                  if(labelValues.get(i) != null) {
                     System.out.printf("%03d: %-8s %s: %s%n",i,labelValues.get(i), labels.get(j),labelValues.get(i));
                     count++;
                  } else {
                  //System.out.printf("%03d: %s%n",i,directives.get(count));
                  //count++;
                  }
                  break;
               }
            }
         
            System.out.printf("%03d: %-8s %s%n",i+count,directives.get(i-count),"---");
         }
         */
      }
      try {
         PrintWriter writer = new PrintWriter(args[1],"UTF-8");
         writer.println("V2.0 raw");
         for(int i = 0; i < directives.size(); i++) {
            writer.println(directives.get(i));
         }
         writer.close();
         //outputFile.close();
      } catch(Exception IOException) {
         //System.out.println("Error writing to file.");
      }
   
   }
}