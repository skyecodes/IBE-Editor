package com.github.franckyi.ibeeditor.editor;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.ListExtended;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.function.Consumer;

public abstract class AbstractProperty<T> extends ListExtended.NodeEntry<Node> {

    private final String name;
    private final T initialValue;
    private final Consumer<T> action;

    public AbstractProperty(String name, T initialValue, Consumer<T> action) {
        this(name, initialValue, action, new HBox(20));
        HBox node = (HBox) this.getNode();
        node.setAlignment(Pos.LEFT);
        node.getChildren().add(new Label(name));
    }

    public AbstractProperty(String name, T initialValue, Consumer<T> action, Node node) {
        super(node);
        this.name = name;
        this.initialValue = initialValue;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public T getInitialValue() {
        return initialValue;
    }

    public abstract T getValue();

    public void apply() {
        action.accept(this.getValue());
    }

    @Override
    public boolean onMouseClicked(GuiScreenEvent.MouseClickedEvent event) {
        return super.onMouseClicked(event);
    }
}
