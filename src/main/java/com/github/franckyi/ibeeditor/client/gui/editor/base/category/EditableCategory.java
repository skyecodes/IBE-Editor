package com.github.franckyi.ibeeditor.client.gui.editor.base.category;

import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.IEditableCategoryProperty;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class EditableCategory<T> extends AbstractCategory {

    private int editableStart;

    public EditableCategory() {
        this(0);
    }

    public EditableCategory(int editableStart) {
        this.editableStart = editableStart;
    }

    protected void addProperty(T initialValue) {
        int index = this.getPropertyCount();
        AbstractProperty<T> p = this.createNewProperty(initialValue, index);
        this.getChildren().add(index + editableStart, p);
        p.updateSize(this.getWidth());
    }

    protected abstract AbstractProperty<T> createNewProperty(T initialValue, int index);

    protected abstract T getDefaultPropertyValue();

    protected IEditableCategoryProperty getProperty(int index) {
        return (IEditableCategoryProperty) this.getChildren().get(index + editableStart);
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
            this(new String[]{TextFormatting.GREEN + text});
        }

        public AddButton(String... textLines) {
            super(null, aVoid -> {
            });
            this.getNode().getChildren().remove(1);
            button.getText().clear();
            button.getText().addAll(Stream.of(textLines).map(ITextComponent::getTextComponentOrEmpty).collect(Collectors.toList()));
        }

        @Override
        protected void build() {
            this.addAll(button = new TexturedButton("add.png"));
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
