package com.github.franckyi.guapi.api.theme;

import com.github.franckyi.guapi.api.EventTarget;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.util.ScreenEventType;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;

public interface Skin<N extends Node> extends EventTarget {
    boolean preRender(N node, PoseStack matrices, int mouseX, int mouseY, float delta);

    void render(N node, PoseStack matrices, int mouseX, int mouseY, float delta);

    void postRender(N node, PoseStack matrices, int mouseX, int mouseY, float delta);

    int computeWidth(N node);

    int computeHeight(N node);

    <E extends ScreenEvent> void onEvent(ScreenEventType<E> type, E event);

    default Minecraft mc() {
        return Minecraft.getInstance();
    }

    default Font font() {
        return mc().font;
    }
}
