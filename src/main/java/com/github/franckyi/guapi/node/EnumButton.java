package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.ValueNode;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class EnumButton<T> extends Button implements ValueNode<T> {

    private List<T> values;
    private T value;
    private int index;
    private Function<T, String> renderer;
    private final Set<BiConsumer<T, T>> onValueChangedListeners;

    @SafeVarargs
    public EnumButton(T... values) {
        this(new ArrayList<>(Arrays.asList(values)));
    }

    public EnumButton(List<T> values) {
        this.values = values;
        renderer = Objects::toString;
        index = 0;
        onValueChangedListeners = new HashSet<>();
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
        if (values.contains(value)) {
            this.index = this.getValues().indexOf(value);
        }
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        if (values.contains(value)) {
            T old = this.value;
            this.value = value;
            this.index = this.getValues().indexOf(value);
            this.updateText();
            if (old != value) {
                this.onValueChanged(old, value);
            }
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        if (index < values.size()) {
            this.index = index;
            this.value = this.getValues().get(index);
            this.updateText();
        }
    }

    public Function<T, String> getRenderer() {
        return renderer;
    }

    public void setRenderer(Function<T, String> renderer) {
        this.renderer = renderer;
        this.updateText();
    }

    private void updateText() {
        if (this.getValue() != null) {
            this.setText(this.getRenderer().apply(this.getValue()));
        }
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
        if (this.getValues() != null && !this.getValues().isEmpty()) {
            this.setComputedWidth(this.getValues().stream().map(this.getRenderer()).mapToInt(mc.fontRenderer::getStringWidth).max().orElse(0) + 10);
        } else if (value != null) {
            this.setComputedWidth(mc.fontRenderer.getStringWidth(renderer.apply(value)) + 10);
        } else {
            this.setComputedWidth(0);
        }
    }

    @Override
    public Set<BiConsumer<T, T>> getOnValueChangedListeners() {
        return onValueChangedListeners;
    }
}
