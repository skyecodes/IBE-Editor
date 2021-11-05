package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.RenderHelper;
import com.github.franckyi.guapi.api.node.EnumButton;
import com.github.franckyi.guapi.base.theme.vanilla.delegate.VanillaButtonSkinDelegate;
import com.github.franckyi.guapi.base.theme.vanilla.delegate.VanillaEnumButtonSkinDelegate;
import net.minecraft.network.chat.Component;

import java.util.function.Function;

public class VanillaEnumButtonSkin extends VanillaButtonSkin<EnumButton<?>> {
    public VanillaEnumButtonSkin(EnumButton<?> node) {
        this(node, new VanillaEnumButtonSkinDelegate(node));
    }

    protected VanillaEnumButtonSkin(EnumButton<?> node, VanillaButtonSkinDelegate<EnumButton<?>> delegate) {
        super(node, delegate);
    }

    @Override
    @SuppressWarnings("unchecked")
    public int computeWidth(EnumButton<?> node) {
        return node.getValues().stream()
                .mapToInt(e -> RenderHelper.getFontWidth(((Function<Object, Component>) node.getTextFactory()).apply(e)))
                .max().orElse(0) + 20;
    }
}
