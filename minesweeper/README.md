## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

---

# Game Logic / Flow

- Link to diagram: https://whimsical.com/minesweeper-5Wn2mbHadbV3jbStGr1Ub4

![flowchart1](flowchart1.png)
![flowchart2](flowchart2.png)
![flowchart2.2](flowchart2.2.png)

## Phase 1: Game Loads onto Console for first time.

    - display intro message + prompt to start new game.
    - wait for player to yes 'Y' or 'yes' on Keyboard.
    - logic: Use a Scanner.

## Phase 2: New Game Starts

GENERATE HIDDEN MAP LAYER

- Location of mines (10) are randomly placed.

  - Hidden map/array is created.
  - 10 randomised coordinates on the map are given.
  - Mines are place inside.

- All other coordinates are given a number based on sum of adjacent mines.

  - Use an iterator?
  - place int number inside the given coordinate.

  Check NUMBER condition: How many squares surrounding the chosen coordinates contain a mine?

  - logic: Number must be from 0-8.
  - logic: all adjacent spaces of coordinates must be checked for mines.
  - rendering:
    - If 0, display blank space.
    - If 1-8, display number.

GENERATE + RENDER DISPLAYED MAP LAYER

- 10x10 Grid renders on console.
  - 100 hidden squares '?'
  - Extra space for coordinate labels on x/y axis.
- Displays user command input prompt message on console:
  - "Enter (x,y) coordinate to reveal square"
  - logic: x is row, y column in 10x10 grid map (array)

## Phase 3: Player Turn - Check Win/Continue/Lose Conditions.

---

1. Check LOSE condition: Is the square a mine?

   - logic: do any of mine coordinates the hidden map/array include the user's inputted coordinate?

   A. YES/TRUE: PLAYER LOSES.
   → print "boom! game over"
   -> ask to reset game
   → reset game (go to first step)

   B. NO/FALSE: Square is not a mine. -> go to 2.

2. Check WIN condition: Is the square the last remaining non-mine square?

   - logic: How many exist? (90, if 10 mines in 10x10 grid).
   - logic: How many have been revealed?

   YES/TRUE: PLAYER WINS.
   -> Display winner message.
   -> Reveal hidden map/array with all mine locations.
   -> Display user command prompt to reset the game. - "Play Again? Enter 'Y' to start a new game.

   NO/FALSE: Other no-mines squares still exist. -> Go to 3.

3. End of Player Turn, continue to next turn -> go to 1.

# Arrays/mapping

## Visible Map Array

---

|     | [0] | [1] | [2] |
| --- | --- | --- | --- |
| A = | ?   | ?   | ?   |
| --- | --- | --- | --- |
| B = | ?   | ?   | ?   |
| --- | --- | --- | --- |
| C = | ?   | ?   | ?   |

---

- Render condition
  - if revealed? adjacentMinesNum : "?";

## Hidden Map Array

---

|     | [0] | [1] | [2] |
| --- | --- | --- | --- |
| A = | -1  | 2   | 1   |
| --- | --- | --- | --- |
| B = | 2   | -1  | 1   |
| --- | --- | --- | --- |
| C = | 1   | 1   | -1  |

---

## KEY:

- if value = -1, it has a mine
- if value 0/1/2, refers to no. adjacent mines around this tile
- Values located at array[index] coordinates
  - (order doesn't matter?)
    - if letter = y-axis
    - else if number = x-axis

# How will I store data?

- Arrays? ArrayLists? hashmaps? hashsets? 2D arrays?

Use ArrayLists to have mutable no. of elements in arrays. - good for controlled size of grid on render for new game. but not for values

- new ArrayList[n] (n=number of rows/columns to generate)

Use hashmaps to hold coordinate:value pairs? - good if you need booleans, but for later complexity.

- `HashMap<String,Integer>`

---

//minesweeper.. hashmap for each coodinate? or hashset?

// (0,0) : hasBomb=true;

// (0,0) : null //nobomb?

// "A1" : hasMine=false/true, adjacentMines= 0-8 , isNextTo = {A2,B2}

//(can update them with the randomiser?)

# Modularisation

Why Modularize?

- Separation of Concerns – Each package has a clear responsibility.
- Scalability – Makes it easier to add new features later.
- Maintainability – Easier debugging and refactoring.

Game modularation approach:

1. displayboard
   GridDisplay.java: Responsible for rendering the game board.

2. generateboard
   HiddenBoard.java: initializing the board with hidden elements, such as mines

MineCounter.java: counting of mines surrounding each square.

Mines.java: Functions related to mine placement and management.

3. playgame
   GameResult.java: checks win/lose game conditions at the end of each play turn

PlayerMove.java: handle user commands, user input validation, errorhandling

ResetGame.java: game reset logic: clear previous game+board, generate new ones

4. utils
   Coordinates.java: operations relating to coordinates, probably including storage and manipulation. Keep utility lightweight and focused on assisting other classes efficiently.

MineSweeper.java: Likely the main entry point of the program, coordinating between different components. Ensure this orchestrates the initialization and control flow of the gameplay loop seamlessly.
