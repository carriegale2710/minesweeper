import board.*;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;



public class MineSweeper {

    //SECTION - current game being played
    
    public static void main(String[] args) throws Exception {
        
        //create new game object
        MineSweeper currentGame = new MineSweeper();

        //create new board object for setup/updating the board at each turn
        Board currentBoard = new Board();
        
        //board setup: create empty grids to fill later
        int[][] displayGrid = new int[3][3]; //visible info - what user sees - updates on each player move
        int[][] hiddenGrid = new int[3][3]; //hidden info - map of mines/nums underneath - does not change -setup up at beginning of game
    

        //setting up new game
        currentBoard.setupGrids(currentBoard.displayGrid, currentBoard.hiddenGrid); // pass Board to setup
        currentBoard.renderHiddenGrid(currentBoard.hiddenGrid); // pass Board to render
        currentBoard.renderDisplayGrid(currentBoard.displayGrid);

        // pass Board to player move logic, etc.

        playerMove(currentBoard); // TODO: make this a loop for multiple turns
    }



    

    //SECTION - game logic -> //TODO - move to play package

    static void playerMove(Board currentBoard) {
        
        // TODO: add input validation - out of bounds, NaN, too many inputs etc.
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter row: ");
        int row = scan.nextInt();
        System.out.print("Enter column: ");
        int column = scan.nextInt();
        scan.close();
        
        //separate this logic out?? - lose game 
        if (currentBoard.hiddenGrid[row][column] == -1) {
            currentBoard.displayGrid[row][column] = -1;
            currentBoard.renderDisplayGrid(currentBoard.displayGrid);
            System.out.println("boom - you stepped on a mine :(");
            //TODO - (bonus) reveal all the squares/mines after
        } else {
            currentBoard.displayGrid[row][column] = currentBoard.hiddenGrid[row][column];
            currentBoard.renderDisplayGrid(currentBoard.displayGrid);
            System.out.println("safe - no mines");
            if (checkWin(currentBoard)) {
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


