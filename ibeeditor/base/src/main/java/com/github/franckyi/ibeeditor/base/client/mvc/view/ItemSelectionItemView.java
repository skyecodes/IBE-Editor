package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.guapi.api.node.ItemView;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ItemSelectionItemView extends SelectionItemView {
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
