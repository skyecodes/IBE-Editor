package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.api.common.Attribute;
import com.github.franckyi.guapi.api.mvc.Model;

public class AttributeItemModel implements Model {
    private final Attribute attribute;

    public AttributeItemModel(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute getAttribute() {
        return attribute;
    }
}
