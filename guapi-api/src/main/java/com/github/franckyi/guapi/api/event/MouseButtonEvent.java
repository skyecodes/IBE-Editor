package com.github.franckyi.guapi.api.event;

public interface MouseButtonEvent extends MouseEvent {
    int LEFT_BUTTON = 0;
    int RIGHT_BUTTON = 1;
    int MIDDLE_BUTTON = 2;

    int getButton();
}
