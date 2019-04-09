package com.github.franckyi.ibeeditor.editor;

import com.github.franckyi.guapi.Group;
import com.github.franckyi.ibeeditor.node.TexturedButton;
import net.minecraft.util.text.TextFormatting;

public interface EditablePropertyListChild {

    Group getNode();

    ListControls getControls();

    default void build() {
        Group root = this.getNode();
        getControls().getRemove().setPrefSize(20, 20);
        getControls().getRemove().getOnMouseClickedListeners().add(event -> getControls().getCategory().removeProperty(getControls().getIndex()));
        root.getChildren().add(getControls().getRemove());
    }

    default void update(int newIndex) {
        getControls().setIndex(newIndex);
    }

    class ListControls {

        private final EditablePropertyList category;
        private final TexturedButton remove;
        private int index;

        public ListControls(EditablePropertyList category, int index) {
            this.category = category;
            this.index = index;
            this.remove = new TexturedButton("delete.png", TextFormatting.RED + "Remove");
        }

        public EditablePropertyList getCategory() {
            return category;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public TexturedButton getRemove() {
            return remove;
        }
    }

}
