package com.github.franckyi.gameadapter.forge.common.text;

import com.github.franckyi.gameadapter.TextHandler;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.text.TextComponentFactory;
import net.minecraft.util.text.ITextComponent;

public final class ForgeTextComponentFactory implements TextComponentFactory<ITextComponent> {
    public static final TextComponentFactory<ITextComponent> INSTANCE = new ForgeTextComponentFactory();

    private ForgeTextComponentFactory() {
    }

    @Override
    public ITextComponent createComponentFromText(Text text) {
        return ITextComponent.Serializer.fromJson(TextHandler.getSerializer().toJson(text));
    }
}
