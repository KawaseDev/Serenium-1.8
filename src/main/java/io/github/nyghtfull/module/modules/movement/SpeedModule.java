package io.github.nyghtfull.module.modules.movement;

import io.github.nyghtfull.event.EventTarget;
import io.github.nyghtfull.event.events.impl.game.UpdateEvent;
import io.github.nyghtfull.module.Category;
import io.github.nyghtfull.module.Module;
import io.github.nyghtfull.property.properties.BooleanProperty;
import io.github.nyghtfull.property.properties.ModeProperty;
import io.github.nyghtfull.property.properties.NumberProperty;
import io.github.nyghtfull.util.player.MoveUtil;
import org.lwjglx.input.Keyboard;

public class SpeedModule extends Module {
    private ModeProperty mode = new ModeProperty("Mode", "Vanilla", "Vanilla", "Watchdog", "Verus");

    public SpeedModule() {
        super("Speed", "Zooooooooooooooooooooooooooooooooom weeeeeeeeeeeeeeeehh", Category.MOVEMENT);
        registerProperties(mode);
    }

    @EventTarget
    public void updateEvent(UpdateEvent event) {
        setSuffix(mode.getProperty());
        mc.gameSettings.keyBindJump.pressed = true;

        switch (mode.getProperty()) {
            case "Vanilla":
                MoveUtil.strafe(0.6);
                break;
        }
    }

    @Override
    public void onDisable() {
        mc.gameSettings.keyBindJump.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode());
        super.onDisable();
    }
}
