package com.github.franckyi.ibeeditor.client.screen.controller.selection.element;

import com.github.franckyi.ibeeditor.client.screen.model.selection.element.ImageListSelectionElementModel;
import com.github.franckyi.ibeeditor.client.screen.view.selection.element.ImageListSelectionElementView;

public class ImageListSelectionElementController extends ListSelectionElementController<ImageListSelectionElementModel, ImageListSelectionElementView> {
    public ImageListSelectionElementController(ImageListSelectionElementModel model, ImageListSelectionElementView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getImageView().setTextureId(model.getTextureId());
    }
}
