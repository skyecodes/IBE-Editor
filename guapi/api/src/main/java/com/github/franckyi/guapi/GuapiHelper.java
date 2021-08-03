package com.github.franckyi.guapi;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.TextHandler;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.text.TextEvent;
import com.github.franckyi.gameadapter.api.common.text.TextFactory;
import com.github.franckyi.gameadapter.api.common.text.builder.PlainTextBuilder;
import com.github.franckyi.gameadapter.api.common.text.builder.TranslatedTextBuilder;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.guapi.api.NodeFactory;
import com.github.franckyi.guapi.api.mvc.Controller;
import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.guapi.api.node.builder.*;
import com.github.franckyi.guapi.api.util.Align;
import com.github.franckyi.guapi.api.util.Insets;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class GuapiHelper {
    public static final String BLACK = Color.BLACK;
    public static final String DARK_BLUE = Color.DARK_BLUE;
    public static final String DARK_GREEN = Color.DARK_GREEN;
    public static final String DARK_AQUA = Color.DARK_AQUA;
    public static final String DARK_RED = Color.DARK_RED;
    public static final String DARK_PURPLE = Color.DARK_PURPLE;
    public static final String GOLD = Color.GOLD;
    public static final String GRAY = Color.GRAY;
    public static final String DARK_GRAY = Color.DARK_GRAY;
    public static final String BLUE = Color.BLUE;
    public static final String GREEN = Color.GREEN;
    public static final String AQUA = Color.AQUA;
    public static final String RED = Color.RED;
    public static final String LIGHT_PURPLE = Color.LIGHT_PURPLE;
    public static final String YELLOW = Color.YELLOW;
    public static final String WHITE = Color.WHITE;
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
        return Guapi.getNodeFactory();
    }

    private static TextFactory textFactory() {
        return TextHandler.getTextFactory();
    }

    public static ButtonBuilder button() {
        return node().createButton();
    }

    public static ButtonBuilder button(String text) {
        return node().createButton(text);
    }

    public static ButtonBuilder button(Text text) {
        return node().createButton(text);
    }

    public static ButtonBuilder button(Consumer<ButtonBuilder> with) {
        return node().createButton(with);
    }

    public static CheckBoxBuilder checkBox() {
        return node().createCheckBox();
    }

    public static CheckBoxBuilder checkBox(String text) {
        return node().createCheckBox(text);
    }

    public static CheckBoxBuilder checkBox(Text text) {
        return node().createCheckBox(text);
    }

    public static CheckBoxBuilder checkBox(Consumer<CheckBoxBuilder> with) {
        return node().createCheckBox(with);
    }

    public static <E extends Enum<E>> EnumButtonBuilder<E> enumButton(Class<? extends E> enumClass) {
        return node().createEnumButton(enumClass);
    }

    public static <E extends Enum<E>> EnumButtonBuilder<E> enumButton(E value) {
        return node().createEnumButton(value);
    }

    public static HBoxBuilder hBox() {
        return node().createHBox();
    }

    public static HBoxBuilder hBox(int spacing) {
        return node().createHBox(spacing);
    }

    public static HBoxBuilder hBox(Node... children) {
        return node().createHBox(children);
    }

    public static HBoxBuilder hBox(Collection<? extends Node> children) {
        return node().createHBox(children);
    }

    public static HBoxBuilder hBox(int spacing, Node... children) {
        return node().createHBox(spacing, children);
    }

    public static HBoxBuilder hBox(int spacing, Collection<? extends Node> children) {
        return node().createHBox(spacing, children);
    }

    public static HBoxBuilder hBox(Consumer<HBoxBuilder> with) {
        return node().createHBox(with);
    }

    public static ImageViewBuilder imageView(String id) {
        return node().createImageView(id);
    }

    public static ImageViewBuilder imageView(String id, int imageWidth, int imageHeight) {
        return node().createImageView(id, imageWidth, imageHeight);
    }

    public static ImageViewBuilder imageView(String id, Consumer<ImageViewBuilder> with) {
        return node().createImageView(id, with);
    }

    public static ItemViewBuilder itemView() {
        return node().createItemView();
    }

    public static ItemViewBuilder itemView(Item item) {
        return node().createItemView(item);
    }

    public static ItemViewBuilder itemView(Consumer<ItemViewBuilder> with) {
        return node().createItemView(with);
    }

    public static LabelBuilder label() {
        return node().createLabel();
    }

    public static LabelBuilder label(String text) {
        return node().createLabel(text);
    }

    public static LabelBuilder label(Text text) {
        return node().createLabel(text);
    }

    public static LabelBuilder label(String text, boolean shadow) {
        return node().createLabel(text, shadow);
    }

    public static LabelBuilder label(Text text, boolean shadow) {
        return node().createLabel(text, shadow);
    }

    public static LabelBuilder label(Consumer<LabelBuilder> with) {
        return node().createLabel(with);
    }

    public static <E> ListViewBuilder<E> listView(Class<E> eClass) {
        return node().createListView(eClass);
    }

    public static <E> ListViewBuilder<E> listView(Class<E> eClass, int itemHeight) {
        return node().createListView(eClass, itemHeight);
    }

    public static <E> ListViewBuilder<E> listView(int itemHeight, E... items) {
        return node().createListView(itemHeight, items);
    }

    public static <E> ListViewBuilder<E> listView(int itemHeight, Collection<? extends E> items) {
        return node().createListView(itemHeight, items);
    }

    public static <E> ListViewBuilder<E> listView(Class<E> eClass, Consumer<ListViewBuilder<E>> with) {
        return node().createListView(eClass, with);
    }

    public static SliderBuilder slider() {
        return node().createSlider();
    }

    public static SliderBuilder slider(double value) {
        return node().createSlider(value);
    }

    public static SliderBuilder slider(double value, double minValue, double maxValue) {
        return node().createSlider(value, minValue, maxValue);
    }

    public static SliderBuilder slider(double value, double minValue, double maxValue, double step) {
        return node().createSlider(value, minValue, maxValue, step);
    }

    public static SliderBuilder slider(Consumer<SliderBuilder> with) {
        return slider().with(with);
    }

    public static SpriteViewBuilder spriteView() {
        return node().createSpriteView();
    }

    public static SpriteViewBuilder spriteView(Supplier<Object> spriteFactory) {
        return node().createSpriteView(spriteFactory);
    }

    public static SpriteViewBuilder spriteView(Supplier<Object> spriteFactory, int imageWidth, int imageHeight) {
        return node().createSpriteView(spriteFactory, imageWidth, imageHeight);
    }

    public static SpriteViewBuilder spriteView(Consumer<SpriteViewBuilder> with) {
        return spriteView().with(with);
    }

    public static TextFieldBuilder textField() {
        return node().createTextField();
    }

    public static TextFieldBuilder textField(String value) {
        return node().createTextField(value);
    }

    public static TextFieldBuilder textField(String label, String value) {
        return node().createTextField(label, value);
    }

    public static TextFieldBuilder textField(Text label, String value) {
        return node().createTextField(label, value);
    }

    public static TextFieldBuilder textField(Consumer<TextFieldBuilder> with) {
        return node().createTextField(with);
    }

    public static TextAreaBuilder textArea() {
        return node().createTextArea();
    }

    public static TextAreaBuilder textArea(String value) {
        return node().createTextArea(value);
    }

    public static TextAreaBuilder textArea(String label, String value) {
        return node().createTextArea(label, value);
    }

    public static TextAreaBuilder textArea(Text label, String value) {
        return node().createTextArea(label, value);
    }

    public static TextAreaBuilder textArea(Consumer<TextAreaBuilder> with) {
        return node().createTextArea(with);
    }

    public static TexturedButtonBuilder texturedButton(String id, boolean drawButton) {
        return node().createTexturedButton(id, drawButton);
    }

    public static TexturedButtonBuilder texturedButton(String id, int imageWidth, int imageHeight, boolean drawButton) {
        return node().createTexturedButton(id, imageWidth, imageHeight, drawButton);
    }

    public static TexturedButtonBuilder texturedButton(String id, boolean drawButton, Consumer<TexturedButtonBuilder> with) {
        return node().createTexturedButton(id, drawButton, with);
    }

    public static TexturedToggleButtonBuilder texturedToggleButton(String id, boolean drawButton) {
        return node().createTexturedToggleButton(id, drawButton);
    }

    public static TexturedToggleButtonBuilder texturedToggleButton(String id, int imageWidth, int imageHeight, boolean drawButton) {
        return node().createTexturedToggleButton(id, imageWidth, imageHeight, drawButton);
    }

    public static TexturedToggleButtonBuilder texturedToggleButton(String id, boolean drawButton, Consumer<TexturedToggleButtonBuilder> with) {
        return node().createTexturedToggleButton(id, drawButton, with);
    }

    public static ToggleButtonBuilder toggleButton() {
        return node().createToggleButton();
    }

    public static ToggleButtonBuilder toggleButton(String text) {
        return node().createToggleButton(text);
    }

    public static ToggleButtonBuilder toggleButton(Text text) {
        return node().createToggleButton(text);
    }

    public static ToggleButtonBuilder toggleButton(Consumer<ToggleButtonBuilder> with) {
        return node().createToggleButton(with);
    }

    public static <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(Class<E> eClass) {
        return node().createTreeView(eClass);
    }

    public static <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(Class<E> eClass, int itemHeight) {
        return node().createTreeView(eClass, itemHeight);
    }

    public static <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(int itemHeight, E root) {
        return node().createTreeView(itemHeight, root);
    }

    public static <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> treeView(Class<E> eClass, Consumer<TreeViewBuilder<E>> with) {
        return node().createTreeView(eClass, with);
    }

    public static VBoxBuilder vBox() {
        return node().createVBox();
    }

    public static VBoxBuilder vBox(int spacing) {
        return node().createVBox(spacing);
    }

    public static VBoxBuilder vBox(Node... children) {
        return node().createVBox(children);
    }

    public static VBoxBuilder vBox(Collection<? extends Node> children) {
        return node().createVBox(children);
    }

    public static VBoxBuilder vBox(int spacing, Collection<? extends Node> children) {
        return node().createVBox(spacing, children);
    }

    public static VBoxBuilder vBox(int spacing, Node... children) {
        return node().createVBox(spacing, children);
    }

    public static VBoxBuilder vBox(Consumer<VBoxBuilder> with) {
        return node().createVBox(with);
    }

    public static SceneBuilder scene() {
        return node().createScene();
    }

    public static SceneBuilder scene(Node root) {
        return node().createScene(root);
    }

    public static SceneBuilder scene(Node root, boolean fullScreen) {
        return node().createScene(root, fullScreen);
    }

    public static SceneBuilder scene(Node root, boolean fullScreen, boolean texturedBackground) {
        return node().createScene(root, fullScreen, texturedBackground);
    }

    public static SceneBuilder scene(Consumer<SceneBuilder> with) {
        return node().createScene(with);
    }

    public static Text emptyText() {
        return textFactory().createEmptyText();
    }

    public static PlainTextBuilder text() {
        return textFactory().createPlainText();
    }

    public static PlainTextBuilder text(String text) {
        return textFactory().createPlainText(text);
    }

    public static TranslatedTextBuilder translated() {
        return textFactory().createTranslatedText();
    }

    public static TranslatedTextBuilder translated(String translate) {
        return textFactory().createTranslatedText(translate);
    }

    public static TextEvent event(String action, String value) {
        return textFactory().createEvent(action, value);
    }

    public static TextEvent link(String url) {
        return textFactory().createLink(url);
    }

    public static int rgb(int r, int g, int b) {
        return Color.fromRGB(r, g, b);
    }

    public static int rgba(int r, int g, int b, int a) {
        return Color.fromRGBA(r, g, b, a);
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

    public static <M extends Model, V extends View, C extends Controller<M, V>> Node mvc(MVC<M, V, C> mvc, M model) {
        return mvc.setup(model).getRoot();
    }
}
