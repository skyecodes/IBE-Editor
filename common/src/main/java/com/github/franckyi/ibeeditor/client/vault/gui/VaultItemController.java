package com.github.franckyi.ibeeditor.client.vault.gui;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.client.vault.VaultItemModel;

public abstract class VaultItemController<M extends VaultItemModel, V extends VaultItemView> extends AbstractController<M, V> {
    public VaultItemController(M model, V view) {
        super(model, view);
    }
}
