package com.github.franckyi.ibeeditor.mixin;

import com.github.franckyi.ibeeditor.impl.client.IBEEditorClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(at = @At("HEAD"), method = "disconnect(Lnet/minecraft/client/gui/screen/Screen;)V")
    private void disconnect(Screen screen, CallbackInfo info) {
        IBEEditorClient.setModInstalledOnServer(false);
    }
}
