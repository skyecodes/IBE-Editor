package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.factory.ObservableListFactory;
import com.github.franckyi.guapi.event.MouseEvent;
import com.github.franckyi.guapi.event.ScreenEventType;

import java.util.function.Function;

public class ListView<T> extends Node implements Parent {
    private final ObservableList<T> items = ObservableListFactory.arrayList();
    private Function<T, Node> renderer;

    public ListView() {
    }

    public ObservableList<T> getItems() {
        return items;
    }

    public Function<T, Node> getRenderer() {
        return renderer;
    }

    public void setRenderer(Function<T, Node> renderer) {
        this.renderer = renderer;
    }

    @Override
    protected <E extends MouseEvent> void handleMouseEvent(ScreenEventType<E> type, E event) {

    }

    @Override
    public void updateChildrenPos() {

    }
}
