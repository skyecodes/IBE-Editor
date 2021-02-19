package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.ObservableList;

public interface ListView<E> extends ListNode<E> {
    ObservableList<E> getItems();
}
