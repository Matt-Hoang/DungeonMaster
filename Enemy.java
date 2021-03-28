/** Representation of an enemy  */
public class Enemy extends Entity{
  /** Constructs enemy with a name and max hp
  *   @param n    enemy's name
  *   @param mHp  enemy's max HP
  */
  public Enemy(String n, int mHp) {
    super(n, mHp);
  }

  /* Deals random amount of damage within a set range
  *  @param e entity that is being attacked  
  *  @return string representation of the attack details 
  */
  @Override
  public String attack (Entity e) {
    int dmg = (int) (Math.random()*5 + 1);
    e.takeDamage(dmg);
    return super.getName() + " attacks " + e.getName() + " for " + dmg + " damage.";
    
  }

}