package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.ValueNode;
import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.node.ListExtended;
import com.github.franckyi.guapi.node.TexturedButton;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class AbstractProperty<T> extends ListExtended.NodeEntry<HBox> implements ValueNode<T> {

    protected final TexturedButton resetButton;
    protected final T initialValue;
    protected final Consumer<T> action;
    private final Set<BiConsumer<T, T>> onValueChangedListeners;

    public AbstractProperty(T initialValue, Consumer<T> action) {
        super(new HBox());
        this.initialValue = initialValue;
        this.action = action;
        onValueChangedListeners = new HashSet<>();
        this.build();
        this.setValue(initialValue);
        this.addAll(resetButton = new TexturedButton("reset.png", TextFormatting.YELLOW + "Reset to default"));
        resetButton.getOnMouseClickedListeners().add(e -> this.setValue(initialValue));
    }

    @Override
    public Set<BiConsumer<T, T>> getOnValueChangedListeners() {
        return onValueChangedListeners;
    }

    protected void updateSize(int listWidth) {
    }

    protected abstract T getValue();

    protected abstract void setValue(T value);

    protected abstract void build();

    public void apply() {
        action.accept(this.getValue());
    }

    public final void addAll(Node... nodes) {
        this.getNode().getChildren().addAll(Arrays.asList(nodes));
    }

}
