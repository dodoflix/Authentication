package me.dodo.authentication.events.restrictions;

import me.dodo.authentication.Authentication;
import me.dodo.authentication.cache.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class PlayerCommandPreprocess implements Listener {
    private final Authentication instance;

    public PlayerCommandPreprocess() {
        instance = Authentication.getInstance();
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().split(" ")[0].replaceAll("/", "");
        Player player = event.getPlayer();
        List<User> filtered = instance.findUser(player);
        if (filtered.size() >= 1 && !filtered.get(0).getLoggedIn()) {
            if(!(command.equals("login") || command.equals("register")))
            {
                player.sendMessage("Komut kullanmak için öncelikle giriş yapmalısınız.");
                event.setCancelled(true);
            }
        }
    }
}
