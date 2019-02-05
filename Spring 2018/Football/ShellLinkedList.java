/* The ShellLinkedList class
   Anderson, Franceschi
*/

public abstract class ShellLinkedList
{
  protected TeamNode head;
  protected int numberOfItems;

  //set the head to null
  //set the numberOfItems to 0
  public ShellLinkedList( )
  {
      head = null;
      numberOfItems = 0;
  }

  public int getNumberOfItems( )
  {
       return numberOfItems;
  }
   
  //changed from boolean to int in order to return number of items in list
  public int isEmpty( )
  {
      // return the number of the items in the list
      return numberOfItems;
  }
 
  public String toString()
  {
     //returns a string representing all the items in the list
     TeamNode untilNull = head;
     String retVal = String.format("");
     while(untilNull != null) {
         retVal += untilNull.getTeam().toString();
         untilNull = untilNull.getNext();
     }
     return retVal;
  }
}
