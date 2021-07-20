package com.github.franckyi.minecraft.impl.common.text;

import com.github.franckyi.minecraft.TextHandler;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.text.TextComponentFactory;
import net.minecraft.util.text.ITextComponent;

public final class ForgeTextComponentFactory implements TextComponentFactory<ITextComponent> {
    public static final TextComponentFactory<ITextComponent> INSTANCE = new ForgeTextComponentFactory();

    private ForgeTextComponentFactory() {
    }

    @Override
    public ITextComponent createComponentFromText(Text text) {
        return ITextComponent.Serializer.fromJson(TextHandler.getSerializer().toJson(text));
    }

    @Override
    public Text createTextFromComponent(ITextComponent component) {
        return TextHandler.getSerializer().fromJson(ITextComponent.Serializer.toJson(component), Text.class);
    }

    @Override
    public String getRawTextFromComponent(ITextComponent component) {
        return component.getString();
    }
}
