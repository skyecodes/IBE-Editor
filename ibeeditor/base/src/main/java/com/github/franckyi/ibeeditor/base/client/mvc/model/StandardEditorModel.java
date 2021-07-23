package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.api.common.text.Text;

import java.util.function.Consumer;

public abstract class StandardEditorModel<T, C extends CategoryModel> extends ListEditorModel<C> {
    private final T target;
    private final Consumer<T> action;
    private final Text disabledTooltip;
    private final String title;

    protected StandardEditorModel(T target, Consumer<T> action, Text disabledTooltip, String title) {
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

    public Text getDisabledTooltip() {
        return disabledTooltip;
    }

    public boolean isDisabled() {
        return getDisabledTooltip() != null;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void apply() {
        getAction().accept(applyChanges());
    }

    protected abstract T applyChanges();
}
