package com.github.franckyi.ibeeditor.gui.property;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PropertyFactory {

    private Map<String, List<BaseProperty<?>>> map = new LinkedHashMap<>();

    public Category newCategory(String name) {
        Category category = new Category();
        map.put(name, category.properties);
        return category;
    }

    public Map<String, List<BaseProperty<?>>> create() {
        return map;
    }

    public class Category {

        private List<BaseProperty<?>> properties = new ArrayList<>();

        public Category addAll(BaseProperty<?>... properties) {
            addAll(Arrays.asList(properties));
            return this;
        }

        public Category addAll(Collection<? extends BaseProperty<?>> properties) {
            this.properties.addAll(properties);
            return this;
        }

        public <E> Category addEnum(String name, Collection<E> values, Supplier<E> value, Consumer<E> apply) {
            this.properties.add(new EnumProperty<>(name, values, value, apply));
            return this;
        }

        public Category addString(String name, Supplier<String> value, Consumer<String> apply) {
            properties.add(new StringProperty(name, value, apply));
            return this;
        }

        public Category addInteger(String name, Supplier<Integer> value, Consumer<Integer> apply) {
            properties.add(new IntegerProperty(name, value, apply));
            return this;
        }

        public Category addDouble(String name, Supplier<Double> value, Consumer<Double> apply) {
            properties.add(new DoubleProperty(name, value, apply));
            return this;
        }

        public Category addBoolean(String name, Supplier<Boolean> value, Consumer<Boolean> apply) {
            properties.add(new BooleanProperty(name, value, apply));
            return this;
        }

        public Category nextCategory(String name) {
            return PropertyFactory.this.newCategory(name);
        }

        public Map<String, List<BaseProperty<?>>> endCategoryAndCreate() {
            return map;
        }

        public PropertyFactory endCategory() {
            return PropertyFactory.this;
        }
    }

}
