package ca.udem.ift1025.tp1.corrige;

//import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommand;
//import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommandSystem;

package guildcommands.GuildCommand;package guildcommands.GuildCommandSystem;

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

        Guilde maGuilde = makeGuilde(guildCommandSystem.actualCommand());

        while (guildCommandSystem.hasNextCommand()) {
            GuildCommand command = guildCommandSystem.nextCommand();
            switch (command.getName()) {
                case "buy-hero" -> {
                    String name = command.nextString();
                    Int level = command.nextInt();
                    Double moneyCost = command.nextDouble();
                    Int armorCost = command.nextInt();
                    Double hp = command.nextInt();
                }
                case "buy-armor" ->{
                    // TODO
                }
                case "do-quest" -> {
                    // TODO
                }
                case "train-hero" -> {
                    // TODO
                }
            }
        }
        
        maGuilde.makeSummary();
        maGuilde.makeErrors();
    }


    public static Guilde makeGuilde(GuildCommand command) {
        double montantInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        return new Guild(montantInitial, nbArmures);
    }
}