package io.github.nyghtfull.module;

import lombok.Getter;

@Getter
public enum Category {
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    WORLD("World"),
    PLAYER("Player"),
    VISUAL("Visual"),
    EXPLOIT("Exploit"),
    CSGO("CsGo");

    private final String name;

    Category(String theName) {
        name = theName;
    }
}
