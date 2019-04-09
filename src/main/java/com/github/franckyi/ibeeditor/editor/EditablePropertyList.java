package com.github.franckyi.ibeeditor.editor;

import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.ibeeditor.node.TexturedButton;
import net.minecraft.util.text.TextFormatting;

import java.util.Collections;
import java.util.List;

public abstract class EditablePropertyList<T> extends PropertyList {

    private int editableStart;

    public EditablePropertyList() {
        this(0);
    }

    public EditablePropertyList(int editableStart) {
        this.editableStart = editableStart;
    }

    protected void addProperty(T initialValue) {
        mc.mouseHelper.ungrabMouse();
        int index = this.getPropertyCount();
        this.getChildren().add(index + editableStart, createNewProperty(initialValue, index));
    }

    protected abstract AbstractProperty<T> createNewProperty(T initialValue, int index);

    protected abstract T getDefaultPropertyValue();

    protected EditablePropertyListChild getProperty(int index) {
        return (EditablePropertyListChild) this.getChildren().get(index + editableStart);
    }

    public void swapProperties(int i1, int i2) {
        if (i1 >= 0 && i2 < this.getPropertyCount()) {
            List<? extends AbstractProperty> list = this.getChildren();
            Collections.swap(list, i1 + editableStart, i2 + editableStart);
            this.getProperty(i1).update(i1);
            this.getProperty(i2).update(i2);
        }
    }

    public void removeProperty(int index) {
        this.getChildren().remove(index + editableStart);
        for (int i = 0; i < this.getPropertyCount(); i++) {
            this.getProperty(i).update(i);
        }
    }

    public int getPropertyCount() {
        return this.getChildren().size() - (1 + editableStart);
    }

    protected class AddButton extends AbstractProperty<Void> {

        private TexturedButton button;

        public AddButton() {
            this("");
        }

        public AddButton(String text) {
            super(null, aVoid -> {
            });
            this.getNode().getChildren().remove(1);
            button.setText(TextFormatting.GREEN + text);
        }

        @Override
        protected void build() {
            this.getNode().getChildren().add(button = new TexturedButton("add.png"));
            button.getOnMouseClickedListeners().add(event -> {
                addProperty(getDefaultPropertyValue());
                int count = getPropertyCount();
                getProperty(--count).update(count--);
                if (count >= 0) {
                    getProperty(count).update(count);
                }
            });
            this.getNode().setAlignment(Pos.CENTER);
        }

        @Override
        protected Void getValue() {
            return null;
        }

        @Override
        protected void setValue(Void value) {
        }
    }
}
