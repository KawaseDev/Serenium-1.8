package io.github.nyghtfull.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import io.github.nyghtfull.Serenium;
import io.github.nyghtfull.interfaces.IAccess;
import io.github.nyghtfull.property.Property;
import lombok.Getter;
import lombok.Setter;
import org.lwjglx.Sys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Module implements IAccess {
    private final String name, desc;
    private final Category category;
    public int keyCode;
    private final List<Property<?>> propertyList = new ArrayList<>();

    private String suffix;
    private boolean toggled;

    protected Module(String name, String description, Category category) {
        this.name = name;
        this.category = category;
        this.keyCode = 0;
        this.desc = !description.isBlank() ? description : "This Module Does Not Contain a Description";
    }

    protected Module(String name, String description, Category category, int keyCode) {
        this.name = name;
        this.category = category;
        this.keyCode = keyCode;
        this.desc = !description.isBlank() ? description : "This Module Does Not Contain a Description";
    }

    public void registerProperty(Property<?> property) {
        propertyList.add(property);
    }

    public void registerProperties(Property<?>... properties) {
        propertyList.addAll(Arrays.asList(properties));
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;

        if(isToggled()) {
            System.out.println("attempting to enable modules");
            onEnable();
        } else {
            //we buy the milk
            System.out.println("attempting to disable modules");
            onDisable();
        }
    }

    public void toggle() {
        setToggled(!isToggled());
    }

    public void onEnable() {
        Serenium.getInstance().getEventManager().register(this);
    }

    public void onDisable() {
        Serenium.getInstance().getEventManager().unregister(this);
    }

    public String getDisplayName(boolean doesHaveSuffix) {
        String idk = getName();

        if(suffix != null) {
            idk += doesHaveSuffix ? getPreFix() + getSuffix() : "";
        }

        return idk;
    }

    private String getPreFix() {
        //todo: make this have multiple stuff like -, # and so on
        return ChatFormatting.GRAY +  " ";
    }
}
