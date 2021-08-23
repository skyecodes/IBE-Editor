package com.github.franckyi.gameadapter.forge.mixin.common.text;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.text.ITranslatedText;
import net.minecraft.util.text.TranslationTextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Arrays;
import java.util.List;

@Mixin(TranslationTextComponent.class)
public abstract class ForgeTranslationTextComponentMixin extends ForgeTextComponentMixin implements ITranslatedText {
    @Shadow
    @Final
    private String key;

    @Shadow
    @Final
    private Object[] args;

    private List<?> with;

    @Override
    public String getTranslate() {
        return key;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<IText> getWith() {
        if (with == null) {
            with = Arrays.asList(args);
        }
        return (List<IText>) with;
    }
}
