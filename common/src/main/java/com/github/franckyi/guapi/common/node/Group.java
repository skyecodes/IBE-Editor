package com.github.franckyi.guapi.common.node;

import com.github.franckyi.guapi.common.data.ObservableArrayList;
import com.github.franckyi.guapi.common.data.ObservableList;

public abstract class Group extends Node {
    protected final ObservableList<Node> children = new ObservableArrayList<>();

    public ObservableList<Node> getChildren() {
        return children;
    }
}
