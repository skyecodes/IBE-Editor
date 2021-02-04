package com.github.franckyi.gamehooks.api.client;

public interface KeyBindings {
    KeyBinding register(String name, int keyCode, String category);

    interface KeyBinding {
        boolean isPressed();
    }
}
