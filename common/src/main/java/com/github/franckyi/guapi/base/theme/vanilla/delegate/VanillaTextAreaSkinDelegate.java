package com.github.franckyi.guapi.base.theme.vanilla.delegate;

import com.github.franckyi.guapi.api.node.TextArea;
import com.github.franckyi.ibeeditor.mixin.MultiLineEditBoxMixin;
import com.github.franckyi.ibeeditor.mixin.MultilineTextFieldMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.MultiLineEditBox;
import net.minecraft.client.gui.components.Whence;
import net.minecraft.client.gui.screens.Screen;

public class VanillaTextAreaSkinDelegate<N extends TextArea> extends MultiLineEditBox implements VanillaWidgetSkinDelegate {
    private final N node;
    private final MultiLineEditBoxMixin self;
    private final MultilineTextFieldMixin textFieldMixin;

    public VanillaTextAreaSkinDelegate(N node) {
        super(Minecraft.getInstance().font, node.getX(), node.getY(), node.getWidth() - 8, node.getHeight(), node.getPlaceholder(), node.getLabel());
        this.node = node;
        self = (MultiLineEditBoxMixin) this;
        textFieldMixin = (MultilineTextFieldMixin) self.getTextField();
        active = !node.isDisabled();
        setCharacterLimit(node.getMaxLength());
        setValue(node.getText());
        setFocused(node.isFocused());
        setValueListener(node::setText);
        node.xProperty().addListener(this::setX);
        node.yProperty().addListener(this::setY);
        node.widthProperty().addListener(newVal -> {
            setWidth(newVal - 8);
            textFieldMixin.setWidth(newVal - 8);
            textFieldMixin.invokeReflowDisplayLines();
        });
        node.heightProperty().addListener(newVal -> height = newVal);
        node.disabledProperty().addListener(newVal -> active = !newVal);
        node.labelProperty().addListener(this::setMessage);
        node.maxLengthProperty().addListener(this::setCharacterLimit);
        node.textProperty().addListener(this::updateText);
        node.focusedProperty().addListener(this::setFocused);
        self.getTextField().seekCursor(Whence.ABSOLUTE, 0); // fix in order to render text
    }

    private void updateText(String text) {
        if (node.getValidator().test(text)) {
            if (text.length() > node.getMaxLength()) {
                textFieldMixin.setRawValue(text.substring(0, node.getMaxLength()));
            } else {
                textFieldMixin.setRawValue(text);
            }
            textFieldMixin.invokeReflowDisplayLines();
        }
    }

    // Mojang somehow broke moving the cursor with the mouse???
    @Override
    public boolean mouseClicked(double d, double e, int i) {
        if (this.withinContentAreaPoint(d, e) && i == 0) {
            self.getTextField().setSelecting(Screen.hasShiftDown());
            self.invokeSeekCursorScreen(d, e);
            return true;
        } else {
            return super.mouseClicked(d, e, i);
        }
    }

    @Override
    public void doTick() {
        tick();
    }
}
