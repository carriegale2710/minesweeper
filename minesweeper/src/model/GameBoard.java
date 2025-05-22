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
    //the grid the user should seeupdates based on revealed tiles by user
    String[][] displayGrid = new String[gridSize][gridSize];
    for (String[] rowArr : displayGrid) Arrays.fill(rowArr, "?");
    return displayGrid;
  }

  public String[][] revealCoordinate(int[] coordinates, Integer[][] hiddenGrid, String[][] displayGrid) {
    System.out.println("revealCoordinate running");

    //coordinates
    System.out.println("coordinates received: " + coordinates);
    int row = coordinates[0];
    int col = coordinates[1];
    
    //needs to swap the displayGrid value at coodinates with the same one as hiddenGrid 
    int hiddenValue = hiddenGrid[row][col];
    System.out.println("value: " + hiddenValue);

    //if number 1-8, print in that number
    //if value is 0, print in " "
    //if value is -1, print in "*"

    if (hiddenValue == -1){
      displayGrid[row][col] = "*";
    } else if (hiddenValue == 0) {
      displayGrid[row][col] = " ";
    } else {
      displayGrid[row][col] = Integer.toString(hiddenValue) ;
    }

    return displayGrid;
  }
  
  // public Boolean[][] setVisibilityGrid(int gridSize){
  //   Boolean[][] visibilityGrid = new Boolean[gridSize][gridSize];
  //   for (Boolean[] rowArr : visibilityGrid) Arrays.fill(rowArr, false); //set all to false
  //   return visibilityGrid;
  // }

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
    System.out.println();
    System.out.println("Mine Coodinates randomly generated: ");
    System.out.println("- Key: (row,column)");


    //place mines inside hidden grid at coordinate
    int minesPlaced = 0;
    while (minesPlaced < numOfMines){
      //generate random coordinates
      int row = (int)(Math.random()*hiddenGrid[0].length);
      int col = (int)(Math.random()*hiddenGrid[1].length);
      // int[] mine =  new int[] {row, col};
      if (hiddenGrid[row][col] != -1){ // NOTE - MINE VALIDATION: only put if mine not at this index already
        hiddenGrid[row][col] = -1; //add mine (represented by -1)
        System.out.println("- mine"+minesPlaced+": ("+row+","+col+")");
        minesPlaced ++;
      }
    }
    System.out.println();
  }

  private void setNumbers(Integer[][] hiddenGrid){
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
