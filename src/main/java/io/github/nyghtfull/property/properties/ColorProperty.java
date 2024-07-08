package io.github.nyghtfull.property.properties;

import io.github.nyghtfull.property.Property;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class ColorProperty extends Property<Color> {

    private float hue = 0;
    private float saturation = 1;
    private float brightness = 1;

    public ColorProperty(String name, Color color) {
        super(name, color);
        this.setColor(color);
    }

    public Color getColor() {
        return new Color(Color.HSBtoRGB(hue, saturation, brightness), true);
    }

    public void setColor(Color color) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        hue = hsb[0];
        saturation = hsb[1];
        brightness = hsb[2];
    }

    public void setColor(float hue, float saturation, float brightness, float alpha) {
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;

    }
}
