package com.github.franckyi.ibeeditor;

import com.github.franckyi.gamehooks.impl.FabricClientHooks;
import com.github.franckyi.gamehooks.impl.FabricCommonHooks;
import com.github.franckyi.gamehooks.impl.FabricServerHooks;
import com.github.franckyi.guapi.hooks.impl.FabricScreenHandler;
import com.github.franckyi.guapi.hooks.impl.theme.vanilla.FabricVanillaThemeRenderer;
import com.github.franckyi.guapi.theme.vanilla.VanillaTheme;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public final class IBEEditorFabricMod implements ModInitializer, ClientModInitializer, DedicatedServerModInitializer {
    @Override
    public void onInitialize() {
        IBEEditor.initCommon(FabricCommonHooks.INSTANCE);
    }

    @Override
    public void onInitializeClient() {
        IBEEditor.initClient(FabricClientHooks.INSTANCE, FabricScreenHandler.INSTANCE, VanillaTheme.create(FabricVanillaThemeRenderer.INSTANCE));
        ClientTickEvents.END_CLIENT_TICK.register(mc -> IBEEditorClient.tick());
    }

    @Override
    public void onInitializeServer() {
        IBEEditor.initServer(FabricServerHooks.INSTANCE);
    }
}
