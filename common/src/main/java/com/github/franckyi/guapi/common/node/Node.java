package com.github.franckyi.guapi.common.node;

import com.github.franckyi.guapi.common.GUAPI;
import com.github.franckyi.guapi.common.data.ObjectProperty;
import com.github.franckyi.guapi.common.data.ObservableValue;
import com.github.franckyi.guapi.common.data.Property;
import com.github.franckyi.guapi.common.data.ReadOnlyProperty;
import com.github.franckyi.guapi.common.hooks.RenderContext;
import com.github.franckyi.guapi.common.skin.Skin;
import com.github.franckyi.guapi.common.skin.Theme;

import java.util.function.Function;

public abstract class Node {
    public static final Integer INFINITE = Integer.MAX_VALUE;
    public static final Integer COMPUTED = -1;

    protected final Property<Integer> xProperty = new ObjectProperty<>(0);
    private final ObservableValue<Integer> xPropertyReadOnly = new ReadOnlyProperty<>(xProperty);
    protected final Property<Integer> yProperty = new ObjectProperty<>(0);
    private final ObservableValue<Integer> yPropertyReadOnly = new ReadOnlyProperty<>(yProperty);
    protected final Property<Integer> widthProperty = new ObjectProperty<>(0);
    private final ObservableValue<Integer> widthPropertyReadOnly = new ReadOnlyProperty<>(widthProperty);
    protected final Property<Integer> heightProperty = new ObjectProperty<>(0);
    private final ObservableValue<Integer> heightPropertyReadOnly = new ReadOnlyProperty<>(heightProperty);

    private final Property<Integer> minWidthProperty = new ObjectProperty<>(0);
    private final Property<Integer> minHeightProperty = new ObjectProperty<>(0);
    private final Property<Integer> prefWidthProperty = new ObjectProperty<>(COMPUTED);
    private final Property<Integer> prefHeightProperty = new ObjectProperty<>(COMPUTED);
    private final Property<Integer> maxWidthProperty = new ObjectProperty<>(INFINITE);
    private final Property<Integer> maxHeightProperty = new ObjectProperty<>(INFINITE);
    protected final Property<Integer> computedWidthProperty = new ObjectProperty<>(0);
    private final ObservableValue<Integer> computedWidthPropertyReadOnly = new ReadOnlyProperty<>(computedWidthProperty);
    protected final Property<Integer> computedHeightProperty = new ObjectProperty<>(0);
    private final ObservableValue<Integer> computedHeightPropertyReadOnly = new ReadOnlyProperty<>(computedHeightProperty);

    protected final Property<Node> parentProperty = new ObjectProperty<>();
    private final ObservableValue<Node> parentPropertyReadOnly = new ReadOnlyProperty<>(parentProperty);

    public int getX() {
        return xProperty.getValue();
    }

    public ObservableValue<Integer> xProperty() {
        return xPropertyReadOnly;
    }

    protected void setX(int value) {
        xProperty.setValue(value);
    }

    public int getY() {
        return yProperty.getValue();
    }

    public ObservableValue<Integer> yProperty() {
        return yPropertyReadOnly;
    }

    protected void setY(int value) {
        yProperty.setValue(value);
    }

    public int getWidth() {
        return widthProperty.getValue();
    }

    public ObservableValue<Integer> widthProperty() {
        return widthPropertyReadOnly;
    }

    protected void setWidth(int value) {
        widthProperty.setValue(value);
    }

    public int getHeight() {
        return heightProperty.getValue();
    }

    public ObservableValue<Integer> heightProperty() {
        return heightPropertyReadOnly;
    }

    protected void setHeight(int value) {
        heightProperty.setValue(value);
    }

    public int getMinWidth() {
        return minWidthProperty.getValue();
    }

    public Property<Integer> minWidthProperty() {
        return minWidthProperty;
    }

    public void setMinWidth(int value) {
        minWidthProperty.setValue(value);
    }

    public int getMinHeight() {
        return minHeightProperty.getValue();
    }

    public Property<Integer> minHeightProperty() {
        return minHeightProperty;
    }

    public void setMinHeight(int value) {
        minHeightProperty.setValue(value);
    }

    public int getPrefWidth() {
        return prefWidthProperty.getValue();
    }

    public Property<Integer> prefWidthProperty() {
        return prefWidthProperty;
    }

    public void setPrefWidth(int value) {
        prefWidthProperty.setValue(value);
    }

    public int getPrefHeight() {
        return prefHeightProperty.getValue();
    }

    public Property<Integer> prefHeightProperty() {
        return prefHeightProperty;
    }

    public void setPrefHeight(int value) {
        prefHeightProperty.setValue(value);
    }

    public int getMaxWidth() {
        return maxWidthProperty.getValue();
    }

    public Property<Integer> maxWidthProperty() {
        return maxWidthProperty;
    }

    public void setMaxWidth(int value) {
        maxWidthProperty.setValue(value);
    }

    public int getMaxHeight() {
        return maxHeightProperty.getValue();
    }

    public Property<Integer> maxHeightProperty() {
        return maxHeightProperty;
    }

    public void setMaxHeight(int value) {
        maxHeightProperty.setValue(value);
    }

    public int getComputedWidth() {
        return computedWidthProperty.getValue();
    }

    public ObservableValue<Integer> computedWidthProperty() {
        return computedWidthPropertyReadOnly;
    }

    protected void setComputedWidth(int value) {
        computedWidthProperty.setValue(value);
    }

    public int getComputedHeight() {
        return computedHeightProperty.getValue();
    }

    public ObservableValue<Integer> computedHeightProperty() {
        return computedHeightPropertyReadOnly;
    }

    protected void setComputedHeight(int value) {
        computedHeightProperty.setValue(value);
    }

    public Node getParent() {
        return parentProperty.getValue();
    }

    public ObservableValue<Node> parentProperty() {
        return parentPropertyReadOnly;
    }

    protected void setParent(Node value) {
        parentProperty.setValue(value);
    }

    public abstract <T extends Node> Function<Theme, Skin<T>> getSkinFactory();

    public void render(RenderContext<?> ctx) {
        this.getSkinFactory().apply(GUAPI.getTheme()).render(this, ctx);
    }

    public void computeHeight() {
        this.setComputedHeight(this.getSkinFactory().apply(GUAPI.getTheme()).computeHeight(this));
    }

    public void computeWidth() {
        this.setComputedWidth(this.getSkinFactory().apply(GUAPI.getTheme()).computeWidth(this));
    }
}
