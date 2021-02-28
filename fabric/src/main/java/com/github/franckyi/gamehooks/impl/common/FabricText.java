package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Text;
import net.minecraft.text.LiteralText;

public class FabricText implements Text {
    public static final Text EMPTY = new FabricText(LiteralText.EMPTY);
    private final net.minecraft.text.Text text;

    public FabricText(net.minecraft.text.Text text) {
        this.text = text;
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.text.Text get() {
        return text;
    }
}
