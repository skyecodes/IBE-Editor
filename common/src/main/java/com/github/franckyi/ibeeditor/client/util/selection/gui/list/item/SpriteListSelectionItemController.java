package com.github.franckyi.ibeeditor.client.util.selection.gui.list.item;

import com.github.franckyi.ibeeditor.client.util.selection.gui.list.ListSelectionItemController;

public class SpriteListSelectionItemController extends ListSelectionItemController<SpriteListSelectionItemModel, SpriteListSelectionItemView> {
    public SpriteListSelectionItemController(SpriteListSelectionItemModel model, SpriteListSelectionItemView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getSpriteView().setSpriteFactory(model.getSpriteFactory());
    }
}

