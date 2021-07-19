package com.github.franckyi.guapi.api.event;

public interface MouseDragEvent extends MouseButtonEvent {
    double getDeltaX();

    double getDeltaY();
}
