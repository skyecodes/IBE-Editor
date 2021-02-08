package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.factory.ObservableListFactory;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.guapi.event.MouseEvent;
import com.github.franckyi.guapi.event.ScreenEventType;

public class ListView<E> extends Node implements Parent {
    private final ObservableList<E> items = ObservableListFactory.arrayList();
    private final IntegerProperty itemHeightProperty = PropertyFactory.ofInteger();
    private final IntegerProperty fullWidthProperty = PropertyFactory.ofInteger();
    private final IntegerProperty fullHeightProperty = PropertyFactory.ofInteger();
    private final IntegerProperty baseXProperty = PropertyFactory.ofInteger();
    private final IntegerProperty baseYProperty = PropertyFactory.ofInteger();
    private final ObjectProperty<Renderer<E>> rendererProperty = PropertyFactory.ofObject();

    public ListView(int itemHeight) {
        setItemHeight(itemHeight);
        itemHeightProperty().addListener(this::resetSkin);
        widthProperty().addListener(this::shouldComputeSize);
        heightProperty().addListener(this::shouldComputeSize);
    }

    @Override
    protected <E extends MouseEvent> void handleMouseEvent(ScreenEventType<E> type, E event) {
        if (inBounds(event.getMouseX(), event.getMouseY())) {
            notifyEvent(type, event);
            if (event.getTarget() == null) {
                event.setTarget(this);
            }
        }
    }

    public ObservableList<E> getItems() {
        return items;
    }

    public int getItemHeight() {
        return itemHeightProperty().getValue();
    }

    public IntegerProperty itemHeightProperty() {
        return itemHeightProperty;
    }

    public void setItemHeight(int value) {
        itemHeightProperty().setValue(value);
    }

    public int getFullWidth() {
        return fullWidthProperty().getValue();
    }

    public IntegerProperty fullWidthProperty() {
        return fullWidthProperty;
    }

    public void setFullWidth(int value) {
        fullWidthProperty().setValue(value);
    }

    public int getFullHeight() {
        return fullHeightProperty().getValue();
    }

    public IntegerProperty fullHeightProperty() {
        return fullHeightProperty;
    }

    public void setFullHeight(int value) {
        fullHeightProperty().setValue(value);
    }

    public int getBaseX() {
        return baseXProperty().getValue();
    }

    public IntegerProperty baseXProperty() {
        return baseXProperty;
    }

    public void setBaseX(int value) {
        baseXProperty().setValue(value);
    }

    public int getBaseY() {
        return baseYProperty().getValue();
    }

    public IntegerProperty baseYProperty() {
        return baseYProperty;
    }

    public void setBaseY(int value) {
        baseYProperty().setValue(value);
    }

    public Renderer<E> getRenderer() {
        return rendererProperty().getValue();
    }

    public ObjectProperty<Renderer<E>> rendererProperty() {
        return rendererProperty;
    }

    public void setRenderer(Renderer<E> renderer) {
        rendererProperty().setValue(renderer);
    }

    public void forceParent(Node node) {
        node.setParent(this);
    }

    public void forceX(Node node, int value) {
        node.setX(value);
    }

    public void forceY(Node node, int value) {
        node.setY(value);
    }

    @Override
    public void shouldUpdateChildrenPos() {
    }

    public interface Renderer<E> {
        Node getView(E item);
    }
}
