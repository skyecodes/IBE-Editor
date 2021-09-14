package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;

import java.util.List;

public interface TextArea extends TextField {
    default boolean isWrapText() {
        return wrapTextProperty().getValue();
    }

    BooleanProperty wrapTextProperty();

    default void setWrapText(boolean value) {
        wrapTextProperty().setValue(value);
    }

    default int getScrollX() {
        return scrollXProperty().getValue();
    }

    IntegerProperty scrollXProperty();

    default void setScrollX(int value) {
        scrollXProperty().setValue(value);
    }

    default int getScrollY() {
        return scrollYProperty().getValue();
    }

    IntegerProperty scrollYProperty();

    default void setScrollY(int value) {
        scrollYProperty().setValue(value);
    }

    List<String> getLines();
}
