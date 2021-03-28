import java.awt.Point;
import java.io.*;
import java.util.*;

/** represents the dungeon map */
public class Map {
  /* Represents the current map active */
  private char [][] CurrentMap = new char [5][5];
  
  /* Represents if location has been visited  */
  private boolean [][] visited = new boolean [5][5];

  /** Constructs the map for level 1
  */
  public Map() {
    loadMap(1);
  }
  
  /** Reads the map in and stores it in the character array
  *   @param mapNum set map number to load as CurrentMap
  */
  public void loadMap(int mapNum) {
    // selected map is read in and appened to str as a string
    String str = "";
    try {
      Scanner read = new Scanner(new File("Map" + mapNum + ".txt"));
      while (read.hasNext()) {
        String character = read.next();
        str = str + character;         
      }
      read.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found!");
    } 
    
    // string is converted to char array using .charAt() and added to CurrentMap
    int k = 0;
    for (int i = 0; i < CurrentMap.length; i++){
      for (int j = 0; j < CurrentMap[i].length; j++) {
        CurrentMap[i][j] = str.charAt(k);
        k++;
      }
    }
    
    // sets boolean array to all false
    for (int i = 0; i < visited.length; i++){
      for (int j = 0; j < visited[i].length; j++) {
        visited[i][j] = false;
      }
    }

    // reveals start location 
    Point start = findStart();
    reveal(start);
  }

  /** Retrieves character at location p of character array
  *   @param p current location of hero
  */ 
  public char getCharAtLoc(Point p) {
    return CurrentMap[(int) p.getX()][(int) p.getY()];
  }

  /** String representation of a map object
  *   @return string representation of this map
  */
  public String mapToString(Point p) {
    String s = "";
    for (int i = 0; i < visited.length; i++){
      for (int j = 0; j < visited[i].length; j++) {
        if (p.getX() == i && p.getY() == j) {
          s += "* ";
        } else if (visited[i][j] == false) {
          s += "x ";
        } else {
          s += CurrentMap[i][j] + " ";
        }
      }
      s += "\n";
    }
    return s.substring(0, s.length() - 1); //removes last "\n"
  }  
  
  
  /** Retrieves location of the start
  *   @return coordinate values of the start  
  */ 
  public Point findStart() {
    Point location = new Point();
    for (int i = 0; i < CurrentMap.length; i++){
      for (int j = 0; j < CurrentMap[i].length; j++) {
        if (CurrentMap[i][j] == 's') {
          location.setLocation(i, j);
        }
      }
    }
    return location;  
  }
  
  /** Switches boolean value of location to true 
  */ 
  public void reveal(Point p) {
    visited[(int)p.getX()][(int)p.getY()] = true;
  }

  
  /** Changes character 'm' or 'i' to 'n'
  */ 
  public void removeCharAtLoc(Point p) {
    if (getCharAtLoc(p) == 'm' || getCharAtLoc(p) =='i') {
      CurrentMap[(int)p.getX()][(int)p.getY()] = 'n';
    }
  }

}