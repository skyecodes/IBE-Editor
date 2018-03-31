package com.github.franckyi.ibeeditor.gui.property;

import com.github.franckyi.ibeeditor.gui.base.GuiFormatButton;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class FormattedStringProperty extends StringProperty {

    private final GuiFormatButton formatButton;

    public FormattedStringProperty(String name, Supplier<String> value, Consumer<String> apply) {
        super(name, value, apply);
        formatButton = new GuiFormatButton(0, 0, 0, textField);
        getButtonList().add(formatButton);
    }

    @Override
    protected void place() {
        super.place();
        formatButton.x = slotRight - 170;
        formatButton.y = slotTop;
    }
}
