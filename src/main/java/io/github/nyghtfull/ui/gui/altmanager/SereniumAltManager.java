package io.github.nyghtfull.ui.gui.altmanager;

import io.github.nyghtfull.ui.gui.GuiSelectedText;
import io.github.nyghtfull.util.math.TimeUtil;
import io.github.nyghtfull.util.player.TokenUtil;
import io.github.nyghtfull.util.render.RenderUtil;
import net.minecraft.client.gui.*;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Session;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SereniumAltManager extends GuiScreen {
    public static List<Alt> alts = new ArrayList<>(Arrays.asList(
    ));


    private int selectedID = 0;
    private String selectedName = "", token = "", status;
    private GuiSelectedText guiSelectedText;
    private boolean isPremium, attemptedToLogin;
    private TimeUtil timeUtil = new TimeUtil();

    public void initGui() {
        int j = this.height / 4 + 48;

        addButton(0, this.width / 2 + 50, j + 300, 100, "Direct Login");
        addButton(1, this.width / 2 - 60, j + 300, 100, "Add Alt");
        addButton(2, this.width / 2 - 170, j + 300, 100, "Remove Alt");

        timeUtil.reset();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int centerX = scaledResolution.getScaledWidth() / 2;
        int centerY = scaledResolution.getScaledHeight() / 2 - 265;
        int yOffset = 0;
        int id = 0;

        for (Alt item : alts) {
            //old (why make an alt class when u can summon 2 demons just to separate a name and a token ima just leave it like this cuz its funny <3)
            try {
                guiSelectedText = (new GuiSelectedText(
                        id, this.width / 2 - 100, centerY + 10 + yOffset,
                        190, 30, item.getName(),
                        item.getToken() != null ? TokenUtil.getProfileInfoT(item.getToken())[1] : /* uuid of the steve account */ "8667ba71-b85a-4004-af54-457a9734eed7")
                );
            } catch (IOException e) {
                e.printStackTrace();
            }

            selectedTextList.add(guiSelectedText);

            if (RenderUtil.isHovered(mouseX, mouseY, guiSelectedText.xPosition, guiSelectedText.yPosition, guiSelectedText.getButtonWidth(), guiSelectedText.getHeight())) {
                selectedID = guiSelectedText.id;
                selectedName = item.getName();

                isPremium = item.getToken() != null;
                token = item.getToken();
            }

            yOffset += fontRendererObj.FONT_HEIGHT + 25;
            id += 1;
        }

        drawCenteredString(mc.fontRendererObj,
                "Alts. Hovered " + selectedName + EnumChatFormatting.WHITE + " Selected ID " +
                        selectedID + " (" + (status == null ? mc.session.getUsername() : status) + ")", centerX, centerY, Color.WHITE.getRGB()
        );
        //RenderUtil.drawGradientText(mc.session.getUsername(), 3, 3, new Color(78, 73, 165), Color.white);

        //acoustic
        if (timeUtil.finished(200)) {
            attemptedToLogin = false;
        }

        if (!attemptedToLogin) {
            timeUtil.reset();
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0 -> mc.displayGuiScreen(new GuiSelectWorld(this));
            case 1 -> mc.displayGuiScreen(new AddAltGui());
        }
    }

    public void actionPerformed(GuiSelectedText text) {
        if (text.id == selectedID && !attemptedToLogin) {
            if (isPremium) {
                new Thread(() -> {
                    try {
                        String[] playerInfo = TokenUtil.getProfileInfo(token);
                        mc.session = new Session(playerInfo[0], playerInfo[1], token, "mojang");
                        status = "Logged in as " + playerInfo[0];
                    } catch (Exception e) {
                        status = "Token Expired Re Add The Alt";
                        e.printStackTrace();
                    }
                }).start();
            } else {
                mc.session = new Session(selectedName, "", "", "mojang");
                status = "Logged in as " + selectedName;
            }

            attemptedToLogin = true;
        }
    }

    private void addButton(int id, int width, int x, int size, String title) {
        GuiButton button = new GuiButton(id, width, x, title);
        button.setWidth(size);
        buttonList.add(button);
    }
}
