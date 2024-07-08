package io.github.nyghtfull.util.render;

import io.github.nyghtfull.interfaces.IAccess;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjglx.util.vector.Vector2f;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtil implements IAccess {
    public static void drawGradientText(String text, float x, float y, Color firstColor, Color secondColor) {
        FontRenderer fr = mc.fontRendererObj;
        float charX = x;

        for (char i2 : (text.toCharArray())) {
            String string = String.valueOf(i2);

            fr.drawStringWithShadow(
                    string,
                    charX, y,
                    ColorUtil.getAccentColor(new Vector2f(charX * 32, 6), firstColor, secondColor).getRGB()
            );

            charX += fr.getStringWidth(string) + 0.50F;
        }
    }

    public static void image(final ResourceLocation imageLocation, final float x, final float y, final float width, final float height, final Color color) {
        glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.0F);
        GL11.glColor4d(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
        mc.getTextureManager().bindTexture(imageLocation);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, width, height, width, height);
        GlStateManager.resetColor();
        GlStateManager.disableBlend();
    }

    public static void drawOutlinedRec(float x, float y, float width, float height, final float outlineThickness, int rectColor, int outlineColor) {
        Gui.drawRect((int) x, (int) y, (int) width, (int) height, rectColor);
        glEnable(GL_LINE_SMOOTH);
        ColorUtil.color(outlineColor, 255);

        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableTexture2D();

        glLineWidth(outlineThickness);
        float cornerValue = (float) (outlineThickness * .19);

        glBegin(GL_LINES);
        glVertex2d(x, y - cornerValue);
        glVertex2d(x, y + height + cornerValue);
        glVertex2d(x + width, y + height + cornerValue);
        glVertex2d(x + width, y - cornerValue);
        glVertex2d(x, y);
        glVertex2d(x + width, y);
        glVertex2d(x, y + height);
        glVertex2d(x + width, y + height);
        glEnd();

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();

        glDisable(GL_LINE_SMOOTH);
    }

    public static boolean isHovered(final float mouseX, final float mouseY, final float x, final float y, final float width, final float height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
