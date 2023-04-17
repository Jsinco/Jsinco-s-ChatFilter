package me.jsinco.j_chatfilter;

import me.jsinco.j_chatfilter.util.FilteredWords;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandClass implements CommandExecutor {

    public ArrayList<String> CensoredArray =
            new ArrayList<>(Arrays.asList(FilteredWords.get().getString("CensoredWords")
                    .replace("[","").replace("]","").replace(" ", "").split(",")));



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;

        if (args.length > 0) {

            if (!args[1].isEmpty()) { //args 1 cannot be empty
                if (args[0].equalsIgnoreCase("add")) { //if arg is add
                    CensoredArray.add(args[1]); //adds arg 1 (word) to array
                    FilteredWords.get().set("CensoredWords", CensoredArray);
                    FilteredWords.save();//updates the yml file and saves it

                    p.sendMessage(ChatColor.GRAY + "Blacklisted " + ChatColor.GOLD + "'"+args[1]+"'");
                } else if (args[0].equalsIgnoreCase("remove")) { //if arg is remove
                    boolean wordFound = false;

                    for (int i = 0; i < CensoredArray.size(); i++) {
                        if (args[1].equalsIgnoreCase(CensoredArray.get(i))) {
                            CensoredArray.remove(i);
                            FilteredWords.get().set("CensoredWords", CensoredArray);
                            FilteredWords.save();
                            wordFound = true;

                            p.sendMessage(ChatColor.GRAY + "Whitelisted " + ChatColor.GOLD + "'"+args[1]+"'");
                            break;
                        }
                    }
                    //tell the user if word was found or not
                    if (!wordFound) {
                        p.sendMessage("Word not found in list");
                    }
                } else {
                    p.sendMessage("missing arg");
                }
            } else {
                p.sendMessage(ChatColor.RED + "Error: Missing word");
            }
        } else {
            p.sendMessage(ChatColor.RED + "DEBUG: " + ChatColor.LIGHT_PURPLE + CensoredArray);


        }
        return true;
    }

    public void UpdateYML(){
        FilteredWords.get().set("CensoredWords", CensoredArray);
        FilteredWords.save();
    }
}
