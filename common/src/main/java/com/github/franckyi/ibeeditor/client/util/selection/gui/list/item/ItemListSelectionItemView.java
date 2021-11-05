package com.github.franckyi.ibeeditor.client.util.selection.gui.list.item;

import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.ibeeditor.client.util.selection.gui.list.ListSelectionItemView;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class ItemListSelectionItemView extends ListSelectionItemView {
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
