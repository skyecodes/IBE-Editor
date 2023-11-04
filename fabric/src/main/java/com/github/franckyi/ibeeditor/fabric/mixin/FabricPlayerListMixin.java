package com.github.franckyi.ibeeditor.fabric.mixin;

import com.github.franckyi.ibeeditor.common.ServerEventHandler;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerList.class)
public class FabricPlayerListMixin {
    @Inject(at = @At("TAIL"), method = "placeNewPlayer")
    private void onPlayerConnect(Connection connection, ServerPlayer player, CommonListenerCookie commonListenerCookie, CallbackInfo ci) {
        ServerEventHandler.onPlayerJoin(player);
    }

    @Inject(at = @At("HEAD"), method = "remove")
    private void remove(ServerPlayer player, CallbackInfo ci) {
        ServerEventHandler.onPlayerLeave(player);
    }
}
