package com.github.franckyi.ibeeditor.client.property;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.node.EnumButton;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class PropertyEnum<T> extends EmptyProperty<T> {

    protected EnumButton<T> enumButton;

    public PropertyEnum(String name, T initialValue, Collection<T> values, Consumer<T> action) {
        super(name, initialValue, action);
        enumButton.setValues(new ArrayList<>(values));
        enumButton.setRenderer(t -> StringUtils.capitalize(t.toString().toLowerCase()));
    }

    @Override
    protected void build() {
        super.build();
        this.addAll(enumButton = new EnumButton<>());
        enumButton.setMargin(Insets.right(5));
    }

    @Override
    protected void updateSize(int listWidth) {
        nameLabel.setPrefWidth(listWidth - enumButton.getWidth() - 69);
    }

    @Override
    protected T getValue() {
        return enumButton.getValue();
    }

    @Override
    protected void setValue(T value) {
        enumButton.setValue(value);
    }
}
