package com.github.franckyi.ibeeditor.client.block;

import com.github.franckyi.ibeeditor.client.Category;

public abstract class TileEntityCategory<T> extends Category {

    protected final BlockEditor editor;
    protected final T t;

    public TileEntityCategory(BlockEditor editor, T t) {
        this.editor = editor;
        this.t = t;
    }
}
