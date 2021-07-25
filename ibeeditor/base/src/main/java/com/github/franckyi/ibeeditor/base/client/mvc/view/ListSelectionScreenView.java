package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.base.client.mvc.ListSelectionItemMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ListSelectionItemModel;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ListSelectionScreenView extends EditorView {
    private Label titleLabel;
    private ListView<ListSelectionItemModel> listView;
    private TextField searchField;

    @Override
    protected Node createHeader() {
        return hBox(header -> {
            header.add(hBox().prefWidth(16));
            header.add(titleLabel = label().textAlign(CENTER).prefHeight(20), 1);
            header.add(createButton("ibeeditor:textures/gui/settings.png", "ibeeditor.gui.settings").action(ModScreenHandler::openSettingsScreen));
            header.align(CENTER);
        });
    }

    @Override
    protected Node createEditor() {
        return hBox(editor -> {
            editor.add(vBox(), 1);
            editor.add(vBox(center -> {
                center.add(searchField = textField().placeholder(translated("ibeeditor.gui.search")));
                center.add(listView = listView(ListSelectionItemModel.class, 25)
                        .renderer(item -> mvc(ListSelectionItemMVC.INSTANCE, item))
                        .padding(5).childrenFocusable(), 1);
                center.spacing(5).fillWidth();
            }), 4);
            editor.add(vBox(), 1);
            editor.spacing(10).fillHeight();
        });
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public ListView<ListSelectionItemModel> getListView() {
        return listView;
    }

    public TextField getSearchField() {
        return searchField;
    }
}
