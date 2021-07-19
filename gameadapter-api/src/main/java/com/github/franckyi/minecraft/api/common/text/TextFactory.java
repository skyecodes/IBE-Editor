package com.github.franckyi.minecraft.api.common.text;

public interface TextFactory<T> {
    T createComponentFromText(Text text);

    Text createTextFromComponent(T component);

    String getRawTextFromComponent(T component);
}
