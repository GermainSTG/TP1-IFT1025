import java.util.ArrayList;
import java.util.*;

import Hero.*;


public class Quete {

    private int level;
    private double hpCost;

    public Quete(int level, double hpCost) {
        this.level = level;
        this.hpCost = hpCost;
    }

    public Hero selectHero(ArrayList<Hero> heroes) {

        Collections.sort(heroes, new LevelSort());
        Hero selectedHero;

        for (Hero hero : heroes) {
            if (hero.getLevel() >= this.level) {
                return hero;
            }
        }

        return null;
    }

    public Boolean completeQuest(Hero hero) {

        hero.loseHPFromQuest(hpCost, level);

        if (hero.isAlive()) {
            return true;
        }
        return false;
    }
}