package com.github.franckyi.guapi.event;

public class ActionEvent extends ScreenEvent {
    private final MouseButtonEvent clickEvent, releaseEvent;

    public ActionEvent(MouseButtonEvent clickEvent, MouseButtonEvent releaseEvent) {
        this.clickEvent = clickEvent;
        this.releaseEvent = releaseEvent;
    }

    public MouseButtonEvent getClickEvent() {
        return clickEvent;
    }

    public MouseButtonEvent getReleaseEvent() {
        return releaseEvent;
    }
}
