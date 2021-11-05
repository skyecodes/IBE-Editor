package com.github.franckyi.ibeeditor.client.vault.gui;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.client.vault.VaultModel;

public final class VaultMVC extends SimpleMVC<VaultModel, VaultView, VaultController> {
    public static final VaultMVC INSTANCE = new VaultMVC();

    private VaultMVC() {
        super(VaultView::new, VaultController::new);
    }
}
