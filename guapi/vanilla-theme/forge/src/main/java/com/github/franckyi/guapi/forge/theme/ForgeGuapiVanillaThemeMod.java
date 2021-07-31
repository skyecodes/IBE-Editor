package com.github.franckyi.guapi.forge.theme;

import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.DelegatedRendererProvider;
import com.github.franckyi.guapi.api.util.NodeType;
import com.github.franckyi.guapi.base.theme.VanillaTheme;
import com.github.franckyi.guapi.forge.theme.vanilla.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("guapi-vanilla-theme")
public class ForgeGuapiVanillaThemeMod {
    public ForgeGuapiVanillaThemeMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientInit);
    }

    private void onClientInit(FMLClientSetupEvent event) {
        initSkin(NodeType.BUTTON, ForgeVanillaButtonRenderer::new);
        initSkin(NodeType.TEXTURED_BUTTON, ForgeVanillaTexturedButtonRenderer::new);
        initSkin(NodeType.ENUM_BUTTON, ForgeVanillaButtonRenderer::new);
        initSkin(NodeType.TEXT_FIELD, ForgeVanillaTextFieldRenderer::new);
        initSkin(NodeType.CHECK_BOX, ForgeVanillaCheckBoxRenderer::new);
        initSkin(NodeType.LIST_VIEW, ForgeVanillaListViewRenderer::new);
        initSkin(NodeType.TREE_VIEW, ForgeVanillaTreeViewRenderer::new);
        initSkin(NodeType.TOGGLE_BUTTON, ForgeVanillaButtonRenderer::new);
        initSkin(NodeType.TEXTURED_TOGGLE_BUTTON, ForgeVanillaTexturedButtonRenderer::new);
        initSkin(NodeType.SLIDER, ForgeVanillaSliderRenderer::new);
        initSkin(NodeType.TEXT_AREA, ForgeVanillaTextAreaRenderer::new);
        Guapi.registerTheme("vanilla", VanillaTheme.INSTANCE);
    }

    private <N extends Node> void initSkin(NodeType<N> type, DelegatedRendererProvider<N> delegatedRendererProvider) {
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(type, delegatedRendererProvider);
    }
}
