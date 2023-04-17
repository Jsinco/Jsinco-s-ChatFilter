package me.jsinco.j_chatfilter;

import me.jsinco.j_chatfilter.util.FilteredWords;
import org.bukkit.plugin.java.JavaPlugin;



public final class J_ChatFilter extends JavaPlugin {

    //String MsgPlayer = getConfig().getString()
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
