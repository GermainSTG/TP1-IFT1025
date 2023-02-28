public class Guild {
    private Hero[] heroes;  // à modifier si nécessaire, juste en attendant
    private String[] errors;  // idem, utiliser Arrays?
    public Guild(initialMoney, initialArmor) {

    }

    public void buyHero(String name, int level, double moneyCost, int armorCost, double hitPoints) {

    }

    public void trainHero(String name) {
        if (!heroExist(name)) {
            String error = "-Le héros au nom de" + name + "n'apparêt pas dans la liste\n";
            // TODO: ajouter error à errors
        }
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