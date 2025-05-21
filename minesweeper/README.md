# Braindump

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
