package me.dodo.authentication.commands;

import me.dodo.authentication.Authentication;
import me.dodo.authentication.cache.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Login implements CommandExecutor {
    private final Authentication instance;

    public Login() {
        instance = Authentication.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;
        Player player = (Player) sender;
        List<User> filtered = instance.findUser(player);
        if (filtered.size() < 1)
            return false;
        if (filtered.get(0).getLoggedIn()) {
            player.sendMessage("Zaten giriş yaptınız.");
            return false;
        }
        if (filtered.get(0).getPassword().isEmpty()) {
            player.sendMessage("Lütfen ilk önce '/register <şifre> <şifre>' yazarak kayıt olunuz.'");
            return false;
        }
        if (args.length != 1) {
            player.sendMessage("Lütfen '/login <şifre>' şeklinde giriniz.");
            return false;
        }
        if (args[0].equals(filtered.get(0).getPassword())) {
            filtered.get(0).setLoggedIn(true);
            player.sendMessage("Başarıyla giriş yapıldı!");
            player.removePotionEffect(PotionEffectType.BLINDNESS);
        } else {
            player.sendMessage("Şifreyi hatalı girdiniz.");
        }
        return true;
    }
}
