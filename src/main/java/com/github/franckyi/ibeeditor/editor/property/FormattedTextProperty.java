package com.github.franckyi.ibeeditor.editor.property;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.ibeeditor.node.TexturedButton;

import java.util.function.Consumer;

public class FormattedTextProperty extends StringProperty {

    private final TexturedButton formatButton;

    public FormattedTextProperty(String name, String value, Consumer<String> action) {
        super(name, value.startsWith("§r") ? value.substring(2) : value, action);
        formatButton = new TexturedButton("format.png");
        formatButton.setMargin(Insets.left(10));
        formatButton.getOnMouseClickedListeners().add(e -> {
            textField.setText(textField.getText() + "§");
            textField.getView().setFocused(true);
        });
        this.getNode().getChildren().add(formatButton);
    }

    public TexturedButton getFormatButton() {
        return formatButton;
    }

    @Override
    public void apply() {
        action.accept("§r" + this.getValue());
    }

    @Override
    protected void updateTextFieldSize() {
        textField.setPrefWidth(this.getList().getWidth() - 110);
    }
}
