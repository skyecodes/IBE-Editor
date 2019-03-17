package com.github.franckyi.ibeeditor.editor;

import java.util.ArrayList;
import java.util.List;

public class EditorCategory {

    private final List<AbstractProperty> properties;
    private String name;

    public EditorCategory(String name) {
        this.name = name;
        properties = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AbstractProperty> getProperties() {
        return properties;
    }

}
