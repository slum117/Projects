/* The Team Class
   Anderson, Franceschi
*/

public class Team
{
  private String nickname;
  private int wins;
  private int losses;

  /** constructor
  *  @param newNickname   starting value for nickname
  *  @param newWins       starting balue for wins
  *  @param newLosses     starting value for losses
  */
  public Team( String newNickname, int newWins, int newLosses )
  {
     nickname = newNickname;
     wins = newWins;
     losses = newLosses;
  }

  /** getNickname
  * @return nickname
  */
  public String getNickname( )
  {
    return nickname; 
  }

  /** getWins
  * @return wins
  */
  public int getWins( )
  {
     return wins;
  }

  /** getLosses
  * @return losses
  */
  public int getLosses( )
  {
    return losses; 
  }

  /** setNickname
  * @param newNickname  new value for nickname
  */
  public void setNickname( String newNickname )
  {
     nickname = newNickname;
  }

  /** setWins
  * @param newWins  new value for wins
  *                 newWins must be >= 0. otherwise value is unchanged
  */
  public void setWins( int newWins )
  {
     wins = newWins;
  }

  /** setLosses
  * @param newLosses  new value for losses
  *                   newLosses must be >= 0. otherwise value is unchanged
  */
  public void setLosses( int newLosses )
  {
     losses = newLosses;
  }

  /** equals
  * @param o  another Team object
  * @return  true if the instance variables in this object are equal to the
  *          instance variables in t; false otherwise
  */
  public boolean equals( Object o )
  {
    boolean equalNames = false;
    boolean equalLoss = false;
    boolean equalWins = false;
    if (o instanceof Team) {
      Team otherTeam = (Team)o;
      if(otherTeam.getNickname().equalsIgnoreCase(nickname))
         equalNames = true;
      if(otherTeam.getWins() == wins)
         equalWins = true;
      if(otherTeam.getLosses() == losses)
         equalLoss = true;
    }
	 return equalNames && equalLoss && equalWins;
    
  }

  /** toString
  * @return String representation of nickname, wins, and losses
  */
  public String toString( )
  {
     String retVal = String.format("Name: %-12s Wins: %-3d Losses: %d %n", nickname, wins, losses);
     return retVal;
  }

  /** winningPercentage
  * @return  wins / total games; 0 if no games have been played
  */
  public double winningPercentage( )
  {
     return  wins+losses > 0 ? (double)wins/(wins+losses) : 0;
  }
}
