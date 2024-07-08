package io.github.nyghtfull.util.render;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;
import io.github.nyghtfull.Serenium;
import io.github.nyghtfull.interfaces.IAccess;
import io.github.nyghtfull.module.modules.render.ClickGuiModule;

import java.awt.*;

public class ImguiUtil implements IAccess {
    public static void setDarkMode() {
        ImGuiStyle im = ImGui.getStyle();

        im.setWindowRounding(5);
        im.setFrameRounding(5);
        im.setColor(ImGuiCol.Text, new Color(255, 255, 255).getRGB());
        im.setColor(ImGuiCol.TextDisabled, new Color(128, 128, 128).getRGB());

        im.setColor(ImGuiCol.WindowBg, new Color(30, 30, 30).getRGB());
        im.setColor(ImGuiCol.ChildBg, new Color(40, 40, 40).getRGB());
        im.setColor(ImGuiCol.PopupBg, new Color(50, 50, 50).getRGB());

        im.setColor(ImGuiCol.Border, new Color(30, 30, 30).getRGB());
        im.setColor(ImGuiCol.BorderShadow, new Color(30, 30, 30).getRGB());

        //new Color(60, 60, 200)
        int cherryColor = ColorUtil.getSwap(Serenium.getInstance().getModuleManager().getModule(ClickGuiModule.class).color.getColor());
        // Color cherryColorHov = new Color(90, 90, 200);
        int cherryColorHov = ColorUtil.getSwap(Serenium.getInstance().getModuleManager().getModule(ClickGuiModule.class).hovColor.getColor());
        //fram outline thing
        im.setColor(ImGuiCol.FrameBg, new Color(40, 40, 40).getRGB());
        im.setColor(ImGuiCol.FrameBgHovered, new Color(60, 60, 60).getRGB());
        im.setColor(ImGuiCol.FrameBgActive, cherryColor);
        im.setColor(ImGuiCol.CheckMark, cherryColor);
        im.setColor(ImGuiCol.Button, cherryColor);
        im.setColor(ImGuiCol.ButtonActive, cherryColor);
        im.setColor(ImGuiCol.ButtonHovered, cherryColorHov);
        im.setColor(ImGuiCol.SliderGrab, cherryColor);
        im.setColor(ImGuiCol.SliderGrabActive, cherryColorHov);
        im.setColor(ImGuiCol.Tab, cherryColor);
        im.setColor(ImGuiCol.TabHovered, cherryColorHov);
        im.setColor(ImGuiCol.TabActive, cherryColor);
        im.setColor(ImGuiCol.Header, cherryColor);
        im.setColor(ImGuiCol.HeaderHovered, cherryColorHov);
        im.setColor(ImGuiCol.HeaderActive, cherryColor);

        im.setColor(ImGuiCol.TitleBg, new Color(25, 25, 25).getRGB());
        im.setColor(ImGuiCol.TitleBgActive, new Color(35, 35, 35).getRGB());
        im.setColor(ImGuiCol.TitleBgCollapsed, new Color(15, 15, 15).getRGB());

        im.setColor(ImGuiCol.MenuBarBg, new Color(30, 30, 30).getRGB());

        im.setColor(ImGuiCol.ScrollbarBg, new Color(20, 20, 20).getRGB());
        im.setColor(ImGuiCol.ScrollbarGrab, new Color(60, 60, 60).getRGB());
        im.setColor(ImGuiCol.ScrollbarGrabHovered, new Color(80, 80, 80).getRGB());
        im.setColor(ImGuiCol.ScrollbarGrabActive, new Color(100, 100, 100).getRGB());

        im.setColor(ImGuiCol.TextSelectedBg, new Color(60, 60, 60, 150).getRGB());
        //im.setColor(ImGuiCol.DragDropTarget, new Color(255, 0, 0, 100).getRGB());
        im.setColor(ImGuiCol.NavHighlight, new Color(255, 255, 255, 100).getRGB());
        im.setColor(ImGuiCol.NavWindowingHighlight, new Color(255, 255, 255, 50).getRGB());
        im.setColor(ImGuiCol.NavWindowingDimBg, new Color(20, 20, 20, 150).getRGB());
        im.setColor(ImGuiCol.ModalWindowDimBg, new Color(20, 20, 20, 150).getRGB());
    }
}
