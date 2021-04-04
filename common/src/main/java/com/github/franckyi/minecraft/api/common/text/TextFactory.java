package com.github.franckyi.minecraft.api.common.text;

public interface TextFactory<T> {
    T createComponent(Text text);

    Text fromComponent(T component);
}
