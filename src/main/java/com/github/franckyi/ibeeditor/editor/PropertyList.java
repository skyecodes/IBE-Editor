package com.github.franckyi.ibeeditor.editor;

import com.github.franckyi.guapi.node.ListExtended;

import java.util.Arrays;

public class PropertyList extends ListExtended<AbstractProperty> {

    public PropertyList() {
        super(25);
    }

    public void apply() {
        this.getChildren().forEach(AbstractProperty::apply);
    }

    public final void addAll(AbstractProperty... properties) {
        this.getChildren().addAll(Arrays.asList(properties));
    }

}
