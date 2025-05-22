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
    //hidden info - map of mines/nums underneath // user should not see this 
    int gridSize = getGridSize(selectedlevel);
    int[][] hiddenGrid = new int[gridSize][gridSize]; 
    for (int[] rowArr : hiddenGrid) Arrays.fill(rowArr, -2); // clears previous game grid
    for (int[] rowArr : hiddenGrid) Arrays.fill(rowArr, 0); // Fill hiddenGrid with 0s
    setMines(gridSize, hiddenGrid);
    setNumbers(hiddenGrid);
    return hiddenGrid;
  }

  public int[][] setDisplayGrid(int selectedlevel){
    //the grid the user should seeupdates based on revealed tiles by user
    int gridSize = getGridSize(selectedlevel);
    int[][] displayGrid = new int[gridSize][gridSize]; 
    for (int[] rowArr : displayGrid) Arrays.fill(rowArr, -2); // clears previous game grid
    for (int[] rowArr : displayGrid) Arrays.fill(rowArr, 0); // Fill hiddenGrid with 0s
    return displayGrid;
  }


  private int getGridSize(int selectedlevel) {
    //depends on level selected //this determines no. of mines too
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
    //depends on level selected
    int numOfMines = gridSize;
    System.out.println("numOfMines: " + numOfMines);
    //place mines inside hidden grid at coordinate
    int minesPlaced = 0;
    System.out.println();
    while (minesPlaced < numOfMines){
      //generate random coordinates
      int row = (int) (Math.random() * hiddenGrid.length);
      int col = (int) (Math.random() * hiddenGrid[0].length);
      // int[] mine =  new int[] {row, col};
      if (hiddenGrid[row][col] != -1){ // if mine not here already
        hiddenGrid[row][col] = -1; //add mine (represented by -1)
        System.out.println("mine["+minesPlaced+"]: ("+row+","+col+")");
        minesPlaced ++;
      }
    }
    System.out.println();
  }

  private void setNumbers(int[][] hiddenGrid){
    System.out.println("HERE");
    for (int row = 0; row < hiddenGrid.length; row++) {
      for (int column = 0; column < hiddenGrid[0].length; column++) {
        if (hiddenGrid[row][column] == -1) continue;
        int count = 0;
        for (int x = -1; x <= 1; x++) {
          for (int y = -1; y <= 1; y++) {
            int nRow = row + x, nColumn = column + y;
            if (nRow >= 0 && nRow < hiddenGrid.length && nColumn >= 0 && nColumn < hiddenGrid[0].length) {
              if (hiddenGrid[nRow][nColumn] == -1) count++;
            }
          }
        }
        hiddenGrid[row][column] = count;
      }
    }
  }




}
