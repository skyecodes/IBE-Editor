package com.github.franckyi.ibeeditor.fabric.mixin;

import com.github.franckyi.ibeeditor.client.ClientContext;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public abstract class FabricClientPacketListenerMixin {
    @Inject(method = "handleLogin(Lnet/minecraft/network/protocol/game/ClientboundLoginPacket;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/protocol/game/ClientboundLoginPacket;playerId()I"))
    private void onLogin(CallbackInfo info) {
        ClientContext.setModInstalledOnServer(false);
    }
}
