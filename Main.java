// package ca.udem.ift1025.tp1.corrige;

//import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommand;
//import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommandSystem;

import guildcommands.*;

import java.util.Arrays;

public class Main {
    /**
     * Args: array with
     * <ol>
     *     <li>guild:&lt;montant initial&gt;,&lt;armures initiales&gt;</li>
     * </ol>
     *
     * @param args
     */
    public static void main(String[] args) {
        GuildCommandSystem guildCommandSystem = new GuildCommandSystem(args);

        Guild maGuild = makeGuild(guildCommandSystem.actualCommand());

        while (guildCommandSystem.hasNextCommand()) {
            GuildCommand command = guildCommandSystem.nextCommand();
            switch (command.getName()) {
                case "buy-hero" -> {
                    String name = command.nextString();
                    int level = command.nextInt();
                    double moneyCost = command.nextDouble();
                    int armorCost = command.nextInt();
                    double hitPoints = command.nextDouble();
                    maGuild.buyHero(name, level, moneyCost, armorCost, hitPoints);
                }
                case "buy-armor" ->{
                    int number = command.nextInt();
                    int price = command.nextInt();
                    maGuild.buyArmor(number, price);
                }
                case "do-quest" -> {
                    int level = command.nextInt();
                    double hpCost = command.nextDouble();
                    int moneyReward = command.nextInt();
                    int armorReward = command.nextInt();
                    maGuild.doQuest(level, hpCost, moneyReward, armorReward);
                }
                case "train-hero" -> {
                    String name = command.nextString();
                    maGuild.trainHero(name);
                }
            }
        }
        printSummary(args, maGuild);
    }


    public static Guild makeGuild(GuildCommand command) {
        double montantInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        return new Guild(montantInitial, nbArmures);
    }

    private static void printSummary(String[] args, Guild maGuild) {

        System.out.println("Input : \n");
        for (String arg : args) {
            System.out.print(arg + " ");
        }

        System.out.println("\n\n");
        System.out.println("Output : \n");
        maGuild.makeSummary();
    }
}