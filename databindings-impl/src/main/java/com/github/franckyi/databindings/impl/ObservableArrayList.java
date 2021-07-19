package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.event.ObservableListChangeListener;
import com.github.franckyi.databindings.impl.event.ObservableListChangeEventImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class ObservableArrayList<E> extends ArrayList<E> implements ObservableList<E> {
    protected final List<ObservableListChangeListener<? super E>> listeners = new ArrayList<>();

    public ObservableArrayList() {
    }

    @Override
    public E set(int index, E element) {
        if (!canAdd(element)) return null;
        E removed = super.set(index, element);
        if (removed != element) {
            notify(ObservableListChangeEventImpl.<E>builder().replace(index, removed, element).build());
        }
        return removed;
    }

    @Override
    public boolean add(E e) {
        if (!canAdd(e)) return false;
        super.add(e);
        notify(ObservableListChangeEventImpl.<E>builder().add(size() - 1, e).build());
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (!canAdd(element)) return;
        super.add(index, element);
        notify(ObservableListChangeEventImpl.<E>builder().add(index, element).build());
    }

    @Override
    public E remove(int index) {
        E removed = super.remove(index);
        notify(ObservableListChangeEventImpl.<E>builder().remove(index, removed).build());
        return removed;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >= 0 && super.remove(o)) {
            notify(ObservableListChangeEventImpl.<E>builder().remove(index, (E) o).build());
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        ObservableListChangeEventImpl<E> event = ObservableListChangeEventImpl.<E>builder().clear(this).build();
        super.clear();
        notify(event);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        c = canAddAll(c);
        int initialSize = size();
        if (super.addAll(c)) {
            notify(ObservableListChangeEventImpl.<E>builder().addAll(initialSize, c).build());
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        c = canAddAll(c);
        if (super.addAll(index, c)) {
            notify(ObservableListChangeEventImpl.<E>builder().addAll(index, c).build());
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        List<E> copy = new ArrayList<>(this);
        if (super.removeAll(c)) {
            computeRemoved(copy);
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        List<E> copy = new ArrayList<>(this);
        if (super.retainAll(c)) {
            computeRemoved(copy);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        List<E> copy = new ArrayList<>(this);
        if (super.removeIf(filter)) {
            computeRemoved(copy);
            return true;
        }
        return false;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        List<E> copy = new ArrayList<>(this);
        super.replaceAll(operator);
        ObservableListChangeEventImpl.Builder<E> builder = ObservableListChangeEventImpl.builder();
        for (int i = 0; i < copy.size(); i++) {
            E oldValue = copy.get(i);
            E newValue = get(i);
            if (!Objects.equals(oldValue, newValue)) {
                builder.replace(i, oldValue, newValue);
            }
        }
        ObservableListChangeEventImpl<E> event = builder.build();
        if (!event.getAllChanged().isEmpty()) {
            notify(event);
        }
    }

    @Override
    public void addListener(ObservableListChangeListener<? super E> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ObservableListChangeListener<? super E> listener) {
        listeners.remove(listener);
    }

    protected boolean canAdd(E element) {
        return true;
    }

    protected Collection<? extends E> canAddAll(Collection<? extends E> c) {
        return c.stream().filter(this::canAdd).collect(Collectors.toList());
    }

    protected void computeRemoved(List<E> copy) {
        ObservableListChangeEventImpl.Builder<E> builder = ObservableListChangeEventImpl.builder();
        int index = 0;
        for (int copyIndex = 0; copyIndex < copy.size(); copyIndex++) {
            E oldValue = copy.get(copyIndex);
            E newValue = index < size() ? get(index) : null;
            if (oldValue != newValue) {
                builder.remove(copyIndex, oldValue);
            } else if (++index == size()) {
                break;
            }
        }
        notify(builder.build());
    }

    protected void notify(ObservableListChangeEventImpl<E> event) {
        listeners.forEach(listener -> listener.onListChange(event));
    }
}
