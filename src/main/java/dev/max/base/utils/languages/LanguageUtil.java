package dev.max.base.utils.languages;

import dev.max.base.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LanguageUtil {

    private Main main;
    private static HashMap<String, SupportedLanguages> languagesHashMap = new HashMap<>();
    private static HashMap<SupportedLanguages, Map<String, Object>> languageContent = new HashMap<>();

    public LanguageUtil(Main main) {
        this.main = main;
    }

    public void loadLanguageFiles() {

        for(SupportedLanguages languages : SupportedLanguages.values()) {
            String fileName = languages.getLang();
            File file = new File(main.getDataFolder() + "/languages/" + fileName + ".yml");

            main.saveResource("languages/" + fileName + ".yml", false);

            YamlConfiguration configFile = YamlConfiguration.loadConfiguration(file);
            languageContent.put(languages, configFile.getValues(true));
        }

    }

    public void loadLanguage() {

        Bukkit.getConsoleSender().sendMessage("Loading Language...");

        new BukkitRunnable() {
            @Override
            public void run() {
                SupportedLanguages language = SupportedLanguages.ENGLISH;

                for(SupportedLanguages lang : SupportedLanguages.values()) {
                    if(main.getConfig().getString("plugin.language").equalsIgnoreCase(lang.getLang())) {
                        language = lang;
                        break;
                    }
                }

                languagesHashMap.put(main.getConfig().getString("plugin.language"), language);
            }
        }.runTaskLater(main, 20);

    }

    public static String getMessage(String messageKey) {
        return getMessage(languagesHashMap.get(Main.getInstance().getConfig().getString("plugin.language")), messageKey);
    }

    public static String getMessage(SupportedLanguages language, String messageKey)
    {
        return ChatColor.translateAlternateColorCodes('&', languageContent.get(language).get(messageKey).toString());
    }

}
