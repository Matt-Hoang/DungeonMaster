/* Interface for magical attacks of an object */
public interface Magical {
  /** Magic menu interface */
  static final String MAGIC_MENU = "1. Magic Missle\n2. Fireball\n3. Thunderclap";
  
  /** Method to make the object use magicMissile */
  public String magicMissile(Entity e);

  /** Method to make the object use fireball */
  public String fireball(Entity e);

  /** Method to make the object use thunderclap */
  public String thunderclap(Entity e);

}