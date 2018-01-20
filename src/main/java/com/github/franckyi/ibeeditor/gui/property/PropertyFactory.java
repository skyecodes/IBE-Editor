package com.github.franckyi.ibeeditor.gui.property;

import java.util.*;

public class PropertyFactory {

    private Map<String, List<BaseProperty<?>>> map = new LinkedHashMap<>();

    public Category newCategory(String name) {
        Category category = new Category();
        map.put(name, category.properties);
        return category;
    }

    public class Category {

        private List<BaseProperty<?>> properties = new ArrayList<>();

        public Category addAll(BaseProperty<?>... properties) {
            this.properties.addAll(Arrays.asList(properties));
            return this;
        }

        public Category addString(String name, String value) {
            properties.add(new StringProperty(name, value));
            return this;
        }

        public Category addString(String name) {
            return addString(name, "");
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

    public Map<String, List<BaseProperty<?>>> create() {
        return map;
    }

}
