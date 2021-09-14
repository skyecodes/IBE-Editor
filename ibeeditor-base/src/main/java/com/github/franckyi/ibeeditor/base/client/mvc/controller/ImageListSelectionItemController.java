package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.ibeeditor.base.client.mvc.model.ImageListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ImageListSelectionItemView;

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
