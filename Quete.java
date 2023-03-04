import java.util.ArrayList;
import heroes.*;
import java.util.*;


public class Quete {

    private int level;
    private double hpCost;
    private Hero selectedHero;

    public Quete(int level, double hpCost) {
        this.level = level;
        this.hpCost = hpCost;
    }

    public boolean selectHero(ArrayList<Hero> heroes) {
        /*
         * Triage de la liste en ordre croissant avec le comparateur
         * que nous avons créé dans la class LevelSort() afin de trier les
         * heroes par level.
         */
        Collections.sort(heroes, new LevelSort());

        Hero awaiting = heroes.get(0);
        int dist = 10;

        if (!heroes.isEmpty()) {

            for (Hero hero : heroes) {
                int currentDist = Math.abs(this.level - hero.getLevel());
                if (currentDist < dist) {
                    dist = currentDist;
                    awaiting = hero;
                }
                if (currentDist > dist) {
                    break;
                }
            }
            this.selectedHero = awaiting;
            return true;
        }

        return false;
    }

    public Hero getSelectedHero() {
        return selectedHero;
    }

    public Boolean completeQuest(Hero hero) {

        hero.loseHPFromQuest(hpCost, level);

        if (hero.isAlive()) {
            return true;
        }

        return false;
    }
}
