package io.github.nyghtfull.event.events.impl.packet;

import io.github.nyghtfull.event.events.callables.EventCancellable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.Packet;

@Getter
@Setter
@AllArgsConstructor
public class PacketReceiveEvent extends EventCancellable {
    private Packet<?> packet;
}

