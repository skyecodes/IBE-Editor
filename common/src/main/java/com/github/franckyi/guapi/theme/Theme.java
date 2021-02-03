package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.node.HBox;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.VBox;

public interface Theme {
    Skin<Label> getLabelSkin();

    Skin<HBox> getHBoxSkin();

    Skin<VBox> getVBoxSkin();
}
