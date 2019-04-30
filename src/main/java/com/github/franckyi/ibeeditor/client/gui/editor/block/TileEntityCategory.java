package com.github.franckyi.ibeeditor.client.gui.editor.block;

import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;

public abstract class TileEntityCategory<T> extends AbstractCategory {

    protected final T t;

    public TileEntityCategory(T t) {
        this.t = t;
    }
}
