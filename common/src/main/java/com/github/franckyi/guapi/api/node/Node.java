package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.*;
import com.github.franckyi.guapi.api.EventTarget;
import com.github.franckyi.guapi.api.Renderable;
import com.github.franckyi.guapi.api.event.MouseEvent;
import com.github.franckyi.guapi.api.event.ScreenEventHandler;
import com.github.franckyi.guapi.util.Color;
import com.github.franckyi.guapi.util.Insets;
import com.github.franckyi.guapi.util.ScreenEventType;

public interface Node extends ScreenEventHandler, Renderable<Object>, EventTarget {
    int DEFAULT_BACKGROUND_COLOR = Color.rgba(0, 0, 0, 0);
    int INFINITE_SIZE = Integer.MAX_VALUE;
    int COMPUTED_SIZE = -1;
    int NONE = -1;

    int getX();

    ObservableIntegerValue xProperty();

    void setX(int value);

    int getY();

    ObservableIntegerValue yProperty();

    void setY(int value);

    int getWidth();

    ObservableIntegerValue widthProperty();

    void setWidth(int value);

    int getHeight();

    ObservableIntegerValue heightProperty();

    void setHeight(int value);

    int getMinWidth();

    IntegerProperty minWidthProperty();

    void setMinWidth(int value);

    int getMinHeight();

    IntegerProperty minHeightProperty();

    void setMinHeight(int value);

    int getPrefWidth();

    IntegerProperty prefWidthProperty();

    void setPrefWidth(int value);

    int getPrefHeight();

    IntegerProperty prefHeightProperty();

    void setPrefHeight(int value);

    int getMaxWidth();

    IntegerProperty maxWidthProperty();

    void setMaxWidth(int value);

    int getMaxHeight();

    IntegerProperty maxHeightProperty();

    void setMaxHeight(int value);

    int getParentPrefWidth();

    IntegerProperty parentPrefWidthProperty();

    void setParentPrefWidth(int value);

    int getParentPrefHeight();

    IntegerProperty parentPrefHeightProperty();

    void setParentPrefHeight(int value);

    int getComputedWidth();

    ObservableIntegerValue computedWidthProperty();

    int getComputedHeight();

    ObservableIntegerValue computedHeightProperty();

    int getBackgroundColor();

    IntegerProperty backgroundColorProperty();

    void setBackgroundColor(int value);

    Insets getPadding();

    ObjectProperty<Insets> paddingProperty();

    void setPadding(Insets value);

    Parent getParent();

    ObservableObjectValue<Parent> parentProperty();

    void setParent(Parent value);

    Scene getScene();

    ObservableObjectValue<Scene> sceneProperty();

    boolean isDisable();

    BooleanProperty disableProperty();

    void setDisable(boolean value);

    boolean isDisabled();

    ObservableBooleanValue disabledProperty();

    boolean isRoot();

    ObservableBooleanValue rootProperty();

    boolean isFocused();

    ObservableBooleanValue focusedProperty();

    boolean isHovered();

    ObservableBooleanValue hoveredProperty();

    <E extends MouseEvent> void handleMouseEvent(ScreenEventType<E> type, E event);

    default int getLeft() {
        return getX();
    }

    default int getTop() {
        return getY();
    }

    default int getRight() {
        return getX() + getWidth();
    }

    default int getBottom() {
        return getY() + getHeight();
    }

    default boolean inBounds(double x, double y) {
        return x >= getLeft() && x <= getRight() && y >= getTop() && y <= getBottom();
    }

    boolean checkRender();

    void shouldComputeSize();
}
