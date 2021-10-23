package ru.universalstudio.universalcode.commands;

import org.bukkit.command.*;
import ru.universalstudio.universalcode.tools.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: UniversalStudio
 * @Author default source code: WinLocker02 (Thank pasting wCode -> UniversalCode)
 */

public class ExecuteCodeReload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(!Utils.has(s, "ucode.reload")) {
            return true;
        }
        Utils.reloadConfig();
        Utils.sendMessage(s, Utils.getMessage("codereload"));
        return true;
    }

}
