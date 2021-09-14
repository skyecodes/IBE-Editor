package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;

public interface ListNode<E> extends Node, Parent {
    default boolean isChildrenFocusable() {
        return childrenFocusableProperty().getValue();
    }

    BooleanProperty childrenFocusableProperty();

    default void setChildrenFocusable(boolean value) {
        childrenFocusableProperty().setValue(value);
    }

    default E getFocusedElement() {
        return focusedElementProperty().getValue();
    }

    ObjectProperty<E> focusedElementProperty();

    default void setFocusedElement(E value) {
        focusedElementProperty().setValue(value);
    }

    default E getScrollTo() {
        return scrollToProperty().getValue();
    }

    ObjectProperty<E> scrollToProperty();

    default void setScrollTo(E value) {
        scrollToProperty().setValue(value);
    }

    default int getItemHeight() {
        return itemHeightProperty().getValue();
    }

    IntegerProperty itemHeightProperty();

    default void setItemHeight(int value) {
        itemHeightProperty().setValue(value);
    }

    default int getFullWidth() {
        return fullWidthProperty().getValue();
    }

    IntegerProperty fullWidthProperty();

    default void setFullWidth(int value) {
        fullWidthProperty().setValue(value);
    }

    default int getFullHeight() {
        return fullHeightProperty().getValue();
    }

    IntegerProperty fullHeightProperty();

    default void setFullHeight(int value) {
        fullHeightProperty().setValue(value);
    }

    default int getBaseX() {
        return baseXProperty().getValue();
    }

    IntegerProperty baseXProperty();

    default void setBaseX(int value) {
        baseXProperty().setValue(value);
    }

    default int getBaseY() {
        return baseYProperty().getValue();
    }

    IntegerProperty baseYProperty();

    default void setBaseY(int value) {
        baseYProperty().setValue(value);
    }

    default Renderer<E> getRenderer() {
        return rendererProperty().getValue();
    }

    ObjectProperty<Renderer<E>> rendererProperty();

    default void setRenderer(Renderer<E> renderer) {
        rendererProperty().setValue(renderer);
    }

    interface Renderer<E> {
        Node getView(E item);
    }
}
