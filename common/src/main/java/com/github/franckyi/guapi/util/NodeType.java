package com.github.franckyi.guapi.util;

import com.github.franckyi.guapi.api.node.*;

public final class NodeType<N extends Node> {
    public static final NodeType<Label> LABEL = new NodeType<>("Label");
    public static final NodeType<HBox> HBOX = new NodeType<>("HBox");
    public static final NodeType<VBox> VBOX = new NodeType<>("VBox");
    public static final NodeType<WeightedHBox> WEIGHTED_HBOX = new NodeType<>("WeightedHBox");
    public static final NodeType<WeightedVBox> WEIGHTED_VBOX = new NodeType<>("WeightedVBox");
    public static final NodeType<Button> BUTTON = new NodeType<>("Button");
    public static final NodeType<TextField> TEXTFIELD = new NodeType<>("TextField");
    public static final NodeType<CheckBox> CHECKBOX = new NodeType<>("CheckBox");
    public static final NodeType<ListView<?>> LISTVIEW = new NodeType<>("ListView");
    private final String s;

    private NodeType(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "NodeType{" + s + '}';
    }
}
