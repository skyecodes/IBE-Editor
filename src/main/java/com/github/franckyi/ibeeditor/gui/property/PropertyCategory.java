package com.github.franckyi.ibeeditor.gui.property;

import com.github.franckyi.ibeeditor.gui.child.GuiPropertyList;
import com.github.franckyi.ibeeditor.gui.child.IGuiPropertyListFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class PropertyCategory<T extends BaseProperty<?>> {

    private String categoryName;
    private List<T> properties = new ArrayList<>();
    private IGuiPropertyListFactory<T> guiFactory;
    private Consumer<List<T>> action;

    public PropertyCategory(String categoryName) {
        this(categoryName, (parent, mc, properties1) -> new GuiPropertyList<>(parent, mc, 25, properties1), properties -> properties.forEach(BaseProperty::apply));
    }

    public PropertyCategory(String categoryName, IGuiPropertyListFactory<T> guiFactory) {
        this(categoryName, guiFactory, properties -> properties.forEach(BaseProperty::apply));
    }

    public PropertyCategory(String categoryName, Consumer<List<T>> action) {
        this(categoryName, (parent, mc, properties1) -> new GuiPropertyList<>(parent, mc, 25, properties1), action);
    }

    public PropertyCategory(String categoryName, IGuiPropertyListFactory<T> guiFactory, Consumer<List<T>> action) {
        this.categoryName = categoryName;
        this.guiFactory = guiFactory;
        this.action = action;
    }

    @SafeVarargs
    public final PropertyCategory<T> addAll(T... properties) {
        addAll(Arrays.asList(properties));
        return this;
    }

    public PropertyCategory<T> addAll(Collection<T> properties) {
        this.properties.addAll(properties);
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<T> getProperties() {
        return properties;
    }

    public IGuiPropertyListFactory<T> getGuiFactory() {
        return guiFactory;
    }

    public void apply() {
        action.accept(properties);
    }
}
