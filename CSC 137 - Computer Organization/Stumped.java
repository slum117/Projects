import java.util.*;
import java.io.*;

public class Stumped
{
   public static void main(String[] args) throws FileNotFoundException
   {
      String OPcode, objectFile = "";
      File f = new File("");
      Scanner fileReader = null;
      int selectInt, programCounter = 0, temp,temp2;
      ArrayList<String> machineCode = new ArrayList<String>(); // should be memory?
      Stack<Integer> stk = new Stack<Integer>();
      int[] memory = new int[256];
      boolean halt = false;
      
      // get the file/arguments
      if (args.length > 0) {
         objectFile = args[0];
         f = new File(objectFile);
         if(f.exists()) {
            fileReader = new Scanner(f);
            fileReader.nextLine(); // skips header line
         }
         if(args.length > 1)
            selectInt = Integer.parseInt(args[1]);
      } else
         System.out.println("USAGE: java Stumped.java <object file> [integer in value]");
         
      //load the machine code into memory
      while (f.exists() && fileReader.hasNext())
      {
         machineCode.add(fileReader.next());
      }
      if(f.exists()) {
         while(!halt) {
         //System.out.println("Program Counter: " + programCounter + " Instruction: " + machineCode.get(programCounter));
         //fetch the instruction
            OPcode = machineCode.get(programCounter);
         //decode instruction and execute
         //pushi
            if(OPcode.startsWith("1") && !OPcode.equals("100")) {
               stk.push(Integer.parseInt(OPcode.substring(1),16)); 
               //System.out.println("Push " + Integer.parseInt(OPcode.substring(1),16));
            }
            //push Address
            else if(OPcode.startsWith("2") && !OPcode.equals("200")) {
               temp = memory[Integer.parseInt(OPcode.substring(1),16)];
               stk.push(temp);
            }
            //pop A
            else if(OPcode.startsWith("3") && !OPcode.equals("300")) {
               temp = stk.pop();
               memory[Integer.parseInt(OPcode.substring(1),16)] = temp;
               //System.out.println("Pop " + Integer.parseInt(OPcode.substring(1),16) +", "+ temp);               
            }
            //JMP A
            else if(OPcode.startsWith("4") && !OPcode.equals("400"))
               programCounter = Integer.parseInt(OPcode.substring(1),16)-1;
            //JZ A
            else if(OPcode.startsWith("5") && !OPcode.equals("500")) {
               if(stk.pop() == 0)
                  programCounter = Integer.parseInt(OPcode.substring(1),16)-1;
            }
            //JNZ A
            else if(OPcode.startsWith("6") && !OPcode.equals("600")) {
               if(stk.pop() != 0)
                  programCounter = Integer.parseInt(OPcode.substring(1),16)-1;
            }
            //IN
            else if(OPcode.startsWith("D")) {
               if(args.length > 1) {
                  stk.push(Integer.parseInt(args[1]));
               }
            }
            //out
            else if(OPcode.startsWith("E")) {
               System.out.println(stk.pop());
            }
            //halt
            else if(OPcode.equals("0F00"))
            {halt = !halt;}
            //do nothing
            else if(OPcode.equals("0000"))
            {}
            //push pc
            else if(OPcode.equals("0100"))
               stk.push(programCounter);
            //pop pc
            else if(OPcode.equals("0200"))
               programCounter = stk.pop() - 1;
            //LD
            else if(OPcode.equals("0300") || OPcode.equals("300")) {
               temp = stk.pop();
               stk.push(memory[temp]);
            }
            //ST
            else if(OPcode.equals("0400")) {
               temp = stk.pop();
               memory[stk.pop()] = temp;  
            }
            //DUP
            else if(OPcode.equals("0500")) {
               stk.push(stk.peek());
            }
            //DROP
            else if(OPcode.equals("0600"))
               stk.pop();
            //OVER
            else if(OPcode.equals("0700")) {
               temp = stk.pop();
               temp2 = stk.peek();
               stk.push(temp);
               stk.push(temp2);
            }
            //DNEXT
            else if(OPcode.equals("0800")) {
               temp = stk.pop();
               stk.pop();
               stk.push(temp);
            }
            //SWAP
            else if(OPcode.equals("0900")) {
               temp = stk.pop();
               temp2 = stk.pop();
               stk.push(temp);
               stk.push(temp2);
            }
            //add
            else if(OPcode.equals("F000")) {
               temp = stk.pop() + stk.pop();
               stk.push(temp);
               //System.out.println("Add " + temp);
            }
            //sub
            else if(OPcode.equals("F001")) {
               temp = stk.pop();
               stk.push(stk.pop() - temp);
            }
            //mul
            else if(OPcode.equals("F002")) {
               temp = stk.pop();
               stk.push(stk.pop() * temp);
            }
            //div
            else if(OPcode.equals("F003")) {
               temp = stk.pop();
               temp2 = stk.pop();
               if(temp != 0)
                  stk.push(temp2/temp);
               else
                  halt = !halt;
            }
            //mod
            else if(OPcode.equals("F004")) {
               temp = stk.pop();
               temp2 = stk.pop();
               if(temp != 0)
                  stk.push(temp2%temp);
               else
                  halt = !halt;
            }  
            //shl
            else if(OPcode.equals("F005")) {
               temp = stk.pop();
               temp2 = stk.pop();
               stk.push((int)(temp2 * Math.pow(2,temp))); // needs checking
            }
            //shr
            else if(OPcode.equals("F006")) {
               temp = stk.pop();
               temp2 = stk.pop();
               stk.push((int)(temp2 / Math.pow(2,temp))); // needs checking
            }
            //BAND
            else if(OPcode.equals("F007"))
               stk.push(stk.pop() & stk.pop());
            //BOR
            else if(OPcode.equals("F008"))
               stk.push(stk.pop() | stk.pop());
            //BXOR
            else if(OPcode.equals("F009"))
               stk.push(stk.pop() ^ stk.pop());
            //AND
            else if(OPcode.equals("F00A")) {
               temp = stk.pop();
               temp2 = stk.pop();
               if(temp > 0 && temp2 > 0)
                  stk.push(1);
               else
                  stk.push(0);
            }
            //OR
            else if(OPcode.equals("F00B")) {
               temp = stk.pop();
               temp2 = stk.pop();
               if(temp > 0 || temp2 > 0)
                  stk.push(1);
               else
                  stk.push(0);
            }
            //EQ
            else if(OPcode.equals("F00C")) {
               temp = stk.pop();
               temp2 = stk.pop();
               if(temp == temp2)
                  stk.push(1);
               else
                  stk.push(0);
            }
            //NE
            else if(OPcode.equals("F00D")) {
               temp = stk.pop();
               temp2 = stk.pop();
               if(temp != temp2)
                  stk.push(1);
               else
                  stk.push(0);
            }
            //GE
            else if(OPcode.equals("F00E")) {
               temp = stk.pop();
               temp2 = stk.pop();
               if(temp <= temp2)
                  stk.push(1);
               else
                  stk.push(0);
            }
            //LE
            else if(OPcode.equals("F00F")) {
               temp = stk.pop();
               temp2 = stk.pop();
               if(temp >= temp2)
                  stk.push(1);
               else
                  stk.push(0);
            }
            //GT
            else if(OPcode.equals("F010")) {
               temp = stk.pop();
               temp2 = stk.pop();
               if(temp < temp2)
                  stk.push(1);
               else
                  stk.push(0);
            }
            //LT
            else if(OPcode.equals("F011")) {
               temp = stk.pop();
               temp2 = stk.pop();
               if(temp > temp2)
                  stk.push(1);
               else
                  stk.push(0);
            }
            //NEG
            else if(OPcode.equals("F012")) {
               temp = stk.pop();
               stk.push(temp*-1);
            }   
            //BNOT
            else if(OPcode.equals("F013"))
               stk.push(~stk.pop());
            //NOT
            else if(OPcode.equals("F014")) {
               temp = stk.pop();
               if (temp > 0)
                  stk.push(0);
               else
                  stk.push(1);
            }
            programCounter++;
            
         }
      } // end of fetch/decode/execute
   }
}