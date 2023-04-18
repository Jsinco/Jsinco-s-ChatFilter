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

    ArrayList<String> CensoredArray =
            new ArrayList<>(Arrays.asList(FilteredWords.get().getString("CensoredWords")
                    .replace("[","").replace("]","").replace(" ", "").split(",")));

    boolean FilterToggle = true;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;

        if (args.length > 0) {
            //try to fix later
            try {
                if (!args[1].isEmpty()) { //args 1 cannot be empty
                    if (args[0].equalsIgnoreCase("blacklist")) { //if arg is add
                        boolean addWord = true;
                        for (String value : CensoredArray) {
                            if (value.equals(args[1].toLowerCase())) {
                                p.sendMessage(ChatColor.GRAY + "This word is already on the " + ChatColor.RED + "blacklist");
                                addWord = false;
                                break;
                            }
                        }
                        if (addWord){
                            CensoredArray.add(args[1].toLowerCase()); //adds arg 1 (word) to array
                            UpdateYML();
                            p.sendMessage(ChatColor.GRAY + "Blacklisted " + ChatColor.GOLD + "'" + args[1] + "'");
                        }
                    } else if (args[0].equalsIgnoreCase("whitelist")) { //if arg is remove
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
                            p.sendMessage(ChatColor.GRAY + "Cannot find word");
                        }
                    } else if (args[0].equalsIgnoreCase("whitelistall") && args[1].equalsIgnoreCase("confirm")) {
                        CensoredArray.clear();
                        p.sendMessage(ChatColor.RED + "Blacklist " + ChatColor.GRAY + "cleared.");

                    }


                } else {
                    p.sendMessage(ChatColor.RED + "Error: Missing word");
                }
            } catch (Exception e){
                if (args[0].equalsIgnoreCase("whitelistall")){
                    p.sendMessage(ChatColor.GRAY + "Are you sure? This will clear your entire " + ChatColor.RED + "blacklist" + ChatColor.GRAY +
                            ". If you are, do " + ChatColor.YELLOW + "/filter whitelistall confirm");
                } else if (args[0].equalsIgnoreCase("blacklist") || args[0].equalsIgnoreCase("whitelist")){
                    p.sendMessage(ChatColor.GRAY + "Give a word...");
                }
            }
            //3 command arge up there ^ add, remove, removeall
            if (args[0].equalsIgnoreCase("show")){
                p.sendMessage(ChatColor.GRAY + "Here's the " + ChatColor.RED + "blacklist \n" + ChatColor.WHITE + CensoredArray);
            }

        } else {
            p.sendMessage(ChatColor.YELLOW + "/filter " + ChatColor.LIGHT_PURPLE + "[blacklist | whitelist | whitelistall | show]");


        }
        return true;
    }

    public void UpdateYML(){
        FilteredWords.get().set("CensoredWords", CensoredArray);
        FilteredWords.save();
    }
}

/*
if (args[0].equalsIgnoreCase("removeall")) {
                if (args[1].equalsIgnoreCase("confirm")) {
                    CensoredArray.clear();
                    UpdateYML();
                    p.sendMessage(ChatColor.GRAY + "Blacklist cleared");
                } else {
                    p.sendMessage(ChatColor.GRAY +
                            "This will delete all words in your blacklist. If you are sure do /filter removeall confirm");
                }
            } else if (!args[1].isEmpty()) { //args 1 cannot be empty
                if (args[0].equalsIgnoreCase("add")) { //if arg is add
                    CensoredArray.add(args[1]); //adds arg 1 (word) to array
                    UpdateYML();//updates the yml file and saves it

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
                } else {
                    p.sendMessage("missing arg");
                }
            } else {
                p.sendMessage(ChatColor.RED + "Error: Missing word");
            }

 */
