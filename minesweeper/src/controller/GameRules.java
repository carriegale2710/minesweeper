/*SECTION 4. GameController class (`controller` package)

  IT SHOULD:
   - Processes player moves/commands, 
   - Checks win/lose conditions - determines result of each move
   - main game logic goes here
   - Methods like playerMove(GameBoard board), checkWin(GameBoard board)
 
  IT SHOULD NOT:
  - directly handle any user input/output (view)
  - directly manage board's internal data structures (model)
  - contain any board setup logic (model)

   */

package controller;

public class GameRules {


  
  
  public boolean isMineAt(Integer[][] hiddenGrid, int[] coordinate) {
    //coordinates from user input passed in too
    int row = coordinate[0];
    int column = coordinate[1];
    //only needs to check one element in whole grid array -the one the user choose
    boolean mineIsPresent = hiddenGrid[row][column] == -1;
    return mineIsPresent ;
  }
  
  public boolean checkWin(Integer[][] hiddenGrid, String[][] displayGrid) {
    //if no non-mines remaining - user wins --> needs to check all elements
    
    //loop thru each row
    for (int row = 0; row < hiddenGrid.length; row++) { 
      //loop cols/cells in row
      for (int col = 0; col < hiddenGrid[0].length; col++) { //loop cols in row
        //check if cell is still hidden 
        if (hiddenGrid[row][col] != -1 && displayGrid[row][col] == "?") {  //FIXME - boolean[][] = {[true,true],[]};
          return false; //not yet
        }
      }
    }
    return true;
    
  }
  
  
  
  
}
