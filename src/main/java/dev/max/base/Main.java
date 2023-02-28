package dev.max.base;

import dev.max.base.utils.languages.LanguageUtil;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Main extends JavaPlugin {

    public static Main instance;
    public String prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("plugin.prefix")) + "§7 ";
    public LanguageUtil languageUtil;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        /* Setup the configs */
        SetupConfigs();

        /* Setup the language System */
        languageUtil = new LanguageUtil(this);
        languageUtil.loadLanguageFiles();
        languageUtil.loadLanguage();

        /* Register Commands and Listeners */
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        SaveConfigs();
    }

    private void SetupConfigs() {
        saveDefaultConfig();
    }

    private void SaveConfigs() {
        saveDefaultConfig();
    }

    private void registerCommands() {
        // Register your commands
    }

    private void registerListeners() {
        // Register your listeners
    }

    public static Main getInstance() {
        return instance;
    }
}
