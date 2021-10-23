package ru.universalstudio.universalcode.tools;

import java.util.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.*;
import com.sk89q.worldedit.bukkit.*;
import com.sk89q.worldguard.bukkit.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: UniversalStudio
 * @Author default source code: WinLocker02 (Thank pasting wCode -> UniversalCode)
 */

public class Tools {

    private static JavaPlugin instance;
    private static Random random = new Random();

    public static JavaPlugin getInstance() {
        return instance;
    }

    public static void setInstance(JavaPlugin plugin) {
        instance = plugin;
    }

    public static List<String> getAliases(String alias) {
        List<String> aliases = new ArrayList(Collections.singletonList(alias)); // ну...
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            JavaPlugin javaPlugin = (JavaPlugin)plugin;
            if ((javaPlugin.getCommand(alias) != null) && (javaPlugin.getCommand(alias).getAliases() != null)) {
                aliases.addAll(javaPlugin.getCommand(alias).getAliases());
            }
            if (javaPlugin.getDescription().getCommands() != null) {
                for (Object entry : javaPlugin.getDescription().getCommands().entrySet()) {
                    Map.Entry entryMap = (Map.Entry)entry;
                    String command = entryMap.getKey().toString();
                    if (javaPlugin.getCommand(command) != null ||
                            javaPlugin.getCommand(command).getAliases() != null ||
                            javaPlugin.getCommand(command).getAliases().stream().anyMatch(commandAlias -> commandAlias.equalsIgnoreCase(alias))) {
                        aliases.add(command);
                    }
                }
            }
        }
        return aliases;
    }

    public static List<String> getAliases(List<String> list) {
        List<String> aliases = new ArrayList();
        for(String alias : list) {
            aliases.addAll(Tools.getAliases(alias));
        }
        return aliases;
    }

    public static WorldGuardPlugin getWorldGuard() {// после увиденных утилиток на ворлдгуард и ворлдедит я потерял смысл как либо взаимодействовать потом с этими плагинами через огромные утилки
        Plugin pluginWorldGuard = Bukkit.getPluginManager().getPlugin("WorldEdit");
        if(pluginWorldGuard instanceof WorldGuardPlugin) {
            return (WorldGuardPlugin) pluginWorldGuard;
        }
        return null;
    }

    public static WorldEditPlugin getWorldEdit() {
        Plugin pluginWorldEdit = Bukkit.getPluginManager().getPlugin("WorldEdit");
        if(pluginWorldEdit instanceof WorldEditPlugin) {
            return (WorldEditPlugin)pluginWorldEdit;
        }
        return null;
    }

    public static int random(int n, int n2) {
        return n + random.nextInt(n2 + 1 - n);
    }

    public static boolean chance(int count) {
        return (count > random.nextInt(101));
    }

    public static void disable() {
        HandlerList.unregisterAll(instance); // от меня лично HandlerList
        Bukkit.getPluginManager().disablePlugin(instance);
    }

    public static void execute(String command) { // баккит творит удивительные вещи
        Bukkit.getScheduler().runTask(instance, new Runnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }
        });
    }

    public static void execute(List<String> list) {
        for(String command : list) {
            execute(command);
        }
    }

}
