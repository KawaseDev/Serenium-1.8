package io.github.nyghtfull.ui.imgui.windows;

import imgui.ImGui;
import imgui.flag.ImGuiColorEditFlags;
import imgui.flag.ImGuiInputTextFlags;
import imgui.type.ImString;
import io.github.nyghtfull.Serenium;
import io.github.nyghtfull.interfaces.IAccess;
import io.github.nyghtfull.module.Category;
import io.github.nyghtfull.module.Module;
import io.github.nyghtfull.property.Property;
import io.github.nyghtfull.property.properties.BooleanProperty;
import io.github.nyghtfull.property.properties.ColorProperty;
import io.github.nyghtfull.property.properties.ModeProperty;
import io.github.nyghtfull.property.properties.NumberProperty;
import io.github.nyghtfull.ui.imgui.ImGuiImpl;
import io.github.nyghtfull.util.render.ColorUtil;
import io.github.nyghtfull.util.render.ImguiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjglx.input.Keyboard;

public class SereniumClickGui extends GuiScreen implements IAccess {
    private final ImString searchText = new ImString(500);
    private boolean shouldSetKey = false;
    private Category category;
    private Module module;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        ImGuiImpl.render(io -> {
            ImGui.setNextWindowSize(1000, 500);
            if (ImGui.begin("Click Gui")) {
                ImguiUtil.setDarkMode();

                for (Category category1 : Category.values()) {
                    ImGui.beginTabBar("Dick <3");

                    if (ImGui.beginTabItem(category1.getName() + "##tab")) {
                        category = category1;
                        ImGui.endTabItem();
                    }

                    ImGui.endTabBar();
                }

                for (Module module : Serenium.getInstance().getModuleManager().getModulesFromCategory(category)) {
                    ImGui.setCursorPosX(ImGui.getCursorPosX() + 20);
                    if (ImGui.collapsingHeader(module.getName())) {
                        drawToggle(module);
                        this.module = module;

                        ImGui.text(module.getDesc());

                        ImGui.sameLine();
                        if (ImGui.button(shouldSetKey ? "Listening..." : "Key " + Keyboard.getKeyName(module.getKeyCode()))) {
                            shouldSetKey = true;
                        }

                        ImGui.separator();
                        for (Property<?> property : module.getPropertyList()) {
                            if (!property.getVisible().get()) continue;

                            if (property instanceof BooleanProperty booleanProperty) {
                                if (ImGui.checkbox(property.getName(), booleanProperty.getProperty())) {
                                    booleanProperty.setProperty(!booleanProperty.getProperty());
                                }
                            }

                            if (property instanceof NumberProperty numberProperty) {
                                if (ImGui.sliderFloat("##" + numberProperty.getName(), numberProperty.getProperty().getData(), numberProperty.getMin(), numberProperty.getMax(), numberProperty.getName() + " " + numberProperty.getProperty().getData()[0])) {
                                    numberProperty.setValue(numberProperty.getProperty().getData()[0]);
                                }

                                numberProperty.getProperty().getData()[0] = numberProperty.getValue();
                            }

                            if (property instanceof ColorProperty colorProperty) {
                                float[] color = ColorUtil.toGLColor(colorProperty.getColor().getRGB());

                                if (ImGui.colorEdit4(property.getName(), color, ImGuiColorEditFlags.AlphaBar)) {
                                    colorProperty.setColor(ColorUtil.toColor(color));
                                }
                            }

                            if (property instanceof ModeProperty modeProperty) {
                                if (ImGui.beginCombo(modeProperty.getName(), modeProperty.getProperty())) {
                                    ImGui.inputTextWithHint("##" + modeProperty.getProperty(), "Search For Modes.", searchText, ImGuiInputTextFlags.None);
                                    String search = searchText.get().toLowerCase();

                                    for (String mode : modeProperty.getModes()) {
                                        if (search.isEmpty() || mode.toLowerCase().contains(search)) {
                                            if (ImGui.selectable(mode)) {
                                                modeProperty.setProperty(mode);
                                                searchText.set(new ImString(500));
                                            }
                                        }
                                    }

                                    ImGui.endCombo();
                                }
                            }
                        }
                    } else {
                        drawToggle(module);
                    }
                }

            }

            ImGui.end();
        });
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE && !shouldSetKey) {
            Minecraft.getMinecraft().displayGuiScreen(null);
        }

        if (shouldSetKey) {
            module.setKeyCode(keyCode == Keyboard.KEY_ESCAPE ? 0 : keyCode);
            shouldSetKey = false;
        }
    }

    public void drawToggle(Module module) {
        ImGui.sameLine(-16);

        ImGui.setCursorPosX(ImGui.getCursorPosX() + 20);
        if (ImGui.checkbox("##T" + module.getName(), module.isToggled())) {
            module.toggle();
        }
    }
}
