// needs validation for format
public class Person implements Comparable<Person>, java.io.Serializable
{
   //fields
   private String name;
   private String lastName;
   private String id;
   private String email;
   private String phone;
   
   //constructors
   public Person()
   {
      name = "";
      lastName = "";
      id = "";
      email = "";
      phone = "";
   }
   public Person(String n, String l, String initialed, String em, String p)
   {
      name = n;
      lastName = l;
      id = initialed;
      email = em;
      phone = p;
   }
   
   //accessors
   public String getName(){
      return name;
   }
   public String getLastName(){
      return lastName;
   }
   public String getID() {
      return id;
   }
   public String getEmail() {
      return email;
   }
   public String getPhone() {
      return phone;
   }
   //mutators
   public void setName(String nam) {
      name = nam;
   }
   public void setLastName(String nam) {
      lastName = nam;
   }
   public void setID(String ID) {
      id = ID;
   }
   public void setEmail(String Email) {
      email = Email;
   }
   public void setPhone(String pho) {
      phone = pho;
   }
   
   //equals
      // compares the id number
   public boolean equals(Person p) {
      if(p.getID().equalsIgnoreCase(id))
         return true;
      else
         return false;
   }
   
      //compares the last names
   public int compareTo(Person p)
   {
      return lastName.compareTo(p.getLastName());
   }
   /*
   // validation
   public boolean validateId(String id)
   {
   
   }
   public boolean validateEmail(String email)
   {
   
   }
   public boolean validate(String phone)
   {
   
   }
   */
   public String toString()
   {
      String retVal = String.format(name + " " + lastName + " ID: " + id + " eMail: " + email + " Phone: " + phone);
      return retVal;
   }
}