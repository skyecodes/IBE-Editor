package com.github.franckyi.guapi.api.util;

import com.github.franckyi.guapi.api.node.*;

public final class NodeType<N extends Node> {
    public static final NodeType<Label> LABEL = new NodeType<>("Label");
    public static final NodeType<HBox> HBOX = new NodeType<>("HBox");
    public static final NodeType<VBox> VBOX = new NodeType<>("VBox");
    public static final NodeType<Button> BUTTON = new NodeType<>("Button");
    public static final NodeType<TextField> TEXT_FIELD = new NodeType<>("TextField");
    public static final NodeType<CheckBox> CHECK_BOX = new NodeType<>("CheckBox");
    public static final NodeType<ListView<?>> LIST_VIEW = new NodeType<>("ListView");
    public static final NodeType<TreeView<?>> TREE_VIEW = new NodeType<>("TreeView");
    public static final NodeType<ImageView> IMAGE_VIEW = new NodeType<>("ImageView");
    public static final NodeType<TexturedButton> TEXTURED_BUTTON = new NodeType<>("TexturedButton");
    public static final NodeType<EnumButton<?>> ENUM_BUTTON = new NodeType<>("EnumButton");
    public static final NodeType<ToggleButton> TOGGLE_BUTTON = new NodeType<>("ToggleButton");
    public static final NodeType<TexturedToggleButton> TEXTURED_TOGGLE_BUTTON = new NodeType<>("TexturedToggleButton");
    public static final NodeType<ItemView> ITEM_VIEW = new NodeType<>("ItemView");
    private final String s;

    private NodeType(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "NodeType{" + s + '}';
    }
}
