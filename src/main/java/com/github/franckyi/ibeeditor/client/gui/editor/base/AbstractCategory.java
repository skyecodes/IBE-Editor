package com.github.franckyi.ibeeditor.client.gui.editor.base;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.node.ListExtended;

import java.util.Arrays;

public abstract class AbstractCategory extends ListExtended<AbstractProperty<?>> {

    public AbstractCategory() {
        super(25);
        this.setOffset(new Insets(0, 10, 10, 10));
    }

    public void preApply() {
    }

    public void apply() {
        this.getChildren().forEach(AbstractProperty::apply);
    }

    public void postApply() {
    }

    public final void addAll(AbstractProperty<?>... properties) {
        this.getChildren().addAll(Arrays.asList(properties));
    }

}
