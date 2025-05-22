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

  //if no non-mines remaining - user wins
  
  public boolean checkWin(int[][] hiddenGrid, int[][] displayGrid) {
    //needs to check all elements
      for (int row = 0; row < hiddenGrid.length; row++) {
          for (int column = 0; column < hiddenGrid[0].length; column++) {
              if (hiddenGrid[row][column] != -1 && displayGrid[row][column] == -2) {
                  return false;
              }
          }
      }
      return true;
  }


  public boolean isMineAt(int[][] hiddenGrid, int[] coordinate) {
    //coordinates from user input passed in too
    int row = coordinate[0];
    int column = coordinate[1];
    //only needs to check one element in whole grid array -the one the user choose
    boolean mineIsPresent = hiddenGrid[row][column] == -1;
    return mineIsPresent ;
  }




    
}
