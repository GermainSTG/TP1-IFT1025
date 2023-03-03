import java.util.Collection;
import java.util.ArrayList;
import Hero.*;

public class Quete {

    private int level;
    private double hpCost;

    public Quete(int level, double hpCost) {
        this.level = level;
        this.hpCost = hpCost;
    }

    public Hero selectHero(ArrayList<Hero> heros) {
        Collection.sort(heros, Hero.ComparatorLevel);
        for (Hero hero : heros) {
            if (hero.getLevel() >= this.level) {
                return hero;
            }
        }
    }

    public Boolean completeQuest(Hero hero) {

        // Pour calculer les points de vie du héros, on fait: maxHP*1.5 de plus, ça
        // permet au héros de regagner des ponts de vie.

        if (hero.getLevel() != this.level) {
            hero.loseHP(this.hpCost - (hero.getLevel() - this.level) * 1.5);
        } else {
            hero.loseHP(this.hpCost);
        }
        if (hero.isAlive()) {
            return true;
        }
        return false;
    }
}