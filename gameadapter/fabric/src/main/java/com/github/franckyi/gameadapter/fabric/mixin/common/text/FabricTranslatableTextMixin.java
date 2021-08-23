package com.github.franckyi.gameadapter.fabric.mixin.common.text;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.text.ITranslatedText;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Arrays;
import java.util.List;

@Mixin(TranslatableText.class)
public abstract class FabricTranslatableTextMixin extends FabricBaseTextMixin implements ITranslatedText {
    @Shadow @Final private String key;

    @Shadow @Final private Object[] args;

    @Override
    public String getTranslate() {
        return key;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<IText> getWith() {
        return (List) Arrays.asList(args);
    }
}
