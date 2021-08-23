package com.github.franckyi.guapi.api;

import com.github.franckyi.gameadapter.api.client.ISprite;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.guapi.api.node.builder.*;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface NodeFactory {
    ButtonBuilder createButton();

    ButtonBuilder createButton(String text);

    ButtonBuilder createButton(IText text);

    ButtonBuilder createButton(Consumer<ButtonBuilder> with);

    CheckBoxBuilder createCheckBox();

    CheckBoxBuilder createCheckBox(String text);

    CheckBoxBuilder createCheckBox(IText text);

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

    ImageViewBuilder createImageView(IIdentifier id);

    ImageViewBuilder createImageView(IIdentifier id, int imageWidth, int imageHeight);

    ImageViewBuilder createImageView(IIdentifier id, Consumer<ImageViewBuilder> with);

    ItemViewBuilder createItemView();

    ItemViewBuilder createItemView(IItemStack id);

    ItemViewBuilder createItemView(Consumer<ItemViewBuilder> with);

    LabelBuilder createLabel();

    LabelBuilder createLabel(String text);

    LabelBuilder createLabel(IText text);

    LabelBuilder createLabel(String text, boolean shadow);

    LabelBuilder createLabel(IText text, boolean shadow);

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

    SpriteViewBuilder createSpriteView(Supplier<ISprite> spriteFactory);

    SpriteViewBuilder createSpriteView(Supplier<ISprite> spriteFactory, int imageWidth, int imageHeight);

    TextFieldBuilder createTextField();

    TextFieldBuilder createTextField(String value);

    TextFieldBuilder createTextField(String label, String value);

    TextFieldBuilder createTextField(IText label, String value);

    TextFieldBuilder createTextField(Consumer<TextFieldBuilder> with);

    TextAreaBuilder createTextArea();

    TextAreaBuilder createTextArea(String value);

    TextAreaBuilder createTextArea(String label, String value);

    TextAreaBuilder createTextArea(IText label, String value);

    TextAreaBuilder createTextArea(Consumer<TextAreaBuilder> with);

    TexturedButtonBuilder createTexturedButton(IIdentifier id, boolean drawButton);

    TexturedButtonBuilder createTexturedButton(IIdentifier id, int imageWidth, int imageHeight, boolean drawButton);

    TexturedButtonBuilder createTexturedButton(IIdentifier id, boolean drawButton, Consumer<TexturedButtonBuilder> with);

    TexturedToggleButtonBuilder createTexturedToggleButton(IIdentifier id, boolean drawButton);

    TexturedToggleButtonBuilder createTexturedToggleButton(IIdentifier id, int imageWidth, int imageHeight, boolean drawButton);

    TexturedToggleButtonBuilder createTexturedToggleButton(IIdentifier id, boolean drawButton, Consumer<TexturedToggleButtonBuilder> with);

    ToggleButtonBuilder createToggleButton();

    ToggleButtonBuilder createToggleButton(String text);

    ToggleButtonBuilder createToggleButton(IText text);

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
