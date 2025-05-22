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
  private int[][] displayGrid; //what user sees
  
  
  public void gameBootUp() {
    System.out.println("Welcome to MineSweeper");

  }

  public void gameLevelPrompt() {
    System.out.println("Please choose the grid size you want to play, enter 1/2/3/4");
    System.out.println("Level 1: 3x3");
    System.out.println("Level 2: 5x5");
    System.out.println("Level 3: 10x10");
    System.out.println("Level 4: 15x15");
  }


  //SECTION - DISPLAY GRID
  
  public int[][] getDisplayGrid() {
    return displayGrid;
  }

  public void setDisplayGrid(int[][] hiddenGrid){
    //update diaply grid based on changes to the hiddengrid
    displayGrid = hiddenGrid; 
  }


  

    
}
