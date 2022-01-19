package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.builder.ListViewBuilder;

import java.util.Collection;

public class ListViewImpl<E> extends AbstractListView<E> implements ListViewBuilder<E> {
    public ListViewImpl() {
        super();
    }

    public ListViewImpl(int itemHeight) {
        super(itemHeight);
    }

    @SafeVarargs
    public ListViewImpl(int itemHeight, E... items) {
        super(itemHeight, items);
    }

    public ListViewImpl(int itemHeight, Collection<? extends E> items) {
        super(itemHeight, items);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class<?> getType() {
        return ListView.class;
    }

    @Override
    public String toString() {
        return "ListView" + getItems();
    }
}
