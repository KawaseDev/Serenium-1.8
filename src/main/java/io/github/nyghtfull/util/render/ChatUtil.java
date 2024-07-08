package io.github.nyghtfull.util.render;

import com.mojang.realmsclient.gui.ChatFormatting;
import io.github.nyghtfull.Serenium;
import io.github.nyghtfull.interfaces.IAccess;
import net.minecraft.util.ChatComponentText;

public class ChatUtil implements IAccess {
    public static void display(final Object message) {
        if (mc.thePlayer != null) {
            mc.thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.RED + "[" + Serenium.NAME + "]" + ChatFormatting.GRAY + " -> " + message));
        }
    }
}
