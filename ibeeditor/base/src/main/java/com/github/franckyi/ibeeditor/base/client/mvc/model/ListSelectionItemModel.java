package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.guapi.api.mvc.Model;

import java.util.Locale;

public class ListSelectionItemModel implements Model {
    private final String name;
    private final String id;

    public ListSelectionItemModel(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean matches(String s) {
        if (s.isEmpty()) {
            return true;
        }
        String lower = s.toLowerCase(Locale.ROOT);
        return id.toLowerCase(Locale.ROOT).contains(lower) || Game.getCommon().translate(name).toLowerCase(Locale.ROOT).contains(lower);
    }

    public Type getType() {
        return Type.DEFAULT;
    }

    public enum Type {
        DEFAULT, ITEM, IMAGE, SPRITE;
    }
}
