package com.github.franckyi.guapi.common.data.event;

import com.github.franckyi.guapi.common.data.ObservableArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ListChangeEvent<E> {
    private final List<ChangeEntry<E>> allChanged;
    private List<SimpleChangeEntry<E>> added;
    private List<SimpleChangeEntry<E>> removed;
    private List<ChangeEntry<E>> replaced;

    private ListChangeEvent(List<ChangeEntry<E>> changes) {
        this.allChanged = changes;
    }

    public List<ChangeEntry<E>> getAllChanged() {
        return allChanged;
    }

    public List<SimpleChangeEntry<E>> getAdded() {
        if (added == null) {
            added = allChanged.stream()
                    .filter(ChangeEntry::isAdd)
                    .map(e -> new SimpleChangeEntry<>(e.index, e.newValue))
                    .collect(Collectors.toList());
        }
        return added;
    }

    public List<SimpleChangeEntry<E>> getRemoved() {
        if (removed == null) {
            removed = allChanged.stream()
                    .filter(ChangeEntry::isRemove)
                    .map(e -> new SimpleChangeEntry<>(e.index, e.oldValue))
                    .collect(Collectors.toList());
        }
        return removed;
    }

    public List<ChangeEntry<E>> getReplaced() {
        if (replaced == null) {
            replaced = allChanged.stream()
                    .filter(ChangeEntry::isReplace)
                    .collect(Collectors.toList());
        }
        return replaced;
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    public interface Listener<E> {
        void onChange(ListChangeEvent<? extends E> event);
    }

    public static class ChangeEntry<E> {
        private final int index;
        private final E oldValue, newValue;

        private ChangeEntry(int index, E oldValue, E newValue) {
            this.index = index;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        public int getIndex() {
            return index;
        }

        public E getOldValue() {
            return oldValue;
        }

        public E getNewValue() {
            return newValue;
        }

        public boolean isAdd() {
            return oldValue == null && newValue != null;
        }

        public boolean isRemove() {
            return oldValue != null && newValue == null;
        }

        public boolean isReplace() {
            return oldValue != null && newValue != null;
        }
    }

    public static class SimpleChangeEntry<E> {
        private final int index;
        private final E value;

        private SimpleChangeEntry(int index, E value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public E getValue() {
            return value;
        }
    }

    public static class Builder<E> {
        private final List<ChangeEntry<E>> changes;

        private Builder() {
            changes = new ArrayList<>();
        }

        public Builder<E> add(int index, E value) {
           return replace(index, null, value);
        }

        public Builder<E> remove(int index, E value) {
            return replace(index, value, null);
        }

        public Builder<E> replace(int index, E oldValue, E newValue) {
            changes.add(new ChangeEntry<>(index, oldValue, newValue));
            return this;
        }

        public Builder<E> clear(List<E> list) {
            for (int i = 0; i < list.size(); i++) {
                remove(i, list.get(i));
            }
            return this;
        }

        public Builder<E> addAll(int index, Collection<? extends E> c) {
            int i = 0;
            for (E val: c) {
                add(index + i, val);
                i++;
            }
            return this;
        }

        public ListChangeEvent<E> build() {
            return new ListChangeEvent<>(changes);
        }
    }
}
