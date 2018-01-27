package com.github.franckyi.ibeeditor.gui;

import com.github.franckyi.ibeeditor.gui.property.PropertyCategory;
import net.minecraft.client.gui.GuiScreen;

import java.util.Arrays;

public class GuiTestEditor extends GuiEditor {

    protected GuiTestEditor(GuiScreen parentScreen) {
        super(parentScreen);
        setCategories(Arrays.asList(
                new PropertyCategory("String")
                        .addString("String 1", () -> "Value 1")
                        .addString("String 2", () -> "Value 2"),
                new PropertyCategory("Boolean")
                        .addBoolean("Boolean 1", () -> true, b -> {})
                        .addBoolean("Boolean 2", () -> false, b -> {}),
                new PropertyCategory("Integer")
                        .addInteger("Integer 1", () -> 1, i -> {})
                        .addInteger("Integer 2", () -> 2, i -> {}),
                new PropertyCategory("Double")
                        .addDouble("Double 1", () -> 0.1, d -> {})
                        .addDouble("Double 2", () -> 0.2, d -> {}),
                new PropertyCategory("Enum")
                        .addEnum("Enum 1", Arrays.asList("Value 1", "Value 2", "Value 3"), () -> "Value 1", e -> {}),
                new PropertyCategory("Mix")
                        .addString("String", () -> "Value", s -> {})
                        .addBoolean("Boolean", () -> true, b -> {})
                        .addInteger("Integer", () -> 0, i -> {})
                        .addDouble("Double", () -> 0.0, d -> {})
                        .addEnum("Enum", Arrays.asList("Value 1", "Value 2", "Value 3"), () -> "Value 1", e -> {})
        ));
    }

    protected GuiTestEditor() {
        this(null);
    }

}
