import Math;
public class Guild {
    private Hero[] heroes;  // à modifier si nécessaire, juste en attendant
    private String[] errors;  // idem, utiliser Arrays?
    public Guild(initialMoney, initialArmor) {

    }

    public void buyHero(String name, int level, double moneyCost, int armorCost, double hitPoints) {

    }

    public void trainHero(String name) {
        for (Hero hero : heroes) {
            if (hero.getName().equals(name)) {
                double c = Math.log(hero.getLevel() + 10);  // une constante reveant dans les calculs de prix
                double moneyCost = 20 * c;
                double armorCost = Math.roof(c);
                if ((moneyCost >= bank.getMoney()) and (armorCost > bank.getArmor())) {
                    bank.loseMoney(moneyCost);
                    bank.loseArmor(armorCost);
                    hero.upgrade();
                } else {
                    String error = "-Il vous manque de l'argent et/ou des armures pour améliorer" + name +"\n";
                    // TODO: ajouter error à errors
                }
                break;
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