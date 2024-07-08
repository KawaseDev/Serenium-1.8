package io.github.nyghtfull.module.modules.combat;

import io.github.nyghtfull.event.EventTarget;
import io.github.nyghtfull.event.events.impl.game.UpdateEvent;
import io.github.nyghtfull.module.Category;
import io.github.nyghtfull.module.Module;

public class NoClickDelay extends Module {
    public NoClickDelay() {
        super("No Click Delay", "Removes The Click Delay In Minecraft", Category.COMBAT);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if(mc.thePlayer != null && mc.theWorld != null) {
            mc.leftClickCounter = 0;
        }
    }
}
