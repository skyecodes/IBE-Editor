package com.github.franckyi.gameadapter.fabric.common;

import com.github.franckyi.gameadapter.TextHandler;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.text.TextComponentFactory;

public final class FabricTextComponentFactory implements TextComponentFactory<net.minecraft.text.Text> {
    public static final TextComponentFactory<net.minecraft.text.Text> INSTANCE = new FabricTextComponentFactory();

    private FabricTextComponentFactory() {
    }

    @Override
    public net.minecraft.text.Text createComponentFromText(Text text) {
        return net.minecraft.text.Text.Serializer.fromJson(TextHandler.getSerializer().toJson(text));
    }
}
