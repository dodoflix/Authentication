package me.dodo.authentication.events.restrictions;

import me.dodo.authentication.Authentication;
import me.dodo.authentication.cache.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class InventoryClick implements Listener {
    private final Authentication instance;

    public InventoryClick() {
        instance = Authentication.getInstance();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        List<User> filtered = instance.findUser(player);
        if (filtered.size() >= 1 && !filtered.get(0).getLoggedIn()) {
            event.setCancelled(true);
        }
    }
}
