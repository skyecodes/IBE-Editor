package com.github.franckyi.ibeeditor.client.editor.common.category;

import com.github.franckyi.ibeeditor.client.editor.common.AbstractCategory;

public abstract class CapabilityCategory<T> extends AbstractCategory {

    protected final T capability;

    public CapabilityCategory(T capability) {
        this.capability = capability;
    }
}
