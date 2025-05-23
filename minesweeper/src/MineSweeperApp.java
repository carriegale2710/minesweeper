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

    Scanner scan = new Scanner(System.in); //userinputs

    //create instances / import methods 
    MineSweeperApp MineSweeper = new MineSweeperApp();
    GameRules gameStatus = new GameRules();
    GameDisplay gameDisplay = new GameDisplay();

    //NOTE - Game App boots up
    gameDisplay.gameIntro(); 
    gameDisplay.gameLevelPrompt();
    int selectedLevel = levelSelectInput(scan);  
    
    //NOTE - GAME SETUP
    GameBoard gameboard = new GameBoard(); //generate new board
    int gridSize = gameboard.getGridSize(selectedLevel); //set grid size 
    //generate tile values
    Integer[][] hiddenGrid = gameboard.setHiddenGrid(gridSize); 
    String[][] displayGrid = gameboard.setDisplayGrid(gridSize); 
    //reset win/lose conditions
    boolean gameOver = false;
    boolean gameWon = false;

    //NOTE - The main game running loop --> continues until win/lose condition is met:
    while (gameOver == false && gameWon == false) {
      gameDisplay.printGrid(hiddenGrid); //(for debugging)
      gameDisplay.printGrid(displayGrid); 

      System.out.println("\n--- NEXT MOVE --- ");
      int[] coordinates = playerMoveInput(scan, gridSize); //takes user command input
      gameboard.revealCoordinate(coordinates, hiddenGrid, displayGrid); 
      gameOver = gameStatus.isMineAt(hiddenGrid, coordinates); 
      gameWon = gameStatus.checkWin(hiddenGrid, displayGrid); 
      gameDisplay.moveResultMessage(gameOver, gameWon); // prints out result of player's move (win/loss/safe)
    }
    
    //NOTE - END OF GAME 
    //end the game if win/loss condition is met
    if (gameOver || gameWon) {
      //revealAllMines
      System.out.println("Game finished :)");
    }

    scan.close();
    System.exit(0); 
  }

  //NOTE - CLASS METHODS
  
  static int levelSelectInput(Scanner scan) {
    int levelsAvailable = 4; // this could change 
    int selectedLevel = -1; //default value
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
      try {
        //is the input a integer?
        int input = Integer.parseInt(rawInput);
        inputIsInt = true;
        //is the input within bounds?
        inputIsWithinBounds = inputIsWithinBounds(input, minLimit, maxLimit);
        if (!inputIsWithinBounds){
          throw new Exception();
        } else {
          validInput = input;
        }
      } catch (Exception e) {
        if (!inputIsWithinBounds) {
          System.out.println("Invalid input:  Please enter level number from " + minLimit + "-" + maxLimit);
        } else {
          System.out.println("Error: " + e.getMessage());
        }
      }
    }
    return validInput;
  }

  public static boolean inputIsWithinBounds (int input, int minLimit, int maxLimit) {
    boolean inputValid = false;
    if (input >= minLimit && input <= maxLimit ) {
      inputValid = true;
    } else {
      System.out.println("Input of " + input + " is invalid. Please enter a number from "+ minLimit +" to "+ maxLimit +" only.");
    }
    return inputValid;
  }
  
  //play again prompt -> if yes a new game should start with new values 
    //can track num of games won vs lost this way too?
  public static String playAgainPrompt(Scanner scan) {
    System.out.println("Play Again? Enter 'Y'..");
    String response = scan.nextLine();  
    //find way to start game again if 'Y' inputted 
      //create new instance of the MineSweeperApp? 
    return response;
  }

}

