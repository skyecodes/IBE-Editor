package com.github.franckyi.ibeeditor.client.util.selection.gui.list.item;

import com.github.franckyi.ibeeditor.client.util.selection.gui.list.ListSelectionItemController;

public class ImageListSelectionItemController extends ListSelectionItemController<ImageListSelectionItemModel, ImageListSelectionItemView> {
    public ImageListSelectionItemController(ImageListSelectionItemModel model, ImageListSelectionItemView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getImageView().setTextureId(model.getTextureId());
    }
}
