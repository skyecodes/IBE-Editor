package com.github.franckyi.ibeeditor.client.util.selection.gui.list.item;

import com.github.franckyi.guapi.api.node.ImageView;
import com.github.franckyi.ibeeditor.client.util.selection.gui.list.ListSelectionItemView;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

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
