package ru.universalstudio.universalcode.commands;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import ru.universalstudio.universalcode.*;
import ru.universalstudio.universalcode.tools.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: UniversalStudio
 * @Author default source code: WinLocker02 (Thank pasting wCode -> UniversalCode)
 */

public class ExecuteCode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (!(s instanceof Player)) {
            return true;
        }
        if (!Utils.has(s, "ucode.code")) {
            return true;
        }
        Player player = (Player)s;
        if (args.length == 0) {
            Utils.sendMessage(player, Utils.getMessage("code"));
            return true;
        }
        Codes.getCode(args[0]); // если я ошибся, то не кидайте помидроками
        Utils.sendMessage(player, Utils.getMessage("code-notfound"));
        return true;
    }

}
