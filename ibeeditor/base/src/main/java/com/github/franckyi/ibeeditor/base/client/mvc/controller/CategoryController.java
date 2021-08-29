package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.CategoryView;

public class CategoryController extends AbstractController<CategoryModel, CategoryView> {
    public CategoryController(CategoryModel model, CategoryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        model.selectedProperty().addListener(this::updateLabel);
        model.validProperty().addListener(this::updateLabel);
        view.getLabel().hoveredProperty().addListener(this::updateLabel);
        view.getLabel().onAction(() -> model.getEditor().setSelectedCategory(model));
        view.getLabel().setLabel(model.getName());
        updateLabel();
    }

    private void updateLabel() {
        IText text = model.getName().copy();
        if (view.getLabel().isHovered()) {
            text.setUnderlined(true);
        }
        if (model.isSelected()) {
            text.setBold(true);
            if (model.isValid()) {
                text.setColor("yellow");
            } else {
                text.setColor("red");
            }
        } else if (!model.isValid()) {
            text.setColor("red");
        }
        view.getLabel().setLabel(text);
    }
}
