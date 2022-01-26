package me.dodo.authentication.commands;

import me.dodo.authentication.Authentication;
import me.dodo.authentication.cache.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Register implements CommandExecutor {
    private final Authentication instance;

    public Register() {
        instance = Authentication.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;
        Player player = (Player) sender;
        List<User> filtered = instance.findUser(player);
        if (filtered.size() < 1)
            return false;
        if (!filtered.get(0).getPassword().isEmpty()) {
            player.sendMessage("Zaten kayıt oldunuz.");
            return false;
        }
        if (args.length != 2) {
            player.sendMessage("Lütfen '/register <şifre> <şifre>' şeklinde kayıt olunuz.");
            return false;
        }
        if (!args[0].equals(args[1])) {
            player.sendMessage("Girdiğiniz iki şifre birbiriyle uyuşmuyor.");
            return false;
        }
        filtered.get(0).setPassword(args[0]);
        filtered.get(0).setLoggedIn(true);
        player.sendMessage("Başarıyla kayıt oldunuz!");
        player.removePotionEffect(PotionEffectType.BLINDNESS);
        return true;
    }
}
