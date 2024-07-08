package io.github.nyghtfull.event.events.impl.input;

import io.github.nyghtfull.event.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KeyboardEvent implements Event {
    private final int keyCode;
}
