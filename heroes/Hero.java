package heroes;

public abstract class Hero {

    protected String name;
    protected int level;
    protected double hitPoints, maxHP;

    public Hero(String name, double hitPoints) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.maxHP = this.hitPoints;
    }

    public void upgrade() {
        maxHP *= 1.5;
        hitPoints = maxHP;
    }

    public void loseHPFromQuest(double HPLoss, int levelQuest) {
        /*
         * La perte de HP est moindre si le level du hero est plus grand que celui de la
         * quête.
         */
        if (level > levelQuest) {
            double actualHPLoss = HPLoss - (level - levelQuest) * 1.5;
            loseHP(actualHPLoss);
        } else {
            loseHP(HPLoss);
        }
    }

    public static Hero findHeroType(int level, String name, double hitPoints) {
        /*
         * Retourne le bon hero initialisé selon le level en paramètre
         * sinon si le level ne correspond pas à un level de hero on
         * retourn null.
         */
        Hero newHero = null;
        switch (level) {
            case 0 -> newHero = new CommonHero(name, hitPoints);
            case 1 -> newHero = new UncommonHero(name, hitPoints);
            case 2 -> newHero = new RareHero(name, hitPoints);
            case 3 -> newHero = new EpicHero(name, hitPoints);
            case 4 -> newHero = new LegendaryHero(name, hitPoints);
        }
        return newHero;
    }

    public void loseHP(double hitPoints) {
        this.hitPoints -= hitPoints;
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    public int getLevel() {
        return level;
    }

    public double getHitPoints() {
        return hitPoints;
    }

    public double getMaxHP() {
        return maxHP;
    }

    public String getName() {
        return name;
    }

}
