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

  public Integer[][] setHiddenGrid(int gridSize){
    //hidden info - map of mines/nums underneath // user should not see this 
    Integer[][] hiddenGrid = new Integer[gridSize][gridSize];
    for (Integer[] rowArr : hiddenGrid) Arrays.fill(rowArr, 0);
    setMines(hiddenGrid);
    setNumbers(hiddenGrid);
    return hiddenGrid;
  }

  public String[][] setDisplayGrid(int gridSize){
    //the grid the user should see updates based on revealed tiles by user
    String[][] displayGrid = new String[gridSize][gridSize];
    for (String[] rowArr : displayGrid) Arrays.fill(rowArr, "?");
    return displayGrid;
  }

  public String[][] revealCoordinate(int[] coordinates, Integer[][] hiddenGrid, String[][] displayGrid) {
    // get coordinates
    int row = coordinates[0];
    int col = coordinates[1];
    
    //get value of that specific coordinate
    int hiddenValue = hiddenGrid[row][col];
    
    //reveal the hidden value on the display grid
    if (hiddenValue == -1){
      displayGrid[row][col] = "*"; //mine tiles
    } else if (hiddenValue == 0) {
      displayGrid[row][col] = " "; //blank tiles
    } else {
      displayGrid[row][col] = Integer.toString(hiddenValue) ; //number tiles
    }
    return displayGrid;
  }

  //reveal the hidden value on the display grid for all mines
  public void revealAllMines(Integer[][] hiddenGrid, String[][] displayGrid) {
    //loop through each row
    for (int row = 0; row < hiddenGrid.length; row ++){
      //loop through each col/cell in this row
      for (int col = 0; col < hiddenGrid[0].length; col++){
        int currentCell = hiddenGrid[row][col];
        if (currentCell == -1) {//if cell has mine, skip
          displayGrid[row][col] = "*";
        }
      }
    }
  }
  
  public int getGridSize(int selectedlevel) {
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
    System.out.println("grid size: " + gridSize + "x" + gridSize);
    return gridSize;
  }
  
  private void setMines(Integer[][] hiddenGrid){
    //depends on level selected
    int numOfMines = hiddenGrid.length;
    System.out.println("no.of mines: " + numOfMines);
    //place mines inside hidden grid at coordinate
    int minesPlaced = 0;
    while (minesPlaced < numOfMines){
      //generate random coordinates
      int row = (int)(Math.random()*numOfMines);
      int col = (int)(Math.random()*numOfMines);
      // NOTE - MINE VALIDATION: only if mine not at this index already
      if (hiddenGrid[row][col] != -1){ 
        hiddenGrid[row][col] = -1; //add mine at this index in array (represented by -1)
        minesPlaced ++;
      }
    }
    System.out.println();
  }

  private void setNumbers(Integer[][] hiddenGrid){
    //loop through each row
    for (int row = 0; row < hiddenGrid.length; row ++){
      //loop through each col/cell in this row
      for (int col = 0; col < hiddenGrid[0].length; col++){
        int currentCell = hiddenGrid[row][col];
        if (currentCell == -1) {//if cell has mine, skip
          continue; //skip
        }
        //loop thru neighbours for mines (has value of -1) if yes, add to count.  (there are 8 neighbours)
        //check for each column before and after current (x)
        int minesCount = 0; 
        for (int x = -1; x <=1; x++) {
          for (int y=-1; y<=1; y++){
            //coordinates of neigbours
            int nRow = row+x;
            int nCol = col+y;
            //check the coordinates are inside grid bounds (less than gridvalue)
            if ((nRow < 0 || nRow >= hiddenGrid.length) || (nCol < 0 || nCol >= hiddenGrid[0].length)){
              continue; //skip
            }
            //if value is -1, add to count
            if (hiddenGrid[nRow][nCol] == -1){
              minesCount++;
            }  
            //update to final no. of mines counted in neigbour tiles
            hiddenGrid[row][col] = minesCount;
          }
        }
      }
    }
  }






}
