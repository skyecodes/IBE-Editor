package com.github.franckyi.gameadapter.api.common.text;

public interface TextComponentFactory<T> {
    T createComponentFromText(Text text);
}
