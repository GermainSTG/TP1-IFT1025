package Hero;
public abstract class Hero {
    protected String name;
    protected int level;
    protected double hitPoints, maxHP;

    public Hero(String name, double hitPoints) {
        this.name = name;
        this.hitPoints = hitPoints;
    }

    public void upgrade() {
        // doit initialiser nouvelle classe et retirer ancienne
        level += 1;
        double oldMaxHP = maxHP;
        maxHP *= 1.5;
        hitPoints += (maxHP - oldMaxHP);  // ajoute le même nb de HP à hitPoints que ajouté à maxHP
    }

    public void loseHPFromQuest(double HPLoss, int levelQuest) {
        if (level > levelQuest) {
            double actualHPLoss = HPLoss - (level - levelQuest) * 1.5;
            loseHP(actualHPLoss);
        } else {
            loseHP(HPLoss);
        }
    }

    public void addHP(double hitPoints) {
        this.hitPoints += hitPoints;
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