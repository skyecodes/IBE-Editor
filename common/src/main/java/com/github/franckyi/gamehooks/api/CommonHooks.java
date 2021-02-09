package com.github.franckyi.gamehooks.api;

import com.github.franckyi.gamehooks.api.common.text.TextFactory;

public interface CommonHooks {
    <T> TextFactory<T> text();
}
