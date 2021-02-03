package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.event.ListChangeEvent;
import com.github.franckyi.databindings.event.PropertyChangeEvent;
import com.github.franckyi.databindings.impl.ObservableArrayList;
import com.github.franckyi.guapi.event.MouseEvent;
import com.github.franckyi.guapi.event.ScreenEventType;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class Group extends Node {
    protected final ObservableList<Node> children = new ChildrenList();

    public Group() {
        getChildren().addListener(this::_updateChildren);
        paddingProperty().addListener(this::_updateSizeAndChildrenPos);
        xProperty().addListener(this::_updateChildrenPos);
        yProperty().addListener(this::_updateChildrenPos);
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
                type.onEvent(this, event);
                eventHandlerDelegate.handleEvent(type, event);
            }
        }
    }

    protected abstract void updateChildrenPos();

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
            if (contains(element)) {
                System.err.println("Node \"" + element + "\" already present in Group \"" + Group.this + "\"!");
                return false;
            }
            if (element.getParent() != null) {
                System.err.println("Node \"" + element + "\" already present in Group \"" + element.getParent() + "\"!");
                return false;
            }
            if (element.getScene() != null) {
                System.err.println("Node \"" + element + "\" already present in Scene \"" + element.getScene() + "\"!");
            }
            return true;
        }

        @Override
        protected Collection<? extends Node> canAddAll(Collection<? extends Node> c) {
            return super.canAddAll(c).stream().distinct().collect(Collectors.toList());
        }
    }
}
