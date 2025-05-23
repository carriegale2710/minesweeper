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
  
  //NOTE - print intro on bootup (could put instructions/game rules here later if needed for better UX)
  public void gameIntro() {
    System.out.println();
    System.out.println("Welcome to MineSweeper");

  }

  //NOTE - prints at bootup only - user selects level aka grid size
  public void gameLevelPrompt() {
    System.out.println();
    System.out.println("Please choose the grid size you want to play, enter 1/2/3/4");
    System.out.println("Level 1: 3x3");
    System.out.println("Level 2: 5x5");
    System.out.println("Level 3: 10x10");
    System.out.println("Level 4: 15x15");
  }

  //NOTE - prints result of player's previous move
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

  //SECTION - Rendering the GRID

  //NOTE - prints either hidden or display grid 

  public <T> void printGrid(T[][] anyGrid){ 

    ArrayList<String> columnKeys = new ArrayList<>();

    //row loop
    for (int rowIndex = 0; rowIndex < anyGrid.length; rowIndex++) {
      // print row keys
      if (rowIndex < 10){
        columnKeys.add(rowIndex+ "  ");
        System.out.print(" "+rowIndex + " |"); 
      } else {
        columnKeys.add(rowIndex+ " ");
        System.out.print(rowIndex + " |"); 
      }
      T[] rowArr = anyGrid[rowIndex];

      //cell loop 
      for (T cell : rowArr) { 
        //print inner tile values
        if (cell.equals(-1)){ //for alignment only
        System.out.print(cell + " |"); //row value
        } else {
          System.out.print(" " +cell + " |"); //row value
        }
      }
      System.out.println(); // move to next line after each row

    } 

    // get column keys
    System.out.println("     " + columnKeys.stream()
      .map(Object::toString)
      .collect(java.util.stream.Collectors.joining(" ")
      
    ));
  } 
}
