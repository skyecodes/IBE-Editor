package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.EntryView;

public abstract class EntryController<M extends EntryModel, V extends EntryView> extends AbstractController<M, V> {
    public EntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getUpButton().onAction(() -> model.getCategory().moveEntryUp(model.getListIndex()));
        view.getDownButton().onAction(() -> model.getCategory().moveEntryDown(model.getListIndex()));
        view.getDeleteButton().onAction(() -> model.getCategory().deleteEntry(model.getListIndex()));
        view.getResetButton().setVisible(model.isResetable());
        view.getResetButton().onAction(this::resetModel);
        model.listIndexProperty().addListener(this::updateListButtons);
        updateListButtons(model.getListIndex());
    }

    private void updateListButtons(int listIndex) {
        view.setListButtonsVisible(listIndex >= 0);
        view.getUpButton().setVisible(listIndex != 0);
        view.getDownButton().setVisible(listIndex != model.getListSize() - 1);
    }

    protected void resetModel() {
        model.reset();
    }
}
