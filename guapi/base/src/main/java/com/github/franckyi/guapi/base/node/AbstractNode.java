package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.*;
import com.github.franckyi.gameadapter.api.client.IMatrices;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.event.ScreenEventListener;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.Parent;
import com.github.franckyi.guapi.api.node.Scene;
import com.github.franckyi.guapi.api.node.ScreenEventHandler;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.api.util.Insets;
import com.github.franckyi.guapi.api.util.NodeType;
import com.github.franckyi.guapi.api.util.ScreenEventType;
import com.github.franckyi.guapi.base.event.ScreenEventHandlerDelegate;

public abstract class AbstractNode implements Node {
    protected final IntegerProperty xProperty = IntegerProperty.create();
    protected final IntegerProperty yProperty = IntegerProperty.create();
    protected final IntegerProperty widthProperty = IntegerProperty.create();
    protected final IntegerProperty heightProperty = IntegerProperty.create();

    private final IntegerProperty minWidthProperty = IntegerProperty.create();
    private final IntegerProperty minHeightProperty = IntegerProperty.create();
    private final IntegerProperty prefWidthProperty = IntegerProperty.create(COMPUTED_SIZE);
    private final IntegerProperty prefHeightProperty = IntegerProperty.create(COMPUTED_SIZE);
    private final IntegerProperty maxWidthProperty = IntegerProperty.create(INFINITE_SIZE);
    private final IntegerProperty maxHeightProperty = IntegerProperty.create(INFINITE_SIZE);
    private final IntegerProperty parentPrefWidthProperty = IntegerProperty.create(NONE);
    private final IntegerProperty parentPrefHeightProperty = IntegerProperty.create(NONE);

    protected final IntegerProperty computedWidthProperty = IntegerProperty.create();
    private final ObservableIntegerValue computedWidthPropertyReadOnly = ObservableIntegerValue.readOnly(computedWidthProperty);
    protected final IntegerProperty computedHeightProperty = IntegerProperty.create();
    private final ObservableIntegerValue computedHeightPropertyReadOnly = ObservableIntegerValue.readOnly(computedHeightProperty);

    private final IntegerProperty backgroundColorProperty = IntegerProperty.create(DEFAULT_BACKGROUND_COLOR);
    private final ObjectProperty<Insets> paddingProperty = ObjectProperty.create(Insets.NONE);
    private final ObservableList<Text> tooltip = ObservableList.create();

    protected final ObjectProperty<Parent> parentProperty = ObjectProperty.create();
    protected final ObjectProperty<Scene> sceneProperty = ObjectProperty.create();
    private final ObservableObjectValue<Scene> scenePropertyReadOnly = ObservableObjectValue.readOnly(sceneProperty);

    private final BooleanProperty visibleProperty = BooleanProperty.create(true);
    private final BooleanProperty disableProperty = BooleanProperty.create();
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
    public ObservableList<Text> getTooltip() {
        return tooltip;
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
            skin = Guapi.getTheme().provideSkin(this, getType());
        }
        return skin;
    }

    protected abstract <N extends Node> NodeType<N> getType();

    protected void resetSkin() {
        skin = null;
    }

    @Override
    public <E extends ScreenEvent> void handleEvent(ScreenEventType<E> target, E event) {
        target.ifMouseEvent(event, this::handleMouseEvent, () -> notifyEvent(target, event));
    }

    protected <E extends ScreenEvent> void notifyEvent(ScreenEventType<E> target, E event) {
        target.onEvent(this, event);
        eventHandlerDelegate.handleEvent(target, event);
        getSkin().onEvent(target, event);
    }

    @Override
    public <E extends ScreenEvent> void addListener(ScreenEventType<E> target, ScreenEventListener<E> listener) {
        eventHandlerDelegate.addListener(target, listener);
    }

    @Override
    public <E extends ScreenEvent> void removeListener(ScreenEventType<E> target, ScreenEventListener<E> listener) {
        eventHandlerDelegate.removeListener(target, listener);
    }

    @Override
    public boolean preRender(IMatrices matrices, int mouseX, int mouseY, float delta) {
        boolean res = checkRender();
        if (isVisible()) {
            res |= getSkin().preRender(this, matrices, mouseX, mouseY, delta);
        }
        return res;
    }

    @Override
    public void render(IMatrices matrices, int mouseX, int mouseY, float delta) {
        if (isVisible()) {
            getSkin().render(this, matrices, mouseX, mouseY, delta);
        }
    }

    @Override
    public void postRender(IMatrices matrices, int mouseX, int mouseY, float delta) {
        if (isVisible()) {
            getSkin().postRender(this, matrices, mouseX, mouseY, delta);
        }
    }

    @Override
    public void doTick() {
        getSkin().doTick();
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
        if (sceneProperty.isBound()) {
            sceneProperty.unbind();
        }
        if (newVal != null) {
            sceneProperty.bind(newVal.sceneProperty());
        } else {
            sceneProperty.setValue(null);
        }
    }
}
