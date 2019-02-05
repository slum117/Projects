// Administrator Login     Student Login
// Name: group             ID number: 1
// Last name: account
// ID number: 147          

import java.io.*;
import java.util.*;
public class Driver implements java.io.Serializable
{
   public static void main(String[] args)
   {
      boolean tFiles = true;;
      //Administrator admin = null;
      Administrator root = null;
      try
      {
         FileInputStream file = new FileInputStream("admin.ser");
         ObjectInputStream in = new ObjectInputStream(file);
         root = (Administrator)in.readObject();
         in.close();
         file.close();
         tFiles = true;
      } catch (IOException ex) {
         System.out.println("System could not find the list of administrators");
         tFiles = false;
      } catch (ClassNotFoundException ex) {
         System.out.println("Administrator not found");
         tFiles = false;
      }
      Person p = new Person("Admin","A","358","","");
      root.add(p);
      //uses listOfStudents.ser to fill studentDatabase
      Students studentDatabase = null;
      try
      {
         FileInputStream file = new FileInputStream("listOfStudents.ser");
         ObjectInputStream in = new ObjectInputStream(file);
         studentDatabase = (Students)in.readObject();
         in.close();
         file.close();
         tFiles = true;
      } catch (IOException ex) {
         System.out.println("System could not find the list of students");
         tFiles = false;
      } catch (ClassNotFoundException ex) {
         System.out.println("Students.Class not found");
         tFiles = false;
      }
      //use serializable to fill ArrayList<Item>
      ArrayList<Item> itemDatabase = null;
      try
      {
         FileInputStream file = new FileInputStream("items.ser");
         ObjectInputStream in = new ObjectInputStream(file);
         itemDatabase = (ArrayList<Item>)in.readObject();
         in.close();
         file.close();
         tFiles = true;
      } catch (IOException ex) {
         System.out.println("System could not find the list of items");
         tFiles = false;
      } catch (ClassNotFoundException ex) {
         System.out.println("Item.Class not found");
         tFiles = false;
      }
      boolean logOut = false;
      while(!logOut)
      {
         if(tFiles) {
         //makes a scanner for receiving user input
         //asks the user if they are a student/administrator/ or they want to exit
            Scanner console = new Scanner(System.in);
            System.out.println("Please select a user [Student] or [Administrator]");
            System.out.println("or enter \"Exit\" to exit the program");
            String userType = console.nextLine();
            boolean isStudent = false, isAdmin = false,exit = false, exitCondition = false;;
            do{
               if(userType.equalsIgnoreCase("Student")) {
                  isStudent = true;
                  exitCondition = true;
                  exit = false;
               } else if(userType.equalsIgnoreCase("Administrator")) {
                  isAdmin = true;
                  exitCondition = true;
                  exit = false;
               } else if(userType.equalsIgnoreCase("exiT")) {
                  System.out.println("Exitting Program");
                  exitCondition = true;
                  exit = true;
                  logOut = true;
               } else {
                  System.out.println(userType + " is not recognized. Please enter [Student] or [Administrator].");
                  userType = console.nextLine();
               }
            }while(!exitCondition);
         
         //asks for id number if student or admin is used
         
            if(!exit){
               boolean validAdmin = false;
               Student currentStudent = null;
               Person currentAdmin = null;
            // asks user for their ID number and makes currentStudent reference the student if the student is found
               if(isStudent) {
                  System.out.print("Please enter your ID number: ");
                  while(!console.hasNextInt())
                  {
                     System.out.print("Please enter your ID number.");
                     console.next();
                  }
               
                  String idNumber = Integer.toString(console.nextInt());
                  try{         
                     currentStudent = studentDatabase.searchStudents(idNumber);
                     if(currentStudent != null) {
                        System.out.println("Welcome " + currentStudent.getPerson().getName());
                     } else
                        System.out.println("Invalid ID number");
                  } catch(NullPointerException ex) {
                     System.out.println("Caught a null pointer");
                  }
               // asks user for name,last name, and id to create a person which is used to search for the person in administrator   
               } else if (isAdmin) {
                  currentAdmin = createPerson(console,true,studentDatabase);
                  validAdmin = root.search(currentAdmin);
                  if(validAdmin) {
                     System.out.println("Welcome Admin " + currentAdmin.getName());
                     currentAdmin = root.getPerson(root.getCallNumber());
                  } else
                     System.out.println("Invalid User");
               } else
                  System.out.println("You shouldn't be here"); // end of isAdmin
               ArrayList<Item> studentCheckOut;
            //if the user logs in as a administrator
               if(currentAdmin != null && currentStudent == null && validAdmin)
               {
                  adminMenu(console,itemDatabase,studentDatabase);
               //if the user logs in as a student
               } else if(currentStudent != null && currentAdmin == null)
               {
                  if(currentStudent.getCheckedOut().size() != 0) // still needs to be implemented with ser file
                     returnTheBooks(console,currentStudent,itemDatabase);
                  studentCheckOut = studentMenu(console,itemDatabase);
                  for(int i = 0; i < studentCheckOut.size();i++) {
                     System.out.println(studentCheckOut.get(i).getTitle());
                     studentCheckOut.get(i).setStatus(false);
                  }
                  for(int i = 0; i < studentCheckOut.size();i++)
                     currentStudent.addCheckedOut(studentCheckOut.get(i));
               }
            // serializes all data
               serializeAll(root,studentDatabase,itemDatabase);
           
            
            } // end of if(!exit)
         } // end of if(tFiles)
         else
            System.out.println("Error loading files.");
      
      } // end of
      //System.out.println("Closing Program");
   }
   
   //methods involving student
      //returns a list of items that the student is checking out
      //construct after serializing
   public static void returnTheBooks(Scanner console, Student currentStudent,ArrayList<Item> itemDatabase)
   {
      boolean yesOrNo = false;
      boolean yes = false;
      String retItem = "";
      int chooseNumber = -1;
      do{
         System.out.println("Do you want to return any of your items? [Yes] [No]");
         retItem = console.next();
         if(retItem.equalsIgnoreCase("yes") || retItem.equalsIgnoreCase("no"))
            if(retItem.equalsIgnoreCase("yes"))
            {
               yesOrNo = true;
               yes = true;
            } else 
            {
               yesOrNo = true;
            }
         else
            while(!yesOrNo)
            {
               System.out.println("Please answer with [Yes] or [No].");
               retItem = console.next();
               if(retItem.equalsIgnoreCase("yes") || retItem.equalsIgnoreCase("no"))
                  if(retItem.equalsIgnoreCase("yes"))
                  {
                     yesOrNo = true;
                     yes = true;
                  } else 
                  {
                     yesOrNo = true;
                  }
            }
      } while(!yesOrNo);
      if(yes)
      {  
         String removeMore = "yes";
         while(currentStudent.getCheckedOut().size() != 0 && removeMore.equalsIgnoreCase("yes"))
         {
            for(int i = 0;i < currentStudent.getCheckedOut().size();i++)
               System.out.println(i+1+". "+currentStudent.getCheckedOut().get(i).getTitle() +" by " + currentStudent.getCheckedOut().get(i).getAuthor());
            System.out.println("Please select a number between 1 and " + currentStudent.getCheckedOut().size() + " to select the book, or 0 to exit.");
            while(!console.hasNextInt())
            {
               System.out.println("Please enter a number");
               console.next();
            }
            chooseNumber = console.nextInt() - 1;
            boolean libraryRemoved = false;
            if(chooseNumber >= 0 && chooseNumber < currentStudent.getCheckedOut().size())
            {
               
               for(int i = 0; i < itemDatabase.size() && !libraryRemoved; i++)
                  //if the item has not been deleted
                  if(itemDatabase.get(i).equals(currentStudent.getCheckedOut().get(chooseNumber))) 
                  {
                     itemDatabase.get(i).setStatus(true);
                     System.out.println(currentStudent.getCheckedOut().get(chooseNumber).getTitle() + " has been returned to the library.");
                     libraryRemoved = false;
                  }
                  else if(!itemDatabase.get(i).equals(currentStudent.getCheckedOut().get(chooseNumber)))
                  {
                     System.out.println(currentStudent.getCheckedOut().get(chooseNumber).getTitle() + " has been removed from the library.");
                     currentStudent.removeCheckedItem(currentStudent.getCheckedOut().get(chooseNumber));
                     libraryRemoved = true;
                  }
            
               if(currentStudent.getCheckedOut().size() == 0) {
                  System.out.println("You do not have any items to return.");
                  removeMore = "no";
               } else
               {
                  System.out.println("Do you want to remove more items? ");
                  removeMore = console.next();
                  if(!removeMore.equalsIgnoreCase("no") && !removeMore.equalsIgnoreCase("yes"))
                  {
                     boolean invalid = true;
                     while(invalid)
                     {
                        System.out.println("Please enter [Yes] or [No]");
                        removeMore = console.next();
                        if(removeMore.equalsIgnoreCase("yes") || removeMore.equalsIgnoreCase("no"))
                           invalid = false;
                     }
                  }
               }
            } else if(chooseNumber >= currentStudent.getCheckedOut().size()) {
               System.out.println("Invalid Selection. Item #" + (chooseNumber+1) + " does not exist. Please select another number.");
            } else
               break;
         }
      }
   }
   public static ArrayList<Item> studentMenu(Scanner console, ArrayList<Item> itemDatabase)
   {
      String userChoice = "";
      ArrayList<Item> checkingOut = new ArrayList<Item>();
      Item currentCheckOut = null;
      do{
         currentCheckOut = null;
         System.out.println("Studentc Menu: ");
         studentMenuOptions();
         userChoice = console.next();
         if(userChoice.equalsIgnoreCase("author") || userChoice.equalsIgnoreCase("1")) {
            currentCheckOut = searchByAuthor(console, itemDatabase); // set currentCheckOut to false so that it may not be picked up again
         } else if(userChoice.equalsIgnoreCase("subject") || userChoice.equalsIgnoreCase("2")) {
            currentCheckOut = searchBySubject(console,itemDatabase);
         } else if(userChoice.equalsIgnoreCase("keyword") || userChoice.equalsIgnoreCase("3")) {
            currentCheckOut = searchByKeyword(console,itemDatabase);  
         } else if(userChoice.equalsIgnoreCase("exit") || userChoice.equalsIgnoreCase("4")) {
            userChoice = "exit";
         } else
            System.out.println(userChoice + " is not recognized.");
         if(currentCheckOut != null && currentCheckOut.getStatus())
         {
            System.out.println(currentCheckOut.getTitle() + " added");
            checkingOut.add(currentCheckOut);
         } 
         if(currentCheckOut != null) {
            if(!currentCheckOut.getStatus())
               System.out.println(currentCheckOut.getTitle() + " is unavailable."); // need to test after serializing
         }
      }while(!userChoice.equalsIgnoreCase("exit"));
      return checkingOut;
   }
   
   public static void studentMenuOptions()
   {
      System.out.println(" Search by: ");
      System.out.println(" 1. Author");
      System.out.println(" 2. Subject");
      System.out.println(" 3. Keyword");
      System.out.println(" 4. Exit");
   } 
   public static Item searchByKeyword(Scanner console, ArrayList<Item> itemDatabase)
   {
      System.out.println("Please enter a keyword: ");
      console.nextLine();
      String keyWord = console.nextLine();
      ArrayList<Item> matchingKeyword = new ArrayList<Item>();
      System.out.println("Searching for " + keyWord + " in the title");
      for(int i = 0; i < itemDatabase.size(); i++)
         if((itemDatabase.get(i).getTitle().toLowerCase().contains(keyWord.toLowerCase()) || itemDatabase.get(i).getSubject().toLowerCase().contains(keyWord.toLowerCase()))) {
            matchingKeyword.add(itemDatabase.get(i));
         }
      for(int i = 0; i < matchingKeyword.size();i++)
         System.out.println(i+1+". "+matchingKeyword.get(i));
      int chooseNumber = 0;
      if(matchingKeyword.size() > 0)
      {
         System.out.println("Please select a number to choose the book you want. Enter 0 if your book is not listed");
         while(!console.hasNextInt() && matchingKeyword.size() != 0) {
            System.out.println("Please select a number between 1 and "+matchingKeyword.size()+" to choose your book. Enter 0 if your book is not listed.");
            console.next();
         }
         chooseNumber = console.nextInt()-1;
         while(chooseNumber > matchingKeyword.size()-1 && matchingKeyword.size() != 0) {
            System.out.println("Please choose a number between 1 and " + matchingKeyword.size() + " to select your book. Enter 0 if your book is not listed.");
            while(!console.hasNextInt()) {
               System.out.println("Please select a number");
               console.next();
            }
            chooseNumber = console.nextInt()-1;
         }
         if(chooseNumber < 0)
            return null;
      } else {
         System.out.println("No results for " + keyWord);
         return null;  
      }
      return matchingKeyword.get(chooseNumber);
   }
      // searches for subject and lists all books with said subject and allows for user to select one
   public static Item searchBySubject(Scanner console, ArrayList<Item> itemDatabase)
   {
      String input = "";
      String sName;
      ArrayList<Item> subjectReferenced;
      do{
         System.out.print("Please enter the subject: ");
         console.nextLine();
         sName = console.nextLine();
         System.out.println(sName+" is this correct? [Yes] [No]");
         input = console.next();
      } while (!input.equalsIgnoreCase("yes"));
      subjectReferenced = wantedSubject(itemDatabase,sName);
      for(int i = 0; i < subjectReferenced.size();i++)
         System.out.println(i+1+". "+subjectReferenced.get(i));
      int chooseNumber = 0;
      if(subjectReferenced.size() != 0)
      {
         System.out.println("Please select a number to choose the book you want. Enter 0 if your book is not listed");
         while(!console.hasNextInt() && subjectReferenced.size() != 0) {
            System.out.println("Please select a number between 1 and "+subjectReferenced.size()+" to choose your book. Enter 0 if your book is not listed.");
            console.next();
         }
         chooseNumber = console.nextInt()-1;
         while(chooseNumber > subjectReferenced.size()-1 && subjectReferenced.size() != 0) {
            System.out.println("Please choose a number between 1 and " + subjectReferenced.size() + " to select your book. Enter 0 if your book is not listed.");
            while(!console.hasNextInt()) {
               System.out.println("Please select a number");
               console.next();
            }
            chooseNumber = console.nextInt()-1;
         }
         if(chooseNumber < 0)
            return null;
      } else {
         System.out.println("No results for " + sName);
         return null;
      }
      System.out.println("Adding " + subjectReferenced.get(chooseNumber).getTitle() + " to check out list");
      return subjectReferenced.get(chooseNumber);
   }
   public static Item searchByAuthor(Scanner console,ArrayList<Item> itemDatabase)
   {
      String input = "";
      String aName;
      do{
         System.out.print("Please enter the author's name: ");
         console.nextLine();
         aName = console.nextLine();
         System.out.println(aName + " is this correct? [Yes] [No]");
         input = console.next();
      }while(!input.equalsIgnoreCase("yes"));
      sortByAuthor(itemDatabase);
      ArrayList<Item> matchingAuthors = authors(aName,itemDatabase);
      for(int i = 0; i < matchingAuthors.size();i++)
         System.out.println(i+1+". "+matchingAuthors.get(i));
      
      int chooseNumber = 0;
      if(matchingAuthors.size() != 0)
      {
         System.out.println("Please select a number to choose the book you want. Enter 0 if your book is not listed");
         while(!console.hasNextInt() && matchingAuthors.size() != 0) {
            System.out.println("Please select a number between 1 and "+matchingAuthors.size()+" to choose your book. Enter 0 if your book is not listed.");
            console.next();
         }
         chooseNumber = console.nextInt()-1;
         while(chooseNumber > matchingAuthors.size()-1 && matchingAuthors.size() != 0) {
            System.out.println("Please choose a number between 1 and " + matchingAuthors.size() + " to select your book. Enter 0 if your book is not listed.");
            while(!console.hasNextInt()) {
               System.out.println("Please select a number");
               console.next();
            }
            chooseNumber = console.nextInt()-1;
         }
         if(chooseNumber < 0)
            return null;
      } else {
         System.out.println("No results for " + aName);
         return null;
      }
      return matchingAuthors.get(chooseNumber);
   }
               
   
   //methods involving admin
   // has the user input their information in order to create 
   // person object to search for administrator
   public static void adminMenuOptions()
   {
      System.out.println("Admin Menu: ");
      System.out.println("1. Search for Item  ");
      System.out.println("2. Search for Student");
      System.out.println("3. Add Item");
      System.out.println("4. Add Student");
      System.out.println("5. Exit");
   }
   public static void createStudent(Scanner console, Students studentDatabase)
   {
      Person newPerson = createPerson(console,false,studentDatabase);
      Student newStudent = new Student(newPerson);
      studentDatabase.add(newStudent);
   }
   public static void adminMenu(Scanner console, ArrayList<Item> itemDatabase, Students studentDatabase)
   {
      String userChoice = "";
      userChoice = console.nextLine();
      do{
         adminMenuOptions();
         userChoice = console.nextLine();
         Item currentItem = null;
         String wantToRemoveItem = "yes";
         
         // admin searches for item
         if(userChoice.equalsIgnoreCase("Search for item") || userChoice.equalsIgnoreCase("1")) {
            do{
               studentMenuOptions();
               userChoice = console.next();
               if(userChoice.equalsIgnoreCase("author") || userChoice.equalsIgnoreCase("1")) {
                  currentItem = searchByAuthor(console, itemDatabase); // set currentCheckOut to false so that it may not be picked up again
               } else if(userChoice.equalsIgnoreCase("subject") || userChoice.equalsIgnoreCase("2")) {
                  currentItem = searchBySubject(console,itemDatabase);
               } else if(userChoice.equalsIgnoreCase("keyword") || userChoice.equalsIgnoreCase("3")) {
                  currentItem = searchByKeyword(console,itemDatabase);  
               } else if(userChoice.equalsIgnoreCase("exit") || userChoice.equalsIgnoreCase("4")) {
                  userChoice = "exit";
                  console.nextLine();
               } else
                  System.out.println(userChoice + " is not recognized.");
               if(currentItem != null)
               {
                  System.out.println("Do you want to remove " + currentItem.getTitle() + " by " + currentItem.getAuthor()+ " [Yes] [No]");
                  wantToRemoveItem = console.next();
                  if(wantToRemoveItem.equalsIgnoreCase("yes"))
                  {
                     itemDatabase.remove(currentItem);
                     System.out.println(currentItem.getTitle() + " by " + currentItem.getAuthor() + " has been removed.");
                     System.out.println("Do you want to remove more items? [Yes] [No]");
                     wantToRemoveItem = console.next();
                     if(wantToRemoveItem.equalsIgnoreCase("no"))
                     {
                        userChoice = "exit";
                        console.nextLine();
                        currentItem = null;
                     }
                     else
                     {
                        currentItem = null;
                        console.nextLine();
                     }
                  }
               }
            } while(!userChoice.equalsIgnoreCase("exit"));
            userChoice = "3";
            
         } else if(userChoice.equalsIgnoreCase("Add Item") || userChoice.equalsIgnoreCase("3")) {
            addItem(console,itemDatabase);
         } else if(userChoice.equalsIgnoreCase("Add Student") || userChoice.equalsIgnoreCase("4")) {
            createStudent(console,studentDatabase);
         } else if(userChoice.equalsIgnoreCase("Search for Student") || userChoice.equalsIgnoreCase("2")) {
            searchStudent(console,studentDatabase);
         } else if(userChoice.equalsIgnoreCase("exit") || userChoice.equalsIgnoreCase("5")) {
            userChoice = "exit";
         } else if(userChoice.equalsIgnoreCase("")){
         } else
            System.out.println(userChoice + " is not recognized."); 
      }while(!userChoice.equalsIgnoreCase("exit"));
   }
   public static void removeStudent(String yesOrNo,Students studentDatabase, Student pendingStudent)
   {
      if(yesOrNo.equalsIgnoreCase("yes"))
      {
         System.out.println(pendingStudent.getPerson().getName() + " " + pendingStudent.getPerson().getLastName() + " has been removed.");
         studentDatabase.remove(pendingStudent);
      }
   }
            
   public static void searchStudent(Scanner console, Students studentDatabase)
   {
      String yesOrNo, idNumber, otherIdentifier,searchMore = "";
      boolean validAnswer = false;
      int chooseNumber = 0;
      
      // asks if admin knows user ID
      do{
         System.out.println("Do you know the student's ID number?");
         yesOrNo = console.next();
         console.nextLine();
         if(yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("no"))
            validAnswer = true;
         else
            while(!validAnswer)
            {
               System.out.println("Enter [Yes] or [No].");
               yesOrNo = console.next();
               console.nextLine();
               if(yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("no"))
                  validAnswer = true;
            }
         if(yesOrNo.equalsIgnoreCase("yes")){
            System.out.println("Student ID number: ");
            while(!console.hasNextInt()) {
               System.out.println("PLease enter an Integer");
               console.nextLine();
            }
            idNumber = Integer.toString(console.nextInt());
            Student pendingStudent = studentDatabase.searchStudents(idNumber);
            console.nextLine();
         
            validAnswer = false;
         // asks if this user is to be removed
            if(pendingStudent != null)
            {
               System.out.println(pendingStudent);
               System.out.println("Do you want to remove this user?");
               yesOrNo = console.next();
               console.nextLine();
               if(yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("no")) {
                  validAnswer = true;
                  removeStudent(yesOrNo,studentDatabase, pendingStudent);
               } else
                  while(!validAnswer)
                  {
                     System.out.println("Enter [Yes] or [No].");
                     yesOrNo = console.next();
                     console.nextLine();
                     if(yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("no"))
                     {
                        validAnswer = true;
                        removeStudent(yesOrNo,studentDatabase, pendingStudent);
                     }
                  }
            } else
               System.out.println("ID #" + idNumber + " does not exist");
         // if admin doesn't know user's ID number
         } else {
         // has the admin input information they know about the student
            validAnswer = false;
            System.out.println("Do you know the students name and or last name, email, or phone?");
            System.out.println("[Name] [Last name] [Name and last name] [Email] [Phone] [None]");
            otherIdentifier = console.nextLine();
            if(otherIdentifier.equalsIgnoreCase("none") || otherIdentifier.equalsIgnoreCase("name") ||otherIdentifier.equalsIgnoreCase("last name") ||otherIdentifier.equalsIgnoreCase("name and last name") || otherIdentifier.equalsIgnoreCase("email") || otherIdentifier.equalsIgnoreCase("phone"))
            {
               validAnswer = true;
            } else {
               while(!validAnswer)
               {
                  System.out.println("Please enter [Name] [Last name] [Name and last name] [Email] [Phone] or [None] without the brackets");
                  otherIdentifier = console.nextLine();
                  if(otherIdentifier.equalsIgnoreCase("none") || otherIdentifier.equalsIgnoreCase("name") ||otherIdentifier.equalsIgnoreCase("last name") ||otherIdentifier.equalsIgnoreCase("name and last name") || otherIdentifier.equalsIgnoreCase("email") || otherIdentifier.equalsIgnoreCase("phone"))
                  {
                     validAnswer = true;
                  }
               }
            }  
            // returns arraylist of students matching the input criteria
            ArrayList<Student> matchingStudents = new ArrayList<Student>();
            if(otherIdentifier.equalsIgnoreCase("name")) {
               matchingStudents = studentDatabase.searchStudentsByName(console);
            } else if(otherIdentifier.equalsIgnoreCase("last name")) {
               matchingStudents = studentDatabase.searchByLastName(console);
            } else if(otherIdentifier.equalsIgnoreCase("name and last name")) {
               matchingStudents = studentDatabase.searchByBothNames(console);
            } else if(otherIdentifier.equalsIgnoreCase("email")) {
               matchingStudents = studentDatabase.searchByEmail(console);
            } else if(otherIdentifier.equalsIgnoreCase("phone")) {
               matchingStudents = studentDatabase.searchByPhone(console);
            } else if(otherIdentifier.equalsIgnoreCase("none")) {
            
            } else
               System.out.println(otherIdentifier + " invalid option.");
            
            // if students are found matching the criteria, then the system asks if the user wants to remove any of the listed students
            if(matchingStudents.size() > 0) {
               for(int i = 0; i < matchingStudents.size();i++)
                  System.out.println(i+1+" " + matchingStudents.get(i));
               System.out.println("Do you want to remove any of these students? [Yes] [No]");
               yesOrNo = console.next();
               validAnswer = yesOrNo.equalsIgnoreCase("no") || yesOrNo.equalsIgnoreCase("yes");
               while(!validAnswer)
               {
                  System.out.println("Please enter [Yes] or [No]");
                  yesOrNo = console.next();
                  validAnswer = yesOrNo.equalsIgnoreCase("no") || yesOrNo.equalsIgnoreCase("yes");
               }
               console.nextLine();
               if(yesOrNo.equalsIgnoreCase("yes"))
               {
                  System.out.println("Please enter one of the numbers to the left of the student's name or enter [0] to exit");
                  while(!console.hasNextInt())
                  {
                     System.out.println("Enter a number to the left of the student name or [0] to exit.");
                     console.next();
                  }
                  chooseNumber = console.nextInt() - 1;
                  if(chooseNumber >= 0) {
                     removeStudent(yesOrNo,studentDatabase,matchingStudents.get(chooseNumber));
                     yesOrNo = "";
                  }
               }
               // if there are no students that match the criteria
            } else if (matchingStudents.size() == 0) {
            } else
               System.out.println("No results found.");
         }
         
         // asks the user if they want to search for more students
         // unless no more students exist in database
         if(studentDatabase.sortID().size() == 0) {
            System.out.println("All students have been deleted. Please add more so you can delete them later.");
            searchMore = "no";
         } else {
            System.out.println("Do you want to search for more students? [Yes] [No]");
            searchMore = console.next();
            boolean invalidAnswer = searchMore.equalsIgnoreCase("yes") || searchMore.equalsIgnoreCase("no");
            while(!invalidAnswer)
            {
               System.out.println("Please enter [Yes] or [No].");
               searchMore = console.next();
               invalidAnswer = searchMore.equalsIgnoreCase("yes") || searchMore.equalsIgnoreCase("no");
                  
            }
            console.nextLine();
         }
      }while(searchMore.equalsIgnoreCase("yes"));
   } 
  
   public static Person createPerson(Scanner console,boolean loggingIn,Students studentDatabase) {
      String correct = "";
      String name,lastName,idNumber,email = "",phone = "";
      do
      {
         System.out.print("Name: ");
         name = console.next();
         System.out.print("Last name: ");
         lastName = console.next();
         System.out.print("ID number: ");
         while(!console.hasNextInt())
         {
            System.out.print("Please enter an integer: ");
            console.next();
         }
         boolean validID = false;
         idNumber = Integer.toString(console.nextInt());
         /*
         System.out.println(name + " " + lastName + " " + idNumber + " is this correct? [Yes] or [No]");
               
         correct = console.next();
         boolean invalidInput = false;
         if(correct.equalsIgnoreCase("yes"))
            invalidInput = false;
         else if(correct.equalsIgnoreCase("no"))
            invalidInput = false;
         else
            invalidInput = true;
         while(invalidInput)
         {
            System.out.println("Please enter [Yes] or [No]:");
            correct = console.next();
            if(correct.equalsIgnoreCase("yes"))
               invalidInput = false;
            else if(correct.equalsIgnoreCase("no"))
               invalidInput = false;
            else
               invalidInput = true;
         }
         */
         // creating a new person
         if(!loggingIn)
         {
            ArrayList<Student> idSorted = studentDatabase.sortID();
            for(int i = 0; i < idSorted.size(); i++)
            {
               while(idNumber.equalsIgnoreCase(idSorted.get(i).getPerson().getID()))
               {
                  System.out.println(idNumber + " exists within the system. Please enter a new ID number.");
                  while(!console.hasNextInt())
                  {
                     System.out.print("Please enter an integer: ");
                     console.next();
                  }
                  idNumber = Integer.toString(console.nextInt());
               }
            }
            System.out.println("Email, if there is none, please type [None] ");
            email = console.next();
            if(email.equalsIgnoreCase("none")) {
               email = "";
               console.nextLine();
            } else {
               while(!email.contains("@"))
               {
                  System.out.println("Invalid Email\nPlease enter a new one: ");
                  email = console.next();
               }
               console.nextLine();
            }
            System.out.println("10 Digit phone Number if none, please type [None] ");
            System.out.println("Example: 1800515300");
            phone = console.nextLine();
            String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";
            if(phone.equalsIgnoreCase("none")) {
               phone = "";
            } else
               while(!phone.matches(regexStr))//validate phone number
               {
                  System.out.println("Invalid phone number.");
                  phone = console.nextLine();
               }
         }
      } while(correct.equalsIgnoreCase("no"));
      return new Person(name,lastName,idNumber,email,phone);  
   }
   
   // adds item to the database
   public static void addItem(Scanner console, ArrayList<Item> itemDatabase)
   {
      String correct = "";
      Item createdItem;
      String iName, iSubject, iAuthor, iPublisher, iYearPublished,iType, addMore;
      boolean iStatus = false;;
      do {
         //admin inputs info about item
         System.out.print("Item Name: ");
         iName = console.nextLine();
         System.out.print("Item Subject: ");
         iSubject = console.nextLine();
         System.out.print("Item Author: ");
         iAuthor = console.nextLine();
         System.out.print("Item Publisher: ");
         iPublisher = console.nextLine();
         System.out.print("Item Publish Date: ");
         iYearPublished = console.nextLine();
         System.out.print("Item Type: ");
         iType = console.nextLine();
         System.out.print("Is the item missing or unavailable to be within the library?");
         do {
            correct = console.nextLine();
            if(correct.equalsIgnoreCase("yes"))
            {
               iStatus = false;
               correct = "";
            } else if (correct.equalsIgnoreCase("no"))
            {
               iStatus = true;
               correct = "";
            } else
               System.out.println("Please enter [Yes] or [No]");
         } while(!correct.equalsIgnoreCase(""));
         if(!iStatus)
         {
            System.out.println("Please make sure the library is able to receive " + iName + " by " + iAuthor);
         } else {
            createdItem = new Item(iName,iSubject,iAuthor,iPublisher,iYearPublished,iStatus,iType);
            System.out.println(iName + " by " + iAuthor + " has been added to the library.");
            itemDatabase.add(createdItem);
         }
         System.out.println("Do you want to add another item?");
         do {
            addMore = console.nextLine();
            if(addMore.equalsIgnoreCase("yes")) {
               addMore = "";
               correct = "no";
            } else if (addMore.equalsIgnoreCase("no")) {
               addMore = "";
               correct = "yes";
            } else
               System.out.println("Please enter [Yes] or [No]");
         } while(!addMore.equalsIgnoreCase(""));
      } while (!correct.equalsIgnoreCase("yes"));
   }
   // methods involving the items
      //returns a arrayList of the matching authors
   public static ArrayList<Item> authors(String authorName, ArrayList<Item> a)
   {
      ArrayList<Item> matchingAuthor = new ArrayList<Item>();
      for(int i = 0; i < a.size(); i++)
         if(a.get(i).getAuthor().toLowerCase().contains(authorName.toLowerCase()))
            matchingAuthor.add(a.get(i));
      return matchingAuthor;
   }
      //sorts the item list by author
   public static void sortByAuthor(ArrayList<Item> a)
   {
      Item k = null;
      int j;
      for(int i = 0; i < a.size(); i++)
      {
         k = a.get(i);
         j = i-1;
         
         while(j>= 0 && a.get(j).getAuthor().compareToIgnoreCase(k.getAuthor()) > 0)
         {
            a.set(j+1,a.get(j));
            j=j-1;
         }
         a.set(j+1,k);
      }
   }
   public static ArrayList<Item> wantedSubject(ArrayList<Item> a,String subject)
   {
      ArrayList<Item> returnList = new ArrayList<Item>();
      for(int i = 0; i < a.size(); i++)
         if(subject.equalsIgnoreCase(a.get(i).getSubject()))
            returnList.add(a.get(i));
      return returnList;
   }
   public static void serializeAll(Administrator admin, Students studentDatabase, ArrayList<Item> itemDatabase)
   {
      // serialize students
      try {
         FileOutputStream studentFile = new FileOutputStream("listOfStudents.ser");
         ObjectOutputStream studentOut = new ObjectOutputStream(studentFile);
         studentOut.writeObject(studentDatabase);
         studentOut.close();
         studentFile.close();
         //System.out.println("listOfStudents.ser has been updated");
      } catch (IOException ex) {
         System.out.println("System could not serialize list of students");
      }
      // serialize admin group
      try {
         FileOutputStream adminFile = new FileOutputStream("admin.ser");
         ObjectOutputStream adminOut = new ObjectOutputStream(adminFile);
         adminOut.writeObject(admin);
         adminOut.close();
         adminFile.close();
         //System.out.println("list of admins has been updated");
      } catch (IOException ex) {
         System.out.println("System could not serialize list of administrators.");
      }
      try {
         FileOutputStream itemFile = new FileOutputStream("items.ser");
         ObjectOutputStream itemOut = new ObjectOutputStream(itemFile);
         itemOut.writeObject(itemDatabase);
         itemOut.close();
         itemFile.close();
         //System.out.println("List of items has been updated");
      } catch (IOException ex) {
         System.out.println("System could not serialize list of items");
      }
   }
}