package com.github.franckyi.ibeeditor.client.property;

import com.github.franckyi.guapi.node.TexturedButton;
import net.minecraft.util.text.TextFormatting;

import java.util.function.Consumer;

public class PropertyFormattedText extends PropertyString {

    protected TexturedButton formatButton;

    public PropertyFormattedText(String name, String value, Consumer<String> action) {
        super(name, value.startsWith("§r") ? value.substring(2) : value, action);
    }

    @Override
    protected void build() {
        super.build();
        this.addAll(formatButton = new TexturedButton("format.png", TextFormatting.AQUA + "Format"));
        formatButton.getOnMouseClickedListeners().add(e -> {
            textField.setValue(textField.getValue() + "§");
            textField.getView().setFocused(true);
        });
    }

    @Override
    public void apply() {
        action.accept("§r" + this.getValue());
    }

    @Override
    protected void updateSize(int listWidth) {
        textField.setPrefWidth(listWidth - 136);
    }

}
