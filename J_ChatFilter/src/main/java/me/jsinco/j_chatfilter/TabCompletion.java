package me.jsinco.j_chatfilter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1){
            List<String> arg1suggest = new ArrayList<>();
            arg1suggest.add("blacklist");
            arg1suggest.add("whitelist");
            arg1suggest.add("whitelistall");
            arg1suggest.add("show");

            return arg1suggest;
        }
        return null;
    }
}
