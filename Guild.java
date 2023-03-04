import java.util.ArrayList;
import heroes.*;


public class Guild {

    private ArrayList<Hero> heroes = new ArrayList<Hero>();
    private ArrayList<String> errors = new ArrayList<String>();
    private Bank bank;

    public Guild(double initialMoney, int initialArmor) {
        // La bank permet la gestion de la money et des armors.
        bank = new Bank(initialMoney, initialArmor);
    }

    public void buyHero(String name, int level, double moneyCost, int armorCost, double hitPoints) {

        // Verification des ressources disponibles.
        if (bank.getMoney() >= moneyCost & bank.getArmor() >= armorCost) {

            Hero newHero = Hero.findHeroType(level, name, hitPoints);

            if (newHero != null) {
                heroes.add(newHero);
                bank.loseMoney(moneyCost);
                bank.loseArmor(armorCost);
            } else {
                errors.add("Le niveau donné pour " + name + " n'existe pas.");
            }

        } else {
            errors.add("Il vous manque de l'argent et/ou des armures pour acheter " + name);
        }
    }

    public void trainHero(String name) {
        for (Hero hero : heroes) {
            if (hero.getName().equals(name)) { // Trouve le héros à upgrade.

                // Calculs pour les coûts.
                double cst = Math.log(hero.getLevel() + 10); // Une constante revenant dans les calculs de prix.
                double moneyCost = 20 * cst;
                int armorCost = (int) Math.ceil(cst);

                // Verification des ressources disponibles.
                if (bank.getMoney() >= moneyCost & bank.getArmor() >= armorCost) {

                    // Initialisation (si possible) du hero du level suivant.
                    Hero upgradedHero = Hero.findHeroType(hero.getLevel() + 1, name, hero.getMaxHP());

                    if (upgradedHero != null) {
                        bank.loseMoney(moneyCost);
                        bank.loseArmor(armorCost);
                        upgradedHero.upgrade();
                        heroes.remove(hero);
                        heroes.add(upgradedHero);
                    } else {
                        errors.add(hero.getName() + " est déjà amélioré au maximum.");
                    }

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

        // Un hero pouvant résoudre la quête a t-il été trouvé ?
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

        // Verification des ressources disponibles.
        if (bank.getMoney() >= totalPrice) {
            bank.gainArmor(number);
            bank.loseMoney(totalPrice);
        } else {
            errors.add("Il vous manque de l'argent pour acheter " + number + " armure.s à " + price + "$.");
        }
    }

    private double roundToTenth(double number) {
        // Arrondi à la première décimale.
        return (Math.round(number * 10.0) / 10.0);
    }

    public void makeSummary() {
        // Impression des status de la bank.
        double roundedMoney = roundToTenth(bank.getMoney());
        System.out.println("Guild Bank account : " + roundedMoney + " gold & " + bank.getArmor() + " armors.");

        // Impression des heroes après le jeu.
        if (!heroes.isEmpty()) {
            System.out.println("Heroes : ");
            for (Hero hero : heroes) {
                double roundedHP = roundToTenth(hero.getHitPoints());
                System.out.println("-" + hero.getName() + ": level=" + hero.getLevel() + ", HP=" + roundedHP);
            }
        }

        // Impression des erreurs rencontrées pendant le jeu.
        if (!errors.isEmpty()) {
            System.out.println("Erreur.s. : ");
            for (String error : errors) {
                System.out.println("- " + error);
            }
        }
    }

}
