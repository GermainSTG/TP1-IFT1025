import Hero.*;
import java.util.ArrayList;

public class Guild {

    private ArrayList<Hero> heroes = new ArrayList<Hero>(); // à modifier si nécessaire, juste en attendant
    private ArrayList<String> errors = new ArrayList<String>(); // idem, utiliser Arrays?
    private Bank bank;

    public Guild(double initialMoney, int initialArmor) {
        this.bank = new Bank(initialMoney, initialArmor);
    }

    public void buyHero(String name, int level, double moneyCost, int armorCost, double hitPoints) {
        if (moneyCost >= bank.getMoney() & armorCost >= bank.getArmor()) {
            Hero newHero = null;
            switch (level) {
                case 0 -> {
                    newHero = new CommonHero(name, hitPoints);
                } case 1 -> {
                    newHero = new UncommonHero(name, hitPoints);
                } case 2 -> {
                    newHero = new RareHero(name, hitPoints);
                } case 3 -> {
                    newHero = new EpicHero(name, hitPoints);
                } case 4 -> {
                    newHero = new LegendaryHero(name, hitPoints);
                }
            }
            heroes.add(newHero);
        } else {
            errors.add("Il vous manque de l'argent et/ou des armures pour acheter " + name);
        }
    }

    public void trainHero(String name) {
        for (Hero hero : heroes) {
            if (hero.getName().equals(name)) {  // trouve le héros à upgrade
                double c = Math.log(hero.getLevel() + 10);  // une constante revenant dans les calculs de prix
                double moneyCost = 20 * c;
                int armorCost = (int) Math.floor(c);
                if (bank.getMoney() >= moneyCost & bank.getArmor() >= armorCost) {
                    bank.loseMoney(moneyCost);
                    bank.loseArmor(armorCost);
                    hero.upgrade();
                } else {
                    errors.add("Il vous manque de l'argent et/ou des armures pour améliorer " + name);
                }
                return;
            }
        }
        errors.add("Le héros au nom de " + name + " n'apparait pas dans la liste");
    }


    public void doQuest(int level, double hpCost, int moneyReward, int armorReward) {

        Quete quest = new Quete(level, hpCost);

        Hero hero = quest.selectHero(heroes);

        switch (quest.completeQuest(hero)) {
            case true -> {
                bank.gainArmor(armorReward);
                bank.gainMoney(moneyReward);
            }
            case false -> {
                heroes.remove(hero);
            }
        }

    }

    public void buyArmor(int number, int price) {

        if (bank.getMoney() >= (double) number * price) {
            bank.gainArmor(number);
            bank.loseMoney((double) number * price);
        } else {
            errors.add("Il vous manque de l'argent pour acheter " + number + " armure.s.");
        }

    }

    public void makeSummary() {
        System.out.println("Guild Bank account : " + bank.getMoney() + " gold & " + bank.getArmor() + " armors.");
        System.out.println("Hero.s. : ");
        for (Hero hero : heroes) {
            System.out.println("-" + hero.getName() + ": level=" + hero.getLevel() + ", HP=" + hero.getMaxHP());
        }
    }

    public void makeErrors() {
        System.out.println("Erreur.s. : ");
        for (String error : errors) {
            System.out.println("- " + error);
        }
    }

}
