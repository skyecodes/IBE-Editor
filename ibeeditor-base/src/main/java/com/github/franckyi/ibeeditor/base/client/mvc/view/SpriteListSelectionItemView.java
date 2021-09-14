package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.guapi.api.node.SpriteView;

import static com.github.franckyi.guapi.api.GuapiHelper.spriteView;

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
