package com.github.franckyi.guapi.theme.vanilla.base;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.text.Text;
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
        return Math.max(90, Arrays.stream(node.getValues())
                .mapToInt(e -> Game.getClient().getRenderer().getFontWidth((Text) node.getTextFactory().apply(node.getValue())))
                .max().orElse(0) + 20);
    }
}
