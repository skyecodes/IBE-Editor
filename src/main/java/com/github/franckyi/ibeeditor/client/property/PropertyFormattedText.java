package com.github.franckyi.ibeeditor.client.property;

import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.config.IBEEditorConfig;
import net.minecraft.util.text.TextFormatting;

import java.util.function.Consumer;

public class PropertyFormattedText extends PropertyString {

    protected TexturedButton formatButton;

    public PropertyFormattedText(String name, String value, Consumer<String> action) {
        super(name, value, action);
    }

    @Override
    protected void build() {
        super.build();
        this.addAll(formatButton = new TexturedButton("format.png", TextFormatting.AQUA + "Format"));
        formatButton.getOnMouseClickedListeners().add(e -> {
            if (IBEEditorConfig.CLIENT.appendFormatCharAtCursor.get()) {
                textField.getView().writeText("§");
            } else {
                textField.setValue(textField.getValue() + "§");
            }
            textField.getView().setFocused(true);
        });
    }

    @Override
    protected void updateSize(int listWidth) {
        textField.setPrefWidth(listWidth - 136);
    }

}
