package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.api.theme.vanilla.FabricVanillaDelegateRenderer;
import com.github.franckyi.ibeeditor.mixin.TextFieldWidgetMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.Objects;

public class FabricVanillaTextFieldRenderer extends TextFieldWidget implements FabricVanillaDelegateRenderer {
    private final TextField node;

    public FabricVanillaTextFieldRenderer(TextField node) {
        super(MinecraftClient.getInstance().textRenderer, node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabel().get());
        this.node = node;
        initLabeled(node, this);
        setMaxLength(node.getMaxLength());
        setText(node.getText());
        setCursorToStart(); // fix in order to render text
        setFocused(node.isFocused());
        setChangedListener(node::setText);
        node.maxLengthProperty().addListener(this::setMaxLength);
        node.textProperty().addListener(newVal -> {
            int cursor = getCursor();
            setText(newVal);
            setCursor(cursor);
        });
        node.focusedProperty().addListener(this::setFocused);
        node.validatorProperty().addListener(this::updateValidator);
        node.validationForcedProperty().addListener(this::updateValidator);
        node.textRendererProperty().addListener(this::updateRenderer);
        node.cursorPositionProperty().addListener(super::setSelectionStart);
        node.highlightPositionProperty().addListener(super::setSelectionEnd);
        updateValidator();
        updateRenderer();
    }

    private void updateValidator() {
        if (node.isValidationForced()) {
            if (node.getValidator() == null) {
                setTextPredicate(Objects::nonNull);
            } else {
                setTextPredicate(node.getValidator());
            }
        } else {
            setTextPredicate(Objects::nonNull);
        }
    }

    private void updateRenderer() {
        if (node.getTextRenderer() == null) {
            setRenderTextProvider((string, integer) -> OrderedText.styledForwardsVisitedString(string, Style.EMPTY));
        } else {
            setRenderTextProvider((string, integer) -> ((Text) node.getTextRenderer().render(string, integer).get()).asOrderedText());
        }
        setCursorToStart(); // fix in order to render text
    }

    @Override
    public void setSelectionStart(int value) {
        super.setSelectionStart(value);
        node.setCursorPosition(getCursor());
    }

    @Override
    public void setSelectionEnd(int value) {
        super.setSelectionEnd(value);
        node.setHighlightPosition(((TextFieldWidgetMixin) this).getSelectionEnd());
    }
}
