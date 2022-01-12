package com.github.franckyi.ibeeditor.client.screen.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.client.screen.controller.VaultScreenController;
import com.github.franckyi.ibeeditor.client.screen.model.VaultScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.VaultScreenView;

public final class VaultScreenMVC extends SimpleMVC<VaultScreenModel, VaultScreenView, VaultScreenController> {
    public static final VaultScreenMVC INSTANCE = new VaultScreenMVC();

    private VaultScreenMVC() {
        super(VaultScreenView::new, VaultScreenController::new);
    }
}
