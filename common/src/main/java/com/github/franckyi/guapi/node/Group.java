package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.event.ListChangeEvent;
import com.github.franckyi.databindings.event.PropertyChangeEvent;
import com.github.franckyi.databindings.impl.ObservableArrayList;
import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.event.MouseEvent;
import com.github.franckyi.guapi.event.ScreenEventType;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class Group extends Node implements Parent {
    protected final ObservableList<Node> children = new ChildrenList();

    public Group() {
        getChildren().addListener(this::_updateChildren);
        paddingProperty().addListener(this::_updateSizeAndChildrenPos);
        xProperty().addListener(this::_updateChildrenPos);
        yProperty().addListener(this::_updateChildrenPos);
        widthProperty().addListener(this::_updateSizeAndChildrenPos);
        heightProperty().addListener(this::_updateSizeAndChildrenPos);
    }

    public ObservableList<Node> getChildren() {
        return children;
    }

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
        if (getMaxWidth() != INFINITE_SIZE) return getMaxWidth() - getPadding().getHorizontal();
        if (getPrefWidth() != COMPUTED_SIZE) return getPrefWidth() - getPadding().getHorizontal();
        if (!parentProperty().hasValue()) return Parent.super.getMaxChildrenWidth();
        return getParent().getMaxChildrenWidth() - getPadding().getHorizontal();
    }

    @Override
    public int getMaxChildrenHeight() {
        if (getMaxHeight() != INFINITE_SIZE) return getMaxHeight() - getPadding().getVertical();
        if (getPrefHeight() != COMPUTED_SIZE) return getPrefHeight() - getPadding().getVertical();
        if (!parentProperty().hasValue()) return Parent.super.getMaxChildrenHeight();
        return getParent().getMaxChildrenHeight() - getPadding().getVertical();
    }

    @Override
    public void computeWidth() {
        children.forEach(Node::updateWidth);
        super.computeWidth();
    }

    @Override
    public void computeHeight() {
        children.forEach(Node::updateHeight);
        super.computeHeight();
    }

    private void _updateChildren(ListChangeEvent<? extends Node> event) {
        event.getRemoved(true).forEach(entry -> {
            if (entry.getValue().getParent() == Group.this) {
                entry.getValue().setParent(null);
            }
        });
        event.getAdded(true).forEach(entry -> entry.getValue().setParent(this));
        computeSize();
        updateChildrenPos();
    }

    private void _updateSizeAndChildrenPos(PropertyChangeEvent<?> event) {
        computeSize();
        updateChildrenPos();
    }

    private void _updateChildrenPos(PropertyChangeEvent<?> event) {
        updateChildrenPos();
    }

    private class ChildrenList extends ObservableArrayList<Node> {
        @Override
        protected boolean canAdd(Node element) {
            if (element.getParent() != null) {
                GameHooks.getLogger().error(GUAPI.MARKER, "Can't add Node \"" + element + "\" to Group: already present in Parent \"" + element.getParent() + "\"");
                return false;
            }
            return true;
        }

        @Override
        protected Collection<? extends Node> canAddAll(Collection<? extends Node> c) {
            return super.canAddAll(c).stream().distinct().collect(Collectors.toList());
        }
    }
}
