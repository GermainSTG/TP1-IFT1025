import Math;
public class Guild {
    private Hero[] heroes;  // à modifier si nécessaire, juste en attendant
    private String[] errors;  // idem, utiliser Arrays?
    private Bank bank;
    public Guild(initialMoney, initialArmor) {

    }

    public void buyHero(String name, int level, double moneyCost, int armorCost, double hitPoints) {
        if (moneyCost >= bank.getMoney() and armorCost >= bank.getArmor()) {
            Hero newHero;
            if (level == 0) {
                newHero = new Common(name, hitPoints);
            } else if (level == 1) {
                newHero = new Uncommon(name, hitPoints);
            } else if(level == 2) {
                newHero = new Rare(name, hitPoints);
            } else if (level == 3) {
                newHero = new Epic(name, hitPoints);
            } else if (level == 4) {
                newHero = new Legendary(name, hitPoints);
            } else {
                String error = "-Ce type de Héros n'existe pas"
                // TODO: ajouter error à errors
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
                return;  // non vu que void, à modifier
            }
        }
        String error = "-Le héros au nom de" + name + "n'apparêt pas dans la liste\n";
        // TODO: ajouter error à errors

    }

    private boolean heroExist(String name) {
        for (Hero hero : heroes) {
            if (hero.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}