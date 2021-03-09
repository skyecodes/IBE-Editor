package com.github.franckyi.minecraft.impl.common.text;

import com.github.franckyi.minecraft.api.common.text.Text;
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
    public ITextComponent get() {
        return text;
    }
}
