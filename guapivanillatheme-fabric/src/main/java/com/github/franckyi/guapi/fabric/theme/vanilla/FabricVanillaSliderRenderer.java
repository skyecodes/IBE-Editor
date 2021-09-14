package com.github.franckyi.guapi.fabric.theme.vanilla;

import com.github.franckyi.guapi.api.node.Slider;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

public class FabricVanillaSliderRenderer extends SliderWidget implements FabricVanillaDelegateRenderer {
    private final Slider node;

    public FabricVanillaSliderRenderer(Slider node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), LiteralText.EMPTY, 0);
        this.node = node;
        initNode(node, this);
        node.valueProperty().addListener(this::updateView);
        node.labelFactoryProperty().addListener(this::updateMessage);
        updateView();
        updateMessage();
    }

    private void updateView() {
        double max = node.getMaxValue() - node.getMinValue();
        double value = node.getValue() - node.getMinValue();
        this.value = value / max;
        updateMessage();
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        updateNodeFromMouse(mouseX);
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
        updateNodeFromMouse(mouseX);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (amount > 0) {
            node.increment();
        } else if (amount < 0) {
            node.decrement();
        }
        return false;
    }

    private void updateNodeFromMouse(double mouseX) {
        updateNode((mouseX - (x + 4)) / (width - 8));
    }

    private void updateNode(double newRawValue) {
        double range = node.getMaxValue() - node.getMinValue();
        double value = MathHelper.clamp(newRawValue, 0, 1) * range + node.getMinValue();
        double fixedValue = value - (value % node.getStep());
        node.setValue(fixedValue);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_RIGHT) {
            node.increment();
        } else if (keyCode == GLFW.GLFW_KEY_LEFT) {
            node.decrement();
        }
        return false;
    }

    @Override
    protected void updateMessage() {
        setMessage((Text) node.getLabelFactory().apply(node.getValue()));
    }

    @Override
    protected void applyValue() {
    }
}
