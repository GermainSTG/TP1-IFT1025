package ca.udem.ift1025.tp1.corrige.guildcommands;

public class GuildCommand {

    private String name;
    private final String[] nextValues;
    private int index = 0;
    public GuildCommand(String command) {
        name = command.split(":")[0];
        nextValues = (command.split(":")[1]).split(",");
    }

    public int nextInt() {
        return Integer.parseInt(nextValues[index++]);
    }
    public String nextString(){
        return nextValues[index++];
    }

    public String getName() {
        return name;
    }
    public double nextDouble() {
        return Double.parseDouble(nextValues[index++]);
    }
}
