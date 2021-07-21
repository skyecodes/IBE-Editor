package com.github.franckyi.ibeeditor.fabric.mixin;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.ibeeditor.impl.server.ServerEventHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class FabricPlayerManagerMixin {
    @Inject(at = @At("TAIL"), method = "onPlayerConnect(Lnet/minecraft/network/ClientConnection;Lnet/minecraft/server/network/ServerPlayerEntity;)V")
    private void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo info) {
        ServerEventHandler.onPlayerJoin(Game.getCommon().getPlayerFactory().createPlayer(player));
    }

    @Inject(at = @At("HEAD"), method = "remove(Lnet/minecraft/server/network/ServerPlayerEntity;)V")
    private void remove(ServerPlayerEntity player, CallbackInfo info) {
        ServerEventHandler.onPlayerLeave(Game.getCommon().getPlayerFactory().createPlayer(player));
    }
}
