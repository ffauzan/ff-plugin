package uk.haku.ffp;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.data.def.ItemData;
import emu.grasscutter.game.avatar.Avatar;
import emu.grasscutter.game.entity.EntityAvatar;
import emu.grasscutter.game.inventory.GameItem;
import emu.grasscutter.game.player.Player;

import java.util.*;

@Command(label = "ffequip", usage = "ffequip <itemId>",
aliases = {"ffeq"}, permission = "ff.equip")

public class Equip implements CommandHandler {
    @Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {
        if (args.size() != 1) {
            CommandHandler.sendMessage(sender, "wrong args size");
            return;
        }

        Avatar activeAvatar = sender.getTeamManager().getCurrentAvatarEntity().getAvatar();

        int itemId = Integer.parseInt(args.get(0));
        GameItem item = new GameItem(itemId);

        sender.getInventory().addItem(item);
        boolean isSuccess =  activeAvatar.equipItem(item, true);
        
        if (isSuccess == true) {
            CommandHandler.sendMessage(sender, "Succes " + item.getItemType().name());
        } else {
            CommandHandler.sendMessage(sender, "Fail " + item.getItemType().name());
        }
    }
}