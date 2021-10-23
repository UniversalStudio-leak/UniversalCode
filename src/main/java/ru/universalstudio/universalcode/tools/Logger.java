package ru.universalstudio.universalcode.tools;

import org.bukkit.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: UniversalStudio
 * @Author default source code: WinLocker02 (Thank pasting wCode -> UniversalCode)
 */

public class Logger {

    public static void info(String text) {
        Bukkit.getConsoleSender().sendMessage("§e(" + Tools.getInstance().getName() + "/INFO) " + text);
    }

    public static void warn(String text) {
        Bukkit.getConsoleSender().sendMessage("§e(" + Tools.getInstance().getName() + "/WARN) " + text);
    }

    public static void error(String text) {
        Bukkit.getConsoleSender().sendMessage("§e(" + Tools.getInstance().getName() + "/ERROR) " + text);
    }

}
