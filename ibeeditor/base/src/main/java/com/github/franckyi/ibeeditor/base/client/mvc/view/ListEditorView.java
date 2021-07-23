package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.base.client.mvc.EditorCategoryMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.EditorEntryMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EditorEntryModel;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class ListEditorView extends EditorView {
    private ListView<EditorCategoryModel> categoryList;
    private ListView<EditorEntryModel> entryList;

    @Override
    protected Node createEditor() {
        return hBox(editor -> {
            editor.add(categoryList = listView(EditorCategoryModel.class, left -> left.itemHeight(25).padding(5).renderer(item -> mvc(EditorCategoryMVC.INSTANCE, item))), 1);
            editor.add(entryList = listView(EditorEntryModel.class, right -> right.itemHeight(25).padding(5).renderer(item -> mvc(EditorEntryMVC.INSTANCE, item))), 2);
            editor.spacing(10).fillHeight();
        });
    }

    public ListView<EditorCategoryModel> getCategoryList() {
        return categoryList;
    }

    public ListView<EditorEntryModel> getEntryList() {
        return entryList;
    }
}
