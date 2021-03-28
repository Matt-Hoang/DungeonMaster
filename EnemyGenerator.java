import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/** Creates different types of enemies */
public class EnemyGenerator {
  /* Represents all enemies possible */
  private ArrayList<Enemy> enemyList = new ArrayList<>();

  /** Adds all enemies from text file to ArrayList 
  */
  public EnemyGenerator() {
    try {
      Scanner read = new Scanner(new File("Enemies.txt"));
      while (read.hasNext()) {
        String line = read.nextLine();
        String [] values = line.split(",");

        //add enemy to list
        Enemy enemy = new Enemy(values[0], Integer.valueOf(values[1])); // str name, int mHp
        enemyList.add(enemy);  
        
      }
      read.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found!");
    }
  }
  
  /* Randomly selects enemy from the list and if it is normal or magical
  *  @return enemy or magical enemy with name and max HP 
  */
  public Enemy generateEnemy() {
    String name = "";

    int randEnemy =  (int) (Math.random()*enemyList.size());
    int enemyType = (int) (Math.random()*2 + 1);
    
    if (enemyType == 1) {
      // normal enemy
      Enemy enemy = new Enemy(enemyList.get(randEnemy).getName(), enemyList.get(randEnemy).getHP());
      return enemy;
    } else {
      // magical enemy
      int magicType = (int) (Math.random()*3 + 1);

      // selects between 3 different magical enemy titles
      if (magicType == 1) {
        name = "Magical " + enemyList.get(randEnemy).getName();
      } else if (magicType == 2) {
        name = "Wizard " + enemyList.get(randEnemy).getName();
      } else if (magicType == 3) {
        name = "Warlock " + enemyList.get(randEnemy).getName();
      }
      MagicalEnemy enemy = new MagicalEnemy(name, enemyList.get(randEnemy).getHP());
      return enemy;
    }

  }

}
