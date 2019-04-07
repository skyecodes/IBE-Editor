package com.github.franckyi.ibeeditor.editor.item.property;

import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.ibeeditor.editor.item.category.DisplayPropertyList;
import com.github.franckyi.ibeeditor.editor.property.FormattedTextProperty;
import com.github.franckyi.ibeeditor.node.TexturedButton;

import java.util.function.Consumer;

public class LoreProperty extends FormattedTextProperty {

    private DisplayPropertyList category;

    private int index;
    private TexturedButton up;
    private TexturedButton down;
    private TexturedButton remove;

    public LoreProperty(DisplayPropertyList category, int index, String value, Consumer<String> action) {
        super("", value, action);
        this.category = category;
        this.index = index;
        HBox root = this.getNode();
        up = new TexturedButton("arrow_up.png");
        up.setPrefSize(20, 20);
        up.getOnMouseClickedListeners().add(event -> category.swapLores(this.index - 1, this.index));
        down = new TexturedButton("arrow_down.png");
        down.setPrefSize(20, 20);
        down.getOnMouseClickedListeners().add(event -> category.swapLores(this.index, this.index + 1));
        remove = new TexturedButton("delete.png");
        remove.setPrefSize(20, 20);
        remove.getOnMouseClickedListeners().add(event -> category.removeLore(this.index));
        root.getChildren().add(up);
        root.getChildren().add(down);
        root.getChildren().add(remove);
    }

    public void update(int newIndex) {
        this.index = newIndex;
        this.getNameLabel().setText("Lore #" + (index + 1));
        up.setVisible(index != 0);
        down.setVisible(category.getLoreCount() != index + 1);
    }

    @Override
    protected void updateTextFieldSize() {
        textField.setPrefWidth(this.getList().getWidth() - 170);
    }
}
