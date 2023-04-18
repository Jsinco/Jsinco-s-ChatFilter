package me.jsinco.j_chatfilter;

import org.bukkit.plugin.java.JavaPlugin;

import me.jsinco.j_chatfilter.util.FilteredWords;



public final class J_ChatFilter extends JavaPlugin {

    @Override
    public void onEnable() {
        String[] DefaultCensored = new String[]{"nigga", "nigger", "nig", "faggot", "coon", "africoon", "chink", "fag", "retard"};

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
        getCommand("filter").setTabCompleter(new TabCompletion());
    }

    @Override
    public void onDisable() {
        FilteredWords.save();
    }
}
