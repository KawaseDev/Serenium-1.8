package io.github.nyghtfull.util.player;

import io.github.nyghtfull.interfaces.IAccess;
import net.minecraft.client.settings.KeyBinding;

public class PlayerUtil implements IAccess {
    public static void sendClick(final int button, final boolean state) {
        final int keyBind = button == 0 ? mc.gameSettings.keyBindAttack.getKeyCode() : mc.gameSettings.keyBindUseItem.getKeyCode();

        KeyBinding.setKeyBindState(keyBind, state);

        if (state) {
            KeyBinding.onTick(keyBind);
        }
    }
}
