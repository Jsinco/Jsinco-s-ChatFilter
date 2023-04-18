package me.jsinco.j_chatfilter;

import me.jsinco.j_chatfilter.util.FilteredWords;
import org.bukkit.plugin.java.JavaPlugin;

/* For me
permissions so mods,admins, and ops and see word that was blocked and which player sent it. also so only those players can access /filter
config, etc
 */

public final class J_ChatFilter extends JavaPlugin {

    String[] DefaultCensored = new String[]{"nigga", "nigger", "nig", "faggot", "coon", "africoon", "chink", "fag", "retard"};
    @Override
    public void onEnable() {
        //Setup config
        getConfig().options().copyDefaults();
        saveDefaultConfig();


        FilteredWords.setup();
        FilteredWords.get().addDefault("CensoredWords", DefaultCensored);
        FilteredWords.get().options().copyDefaults(true);
        FilteredWords.save();


        //ChatListener class & Command class
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getCommand("filter").setExecutor(new CommandClass());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
