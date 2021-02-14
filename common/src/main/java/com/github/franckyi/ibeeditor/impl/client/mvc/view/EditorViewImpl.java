package com.github.franckyi.ibeeditor.impl.client.mvc.view;

import com.github.franckyi.guapi.GUAPIMVC;
import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.VBox;
import com.github.franckyi.ibeeditor.api.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.model.EntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.EditorView;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public class EditorViewImpl implements EditorView {
    private final VBox root;
    private Label headerLabel;
    private ListView<CategoryModel> categoryList;
    private ListView<EntryModel> entryList;
    private Button doneButton;
    private Button cancelButton;

    public EditorViewImpl() {
        root = vBox(root -> {
            root.spacing(5).align(CENTER).padding(5).fillWidth();
            root.add(headerLabel = label(translated("ibeeditor.gui.editor", AQUA, BOLD)).textAlign(CENTER).prefHeight(20));
            root.add(hBox(main -> {
                main.add(categoryList = listView(CategoryModel.class, left -> left.itemHeight(25).padding(5).renderer(item -> GUAPIMVC.load(CategoryModel.class, item))), 1);
                main.add(entryList = listView(EntryModel.class, right -> right.itemHeight(25).padding(5).renderer(item -> GUAPIMVC.load(EntryModel.class, item))), 2);
                main.spacing(10).fillHeight();
            }), 1);
            root.add(hBox(footer -> {
                footer.spacing(20).align(CENTER);
                footer.add(doneButton = button(translated("gui.done", GREEN)).prefWidth(90));
                footer.add(cancelButton = button(translated("gui.cancel", RED)).prefWidth(90));
            }));
        });
    }

    @Override
    public VBox getRoot() {
        return root;
    }

    @Override
    public Label getHeaderLabel() {
        return headerLabel;
    }

    @Override
    public ListView<CategoryModel> getCategoryList() {
        return categoryList;
    }

    @Override
    public ListView<EntryModel> getEntryList() {
        return entryList;
    }

    @Override
    public Button getDoneButton() {
        return doneButton;
    }

    @Override
    public Button getCancelButton() {
        return cancelButton;
    }
}
