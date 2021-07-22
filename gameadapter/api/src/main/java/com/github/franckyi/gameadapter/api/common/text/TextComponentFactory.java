package com.github.franckyi.gameadapter.api.common.text;

public interface TextComponentFactory<T> {
    T createComponentFromText(Text text);

    Text createTextFromComponent(T component);

    String getRawTextFromComponent(T component);
}
