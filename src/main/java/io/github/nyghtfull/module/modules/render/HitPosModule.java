package io.github.nyghtfull.module.modules.render;

import io.github.nyghtfull.event.EventTarget;
import io.github.nyghtfull.event.events.impl.game.UpdateEvent;
import io.github.nyghtfull.event.events.impl.render.Render3DEvent;
import io.github.nyghtfull.module.Category;
import io.github.nyghtfull.module.Module;
import io.github.nyghtfull.property.properties.NumberProperty;
import io.github.nyghtfull.util.render.ESPUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class HitPosModule extends Module {
    private NumberProperty width = new NumberProperty("Width", 0.1f, 0.01f, 3, 0.05f);
    private NumberProperty height = new NumberProperty("Height", 0.2f, 0.01f, 3, 0.05f);

    private Vec3 hitPosition;
    private Entity entity;

    public HitPosModule() {
        super("Hit Pos", "Shows In What Position You Hit Your Target", Category.VISUAL);
        registerProperties(width, height);
    }

    @EventTarget
    public void updateEvent(UpdateEvent event) {
        if (mc.gameSettings.keyBindAttack.isKeyDown() && mc.objectMouseOver != null &&
                mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY
        ) {
            entity = mc.objectMouseOver.entityHit;
            hitPosition = mc.objectMouseOver.hitVec;
        }
    }

    @EventTarget
    public void onRender3D(Render3DEvent event) {
        if (entity == null) {
            return;
        }

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GlStateManager.disableCull();
        GL11.glDepthMask(false);

        ESPUtil.drawESPPos(
                (float) hitPosition.xCoord,
                (float) hitPosition.yCoord,
                (float) hitPosition.zCoord,
                width.getProperty().get(), height.getProperty().get(), Color.white
        );

        GL11.glDepthMask(true);
        GlStateManager.enableCull();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }
}
