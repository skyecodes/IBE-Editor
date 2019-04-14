package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.guapi.node.ListExtended;

import java.util.Arrays;

public class Category extends ListExtended<AbstractProperty<?>> {

    public Category() {
        super(25);
    }

    public void apply() {
        this.getChildren().forEach(AbstractProperty::apply);
    }

    public final void addAll(AbstractProperty<?>... properties) {
        this.getChildren().addAll(Arrays.asList(properties));
    }

}
