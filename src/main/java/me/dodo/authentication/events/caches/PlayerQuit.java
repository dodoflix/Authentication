package me.dodo.authentication.events.caches;

import me.dodo.authentication.Authentication;
import me.dodo.authentication.cache.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class PlayerQuit implements Listener {
    private final Authentication instance;


    public PlayerQuit() {
        instance = Authentication.getInstance();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        List<User> filtered = instance.findUser(player);
        if (filtered.size() >= 1 && filtered.get(0).getLoggedIn()) {
            filtered.get(0).setLoggedIn(false);
        } else {
            player.removePotionEffect(PotionEffectType.BLINDNESS);
        }
    }
}
