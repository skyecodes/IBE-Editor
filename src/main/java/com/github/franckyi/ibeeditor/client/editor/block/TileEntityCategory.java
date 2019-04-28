package com.github.franckyi.ibeeditor.client.editor.block;

import com.github.franckyi.ibeeditor.client.editor.common.AbstractCategory;

public abstract class TileEntityCategory<T> extends AbstractCategory {

    protected final T t;

    public TileEntityCategory(T t) {
        this.t = t;
    }
}
