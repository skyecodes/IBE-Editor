package com.github.franckyi.ibeeditor.client.screen.view.selection.element;

import com.github.franckyi.guapi.api.node.ImageView;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class ImageListSelectionElementView extends ListSelectionElementView {
    private ImageView imageView;

    @Override
    public void build() {
        super.build();
        getRoot().getChildren().add(0, imageView = imageView(null));
    }

    public ImageView getImageView() {
        return imageView;
    }
}
