package me.jsinco.j_chatfilter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1){
            String[] list = new String[]{"add","remove"};
        }
    }
}
