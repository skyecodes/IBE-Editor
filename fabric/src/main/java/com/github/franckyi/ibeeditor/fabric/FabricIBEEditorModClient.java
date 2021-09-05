package com.github.franckyi.ibeeditor.fabric;

import com.github.franckyi.ibeeditor.base.client.ClientEventHandler;
import com.github.franckyi.ibeeditor.base.client.ClientInit;
import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FabricIBEEditorModClient implements ClientModInitializer {
    private static final Logger LOGGER = LogManager.getLogger();

    public FabricIBEEditorModClient() {
        ClientInit.init();
    }

    @Override
    public void onInitializeClient() {
        ClientInit.setup();
        ScreenEvents.AFTER_INIT.register(this::afterScreenInit);
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public void onResourceManagerReload(ResourceManager manager) {
                StopWatch watch = StopWatch.createStarted();
                // pre-loads the mod textures
                manager.listResources("textures/gui", s -> s.endsWith(".png"))
                        .stream().filter(id -> id.getNamespace().equals("ibeeditor"))
                        .forEach(id -> Minecraft.getInstance().getTextureManager().register(id, new SimpleTexture(id)));
                // renders an empty item editor
                ModScreenHandler.optimize(new PoseStack());
                watch.stop();
                LOGGER.info("Optimized IBE Editor (took {} s)", watch.getTime() / 1000.);
            }

            @Override
            public ResourceLocation getFabricId() {
                return new ResourceLocation("ibeeditor", "resource_manager_reload_listener");
            }
        });
    }

    private void afterScreenInit(Minecraft mc, Screen screen, int width, int height) {
        if (screen instanceof AbstractContainerScreen) {
            ScreenKeyboardEvents.beforeKeyPress(screen).register(this::handledScreenKeyPressed);
        }
    }

    private void handledScreenKeyPressed(Screen screen, int key, int scancode, int modifiers) {
        ClientEventHandler.onScreenEvent((AbstractContainerScreen<?>) screen, key);
    }
}
