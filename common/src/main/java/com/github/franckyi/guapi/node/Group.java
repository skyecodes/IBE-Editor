package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.event.ListChangeEvent;
import com.github.franckyi.databindings.impl.ObservableArrayList;
import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.event.MouseEvent;
import com.github.franckyi.guapi.event.ScreenEventType;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class Group extends Node implements Parent {
    protected final ObservableList<Node> children = new ChildrenList();

    protected boolean shouldUpdateChildrenPos = true;

    public Group() {
        getChildren().addListener(this::updateChildren);
        paddingProperty().addListener(this::shouldComputeSizeAndChildrenPos);
        xProperty().addListener(this::shouldUpdateChildrenPos);
        yProperty().addListener(this::shouldUpdateChildrenPos);
        widthProperty().addListener(this::shouldComputeSizeAndChildrenPos);
        heightProperty().addListener(this::shouldComputeSizeAndChildrenPos);
    }

    public ObservableList<Node> getChildren() {
        return children;
    }

    @Override
    public void tick() {
        super.tick();
        getChildren().forEach(Node::tick);
    }

    @Override
    protected void checkRender() {
        super.checkRender();
        if (shouldUpdateChildrenPos) {
            updateChildrenPos();
            shouldUpdateChildrenPos = false;
        }
    }

    public void shouldUpdateChildrenPos() {
        shouldUpdateChildrenPos = true;
    }

    protected abstract void updateChildrenPos();

    @Override
    public <E extends MouseEvent> void handleMouseEvent(ScreenEventType<E> type, E event) {
        if (inBounds(event.getMouseX(), event.getMouseY())) {
            for (Node node : getChildren()) {
                node.handleMouseEvent(type, event);
                if (event.getTarget() != null) break;
            }
            if (event.getTarget() == null) {
                event.setTarget(this);
                notifyEvent(type, event);
            }
        }
    }

    @Override
    public int getMaxChildrenWidth() {
        if (getMaxWidth() != INFINITE_SIZE)
            return getMaxWidth() - getPadding().getHorizontal();
        if (getPrefWidth() != COMPUTED_SIZE)
            return getPrefWidth() - getPadding().getHorizontal();
        if (!parentProperty().hasValue())
            return Parent.super.getMaxChildrenWidth();
        return getParent().getMaxChildrenWidth() - getPadding().getHorizontal();
    }

    @Override
    public int getMaxChildrenHeight() {
        if (getMaxHeight() != INFINITE_SIZE)
            return getMaxHeight() - getPadding().getVertical();
        if (getPrefHeight() != COMPUTED_SIZE)
            return getPrefHeight() - getPadding().getVertical();
        if (!parentProperty().hasValue())
            return Parent.super.getMaxChildrenHeight();
        return getParent().getMaxChildrenHeight() - getPadding().getVertical();
    }

    private void updateChildren(ListChangeEvent<? extends Node> event) {
        event.getRemoved(true).forEach(entry -> {
            if (entry.getValue().getParent() == Group.this) {
                entry.getValue().setParent(null);
            }
        });
        event.getAdded(true).forEach(entry -> entry.getValue().setParent(this));
        shouldComputeSizeAndChildrenPos();
    }

    private void shouldComputeSizeAndChildrenPos() {
        this.shouldComputeSize();
        this.shouldUpdateChildrenPos();
    }

    private static class ChildrenList extends ObservableArrayList<Node> {
        @Override
        protected boolean canAdd(Node element) {
            if (element.getParent() != null) {
                GameHooks.logger().error(GUAPI.MARKER, "Can't add Node \"" + element + "\" to Group: already present in Parent \"" + element.getParent() + "\"");
                return false;
            }
            return true;
        }

        @Override
        protected Collection<? extends Node> canAddAll(Collection<? extends Node> c) {
            return super.canAddAll(c).stream().distinct().filter(this::canAdd).collect(Collectors.toList());
        }
    }
}
