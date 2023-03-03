import java.util.Comparator;
import Hero.*;

class LevelSort implements Comparator<Hero> {

    public int compare(Hero a, Hero b) {
        int compA = a.getLevel();
        int compB = b.getLevel();
        return compB - compA;
    }

}