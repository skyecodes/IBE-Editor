package com.github.franckyi.guapi;

import com.github.franckyi.guapi.event.EventHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.List;

public interface ScreenEventListener {

    Minecraft mc = Minecraft.getInstance();

    List<EventHandler<GuiScreenEvent.MouseClickedEvent>> getOnMouseClickedListeners();

    List<EventHandler<GuiScreenEvent.MouseReleasedEvent>> getOnMouseReleasedListeners();

    List<EventHandler<GuiScreenEvent.MouseDragEvent>> getOnMouseDraggedListeners();

    List<EventHandler<GuiScreenEvent.MouseScrollEvent>> getOnMouseScrolledListeners();

    List<EventHandler<GuiScreenEvent.KeyboardKeyPressedEvent>> getOnKeyPressedListeners();

    List<EventHandler<GuiScreenEvent.KeyboardKeyReleasedEvent>> getOnKeyReleasedListeners();

    List<EventHandler<GuiScreenEvent.KeyboardCharTypedEvent>> getOnCharTypedListeners();

    default boolean onMouseClicked(GuiScreenEvent.MouseClickedEvent event) {
        this.getOnMouseClickedListeners().forEach(listener -> listener.handle(event));
        return false;
    }

    default boolean onMouseReleased(GuiScreenEvent.MouseReleasedEvent event) {
        this.getOnMouseReleasedListeners().forEach(listener -> listener.handle(event));
        return false;
    }

    default boolean onMouseDragged(GuiScreenEvent.MouseDragEvent event) {
        this.getOnMouseDraggedListeners().forEach(listener -> listener.handle(event));
        return false;
    }

    default boolean onMouseScrolled(GuiScreenEvent.MouseScrollEvent event) {
        this.getOnMouseScrolledListeners().forEach(listener -> listener.handle(event));
        return false;
    }

    default boolean onKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent event) {
        this.getOnKeyPressedListeners().forEach(listener -> listener.handle(event));
        return false;
    }

    default boolean onKeyReleased(GuiScreenEvent.KeyboardKeyReleasedEvent event) {
        this.getOnKeyReleasedListeners().forEach(listener -> listener.handle(event));
        return false;
    }

    default boolean onCharTyped(GuiScreenEvent.KeyboardCharTypedEvent event) {
        this.getOnCharTypedListeners().forEach(listener -> listener.handle(event));
        return false;
    }

}
