package com.github.franckyi.minecraft.api.common.text.builder;

import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.text.TranslatedText;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface TranslatedTextBuilder extends TranslatedText, GenericTextBuilder<TranslatedTextBuilder> {
    default TranslatedTextBuilder translate(String translate) {
        return with(t -> t.setTranslate(translate));
    }

    default TranslatedTextBuilder with(Text... with) {
        return with(Lists.newArrayList(with));
    }

    default TranslatedTextBuilder with(Collection<? extends Text> with) {
        return with(new ArrayList<>(with));
    }

    default TranslatedTextBuilder with(List<Text> with) {
        return with(t -> t.setWith(with));
    }
}
