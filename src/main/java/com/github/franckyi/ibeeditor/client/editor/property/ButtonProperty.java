package com.github.franckyi.ibeeditor.client.editor.property;

import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.ibeeditor.client.editor.AbstractProperty;

import java.util.Arrays;
import java.util.List;

public class ButtonProperty extends AbstractProperty<Void> {

    private final List<String> tooltip;
    private Button button;

    public ButtonProperty(String text, Runnable action) {
        this(text, action, new String[0]);
    }

    public ButtonProperty(String text, Runnable action, String... tooltip) {
        super(null, aVoid -> {
        });
        this.tooltip = Arrays.asList(tooltip);
        this.getNode().getChildren().remove(1);
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
    public void drawEntry(int entryWidth, int entryHeight, int mouseX, int mouseY, boolean p_194999_5_, float partialTicks) {
        super.drawEntry(entryWidth, entryHeight, mouseX, mouseY, p_194999_5_, partialTicks);
        if (button.getView().isMouseOver() && !tooltip.isEmpty()) {
            mc.currentScreen.drawHoveringText(tooltip, mouseX, mouseY);
        }
    }
}
