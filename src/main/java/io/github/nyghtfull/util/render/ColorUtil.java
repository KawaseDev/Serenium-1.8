package io.github.nyghtfull.util.render;

import io.github.nyghtfull.interfaces.IAccess;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjglx.util.vector.Vector2f;

import java.awt.*;

public class ColorUtil implements IAccess {
    public static float[] toGLColor(final int color) {
        final float f = (float) (color >> 16 & 255) / 255.0F;
        final float f1 = (float) (color >> 8 & 255) / 255.0F;
        final float f2 = (float) (color & 255) / 255.0F;
        final float f3 = (float) (color >> 24 & 255) / 255.0F;
        return new float[]{f, f1, f2, f3};
    }

    public static Color toColor(float[] colors){
        return new Color(colors[0], colors[1], colors[2], colors[3]);
    }

    public static Color getAccentColor(Vector2f screenCoordinates, Color firstColor, Color secondColor) {
        return mixColors(firstColor, secondColor, getBlendFactor(screenCoordinates));
    }

    static Color mixColors(final Color color1, final Color color2, final double percent) {
        final double inverse_percent = 1.0 - percent;
        final int redPart = (int) (color1.getRed() * percent + color2.getRed() * inverse_percent);
        final int greenPart = (int) (color1.getGreen() * percent + color2.getGreen() * inverse_percent);
        final int bluePart = (int) (color1.getBlue() * percent + color2.getBlue() * inverse_percent);

        return new Color(redPart, greenPart, bluePart);
    }

    public static double getBlendFactor(Vector2f screenCoordinates) {
        return Math.sin(
                System.currentTimeMillis() / 175.0D + screenCoordinates.getX() * 0.0007D + screenCoordinates.getY() * 0.0007D
        ) * 0.5D + 0.5D;
    }

    public static void color(int color, float alpha) {
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        GlStateManager.color(r, g, b, alpha);
    }

    public static Color withAlpha(final Color color, final int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    public static int getSwap(Color color) {
        return new Color(color.getBlue(), color.getGreen(), color.getRed()).getRGB();
    }
}
