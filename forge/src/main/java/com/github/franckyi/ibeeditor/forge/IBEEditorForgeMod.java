package com.github.franckyi.ibeeditor.forge;

import com.github.franckyi.ibeeditor.forge.client.IBEEditorForgeClient;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("ibeeditor")
public class IBEEditorForgeMod {
    public IBEEditorForgeMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(IBEEditorForgeClient::onClientInit);
    }
}
