package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ListSelectionItemView;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ListSelectionItemController<M extends ListSelectionItemModel, V extends ListSelectionItemView> extends AbstractController<M, V> {
    public ListSelectionItemController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getNameLabel().setLabel(translated(model.getName()).bold());
        view.getIdLabel().setLabel(text(model.getId()).italic());
    }
}
