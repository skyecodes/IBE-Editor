package com.github.franckyi.ibeeditor.mixin;

import com.github.franckyi.gamehooks.impl.common.FabricPlayer;
import com.github.franckyi.ibeeditor.impl.server.IBEEditorServer;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Inject(at = @At("TAIL"), method = "onPlayerConnect(Lnet/minecraft/network/ClientConnection;Lnet/minecraft/server/network/ServerPlayerEntity;)V")
    private void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo info) {
        IBEEditorServer.notifyClient(new FabricPlayer(player));
    }

    @Inject(at = @At("HEAD"), method = "remove(Lnet/minecraft/server/network/ServerPlayerEntity;)V")
    private void remove(ServerPlayerEntity player, CallbackInfo info) {
        IBEEditorServer.removeAllowedPlayer(new FabricPlayer(player));
    }
}
