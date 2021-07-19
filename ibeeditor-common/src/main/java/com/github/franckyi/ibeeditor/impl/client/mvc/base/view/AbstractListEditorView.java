package com.github.franckyi.ibeeditor.impl.client.mvc.base.view;

import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.ListEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.IBEEditorMVC;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public abstract class AbstractListEditorView extends AbstractEditorView implements ListEditorView {
    private ListView<EditorCategoryModel> categoryList;
    private ListView<EditorEntryModel> entryList;

    @Override
    protected Node createEditor() {
        return hBox(editor -> {
            editor.add(categoryList = listView(EditorCategoryModel.class, left -> left.itemHeight(25).padding(5).renderer(item -> mvc(IBEEditorMVC.EDITOR_CATEGORY, item))), 1);
            editor.add(entryList = listView(EditorEntryModel.class, right -> right.itemHeight(25).padding(5).renderer(item -> mvc(IBEEditorMVC.EDITOR_ENTRY, item))), 2);
            editor.spacing(10).fillHeight();
        });
    }

    @Override
    public ListView<EditorCategoryModel> getCategoryList() {
        return categoryList;
    }

    @Override
    public ListView<EditorEntryModel> getEntryList() {
        return entryList;
    }
}
