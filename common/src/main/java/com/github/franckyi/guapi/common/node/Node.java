package com.github.franckyi.guapi.common.node;

import com.github.franckyi.guapi.common.GUAPI;
import com.github.franckyi.guapi.common.data.*;
import com.github.franckyi.guapi.common.data.event.PropertyChangeEvent;
import com.github.franckyi.guapi.common.event.*;
import com.github.franckyi.guapi.common.hooks.RenderContext;
import com.github.franckyi.guapi.common.theme.Skin;
import com.github.franckyi.guapi.common.theme.Theme;
import com.github.franckyi.guapi.common.util.Insets;

import java.util.function.Function;

public abstract class Node implements ScreenEventHandler {
    public static final Integer INFINITE_SIZE = Integer.MAX_VALUE;
    public static final Integer COMPUTED_SIZE = -1;

    protected final IntegerProperty xProperty = new SimpleIntegerProperty();
    protected final IntegerProperty yProperty = new SimpleIntegerProperty();
    protected final IntegerProperty widthProperty = new SimpleIntegerProperty();
    protected final IntegerProperty heightProperty = new SimpleIntegerProperty();
    protected final IntegerProperty computedWidthProperty = new SimpleIntegerProperty();
    protected final IntegerProperty computedHeightProperty = new SimpleIntegerProperty();
    protected final ObjectProperty<Group> parentProperty = new SimpleObjectProperty<>();
    protected final ObjectProperty<Scene> sceneProperty = new SimpleObjectProperty<>();
    protected final ScreenEventHandler eventHandlerDelegate = new ScreenEventHandlerDelegate();
    private final ObservableIntegerValue xPropertyReadOnly = new ReadOnlyIntegerProperty(xProperty);
    private final ObservableIntegerValue yPropertyReadOnly = new ReadOnlyIntegerProperty(yProperty);
    private final ObservableIntegerValue widthPropertyReadOnly = new ReadOnlyIntegerProperty(widthProperty);
    private final ObservableIntegerValue heightPropertyReadOnly = new ReadOnlyIntegerProperty(heightProperty);
    private final IntegerProperty minWidthProperty = new SimpleIntegerProperty();
    private final IntegerProperty minHeightProperty = new SimpleIntegerProperty();
    private final IntegerProperty prefWidthProperty = new SimpleIntegerProperty(COMPUTED_SIZE);
    private final IntegerProperty prefHeightProperty = new SimpleIntegerProperty(COMPUTED_SIZE);
    private final IntegerProperty maxWidthProperty = new SimpleIntegerProperty(INFINITE_SIZE);
    private final IntegerProperty maxHeightProperty = new SimpleIntegerProperty(INFINITE_SIZE);
    private final ObservableIntegerValue computedWidthPropertyReadOnly = new ReadOnlyIntegerProperty(computedWidthProperty);
    private final ObservableIntegerValue computedHeightPropertyReadOnly = new ReadOnlyIntegerProperty(computedHeightProperty);
    private final ObjectProperty<Insets> paddingProperty = new SimpleObjectProperty<>(Insets.NONE);
    private final ObservableValue<Group> parentPropertyReadOnly = new ReadOnlyObjectProperty<>(parentProperty);
    private final ObservableValue<Scene> scenePropertyReadOnly = new ReadOnlyObjectProperty<>(sceneProperty);
    private final ObservableBooleanValue rootProperty = sceneProperty().mapNotNull();
    private final ObservableBooleanValue focusedProperty = sceneProperty()
            .safeBindMap(Scene::focusedProperty, null)
            .mapToBoolean(node -> node == Node.this);
    private final ObservableBooleanValue hoveredProperty = sceneProperty()
            .safeBindMap(Scene::hoveredProperty, null)
            .mapToBoolean(node -> node == Node.this);

    public Node() {
        minWidthProperty().addListener(this::_updateWidth);
        minHeightProperty().addListener(this::_updateHeight);
        prefWidthProperty().addListener(this::_updateWidth);
        prefHeightProperty().addListener(this::_updateHeight);
        maxWidthProperty().addListener(this::_updateWidth);
        maxHeightProperty().addListener(this::_updateHeight);
        computedWidthProperty().addListener(this::_updateWidth);
        computedHeightProperty().addListener(this::_updateHeight);
        widthProperty().addListener(this::_updateParentWidth);
        heightProperty().addListener(this::_updateParentHeight);
        paddingProperty().addListener(this::_updateSize);
        sceneProperty.bind(parentProperty().safeBindMap(Node::sceneProperty, null));
    }

    public int getX() {
        return xProperty().getValue();
    }

    public ObservableIntegerValue xProperty() {
        return xPropertyReadOnly;
    }

    protected void setX(int value) {
        xProperty.setValue(value);
    }

    public int getY() {
        return yProperty().getValue();
    }

    public ObservableIntegerValue yProperty() {
        return yPropertyReadOnly;
    }

    protected void setY(int value) {
        yProperty.setValue(value);
    }

    public int getWidth() {
        return widthProperty().getValue();
    }

    public ObservableIntegerValue widthProperty() {
        return widthPropertyReadOnly;
    }

    protected void setWidth(int value) {
        widthProperty.setValue(value);
    }

    public int getHeight() {
        return heightProperty().getValue();
    }

    public ObservableIntegerValue heightProperty() {
        return heightPropertyReadOnly;
    }

    protected void setHeight(int value) {
        heightProperty.setValue(value);
    }

    public int getMinWidth() {
        return minWidthProperty().getValue();
    }

    public void setMinWidth(int value) {
        minWidthProperty().setValue(value);
    }

    public IntegerProperty minWidthProperty() {
        return minWidthProperty;
    }

    public int getMinHeight() {
        return minHeightProperty().getValue();
    }

    public void setMinHeight(int value) {
        minHeightProperty().setValue(value);
    }

    public IntegerProperty minHeightProperty() {
        return minHeightProperty;
    }

    public int getPrefWidth() {
        return prefWidthProperty().getValue();
    }

    public void setPrefWidth(int value) {
        prefWidthProperty().setValue(value);
    }

    public IntegerProperty prefWidthProperty() {
        return prefWidthProperty;
    }

    public int getPrefHeight() {
        return prefHeightProperty().getValue();
    }

    public void setPrefHeight(int value) {
        prefHeightProperty().setValue(value);
    }

    public IntegerProperty prefHeightProperty() {
        return prefHeightProperty;
    }

    public int getMaxWidth() {
        return maxWidthProperty().getValue();
    }

    public void setMaxWidth(int value) {
        maxWidthProperty().setValue(value);
    }

    public IntegerProperty maxWidthProperty() {
        return maxWidthProperty;
    }

    public int getMaxHeight() {
        return maxHeightProperty().getValue();
    }

    public void setMaxHeight(int value) {
        maxHeightProperty().setValue(value);
    }

    public IntegerProperty maxHeightProperty() {
        return maxHeightProperty;
    }

    public int getComputedWidth() {
        return computedWidthProperty().getValue();
    }

    public ObservableIntegerValue computedWidthProperty() {
        return computedWidthPropertyReadOnly;
    }

    protected void setComputedWidth(int value) {
        computedWidthProperty.setValue(value);
    }

    public int getComputedHeight() {
        return computedHeightProperty().getValue();
    }

    public ObservableIntegerValue computedHeightProperty() {
        return computedHeightPropertyReadOnly;
    }

    protected void setComputedHeight(int value) {
        computedHeightProperty.setValue(value);
    }

    public Insets getPadding() {
        return paddingProperty().getValue();
    }

    public void setPadding(Insets value) {
        paddingProperty().setValue(value);
    }

    public ObjectProperty<Insets> paddingProperty() {
        return paddingProperty;
    }

    public Group getParent() {
        return parentProperty().getValue();
    }

    protected void setParent(Group value) {
        parentProperty.setValue(value);
    }

    public ObservableValue<Group> parentProperty() {
        return parentPropertyReadOnly;
    }

    public Scene getScene() {
        return sceneProperty().getValue();
    }

    void setScene(Scene value) {
        sceneProperty.setValue(value);
    }

    public ObservableValue<Scene> sceneProperty() {
        return scenePropertyReadOnly;
    }

    public boolean isRoot() {
        return rootProperty().getValue();
    }

    public ObservableBooleanValue rootProperty() {
        return rootProperty;
    }

    public boolean isFocused() {
        return focusedProperty().getValue();
    }

    public ObservableBooleanValue focusedProperty() {
        return focusedProperty;
    }

    public boolean isHovered() {
        return hoveredProperty().getValue();
    }

    public ObservableBooleanValue hoveredProperty() {
        return hoveredProperty;
    }

    public boolean inBounds(double x, double y) {
        return x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight();
    }

    public void mouseClicked(MouseButtonEvent event) {
    }

    public void mouseReleased(MouseButtonEvent event) {
    }

    public void mouseDragged(MouseDragEvent event) {
    }

    public void mouseScrolled(MouseScrollEvent event) {
    }

    public void keyPressed(KeyEvent event) {
    }

    public void keyReleased(KeyEvent event) {
    }

    public void charTyped(TypeEvent event) {
    }

    public void mouseMoved(MouseEvent event) {
    }

    @Override
    public <E extends ScreenEvent> void handleEvent(ScreenEventType<E> type, E event) {
        type.ifMouseEvent(event, this::handleMouseEvent);
    }

    protected abstract <E extends MouseEvent> void handleMouseEvent(ScreenEventType<E> type, E event);

    @Override
    public <E extends ScreenEvent> void addListener(ScreenEventType<E> type, ScreenEvent.Listener<E> listener) {
        eventHandlerDelegate.addListener(type, listener);
    }

    @Override
    public <E extends ScreenEvent> void removeListener(ScreenEventType<E> type, ScreenEvent.Listener<E> listener) {
        eventHandlerDelegate.removeListener(type, listener);
    }

    protected abstract <T extends Node> Function<Theme, Skin<T>> getSkinFactory();

    public void render(RenderContext<?> ctx) {
        getSkinFactory().apply(GUAPI.getTheme()).render(this, ctx);
    }

    protected void computeWidth() {
        setComputedWidth(getSkinFactory().apply(GUAPI.getTheme()).computeWidth(this) + getPadding().getHorizontal());
    }

    protected void computeHeight() {
        setComputedHeight(getSkinFactory().apply(GUAPI.getTheme()).computeHeight(this) + getPadding().getVertical());
    }

    protected void computeSize() {
        computeWidth();
        computeHeight();
    }

    protected void updateWidth() {
        int width = getPrefWidth();
        if (width == COMPUTED_SIZE) {
            width = getComputedWidth();
        }
        width = Math.max(Math.min(width, getMaxWidth()), getMinWidth());
        if (getParent() != null) {
            width = Math.min(width, getParent().getWidth());
        }
        setWidth(width);
    }

    protected void updateHeight() {
        int height = getPrefHeight();
        if (height == COMPUTED_SIZE) {
            height = getComputedHeight();
        }
        height = Math.max(Math.min(height, getMaxHeight()), getMinHeight());
        if (getParent() != null) {
            height = Math.min(height, getParent().getHeight());
        }
        setHeight(height);
    }

    protected void updateSize() {
        updateWidth();
        updateHeight();
    }

    private void _updateWidth(PropertyChangeEvent<?> event) {
        updateWidth();
    }

    private void _updateHeight(PropertyChangeEvent<?> event) {
        updateHeight();
    }

    private void _updateSize(PropertyChangeEvent<?> event) {
        updateSize();
    }

    private void _updateParentWidth(PropertyChangeEvent<?> event) {
        if (getParent() != null) {
            getParent().computeWidth();
            getParent().updateChildrenPos();
        }
    }

    private void _updateParentHeight(PropertyChangeEvent<?> event) {
        if (getParent() != null) {
            getParent().computeHeight();
            getParent().updateChildrenPos();
        }
    }
}
