package com.github.franckyi.ibeeditor.base.client.mvc.editor.standard.model;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.model.StandardEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.base.model.AbstractListEditorModel;

import java.util.function.Consumer;

public abstract class AbstractStandardEditorModel<T, C extends EditorCategoryModel> extends AbstractListEditorModel<C> implements StandardEditorModel {
    private final T target;
    private final Consumer<T> action;
    private final Text disabledTooltip;
    private final String title;

    protected AbstractStandardEditorModel(T target, Consumer<T> action, Text disabledTooltip, String title) {
        this.target = target;
        this.action = action;
        this.disabledTooltip = disabledTooltip;
        this.title = title;
    }

    public T getTarget() {
        return target;
    }

    public Consumer<T> getAction() {
        return action;
    }

    @Override
    public Text getDisabledTooltip() {
        return disabledTooltip;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void apply() {
        getAction().accept(applyChanges());
    }

    protected abstract T applyChanges();
}
