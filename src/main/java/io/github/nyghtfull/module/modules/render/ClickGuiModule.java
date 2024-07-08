package io.github.nyghtfull.module.modules.render;

import io.github.nyghtfull.module.Category;
import io.github.nyghtfull.module.Module;
import io.github.nyghtfull.property.properties.ColorProperty;
import io.github.nyghtfull.ui.imgui.windows.SereniumClickGui;
import org.lwjglx.input.Keyboard;

import java.awt.*;

public class ClickGuiModule extends Module {
    public ColorProperty color = new ColorProperty("Color", new Color(78, 73, 165));
    public ColorProperty hovColor = new ColorProperty("Hover Color", new Color(66, 61, 140));

    private final SereniumClickGui sereniumClickGui;

    public ClickGuiModule() {
        super("Click Gui", "", Category.VISUAL, Keyboard.KEY_RSHIFT);
        registerProperties(color, hovColor);
        this.sereniumClickGui = new SereniumClickGui();
    }


    @Override
    public void onEnable() {
        mc.displayGuiScreen(sereniumClickGui);
        setToggled(false);
        super.onEnable();
    }
}
