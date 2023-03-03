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

    public boolean selectHero(ArrayList<Hero> heroes) {

        Collections.sort(heroes, new LevelSort());

        for (Hero hero : heroes) {
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

    public Boolean completeQuest(Hero hero) {

        hero.loseHPFromQuest(hpCost, level);

        if (hero.isAlive()) {
            return true;
        }
        return false;
    }
}