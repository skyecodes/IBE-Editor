package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.ibeeditor.base.client.mvc.model.SpriteListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.SpriteListSelectionItemView;

public class SpriteListSelectionItemController extends ListSelectionItemController<SpriteListSelectionItemModel, SpriteListSelectionItemView> {
    public SpriteListSelectionItemController(SpriteListSelectionItemModel model, SpriteListSelectionItemView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getSpriteView().setSprite(model.getSprite());
    }
}

