package com.github.franckyi.guapi;

import com.github.franckyi.guapi.api.NodeFactory;
import com.github.franckyi.guapi.api.mvc.Controller;
import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.guapi.api.node.builder.*;
import com.github.franckyi.guapi.util.Align;
import com.github.franckyi.guapi.util.Color;
import com.github.franckyi.guapi.util.Insets;
import com.github.franckyi.minecraft.impl.common.text.AbstractText;
import com.github.franckyi.minecraft.impl.common.text.PlainTextImpl;
import com.github.franckyi.minecraft.impl.common.text.TranslatedTextImpl;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.text.builder.PlainTextBuilder;
import com.github.franckyi.minecraft.api.common.text.builder.TranslatedTextBuilder;

import java.util.Collection;
import java.util.function.Consumer;

public final class GUAPIHelper {
    private static final Text EMPTY_TEXT = text("");
    public static final Align TOP_LEFT = Align.TOP_LEFT;
    public static final Align TOP_CENTER = Align.TOP_CENTER;
    public static final Align TOP_RIGHT = Align.TOP_RIGHT;
    public static final Align CENTER_LEFT = Align.CENTER_LEFT;
    public static final Align CENTER = Align.CENTER;
    public static final Align CENTER_RIGHT = Align.CENTER_RIGHT;
    public static final Align BOTTOM_LEFT = Align.BOTTOM_LEFT;
    public static final Align BOTTOM_CENTER = Align.BOTTOM_CENTER;
    public static final Align BOTTOM_RIGHT = Align.BOTTOM_RIGHT;
    public static final Align.Horizontal LEFT = Align.Horizontal.LEFT;
    public static final Align.Horizontal CENTER_H = Align.Horizontal.CENTER;
    public static final Align.Horizontal RIGHT = Align.Horizontal.RIGHT;
    public static final Align.Vertical TOP = Align.Vertical.TOP;
    public static final Align.Vertical CENTER_V = Align.Vertical.CENTER;
    public static final Align.Vertical BOTTOM = Align.Vertical.BOTTOM;

    private static NodeFactory node() {
        return GUAPI.getNodeFactory();
    }

    public static ButtonBuilder button() {
        return node().button();
    }

    public static ButtonBuilder button(String text) {
        return node().button(text);
    }

    public static ButtonBuilder button(Text text) {
        return node().button(text);
    }

    public static ButtonBuilder button(Consumer<ButtonBuilder> with) {
        return node().button(with);
    }

    public static CheckBoxBuilder checkBox() {
        return node().checkBox();
    }

    public static CheckBoxBuilder checkBox(String text) {
        return node().checkBox(text);
    }

    public static CheckBoxBuilder checkBox(Text text) {
        return node().checkBox(text);
    }

    public static CheckBoxBuilder checkBox(Consumer<CheckBoxBuilder> with) {
        return node().checkBox(with);
    }

    public static HBoxBuilder hBox() {
        return node().hBox();
    }

    public static HBoxBuilder hBox(int spacing) {
        return node().hBox(spacing);
    }

    public static HBoxBuilder hBox(Node... children) {
        return node().hBox(children);
    }

    public static HBoxBuilder hBox(Collection<? extends Node> children) {
        return node().hBox(children);
    }

    public static HBoxBuilder hBox(int spacing, Node... children) {
        return node().hBox(spacing, children);
    }

    public static HBoxBuilder hBox(int spacing, Collection<? extends Node> children) {
        return node().hBox(spacing, children);
    }

    public static HBoxBuilder hBox(Consumer<HBoxBuilder> with) {
        return node().hBox(with);
    }

    public static ImageViewBuilder imageView(String id) {
        return node().imageView(id);
    }

    public static ImageViewBuilder imageView(String id, int imageWidth, int imageHeight) {
        return node().imageView(id, imageWidth, imageHeight);
    }

    public static ImageViewBuilder imageView(String id, Consumer<ImageViewBuilder> with) {
        return node().imageView(id, with);
    }

    public static LabelBuilder label() {
        return node().label();
    }

    public static LabelBuilder label(String text) {
        return node().label(text);
    }

    public static LabelBuilder label(Text text) {
        return node().label(text);
    }

    public static LabelBuilder label(String text, boolean shadow) {
        return node().label(text, shadow);
    }

    public static LabelBuilder label(Text text, boolean shadow) {
        return node().label(text, shadow);
    }

    public static LabelBuilder label(Consumer<LabelBuilder> with) {
        return node().label(with);
    }

    public static <E> ListViewBuilder<E> listView(Class<E> eClass) {
        return node().listView(eClass);
    }

    public static <E> ListViewBuilder<E> listView(Class<E> eClass, int itemHeight) {
        return node().listView(eClass, itemHeight);
    }

    public static <E> ListViewBuilder<E> listView(int itemHeight, E... items) {
        return node().listView(itemHeight, items);
    }

    public static <E> ListViewBuilder<E> listView(int itemHeight, Collection<? extends E> items) {
        return node().listView(itemHeight, items);
    }

    public static <E> ListViewBuilder<E> listView(Class<E> eClass, Consumer<ListViewBuilder<E>> with) {
        return node().listView(eClass, with);
    }

    public static TextFieldBuilder textField() {
        return node().textField();
    }

    public static TextFieldBuilder textField(String value) {
        return node().textField(value);
    }

    public static TextFieldBuilder textField(String label, String value) {
        return node().textField(label, value);
    }

    public static TextFieldBuilder textField(Text label, String value) {
        return node().textField(label, value);
    }

    public static TextFieldBuilder textField(Consumer<TextFieldBuilder> with) {
        return node().textField(with);
    }

    public static TexturedButtonBuilder texturedButton(String id, boolean drawButton) {
        return node().texturedButton(id, drawButton);
    }

    public static TexturedButtonBuilder texturedButton(String id, int imageWidth, int imageHeight, boolean drawButton) {
        return node().texturedButton(id, imageWidth, imageHeight, drawButton);
    }

    public static TexturedButtonBuilder texturedButton(String id, boolean drawButton, Consumer<TexturedButtonBuilder> with) {
        return node().texturedButton(id, drawButton, with);
    }

    public static <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(Class<E> eClass) {
        return node().treeView(eClass);
    }

    public static <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(Class<E> eClass, int itemHeight) {
        return node().treeView(eClass, itemHeight);
    }

    public static <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(int itemHeight, E root) {
        return node().treeView(itemHeight, root);
    }

    public static <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(Class<E> eClass, Consumer<TreeViewBuilder<E>> with) {
        return node().treeView(eClass, with);
    }

    public static VBoxBuilder vBox() {
        return node().vBox();
    }

    public static VBoxBuilder vBox(int spacing) {
        return node().vBox(spacing);
    }

    public static VBoxBuilder vBox(Node... children) {
        return node().vBox(children);
    }

    public static VBoxBuilder vBox(Collection<? extends Node> children) {
        return node().vBox(children);
    }

    public static VBoxBuilder vBox(int spacing, Collection<? extends Node> children) {
        return node().vBox(spacing, children);
    }

    public static VBoxBuilder vBox(int spacing, Node... children) {
        return node().vBox(spacing, children);
    }

    public static VBoxBuilder vBox(Consumer<VBoxBuilder> with) {
        return node().vBox(with);
    }

    public static SceneBuilder scene() {
        return node().scene();
    }

    public static SceneBuilder scene(Node root) {
        return node().scene(root);
    }

    public static SceneBuilder scene(Node root, boolean fullScreen) {
        return node().scene(root, fullScreen);
    }

    public static SceneBuilder scene(Node root, boolean fullScreen, boolean texturedBackground) {
        return node().scene(root, fullScreen, texturedBackground);
    }

    public static SceneBuilder scene(Consumer<SceneBuilder> with) {
        return node().scene(with);
    }

    public static Text emptyText() {
        return EMPTY_TEXT;
    }

    public static PlainTextBuilder text(String text) {
        return new PlainTextImpl(text);
    }

    public static TranslatedTextBuilder translated(String translate) {
        return new TranslatedTextImpl(translate);
    }

    public static Text.Event event(String action, String value) {
        return new AbstractText.EventImpl(action, value);
    }

    public static Text.Event link(String url) {
        return new AbstractText.EventImpl("open_url", url);
    }

    public static int rgb(int r, int g, int b) {
        return Color.rgb(r, g, b);
    }

    public static int rgba(int r, int g, int b, int a) {
        return Color.rgba(r, g, b, a);
    }

    public static Insets top(int top) {
        return new Insets(top, 0, 0, 0);
    }

    public static Insets right(int right) {
        return new Insets(0, right, 0, 0);
    }

    public static Insets bottom(int bottom) {
        return new Insets(0, 0, bottom, 0);
    }

    public static Insets left(int left) {
        return new Insets(0, 0, 0, left);
    }

    public static <M, V extends View, C extends Controller<M, V>> Node mvc(MVC<M, V, C> mvc, M model) {
        return mvc.createViewAndBind(model).getRoot();
    }
}
