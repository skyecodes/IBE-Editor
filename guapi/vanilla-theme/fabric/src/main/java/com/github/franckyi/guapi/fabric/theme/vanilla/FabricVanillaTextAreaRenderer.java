package com.github.franckyi.guapi.fabric.theme.vanilla;

import com.github.franckyi.guapi.api.node.TextArea;
import com.github.franckyi.guapi.fabric.theme.mixin.FabricTextFieldWidgetMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class FabricVanillaTextAreaRenderer extends FabricVanillaTextFieldRenderer<TextArea> {
    public FabricVanillaTextAreaRenderer(TextArea node) {
        super(node);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (!this.isActive()) {
            return false;
        }
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (((FabricTextFieldWidgetMixin) this).isEditable()) {
            switch (keyCode) {
                case GLFW.GLFW_KEY_ENTER:
                case GLFW.GLFW_KEY_KP_ENTER:
                    write("\n");
                    return true;
            }
        }
        return false;
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        FabricTextFieldWidgetMixin self = (FabricTextFieldWidgetMixin) this;
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        if (isVisible()) {
            int color;
            if (self.drawsBackground()) {
                color = isFocused() ? -1 : -6250336;
                fill(matrices, x - 1, y - 1, x + width + 1, y + height + 1, color);
                fill(matrices, x, y, x + width, y + height, -16777216);
            }

            color = self.isEditable() ? self.getEditableColor() : self.getUneditableColor();
            List<String> lines = node.getLines();
            if (node.isWrapText()) {
                List<String> wrappedLines = new ArrayList<>();
                int index = 0;
                for (String line : lines) {
                    do {
                        String wrappedLine = textRenderer.trimToWidth((Text) node.getTextRenderer().render(line, index), getInnerWidth()).getString();
                        wrappedLines.add(wrappedLine);
                        if (line.equals(wrappedLine)) {
                            continue;
                        }
                        line = line.substring(wrappedLine.length());
                    } while (true);

                }
            }
        }
    }

    private List<String> getWrappedLines() {
        
    }
}
