package io.github.nyghtfull.util.render;

import io.github.nyghtfull.interfaces.IAccess;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ESPUtil implements IAccess {
    public static void drawESPPos(float x2, float y2, float z2, float width, float height, final Color color) {
        final float x = (float) (x2 - mc.getRenderManager().renderPosX);
        final float y = (float) (y2 - mc.getRenderManager().renderPosY);
        final float z = (float) (z2 - mc.getRenderManager().renderPosZ);
        float[] rgba = color.getRGBColorComponents(null);

        GL11.glLineWidth(2);
        GL11.glColor4f(rgba[0], rgba[1], rgba[2], 1f);
        drawOutLine(x, y, z, width, height + 0.1f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
    }

    public static void drawOutLine(float x, float y, float z, float width, float height) {
        width *= 1.5F;

        float x1 = x - width;
        float z1 = z - width;
        float x2 = x + width;
        float z2 = z - width;
        float x3 = x + width;
        float z3 = z + width;
        float x4 = x - width;
        float z4 = z + width;
        float y2 = y + height;

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer buffer = tessellator.getWorldRenderer();

        buffer.begin(GL11.GL_LINE_LOOP, DefaultVertexFormats.POSITION);
        buffer.pos(x1, y, z1).endVertex();
        buffer.pos(x2, y, z2).endVertex();
        buffer.pos(x3, y, z3).endVertex();
        buffer.pos(x4, y, z4).endVertex();
        tessellator.draw();

        buffer.begin(GL11.GL_LINE_LOOP, DefaultVertexFormats.POSITION);
        buffer.pos(x1, y2, z1).endVertex();
        buffer.pos(x2, y2, z2).endVertex();
        buffer.pos(x3, y2, z3).endVertex();
        buffer.pos(x4, y2, z4).endVertex();
        tessellator.draw();

        buffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
        buffer.pos(x1, y, z1).endVertex();
        buffer.pos(x1, y2, z1).endVertex();
        buffer.pos(x2, y, z2).endVertex();
        buffer.pos(x2, y2, z2).endVertex();
        buffer.pos(x3, y, z3).endVertex();
        buffer.pos(x3, y2, z3).endVertex();
        buffer.pos(x4, y, z4).endVertex();
        buffer.pos(x4, y2, z4).endVertex();
        tessellator.draw();
    }
}
