//randomgenerateboard mines
package generate;
public class MinesGenerator {



    public char[] main() {
        char[] firstMine = getRandomCoordinate();
        System.out.println(firstMine);
        return firstMine;
    }

}