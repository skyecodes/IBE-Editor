package com.github.franckyi.gamehooks.api;

import com.github.franckyi.gamehooks.api.common.TextHooks;

public interface CommonHooks {
    <T> TextHooks<T> text();
}
