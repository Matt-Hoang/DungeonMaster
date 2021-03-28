/** Representation of a magical enemy  */
public class MagicalEnemy extends Enemy implements Magical{
  /** Constructs a magical enemy with a name and max HP
  *   @param n    magical enemy's name
  *   @param mHp  magical enemy's max HP
  */
  public MagicalEnemy(String n, int mHp) {
    super(n, mHp);
  }

  /* Randomly selects between 3 spells to attack 
  *  @param e entity that is being attacked  
  *  @return string representation of the attack details 
  */
  @Override
  public String attack(Entity e) {
    int spell = (int) (Math.random()*3 + 1);
 
    if(spell == 1) {
			return magicMissile(e);
		}
		else if(spell == 2) {
			return fireball(e);
		}
		else {
			return thunderclap(e);
		}
  }

  /* Deals random amount of damage between a set range
  *  @param e entity that is being attacked  
  *  @return string representation of the damage 
  */
  @Override
  public String magicMissile(Entity e) {
    int dmg = (int) (Math.random()*3 + 1);
    e.takeDamage(dmg);
    return super.getName() + " shoots " + e.getName() + " with a Magic Missile for " + dmg + " damage.";
  }

  /* Deals random amount of damage between a set range
  *  @param e entity that is being attacked  
  *  @return string representation of the damage 
  */
  @Override
  public String fireball(Entity e) {
    int dmg = (int) (Math.random()*5 + 2);
    e.takeDamage(dmg);
    return super.getName() + " burns " + e.getName() + " with a Fireball for " + dmg + " damage.";
  }

  /* Deals random amount of damage between a set range
  *  @param e entity that is being attacked  
  *  @return string representation of the damage 
  */
  @Override
  public String thunderclap(Entity e) {
    int dmg = (int) (Math.random()*8 + 4);
    e.takeDamage(dmg);
    return super.getName() + " zaps " + e.getName() + " with Thunderclap for " + dmg + " damage.";
  }

}