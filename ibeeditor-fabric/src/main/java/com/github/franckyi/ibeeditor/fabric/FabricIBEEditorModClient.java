package com.github.franckyi.ibeeditor.fabric;

import com.github.franckyi.gameadapter.api.client.IMatrices;
import com.github.franckyi.gameadapter.api.client.IScreen;
import com.github.franckyi.ibeeditor.base.client.ClientCache;
import com.github.franckyi.ibeeditor.base.client.ClientEventHandler;
import com.github.franckyi.ibeeditor.base.client.ClientInit;
import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.base.client.util.ScreenScalingManager;
import com.github.franckyi.ibeeditor.fabric.client.util.FabricScreenScalingManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FabricIBEEditorModClient implements ClientModInitializer {
    private static final Logger LOGGER = LogManager.getLogger();

    public FabricIBEEditorModClient() {
        ScreenScalingManager.init(FabricScreenScalingManager.INSTANCE);
        ClientInit.init();
    }

    @Override
    public void onInitializeClient() {
        ClientInit.setup();
        ClientCache.init();
        ScreenEvents.AFTER_INIT.register(this::afterScreenInit);
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public void reload(ResourceManager manager) {
                StopWatch watch = StopWatch.createStarted();
                // pre-loads the mod textures
                manager.findResources("textures/gui", s -> s.endsWith(".png"))
                        .stream().filter(id -> id.getNamespace().equals("ibeeditor"))
                        .forEach(id -> MinecraftClient.getInstance().getTextureManager().registerTexture(id, new ResourceTexture(id)));
                // renders an empty item editor
                ModScreenHandler.optimize((IMatrices) new MatrixStack());
                watch.stop();
                LOGGER.info("Optimized IBE Editor (took {} s)", watch.getTime() / 1000.);
            }

            @Override
            public Identifier getFabricId() {
                return new Identifier("ibeeditor", "resource_manager_reload_listener");
            }
        });
    }

    private void afterScreenInit(MinecraftClient mc, Screen screen, int width, int height) {
        if (screen instanceof HandledScreen) {
            ScreenKeyboardEvents.beforeKeyPress(screen).register(this::handledScreenKeyPressed);
        }
    }

    private void handledScreenKeyPressed(Screen screen, int key, int scancode, int modifiers) {
        ClientEventHandler.onScreenEvent((IScreen) screen, key);
    }
}