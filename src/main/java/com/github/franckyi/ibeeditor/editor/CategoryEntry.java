package com.github.franckyi.ibeeditor.editor;

import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.ListExtended;

public class CategoryEntry extends ListExtended.NodeEntry<Label> {

    public CategoryEntry(AbstractEditor editor, String name) {
        super(new Label(name));
        this.getNode().setCentered(true);
        this.getOnMouseClickedListeners().add(e -> editor.onCategoryClicked(this));
    }
}
