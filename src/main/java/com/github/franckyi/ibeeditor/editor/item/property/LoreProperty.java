package com.github.franckyi.ibeeditor.editor.item.property;

import com.github.franckyi.ibeeditor.editor.EditablePropertyListChild;
import com.github.franckyi.ibeeditor.editor.item.category.DisplayPropertyList;
import com.github.franckyi.ibeeditor.editor.property.FormattedTextProperty;

import java.util.function.Consumer;

public class LoreProperty extends FormattedTextProperty implements EditablePropertyListChild {

    private final ListControls controls;

    public LoreProperty(DisplayPropertyList category, int index, String value, Consumer<String> action) {
        super("", value, action);
        this.controls = new ListControls(category, index);
        this.build();
    }

    @Override
    public ListControls getControls() {
        return controls;
    }

    @Override
    public String getLabelFor(int newIndex) {
        return "Lore #" + newIndex + " :";
    }

    @Override
    protected void updateTextFieldSize() {
        textField.setPrefWidth(this.getList().getWidth() - 170);
    }
}
