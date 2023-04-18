package me.jsinco.j_chatfilter;

import me.jsinco.j_chatfilter.util.FilteredWords;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class ChatListener implements Listener {
    @EventHandler
    public void LChat(AsyncPlayerChatEvent e){
        /*
        ArrayList<String> CensoredArrayCL =
                new ArrayList<>(Arrays.asList(FilteredWords.get().getString("CensoredWords")
                        .replace("[","").replace("]","").replace(" ", "").split(",")));
         */

        Player p = e.getPlayer();
        String pMsg = e.getMessage().toLowerCase();

         CommandClass CMDCL = new CommandClass();


        for (String value : CMDCL.CensoredArray) {
            if (pMsg.contains(value)) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "That word is not allowed!");
                break;
            }
        }

        /*
        if (pMsg.equals("code")){ //debug
            J_ChatFilter chatFilter = new J_ChatFilter();
            p.sendMessage(String.valueOf(chatFilter.MsgPlayer));
            p.sendMessage(chatFilter.MsgPlayer);
        }
         */

    }
}
