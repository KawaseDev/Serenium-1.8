package io.github.nyghtfull.module.modules.combat;

import io.github.nyghtfull.event.EventTarget;
import io.github.nyghtfull.event.events.impl.game.TickEvent;
import io.github.nyghtfull.module.Category;
import io.github.nyghtfull.module.Module;
import io.github.nyghtfull.property.properties.BooleanProperty;
import io.github.nyghtfull.property.properties.NumberProperty;
import io.github.nyghtfull.util.math.RandomUtil;
import io.github.nyghtfull.util.math.TimeUtil;
import io.github.nyghtfull.util.player.PlayerUtil;
import net.minecraft.util.MovingObjectPosition;

public class AutoClickerModule extends Module {
    private NumberProperty minCps = new NumberProperty("Min Cps", 10, 0, 20, 1);
    private NumberProperty maxCps = new NumberProperty("Max Cps", 10, 0, 20, 1);
    private BooleanProperty clickLeft = new BooleanProperty("Click Left", true);
    private BooleanProperty clickRight = new BooleanProperty("Click Right", true);

    private TimeUtil timeUtil = new TimeUtil();

    public AutoClickerModule() {
        super("Auto Clicker", "Automatically Clicks", Category.COMBAT);
        registerProperties(minCps, maxCps, clickLeft, clickRight);
    }

    @EventTarget
    public void onTick(TickEvent event) {
        boolean finished = timeUtil.finished((long) (850 / RandomUtil.getAdvancedRandom(minCps.getProperty().floatValue(), maxCps.getProperty().floatValue())));

        if (mc.gameSettings.keyBindAttack.isKeyDown() && !mc.gameSettings.keyBindUseItem.isKeyDown() && clickLeft.getProperty() && finished &&
                mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK
        ) {
            //ChatUtil.display("click");
            PlayerUtil.sendClick(0, true);
            timeUtil.reset();
        }

        if (mc.gameSettings.keyBindUseItem.isKeyDown() && clickRight.getProperty() && finished) {
            PlayerUtil.sendClick(1, true);
            timeUtil.reset();
        }
    }
}
