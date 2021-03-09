package com.github.franckyi.guapi.api;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.guapi.api.node.builder.*;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.util.common.TextFormatting;
import com.github.franckyi.minecraft.util.common.TextStyle;

import java.util.Collection;
import java.util.function.Consumer;

public interface NodeFactory {
    ButtonBuilder button();

    ButtonBuilder button(String text);

    ButtonBuilder button(String text, TextStyle style);

    ButtonBuilder button(String text, TextFormatting... formatting);

    ButtonBuilder button(Text text);

    ButtonBuilder button(Consumer<ButtonBuilder> with);

    CheckBoxBuilder checkBox();

    CheckBoxBuilder checkBox(String text);

    CheckBoxBuilder checkBox(String text, TextStyle style);

    CheckBoxBuilder checkBox(String text, TextFormatting... formatting);

    CheckBoxBuilder checkBox(Text text);

    CheckBoxBuilder checkBox(Consumer<CheckBoxBuilder> with);

    HBoxBuilder hBox();

    HBoxBuilder hBox(int spacing);

    HBoxBuilder hBox(Node... children);

    HBoxBuilder hBox(Collection<? extends Node> children);

    HBoxBuilder hBox(int spacing, Node... children);

    HBoxBuilder hBox(int spacing, Collection<? extends Node> children);

    HBoxBuilder hBox(Consumer<HBoxBuilder> with);

    ImageViewBuilder imageView(String id);

    ImageViewBuilder imageView(String id, int imageWidth, int imageHeight);

    ImageViewBuilder imageView(String id, Consumer<ImageViewBuilder> with);

    LabelBuilder label();

    LabelBuilder label(String text);

    LabelBuilder label(String text, TextStyle style);

    LabelBuilder label(String text, TextFormatting... formatting);

    LabelBuilder label(Text text);

    LabelBuilder label(String text, boolean shadow);

    LabelBuilder label(Text text, boolean shadow);

    LabelBuilder label(Consumer<LabelBuilder> with);

    <E> ListViewBuilder<E> listView(Class<E> eClass);

    <E> ListViewBuilder<E> listView(Class<E> eClass, int itemHeight);

    <E> ListViewBuilder<E> listView(int itemHeight, E... items);

    <E> ListViewBuilder<E> listView(int itemHeight, Collection<? extends E> items);

    <E> ListViewBuilder<E> listView(Class<E> eClass, Consumer<ListViewBuilder<E>> with);

    TextFieldBuilder textField();

    TextFieldBuilder textField(String value);

    TextFieldBuilder textField(String label, String value);

    TextFieldBuilder textField(Text label, String value);

    TextFieldBuilder textField(Consumer<TextFieldBuilder> with);

    TexturedButtonBuilder texturedButton(String id, boolean drawButton);

    TexturedButtonBuilder texturedButton(String id, int imageWidth, int imageHeight, boolean drawButton);

    TexturedButtonBuilder texturedButton(String id, boolean drawButton, Consumer<TexturedButtonBuilder> with);

    <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(Class<E> eClass);

    <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(Class<E> eClass, int itemHeight);

    <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(int itemHeight, E root);

    <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(Class<E> eClass, Consumer<TreeViewBuilder<E>> with);

    VBoxBuilder vBox();

    VBoxBuilder vBox(int spacing);

    VBoxBuilder vBox(Node... children);

    VBoxBuilder vBox(Collection<? extends Node> children);

    VBoxBuilder vBox(int spacing, Collection<? extends Node> children);

    VBoxBuilder vBox(int spacing, Node... children);

    VBoxBuilder vBox(Consumer<VBoxBuilder> with);

    SceneBuilder scene();

    SceneBuilder scene(Node root);

    SceneBuilder scene(Node root, boolean fullScreen);

    SceneBuilder scene(Node root, boolean fullScreen, boolean texturedBackground);

    SceneBuilder scene(Consumer<SceneBuilder> with);
}
