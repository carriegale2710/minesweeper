/*SECTION 1. MinesweeperApp class (main class)

  THIS CLASS SHOULD:
  - Handling game startup and shutdown.
  - Starting and managing the main game loop and game flow.
  - Creates object instances for GameBoard, BoardPrinter, and GameController
  - Passing control between the controller, model, and view as needed.
  - Read user input commands -> pass them to controller

  IT SHOULD NOT:
  - contain any game logic, board setup, or rendering 

  What are the other classes for?
  1. controller/GameController - game logic/rules
  2. model/GameBoard -> game data/grid info
  3. view/BoardDisplay -> game display/rendering to console
 */

import java.util.Scanner;

import controller.GameRules;
import model.GameBoard;
import view.GameDisplay;


public class MineSweeperApp {
  
  
  public static void main(String[] args) {
    //userinputs
    Scanner scan = new Scanner(System.in);
    
    //create instances
    MineSweeperApp MineSweeper = new MineSweeperApp();
    GameRules gameController = new GameRules();
    GameDisplay gameDisplay = new GameDisplay();
    
    //print game intro
    gameDisplay.gameIntro();
    
    //user selects game level
    gameDisplay.gameLevelPrompt();
    int selectedLevel = levelSelectInput(scan); 
 
    //set the board
    GameBoard gameboard = new GameBoard();
    int[][] hiddenGrid = gameboard.setHiddenGrid(selectedLevel);
    int[][] displayGrid = gameboard.setDisplayGrid(selectedLevel);

    //play move -- add loop that continues until win/lose condition is met
    int numOfMoves = 3; //for testing change later
    
    while (numOfMoves > 0){
      numOfMoves = numOfMoves -1;
      System.out.println();
      System.err.println("num of moves left" + numOfMoves);
      
      gameDisplay.printHiddenGrid(hiddenGrid);
      gameDisplay.printDisplayGrid(displayGrid);

      int[] coordinate = playerMoveInput(scan);
      
      boolean gameOver = gameController.isMineAt(hiddenGrid, coordinate); //check is selected coordinate contains a mine
      boolean gameWon = gameController.checkWin(hiddenGrid, displayGrid);
      
      gameDisplay.moveResultMessage(gameOver, gameWon); // prints out result of player's move (win/loss/safe)
      
      //end the game if win/loss condition is met
      if (gameOver || gameWon) {
        System.out.println("Goodbye :)");
        System.exit(0); 
      }

    }

    //close scanner
    scan.close();
  }

  
  static int levelSelectInput(Scanner scan) {
    int selectedLevel = scan.nextInt();
    
    while (selectedLevel < 1 || selectedLevel > 5){
      System.out.println("Invalid Input. Please enter level 0-4 only.");
      selectedLevel = scan.nextInt();
    } 
    System.out.println();
    System.out.println("You selected: Level " + selectedLevel);
    System.out.println("Loading...");
    System.out.println();
    return selectedLevel;
  }

  public static int[] playerMoveInput(Scanner scan) {
    System.out.println();
    System.out.println("--- NEXT MOVE --- ");
    System.out.println();
    System.out.println("Please enter a row and column to reveal the square.");
    System.out.print("Enter row: ");
    int row = scan.nextInt();
    System.out.print("Enter column: ");
    int column = scan.nextInt();
    System.out.println("You entered: " + row+ ","+ column);
    // add input validation - out of bounds, NaN, too many inputs etc.
    int [] coordinate = new int[] {row, column};
    return coordinate;
  }

  public static String playAgainPrompt(Scanner scan) {
    //play again prompt -> if yes a new game should start with new values
    System.out.println("Play Again? Enter 'Y'..");
    String response = scan.nextLine();  //find way to start game again if 'Y' inputted 
    //if yes resetGame? - create new instance of the MineSweeperApp? //can track num of games won vs lost this way too
    return response;
  }



  



  

}

