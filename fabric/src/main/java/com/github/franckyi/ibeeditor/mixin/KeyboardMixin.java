package com.github.franckyi.ibeeditor.mixin;

import com.github.franckyi.ibeeditor.impl.client.ClientEventHandler;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(at = @At("TAIL"), method = "onKey(JIIII)V")
    private void onKey(long window, int key, int scancode, int i, int j, CallbackInfo ci) {
        if (MinecraftClient.getInstance().currentScreen == null) {
            ClientEventHandler.onKeyInput();
        }
    }
}
