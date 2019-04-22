package com.github.franckyi.ibeeditor.client.editor;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.node.ListExtended;

import java.util.Arrays;

public class Category extends ListExtended<AbstractProperty<?>> {

    public Category() {
        super(25);
        this.setOffset(new Insets(0, 10, 10, 10));
    }

    public void apply() {
        this.getChildren().forEach(AbstractProperty::apply);
    }

    public final void addAll(AbstractProperty<?>... properties) {
        this.getChildren().addAll(Arrays.asList(properties));
    }

}
