/*SECTION 2. GameBoard class (`model` package):

  THIS CLASS SHOULD:
  - mainly handle inital board setup and updating its state only
  - Holds the grid data/values in array (hiddenGrid, displayGrid)
  - Handles grid setup (placing mines, calculating numbers)
  - Provides getters/setters for grids - updates the board

  IT SHOULD NOT:
 - Handle user input/output or interact with user (view)
 - contain any game logic/rules (controller)
 - decide when the game starts/ends or other game flow ()

 */

package model;

import java.util.Arrays;

public class GameBoard { 

  public int[][] setHiddenGrid(int selectedlevel){
    int gridSize = getGridSize(selectedlevel);
    int[][] hiddenGrid = new int[gridSize][gridSize]; //hidden info - map of mines/nums underneath
    for (int[] rowArr : hiddenGrid) Arrays.fill(rowArr, -2); // clears previous game grid
    for (int[] rowArr : hiddenGrid) Arrays.fill(rowArr, 0); // Fill hiddenGrid with 0s
    setMines(0, hiddenGrid);
    System.out.println(hiddenGrid.toString());
    return hiddenGrid;
  }
  
  
  private int getGridSize(int selectedlevel) {
    int gridSize = 0;
    if (selectedlevel == 1){
      gridSize = 3;
    } else if (selectedlevel == 2){
      gridSize = 5;
    } else if (selectedlevel == 3){
      gridSize = 10;
    } else if (selectedlevel == 4){
      gridSize = 15;
    } else {
      System.out.println("This grid is not available at the moment."); //add other levels here later
    }
    System.out.println("gridSize: " + gridSize);
    return gridSize;
  }
  
  private void setMines(int gridSize, int[][] hiddenGrid){
  
    int numOfMines = gridSize;
    int randomInt = (int) (Math.random() * numOfMines);
    int minesPlaced = numOfMines;
    while (minesPlaced > 0){
      System.out.println("numOfMines: " + numOfMines);
      System.out.println("randomInt: " + randomInt);
      minesPlaced = minesPlaced -1;
    }
  }
}
