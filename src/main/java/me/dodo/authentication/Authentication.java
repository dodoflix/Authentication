package me.dodo.authentication;

import me.dodo.authentication.cache.User;
import me.dodo.authentication.commands.Login;
import me.dodo.authentication.commands.Register;
import me.dodo.authentication.events.caches.PlayerJoin;
import me.dodo.authentication.events.caches.PlayerQuit;
import me.dodo.authentication.events.restrictions.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Authentication extends JavaPlugin {
    private static Authentication Main;
    private final List<User> Users = new ArrayList<>();

    @Override
    public void onEnable() {
        Main = this;

        for(Player player : getServer().getOnlinePlayers()){
            User user = new User(player.getUniqueId(), false, "");
            Users.add(user);
        }

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);

        // Restrictions
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new PlayerChat(), this);
        getServer().getPluginManager().registerEvents(new PlayerBedEnter(), this);
        getServer().getPluginManager().registerEvents(new PlayerDropItem(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEntity(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemConsume(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemDamage(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocess(), this);

        Objects.requireNonNull(this.getCommand("login")).setExecutor(new Login());
        Objects.requireNonNull(this.getCommand("register")).setExecutor(new Register());
    }

    @Override
    public void onDisable() {

    }

    public static Authentication getInstance(){
        return Main;
    }

    public List<User> getUsers() {
        return Users;
    }

    public List<User> findUser(Player player) {
        return Users.stream()
                .filter(a -> Objects.equals(a.getUUID(), player.getUniqueId()))
                .collect(Collectors.toList());
    }
}
