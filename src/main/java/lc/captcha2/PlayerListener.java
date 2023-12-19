package lc.captcha2;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements Listener {


    @EventHandler
    public void join(PlayerJoinEvent e){
        Player p = e.getPlayer();
        String barr = ChatColor.translateAlternateColorCodes('&', "&7&m-------------------------");
      String msg = ChatColor.translateAlternateColorCodes('&', "&fHola &e" + p.getName() + ", &fpara poder ingresar al servidor");
        String msg2 = ChatColor.translateAlternateColorCodes('&', "&fprimero debes escribir el comando que ves en la pantalla.");
        String barr2 = ChatColor.translateAlternateColorCodes('&', "&7&m-------------------------");
     
        p.sendMessage(barr);
        p.sendMessage(msg);
        p.sendMessage(msg2);
        p.sendMessage(barr2);
        Captcha2.playerkick.add(p);
      Captcha2.player_catpchat.put(p, Captcha2.generateRandomWord(5));
      Bukkit.getConsoleSender().sendMessage(p.getName() + " Captcha2: " + Captcha2.player_catpchat.get(p));

      new BukkitRunnable(){

          @SuppressWarnings("deprecation")
          @Override
          public void run() {
              if(!p.isOnline()){
                  cancel();
                  return;
              }
              if(!Captcha2.playerkick.contains(p)){
                  cancel();
                  return;
              }
              p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Escribe: "));
              p.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&a/Captcha " + Captcha2.player_catpchat.get(p)));
              String mensaje = ChatColor.translateAlternateColorCodes('&', "&fEscribe: &a/Captcha " + Captcha2.player_catpchat.get(p));
              p.sendMessage(mensaje);
          }
      }.runTaskTimerAsynchronously(Captcha2.getPlugin(Captcha2.class), 0, 20);

      new BukkitRunnable(){

          @Override
          public void run() {
            p.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&4Demoraste demasiado, vuelve a intentarlo."));
          }
      }.runTaskLater(Captcha2.getPlugin(Captcha2.class), 900);
    }


    @EventHandler
    public void playerleave(PlayerQuitEvent e){
        Captcha2.playerkick.remove(e.getPlayer());
        Captcha2.player_catpchat.remove(e.getPlayer());
    }
}
