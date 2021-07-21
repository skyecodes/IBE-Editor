package com.github.franckyi.databindings.base.event;

import com.github.franckyi.databindings.api.event.ObservableListChangeEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ObservableListChangeEventImpl<E> implements ObservableListChangeEvent<E> {
    private final List<ChangeEntry<E>> allChanged;
    private List<SimpleChangeEntry<E>> added;
    private List<SimpleChangeEntry<E>> removed;
    private List<ChangeEntry<E>> replaced;

    private ObservableListChangeEventImpl(List<ChangeEntry<E>> changes) {
        this.allChanged = changes;
    }

    @Override
    public List<ChangeEntry<E>> getAllChanged() {
        return allChanged;
    }

    @Override
    public List<SimpleChangeEntry<E>> getAdded(boolean andReplaced) {
        if (added == null) {
            added = allChanged.stream()
                    .filter(andReplaced ? ChangeEntry::wasAddedOrReplaced : ChangeEntry::wasAdded)
                    .map(e -> new SimpleChangeEntryImpl<>(e.getIndex(), e.getNewValue()))
                    .collect(Collectors.toList());
        }
        return added;
    }

    @Override
    public List<SimpleChangeEntry<E>> getRemoved(boolean andReplaced) {
        if (removed == null) {
            removed = allChanged.stream()
                    .filter(andReplaced ? ChangeEntry::wasRemovedOrReplaced : ChangeEntry::wasRemoved)
                    .map(e -> new SimpleChangeEntryImpl<>(e.getIndex(), e.getOldValue()))
                    .collect(Collectors.toList());
        }
        return removed;
    }

    @Override
    public List<ChangeEntry<E>> getReplaced() {
        if (replaced == null) {
            replaced = allChanged.stream()
                    .filter(ChangeEntry::wasReplaced)
                    .collect(Collectors.toList());
        }
        return replaced;
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    public static class ChangeEntryImpl<E> implements ChangeEntry<E> {
        private final int index;
        private final E oldValue, newValue;

        private ChangeEntryImpl(int index, E oldValue, E newValue) {
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
    }

    public static class SimpleChangeEntryImpl<E> implements SimpleChangeEntry<E> {
        private final int index;
        private final E value;

        private SimpleChangeEntryImpl(int index, E value) {
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
            changes.add(new ChangeEntryImpl<>(index, oldValue, newValue));
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
            for (E val : c) {
                add(index + i, val);
                i++;
            }
            return this;
        }

        public ObservableListChangeEventImpl<E> build() {
            return new ObservableListChangeEventImpl<>(changes);
        }
    }
}
