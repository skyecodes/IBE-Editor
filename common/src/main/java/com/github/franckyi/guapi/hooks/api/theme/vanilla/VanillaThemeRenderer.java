package com.github.franckyi.guapi.hooks.api.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.theme.NodeRenderer;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.TextField;

public interface VanillaThemeRenderer {
    <M> NodeRenderer<M> button(Button node);
    <M> NodeRenderer<M> textField(TextField node);
}
