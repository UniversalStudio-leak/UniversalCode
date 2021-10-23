package ru.universalstudio.universalcode.data;

import java.io.*;
import java.util.*;
import org.bukkit.entity.*;
import ru.universalstudio.universalcode.tools.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: UniversalStudio
 * @Author default source code: WinLocker02 (Thank pasting wCode -> UniversalCode)
 */

public class DataPlayers {

    private static Properties data = new Properties();
    private static File file = new File(Tools.getInstance().getDataFolder(), "data.properties");
    private static Map players = new HashMap<>();

    public static void loadData() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            data.load(fileInputStream);
            data.forEach((player, time) -> {
                players.put(player.toString(), Integer.parseInt(time.toString()));
            });
            file.delete();
            data.clear();
            fileInputStream.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void saveData() {
        try {
            for (Object entry : players.entrySet()) {
                Map.Entry entryMap = (Map.Entry) entry;
                data.setProperty(entryMap.getKey().toString(), Integer.toString((int)entryMap.getValue()));
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            data.store(fileOutputStream, null);
            fileOutputStream.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int getPlayer(String player, String code) {
        String codePlayer = player + "." + code;
        return (players.get(codePlayer) == null) ? 0 : ((int)players.get(codePlayer));
    }

    public static int getPlayer(Player player, String code) {
        return getPlayer(player.getName(), code);
    }

    public static void addUse(Player player, String code) {
        players.put((player.getName() + "." + code), getPlayer(player, code) + 1);
    }

}
