package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.base.client.mvc.AttributeItemMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.model.AttributeItemModel;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class AttributeSelectionView extends EditorView {
    private ListView<AttributeItemModel> listView;

    @Override
    protected Node createHeader() {
        return hBox(header -> {
            header.add(hBox().prefWidth(16));
            header.add(label(translated("ibeeditor.gui.choose_attribute").gold().bold()).textAlign(CENTER).prefHeight(20), 1);
            header.add(createButton("ibeeditor:textures/gui/settings.png", "ibeeditor.gui.settings").action(ModScreenHandler::openSettingsScreen));
            header.align(CENTER);
        });
    }

    @Override
    protected Node createEditor() {
        return hBox(editor -> {
            editor.add(vBox(), 1);
            editor.add(listView = listView(AttributeItemModel.class, 25)
                    .renderer(item -> mvc(AttributeItemMVC.INSTANCE, item))
                    .padding(5).childrenFocusable(), 3);
            editor.add(vBox(), 1);
            editor.spacing(10).fillHeight();
        });
    }

    public ListView<AttributeItemModel> getListView() {
        return listView;
    }
}
