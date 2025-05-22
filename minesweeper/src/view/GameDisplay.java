/*SECTION 3. BoardDisplay class (`view` package)

    THIS CLASS SHOULD:
    - only display information on the console
    - render grids and text
    - re-render on each player turn

    IT SHOULD NOT:
    - Contain any game logic
    - modify the board data 
    - handle user input
    - set up/initalise the board (model)
 */

package view;

import java.util.ArrayList;
import java.util.Arrays;

public class GameDisplay {

  //SECTION Game messages
  

  public void gameIntro() {
    System.out.println();
    System.out.println("Welcome to MineSweeper");

  }

  public void gameLevelPrompt() {
    System.out.println();
    System.out.println("Please choose the grid size you want to play, enter 1/2/3/4");
    System.out.println("Level 1: 3x3");
    System.out.println("Level 2: 5x5");
    System.out.println("Level 3: 10x10");
    System.out.println("Level 4: 15x15");
  }

  public void moveResultMessage(boolean gameOver, boolean gameWon) {
    //only shows when game is over (either win/loss)
    System.out.println();
    System.out.println("-----------------------------");
    if (gameOver){
      //insert method here to display the hidden grid (or just mine locations)
      System.out.println("BOOM! You stepped on a mine :(");
    } else if (gameWon) {
      System.out.println("Congrats! You won the game! :D");
    } else {
      System.out.println("No mines here, your safe. :)");
    }
    System.out.println("-----------------------------");
  }



  //SECTION - DISPLAY GRID


  public <T> void printGrid(T[][] anyGrid){ //either hidden or display grid is passed in
    // int length = anyGrid.length;
    // T[][] copyGrid = Arrays.copyOf(anyGrid, length);
    // String[][] printGrid = (String[][]) copyGrid;
    ArrayList<String> columnKeys = new ArrayList<>();

    //row loop
    for (int rowIndex = 0; rowIndex < anyGrid.length; rowIndex++) {
      if (rowIndex < 10){
        columnKeys.add(rowIndex+ "  ");
        System.out.print(" "+rowIndex + " |"); //row keys
      } else {
        columnKeys.add(rowIndex+ " ");
        System.out.print(rowIndex + " |"); //row keys
      }
      T[] rowArr = anyGrid[rowIndex];

      //cell loop 
      for (T cell : rowArr) { 
        if (cell.equals(-1)){ //for alignment only
        System.out.print(cell + " |"); //row value
        } else {
          System.out.print(" " +cell + " |"); //row value
        }
      }
      System.out.println(); // move to next line after each row

    } 
    // column index label
    System.out.println("     " + columnKeys.stream()
      .map(Object::toString)
      .collect(java.util.stream.Collectors.joining(" ")
      
    ));
  }



  

    
}
