package com.github.franckyi.ibeeditor.client.vault.gui;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.client.vault.VaultItemModel;

public final class VaultItemMVC implements MVC<VaultItemModel, VaultItemView, VaultItemController<VaultItemModel, VaultItemView>> {
    public static final VaultItemMVC INSTANCE = new VaultItemMVC();

    private VaultItemMVC() {
    }

    @Override
    public VaultItemView setup(VaultItemModel model) {
        return null;
    }
}
