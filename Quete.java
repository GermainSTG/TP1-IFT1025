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
         * Recherche du hero le plus proche du niveau de la quête.
         */

        if (!heroes.isEmpty()) {

            // Tri par ordre croissant en fonction du level.
            Collections.sort(heroes, new LevelSort());

            Hero awaiting = heroes.get(0);
            int dist = 10;

            for (Hero hero : heroes) {
                int currentDist = Math.abs(this.level - hero.getLevel());

                if (currentDist < dist) {
                    dist = currentDist;
                    awaiting = hero;
                }
                if (currentDist > dist) {
                    /*
                     * Comme la liste de heroes est triée par ordre croissant on peut arreter les
                     * recherches.
                     */
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
