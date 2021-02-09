package com.github.franckyi.ibeeditor;

import com.github.franckyi.gamehooks.impl.ForgeClientHooks;
import com.github.franckyi.gamehooks.impl.ForgeCommonHooks;
import com.github.franckyi.gamehooks.impl.ForgeServerHooks;
import com.github.franckyi.guapi.hooks.impl.ForgeScreenHandler;
import com.github.franckyi.guapi.hooks.impl.theme.vanilla.ForgeVanillaButtonRenderer;
import com.github.franckyi.guapi.hooks.impl.theme.vanilla.ForgeVanillaCheckBoxRenderer;
import com.github.franckyi.guapi.hooks.impl.theme.vanilla.ForgeVanillaListViewRenderer;
import com.github.franckyi.guapi.hooks.impl.theme.vanilla.ForgeVanillaTextFieldRenderer;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.CheckBox;
import com.github.franckyi.guapi.node.ListView;
import com.github.franckyi.guapi.node.TextField;
import com.github.franckyi.guapi.theme.vanilla.VanillaTheme;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("ibeeditor")
public final class IBEEditorForgeMod {
    public IBEEditorForgeMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onServerInit);
    }

    private void onCommonInit(FMLCommonSetupEvent event) {
        IBEEditor.initCommon(ForgeCommonHooks.INSTANCE);
    }

    private void onClientInit(FMLClientSetupEvent event) {
        IBEEditor.initClient(ForgeClientHooks.INSTANCE, ForgeScreenHandler.INSTANCE);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(Button.class, ForgeVanillaButtonRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(TextField.class, ForgeVanillaTextFieldRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(CheckBox.class, ForgeVanillaCheckBoxRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(ListView.class, ForgeVanillaListViewRenderer::new);
        MinecraftForge.EVENT_BUS.addListener(this::onClientTick);
    }

    private void onServerInit(FMLDedicatedServerSetupEvent event) {
        IBEEditor.initServer(ForgeServerHooks.INSTANCE);
    }

    private void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.END) {
            IBEEditorClient.tick();
        }
    }
}
