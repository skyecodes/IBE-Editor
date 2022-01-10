package com.github.franckyi.ibeeditor.client.screen.view.selection.element;

import com.github.franckyi.guapi.api.node.SpriteView;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class SpriteListSelectionElementView extends ListSelectionElementView {
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
