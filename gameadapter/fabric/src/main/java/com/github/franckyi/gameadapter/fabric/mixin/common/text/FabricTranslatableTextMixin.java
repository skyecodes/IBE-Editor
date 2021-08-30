package com.github.franckyi.gameadapter.fabric.mixin.common.text;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.text.ITranslatedText;
import net.minecraft.text.ParsableText;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.*;

import java.util.Arrays;
import java.util.List;

@Mixin(TranslatableText.class)
@Implements(@Interface(iface = ITranslatedText.class, prefix = "proxy$"))
public abstract class FabricTranslatableTextMixin extends FabricBaseTextMixin implements ParsableText {
    @Shadow
    @Final
    private String key;

    @Shadow
    @Final
    private Object[] args;

    private List<?> with;

    public String proxy$getTranslate() {
        return key;
    }

    @SuppressWarnings("unchecked")
    public List<IText> proxy$getWith() {
        if (with == null) {
            with = Arrays.asList(args);
        }
        return (List<IText>) with;
    }
}
