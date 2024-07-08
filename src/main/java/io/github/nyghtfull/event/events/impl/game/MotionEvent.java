package io.github.nyghtfull.event.events.impl.game;

import io.github.nyghtfull.event.events.callables.EventCancellable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MotionEvent extends EventCancellable {
    private double x, y, z;
    private float yaw, pitch;
    private boolean onGround;
}
