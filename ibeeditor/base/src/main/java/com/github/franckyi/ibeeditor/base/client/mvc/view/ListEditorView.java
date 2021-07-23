package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.base.client.mvc.CategoryMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.EntryMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class ListEditorView extends EditorView {
    private ListView<CategoryModel> categoryList;
    private ListView<EntryModel> entryList;

    @Override
    protected Node createEditor() {
        return hBox(editor -> {
            editor.add(categoryList = listView(CategoryModel.class, left -> left.itemHeight(25).padding(5).renderer(item -> mvc(CategoryMVC.INSTANCE, item))), 1);
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
