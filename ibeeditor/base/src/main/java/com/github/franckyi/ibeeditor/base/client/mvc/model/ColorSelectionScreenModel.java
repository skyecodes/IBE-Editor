package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.DoubleProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.gameadapter.Color;

import java.util.function.Consumer;

public class ColorSelectionScreenModel implements Model {
    private final Target target;
    private final Consumer<String> action;
    private final StringProperty hexValueProperty = StringProperty.create();
    private final DoubleProperty redValueProperty = DoubleProperty.create();
    private final DoubleProperty greenValueProperty = DoubleProperty.create();
    private final DoubleProperty blueValueProperty = DoubleProperty.create();

    public ColorSelectionScreenModel(Target target, Consumer<String> action) {
        this(target, action, -1);
    }

    public ColorSelectionScreenModel(Target target, Consumer<String> action, int color) {
        if (color < 0) {
            color = Color.fromRGB(1., 1., 1.);
        }
        this.target = target;
        this.action = action;
        setRedValue(Color.getRed(color));
        setGreenValue(Color.getGreen(color));
        setBlueValue(Color.getBlue(color));
    }

    public void apply() {
        action.accept(getHexValue());
    }

    public String getHexValue() {
        return hexValueProperty().getValue();
    }

    public StringProperty hexValueProperty() {
        return hexValueProperty;
    }

    public void setHexValue(String value) {
        hexValueProperty().setValue(value);
    }

    public double getRedValue() {
        return redValueProperty().getValue();
    }

    public DoubleProperty redValueProperty() {
        return redValueProperty;
    }

    public void setRedValue(double value) {
        redValueProperty().setValue(value);
    }

    public double getGreenValue() {
        return greenValueProperty().getValue();
    }

    public DoubleProperty greenValueProperty() {
        return greenValueProperty;
    }

    public void setGreenValue(double value) {
        greenValueProperty().setValue(value);
    }

    public double getBlueValue() {
        return blueValueProperty().getValue();
    }

    public DoubleProperty blueValueProperty() {
        return blueValueProperty;
    }

    public void setBlueValue(double value) {
        blueValueProperty().setValue(value);
    }

    public Target getTarget() {
        return target;
    }

    public enum Target {
        TEXT, POTION, LEATHER_ARMOR
    }
}
