package io.github.nyghtfull.ui.gui.altmanager;

import io.github.nyghtfull.util.internet.BroswerUtil;
import io.github.nyghtfull.util.io.DataUtil;
import io.github.nyghtfull.util.math.RandomUtil;
import io.github.nyghtfull.util.render.RenderUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.Session;
import org.lwjglx.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public class AddAltGui extends GuiScreen {
    public GuiTextField username;

    public void initGui() {
        //buttons
        buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 96 + 12, I18n.format("Log In")));
        buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 120 + 12, I18n.format("Back")));
        buttonList.add(new GuiButton(2, width / 2 - 100, height / 4 + 144 + 12, I18n.format("Browser Login")));
        buttonList.add(new GuiButton(3, width / 2 - 100, height / 4 + 168 + 12, I18n.format("Random Username")));

        username = new GuiTextField(2, fontRendererObj, width / 2 - 100, 116, 200, 20);
        username.setMaxStringLength(128);
        username.setFocused(true);
        Keyboard.enableRepeatEvents(true);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        RenderUtil.drawGradientText(mc.session.getUsername(), 3, 3, new Color(78, 73, 165), Color.white);

        username.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                mc.session = new Session(username.getText(), "", "", "mojang");
                SereniumAltManager.alts.add(new Alt(username.getText(), null, ""));
                break;
            case 1:
                onGuiClosed();
                mc.displayGuiScreen(null);
                break;
            case 2:
                MicrosoftLogin.getRefreshToken(refreshToken -> {
                    if (refreshToken != null) {
                        new Thread(() -> {
                            MicrosoftLogin.LoginData loginData = BroswerUtil.loginWithRefreshToken(refreshToken);
                            SereniumAltManager.alts.add(new Alt(mc.session.getUsername(), loginData.mcToken, "") /* + loginData.mcToken */);
                        }).start();
                    }
                }, true);
                break;
            case 3:
                String ign = getRandomUserName();
                mc.session = new Session(ign, "", "", "mojang");
                SereniumAltManager.alts.add(new Alt(ign, null, ""));
                break;
        }
    }

    public void updateScreen() {
        username.updateCursorCounter();
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (!username.textboxKeyTyped(typedChar, keyCode) && keyCode == 28 || keyCode == 156) {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        username.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    private String getRandomUserName() {
        String[] commonStarting = new String[] { "o", "0", "qt", "thug", "_" };
        String[] commonEnding = new String[] { "yt", "tv", "kick", "PvP" };
        String commonNames = DataUtil.commonNames[RandomUtil.getAdvancedRandom(1, DataUtil.commonNames.length) - 1];
        int endOrStart = RandomUtil.getAdvancedRandom(1, 2);

        String startName = commonStarting[RandomUtil.getAdvancedRandom(1, commonStarting.length - 1)] + commonNames;
        String endName = commonNames + commonEnding[RandomUtil.getAdvancedRandom(1, commonEnding.length - 1)];

        return (endOrStart == 1 ? startName : endName) + RandomUtil.getAdvancedRandom(1, 100);
    }
}
