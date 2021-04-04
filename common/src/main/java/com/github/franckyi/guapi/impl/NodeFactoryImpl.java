package com.github.franckyi.guapi.impl;

import com.github.franckyi.guapi.api.NodeFactory;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.guapi.api.node.builder.*;
import com.github.franckyi.guapi.impl.node.*;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.Collection;
import java.util.function.Consumer;

public class NodeFactoryImpl implements NodeFactory {
    public static final NodeFactory INSTANCE = new NodeFactoryImpl();

    protected NodeFactoryImpl() {
    }

    @Override
    public ButtonBuilder button() {
        return new ButtonImpl();
    }

    @Override
    public ButtonBuilder button(String text) {
        return new ButtonImpl(text);
    }

    @Override
    public ButtonBuilder button(Text text) {
        return new ButtonImpl(text);
    }

    @Override
    public ButtonBuilder button(Consumer<ButtonBuilder> with) {
        return button().with(with);
    }

    @Override
    public CheckBoxBuilder checkBox() {
        return new CheckBoxImpl();
    }

    @Override
    public CheckBoxBuilder checkBox(String text) {
        return new CheckBoxImpl(text);
    }

    @Override
    public CheckBoxBuilder checkBox(Text text) {
        return new CheckBoxImpl(text);
    }

    @Override
    public CheckBoxBuilder checkBox(Consumer<CheckBoxBuilder> with) {
        return checkBox().with(with);
    }

    @Override
    public HBoxBuilder hBox() {
        return new HBoxImpl();
    }

    @Override
    public HBoxBuilder hBox(int spacing) {
        return new HBoxImpl(spacing);
    }

    @Override
    public HBoxBuilder hBox(Node... children) {
        return new HBoxImpl(children);
    }

    @Override
    public HBoxBuilder hBox(Collection<? extends Node> children) {
        return new HBoxImpl(children);
    }

    @Override
    public HBoxBuilder hBox(int spacing, Node... children) {
        return new HBoxImpl(spacing, children);
    }

    @Override
    public HBoxBuilder hBox(int spacing, Collection<? extends Node> children) {
        return new HBoxImpl(spacing, children);
    }

    @Override
    public HBoxBuilder hBox(Consumer<HBoxBuilder> with) {
        return hBox().with(with);
    }

    @Override
    public ImageViewBuilder imageView(String id) {
        return new ImageViewImpl(id);
    }

    @Override
    public ImageViewBuilder imageView(String id, int imageWidth, int imageHeight) {
        return new ImageViewImpl(id, imageWidth, imageHeight);
    }

    @Override
    public ImageViewBuilder imageView(String id, Consumer<ImageViewBuilder> with) {
        return imageView(id).with(with);
    }

    @Override
    public LabelBuilder label() {
        return new LabelImpl();
    }

    @Override
    public LabelBuilder label(String text) {
        return new LabelImpl(text);
    }

    @Override
    public LabelBuilder label(Text text) {
        return new LabelImpl(text);
    }

    @Override
    public LabelBuilder label(String text, boolean shadow) {
        return new LabelImpl(text, shadow);
    }

    @Override
    public LabelBuilder label(Text text, boolean shadow) {
        return new LabelImpl(text, shadow);
    }

    @Override
    public LabelBuilder label(Consumer<LabelBuilder> with) {
        return label().with(with);
    }

    @Override
    public <E> ListViewBuilder<E> listView(Class<E> eClass) {
        return new ListViewImpl<>();
    }

    @Override
    public <E> ListViewBuilder<E> listView(Class<E> eClass, int itemHeight) {
        return new ListViewImpl<>(itemHeight);
    }

    @Override
    public <E> ListViewBuilder<E> listView(int itemHeight, E... items) {
        return new ListViewImpl<>(itemHeight, items);
    }

    @Override
    public <E> ListViewBuilder<E> listView(int itemHeight, Collection<? extends E> items) {
        return new ListViewImpl<>(itemHeight, items);
    }

    @Override
    public <E> ListViewBuilder<E> listView(Class<E> eClass, Consumer<ListViewBuilder<E>> with) {
        return listView(eClass).with(with);
    }

    @Override
    public TextFieldBuilder textField() {
        return new TextFieldImpl();
    }

    @Override
    public TextFieldBuilder textField(String value) {
        return new TextFieldImpl(value);
    }

    @Override
    public TextFieldBuilder textField(String label, String value) {
        return new TextFieldImpl(label, value);
    }

    @Override
    public TextFieldBuilder textField(Text label, String value) {
        return new TextFieldImpl(label, value);
    }

    @Override
    public TextFieldBuilder textField(Consumer<TextFieldBuilder> with) {
        return textField().with(with);
    }

    @Override
    public TexturedButtonBuilder texturedButton(String id, boolean drawButton) {
        return new TexturedButtonImpl(id, drawButton);
    }

    @Override
    public TexturedButtonBuilder texturedButton(String id, int imageWidth, int imageHeight, boolean drawButton) {
        return new TexturedButtonImpl(id, imageWidth, imageHeight, drawButton);
    }

    @Override
    public TexturedButtonBuilder texturedButton(String id, boolean drawButton, Consumer<TexturedButtonBuilder> with) {
        return texturedButton(id, drawButton).with(with);
    }

    @Override
    public <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(Class<E> eClass) {
        return new TreeViewImpl<>();
    }

    @Override
    public <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(Class<E> eClass, int itemHeight) {
        return new TreeViewImpl<>(itemHeight);
    }

    @Override
    public <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(int itemHeight, E root) {
        return new TreeViewImpl<>(itemHeight, root);
    }

    @Override
    public <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(Class<E> eClass, Consumer<TreeViewBuilder<E>> with) {
        return treeView(eClass).with(with);
    }

    @Override
    public VBoxBuilder vBox() {
        return new VBoxImpl();
    }

    @Override
    public VBoxBuilder vBox(int spacing) {
        return new VBoxImpl(spacing);
    }

    @Override
    public VBoxBuilder vBox(Node... children) {
        return new VBoxImpl(children);
    }

    @Override
    public VBoxBuilder vBox(Collection<? extends Node> children) {
        return new VBoxImpl(children);
    }

    @Override
    public VBoxBuilder vBox(int spacing, Collection<? extends Node> children) {
        return new VBoxImpl(spacing, children);
    }

    @Override
    public VBoxBuilder vBox(int spacing, Node... children) {
        return new VBoxImpl(spacing, children);
    }

    @Override
    public VBoxBuilder vBox(Consumer<VBoxBuilder> with) {
        return vBox().with(with);
    }

    @Override
    public SceneBuilder scene() {
        return new SceneImpl();
    }

    @Override
    public SceneBuilder scene(Node root) {
        return new SceneImpl(root);
    }

    @Override
    public SceneBuilder scene(Node root, boolean fullScreen) {
        return new SceneImpl(root, fullScreen);
    }

    @Override
    public SceneBuilder scene(Node root, boolean fullScreen, boolean texturedBackground) {
        return new SceneImpl(root, fullScreen, texturedBackground);
    }

    @Override
    public SceneBuilder scene(Consumer<SceneBuilder> with) {
        return scene().with(with);
    }
}
