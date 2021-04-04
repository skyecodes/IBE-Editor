package com.github.franckyi.minecraft.impl.common.text;

import com.github.franckyi.minecraft.api.common.text.TextFactory;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.util.text.ITextComponent;

public final class ForgeTextFactory implements TextFactory<ITextComponent> {
    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(Text.class, new Text.Serializer()).create();
    public static final TextFactory<ITextComponent> INSTANCE = new ForgeTextFactory();

    private ForgeTextFactory() {
    }

    @Override
    public ITextComponent createComponent(Text text) {
        return ITextComponent.Serializer.getComponentFromJson(GSON.toJson(text));
    }

    @Override
    public Text fromComponent(ITextComponent component) {
        return GSON.fromJson(ITextComponent.Serializer.toJson(component), Text.class);
    }
}
