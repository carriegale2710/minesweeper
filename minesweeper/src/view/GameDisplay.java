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
    if (gameOver){
      //insert method here to display the hidden grid (or just mine locations)
      System.out.println("BOOM! You stepped on a mine :(");
    } else if (gameWon) {
      System.out.println("Congrats! You won the game!");
    } else {
      System.out.println("No mines here, your safe.");
    }
  }



  //SECTION - DISPLAY GRID

  public void printHiddenGrid(int[][] hiddenGrid){
    //update diaply grid based on changes to the hiddengrid
    System.out.println();
    System.out.println("hidden grid:");
    for (int[] rowArr : hiddenGrid) {
        System.out.print("|"); 
        for (int cell : rowArr) {
          System.out.print(cell); // print each cell
          System.out.print("|"); 
        }
      System.out.println(); // move to next line after each row
    } 
  }
  

  public void printDisplayGrid(int[][] displayGrid){
    //update diaply grid based on changes to the display grid 
    System.out.println();
    System.out.println("display grid:");
    for (int[] rowArr : displayGrid) {
        System.out.print("|"); 
        for (int cell : rowArr) {
            System.out.print(cell); // print each cell
            System.out.print("|"); 
        }
        System.out.println(); // move to next line after each row
    } 
  }


  

    
}
