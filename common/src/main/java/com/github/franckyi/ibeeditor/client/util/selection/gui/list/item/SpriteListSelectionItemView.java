package com.github.franckyi.ibeeditor.client.util.selection.gui.list.item;

import com.github.franckyi.guapi.api.node.SpriteView;
import com.github.franckyi.ibeeditor.client.util.selection.gui.list.ListSelectionItemView;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class SpriteListSelectionItemView extends ListSelectionItemView {
    private SpriteView spriteView;

    @Override
    public void build() {
        super.build();
        getRoot().getChildren().add(0, spriteView = spriteView(null, 18, 18));
    }

    public SpriteView getSpriteView() {
        return spriteView;
    }
}
