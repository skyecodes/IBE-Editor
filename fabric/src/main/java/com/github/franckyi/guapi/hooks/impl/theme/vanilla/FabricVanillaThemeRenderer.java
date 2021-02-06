package com.github.franckyi.guapi.hooks.impl.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.theme.NodeRenderer;
import com.github.franckyi.guapi.hooks.api.theme.vanilla.VanillaThemeRenderer;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.TextField;

public class FabricVanillaThemeRenderer implements VanillaThemeRenderer {
    public static final VanillaThemeRenderer INSTANCE = new FabricVanillaThemeRenderer();

    private FabricVanillaThemeRenderer() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public NodeRenderer<?> button(Button node) {
        return new FabricVanillaButtonRenderer(node);
    }

    @Override
    @SuppressWarnings("unchecked")
    public NodeRenderer<?> textField(TextField node) {
        return new FabricVanillaTextFieldRenderer(node);
    }
}
