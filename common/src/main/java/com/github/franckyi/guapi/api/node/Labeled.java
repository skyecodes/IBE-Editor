package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gamehooks.util.common.Text;

public interface Labeled extends Control {
    Text getLabel();

    ObjectProperty<Text> labelProperty();

    void setLabel(Text value);

    <T> T getLabelComponent();

    <T> ObjectProperty<T> labelComponentProperty();

    <T> void setLabelComponent(T value);
}
