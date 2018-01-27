package com.github.franckyi.ibeeditor.gui.property;

import com.github.franckyi.ibeeditor.gui.child.GuiPropertyList;
import com.github.franckyi.ibeeditor.gui.child.IGuiPropertyListFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PropertyCategory {

    private static final IGuiPropertyListFactory DEFAULT_GUI_FACTORY = GuiPropertyList::new;
    private static final Consumer<List<BaseProperty<?>>> DEFAULT_ACTION = properties -> properties.forEach(BaseProperty::apply);

    private String categoryName;
    private List<BaseProperty<?>> properties = new ArrayList<>();
    private IGuiPropertyListFactory guiFactory;
    private Consumer<List<BaseProperty<?>>> action;

    public PropertyCategory(String categoryName) {
        this(categoryName, DEFAULT_GUI_FACTORY, DEFAULT_ACTION);
    }

    public PropertyCategory(String categoryName, IGuiPropertyListFactory guiFactory) {
        this(categoryName, guiFactory, DEFAULT_ACTION);
    }

    public PropertyCategory(String categoryName, Consumer<List<BaseProperty<?>>> action) {
        this(categoryName, DEFAULT_GUI_FACTORY, action);
    }

    public PropertyCategory(String categoryName, IGuiPropertyListFactory guiFactory, Consumer<List<BaseProperty<?>>> action) {
        this.categoryName = categoryName;
        this.guiFactory = guiFactory;
        this.action = action;
    }

    public PropertyCategory addAll(BaseProperty<?>... properties) {
        addAll(Arrays.asList(properties));
        return this;
    }

    public PropertyCategory addAll(Collection<? extends BaseProperty<?>> properties) {
        this.properties.addAll(properties);
        return this;
    }

    public <E> PropertyCategory addEnum(String name, Collection<E> values, Supplier<E> value, Consumer<E> apply) {
        properties.add(new EnumProperty<>(name, values, value, apply));
        return this;
    }

    public <E> PropertyCategory addEnum(String name, Collection<E> values, Supplier<E> value) {
        return this.addEnum(name, values, value, v -> {});
    }

    public PropertyCategory addString(String name, Supplier<String> value, Consumer<String> apply) {
        properties.add(new StringProperty(name, value, apply));
        return this;
    }

    public PropertyCategory addString(String name, Supplier<String> value) {
        return this.addString(name, value, s -> {});
    }

    public PropertyCategory addInteger(String name, Supplier<Integer> value, Consumer<Integer> apply) {
        properties.add(new IntegerProperty(name, value, apply));
        return this;
    }

    public PropertyCategory addInteger(String name, Supplier<Integer> value) {
        return this.addInteger(name, value, i -> {});
    }

    public PropertyCategory addDouble(String name, Supplier<Double> value, Consumer<Double> apply) {
        properties.add(new DoubleProperty(name, value, apply));
        return this;
    }

    public PropertyCategory addDouble(String name, Supplier<Double> value) {
        return this.addDouble(name, value, d -> {});
    }

    public PropertyCategory addBoolean(String name, Supplier<Boolean> value, Consumer<Boolean> apply) {
        properties.add(new BooleanProperty(name, value, apply));
        return this;
    }

    public PropertyCategory addBoolean(String name, Supplier<Boolean> value) {
        return this.addBoolean(name, value, b -> {});
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<BaseProperty<?>> getProperties() {
        return properties;
    }

    public IGuiPropertyListFactory getGuiFactory() {
        return guiFactory;
    }

    public void apply() {
        action.accept(properties);
    }
}
