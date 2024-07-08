package io.github.nyghtfull.event.events.impl.render;

import io.github.nyghtfull.event.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.client.gui.ScaledResolution;

@Getter
@AllArgsConstructor
public class Render2DEvent implements Event {
    private final ScaledResolution scaledResolution;
    private final float partialTicks;
}
