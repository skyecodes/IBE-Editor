package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.DoubleProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.guapi.api.mvc.Model;

import java.util.function.Consumer;

public class ColorSelectionScreenModel implements Model {
    private final Target target;
    private final Consumer<String> action;
    private final StringProperty hexValueProperty = DataBindings.getPropertyFactory().createStringProperty();
    private final DoubleProperty redValueProperty = DataBindings.getPropertyFactory().createDoubleProperty();
    private final DoubleProperty greenValueProperty = DataBindings.getPropertyFactory().createDoubleProperty();
    private final DoubleProperty blueValueProperty = DataBindings.getPropertyFactory().createDoubleProperty();

    public ColorSelectionScreenModel(Target target, Consumer<String> action) {
        this(target, action, 255, 255, 255);
    }

    public ColorSelectionScreenModel(Target target, Consumer<String> action, double red, double green, double blue) {
        this.target = target;
        this.action = action;
        setRedValue(red);
        setGreenValue(green);
        setBlueValue(blue);
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
