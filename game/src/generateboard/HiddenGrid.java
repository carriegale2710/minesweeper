//reates and manages the game grid
package generateboard

public class Grid {
    
          // Use for minesweeper coordinates?

    public static int getRandomInt() {
        double randomDouble = Math.random()*3;
        System.out.println(randomDouble);
        int randomInt = (int) randomDouble;
        System.out.println(randomInt);
        return randomInt;
    }

    
    public static char[] getRandomCoordinate() {
        int randomInt1 = getRandomInt();
        int randomInt2 = getRandomInt();
        char[][] coordinates = {{'A','B','C'},{'A','B','C'}};
        char[] randomCoordinate = {coordinates[0][randomInt1],coordinates[1][randomInt2]};
        System.out.println(randomCoordinate);
        return randomCoordinate;
    }
}
