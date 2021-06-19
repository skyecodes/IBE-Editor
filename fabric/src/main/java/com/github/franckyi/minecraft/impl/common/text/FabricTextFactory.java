package com.github.franckyi.minecraft.impl.common.text;

import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.text.TextFactory;

public final class FabricTextFactory implements TextFactory<net.minecraft.text.Text> {
    public static final TextFactory<net.minecraft.text.Text> INSTANCE = new FabricTextFactory();

    private FabricTextFactory() {
    }

    @Override
    public net.minecraft.text.Text createComponentFromText(Text text) {
        return net.minecraft.text.Text.Serializer.fromJson(Text.Serializer.GSON.toJson(text));
    }

    @Override
    public Text createTextFromComponent(net.minecraft.text.Text component) {
        return Text.Serializer.GSON.fromJson(net.minecraft.text.Text.Serializer.toJson(component), Text.class);
    }

    @Override
    public String getRawTextFromComponent(net.minecraft.text.Text component) {
        return component.getString();
    }
}
