package com.github.franckyi.ibeeditor.fabric.mixin;

import com.github.franckyi.ibeeditor.base.client.ClientEventHandler;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public abstract class FabricKeyboardHandlerMixin {
    @Inject(at = @At("TAIL"), method = "keyPress")
    private void onKey(long window, int key, int scancode, int i, int j, CallbackInfo ci) {
        if (Minecraft.getInstance().screen == null) {
            ClientEventHandler.onKeyInput();
        }
    }
}
