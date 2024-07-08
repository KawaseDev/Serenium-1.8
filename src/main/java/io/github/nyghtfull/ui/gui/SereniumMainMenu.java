package io.github.nyghtfull.ui.gui;

import io.github.nyghtfull.Serenium;
import io.github.nyghtfull.ui.gui.altmanager.SereniumAltManager;
import io.github.nyghtfull.util.render.RenderUtil;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class SereniumMainMenu extends GuiScreen {

    public void initGui() {
        int j = this.height / 4 + 48;
        int x = j, y = 25;

        //could add all of them at once but meh
        buttonList.add(new GuiButton(0, this.width / 2 - 100, j + 72 + 12, 98, 20, I18n.format("menu.options")));
        buttonList.add(new GuiButton(1, this.width / 2 - 100, x, I18n.format("menu.singleplayer")));
        buttonList.add(new GuiButton(2, this.width / 2 - 100, x + y * 1, I18n.format("menu.multiplayer")));
        buttonList.add(new GuiButton(3, this.width / 2 + 2, j + 72 + 12, 98, 20, I18n.format("menu.quit")));
        buttonList.add(new GuiButton(4, this.width / 2 - 100, x + y * 2, "Alt Manager"));
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        //todo: add themes
        GL11.glPushMatrix();
        GL11.glScalef(3, 3, 3);

        ScaledResolution scaledResolution = new ScaledResolution(mc);
        RenderUtil.drawGradientText(
                Serenium.NAME,
                scaledResolution.getScaledWidth() / 6.0f - fontRendererObj.getStringWidth(Serenium.NAME) / 2.0f - 3,
                scaledResolution.getScaledHeight() / 12, new Color(78, 73, 165), Color.white
        );

        GL11.glPopMatrix();

        RenderUtil.drawGradientText(
                Serenium.BY,
                scaledResolution.getScaledWidth() / 2f - fontRendererObj.getStringWidth(Serenium.BY) / 2.0f - 1,
                scaledResolution.getScaledHeight() / 4 + 32, new Color(78, 73, 165), Color.white
        );

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0 -> mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
            case 1 -> mc.displayGuiScreen(new GuiSelectWorld(this));
            case 2 -> mc.displayGuiScreen(new GuiMultiplayer(this));
            case 3 -> mc.shutdown();
            case 4 -> mc.displayGuiScreen(new SereniumAltManager());
        }
    }
}
