package com.github.franckyi.gamehooks.api.common;

import com.github.franckyi.gamehooks.util.common.Text;

public interface TextFactory<T> {
    T create(Text text);
}
