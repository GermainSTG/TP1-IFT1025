
import java.util.ArrayList;
import java.util.*;
import Hero.*;


public class Quete {

    private int level;
    private double hpCost;
    private Hero selectedHero;

    public Quete(int level, double hpCost) {
        this.level = level;
        this.hpCost = hpCost;
    }

    /*
     * La methode selectHero permet de selectionnner le hero à envoyer en quête
     * parmis
     * ceux de la liste.
     */

    public boolean selectHero(ArrayList<Hero> heroes) {
        /*
         * Triage de la liste en ordre croissant avec le comparateur
         * que nous avons créé dans la class LevelSort() afin de trier les
         * heroes par level.
         */
        Collections.sort(heroes, new LevelSort());

        for (Hero hero : heroes) {
            /*
             * Si un hero à le niveau de la quête on l'envoi en prioritée
             * sinon on prend ceux dont le niveau est supérieur.
             */
            if (hero.getLevel() >= this.level) {
                this.selectedHero = hero;
                return true;
            }
        }

        return false;
    }

    public Hero getSelectedHero() {
        return selectedHero;
    }

    /*
     * La méthide completeQuest() retourne true ou false si le hero
     * a réussi la quête
     */

    public Boolean completeQuest() {

        this.selectedHero.loseHPFromQuest(hpCost, level);

        if (this.selectedHero.isAlive()) {
            return true;
        }

        return false;
    }
}