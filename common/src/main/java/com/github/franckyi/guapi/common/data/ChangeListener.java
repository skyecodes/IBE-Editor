package com.github.franckyi.guapi.common.data;

public interface ChangeListener<T> {
    void onChange(T oldVal, T newVal);
}
