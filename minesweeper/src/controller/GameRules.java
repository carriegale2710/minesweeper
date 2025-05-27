/*SECTION 4. GameController class (`controller` package)

  IT SHOULD:
   - Processes player moves/commands, 
   - Checks win/lose conditions - determines result of each move
   - main game logic goes here
   - Methods like playerMove(GameBoard board), checkWin(GameBoard board)
 
  IT SHOULD NOT:
  - directly handle any user input/output (main)
  - directly manage board's internal data structures (model)
  - contain any board setup logic (model)

   */

package controller;

public class GameRules {

  //SECTION - Game lose condition -> checks for mines at a specific coordinate
  public boolean isMineAt(Integer[][] hiddenGrid, int[] coordinates) {
    System.out.println("Checking for mines...");
    //coordinates from user input passed in too
    int row = coordinates[0];
    int column = coordinates[1];
    //only needs to check one element in whole grid array -the one the user choose
    boolean mineIsPresent = hiddenGrid[row][column] == -1;
    return mineIsPresent ; //GAME LOST if true
  }
  
  //SECTION - game win condition --> needs to check all non-mines are revealed
  public boolean checkWin(Integer[][] hiddenGrid, String[][] displayGrid) {
    System.out.println("Checking win...");
    //loop thru each row
    for (int row = 0; row < hiddenGrid.length; row++) { 
      //loop cols/cells in row
      for (int col = 0; col < hiddenGrid[0].length; col++) { //loop cols in row
        //check if any non-mine cell is still hidden
        if (hiddenGrid[row][col] != -1 && displayGrid[row][col] == "?") { 
          return false; //not yet
        }
      }
    }
    return true; //GAME WON
    
  }
  
  
}
