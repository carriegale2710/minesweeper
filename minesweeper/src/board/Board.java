package board;

import java.util.Arrays;
import java.util.Random;

//creates and manages the game grid

public class Board {



    int[][] displayGrid = new int[3][3]; //what user sees
    int[][] hiddenGrid = new int[3][3]; //hidden info - map of mines/nums underneath
    
    public static void setupGrids(int[][] displayGrid, int[][] hiddenGrid) {
        
        // clears previous game grid
        for (int[] rowArr : displayGrid) Arrays.fill(rowArr, -2);
            
        // Fill hiddenGrid with 0s - reps empty mines
        for (int[] rowArr : hiddenGrid) Arrays.fill(rowArr, 0);

        //generate random mine placement
        int mineCount = 2; //add more mines
        Random rand = new Random();
        int placed = 0;
        
        //note - test this, while loop might be risky
        while (placed < mineCount) {
            int row = rand.nextInt(hiddenGrid.length);
            int column = rand.nextInt(hiddenGrid[0].length);
            if (hiddenGrid[row][column] != -1) {
                hiddenGrid[row][column] = -1;
                placed++;
            }
        }

        //generate tile numbers (no. of adjacent mines)
        //nested for loop for 2d array grid (array inside array)
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

    
    // render the hidden grid
    public void renderHiddenGrid(int[][] hiddenGrid) {
        System.out.println("Hidden Grid:");
        for (int row = 0; row < hiddenGrid.length; row++) {
            for (int column = 0; column < hiddenGrid[0].length; column++) {
                if (hiddenGrid[row][column] == -1) {
                    System.out.print("* ");
                } else {
                    System.out.print(hiddenGrid[row][column] + " ");
                }
            }
            System.out.println();
        }
    }
    
    // Render the display grid to console
    public void renderDisplayGrid(int[][] displayGrid) {
        System.out.println("Display Grid:");
        //todo - should also add some grid lines and coordinate labels on axis too
        for (int row = 0; row < displayGrid.length; row++) {
            for (int column = 0; column < displayGrid[0].length; column++) {
                if (displayGrid[row][column] == -2) {
                    System.out.print("? "); //hidden
                } else if (displayGrid[row][column] == -1) {
                    System.out.print("* "); //mine
                } else {
                    System.out.print(displayGrid[row][column] + " "); //blank
                }
            }
            System.out.println();
        }
    }

    
}
