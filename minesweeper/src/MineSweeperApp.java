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
    
    while (gameOver == false && gameWon == false) {
      System.out.println("\n--- NEXT MOVE --- ");
      //print the display grid (what user will see)
      System.out.println("\ncurrent grid:");
      gameDisplay.printGrid(displayGrid);

      int[] coordinates = playerMoveInput(scan, gridSize);
      gameOver = gameController.isMineAt(hiddenGrid, coordinates); //check if selected coordinate contains a mine
      // gameWon = gameController.checkWin(hiddenGrid, displayGrid);
      gameDisplay.moveResultMessage(gameOver, gameWon); // prints out result of player's move (win/loss/safe)
      gameboard.revealCoordinate(coordinates, hiddenGrid, displayGrid);
      
    }
    
    //end the game if win/loss condition is met
    if (gameOver || gameWon) {
      System.out.println("Game finished :)");
    }
    //close scanner
    scan.close();
    System.exit(0); 

  }
  
  static int levelSelectInput(Scanner scan) {
    int levelsAvailable = 4;
    int selectedLevel = -1;
    while (selectedLevel == -1){
      selectedLevel = intInput(scan, 1, levelsAvailable);
    }
    System.out.println("\nYou selected: Level " + selectedLevel);
    System.out.println("\n----- SETTING UP NEW GAME -----");
    System.out.println("\nLoading...");
    return selectedLevel;
  }

  public static int[] playerMoveInput(Scanner scan, int gridSize) {
    System.out.println("\nPlease enter a row and column to reveal the square.");
    System.out.print("- Enter row: ");
    int row = intInput(scan, 0, gridSize-1);
    System.out.print("- Enter column: ");
    int col = intInput(scan, 0, gridSize-1);
    // input confirmation
    System.out.println("\nYou entered: (" + row + ","+ col +")");
    int [] coordinate = new int[] {row, col};
    return coordinate;
  }
  

  static int intInput(Scanner scan,  int minLimit, int maxLimit) {
    boolean inputIsInt = false;
    boolean inputIsWithinBounds = false;
    int validInput = -1;
    
    //input validation
    while (!inputIsInt || !inputIsWithinBounds){
      String rawInput = scan.nextLine();
      // System.out.println("raw: " + rawInput);
      try {
        //is the input a integer?
        int input = Integer.parseInt(rawInput);
        // System.out.println("input: " +input);
        inputIsInt = true;
        //is the input within bounds?
        inputIsWithinBounds = inputIsWithinBounds(input, minLimit, maxLimit);
        if (!inputIsWithinBounds){
          throw new Exception();
        } else {
          validInput = input;
          // System.out.println("input: " + validInput);
        }
      } catch (Exception e) {
        if (!inputIsWithinBounds) {
          System.out.println("Invalid input:  Please enter level number from " + minLimit + "-" + maxLimit);
        } else {
          System.out.println("Error: " + e.getMessage());
        }
      }
    }
    // System.out.println("input: " + validInput);
    return validInput;
  }

  public static boolean inputIsWithinBounds (int input, int minLimit, int maxLimit) {
    // System.out.println(input + ", ("+ minLimit + "-"+ maxLimit  + ")");
    boolean inputValid = false;
    if (input >= minLimit && input <= maxLimit ) {
      inputValid = true;
    } else {
      System.out.println("Input of " + input + " is invalid. Please enter a number from "+ minLimit +" to "+ maxLimit +" only.");
    }
    return inputValid;
  }
  
  public static String playAgainPrompt(Scanner scan) {
    //play again prompt -> if yes a new game should start with new values
    System.out.println("Play Again? Enter 'Y'..");
    String response = scan.nextLine();  //find way to start game again if 'Y' inputted 
    //if yes resetGame? - create new instance of the MineSweeperApp? //can track num of games won vs lost this way too
    return response;
  }

}

