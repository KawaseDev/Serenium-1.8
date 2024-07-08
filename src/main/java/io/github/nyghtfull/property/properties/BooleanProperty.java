package io.github.nyghtfull.property.properties;

import io.github.nyghtfull.property.Property;

public class BooleanProperty extends Property<Boolean> {
    public BooleanProperty(String name, Boolean toggled) {
        super(name, toggled);
    }
}
