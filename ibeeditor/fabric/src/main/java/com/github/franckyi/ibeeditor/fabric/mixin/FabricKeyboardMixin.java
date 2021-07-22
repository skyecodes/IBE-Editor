package com.github.franckyi.ibeeditor.fabric.mixin;

import com.github.franckyi.ibeeditor.base.client.ClientEventHandler;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class FabricKeyboardMixin {
    @Inject(at = @At("TAIL"), method = "onKey(JIIII)V")
    private void onKey(long window, int key, int scancode, int i, int j, CallbackInfo ci) {
        if (MinecraftClient.getInstance().currentScreen == null) {
            ClientEventHandler.onKeyInput();
        }
    }
}
