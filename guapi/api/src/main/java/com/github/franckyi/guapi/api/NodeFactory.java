package com.github.franckyi.guapi.api;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.guapi.api.node.builder.*;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface NodeFactory {
    ButtonBuilder createButton();

    ButtonBuilder createButton(String text);

    ButtonBuilder createButton(Text text);

    ButtonBuilder createButton(Consumer<ButtonBuilder> with);

    CheckBoxBuilder createCheckBox();

    CheckBoxBuilder createCheckBox(String text);

    CheckBoxBuilder createCheckBox(Text text);

    CheckBoxBuilder createCheckBox(Consumer<CheckBoxBuilder> with);

    <E extends Enum<E>> EnumButtonBuilder<E> createEnumButton(Class<? extends E> enumClass);

    <E extends Enum<E>> EnumButtonBuilder<E> createEnumButton(E value);

    HBoxBuilder createHBox();

    HBoxBuilder createHBox(int spacing);

    HBoxBuilder createHBox(Node... children);

    HBoxBuilder createHBox(Collection<? extends Node> children);

    HBoxBuilder createHBox(int spacing, Node... children);

    HBoxBuilder createHBox(int spacing, Collection<? extends Node> children);

    HBoxBuilder createHBox(Consumer<HBoxBuilder> with);

    ImageViewBuilder createImageView(String id);

    ImageViewBuilder createImageView(String id, int imageWidth, int imageHeight);

    ImageViewBuilder createImageView(String id, Consumer<ImageViewBuilder> with);

    ItemViewBuilder createItemView();

    ItemViewBuilder createItemView(Item id);

    ItemViewBuilder createItemView(Consumer<ItemViewBuilder> with);

    LabelBuilder createLabel();

    LabelBuilder createLabel(String text);

    LabelBuilder createLabel(Text text);

    LabelBuilder createLabel(String text, boolean shadow);

    LabelBuilder createLabel(Text text, boolean shadow);

    LabelBuilder createLabel(Consumer<LabelBuilder> with);

    <E> ListViewBuilder<E> createListView(Class<E> eClass);

    <E> ListViewBuilder<E> createListView(Class<E> eClass, int itemHeight);

    <E> ListViewBuilder<E> createListView(int itemHeight, E... items);

    <E> ListViewBuilder<E> createListView(int itemHeight, Collection<? extends E> items);

    <E> ListViewBuilder<E> createListView(Class<E> eClass, Consumer<ListViewBuilder<E>> with);

    SliderBuilder createSlider();

    SliderBuilder createSlider(double value);

    SliderBuilder createSlider(double value, double minValue, double maxValue);

    SliderBuilder createSlider(double value, double minValue, double maxValue, double step);

    SpriteViewBuilder createSpriteView();

    SpriteViewBuilder createSpriteView(Supplier<Object> spriteFactory);

    SpriteViewBuilder createSpriteView(Supplier<Object> spriteFactory, int imageWidth, int imageHeight);

    TextFieldBuilder createTextField();

    TextFieldBuilder createTextField(String value);

    TextFieldBuilder createTextField(String label, String value);

    TextFieldBuilder createTextField(Text label, String value);

    TextFieldBuilder createTextField(Consumer<TextFieldBuilder> with);

    TextAreaBuilder createTextArea();

    TextAreaBuilder createTextArea(String value);

    TextAreaBuilder createTextArea(String label, String value);

    TextAreaBuilder createTextArea(Text label, String value);

    TextAreaBuilder createTextArea(Consumer<TextAreaBuilder> with);

    TexturedButtonBuilder createTexturedButton(String id, boolean drawButton);

    TexturedButtonBuilder createTexturedButton(String id, int imageWidth, int imageHeight, boolean drawButton);

    TexturedButtonBuilder createTexturedButton(String id, boolean drawButton, Consumer<TexturedButtonBuilder> with);

    TexturedToggleButtonBuilder createTexturedToggleButton(String id, boolean drawButton);

    TexturedToggleButtonBuilder createTexturedToggleButton(String id, int imageWidth, int imageHeight, boolean drawButton);

    TexturedToggleButtonBuilder createTexturedToggleButton(String id, boolean drawButton, Consumer<TexturedToggleButtonBuilder> with);

    ToggleButtonBuilder createToggleButton();

    ToggleButtonBuilder createToggleButton(String text);

    ToggleButtonBuilder createToggleButton(Text text);

    ToggleButtonBuilder createToggleButton(Consumer<ToggleButtonBuilder> with);

    <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> createTreeView(Class<E> eClass);

    <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> createTreeView(Class<E> eClass, int itemHeight);

    <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> createTreeView(int itemHeight, E root);

    <E extends TreeView.TreeItem<E>> TreeViewBuilder<E> createTreeView(Class<E> eClass, Consumer<TreeViewBuilder<E>> with);

    VBoxBuilder createVBox();

    VBoxBuilder createVBox(int spacing);

    VBoxBuilder createVBox(Node... children);

    VBoxBuilder createVBox(Collection<? extends Node> children);

    VBoxBuilder createVBox(int spacing, Collection<? extends Node> children);

    VBoxBuilder createVBox(int spacing, Node... children);

    VBoxBuilder createVBox(Consumer<VBoxBuilder> with);

    SceneBuilder createScene();

    SceneBuilder createScene(Node root);

    SceneBuilder createScene(Node root, boolean fullScreen);

    SceneBuilder createScene(Node root, boolean fullScreen, boolean texturedBackground);

    SceneBuilder createScene(Consumer<SceneBuilder> with);
}
