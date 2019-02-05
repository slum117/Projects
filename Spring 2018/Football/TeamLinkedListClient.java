/* The TeamLinkedListClient class
   Anderson, Franceschi
*/
import java.io.*;
import java.util.*;

public class TeamLinkedListClient
{
  public static void main( String [] args ) throws FileNotFoundException, DataStructureException
  {
     Scanner console = new Scanner(System.in);

    // construct TeamLinkedList
    //read the file and add each team to the list
    TeamLinkedList teams = new TeamLinkedList();
    
    
    readFile("listOfTeams.txt",teams);
    
    //output the number of the items in the list
    System.out.println("Current Number of Teams: " + teams.getNumberOfItems());
    //output all the teams on the screen
    System.out.println(teams);
    
    //call the method mostWins store the result in an array list called winngest
    ArrayList<String> winngest = teams.mostWins();
    
    //output the winnget array list (the array might have 0 or more elements
    System.out.println(winngest);
    
    //call the method fiveBestTeam store the result in an array list called
    ArrayList<Team> topFive = teams.fiveBestTeams();
    
    //output the top  teams from the array list
    System.out.println(topFive);
    
    //output the winngest team
    displayWinngest(teams, winngest);
    
    //output the list of the teams
    System.out.println(teams);
    
    //remove the first element from the last and add it to the end of the list
    removeFirst(teams);
    System.out.println(teams);
    
    //find the third team in the list
    Team thisTeam = findTeam(teams,3);
    //System.out.println(thisTeam);
    
    //ask the user for a team name and remove that team from the list
    deleteTeam(console, teams);
    
    //call the method reverse
    reverseTeam(teams);
    System.out.println(teams);
    
        
    }
    
    // reverses the list
    public static void reverseTeam(TeamLinkedList teams) {
    
      TeamLinkedList list = teams;
      TeamNode current = teams.head.getNext();
      teams.head.setNext(null);
      
      while(current != null)
      {
         TeamNode temp = current;
         current = current.getNext();
         temp.setNext(list.head);
         list.head = temp;
      }
      System.out.println("Teams have been reversed");
    }
    
    public static void deleteTeam(Scanner console, TeamLinkedList teams) throws DataStructureException
    {
    Team isNullOrNot;
      do {
         System.out.print("Please enter a team to delete: ");
         String teamName = console.nextLine();
         isNullOrNot = teams.delete(teamName);
         System.out.println();
         
      } while (isNullOrNot == null);
      System.out.println(isNullOrNot.getNickname() + " has been removed");
    }
    
    // finds the xth team in the list
    public static Team findTeam(TeamLinkedList teams, int teamNumber) {
      TeamNode current = teams.head;
      if(teamNumber <= teams.getNumberOfItems())
      {
         for(int i = 0; i < teamNumber-1; i++)
         {
            current = current.getNext();
         }
         return current.getTeam();
      } else
      {
         System.out.println("Team #" + teamNumber + " does not exist, please select a team between 1 and" + teams.getNumberOfItems());
         return null;
      }
    }
    // puts the first element in the list to the end of the list
    public static void removeFirst(TeamLinkedList teams)
    {
      TeamNode current = teams.head;
      TeamNode temp = teams.head.getNext();
      teams.head = teams.head.getNext();
      current.setNext(null);
      while(temp.getNext() != null)
      {
         temp = temp.getNext();
      }
      temp.setNext(current);
      System.out.println(current.getTeam().getNickname() + " has been moved to the end of the list");
    }
    // displays the "winggest" teams
    public static void displayWinngest(TeamLinkedList teams, ArrayList<String> WinngestTeams) throws DataStructureException
    {
       try {
          for(int i = 0; i < WinngestTeams.size(); i++)
            System.out.println(teams.teek(WinngestTeams.get(i)));
          if(WinngestTeams.size() <= 0)
            throw new DataStructureException("No team found");
       } catch (DataStructureException e) {
         System.out.println(e.getMessage());
         //throw new DataStructureException( "no winnest teams");
         
       }
    }
    
    // reads file input and stores into teams linkedlist
    public static void readFile(String fileName, TeamLinkedList teams) throws FileNotFoundException
    {
       File f = new File(fileName);
       if(f.exists()) {
          Scanner readFile = new Scanner(f);
          
          while(readFile.hasNext())
          {
            String name = "";
            int wins = 0,losses = 0;
            name = readFile.next();
            wins = readFile.nextInt();
            losses = readFile.nextInt();
   
            //System.out.printf("%s: %d wins %d losses %n", name,wins,losses);
            teams.insert(new Team(name,wins,losses));
          }
       }
    }
    //his method receives the link list of the teams and prints the list in the reverse order.
    //you can use a stack or array or array list to print the link list in the reverse order
    public static void reverst(Object o)
    {
    }
}
