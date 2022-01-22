package com.github.franckyi.ibeeditor.client.screen.controller.entry;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.client.screen.view.entry.EntryView;

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
        view.getUpButton().disableProperty().bind(model.listIndexProperty().eq(0));
        view.getDownButton().disableProperty().bind(model.listIndexProperty().eq(model.listSizeProperty().substract(1)));
        model.listIndexProperty().addListener(this::updateListButtons);
        updateListButtons(model.getListIndex());
    }

    private void updateListButtons(int listIndex) {
        view.setListButtonsVisible(listIndex >= 0);
    }

    protected void resetModel() {
        model.reset();
    }
}
