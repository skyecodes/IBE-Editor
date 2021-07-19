package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.ObservableList;

public interface Group extends Node, Parent {
    ObservableList<Node> getChildren();
}
