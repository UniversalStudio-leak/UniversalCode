package ru.universalstudio.universalcode.tools;

import java.util.*;
import org.bukkit.*;
import java.util.stream.*;
import org.bukkit.block.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;
import me.clip.placeholderapi.*;
import org.bukkit.configuration.file.*;
import ru.universalstudio.universalcode.tools.actionbar.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: UniversalStudio
 * @Author default source code: WinLocker02 (Thank pasting wCode -> UniversalCode)
 */

public class Utils {

    private static FileConfiguration config;

    public static FileConfiguration getConfig() {
        return config != null ? config : (config = Config.getFile("config.yml"));
    }

    public static void reloadConfig() {
        config = Config.getFile("config.yml");
    }

    public static String getMessage(String path) {
        return getConfig().getString("messages." + path);
    }

    public static String getString(String path) {
        return getConfig().getString(path);
    }

    public static List<String> getStringList(String path) {
        return getConfig().getStringList(path);
    }

    public static int getInt(String path) {
        return getConfig().getInt(path);
    }

    public static double getDouble(String path) {
        return getConfig().getDouble(path);
    }

    public static boolean getBoolean(String path) {
        return getConfig().getBoolean(path);
    }

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> color(List<String> text) {
        return text.stream().map(x -> color(x)).collect(Collectors.toList());
    }

    public static boolean has(CommandSender player, String permission) { // прост винлокер берёт одни и те же утилитки и ему насрать используется тот или иной код. на оптимизацию похуй, а мне не похуй. сказали без костылей - доплатите и получите. сказали на высшем сорте и без костылей - распишите и получите, высший сорт.
        if(!player.hasPermission(permission)) {
            sendMessage(player, getConfig().getString("messages.no-permission"));
            return false;
        }
        return true;
    }

    public static void sendMessage(CommandSender player, String text) {

        if(text.isEmpty()) return;

        for(String line : text.split(";")) {
            line = line.replace("%player%", player.getName());

            if(line.startsWith("title:")) {
                if(player instanceof Player)
                    Title.sendTitle((Player) player, line.split("title:")[1]);
            }
            else if(line.startsWith("actionbar:")) {
                if(player instanceof Player)
                    Actionbar.sendActionbar((Player) player, line.split("actionbar:")[1]);
            }
            else {
                player.sendMessage(color(getMessage("prefix") + line));
            }
        }
    }

    public static String replacePlaceholders(Player player, String placeholder) {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null && PlaceholderAPI.containsPlaceholders(placeholder)) {
            return PlaceholderAPI.setPlaceholders(player, placeholder);
        }
        return placeholder;
    }

    public static List replacePlaceholders(Player player, List list) {
        List<String> placeholders = new ArrayList<>();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            list.forEach(s -> {
                String placeholder = s.toString();
                if (PlaceholderAPI.containsPlaceholders(placeholder)) {
                    placeholder = PlaceholderAPI.setPlaceholders(player, placeholder);
                }
                placeholders.add(placeholder);
            });
            return placeholders;
        }
        return list; // бомж не имеющий плейсхолдер апи с нужным плейсом))
    }

    public static List replaceList(List replaceList, String string, String string2) {
        List<String> list = new ArrayList();
        replaceList.forEach(value -> list.add(value.toString().replace(string, string2)));
        return list;
    }

    public static boolean equals(String string, String string2) {
        return string.equalsIgnoreCase(string2) || string.toLowerCase().startsWith(string2.toLowerCase() + " ");
    }

    public static boolean equals(String string, List list) {
        return list.stream().anyMatch(value -> (string.equalsIgnoreCase(value.toString()) || string.toLowerCase().startsWith(value.toString().toLowerCase() + " ")));
    }

    public static boolean equalsCommand(String string, List list) {
        return list.stream().anyMatch(command -> string.equalsIgnoreCase("/" + command.toString()) || string.toLowerCase().startsWith("/" + command.toString().toLowerCase() + " "));
    }

    public static List nearBlocks(Location location, int n) { // я потом енто буду пастить, хотя уже видел)
        World world = location.getWorld();
        List<Block> blocks = new ArrayList();
        for (int i = location.getBlockX() - n; i <= location.getBlockX() + n; ++i) {
            for (int j = location.getBlockY() - n; j <= location.getBlockY() + n; ++j) {
                for (int k = location.getBlockZ() - n; k <= location.getBlockZ() + n; ++k) {
                    if (world.getBlockAt(i, j, k).getType() != Material.AIR) {
                        blocks.add(world.getBlockAt(i, j, k));
                    }
                }
            }
        }
        blocks.removeIf(blockLocation -> ((Block)blockLocation).getLocation().equals(location));
        return blocks;
    }

    public static List nearPlayers(Player player, int n) { // я такую утилку уже видел где-то
        List<Player> list = new ArrayList<>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if(!onlinePlayer.equals(player)) { // equals быстрее воркает
                if(player.getWorld().equals(onlinePlayer.getWorld())) {
                    if(player.getLocation().distance(onlinePlayer.getLocation()) <= Math.pow(((double)n), 1.0D)) {
                        list.add(onlinePlayer);
                    }
                }
            }
        }
        return list;
    }

}
