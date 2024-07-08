package io.github.nyghtfull.property.properties;

import imgui.type.ImFloat;
import io.github.nyghtfull.property.Property;
import lombok.Getter;

@Getter
public class NumberProperty extends Property<ImFloat> {
    public Float min, value, max, increment;
    public ImFloat imFloat;

    public NumberProperty(String name, float value, float min, float max, float increment) {
        super(name, new ImFloat(value));
        this.min = min;
        this.value = value;
        this.max = max;
        this.increment = increment;
        this.imFloat = new ImFloat(value);
    }

    public void setValue(double value) {
        double precision = 1 / increment;
        this.value = (float) (Math.round(Math.max(min, Math.min(max, value)) * precision) / precision);
    }
}
