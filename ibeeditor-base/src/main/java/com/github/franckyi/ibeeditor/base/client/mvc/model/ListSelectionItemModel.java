package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.api.Game;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.guapi.api.mvc.Model;

import java.util.Locale;

public class ListSelectionItemModel implements Model {
    private final String name;
    private final IIdentifier id;

    public ListSelectionItemModel(String name, IIdentifier id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public IIdentifier getId() {
        return id;
    }

    public boolean matches(String s) {
        if (s.isEmpty()) {
            return true;
        }
        String lower = s.toLowerCase(Locale.ROOT);
        return id.toString().toLowerCase(Locale.ROOT).contains(lower) || Game.getCommon().translate(name).toLowerCase(Locale.ROOT).contains(lower);
    }

    public Type getType() {
        return Type.DEFAULT;
    }

    public enum Type {
        DEFAULT, ITEM, IMAGE, SPRITE
    }
}
