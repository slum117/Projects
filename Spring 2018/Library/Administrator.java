import java.util.*;
public class Administrator implements java.io.Serializable
{
   private ArrayList<Person> people;
   private int callNumber;
   
   //constructor
   public Administrator()
   {
      people = new ArrayList<Person>();
      callNumber = -1;
   }
   //add remove functions
   public void add(Person p)
   {
      people.add(p);
   }
   public void remove(Person p)
   {
      people.remove(p);
   }
   
   //sort functions
   public ArrayList<Person> sortName()
   {
   
      int j;
      Person k;
      for(int i = 0; i < people.size(); i++)
      {
         k = people.get(i);
         j = i-1;
         
         while(j>= 0 && people.get(j).getName().compareToIgnoreCase(k.getName()) > 0)
         {
            people.set(j+1,people.get(j));
            j=j-1;
         }
         people.set(j+1,k);
         
      }
      return people;
   }
   public ArrayList<Person> sortLastName()
   {
   
      int j;
      Person k;
      for(int i = 0; i < people.size(); i++)
      {
         k = people.get(i);
         j = i-1;
         
         while(j>= 0 && people.get(j).getLastName().compareToIgnoreCase(k.getLastName()) > 0)
         {
            people.set(j+1,people.get(j));
            j=j-1;
         }
         people.set(j+1,k);
         
      }
      return people;
   }
   public ArrayList<Person> sortID()
   {
      Person k = null;
      int j;
      for(int i = 0; i < people.size(); i++)
      {
         k = people.get(i);
         j = i-1;
         
         while(j>= 0 && Integer.parseInt(people.get(j).getID()) > (Integer.parseInt(k.getID())))
         {
            people.set(j+1,people.get(j));
            j=j-1;
         }
         people.set(j+1,k);
         
      }
      return people;
   }
   //toString lists all admins and id number
   public String toString()
   {
      String retStr = String.format("List of administrators: %n");
      retStr+=String.format("Name:   Lastname:    ID:%n");
      for(int i = 0; i < people.size();i++)
      {
         retStr+= String.format("%-7s %-12s %s%n",people.get(i).getName(),people.get(i).getLastName(),people.get(i).getID());
      }
      return retStr;
   }
   //search function sequential search
   public boolean search(Person p)
   {
      sortID();
      int idNumber = Integer.parseInt(p.getID());
      boolean retVal = false;
      for(int i = 0; i < people.size();i++)
      {
         int comparedID = Integer.parseInt(people.get(i).getID());
         if(idNumber == comparedID)
            if(people.get(i).getName().equalsIgnoreCase(p.getName()))
               if(people.get(i).getLastName().equalsIgnoreCase(p.getLastName()))
               {
                  callNumber = i;
                  return true;
               }
      }
      return retVal;
   }
   public int getCallNumber() {
      return callNumber;
   }
   public Person getPerson(int x) {
      return people.get(x);
   }
}