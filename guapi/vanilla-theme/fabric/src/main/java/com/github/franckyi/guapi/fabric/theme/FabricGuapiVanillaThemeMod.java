package com.github.franckyi.guapi.fabric.theme;

import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.DelegatedRendererProvider;
import com.github.franckyi.guapi.api.util.NodeType;
import com.github.franckyi.guapi.base.theme.VanillaTheme;
import com.github.franckyi.guapi.fabric.theme.vanilla.*;
import net.fabricmc.api.ClientModInitializer;

public class FabricGuapiVanillaThemeMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        initSkin(NodeType.BUTTON, FabricVanillaButtonRenderer::new);
        initSkin(NodeType.TEXTURED_BUTTON, FabricVanillaTexturedButtonRenderer::new);
        initSkin(NodeType.ENUM_BUTTON, FabricVanillaButtonRenderer::new);
        initSkin(NodeType.TEXT_FIELD, FabricVanillaTextFieldRenderer::new);
        initSkin(NodeType.CHECK_BOX, FabricVanillaCheckBoxRenderer::new);
        initSkin(NodeType.LIST_VIEW, FabricVanillaListViewRenderer::new);
        initSkin(NodeType.TREE_VIEW, FabricVanillaTreeViewRenderer::new);
        initSkin(NodeType.TOGGLE_BUTTON, FabricVanillaButtonRenderer::new);
        initSkin(NodeType.TEXTURED_TOGGLE_BUTTON, FabricVanillaTexturedButtonRenderer::new);
        initSkin(NodeType.SLIDER, FabricVanillaSliderRenderer::new);
        initSkin(NodeType.TEXT_AREA, FabricVanillaTextAreaRenderer::new);
        Guapi.registerTheme("vanilla", VanillaTheme.INSTANCE);
    }

    private <N extends Node> void initSkin(NodeType<N> type, DelegatedRendererProvider<N> delegatedRendererProvider) {
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(type, delegatedRendererProvider);
    }
}
