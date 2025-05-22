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

    //set up grid values on board
    int gridSize = gameboard.getGridSize(selectedLevel);
    Integer[][] hiddenGrid = gameboard.setHiddenGrid(gridSize);
    String[][] displayGrid = gameboard.setDisplayGrid(gridSize);
    // Boolean[][] visibilityGrid =  gameboard.setVisibilityGrid(gridSize);

    //setup up win/lose conditions
    boolean gameOver = false;
    boolean gameWon = false;
    
    //NOTE - game running loop --> loop that continues until win/lose condition is met
    
    //print the hidden grid (for debugging)
    System.out.println("\nhidden grid:");
    gameDisplay.printGrid(hiddenGrid);
    
    while (gameOver == false && gameWon == false){
      System.out.println("\n--- NEXT MOVE --- ");

      //print the display grid (what user will see)
      System.out.println("\ncurrent grid:");
      gameDisplay.printGrid(displayGrid);
      
      int[] coordinate = playerMoveInput(scan);
      
      gameOver = gameController.isMineAt(hiddenGrid, coordinate); //check is selected coordinate contains a mine
      // gameWon = gameController.checkWin(hiddenGrid, displayGrid);
      gameDisplay.moveResultMessage(gameOver, gameWon); // prints out result of player's move (win/loss/safe)
      
    }

    //end the game if win/loss condition is met
    if (gameOver || gameWon) {
      System.out.println("Game finished :)");
      System.exit(0); 
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
    System.out.println();
    System.out.println("----- SETTING UP NEW GAME -----");
    System.out.println();
    System.out.println("Loading...");
    return selectedLevel;
  }


  public static int[] playerMoveInput(Scanner scan) {

    System.out.println("\nPlease enter a row and column to reveal the square.");

    System.out.print("- Enter row: ");
    int row = scan.nextInt();
    //error handle

    System.out.print("- Enter column: ");
    int column = scan.nextInt();
    //error handle

    System.out.println("\nYou entered: (" + row+ ","+ column+")");
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

