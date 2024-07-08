package io.github.nyghtfull.event.events.impl.render;

import io.github.nyghtfull.event.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Render3DEvent implements Event {
    private final float partialTicks;
}
