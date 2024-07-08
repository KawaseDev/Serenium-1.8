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

public class SprintModule extends Module {
    private BooleanProperty legit = new BooleanProperty("Legit", true);
    private NumberProperty testNumberProperty = new NumberProperty("Test Number Property", 2, 0, 10, 1);
    private ModeProperty testModeProperty = new ModeProperty("Test Number Property", "First", "First", "Second", "Third");

    public SprintModule() {
        super("Sprint", "Sprints Automatically", Category.MOVEMENT, Keyboard.KEY_R);
        registerProperties(legit, testNumberProperty, testModeProperty);
        setToggled(true);
    }

    @EventTarget
    public void updateEvent(UpdateEvent event) {
        if(legit.getProperty()) {
            mc.gameSettings.keyBindSprint.pressed = true;
        } else {
            mc.thePlayer.setSprinting(MoveUtil.isMoving());
        }
    }
}
