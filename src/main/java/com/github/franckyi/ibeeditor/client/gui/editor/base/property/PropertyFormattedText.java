package com.github.franckyi.ibeeditor.client.gui.editor.base.property;

import com.github.franckyi.guapi.node.TexturedButton;
import net.minecraft.util.text.TextFormatting;

import java.util.function.Consumer;

public class PropertyFormattedText extends PropertyString {

    protected TexturedButton formatButton;

    public PropertyFormattedText(String name, String value, Consumer<String> action) {
        super(name, value, action);
    }

    public PropertyFormattedText(String name, String initialValue, Consumer<String> action, int labelSize) {
        super(name, initialValue, action, labelSize);
    }

    @Override
    protected void build() {
        super.build();
        this.addAll(formatButton = new TexturedButton("format.png", TextFormatting.AQUA + "Format"));
        textField.getTooltipText().add(textField.getValue());
        textField.getOnValueChangedListeners().add((oldVal, newVal) -> textField.getTooltipText().set(0, newVal));
        formatButton.getOnMouseClickedListeners().add(e -> {
            int i = textField.getView().getCursorPosition();
            String s = textField.getValue();
            textField.setValue(s.substring(0, i) + "§" + s.substring(i));
            textField.getView().setCursorPosition(i + 1);
            textField.getView().changeFocus(true);
        });
    }

    @Override
    public void updateSize(int listWidth) {
        textField.setPrefWidth(listWidth - nameLabel.getWidth() - OFFSET - 85);
    }

}
