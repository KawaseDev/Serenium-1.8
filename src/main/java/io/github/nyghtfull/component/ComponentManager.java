package io.github.nyghtfull.component;

import io.github.nyghtfull.Serenium;
import io.github.nyghtfull.module.Manager;

import java.util.Objects;


public class ComponentManager extends Manager<Component> {
    public void registerComponents() {
        this.register(
                //no components yet
        );

        Serenium.getInstance().getEventManager().register(this);
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponent(final Class<T> clazz) {
        return (T) this.getBy(module -> Objects.equals(module.getClass(), clazz));
    }
}
