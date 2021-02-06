package com.github.franckyi.guapi.hooks.impl.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.theme.NodeRenderer;
import com.github.franckyi.guapi.hooks.api.theme.vanilla.VanillaThemeRenderer;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.TextField;

public class ForgeVanillaThemeRenderer implements VanillaThemeRenderer {
    public static final VanillaThemeRenderer INSTANCE = new ForgeVanillaThemeRenderer();

    private ForgeVanillaThemeRenderer() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public NodeRenderer<?> button(Button node) {
        return new ForgeVanillaButtonRenderer(node);
    }

    @Override
    @SuppressWarnings("unchecked")
    public NodeRenderer<?> textField(TextField node) {
        return new ForgeVanillaTextFieldRenderer(node);
    }
}
