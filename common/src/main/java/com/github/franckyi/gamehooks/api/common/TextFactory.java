package com.github.franckyi.gamehooks.api.common;

import com.github.franckyi.gamehooks.util.common.text.Text;

public interface TextFactory<T> {
    T create(Text text);

    T create(Text root, Text... siblings);

    Text from(T text);
}
