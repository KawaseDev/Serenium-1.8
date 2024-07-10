package io.github.nyghtfull;

import io.github.nyghtfull.component.ComponentManager;
import io.github.nyghtfull.event.EventManager;
import io.github.nyghtfull.module.ModuleManager;
import io.github.nyghtfull.ui.imgui.ImGuiImpl;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import org.lwjglx.Sys;
import org.lwjglx.opengl.Display;

/**
 * @author Nyghtfull
 * @since 5/21/2024
 */
@Getter
public class Serenium {
    @Getter
    private static final Serenium instance = new Serenium();
    public static final String NAME = "Serenium", VERSION = "b1",
            BY = "Developed By Nyghtfull & Protected By Liticane <3";

    private ModuleManager moduleManager;
    private EventManager eventManager;
    private ComponentManager componentManager;

    public void init() {
        Display.setTitle("Minecraft 1.8.9  - LWJGL " + Sys.getVersion());
        Minecraft.getMinecraft().setWindowIcon();

        moduleManager = new ModuleManager();
        eventManager = new EventManager();
        componentManager = new ComponentManager();

        componentManager.registerComponents();
        moduleManager.registerModules();

        ImGuiImpl.initialize(
                Display.Window.handle
        );
    }

    public void close() { /* w */ }
}
