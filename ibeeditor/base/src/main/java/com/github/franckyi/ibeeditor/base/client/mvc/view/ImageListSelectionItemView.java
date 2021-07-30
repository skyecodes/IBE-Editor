package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.guapi.api.node.ImageView;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ImageListSelectionItemView extends ListSelectionItemView {
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
