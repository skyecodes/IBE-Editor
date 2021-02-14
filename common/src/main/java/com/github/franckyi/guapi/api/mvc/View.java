package com.github.franckyi.guapi.api.mvc;

import com.github.franckyi.guapi.api.node.Node;

public interface View<N extends Node> {
    N getRoot();
}
