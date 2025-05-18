import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public int[][] displayGrid = new int[3][3]; //what user sees
    public int[][] hiddenGrid = new int[3][3]; //hidden info - map of mines/nums underneath

    public static void main(String[] args) throws Exception {
        Game game = new Game();

        //setting up new game
        game.setupGrids();
        game.renderHiddenGrid();
        game.renderDisplayGrid(); 

        //starting the game
        game.playerMove(); // TODO: make this a loop for multiple turns
    }

    //TODO - move these methods to separate classes to bvrek up code

    //SECTION new game setup
    public void setupGrids() {

        // Fill hiddenGrid with 0s - reps empty mines
        for (int[] rowArr : hiddenGrid) Arrays.fill(rowArr, 0);

        //generate random mine placement
        int mineCount = 2; //add more mines
        Random rand = new Random();
        int placed = 0;
        
        //todo - test this, while loop might be risky
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

        // clears previous game grid
        for (int[] rowArr : displayGrid) Arrays.fill(rowArr, -2);
    }

    //SECTION - rendering the UI //TODO - move to render package


    // render the hidden grid
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

    //SECTION - game logic -> //TODO - move to play package



    // player move and lose condition

    public void playerMove() {
        // TODO: add input validation - out of bounds, NaN, too many inputs etc.
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter row (0-2): ");
        int row = scan.nextInt();
        System.out.print("Enter column (0-2): ");
        int column = scan.nextInt();
        scan.close();
        
        //separate this logic out?? - lose game 
        if (hiddenGrid[row][column] == -1) {
            displayGrid[row][column] = -1;
            renderDisplayGrid();
            System.out.println("boom - you stepped on a mine :(");
            //TODO - (bonus) reveal all the squares/mines after
        } else {
            displayGrid[row][column] = hiddenGrid[row][column];
            renderDisplayGrid();
            System.out.println("safe - no mines");
            if (checkWin()) {
                System.out.println("congrats you win!");
            }
        }
    }

    //if no non-mines remaining - user wins
    public boolean checkWin() {
        for (int row = 0; row < hiddenGrid.length; row++) {
            for (int column = 0; column < hiddenGrid[0].length; column++) {
                if (hiddenGrid[row][column] != -1 && displayGrid[row][column] == -2) {
                    return false;
                }
            }
        }
        return true;
    }
    
    


}


