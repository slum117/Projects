/* The TeamLinkedList class
   Anderson, Franceschi
*/

import java.util.ArrayList;

public class TeamLinkedList extends ShellLinkedList
{
  // head and numberOfItems are inherited instance variables

  public TeamLinkedList(  )
  {
    super( );
  }

  /** insert method
  *   @param    t   Team object to insert
  */
  public void insert( Team t )
  {
    // create a TeamNode object and store it in tn
      TeamNode front = new TeamNode(t);
     //call the method setNext on the object tn with the parameter head
     front.setNext(head);
     //set the head to tn
     head = front;
     //increment numberOfItems
     numberOfItems++;
      }

  /** delete method
  *   @param    searchNickname nickname of Team to delete
  *   @return   the Team deleted
  */
  public Team delete( String searchNickname )
                            throws DataStructureException
  {
    //make a copy of the  head node called current 
    //create an objcet of TeamNode called previous and set it to null
    TeamNode current = head;
    TeamNode previous = null;
    //as long as current is not null and the name of the team is not the name of the team of the current node
    while(!(searchNickname.equalsIgnoreCase(current.getTeam().getNickname())) && current != null)
    {
      //System.out.println("stuck in while loop");
      //set previous to current
      previous = current;
      //set the current to current.next
      current = current.getNext();
      if (current == null) {
         break;
      }
     }
   try{
    if ( current == null ) // not found
        throw new DataStructureException(searchNickname + " not found: cannot be deleted");
   // else
   else {
     // if  current is equloa to the head nodes
     if (head.getTeam().getNickname().equalsIgnoreCase(searchNickname))
     {
         //System.out.println("stuck in if");
          //delete the head node
          head = null;
           // delete head and adjust the new head
          //head.setNext(previous);
          head = current.getNext();
     }
     // else
     else {
        //System.out.println("stuck in else");
        //delete the node at the location and adjust the refrence addresees
        previous.setNext(current.getNext());
        
        }
         
      //System.out.println("ending method");
      numberOfItems--;
      return current.getTeam( );
    }
    } catch(DataStructureException e)
    {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /** orderTeams method
  *   @return   an ArrayList of the teams ordered in descending order by winning percentages
  */
  public ArrayList<Team> orderTeams( )
  {
    ArrayList<Team> list = new ArrayList<Team>( );
    // Fill list with the linked list of teams
    TeamNode current = head;

    while ( current != null )
    {
      list.add( current.getTeam( ) );
      current = current.getNext( );
    }

    ArrayList<Team> orderedList = new ArrayList<Team>( );
    // fill orderedList from list using a modified version of selection sort
    // sorting in descending order by winning percentages
    double currentMaxWinPercentage = 0;
    Team temp, tempMaxWP;

    for (int i = 0; i < numberOfItems; i++ )
    {
      // find best team
      tempMaxWP = ( Team ) list.get( 0 );
      for ( int j = 1; j < list.size( ); j++ )
      {
        temp = ( Team ) list.get( j );
        if ( temp.winningPercentage( ) > tempMaxWP.winningPercentage( ) )
          tempMaxWP = temp;
      }

      // add tempMaxWP to orderedList
      orderedList.add( tempMaxWP );
      // remove it from list
      list.remove( tempMaxWP );
    }

    return orderedList;
  }

  /** fiveBestTeams method
  *   @return   an ArrayList representing the five best teams based on winning percentage
  */
  public ArrayList<Team> fiveBestTeams( )
  {
    ArrayList<Team> result = new ArrayList<Team>( );
    //create an orderd list of the teams 
    ArrayList<Team> ordered = orderTeams( );
    Team temp;

    if ( ordered.size( ) < 5 )
    {
      for ( int i = 0; i < ordered.size( ); i++ )
      {
        //get the item at i in the ordered list in the variable temp 
        //get the name of the team stored in temp
        //get the wins of the team stored in temp
        //get the losses of the team stored in temp
        temp = new Team(ordered.get(i).getNickname(),ordered.get(i).getWins(),ordered.get(i).getLosses());
        //cretae an object of Team
        Team adding = temp;
        //add the object to the array list result
        result.add(adding);
        
              }
    }
    else
    {
      for ( int i = 0; i < 5; i++ )
      {
        //get the item at i in the ordered list in the variable temp 
        //get the name of the team stored in temp
        //get the wins of the team stored in temp
        //get the losses of the team stored in temp
        temp = new Team(ordered.get(i).getNickname(),ordered.get(i).getWins(),ordered.get(i).getLosses());
        //cretae an object of Team
        Team adding = temp;
        //add the object to the array list result
        result.add(adding);
      }
    }
    return result;
  }

  /** maxWins method
  *   @return   an int, the maximum number of wins by any team
  */
  public int maxWins( )
  {
    int max = 0; // number of wins cannot be negative
    //copy the head node in the variable called current
      TeamNode current = head;
   // while current is not null
   while(current != null)
    {
     //if the wins of the team stored in the current is gretaer than mxa
      if(current.getTeam().getWins() > max)
         // set max to the wins of the team
         max = current.getTeam().getWins();
        // set the current to the next node
        current = current.getNext();
         
    }
    return max;
  }


  /** mostWins method
  *   @return   an ArrayList of Strings storing all the nicknames of the teams with the most wins
  */
  public ArrayList<String> mostWins( )
  {
    int maxWins = maxWins();
    ArrayList<String> temp = new ArrayList<String>( );
    //make a copy of the head node and call it current
    TeamNode current = head;

   // while current is not null 
   while(current != null) {
      // if the wins of the current team is equal to maxWins
      if(current.getTeam().getWins() == maxWins){
            //add the current team nikname to the temp
            temp.add(current.getTeam().getNickname());
      }      
      //adjust current to the next node 
      current = current.getNext();
       
    }

    return temp;
  }

  /** peek method
  *   @param    searchNickname nickname of Team to search for
  *   @return   a copy of the Team found
  */
  public Team teek( String searchNickname )
      throws DataStructureException
  {
    //make a copy of the head node and store it in the current
    TeamNode current = head;
    //while  current is not null and the searchNickName is not equals to the nukName of the current node
    while(current != null && !(searchNickname.equalsIgnoreCase(current.getTeam().getNickname())))
    {
       //set current to the nextNode
       current = current.getNext();
    }
    
    
   try{
    //if ( current == null ) // not found
    if(current == null)
      throw new DataStructureException( searchNickname + " not found: cannot be deleted");
     // throw new DataStructureException( searchNickname
                  //  + " not found: cannot be deleted" );
    //else
    else
    {
      //get the name of the current team,
      String teamName = current.getTeam().getNickname();
      // get the wins of the current team
      int wins = current.getTeam().getWins();
      //get the losses of the current team
      int losses = current.getTeam().getLosses();
      //create a Team object
      Team copiedTeam = new Team(teamName,wins,losses);
      //return the object
      return copiedTeam;
    }
    } catch (DataStructureException e)
    {
      System.out.println(e.getMessage());
      return null;
    }
    //return null;//this line must be removed
  }
}
