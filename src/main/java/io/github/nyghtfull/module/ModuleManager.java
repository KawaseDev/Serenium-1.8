package io.github.nyghtfull.module;

import io.github.nyghtfull.Serenium;
import io.github.nyghtfull.event.EventTarget;
import io.github.nyghtfull.event.events.impl.input.KeyboardEvent;
import io.github.nyghtfull.module.modules.combat.AutoClickerModule;
import io.github.nyghtfull.module.modules.combat.NoClickDelay;
import io.github.nyghtfull.module.modules.movement.*;
import io.github.nyghtfull.module.modules.render.*;

import java.util.List;
import java.util.Objects;

public class ModuleManager extends Manager<Module> {
    public void registerModules() {
        this.register(
                //Combat
                new AutoClickerModule(),
                new NoClickDelay(),

                // Movement
                new SprintModule(),
                new SpeedModule(),

                //Visual
                new ClickGuiModule(),
                new FurrySpeechModule(),
                new HitPosModule()
        );

        Serenium.getInstance().getEventManager().register(this);
    }

    @EventTarget
    public void onKeyboard(KeyboardEvent event) {
        Serenium.getInstance().getModuleManager().getObjects().stream().filter(module -> module.getKeyCode() == event.getKeyCode()).forEach(Module::toggle);
    }

    @SuppressWarnings("unchecked")
    public <T extends Module> T getModule(final Class<T> clazz) {
        return (T) this.getBy(module -> Objects.equals(module.getClass(), clazz));
    }

    @SuppressWarnings("unchecked")
    public <T extends Module> T getModule(String name) {
        return (T) getObjects().stream()
                .filter(module -> module.getName().equalsIgnoreCase(name))
                .findAny()
                .orElse(null);
    }

    public List<Module> getModules() {
        return this.getObjects();
    }

    public List<Module> getModulesFromCategory(Category category) {
        return this.getMultipleBy(module -> module.getCategory() == category);
    }
}
