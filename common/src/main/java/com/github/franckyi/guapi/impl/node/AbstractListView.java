package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.ObservableListFactory;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.guapi.api.node.ListView;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractListView<E> extends AbstractListNode<E> implements ListView<E> {
    private final ObservableList<E> items = ObservableListFactory.arrayList();

    protected AbstractListView() {
        this(0);
    }

    protected AbstractListView(int itemHeight) {
        this(itemHeight, Collections.emptyList());
    }

    @SafeVarargs
    protected AbstractListView(int itemHeight, E... items) {
        this(itemHeight, Arrays.asList(items));
    }

    protected AbstractListView(int itemHeight, Collection<? extends E> items) {
        super(itemHeight);
        getItems().addAll(items);
    }

    @Override
    public ObservableList<E> getItems() {
        return items;
    }
}
