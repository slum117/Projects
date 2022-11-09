// remember to implement validation for type
public class Item implements Comparable<Item>, java.io.Serializable
{
   //variables
   private String title;
   private String subject;
   private String author;
   private String publisher;
   private String yearPublished;
   private boolean status;
   private String type;
   
   //constructors
   public Item()
   {
      title = "";
      subject = "";
      author = "";
      publisher = "";
   }
   public Item(String t, String s, String a, String p, String y, boolean stat, String typ)
   {
      title = t;
      subject = s;
      author = a;
      publisher = p;
      yearPublished = y;
      status = stat;
      type = typ;
   }
   
   //accessors
   public String getTitle()
   {
      return title;
   }
   public String getSubject()
   {
      return subject;
   }
   public String getAuthor()
   {
      return author;
   }
   public String getPublisher()
   {
      return publisher;
   }
   public String getYearPublished()
   {
      return yearPublished;
   }
   public boolean getStatus()
   {
      return status;
   }
   public String getType()
   {
      return type;
   }
   
   //mutators
   public void setTitle(String title)
   {
      this.title = title;
   }
   public void setSubject(String subject)
   {
      this.subject = subject;
   }
   public void setAuthor(String author)
   {
      this.author = author;
   }
   public void setPublisher(String publisher)
   {
      this.publisher = publisher;
   }
   public void setYearPublished(String yrPub)
   {
      yearPublished = yrPub;
   }
   public void setStatus(boolean stat)
   {
      status = stat;
   }
   public void setType(String mediaType)
   {
      type = mediaType;
   }
   
   //equals
   //ignores the status of the item being compared
   public boolean equals(Item i)
   {
      boolean retVal = true;
      if(!(title.equalsIgnoreCase(i.getTitle())))
         retVal = false;
      if(!(subject.equalsIgnoreCase(i.getSubject())))
         retVal = false;
      if(!(author.equalsIgnoreCase(i.getAuthor())))
         retVal = false;  
      if(!(publisher.equalsIgnoreCase(i.getPublisher())))
         retVal = false;
      if(!(yearPublished.equalsIgnoreCase(i.getYearPublished())))
         retVal = false;    
      if(!(type.equalsIgnoreCase(i.getType())))
         retVal = false; 
      
      return retVal;
   }
   public String toString()
   {
      String retVal = String.format("Title: %s Subject: %s Author: %s Publisher: %s Year Published: %s Type: %s Status: %s",title,subject,author,publisher,yearPublished,type,status);
      return retVal;
   }
   public boolean contains(String s)
   {
      String keyPhrase = s.toLowerCase();
      String inTitle = title.toLowerCase();
      if(inTitle.contains(keyPhrase))
         return true;
      else
         return false;
   }
   /* needs to be worked on
   public boolean Validatedate(String d)
   {
      
   }
   */
   public int compareTo(Item i)
   {
      return title.concat(author).compareTo(i.getTitle().concat(i.getAuthor()));
   }
}