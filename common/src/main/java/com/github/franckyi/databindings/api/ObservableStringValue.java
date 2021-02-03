package com.github.franckyi.databindings.api;

public interface ObservableStringValue extends ObservableObjectValue<String> {
    default ObservableStringValue append(String s) {
        return mapToString(s1 -> s1 + s, s);
    }
}
