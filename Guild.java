import Math;
package TP1-IFT1025.Hero;

public class Guild {

    private Hero[] heroes;  // à modifier si nécessaire, juste en attendant
    private String[] errors;  // idem, utiliser Arrays?
    private Bank bank;

    public Guild(double initialMoney, int initialArmor) {
        this.bank = new Bank(initialMoney, initialArmor);
    }

    public void buyHero(String name, int level, double moneyCost, int armorCost, double hitPoints) {
        if (moneyCost >= bank.getMoney() and armorCost >= bank.getArmor()) {
            Hero newHero;
            switch (level) {
                case 0 -> {
                    newHero = new Common(name, hitPoints);
                }
                case 1 -> {
                    newHero = new Uncommon(name, hitPoints);
                }
                case 2 -> {
                    newHero = new Rare(name, hitPoints);
                }
                case 3 -> {
                    newHero = new Epic(name, hitPoints);
                }
                case 4 -> {
                    newHero = new Legendary(name, hitPoints);
                }
            }
            // TODO: ajouter newHero à heroes
        } else {
            String error = "-Il vous manque de l'argent et/ou des armures pour acheter" + name +"\n";
            // TODO: ajouter error à errors
        }
    }

    public void trainHero(String name) {
        for (Hero hero : heroes) {
            if (hero.getName().equals(name)) {  // trouve le héros à upgrade
                double c = Math.log(hero.getLevel() + 10);  // une constante reveant dans les calculs de prix
                double moneyCost = 20 * c;
                double armorCost = Math.roof(c);
                if ((moneyCost >= bank.getMoney()) and (armorCost >= bank.getArmor())) {
                    bank.loseMoney(moneyCost);
                    bank.loseArmor(armorCost);
                    hero.upgrade();
                } else {
                    String error = "-Il vous manque de l'argent et/ou des armures pour améliorer" + name +"\n";
                    // TODO: ajouter error à errors
                }
                return;  // non vu que void?, à modifier pour que arrête là
            }
        }
        String error = "-Le héros au nom de" + name + "n'apparêt pas dans la liste\n";
        // TODO: ajouter error à errors
    }

    public void doQuest(int level, double hpCost, int moneyReward, int armorReward) {
        /*
         * Il faudrait qu'on définisse le type de structure de donnée pour la
         * liste de hero. On peut en parler Mercredi si t'es dispo.
         */

        Quete quest = new Quete(level, hpCost);

        Hero hero = quest.selectHero(heros);

        switch (quest.completeQuest(hero)) {
            case true -> {
                this.bank.gainArmor(armorReward);
                this.bank.gainMoney(moneyReward);
            }
            case false -> {
                //heros.remove(hero)
            }
        }

    }

    public void makeErrors(String [] errors) {
        // TODO
    }

    public void buyArmor(int number, int price) {
        this.bank.gainArmor(number);
        this.bank.loseMoney(double number * price);
    }

}