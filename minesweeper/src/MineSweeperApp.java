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
    gameDisplay.gameBootUp(); 
    
    //user selects game level
    gameDisplay.gameLevelPrompt();
    int selectedLevel = levelSelectInput(scan); 
    
    //set the board
    GameBoard gameboard = new GameBoard();
    int[][] hiddenGrid = gameboard.setHiddenGrid(selectedLevel);
    System.out.println(hiddenGrid);


    //close scanner
    scan.close();
  }
  
  static int levelSelectInput(Scanner scan) {
    int selectedLevel = scan.nextInt();
    
    while (selectedLevel < 1 || selectedLevel > 5){
      System.out.println("Invalid Input. Please enter level 0-4 only.");
      selectedLevel = scan.nextInt();
    } 
    System.out.println("You selected: Level " + selectedLevel);
    System.out.println("Loading...");
    return selectedLevel;
  }

  

  
  



}

