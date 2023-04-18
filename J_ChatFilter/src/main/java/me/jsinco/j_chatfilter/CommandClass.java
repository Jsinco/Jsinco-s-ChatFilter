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
            //try to fix later
            try {
                if (!args[1].isEmpty()) { //args 1 cannot be empty
                    if (args[0].equalsIgnoreCase("add")) { //if arg is add
                        CensoredArray.add(args[1]); //adds arg 1 (word) to array
                        UpdateYML(); //updates the yml file and saves it

                        p.sendMessage(ChatColor.GRAY + "Blacklisted " + ChatColor.GOLD + "'" + args[1] + "'");
                    } else if (args[0].equalsIgnoreCase("remove")) { //if arg is remove
                        boolean wordFound = false;

                        for (int i = 0; i < CensoredArray.size(); i++) {
                            if (args[1].equalsIgnoreCase(CensoredArray.get(i))) {
                                CensoredArray.remove(i);
                                UpdateYML();
                                wordFound = true;

                                p.sendMessage(ChatColor.GRAY + "Whitelisted " + ChatColor.GOLD + "'" + args[1] + "'");
                                break;
                            }
                        }
                        //tell the user if word was found or not
                        if (!wordFound) {
                            p.sendMessage("Word not found in list");
                        }
                    } else if (args[0].equalsIgnoreCase("removeall") && args[1].equalsIgnoreCase("confirm")) {
                        CensoredArray.clear();
                        p.sendMessage(ChatColor.GRAY + "Ok, " + ChatColor.RED + "blacklist " + ChatColor.GRAY + "cleared.");
                    }


                } else {
                    p.sendMessage(ChatColor.RED + "Error: Missing word");
                }
            } catch (Exception e){
                if (args[0].equalsIgnoreCase("removeall")){
                    p.sendMessage(ChatColor.GRAY + "Are you sure? This will clear your entire " + ChatColor.RED + "blacklist" + ChatColor.GRAY +
                            ". If you are, do " + ChatColor.YELLOW + "/filter removeall confirm");
                } else if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")){
                    p.sendMessage(ChatColor.GRAY + "Give a word...");
                }
            }
            //3 command arge up there ^ add, remove, removeall
            if (args[0].equalsIgnoreCase("blacklist")){
                p.sendMessage(ChatColor.GRAY + "Here's the " + ChatColor.RED + "blacklist \n" + CensoredArray);
            }

        } else {
            p.sendMessage(ChatColor.YELLOW + "/filter " + ChatColor.LIGHT_PURPLE + "[add | remove | removeall | blacklist]");


        }
        return true;
    }

    public void UpdateYML(){
        FilteredWords.get().set("CensoredWords", CensoredArray);
        FilteredWords.save();
    }
}
