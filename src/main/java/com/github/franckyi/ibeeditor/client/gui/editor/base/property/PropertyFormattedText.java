package com.github.franckyi.ibeeditor.client.gui.editor.base.property;

import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.common.IBEConfiguration;
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
        formatButton.getOnMouseClickedListeners().add(e -> {
            if (IBEConfiguration.CLIENT.appendFormatCharAtCursor.get()) {
                int i = textField.getView().getCursorPosition();
                String s = textField.getValue();
                textField.setValue(s.substring(0, i) + "§" + s.substring(i));
                textField.getView().setCursorPosition(i + 1);
            } else {
                textField.setValue(textField.getValue() + "§");
            }
            textField.getView().changeFocus(true);
        });
    }

    @Override
    public void updateSize(int listWidth) {
        textField.setPrefWidth(listWidth - 87 - nameLabel.getWidth());
    }

}
