import java.awt.Point;
import java.util.Scanner;
/* Group32 Project1
*  CECS 277 - 01
*  3/5/21
* 
* Main.java is a game that allows the user to explore a dungeon maze and battle monsters that come
* across their path while navigating through the various dungeon mazes. This class 
* gathers information from Enemy.java, EnemyGenerator.java, Entity.java, Hero.java,
* Magical.java, MagicalEnemy.java, and Map.java in order to create the hero, maps,
* different types of monsters, and the attacking capabilites for the hero and monsters.
*/
class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner (System.in);

    // a) gets name and constuct Hero
    System.out.print("What is your name, traveler? ");
    String PlayerName = in.nextLine();
    Hero h1 = new Hero(PlayerName);
    
    // f) repeat until user quits or hero dies 
    int selection = 0;
    while (selection != 5 && h1.getHP() > 0) {
      // b) display hero with map and have user choose direction 
      System.out.println(h1); 
      System.out.print("1. Go North (up)\n2. Go South (down)\n3. Go East (right)\n4. Go West (left)\n5. Quit\nSelection: ");
      selection = in.nextInt();
      char value = 'z';
      if (selection == 1) {
        value = h1.goNorth();
      } else if (selection == 2) {
        value = h1.goSouth();
      } else if (selection == 3) {
        value = h1.goEast();
      } else if (selection == 4) {
        value = h1.goWest(); 
      } else if (selection == 5) {
        System.out.println("\nYou have quit!");
        break; 
      } else {
        value = 'z';
      }

      // c) get resulting character from hero's direction methods
      if (value == 'z') {
        System.out.println("Invalid input try again");
      } else if (value == 'x') {
        // location out of bounds
        System.out.println("Out of bounds! Try again.");
      } else if (value == 'n') {
        //nothing here
        System.out.println("There was nothing here.");
      } else if (value == 's') {
        // start (nothing here)
        System.out.println("You're back at the start"); 
      } else if (value == 'f') {
        // finish - level up hero to move to next map 
        System.out.println("You found the exit! Proceeding to the next level.");
        h1.levelUp();          
      } else if (value == 'i') {
        // item - hero finds heal potion, heal for 25 hp
        System.out.println("You found a Health Potion! You drink it to restore your health.");
        h1.heal(25);
      } else if (value == 'm') {
        // monster - fight enemy by calling monsterRoom
        EnemyGenerator enemy = new EnemyGenerator();
        Enemy currentEnemy = enemy.generateEnemy();
        boolean trueFalse = monsterRoom(h1, currentEnemy);
        
        if (trueFalse == false) {
          System.out.println("\nGame Over! You've been slaughtered.");
        } 

      } 

    }
  }
  /** Allows the user to chose to fight an enemy or run away 
  *   @param h    hero entity
  *   @param e    enemy entity
  *   @return true or false representing if hero is still alive
  */
  static boolean monsterRoom(Hero h, Enemy e) {
    Scanner in = new Scanner(System.in);
    System.out.println("\nYou've encountered a " + e.getName());
    while (e.getHP() > 0 && h.getHP() > 0) {
      
      System.out.print(e + "\n1. Fight\n2. Run Away\nSelection: ");
      int selection = in.nextInt();
  
      if (selection == 1) {
        //fight
        fight(h, e);
      } else if (selection == 2) {
        //run
        char value = 'x';
        while (value == 'x') {
          int direction =  (int) (Math.random()*4 + 1);
          if (direction == 1) {
            value = h.goNorth();
          } else if (direction == 2) {
            value = h.goSouth();
          } else if (direction == 3) {
            value = h.goEast();
          } else if (direction == 4) {
            value = h.goWest(); 
          }
        }

        if (value == 'n') {
          // nothing here
          System.out.println("There was nothing here.");
        } else if (value == 's') {
          // start (nothing here)
          System.out.println("You're back at the start"); // do we need?
        } else if (value == 'f') {
          // finish - level up hero to move to next map 
          System.out.println("You found the exit! Proceeding to the next level.");
          h.levelUp();          
        } else if (value == 'i') {
          // item - hero finds heal potion, heal for 25 hp
          System.out.println("You found a Health Potion! You drink it to restore your health.");
          h.heal(25);
        } else if (value == 'm') {
          // monster - fight enemy by calling monsterRoom
          EnemyGenerator enemy = new EnemyGenerator();
          Enemy currentEnemy = enemy.generateEnemy();
          boolean trueFalse = monsterRoom(h, currentEnemy);
          
          if (trueFalse == false) {
            System.out.println("Game Over! You've been slaughtered.");
          }
        }
        break;
      }
    }
    //true if alive, false if dead
    if (h.getHP() > 0) {
      return true;
    } else {
      return false; 
    }
  }


  /** Allows the user to fight an enemy with different attacks  
  *   @param h    hero entity
  *   @param e    enemy entity
  *   @return true or false representing if hero is still alive
  */ 
  static boolean fight(Hero h, Enemy e) {
    Scanner in = new Scanner(System.in);
    System.out.print("1. Physical Attack\n2. Magic Attack\nSelection: ");
    int selection = in.nextInt();

    if (selection == 1) {
      System.out.println(h.attack(e));
    } else if (selection == 2) {
      System.out.print(h.MAGIC_MENU + "\nSelection: ");
      int spell = in.nextInt();;
      
      if (spell == 1) {
        System.out.println(h.magicMissile(e));
      } else if (spell == 2) {
        System.out.println(h.fireball(e));
      } else if (spell == 3) {
        System.out.println(h.thunderclap(e)); 
      }
    }
    
    //enemy attack if still alive 
    if (e.getHP() > 0) {
      System.out.println(e.attack(h));
    } else {
      System.out.println("You defeated the " + e.getName() + "!");
    }
    
    //true if alive, false if dead
    if (h.getHP() > 0) {
      return true;
    } else {
      return false; 
    }
  }
}