package com.github.franckyi.ibeeditor.client.editor.gui;

import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.CategoryMVC;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.CategoryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.EntryMVC;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.EntryModel;
import com.github.franckyi.ibeeditor.client.util.gui.IBEView;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public abstract class ListEditorView extends IBEView {
    private ListView<CategoryModel> categoryList;
    private ListView<EntryModel> entryList;

    @Override
    protected Node createEditor() {
        return hBox(editor -> {
            editor.add(categoryList = listView(CategoryModel.class, left -> left.itemHeight(20).padding(5).renderer(item -> mvc(CategoryMVC.INSTANCE, item))), 1);
            editor.add(entryList = listView(EntryModel.class, right -> right.itemHeight(25).padding(5).renderer(item -> mvc(EntryMVC.INSTANCE, item))), 2);
            editor.spacing(10).fillHeight();
        });
    }

    public ListView<CategoryModel> getCategoryList() {
        return categoryList;
    }

    public ListView<EntryModel> getEntryList() {
        return entryList;
    }
}
