package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.guapi.api.GuapiExceptionHandler;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.node.Scene;
import com.github.franckyi.guapi.api.util.ScreenEventType;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;

public final class ModGuapiExceptionHandler implements GuapiExceptionHandler {
    public static final ModGuapiExceptionHandler INSTANCE = new ModGuapiExceptionHandler();

    private ModGuapiExceptionHandler() {
    }

    @Override
    public <E extends ScreenEvent> void handleEventException(Exception e, ScreenEventType<E> type, E event, Scene currentScene) {
        sendGenericErrorMessage();
    }

    @Override
    public void handleTickException(Exception e, Scene currentScene) {
        sendGenericErrorMessage();
    }

    @Override
    public void handleRenderException(Exception e, PoseStack matrices, int mouseX, int mouseY, float delta, Scene currentScene) {
        sendGenericErrorMessage();
    }

    private void sendGenericErrorMessage() {
        Minecraft.getInstance().player.displayClientMessage(ModTexts.Messages.ERROR_GENERIC, false);
    }
}
