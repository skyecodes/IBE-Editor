package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.EnumButton;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.Arrays;

@SuppressWarnings("rawtypes")
public class VanillaEnumButtonSkin extends AbstractVanillaButtonSkin<EnumButton> {
    public VanillaEnumButtonSkin(DelegatedRenderer delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    @SuppressWarnings("unchecked")
    public int computeWidth(EnumButton node) {
        return Math.max(90, Arrays.stream(node.getValues())
                .mapToInt(e -> Minecraft.getClient().getRenderer().getFontWidth((Text) node.getTextFactory().apply(node.getValue())))
                .max().orElse(0) + 20);
    }
}
