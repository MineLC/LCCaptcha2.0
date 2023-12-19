package lc.captcha2;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LCConfig {
    private final Captcha2 plugin;

    private FileConfiguration fileConfiguration = null;
    private File file = null;

    public LCConfig(Captcha2 plugin){
        this.plugin = plugin;
    }


    public void registerConfig(){
        file = new File(plugin.getDataFolder(), "minelc.yml");

        if(!file.exists()){
            plugin.saveResource("minelc.yml", false);

        }

        fileConfiguration = new YamlConfiguration();
        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            Bukkit.getLogger().severe("[Captcha2] Ha habido una IOExcepcion o InvalidConfigurationException cargando el archivo minelc.yml");
        }
    }
    public void saveConfig() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            Bukkit.getLogger().severe("[Captcha2] Ha habido una IOExcepcion guardando el archivo minelc.yml");
        }
    }

    public FileConfiguration getConfig() {
        if (fileConfiguration == null) {
            reloadConfig();
        }
        return fileConfiguration;
    }

    public void reloadConfig() {
        if (fileConfiguration == null) {
            file = new File(plugin.getDataFolder(), "minelc.yml");
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);

        if(file != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(file);
            fileConfiguration.setDefaults(defConfig);
        }
    }
}