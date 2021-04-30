package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.*;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.event.ScreenEventListener;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.Parent;
import com.github.franckyi.guapi.api.node.Scene;
import com.github.franckyi.guapi.api.node.ScreenEventHandler;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.impl.event.ScreenEventHandlerDelegate;
import com.github.franckyi.guapi.util.Insets;
import com.github.franckyi.guapi.util.NodeType;
import com.github.franckyi.guapi.util.ScreenEventType;
import com.github.franckyi.minecraft.api.client.render.Matrices;
import com.github.franckyi.minecraft.api.common.text.Text;

public abstract class AbstractNode implements Node {
    protected final IntegerProperty xProperty = DataBindings.getPropertyFactory().createIntegerProperty();
    protected final IntegerProperty yProperty = DataBindings.getPropertyFactory().createIntegerProperty();
    protected final IntegerProperty widthProperty = DataBindings.getPropertyFactory().createIntegerProperty();
    protected final IntegerProperty heightProperty = DataBindings.getPropertyFactory().createIntegerProperty();

    private final IntegerProperty minWidthProperty = DataBindings.getPropertyFactory().createIntegerProperty();
    private final IntegerProperty minHeightProperty = DataBindings.getPropertyFactory().createIntegerProperty();
    private final IntegerProperty prefWidthProperty = DataBindings.getPropertyFactory().createIntegerProperty(COMPUTED_SIZE);
    private final IntegerProperty prefHeightProperty = DataBindings.getPropertyFactory().createIntegerProperty(COMPUTED_SIZE);
    private final IntegerProperty maxWidthProperty = DataBindings.getPropertyFactory().createIntegerProperty(INFINITE_SIZE);
    private final IntegerProperty maxHeightProperty = DataBindings.getPropertyFactory().createIntegerProperty(INFINITE_SIZE);
    private final IntegerProperty parentPrefWidthProperty = DataBindings.getPropertyFactory().createIntegerProperty(NONE);
    private final IntegerProperty parentPrefHeightProperty = DataBindings.getPropertyFactory().createIntegerProperty(NONE);

    protected final IntegerProperty computedWidthProperty = DataBindings.getPropertyFactory().createIntegerProperty();
    private final ObservableIntegerValue computedWidthPropertyReadOnly = DataBindings.getPropertyFactory().createReadOnlyProperty(computedWidthProperty);
    protected final IntegerProperty computedHeightProperty = DataBindings.getPropertyFactory().createIntegerProperty();
    private final ObservableIntegerValue computedHeightPropertyReadOnly = DataBindings.getPropertyFactory().createReadOnlyProperty(computedHeightProperty);

    private final IntegerProperty backgroundColorProperty = DataBindings.getPropertyFactory().createIntegerProperty(DEFAULT_BACKGROUND_COLOR);
    private final ObjectProperty<Insets> paddingProperty = DataBindings.getPropertyFactory().createObjectProperty(Insets.NONE);
    private final ObjectProperty<Text> tooltipProperty = DataBindings.getPropertyFactory().createObjectProperty();

    protected final ObjectProperty<Parent> parentProperty = DataBindings.getPropertyFactory().createObjectProperty();
    protected final ObjectProperty<Scene> sceneProperty = DataBindings.getPropertyFactory().createObjectProperty();
    private final ObservableObjectValue<Scene> scenePropertyReadOnly = DataBindings.getPropertyFactory().createReadOnlyProperty(sceneProperty);

    private final BooleanProperty visibleProperty = DataBindings.getPropertyFactory().createBooleanProperty(true);
    private final BooleanProperty disableProperty = DataBindings.getPropertyFactory().createBooleanProperty();
    private final ObservableBooleanValue disabledProperty = disableProperty()
            .or(parentProperty().bindMapToBoolean(Parent::disabledProperty, false));

    private final ObservableBooleanValue rootProperty = sceneProperty()
            .bindMap(Scene::rootProperty, null)
            .mapToBoolean(node -> node == AbstractNode.this);
    private final ObservableBooleanValue focusedProperty = sceneProperty()
            .bindMap(Scene::focusedProperty, null)
            .mapToBoolean(node -> node == AbstractNode.this);
    private final ObservableBooleanValue hoveredProperty = sceneProperty()
            .bindMap(Scene::hoveredProperty, null)
            .mapToBoolean(node -> node == AbstractNode.this);

    protected Skin<? super Node> skin;
    protected final ScreenEventHandler eventHandlerDelegate = new ScreenEventHandlerDelegate();
    protected boolean shouldComputeSize = true;
    protected boolean shouldUpdateSize = true;

    protected AbstractNode() {
        minWidthProperty().addListener(this::shouldUpdateSize);
        minHeightProperty().addListener(this::shouldUpdateSize);
        prefWidthProperty().addListener(this::shouldUpdateSize);
        prefHeightProperty().addListener(this::shouldUpdateSize);
        maxWidthProperty().addListener(this::shouldUpdateSize);
        maxHeightProperty().addListener(this::shouldUpdateSize);
        parentPrefWidthProperty().addListener(this::shouldUpdateSize);
        parentPrefHeightProperty().addListener(this::shouldUpdateSize);
        computedWidthProperty().addListener(this::shouldUpdateSize);
        computedHeightProperty().addListener(this::shouldUpdateSize);
        widthProperty().addListener(this::updateParentWidth);
        heightProperty().addListener(this::updateParentHeight);
        paddingProperty().addListener(this::shouldUpdateSize);
        parentProperty().addListener(this::updateScene);
    }

    @Override
    public IntegerProperty xProperty() {
        return xProperty;
    }

    @Override
    public IntegerProperty yProperty() {
        return yProperty;
    }

    @Override
    public IntegerProperty widthProperty() {
        return widthProperty;
    }

    @Override
    public IntegerProperty heightProperty() {
        return heightProperty;
    }

    @Override
    public IntegerProperty minWidthProperty() {
        return minWidthProperty;
    }

    @Override
    public IntegerProperty minHeightProperty() {
        return minHeightProperty;
    }

    @Override
    public IntegerProperty prefWidthProperty() {
        return prefWidthProperty;
    }

    @Override
    public IntegerProperty prefHeightProperty() {
        return prefHeightProperty;
    }

    @Override
    public IntegerProperty maxWidthProperty() {
        return maxWidthProperty;
    }

    @Override
    public IntegerProperty maxHeightProperty() {
        return maxHeightProperty;
    }

    @Override
    public IntegerProperty parentPrefWidthProperty() {
        return parentPrefWidthProperty;
    }

    @Override
    public IntegerProperty parentPrefHeightProperty() {
        return parentPrefHeightProperty;
    }

    @Override
    public ObservableIntegerValue computedWidthProperty() {
        return computedWidthPropertyReadOnly;
    }

    protected void setComputedWidth(int value) {
        computedWidthProperty.setValue(value);
    }

    @Override
    public ObservableIntegerValue computedHeightProperty() {
        return computedHeightPropertyReadOnly;
    }

    protected void setComputedHeight(int value) {
        computedHeightProperty.setValue(value);
    }

    @Override
    public IntegerProperty backgroundColorProperty() {
        return backgroundColorProperty;
    }

    @Override
    public ObjectProperty<Insets> paddingProperty() {
        return paddingProperty;
    }

    @Override
    public ObjectProperty<Text> tooltipProperty() {
        return tooltipProperty;
    }

    @Override
    public ObjectProperty<Parent> parentProperty() {
        return parentProperty;
    }

    @Override
    public ObservableObjectValue<Scene> sceneProperty() {
        return scenePropertyReadOnly;
    }

    @Override
    public BooleanProperty visibleProperty() {
        return visibleProperty;
    }

    @Override
    public BooleanProperty disableProperty() {
        return disableProperty;
    }

    @Override
    public ObservableBooleanValue disabledProperty() {
        return disabledProperty;
    }

    @Override
    public ObservableBooleanValue rootProperty() {
        return rootProperty;
    }

    @Override
    public ObservableBooleanValue focusedProperty() {
        return focusedProperty;
    }

    @Override
    public ObservableBooleanValue hoveredProperty() {
        return hoveredProperty;
    }

    protected <N extends Node> Skin<? super N> getSkin() {
        if (skin == null) {
            skin = GUAPI.getTheme().provideSkin(this, getType());
        }
        return skin;
    }

    protected abstract <N extends Node> NodeType<N> getType();

    protected void resetSkin() {
        skin = null;
    }

    @Override
    public <E extends ScreenEvent> void handleEvent(ScreenEventType<E> type, E event) {
        type.ifMouseEvent(event, this::handleMouseEvent, () -> notifyEvent(type, event));
    }

    protected <E extends ScreenEvent> void notifyEvent(ScreenEventType<E> type, E event) {
        type.onEvent(this, event);
        eventHandlerDelegate.handleEvent(type, event);
        getSkin().onEvent(type, event);
    }

    @Override
    public <E extends ScreenEvent> void addListener(ScreenEventType<E> type, ScreenEventListener<E> listener) {
        eventHandlerDelegate.addListener(type, listener);
    }

    @Override
    public <E extends ScreenEvent> void removeListener(ScreenEventType<E> type, ScreenEventListener<E> listener) {
        eventHandlerDelegate.removeListener(type, listener);
    }

    @Override
    public boolean preRender(Matrices matrices, int mouseX, int mouseY, float delta) {
        boolean res = checkRender();
        if (isVisible()) {
            res |= getSkin().preRender(this, matrices, mouseX, mouseY, delta);
        }
        return res;
    }

    @Override
    public void render(Matrices matrices, int mouseX, int mouseY, float delta) {
        if (isVisible()) {
            getSkin().render(this, matrices, mouseX, mouseY, delta);
        }
    }

    @Override
    public void postRender(Matrices matrices, int mouseX, int mouseY, float delta) {
        if (isVisible()) {
            getSkin().postRender(this, matrices, mouseX, mouseY, delta);
        }
    }

    @Override
    public void tick() {
        getSkin().tick();
    }

    @Override
    public boolean checkRender() {
        boolean res = false;
        if (shouldComputeSize) {
            computeSize();
            res = true;
        }
        if (shouldUpdateSize) {
            updateSize();
            res = true;
        }
        return res;
    }

    @Override
    public void shouldComputeSize() {
        shouldComputeSize = true;
    }

    protected void computeSize() {
        shouldComputeSize = false;
        computeWidth();
        computeHeight();
    }

    private void computeWidth() {
        setComputedWidth(getSkin().computeWidth(this) + getPadding().getHorizontal());
    }

    private void computeHeight() {
        setComputedHeight(getSkin().computeHeight(this) + getPadding().getVertical());
    }

    protected void shouldUpdateSize() {
        shouldUpdateSize = true;
    }

    protected void updateSize() {
        shouldUpdateSize = false;
        updateWidth();
        updateHeight();
    }

    private void updateWidth() {
        int width = getPrefWidth();
        if (width == COMPUTED_SIZE) {
            if (getParentPrefWidth() != NONE) {
                width = getParentPrefWidth();
            } else {
                width = getComputedWidth();
            }
        }
        width = Math.max(Math.min(width, getMaxWidth()), getMinWidth());
        if (parentProperty().hasValue()) {
            width = Math.min(width, getParent().getMaxChildrenWidth());
        }
        setWidth(width);
    }

    private void updateHeight() {
        int height = getPrefHeight();
        if (height == COMPUTED_SIZE) {
            if (getParentPrefHeight() != NONE) {
                height = getParentPrefHeight();
            } else {
                height = getComputedHeight();
            }
        }
        height = Math.max(Math.min(height, getMaxHeight()), getMinHeight());
        if (parentProperty().hasValue()) {
            height = Math.min(height, getParent().getMaxChildrenHeight());
        }
        setHeight(height);
    }

    private void updateParentWidth() {
        if (getParent() != null) {
            getParent().shouldComputeSize();
            getParent().shouldUpdateChildren();
        }
    }

    private void updateParentHeight() {
        if (getParent() != null) {
            getParent().shouldComputeSize();
            getParent().shouldUpdateChildren();
        }
    }

    private void updateScene(Parent newVal) {
        sceneProperty.unbind();
        if (newVal != null) {
            sceneProperty.bind(newVal.sceneProperty());
        } else {
            sceneProperty.setValue(null);
        }
    }
}
