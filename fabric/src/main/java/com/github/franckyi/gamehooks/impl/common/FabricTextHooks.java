package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.TextHooks;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class FabricTextHooks implements TextHooks<Text> {
    public static final TextHooks<?> INSTANCE = new FabricTextHooks();

    private FabricTextHooks() {
    }

    @Override
    public Text getLiteralText(String text) {
        return new LiteralText(text);
    }

    @Override
    public Text getTranslatableText(String text) {
        return new TranslatableText(text);
    }
}
