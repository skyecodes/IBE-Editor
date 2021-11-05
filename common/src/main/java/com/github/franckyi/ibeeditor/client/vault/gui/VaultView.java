package com.github.franckyi.ibeeditor.client.vault.gui;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.client.util.gui.IBEView;
import com.github.franckyi.ibeeditor.client.vault.VaultItemModel;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class VaultView extends IBEView {
    @Override
    protected Node createEditor() {
        return vBox(root -> {
            root.add(listView(VaultItemModel.class, 25).renderer(item -> mvc(VaultItemMVC.INSTANCE, item)), 1);
        });
    }
}
