import currentBoard.*;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;



public class MineSweeper {

    
    public static void main(String[] args) throws Exception {
        MineSweeper currentGame = new MineSweeper();
        
        Board currentBoard = new Board();
        DisplayGrid display = new DisplayGrid();

        //setting up new game
        Board.setupGrids(currentBoard); // pass Board to setup
        display.renderHiddenGrid(currentBoard); // pass Board to render
        display.renderDisplayGrid(currentBoard);

        // pass Board to player move logic, etc.

        playerMove(currentBoard); // TODO: make this a loop for multiple turns
    }

    //TODO - move these methods to separate classes to bvrek up code

    //SECTION new game setup
    //SECTION - rendering the UI //TODO - move to render package

    

    //SECTION - game logic -> //TODO - move to play package

    static void playerMove(Board currentBoard) {
        
        // TODO: add input validation - out of bounds, NaN, too many inputs etc.
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter row (0-2): ");
        int row = scan.nextInt();
        System.out.print("Enter column (0-2): ");
        int column = scan.nextInt();
        scan.close();
        
        //separate this logic out?? - lose game 
        if (currentBoard.hiddenGrid[row][column] == -1) {
            currentBoard.displayGrid[row][column] = -1;
            renderDisplayGrid();
            System.out.println("boom - you stepped on a mine :(");
            //TODO - (bonus) reveal all the squares/mines after
        } else {
            currentBoard.displayGrid[row][column] = currentBoard.hiddenGrid[row][column];
            renderDisplayGrid();
            System.out.println("safe - no mines");
            if (checkWin()) {
                System.out.println("congrats you win!");
            }
        }
    }

        //if no non-mines remaining - user wins
    static boolean checkWin(Board currentBoard) {
        for (int row = 0; row < currentBoard.hiddenGrid.length; row++) {
            for (int column = 0; column < currentBoard.hiddenGrid[0].length; column++) {
                if (currentBoard.hiddenGrid[row][column] != -1 && currentBoard.displayGrid[row][column] == -2) {
                    return false;
                }
            }
        }
        return true;
    }



    


}


