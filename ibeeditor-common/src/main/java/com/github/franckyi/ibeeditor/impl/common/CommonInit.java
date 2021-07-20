package com.github.franckyi.ibeeditor.impl.common;

import com.github.franckyi.databindings.DataBindingsImpl;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.impl.NodeFactoryImpl;
import com.github.franckyi.guapi.impl.theme.vanilla.VanillaTheme;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.TextHandler;
import com.github.franckyi.minecraft.api.MinecraftCommon;
import com.github.franckyi.minecraft.impl.common.text.TextFactoryImpl;
import com.github.franckyi.minecraft.impl.common.text.TextSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CommonInit {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void init(MinecraftCommon common) {
        LOGGER.debug("Initializing IBE Editor - common");
        DataBindingsImpl.init();
        Minecraft.setCommon(common);
        Minecraft.setDefaultLogger(LogManager.getLogger("IBE Editor"));
        TextHandler.setTextFactory(TextFactoryImpl.INSTANCE);
        TextHandler.setSerializer(TextSerializer.GSON);
        GUAPI.registerTheme("vanilla", VanillaTheme.INSTANCE);
        GUAPI.setNodeFactory(NodeFactoryImpl.INSTANCE);
        Networking.init();
        CommonConfiguration.load();
    }
}
