package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.gameadapter.api.client.IRenderer;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.node.EnumButton;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;

import java.util.Arrays;

@SuppressWarnings("rawtypes")
public class VanillaEnumButtonSkin extends AbstractVanillaButtonSkin<EnumButton> {
    public VanillaEnumButtonSkin(DelegatedRenderer delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    @SuppressWarnings("unchecked")
    public int computeWidth(EnumButton node) {
        return Arrays.stream(node.getValues())
                .mapToInt(e -> IRenderer.get().getFontWidth((IText) node.getTextFactory().apply(e)))
                .max().orElse(0) + 20;
    }
}
