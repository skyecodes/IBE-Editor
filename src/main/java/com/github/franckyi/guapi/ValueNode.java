package com.github.franckyi.guapi;

import net.minecraft.client.Minecraft;

import java.util.Set;
import java.util.function.BiConsumer;

public interface ValueNode<T> {

    Set<BiConsumer<T, T>> getOnValueChangedListeners();

    default void onValueChanged(T oldVal, T newVal) {
        Minecraft.getInstance().mouseHelper.ungrabMouse();
        this.getOnValueChangedListeners().forEach(listener -> listener.accept(oldVal, newVal));
    }

}
