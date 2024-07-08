package io.github.nyghtfull.ui.gui.altmanager;

import lombok.Getter;

@Getter
public class Alt {
    public String name, token, skinId;

    public Alt(String name, String token, String skinId) {
        this.name = name;
        this.token = token;
        this.skinId = skinId;
    }
}
