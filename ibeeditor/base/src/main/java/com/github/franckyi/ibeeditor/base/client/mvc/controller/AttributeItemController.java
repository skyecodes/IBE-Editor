package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.AttributeItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.AttributeItemView;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class AttributeItemController extends AbstractController<AttributeItemModel, AttributeItemView> {
    public AttributeItemController(AttributeItemModel model, AttributeItemView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getNameLabel().setLabel(translated(model.getAttribute().getName()).bold());
        view.getIdLabel().setLabel(translated(model.getAttribute().getId()).italic());
    }
}
