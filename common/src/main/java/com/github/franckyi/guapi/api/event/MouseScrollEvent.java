package com.github.franckyi.guapi.api.event;

public interface MouseScrollEvent extends MouseEvent {
    double getDeltaX();
    double getDeltaY();
}
