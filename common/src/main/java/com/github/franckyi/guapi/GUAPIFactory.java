package com.github.franckyi.guapi;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.Text;
import com.github.franckyi.gamehooks.api.common.TextFactory;
import com.github.franckyi.gamehooks.util.common.TextFormatting;
import com.github.franckyi.gamehooks.util.common.TextStyle;
import com.github.franckyi.guapi.api.mvc.Controller;
import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.guapi.api.node.builder.*;
import com.github.franckyi.guapi.impl.node.*;
import com.github.franckyi.guapi.util.Align;
import com.github.franckyi.guapi.util.Color;
import com.github.franckyi.guapi.util.Insets;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public final class GUAPIFactory {
    public static ButtonBuilder button() {
        return new ButtonImpl();
    }

    public static ButtonBuilder button(String text) {
        return new ButtonImpl(text);
    }

    public static ButtonBuilder button(String text, TextStyle style) {
        return button(text(text, style));
    }

    public static ButtonBuilder button(String text, TextFormatting... formatting) {
        return button(text(text, formatting));
    }

    public static ButtonBuilder button(Text text) {
        return new ButtonImpl(text);
    }

    public static ButtonBuilder button(Consumer<ButtonBuilder> with) {
        return button().with(with);
    }

    public static CheckBoxBuilder checkBox() {
        return new CheckBoxImpl();
    }

    public static CheckBoxBuilder checkBox(String text) {
        return new CheckBoxImpl(text);
    }

    public static CheckBoxBuilder checkBox(String text, TextStyle style) {
        return checkBox(text(text, style));
    }

    public static CheckBoxBuilder checkBox(String text, TextFormatting... formatting) {
        return checkBox(text(text, formatting));
    }

    public static CheckBoxBuilder checkBox(Text text) {
        return new CheckBoxImpl(text);
    }

    public static CheckBoxBuilder checkBox(Consumer<CheckBoxBuilder> with) {
        return checkBox().with(with);
    }

    public static HBoxBuilder hBox() {
        return new HBoxImpl();
    }

    public static HBoxBuilder hBox(int spacing) {
        return new HBoxImpl(spacing);
    }

    public static HBoxBuilder hBox(Node... children) {
        return new HBoxImpl(children);
    }

    public static HBoxBuilder hBox(Collection<? extends Node> children) {
        return new HBoxImpl(children);
    }

    public static HBoxBuilder hBox(int spacing, Node... children) {
        return new HBoxImpl(spacing, children);
    }

    public static HBoxBuilder hBox(int spacing, Collection<? extends Node> children) {
        return new HBoxImpl(spacing, children);
    }

    public static HBoxBuilder hBox(Consumer<HBoxBuilder> with) {
        return hBox().with(with);
    }

    public static ImageViewBuilder imageView(String id) {
        return new ImageViewImpl(id);
    }

    public static ImageViewBuilder imageView(String id, int imageWidth, int imageHeight) {
        return new ImageViewImpl(id, imageWidth, imageHeight);
    }

    public static ImageViewBuilder imageView(String id, Consumer<ImageViewBuilder> with) {
        return imageView(id).with(with);
    }

    public static LabelBuilder label() {
        return new LabelImpl();
    }

    public static LabelBuilder label(String text) {
        return new LabelImpl(text);
    }

    public static LabelBuilder label(String text, TextStyle style) {
        return label(text(text, style));
    }

    public static LabelBuilder label(String text, TextFormatting... formatting) {
        return label(text(text, formatting));
    }

    public static LabelBuilder label(Text text) {
        return new LabelImpl(text);
    }

    public static LabelBuilder label(String text, boolean shadow) {
        return new LabelImpl(text, shadow);
    }

    public static LabelBuilder label(Text text, boolean shadow) {
        return new LabelImpl(text, shadow);
    }

    public static LabelBuilder label(Consumer<LabelBuilder> with) {
        return label().with(with);
    }

    public static <E> ListViewBuilder<E> listView(Class<E> eClass) {
        return new ListViewImpl<>();
    }

    public static <E> ListViewBuilder<E> listView(Class<E> eClass, int itemHeight) {
        return new ListViewImpl<>(itemHeight);
    }

    @SafeVarargs
    public static <E> ListViewBuilder<E> listView(int itemHeight, E... items) {
        return new ListViewImpl<>(itemHeight, items);
    }

    public static <E> ListViewBuilder<E> listView(int itemHeight, Collection<? extends E> items) {
        return new ListViewImpl<>(itemHeight, items);
    }

    public static <E> ListViewBuilder<E> listView(Class<E> eClass, Consumer<ListViewBuilder<E>> with) {
        return listView(eClass).with(with);
    }

    public static TextFieldBuilder textField() {
        return new TextFieldImpl();
    }

    public static TextFieldBuilder textField(String value) {
        return new TextFieldImpl(value);
    }

    public static TextFieldBuilder textField(String label, String value) {
        return new TextFieldImpl(label, value);
    }

    public static TextFieldBuilder textField(Text label, String value) {
        return new TextFieldImpl(label, value);
    }

    public static TextFieldBuilder textField(Consumer<TextFieldBuilder> with) {
        return textField().with(with);
    }

    public static TexturedButtonBuilder texturedButton(String id, boolean drawButton) {
        return new TexturedButtonImpl(id, drawButton);
    }

    public static TexturedButtonBuilder texturedButton(String id, int imageWidth, int imageHeight, boolean drawButton) {
        return new TexturedButtonImpl(id, imageWidth, imageHeight, drawButton);
    }

    public static TexturedButtonBuilder texturedButton(String id, boolean drawButton, Consumer<TexturedButtonBuilder> with) {
        return texturedButton(id, drawButton).with(with);
    }

    public static <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(Class<E> eClass) {
        return new TreeViewImpl<>();
    }

    public static <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(Class<E> eClass, int itemHeight) {
        return new TreeViewImpl<>(itemHeight);
    }

    public static <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(int itemHeight, E root) {
        return new TreeViewImpl<>(itemHeight, root);
    }

    public static <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(Class<E> eClass, Consumer<TreeViewBuilder<E>> with) {
        return treeView(eClass).with(with);
    }

    public static VBoxBuilder vBox() {
        return new VBoxImpl();
    }

    public static VBoxBuilder vBox(int spacing) {
        return new VBoxImpl(spacing);
    }

    public static VBoxBuilder vBox(Node... children) {
        return new VBoxImpl(children);
    }

    public static VBoxBuilder vBox(Collection<? extends Node> children) {
        return new VBoxImpl(children);
    }

    public static VBoxBuilder vBox(int spacing, Collection<? extends Node> children) {
        return new VBoxImpl(spacing, children);
    }

    public static VBoxBuilder vBox(int spacing, Node... children) {
        return new VBoxImpl(spacing, children);
    }

    public static VBoxBuilder vBox(Consumer<VBoxBuilder> with) {
        return vBox().with(with);
    }

    public static SceneBuilder scene() {
        return new SceneImpl();
    }

    public static SceneBuilder scene(Node root) {
        return new SceneImpl(root);
    }

    public static SceneBuilder scene(Node root, boolean fullScreen) {
        return new SceneImpl(root, fullScreen);
    }

    public static SceneBuilder scene(Node root, boolean fullScreen, boolean texturedBackground) {
        return new SceneImpl(root, fullScreen, texturedBackground);
    }

    public static SceneBuilder scene(Consumer<SceneBuilder> with) {
        return scene().with(with);
    }

    public static final TextFormatting BLACK = TextFormatting.BLACK;
    public static final TextFormatting DARK_BLUE = TextFormatting.DARK_BLUE;
    public static final TextFormatting DARK_GREEN = TextFormatting.DARK_GREEN;
    public static final TextFormatting DARK_AQUA = TextFormatting.DARK_AQUA;
    public static final TextFormatting DARK_RED = TextFormatting.DARK_RED;
    public static final TextFormatting DARK_PURPLE = TextFormatting.DARK_PURPLE;
    public static final TextFormatting GOLD = TextFormatting.GOLD;
    public static final TextFormatting GRAY = TextFormatting.GRAY;
    public static final TextFormatting DARK_GRAY = TextFormatting.DARK_GRAY;
    public static final TextFormatting BLUE = TextFormatting.BLUE;
    public static final TextFormatting GREEN = TextFormatting.GREEN;
    public static final TextFormatting AQUA = TextFormatting.AQUA;
    public static final TextFormatting RED = TextFormatting.RED;
    public static final TextFormatting LIGHT_PURPLE = TextFormatting.LIGHT_PURPLE;
    public static final TextFormatting YELLOW = TextFormatting.YELLOW;
    public static final TextFormatting WHITE = TextFormatting.WHITE;
    public static final TextFormatting OBFUSCATED = TextFormatting.OBFUSCATED;
    public static final TextFormatting BOLD = TextFormatting.BOLD;
    public static final TextFormatting STRIKETHROUGH = TextFormatting.STRIKETHROUGH;
    public static final TextFormatting UNDERLINE = TextFormatting.UNDERLINE;
    public static final TextFormatting ITALIC = TextFormatting.ITALIC;
    public static final TextFormatting RESET = TextFormatting.RESET;

    public static Text emptyText() {
        return text().empty();
    }

    public static Text text(String text) {
        return text().text(text);
    }

    public static Text text(String text, TextStyle style) {
        return text().text(text, style);
    }

    public static Text text(String text, TextFormatting... formatting) {
        return text().text(text, formatting);
    }

    public static Text link(String text, String url) {
        return text().link(text, url);
    }

    public static Text link(String text, String url, TextStyle style) {
        return text().link(text, url, style);
    }

    public static Text link(String text, String url, TextFormatting... formatting) {
        return text().link(text, url, formatting);
    }

    public static Text translatedText(String text) {
        return text().translatedText(text);
    }

    public static Text translatedText(String text, TextStyle style) {
        return text().translatedText(text, style);
    }

    public static Text translatedText(String text, TextFormatting... formatting) {
        return text().translatedText(text, formatting);
    }

    public static Text translatedLink(String text, String url) {
        return text().translatedLink(text, url);
    }

    public static Text translatedLink(String text, String url, TextStyle style) {
        return text().translatedLink(text, url, style);
    }

    public static Text translatedLink(String text, String url, TextFormatting... formatting) {
        return text().translatedLink(text, url, formatting);
    }

    private static TextFactory text() {
        return GameHooks.common().textFactory();
    }

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

    private static final Map<Class<?>, MVC<?, ?, ?>> mvcMap = new HashMap<>();

    public static <M, V extends View, C extends Controller<M, V>> void registerMVC(Class<V> viewClass, MVC<M, V, C> mvc) {
        mvcMap.put(viewClass, mvc);
    }

    @SuppressWarnings("unchecked")
    public static <M, V extends View, C extends Controller<M, V>> Node mvc(Class<V> viewClass, M model) {
        MVC<M, V, C> mvc = (MVC<M, V, C>) mvcMap.get(viewClass);
        V view = mvc.createView();
        C controller = mvc.getController();
        controller.init(model, view);
        return view.getRoot();
    }
}
