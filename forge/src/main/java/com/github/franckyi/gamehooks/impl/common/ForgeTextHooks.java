package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.TextHooks;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ForgeTextHooks implements TextHooks<ITextComponent> {
    public static final TextHooks<?> INSTANCE = new ForgeTextHooks();

    private ForgeTextHooks() {
    }

    @Override
    public ITextComponent getLiteralText(String text) {
        return new StringTextComponent(text);
    }

    @Override
    public ITextComponent getTranslatableText(String text) {
        return new TranslationTextComponent(text);
    }
}
