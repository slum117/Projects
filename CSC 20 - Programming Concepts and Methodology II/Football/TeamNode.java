/* The TeamNode class
   Anderson, Franceschi
*/

public class TeamNode
{
   private Team team;
   private TeamNode next;

  /** default constructor
  * sets team and next to null
  */
  public TeamNode( )
  {
       next = null;
       team = null;
  }

  /** constructor
  * @param t a Team object reference
  *   sets team to t, and next to null
  */
  public TeamNode( Team t )
  {
      next = null;
      team = t;
  }

  /** getTeam
  *  @return team
  */
  public Team getTeam( )
  {
    return team;
  }

  /** getNext
  * @return next
  */
  public TeamNode getNext( )
  {
    return next;
  }

  /** setTeam
  * @param t new value foe team
  */
  public void setTeam( Team t )
  {
     team = t;
  }

  /** setNext
  * @param t TeamNode reference for next
  */
  public void setNext( TeamNode t )
  {
     next = t;
  }
}
