package com.github.franckyi.ibeeditor.impl.client.mvc.view;

import com.github.franckyi.guapi.api.node.*;
import com.github.franckyi.ibeeditor.api.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.model.EntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.CategoryView;
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
            root.add(headerLabel = label(translatedText("ibeeditor.gui.editor", AQUA, BOLD), true).textAlign(CENTER).prefHeight(20));
            root.add(hBox(main -> {
                main.add(categoryList = listView(CategoryModel.class, left -> left.itemHeight(25).padding(5).renderer(item -> mvc(CategoryView.class, item))), 1);
                main.add(entryList = listView(EntryModel.class, right -> right.itemHeight(25).padding(5).renderer(item -> mvc(item.getDefaultViewType(), item))), 2);
                main.spacing(10).fillHeight();
            }), 1);
            root.add(hBox(footer -> {
                footer.spacing(20).align(CENTER);
                footer.add(doneButton = button(translatedText("gui.done", GREEN)).prefWidth(90));
                footer.add(cancelButton = button(translatedText("gui.cancel", RED)).prefWidth(90));
            }));
        });
    }

    @Override
    public Node getRoot() {
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
