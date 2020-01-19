package com.github.franckyi.ibeeditor.client.gui.editor.base.category;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractProperty;
import net.minecraft.util.text.TextFormatting;

public abstract class ToolsCategory extends AbstractCategory {

    public void addCommand(String command, Runnable with, Runnable without) {
        this.getChildren().add(new ButtonProperty(String.format("Copy /%s command %swith%s formatting",
                command, TextFormatting.GREEN, TextFormatting.RESET), with));
        this.getChildren().add(new ButtonProperty(String.format("Copy /%s command %swithout%s formatting",
                command, TextFormatting.RED, TextFormatting.RESET), without));
    }

    public void addSimple(String text, Runnable action) {
        this.getChildren().add(new ButtonProperty(text, action));
    }

    private static class ButtonProperty extends AbstractProperty<Void> {

        private Button button;

        public ButtonProperty(String text, Runnable action) {
            super(null, aVoid -> {
            });
            this.getNode().getChildren().remove(1);
            button.setMargin(Insets.left(5));
            button.setText(text);
            button.getOnMouseClickedListeners().add(e -> action.run());
        }

        @Override
        protected Void getValue() {
            return null;
        }

        @Override
        protected void setValue(Void value) {
        }

        @Override
        protected void build() {
            this.getNode().setAlignment(Pos.CENTER);
            this.addAll(button = new Button());
        }

        @Override
        public void updateSize(int listWidth) {
            button.setPrefWidth(listWidth - OFFSET * 2 - 40);
        }
    }
}
