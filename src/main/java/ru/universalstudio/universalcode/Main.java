package ru.universalstudio.universalcode;

import org.bukkit.*;
import org.bukkit.plugin.java.*;
import ru.universalstudio.universalcode.data.*;
import ru.universalstudio.universalcode.tools.*;
import ru.universalstudio.universalcode.commands.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: UniversalStudio
 * @Author default source code: WinLocker02 (Thank pasting wCode -> UniversalCode)
 */

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Tools.setInstance(this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[UniversalCode] Plugin recompiled by NaulbiMIX | Sponsored by FlatiCommunity (https://t.me/flaticommunity) | Specially publication for https://teletype.in/@naulbimix/rumine");
        Codes.loadCodes();
        DataPlayers.loadData();
        DataCodes.loadData();
        getCommand("code").setExecutor(new ExecuteCode());
        getCommand("codereload").setExecutor(new ExecuteCodeReload());
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[UniversalCode] The plugin started successfully! Website: u-studio.su");
    }

    @Override
    public void onDisable() {
        DataPlayers.saveData();
        DataCodes.saveData();
    }

}
