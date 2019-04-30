package com.github.franckyi.ibeeditor.client.gui.editor.base.category;

import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;

public abstract class CapabilityCategory<T> extends AbstractCategory {

    protected final T capability;

    public CapabilityCategory(T capability) {
        this.capability = capability;
    }
}
