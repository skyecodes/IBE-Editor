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
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.text.TextFactory;
import com.github.franckyi.minecraft.util.common.TextFormatting;
import com.github.franckyi.minecraft.util.common.TextStyle;

import java.util.Collection;
import java.util.function.Consumer;

public final class GUAPIHelper {
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

    public static ButtonBuilder button(String text, TextStyle style) {
        return node().button(text, style);
    }

    public static ButtonBuilder button(String text, TextFormatting... formatting) {
        return node().button(text, formatting);
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

    public static CheckBoxBuilder checkBox(String text, TextStyle style) {
        return node().checkBox(text, style);
    }

    public static CheckBoxBuilder checkBox(String text, TextFormatting... formatting) {
        return node().checkBox(text, formatting);
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

    public static LabelBuilder label(String text, TextStyle style) {
        return node().label(text, style);
    }

    public static LabelBuilder label(String text, TextFormatting... formatting) {
        return node().label(text, formatting);
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

    private static TextFactory text() {
        return Minecraft.getCommon().getTextFactory();
    }

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
