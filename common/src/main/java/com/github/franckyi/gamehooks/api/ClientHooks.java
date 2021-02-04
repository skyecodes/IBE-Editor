package com.github.franckyi.gamehooks.api;

import com.github.franckyi.gamehooks.api.client.KeyBindings;
import com.github.franckyi.gamehooks.api.client.Renderer;

public interface ClientHooks {
    <MS> Renderer<MS> renderer();

    KeyBindings keyBindings();

    void unlockCursor();
}
