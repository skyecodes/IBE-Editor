package com.github.franckyi.guapi.event;

import net.minecraftforge.client.event.GuiScreenEvent;

@FunctionalInterface
public interface EventHandler<T extends GuiScreenEvent> {

    void handle(T event);

}
