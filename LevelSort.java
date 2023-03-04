import java.util.Comparator;
import heroes.*;

/* La classe LevelSort implemente la class comparator et créée la methode 
 compare qui permettra de trier une liste de heroes dans l'ordre croissant 
 par leur level.
 */

class LevelSort implements Comparator<Hero> {

    public int compare(Hero a, Hero b) {
        int levelA = a.getLevel();
        int levelB = b.getLevel();
        return levelB - levelA;
    }

}
