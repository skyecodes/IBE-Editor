package com.github.franckyi.guapi.forge.theme.vanilla;

import com.github.franckyi.guapi.api.node.Slider;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Mth;
import org.lwjgl.glfw.GLFW;

public class ForgeVanillaSliderRenderer extends AbstractSliderButton implements ForgeVanillaDelegateRenderer {
    private final Slider node;

    public ForgeVanillaSliderRenderer(Slider node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), TextComponent.EMPTY, node.getValue());
        this.node = node;
        initNode(node, this);
        node.valueProperty().addListener(this::updateValue);
        node.labelFactoryProperty().addListener(this::updateMessage);
        updateValue();
        updateMessage();
    }

    private void updateValue() {
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
        double value = Mth.clamp(newRawValue, 0, 1) * range + node.getMinValue();
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
        setMessage((Component) node.getLabelFactory().apply(node.getValue()));
    }

    @Override
    protected void applyValue() {
    }
}
