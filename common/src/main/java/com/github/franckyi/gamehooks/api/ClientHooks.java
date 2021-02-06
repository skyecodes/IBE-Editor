package com.github.franckyi.gamehooks.api;

import com.github.franckyi.gamehooks.api.client.KeyBindingHooks;
import com.github.franckyi.gamehooks.api.client.RendererHooks;

public interface ClientHooks {
    RendererHooks renderer();

    KeyBindingHooks keyBindings();

    void unlockCursor();
}
