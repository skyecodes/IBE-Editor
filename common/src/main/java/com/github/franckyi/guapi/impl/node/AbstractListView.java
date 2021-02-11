package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.ObservableListFactory;
import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.guapi.api.event.MouseEvent;
import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.Scene;
import com.github.franckyi.guapi.util.ScreenEventType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractListView<E> extends AbstractNode implements ListView<E> {
    private final ObservableList<E> items = ObservableListFactory.arrayList();
    private final IntegerProperty itemHeightProperty = PropertyFactory.ofInteger();
    private final IntegerProperty fullWidthProperty = PropertyFactory.ofInteger();
    private final IntegerProperty fullHeightProperty = PropertyFactory.ofInteger();
    private final IntegerProperty baseXProperty = PropertyFactory.ofInteger();
    private final IntegerProperty baseYProperty = PropertyFactory.ofInteger();
    private final ObjectProperty<ListView.Renderer<E>> rendererProperty = PropertyFactory.ofObject();

    protected AbstractListView(int itemHeight) {
        this(itemHeight, Collections.emptyList());
    }

    @SafeVarargs
    protected AbstractListView(int itemHeight, E... items) {
        this(itemHeight, Arrays.asList(items));
    }

    protected AbstractListView(int itemHeight, Collection<? extends E> items) {
        setItemHeight(itemHeight);
        getItems().addAll(items);
        itemHeightProperty().addListener(this::resetSkin);
        widthProperty().addListener(this::shouldComputeSize);
        heightProperty().addListener(this::shouldComputeSize);
        baseXProperty().bind(xProperty());
        baseYProperty().bind(yProperty());
        fullWidthProperty().bind(sceneProperty().bindMap(Scene::widthProperty, getWidth()));
        fullHeightProperty().bind(sceneProperty().bindMap(Scene::heightProperty, getHeight()));
    }

    @Override
    public <E2 extends MouseEvent> void handleMouseEvent(ScreenEventType<E2> type, E2 event) {
        if (inBounds(event.getMouseX(), event.getMouseY())) {
            notifyEvent(type, event);
            if (event.getTarget() == null) {
                event.setTarget(this);
            }
        }
    }

    @Override
    public ObservableList<E> getItems() {
        return items;
    }

    @Override
    public int getItemHeight() {
        return itemHeightProperty().getValue();
    }

    @Override
    public IntegerProperty itemHeightProperty() {
        return itemHeightProperty;
    }

    @Override
    public void setItemHeight(int value) {
        itemHeightProperty().setValue(value);
    }

    @Override
    public int getFullWidth() {
        return fullWidthProperty().getValue();
    }

    @Override
    public IntegerProperty fullWidthProperty() {
        return fullWidthProperty;
    }

    @Override
    public void setFullWidth(int value) {
        fullWidthProperty().setValue(value);
    }

    @Override
    public int getFullHeight() {
        return fullHeightProperty().getValue();
    }

    @Override
    public IntegerProperty fullHeightProperty() {
        return fullHeightProperty;
    }

    @Override
    public void setFullHeight(int value) {
        fullHeightProperty().setValue(value);
    }

    @Override
    public int getBaseX() {
        return baseXProperty().getValue();
    }

    @Override
    public IntegerProperty baseXProperty() {
        return baseXProperty;
    }

    @Override
    public void setBaseX(int value) {
        baseXProperty().setValue(value);
    }

    @Override
    public int getBaseY() {
        return baseYProperty().getValue();
    }

    @Override
    public IntegerProperty baseYProperty() {
        return baseYProperty;
    }

    @Override
    public void setBaseY(int value) {
        baseYProperty().setValue(value);
    }

    @Override
    public ListView.Renderer<E> getRenderer() {
        return rendererProperty().getValue();
    }

    @Override
    public ObjectProperty<ListView.Renderer<E>> rendererProperty() {
        return rendererProperty;
    }

    @Override
    public void setRenderer(ListView.Renderer<E> renderer) {
        rendererProperty().setValue(renderer);
    }

    @Override
    public void shouldUpdateChildren() {
    }
}
