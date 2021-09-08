package com.github.franckyi.ibeeditor;

import com.github.franckyi.gameadapter.api.client.IMatrices;
import com.github.franckyi.ibeeditor.base.client.ClientInit;
import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.base.client.ModTextures;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ForgeIBEEditorClientInit {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void setup() {
        ClientInit.setup();
        StopWatch watch = StopWatch.createStarted();
        Arrays.stream(ModTextures.class.getDeclaredFields()).map(field -> {
            try {
                return (ResourceLocation) field.get(null);
            } catch (IllegalAccessException e) {
                LOGGER.error(e);
                return null;
            }
        }).forEach(id -> Minecraft.getInstance().getTextureManager().register(id, new SimpleTexture(id)));
        ModScreenHandler.optimize((IMatrices) new MatrixStack());
        watch.stop();
        LOGGER.info("Optimized IBE Editor (took {} s)", watch.getTime() / 1000.);
    }
}
