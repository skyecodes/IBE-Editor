package com.github.franckyi.ibeeditor.fabric.mixin;

import com.github.franckyi.ibeeditor.client.ClientContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class FabricMinecraftMixin {
    @Inject(at = @At("HEAD"), method = "clearLevel(Lnet/minecraft/client/gui/screens/Screen;)V")
    private void disconnect(Screen screen, CallbackInfo info) {
        ClientContext.setModInstalledOnServer(false);
    }
}
