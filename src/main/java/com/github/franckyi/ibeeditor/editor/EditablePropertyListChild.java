package com.github.franckyi.ibeeditor.editor;

import com.github.franckyi.guapi.Group;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.ibeeditor.node.TexturedButton;

public interface EditablePropertyListChild {

    Group getNode();

    Label getNameLabel();

    ListControls getControls();

    default void build() {
        Group root = this.getNode();
        getControls().getUp().setPrefSize(20, 20);
        getControls().getUp().getOnMouseClickedListeners().add(event -> getControls().getCategory().swapProperties(getControls().getIndex() - 1, getControls().getIndex()));
        getControls().getDown().setPrefSize(20, 20);
        getControls().getDown().getOnMouseClickedListeners().add(event -> getControls().getCategory().swapProperties(getControls().getIndex(), getControls().getIndex() + 1));
        getControls().getRemove().setPrefSize(20, 20);
        getControls().getRemove().getOnMouseClickedListeners().add(event -> getControls().getCategory().removeProperty(getControls().getIndex()));
        root.getChildren().add(getControls().getUp());
        root.getChildren().add(getControls().getDown());
        root.getChildren().add(getControls().getRemove());
    }

    default void update(int newIndex) {
        getControls().setIndex(newIndex);
        this.getNameLabel().setText(getLabelFor(newIndex));
        getControls().getUp().setVisible(getControls().getIndex() != 0);
        getControls().getDown().setVisible(getControls().getCategory().getPropertyCount() != getControls().getIndex() + 1);
    }

    String getLabelFor(int newIndex);

    class ListControls {

        private final EditablePropertyList category;
        private final TexturedButton up;
        private final TexturedButton down;
        private final TexturedButton remove;
        private int index;

        public ListControls(EditablePropertyList category, int index) {
            this.category = category;
            this.index = index;
            this.up = new TexturedButton("arrow_up.png");
            this.down = new TexturedButton("arrow_down.png");
            this.remove = new TexturedButton("delete.png");
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

        public TexturedButton getUp() {
            return up;
        }

        public TexturedButton getDown() {
            return down;
        }

        public TexturedButton getRemove() {
            return remove;
        }
    }
}
