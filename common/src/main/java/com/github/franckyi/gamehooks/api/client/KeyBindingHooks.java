package com.github.franckyi.gamehooks.api.client;

public interface KeyBindingHooks {
    KeyBinding register(String name, int keyCode, String category);

    interface KeyBinding {
        boolean isPressed();
    }
}
