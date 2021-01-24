package com.github.franckyi.guapi.common.data;

public interface ObservableValue<T> {
    T getValue();
    void addListener(ChangeListener<? super T> listener);
    void removeListener(ChangeListener<? super T> listener);
}
