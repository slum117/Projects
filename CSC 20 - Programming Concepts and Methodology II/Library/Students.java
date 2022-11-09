import java.util.*;
public class Students implements java.io.Serializable
{
   //fields
   private ArrayList<Student> studentList;
   //constructor
   public Students()
   {
      studentList = new ArrayList<Student>();
   }
   
   public void add(Student s)
   {
      studentList.add(s);
   }
   public void remove(Student s)
   {
      studentList.remove(s);
   }
   public void setStudents(ArrayList<Student> setter)
   {
      studentList = setter;
   }
   public String toString()
   {
      String retVal = String.format("");
      for(int i = 0; i < studentList.size(); i++)
      {
         retVal += studentList.get(i).getPerson().getName() + " " + studentList.get(i).getPerson().getLastName() + "\n";
      }
      return retVal;
   }
   // searching for students
   public Student searchStudents(String id)
   {
      Student retVal = null;
      for(int i = 0; i < studentList.size();i++)
         if(id.equalsIgnoreCase(studentList.get(i).getPerson().getID()))
            return studentList.get(i);
         else
            retVal = null;
      return retVal;
   }
   //arrayList search methods
   public ArrayList<Student> searchStudentsByName(Scanner console)
   {
      System.out.println("What is the student's name?");
      String name = console.next();
      ArrayList<Student> matchingStudents = new ArrayList<Student>();
      for(int i = 0; i < studentList.size();i++)
         if(name.equalsIgnoreCase(studentList.get(i).getPerson().getName()))
            matchingStudents.add(studentList.get(i));
      return matchingStudents;
   }
   public ArrayList<Student> searchByLastName(Scanner console)
   {
      System.out.println("What is the student's last name?");
      String lastName = console.next();
      ArrayList<Student> matchingStudents = new ArrayList<Student>();
      for(int i = 0; i < studentList.size();i++)
         if(lastName.equalsIgnoreCase(studentList.get(i).getPerson().getLastName()))
            matchingStudents.add(studentList.get(i));
      return matchingStudents;
   }
   // combine first name and last name in driver class later
   public ArrayList<Student> searchByBothNames(Scanner console)
   {
      System.out.println("What is the student's first name?");
      String bothNames = console.next();
      System.out.println("What is the student's last name?");
      bothNames = bothNames + console.next();
      ArrayList<Student> matchingStudents = new ArrayList<Student>();
      for(int i = 0; i < studentList.size();i++)
         if(bothNames.equalsIgnoreCase(studentList.get(i).getPerson().getName()+studentList.get(i).getPerson().getLastName()))
            matchingStudents.add(studentList.get(i));
      return matchingStudents;
   }
   public ArrayList<Student> searchByEmail(Scanner console)
   {
      System.out.println("What is the student's email?");
      String email = console.next();
      ArrayList<Student> matchingStudents = new ArrayList<Student>();
      for(int i = 0; i < studentList.size();i++)
         if(email.equalsIgnoreCase(studentList.get(i).getPerson().getEmail()))
            matchingStudents.add(studentList.get(i));
      return matchingStudents;
   }
   public ArrayList<Student> searchByPhone(Scanner console)
   {
      System.out.println("What is the student's phone number?");
      String phone = console.next();
      ArrayList<Student> matchingStudents = new ArrayList<Student>();
      for(int i = 0; i < studentList.size();i++)
         if(phone.equalsIgnoreCase(studentList.get(i).getPerson().getPhone()))
            matchingStudents.add(studentList.get(i));
      return matchingStudents;
   }
   // sort by name
   public ArrayList<Student> sortName()
   {
      Student k = null;
      int j;
      for(int i = 0; i < studentList.size(); i++)
      {
         k = studentList.get(i);
         j = i-1;
         
         while(j>= 0 && studentList.get(j).getPerson().getName().compareToIgnoreCase(k.getPerson().getName()) > 0)
         {
            studentList.set(j+1,studentList.get(j));
            j=j-1;
         }
         studentList.set(j+1,k);
         
      }
      return studentList;
   }
   public ArrayList<Student> sortLastName()
   {
      Student k = null;
      int j;
      for(int i = 0; i < studentList.size(); i++)
      {
         k = studentList.get(i);
         j = i-1;
         
         while(j>= 0 && studentList.get(j).getPerson().getLastName().compareToIgnoreCase(k.getPerson().getLastName()) > 0)
         {
            studentList.set(j+1,studentList.get(j));
            j=j-1;
         }
         studentList.set(j+1,k);
         
      }
      return studentList;
   }
   public ArrayList<Student> sortID()
   {
      Student k = null;
      int j;
      for(int i = 0; i < studentList.size(); i++)
      {
         k = studentList.get(i);
         j = i-1;
         
         while(j>= 0 && Integer.parseInt(studentList.get(j).getPerson().getID()) > (Integer.parseInt(k.getPerson().getID())))
         {
            studentList.set(j+1,studentList.get(j));
            j=j-1;
         }
         studentList.set(j+1,k);
         
      }
      return studentList;
   }
   
}