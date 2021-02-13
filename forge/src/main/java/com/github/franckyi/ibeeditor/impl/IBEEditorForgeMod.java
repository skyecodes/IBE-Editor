package com.github.franckyi.ibeeditor.impl;

import com.github.franckyi.gamehooks.impl.ForgeClientHooks;
import com.github.franckyi.gamehooks.impl.ForgeCommonHooks;
import com.github.franckyi.gamehooks.impl.ForgeServerHooks;
import com.github.franckyi.gamehooks.impl.client.ForgeScreen;
import com.github.franckyi.guapi.impl.ForgeScreenHandler;
import com.github.franckyi.guapi.impl.theme.vanilla.*;
import com.github.franckyi.guapi.util.NodeType;
import com.github.franckyi.ibeeditor.impl.IBEEditor;
import com.github.franckyi.ibeeditor.impl.client.IBEEditorClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
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
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.BUTTON, ForgeVanillaButtonRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.TEXTFIELD, ForgeVanillaTextFieldRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.CHECKBOX, ForgeVanillaCheckBoxRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.LISTVIEW, ForgeVanillaListViewRenderer::new);
        MinecraftForge.EVENT_BUS.addListener(this::onClientTick);
        MinecraftForge.EVENT_BUS.addListener(this::onKeyPressed);
    }

    private void onServerInit(FMLDedicatedServerSetupEvent event) {
        IBEEditor.initServer(ForgeServerHooks.INSTANCE);
    }

    private void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.END && Minecraft.getInstance().currentScreen == null) {
            IBEEditorClient.tick();
        }
    }

    private void onKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent.Pre e) {
        if (e.getGui() instanceof ContainerScreen) {
            IBEEditorClient.handleScreenEvent(new ForgeScreen(e.getGui()), e.getKeyCode());
        }
    }
}
