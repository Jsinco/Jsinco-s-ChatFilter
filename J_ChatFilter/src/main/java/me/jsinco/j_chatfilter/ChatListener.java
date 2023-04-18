package me.jsinco.j_chatfilter;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class ChatListener implements Listener {
    @EventHandler
    public void LChat(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();
        String pMsg = e.getMessage().toLowerCase();
        CommandClass CMDCL = new CommandClass();

        for (String value : CMDCL.CensoredArray) {
            if (pMsg.contains(value)) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + J_ChatFilter.getPlugin(J_ChatFilter.class).getConfig().getString("BlockedWord"));
                break;
            }
        }
    }
}
