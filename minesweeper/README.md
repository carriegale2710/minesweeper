# Braindump

## MVP

Recreate a simplified version of the game Minesweeper to be played in the java console:

- [x] The game should be able to randomly generate 10 mines in a 10x10 grid - (3x3 done)

- [x] The user will be able to enter a command that represents a coordinate to check a location for a mine

- [x] The application will display a number from 0-8 depending on how many mines surround that location - needs error handling

- [x] If the user selects a mine, the game will respond "boom!" and the game will be lost

- [x] If every non-mine square has been revealed, the game is won -testing

- [x] Render the grid to the console after every user command

## Bonuses (optional)

- [x] Allow for the user to configure number of mines and grid size via a configuration. - change int of new grid render (rows/columns)
- [ ] (Difficult) Discovering an empty square should reveal all squares around it, and cascade into other nearby empty squares - whole array manipultion? - arraylist?

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
