package io.github.nyghtfull.component;

import io.github.nyghtfull.Serenium;
import io.github.nyghtfull.interfaces.IAccess;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Component implements IAccess {
    public boolean toggled;
    public String name;

    public Component() {
        onEnable();
    }

    public void onEnable() {
        Serenium.getInstance().getEventManager().register(this);
    }

    public void onDisable() {
        Serenium.getInstance().getEventManager().unregister(this);
    }
}
