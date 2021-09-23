package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.guapi.api.mvc.Model;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;

public class ListSelectionItemModel implements Model {
    private final String name;
    private final ResourceLocation id;

    public ListSelectionItemModel(String name, ResourceLocation id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ResourceLocation getId() {
        return id;
    }

    public boolean matches(String s) {
        if (s.isEmpty()) {
            return true;
        }
        String lower = s.toLowerCase(Locale.ROOT);
        return id.toString().toLowerCase(Locale.ROOT).contains(lower) || I18n.get(name).toLowerCase(Locale.ROOT).contains(lower);
    }

    public Type getType() {
        return Type.DEFAULT;
    }

    public enum Type {
        DEFAULT, ITEM, IMAGE, SPRITE
    }
}
