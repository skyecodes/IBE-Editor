package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Text;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ForgeText implements Text {
    public static final Text EMPTY = new ForgeText(StringTextComponent.EMPTY);
    private final ITextComponent text;

    public ForgeText(ITextComponent text) {
        this.text = text;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ITextComponent getText() {
        return text;
    }
}
