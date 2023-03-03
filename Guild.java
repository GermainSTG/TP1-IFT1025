import Hero.*;
import java.util.ArrayList;


public class Guild {

    private ArrayList<Hero> heroes = new ArrayList<Hero>();
    private ArrayList<String> errors = new ArrayList<String>();
    private Bank bank;

    public Guild(double initialMoney, int initialArmor) {
        bank = new Bank(initialMoney, initialArmor);
    }

    public void buyHero(String name, int level, double moneyCost, int armorCost, double hitPoints) {
        if (bank.getMoney() >= moneyCost & bank.getArmor() >= armorCost) {
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
            bank.loseMoney(moneyCost);
            bank.loseArmor(armorCost);
        } else {
            errors.add("Il vous manque de l'argent et/ou des armures pour acheter " + name);
        }
    }

    public void trainHero(String name) {
        for (Hero hero : heroes) {
            if (hero.getName().equals(name)) {  // trouve le héros à upgrade
                double c = Math.log(hero.getLevel() + 10);  // une constante revenant dans les calculs de prix
                double moneyCost = 20 * c;
                int armorCost = (int) Math.ceil(c);
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

        boolean hasSelectedHero = quest.selectHero(heroes);

        if (hasSelectedHero) {
            Hero selectedHero = quest.getSelectedHero();
            Boolean success = quest.completeQuest(selectedHero);

            if (success) {
                bank.gainArmor(armorReward);
                bank.gainMoney(moneyReward);
            } else {
                heroes.remove(selectedHero);
            }
        } else {
            errors.add("Aucun héro ne correspond au niveau de la quête");
        }


    }

    public void buyArmor(int number, int price) {
        double totalPrice = (double) number * price;
        if (bank.getMoney() >= totalPrice) {
            bank.gainArmor(number);
            bank.loseMoney(totalPrice);
        } else {
            errors.add("Il vous manque de l'argent pour acheter " + number + " armure.s à " + price + "$.");
        }

    }

    public void makeSummary() {
        System.out.println("Guild Bank account : " + bank.getMoney() + " gold & " + bank.getArmor() + " armors.");

        System.out.println("Heroes : ");
        for (Hero hero : heroes) {
            System.out.println("-" + hero.getName() + ": level=" + hero.getLevel() + ", HP=" + hero.getHitPoints());
        }

    }

    public void makeErrors() {

        if (errors.size() > 0) {
            System.out.println("Erreur.s. : ");
            for (String error : errors) {
                System.out.println("- " + error);
            }
        }

    }

}
