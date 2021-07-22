package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.*;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.EventTarget;
import com.github.franckyi.guapi.api.Renderable;
import com.github.franckyi.guapi.api.event.MouseEvent;
import com.github.franckyi.guapi.api.util.Color;
import com.github.franckyi.guapi.api.util.Insets;
import com.github.franckyi.guapi.api.util.ScreenEventType;

public interface Node extends ScreenEventHandler, Renderable, EventTarget {
    int DEFAULT_BACKGROUND_COLOR = Color.rgba(0, 0, 0, 0);
    int INFINITE_SIZE = Integer.MAX_VALUE;
    int COMPUTED_SIZE = -1;
    int NONE = -1;

    default int getX() {
        return xProperty().getValue();
    }

    IntegerProperty xProperty();

    default void setX(int value) {
        xProperty().setValue(value);
    }

    default int getY() {
        return yProperty().getValue();
    }

    IntegerProperty yProperty();

    default void setY(int value) {
        yProperty().setValue(value);
    }

    default int getWidth() {
        return widthProperty().getValue();
    }

    IntegerProperty widthProperty();

    default void setWidth(int value) {
        widthProperty().setValue(value);
    }

    default int getHeight() {
        return heightProperty().getValue();
    }

    IntegerProperty heightProperty();

    default void setHeight(int value) {
        heightProperty().setValue(value);
    }

    default int getMinWidth() {
        return minWidthProperty().getValue();
    }

    IntegerProperty minWidthProperty();

    default void setMinWidth(int value) {
        minWidthProperty().setValue(value);
    }

    default int getMinHeight() {
        return minHeightProperty().getValue();
    }

    IntegerProperty minHeightProperty();

    default void setMinHeight(int value) {
        minHeightProperty().setValue(value);
    }

    default int getPrefWidth() {
        return prefWidthProperty().getValue();
    }

    IntegerProperty prefWidthProperty();

    default void setPrefWidth(int value) {
        prefWidthProperty().setValue(value);
    }

    default int getPrefHeight() {
        return prefHeightProperty().getValue();
    }

    IntegerProperty prefHeightProperty();

    default void setPrefHeight(int value) {
        prefHeightProperty().setValue(value);
    }

    default int getMaxWidth() {
        return maxWidthProperty().getValue();
    }

    IntegerProperty maxWidthProperty();

    default void setMaxWidth(int value) {
        maxWidthProperty().setValue(value);
    }

    default int getMaxHeight() {
        return maxHeightProperty().getValue();
    }

    IntegerProperty maxHeightProperty();

    default void setMaxHeight(int value) {
        maxHeightProperty().setValue(value);
    }

    default int getParentPrefWidth() {
        return parentPrefWidthProperty().getValue();
    }

    IntegerProperty parentPrefWidthProperty();

    default void setParentPrefWidth(int value) {
        parentPrefWidthProperty().setValue(value);
    }

    default int getParentPrefHeight() {
        return parentPrefHeightProperty().getValue();
    }

    IntegerProperty parentPrefHeightProperty();

    default void setParentPrefHeight(int value) {
        parentPrefHeightProperty().setValue(value);
    }

    default int getComputedWidth() {
        return computedWidthProperty().getValue();
    }

    ObservableIntegerValue computedWidthProperty();

    default int getComputedHeight() {
        return computedHeightProperty().getValue();
    }

    ObservableIntegerValue computedHeightProperty();

    default int getBackgroundColor() {
        return backgroundColorProperty().getValue();
    }

    IntegerProperty backgroundColorProperty();

    default void setBackgroundColor(int value) {
        backgroundColorProperty().setValue(value);
    }

    default Insets getPadding() {
        return paddingProperty().getValue();
    }

    ObjectProperty<Insets> paddingProperty();

    default void setPadding(Insets value) {
        paddingProperty().setValue(value);
    }

    default Text getTooltip() {
        return tooltipProperty().getValue();
    }

    ObjectProperty<Text> tooltipProperty();

    default void setTooltip(Text value) {
        tooltipProperty().setValue(value);
    }

    default Parent getParent() {
        return parentProperty().getValue();
    }

    ObjectProperty<Parent> parentProperty();

    default void setParent(Parent value) {
        parentProperty().setValue(value);
    }

    default Scene getScene() {
        return sceneProperty().getValue();
    }

    ObservableObjectValue<Scene> sceneProperty();

    default boolean isVisible() {
        return visibleProperty().getValue();
    }

    BooleanProperty visibleProperty();

    default void setVisible(boolean value) {
        visibleProperty().setValue(value);
    }

    default boolean isDisable() {
        return disableProperty().getValue();
    }

    BooleanProperty disableProperty();

    default void setDisable(boolean value) {
        disableProperty().setValue(value);
    }

    default boolean isDisabled() {
        return disabledProperty().getValue();
    }

    ObservableBooleanValue disabledProperty();

    default boolean isRoot() {
        return rootProperty().getValue();
    }

    ObservableBooleanValue rootProperty();

    default boolean isFocused() {
        return focusedProperty().getValue();
    }

    ObservableBooleanValue focusedProperty();

    default boolean isHovered() {
        return hoveredProperty().getValue();
    }

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

    default void askFocus() {
        getScene().askFocus(this);
    }
}
