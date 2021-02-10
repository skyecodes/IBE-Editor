package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.builder.SceneBuilder;

public class SceneImpl extends AbstractScene implements SceneBuilder {
    public SceneImpl(Node root) {
        super(root);
    }

    public SceneImpl(Node root, boolean fullScreen) {
        super(root, fullScreen);
    }

    public SceneImpl(Node root, boolean fullScreen, boolean texturedBackground) {
        super(root, fullScreen, texturedBackground);
    }
}
