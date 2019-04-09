package com.github.franckyi.guapi.node;

import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class EnumButton<T> extends Button {

    private List<T> values;
    private T value;
    private int index;
    private Function<T, String> renderer;

    @SafeVarargs
    public EnumButton(T... values) {
        this(new ArrayList<>(Arrays.asList(values)));
    }

    public EnumButton(List<T> values) {
        this.values = values;
        renderer = Objects::toString;
        index = 0;
        if (!values.isEmpty()) {
            value = values.get(0);
        }
        updateText();
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        this.index = this.getValues().indexOf(value);
        this.updateText();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        this.value = this.getValues().get(index);
        this.updateText();
    }

    public Function<T, String> getRenderer() {
        return renderer;
    }

    public void setRenderer(Function<T, String> renderer) {
        this.renderer = renderer;
        this.updateText();
    }

    private void updateText() {
        this.setText(this.getRenderer().apply(this.getValue()));
    }

    private void cycleValue() {
        if (this.getIndex() == this.getValues().size() - 1) {
            this.setIndex(0);
        } else {
            this.setIndex(this.getIndex() + 1);
        }
    }

    @Override
    public boolean onMouseClicked(GuiScreenEvent.MouseClickedEvent event) {
        if (super.onMouseClicked(event)) {
            this.cycleValue();
            return true;
        }
        return false;
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(this.getValues() != null ? this.getValues().stream().map(this.getRenderer()).mapToInt(mc.fontRenderer::getStringWidth).max().orElse(0) + 10 : 0);
    }

}
