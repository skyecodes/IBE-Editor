package com.github.franckyi.gamehooks.api;

import com.github.franckyi.gamehooks.api.client.KeyBindings;
import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.gamehooks.api.client.Sound;

public interface ClientHooks {
    <M> Renderer<M> renderer();

    KeyBindings keyBindings();

    Sound sound();

    void unlockCursor();
}
