package com.github.franckyi.ibeeditor.client.screen.controller;

import com.github.franckyi.ibeeditor.client.screen.model.VaultScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.VaultScreenView;
import com.github.franckyi.ibeeditor.common.ModTexts;

public class VaultScreenController extends CategoryEntryScreenController<VaultScreenModel, VaultScreenView> {
    public VaultScreenController(VaultScreenModel model, VaultScreenView view) {
        super(model, view);
        view.getHeaderLabel().setLabel(ModTexts.title(ModTexts.VAULT.copy()));
    }
}
