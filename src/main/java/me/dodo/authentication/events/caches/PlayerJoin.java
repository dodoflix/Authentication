package me.dodo.authentication.events.caches;

import me.dodo.authentication.Authentication;
import me.dodo.authentication.cache.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class PlayerJoin implements Listener {
    private final Authentication instance;
    private final List<User> users;

    public PlayerJoin() {
        instance = Authentication.getInstance();
        users = instance.getUsers();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        User user = new User(player.getUniqueId(), false, "");
        List<User> filtered = instance.findUser(player);
        if (filtered.size() == 0) {
            users.add(user);
        }


        filtered = instance.findUser(player);
        if(filtered.size() == 1 && filtered.get(0).getPassword().isEmpty())
            player.sendMessage("Lütfen kayıt olmak için '/register <şifre> <şifre>' komudunu giriniz.");
        else
            player.sendMessage("Lütfen giriş yapmak için '/login <şifre>' komudunu giriniz.");
        PotionEffect effect = new PotionEffect(PotionEffectType.BLINDNESS, 9999, 1, false, false);
        player.addPotionEffect(effect);
    }
}
