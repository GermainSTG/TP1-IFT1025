public class Bank {

    private double money;
    private int armor;


    public Bank(double initialMoney, int initialArmor) {
        this.money = initialMoney;
        this.armor = initialArmor;
    }


    public double getMoney() {
        return money;
    }

    public int getArmor() {
        return armor;
    }

    public void gainMoney(double amount) {
        this.money += amount;
    }

    public void gainArmor(int amount) {
        this.armor += amount;
    }

    public void loseMoney(double amount) {
        this.money -= amount;
    }

    public void loseArmor(int amount) {
        this.armor -= amount;
    }

}