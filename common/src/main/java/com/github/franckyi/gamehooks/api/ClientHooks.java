package com.github.franckyi.gamehooks.api;

import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.RendererHooks;

public interface ClientHooks {
    RendererHooks renderer();

    KeyBinding registerKeyBinding(String name, int keyCode, String category);

    void unlockCursor();
}
