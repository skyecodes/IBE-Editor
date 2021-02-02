package com.github.franckyi.guapi.common.theme;

import com.github.franckyi.guapi.common.node.HBox;
import com.github.franckyi.guapi.common.node.Label;
import com.github.franckyi.guapi.common.node.VBox;

public interface Theme {
    Skin<Label> getLabelSkin();

    Skin<HBox> getHBoxSkin();

    Skin<VBox> getVBoxSkin();
}
