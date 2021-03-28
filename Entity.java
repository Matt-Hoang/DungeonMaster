/** Representation of an entity */
public abstract class Entity {
  /* Represents the entity's name */
  private String name;

  /* Represents the entity's maxHP */
  private int maxHp;

  /* Represents the entity's current HP */
  private int hp;

  /** Constructs an entity with a name and max HP
  *   @param n    entity's name
  *   @param mHp  entity's max HP
  */
  public Entity(String n, int mHp) {
    name = n;
    maxHp = mHp;
    hp = mHp;
  }
  
  /** Attacks an entity 
  *   @param e entity that is being attacked
  */  
  public abstract String attack (Entity e);

  /** Retrieves the name of the entity
  *   @return name of the entity
  */
  public String getName() {
    return name;
  }

  /** Retrieves the HP of the entity
  *   @return name of the HP
  */
  public int getHP() {
    return hp;
  }

  /** Retrieves the max HP of the entity
  *   @return the max HP of the entity
  */
  public int getMaxHP() {
    return maxHp;
  }

  /** Heals entity 
  *   @param h heal amount
  */
  public void heal(int h) {
    if (hp < maxHp) {
      hp += h;
      // hp never goes over max hp
      if (hp > maxHp) {
        hp = maxHp;
      }
    }
  }
  
  /** Decreases entity’s HP 
  *   @param d damage amount
  */
  public void takeDamage(int d) {
    hp -= d; 
    // hp never goes below 0 
    if (hp <= 0) {
      hp = 0;
    }
  }

  /** String representation of entity's name and hp over maxHp.
  */
  @Override
  public String toString() {
    return "\n" + getName() + "\nHP: " + getHP() + "/" + getMaxHP();
  }

}