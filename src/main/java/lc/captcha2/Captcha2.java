package lc.captcha2;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public final class Captcha2 extends JavaPlugin {
    private LCConfig config;
    public static HashMap<Player, String> player_catpchat = new HashMap<Player, String>();
    public static ArrayList<Player> playerkick = new ArrayList<Player>();
    @Override
    public void onEnable() {
        config = new LCConfig(this);
        config.registerConfig();

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getMessenger().registerOutgoingPluginChannel((Plugin)this, "captcha:channel");
        getCommand("captcha").setExecutor(new Command());
        System.out.println("[Captcha] Plugin Habilitado.");
    }

    public static String generateRandomWord(int wordLength) {
        Random r = new Random(); // Intialize a Random Number Generator with SysTime as the seed
        StringBuilder sb = new StringBuilder(wordLength);
        for(int i = 0; i < wordLength; i++) { // For each letter in the word
            char tmp = (char) ('a' + r.nextInt('z' - 'a')); // Generate a letter between a and z
            sb.append(tmp); // Add it to the String
        }
        return sb.toString();
    }

}
