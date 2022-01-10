package com.github.franckyi.ibeeditor.client.screen.view.selection.element;

import com.github.franckyi.guapi.api.node.ItemView;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class ItemListSelectionElementView extends ListSelectionElementView {
    private ItemView itemView;

    @Override
    public void build() {
        super.build();
        getRoot().getChildren().add(0, itemView = itemView());
    }

    public ItemView getItemView() {
        return itemView;
    }
}
