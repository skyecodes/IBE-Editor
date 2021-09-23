package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.CategoryView;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;

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
        MutableComponent text = model.getName().plainCopy();
        if (view.getLabel().isHovered()) {
            text.withStyle(ChatFormatting.UNDERLINE);
        }
        if (model.isSelected()) {
            text.withStyle(ChatFormatting.BOLD);
            if (model.isValid()) {
                text.withStyle(ChatFormatting.YELLOW);
            } else {
                text.withStyle(ChatFormatting.RED);
            }
        } else if (!model.isValid()) {
            text.withStyle(ChatFormatting.RED);
        }
        view.getLabel().setLabel(text);
    }
}
