package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;

public interface TreeView<E extends TreeView.TreeItem<E>> extends ListNode<E> {
    default E getRoot() {
        return rootItemProperty().getValue();
    }

    ObjectProperty<E> rootItemProperty();

    default void setRoot(E value) {
        rootItemProperty().setValue(value);
    }

    default boolean isShowRoot() {
        return showRootProperty().getValue();
    }

    BooleanProperty showRootProperty();

    default void setShowRoot(boolean value) {
        showRootProperty().setValue(value);
    }

    default int getChildrenIncrement() {
        return childrenIncrementProperty().getValue();
    }

    IntegerProperty childrenIncrementProperty();

    default void setChildrenIncrement(int value) {
        childrenIncrementProperty().setValue(value);
    }

    interface TreeItem<E extends TreeItem<E>> {
        ObservableList<E> getChildren();

        default boolean isExpanded() {
            return expandedProperty().getValue();
        }

        BooleanProperty expandedProperty();

        default void setExpanded(boolean value) {
            expandedProperty().setValue(value);
        }

        default E getParent() {
            return parentProperty().getValue();
        }

        ObjectProperty<E> parentProperty();

        default void setParent(E value) {
            parentProperty().setValue(value);
        }
    }
}
