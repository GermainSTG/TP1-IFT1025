package ca.udem.ift1025.tp1.corrige.guildcommands;

public class GuildCommandSystem {

    String[] command;
    int index=0;
    public GuildCommandSystem(String[] args) {
        command = args;
    }

    public boolean hasNextCommand() {
        return index<command.length;
    }

    public GuildCommand nextCommand() {
        index++;
        return new GuildCommand(command[index--]);
    }
}
