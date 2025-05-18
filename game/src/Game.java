import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public int[][] displayGrid = new int[3][3];
    public int[][] hiddenGrid = new int[3][3];

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.setupGrids();
        game.renderHiddenGrid();
        game.renderDisplayGrid();
        game.playerMove();
    }

    public void setupGrids() {
        // Fill hiddenGrid with 0s
        for (int[] rowArr : hiddenGrid) Arrays.fill(rowArr, 0);

        // Randomly place mines
        int mineCount = 2; // Change this for more mines
        Random rand = new Random();
        int placed = 0;
        while (placed < mineCount) {
            int row = rand.nextInt(hiddenGrid.length);
            int column = rand.nextInt(hiddenGrid[0].length);
            if (hiddenGrid[row][column] != -1) {
                hiddenGrid[row][column] = -1;
                placed++;
            }
        }

        // Calculate numbers for adjacent mines
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

        // Fill displayGrid with -2 (unrevealed)
        for (int[] rowArr : displayGrid) Arrays.fill(rowArr, -2);
    }

    // Render the hidden grid to console
    public void renderHiddenGrid() {
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
    public void renderDisplayGrid() {
        System.out.println("Display Grid:");
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