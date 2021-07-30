package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.CategoryView;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class CategoryController extends AbstractController<CategoryModel, CategoryView> {
    public CategoryController(CategoryModel model, CategoryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        model.selectedProperty().addListener(this::updateLabel);
        model.validProperty().addListener(this::updateLabel);
        model.nameProperty().addListener(this::updateLabel);
        view.getLabel().hoveredProperty().addListener(this::updateLabel);
        view.getLabel().onAction(() -> model.getEditor().setSelectedCategory(model));
        updateLabel();
    }

    private void updateLabel() {
        Text text = translated(model.getName());
        if (view.getLabel().isHovered()) {
            text.setUnderlined(true);
        }
        if (model.isSelected()) {
            if (model.isValid()) {
                text.setColor("yellow");
                text.setBold(true);
            } else {
                text.setColor("red");
                text.setBold(true);
            }
        } else if (!model.isValid()) {
            text.setColor("red");
        }
        view.getLabel().setLabel(text);
    }
}
