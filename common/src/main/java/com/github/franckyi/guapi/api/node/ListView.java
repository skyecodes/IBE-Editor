package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;

public interface ListView<E> extends Node, Parent {
    ObservableList<E> getItems();

    int getItemHeight();

    IntegerProperty itemHeightProperty();

    void setItemHeight(int value);

    int getFullWidth();

    IntegerProperty fullWidthProperty();

    void setFullWidth(int value);

    int getFullHeight();

    IntegerProperty fullHeightProperty();

    void setFullHeight(int value);

    int getBaseX();

    IntegerProperty baseXProperty();

    void setBaseX(int value);

    int getBaseY();

    IntegerProperty baseYProperty();

    void setBaseY(int value);

    Renderer<E> getRenderer();

    ObjectProperty<Renderer<E>> rendererProperty();

    void setRenderer(Renderer<E> renderer);

    interface Renderer<E> {
        Node getView(E item);
    }
}
