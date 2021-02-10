package com.github.franckyi.gamehooks.api;

import com.github.franckyi.gamehooks.api.common.TextFactory;

public interface CommonHooks {
    <T> TextFactory<T> text();
}
