package com.github.franckyi.ibeeditor.forge;

import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.base.client.ModTextures;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ForgeIBEEditorClientInit {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void optimize() {
        StopWatch watch = StopWatch.createStarted();
        Arrays.stream(ModTextures.class.getDeclaredFields()).map(field -> {
            try {
                return (ResourceLocation) field.get(null);
            } catch (IllegalAccessException e) {
                LOGGER.error(e);
                return null;
            }
        }).forEach(id -> Minecraft.getInstance().getTextureManager().register(id, new SimpleTexture(id)));
        ModScreenHandler.optimize(new PoseStack());
        watch.stop();
        LOGGER.info("Optimized IBE Editor (took {} s)", watch.getTime() / 1000.);
    }
}
