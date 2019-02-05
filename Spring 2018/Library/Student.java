import java.util.*;
public class Student implements java.io.Serializable
{
   //field
   private Person stud;
   private ArrayList<Item> checkedOut;
   
   //constructor
   public Student(Person p)
   {
      stud = p;
      checkedOut = new ArrayList<Item>();
   }
   
   //accessors
   public Person getPerson()
   {
      return stud;
   }
   public ArrayList<Item> getCheckedOut()
   {
      return checkedOut;
   }
   
   //mutators
   public void setPerson(Person p)
   {
      stud = p;
   }
   public void setCheckedOut(ArrayList<Item> checkedOut)
   {
      this.checkedOut = checkedOut;
   }

   // equals
   // uses from the Person class
   public boolean equals(Student s)
   {
      
      return stud.equals(s.getPerson());
   }
   
   public String toString()
   {
      return stud.toString() + " Student: True";
   }
   
   public void addCheckedOut(Item i)
   {
      checkedOut.add(i);
   }
   public void removeCheckedItem(Item i)
   {
      checkedOut.remove(i);
   }
}