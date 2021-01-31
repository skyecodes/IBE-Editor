package com.github.franckyi.guapi.common.data;

import com.github.franckyi.guapi.common.data.event.ListChangeEvent;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ObservableArrayList<E> extends ArrayList<E> implements ObservableList<E> {
    private final List<ListChangeEvent.Listener<? super E>> listeners;

    public ObservableArrayList() {
        listeners = new ArrayList<>();
    }

    @Override
    public E set(int index, E element) {
        E removed = super.set(index, element);
        if (removed != element) {
            notify(ListChangeEvent.<E>builder().replace(index, removed, element).build());
        }
        return removed;
    }

    @Override
    public boolean add(E e) {
        super.add(e);
        notify(ListChangeEvent.<E>builder().add(size() - 1, e).build());
        return true;
    }

    @Override
    public void add(int index, E element) {
        super.add(index, element);
        notify(ListChangeEvent.<E>builder().add(index, element).build());
    }

    @Override
    public E remove(int index) {
        E removed = super.remove(index);
        notify(ListChangeEvent.<E>builder().remove(index, removed).build());
        return removed;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >= 0 && super.remove(o)) {
            notify(ListChangeEvent.<E>builder().remove(index, (E) o).build());
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        ListChangeEvent<E> event = ListChangeEvent.<E>builder().clear(this).build();
        super.clear();
        notify(event);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int initialSize = size();
        if (super.addAll(c)) {
            notify(ListChangeEvent.<E>builder().addAll(initialSize, c).build());
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (super.addAll(index, c)) {
            notify(ListChangeEvent.<E>builder().addAll(index, c).build());
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
        ListChangeEvent.Builder<E> builder = ListChangeEvent.builder();
        for (int i = 0; i < copy.size(); i++) {
            E oldValue = copy.get(i);
            E newValue = get(i);
            if (!Objects.equals(oldValue, newValue)) {
                builder.replace(i, oldValue, newValue);
            }
        }
        ListChangeEvent<E> event = builder.build();
        if (!event.getAllChanged().isEmpty()) {
            notify(event);
        }
    }

    @Override
    public void addListener(ListChangeEvent.Listener<? super E> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ListChangeEvent.Listener<? super E> listener) {
        listeners.remove(listener);
    }

    protected void computeRemoved(List<E> copy) {
        ListChangeEvent.Builder<E> builder = ListChangeEvent.builder();
        int index = 0;
        for (int copyIndex = 0; copyIndex < copy.size(); copyIndex++) {
            E oldValue = copy.get(copyIndex);
            E newValue = get(index);
            if (oldValue != newValue) {
                builder.remove(copyIndex, oldValue);
            } else if (++index == size()) {
                break;
            }
        }
        notify(builder.build());
    }

    protected void notify(ListChangeEvent<E> event) {
        listeners.forEach(listener -> listener.onChange(event));
    }
}
