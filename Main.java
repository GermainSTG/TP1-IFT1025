// package ca.udem.ift1025.tp1.corrige;

//import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommand;
//import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommandSystem;

import guildcommands.*;

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
                    double hitPoints = command.nextInt();
                    maGuild.buyHero(name, level, moneyCost, armorCost, hitPoints);
                }
                case "buy-armor" ->{
                    // TODO
                }
                case "do-quest" -> {
                    // TODO
                }
                case "train-hero" -> {
                    String name = command.nextString();
                    maGuild.trainHero(name);
                }
            }
        }
        
        maGuilde.makeSummary();
        maGuilde.makeErrors();
    }


    public static Guild makeGuild(GuildCommand command) {
        double montantInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        return new Guild(montantInitial, nbArmures);
    }
}